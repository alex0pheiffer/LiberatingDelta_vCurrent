package com.example.rpg_v4.Main_Menyu_Fragements.region_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rpg_v4.R;


public class region_5_fragment extends Fragment {
    private static final String PlayerLevel= "pl";

    final String THIS_LAYOUT="REGION_MAP_LAYOUT";

    private int pl;

    private LinearLayout gobtn;

    private OnFragmentInteractionListener mListener;

    public region_5_fragment() {}

    public static region_5_fragment newInstance(int pl) {
        region_5_fragment fragment = new region_5_fragment();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_region_5_fragment, container, false);
        setViews(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void cityPtPressed(String city);
        void goBtnPressed();
    }

    public void showgo(boolean okay_go) {
        gobtn.setAlpha(1);
    }

    public void setViews(View view) {
        //MaleficereMansionLayout = view.findViewById(R.id.Maleficere_Mansion_layout);
        //MaleficereMansionBtn = view.findViewById(R.id.Maleficere_Mansion_img);
        //MaleficereMansionText = view.findViewById(R.id.Maleficere_Mansion_tag);
        //ChipperTowneLayout = view.findViewById(R.id.Chipper_Towne_layout);
        //ChipperTowneBtn = view.findViewById(R.id.Chipper_Towne_img);
        //ChipperTowneText = view.findViewById(R.id.Chipper_Towne_tag);
        gobtn = view.findViewById(R.id.region_5_gobtn);
    }
}
