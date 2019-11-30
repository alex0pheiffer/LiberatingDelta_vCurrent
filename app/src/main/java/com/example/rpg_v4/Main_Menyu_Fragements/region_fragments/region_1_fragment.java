package com.example.rpg_v4.Main_Menyu_Fragements.region_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rpg_v4.PL;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;

public class region_1_fragment extends Fragment implements RegionFragmentInterface{
    private static final String PlayerLevel = "pl";

    final String THIS_LAYOUT="REGION_MAP";

    private int pl;
    private regions thisRegion = new Veneland();
    private cityPt[] allCities = PL.getAllCitiesRegion(thisRegion.getNom()); //of this region
    private cityPt[] unlockedCities;
    private LinearLayout MaleficereMansionLayout, ChipperTowneLayout;
    private ImageView MaleficereMansionBtn, ChipperTowneBtn;
    private TextView MaleficereMansionText, ChipperTowneText;

    private onRegion1SelectedListener mListener;

    public region_1_fragment() {}

    public static region_1_fragment newInstance(int pl) {
        System.out.println("newInstance: "+pl);
        region_1_fragment fragment = new region_1_fragment();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        fragment.setArguments(args);
        System.out.println("newInstance: "+fragment.getArguments());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreateHere: "+getArguments()+", "+savedInstanceState);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pl = getArguments().getInt(PlayerLevel);
            System.out.println("onCreate: "+this.pl);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region_1_fragment, container, false);

        System.out.println("onCreateView: "+pl);
        unlockedCities = PL.getCities(pl,thisRegion.getNom());
        this.setViews(view);

        for (int n = 0; n<unlockedCities.length; n++) {
            if (unlockedCities[n].getNom().equals(allCities[0].getNom())) {
                //Maleficere Mansion
                MaleficereMansionBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        //we want to change the display of the cityPt information in the other fragment
                        mListener.MaleficereMansionPressed();
                    }
                });
            }
            if (unlockedCities[n].getNom().equals(allCities[1].getNom())) {
                //Chipper Towne
                ChipperTowneBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        //we want to change the display of the cityPt information in the other fragment
                        System.out.println("ChipperTownePressed");
                        mListener.ChipperTownePressed();
                    }
                });
            }


        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRegion1SelectedListener) {
            mListener = (onRegion1SelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onRegion1SelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onRegion1SelectedListener {
        void MaleficereMansionPressed();
        void ChipperTownePressed();
    }

    public void setViews(View view) {
        MaleficereMansionLayout = view.findViewById(R.id.Maleficere_Mansion_layout);
        MaleficereMansionBtn = view.findViewById(R.id.Maleficere_Mansion_img);
        MaleficereMansionText = view.findViewById(R.id.Maleficere_Mansion_tag);
        ChipperTowneLayout = view.findViewById(R.id.Chipper_Towne_layout);
        ChipperTowneBtn = view.findViewById(R.id.Chipper_Towne_img);
        ChipperTowneText = view.findViewById(R.id.Chipper_Towne_tag);
    }

    public String getCURRENT_LAYOUT() {
        return THIS_LAYOUT;
    }

    public regions getRegion() {return thisRegion;}

    public String toString() {
        return "region_1_fragment";
    }
}
