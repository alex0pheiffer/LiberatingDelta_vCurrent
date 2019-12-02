package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.regions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link main_menyu_region_map_btn.onRegionMapBtnSelectedListener} interface
 * to handle interaction events.
 * Use the {@link main_menyu_region_map_btn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_menyu_region_map_btn extends Fragment implements MainMenyuRegionBtnInterface{
    private static final String REGION = "region";
    private static final String PL = "pl";

    private regions region;
    private int pl;
    private PL this_pl;
    private TextView mainbtn, regionName;
    private final String THIS_LAYOUT = "MAIN_MENYU_LAYOUT";

    private onRegionMapBtnSelectedListener mListener;
    public void setOnRegionMapSelectedListener(onRegionMapBtnSelectedListener callback  ) {
        this.mListener= callback;
    }
    public interface onRegionMapBtnSelectedListener {
        String getCURRENT_LAYOUT();
        void regionBtnPressed();
        boolean checkLayout(String THIS_LAYOUT);
    }

    public main_menyu_region_map_btn() {
        // Required empty public constructor
    }

    public static main_menyu_region_map_btn newInstance(String region, int pl) {
        main_menyu_region_map_btn fragment = new main_menyu_region_map_btn();
        Bundle args = new Bundle();
        args.putString(REGION, region);
        args.putInt(PL, pl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            System.out.println("gets called: "+savedInstanceState);
            pl = getArguments().getInt(PL);
            this_pl = PL_VendingMachine.getPL(pl);
            region = this_pl.getRegion(getArguments().getString(REGION));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menyu_region_map_btn, container, false);
        mainbtn = view.findViewById(R.id.menyu_regionmap_press);
        regionName = view.findViewById(R.id.menyu_map_img_label);

        mainbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.regionBtnPressed();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRegionMapBtnSelectedListener) {
            mListener = (onRegionMapBtnSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onRegionMapBtnSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void recieveCURRENT_LAYOUT(String PREVIOUS_LAYOUT, String CURRENT_LAYOUT) {
        if (PREVIOUS_LAYOUT.equals(THIS_LAYOUT) && !CURRENT_LAYOUT.equals(THIS_LAYOUT)) {
            //remove the layout from the activity
        }
        else if(!PREVIOUS_LAYOUT.equals(THIS_LAYOUT) && CURRENT_LAYOUT.equals(THIS_LAYOUT)) {
            //add the layout to the activity
        }
    }

    public String getCURRENT_LAYOUT() {
        return THIS_LAYOUT;
    }

    public void setRegion(regions region) {
        this.region = region;
        regionName.setText(this.region.getNom());
    }

    public regions getRegion() {
        return this.region;
    }

    public String toString() {
        return "main_menyu_region_map_btn";
    }
}
