package com.example.rpg_v4.Main_Menyu_Fragements.characterViewSubFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;

public class characterViewStatsSubFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView characterHP, characterAtk, characterPEva, characterMEva, characterType, characterEXP;
    private View expCanvas, atkCanvas, pEvaCanvas, mEvaCanvas, defCanvas;

    private OnFragmentInteractionListener mListener;

    public characterViewStatsSubFragment() {
        // Required empty public constructor
    }

    public static characterViewStatsSubFragment newInstance(String param1, String param2) {
        characterViewStatsSubFragment fragment = new characterViewStatsSubFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_view_stats_sub, container, false);

        System.out.println("created view: ");

        characterHP = view.findViewById(R.id.characterView_hpStat);
        characterAtk = view.findViewById(R.id.characterView_atkStat);
        characterPEva = view.findViewById(R.id.characterView_pEvaPercent);
        characterMEva = view.findViewById(R.id.characterView_mEvaPercent);
        characterType = view.findViewById(R.id.characterView_typeStat);
        characterEXP = view.findViewById(R.id.characterView_expStat);

        System.out.println("\t"+characterHP+"\t"+characterAtk);

        //expCanvas = view.findViewById(R.id.characterView_expCanvas);
        //pEvaCanvas = view.findViewById(R.id.characterView_pEvaCanvas);
        //mEvaCanvas = view.findViewById(R.id.characterView_mEvaCanvas);
        //defCanvas = view.findViewById(R.id.characterView_defenseCanvas);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }

    public void setHP(int health) {
        characterHP.setText(""+health);
    }
    public void setAtk(int atk) {
        characterAtk.setText(""+atk);
    }
    public void setPEva(int eva) {
        characterPEva.setText(""+eva);
    }
    public void setMEva(int eva) {
        characterMEva.setText(""+eva);
    }
    public void setMagicType(String type) {
        characterType.setText(type);
    }
    public void setEXP(String expStr) {
        characterEXP.setText(expStr);
    }
    //pEvaCanvas.setBackground(new evaDrawable(this_character.getStats().getEvasiveA()));
    //mEvaCanvas.setBackground(new evaDrawable(this_character.getStats().getEvasiveM()));
    //defCanvas.setBackground(new defDrawable(this_character.getStats().getADefense(),this_character.getStats().getFireDefense(),this_character.getStats().getWaterDefense(),this_character.getStats().getLandDefense(),this_character.getStats().getAirDefense()));

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
