package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.PL;

public class dedicatedBackBtn extends Fragment {
    private static final String PlayerLevel = "PL";
    private static final String Layout = "layout";

    //private final String view_layout = "DECK_VIEW";
    //private final String edit_layout = "DECK_EDIT";

    private int pl;
    private PL this_pl;
    private String current_layout;

    private View backbtn;

    private nonRegionBackButtonListener mListener;

    public dedicatedBackBtn() {
        // Required empty public constructor
    }

    public static dedicatedBackBtn newInstance(int pl, String layout) {
        dedicatedBackBtn fragment = new dedicatedBackBtn();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putString(Layout, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(pl);
            current_layout = getArguments().getString(Layout);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dedicated_back_btn_fragment, container, false);
        backbtn = view.findViewById(R.id.nonregion_backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.backBtnPressed(current_layout);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof nonRegionBackButtonListener) {
            mListener = (nonRegionBackButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement nonRegionBackButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void modifyLayout(String layout) {
        current_layout = layout;
    }

    public interface nonRegionBackButtonListener {
        void backBtnPressed(String layout);
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
