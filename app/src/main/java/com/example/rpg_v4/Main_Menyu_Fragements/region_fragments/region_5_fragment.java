package com.example.rpg_v4.Main_Menyu_Fragements.region_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpg_v4.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link region_5_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link region_5_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class region_5_fragment extends Fragment {
    private static final String PlayerLevel= "pl";

    final String THIS_LAYOUT="REGION_MAP";

    private int pl;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_region_5_fragment, container, false);
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

    }
}
