package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rpg_v4.PL;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Characters;

public class main_menyu_frontcharacter extends Fragment {
    private static final String CHARACTER = "character";
    private static final String WEAPON = "weapon";
    private static final String PlayerLevel = "pl";

    private Characters character;
    private String weapon;
    private int pl;
    private ImageView frontcharacter;

    private onMenyuFrontcharacterSelectedListener mListener;

    public main_menyu_frontcharacter() {}

    public static main_menyu_frontcharacter newInstance(String character, String weapon, int pl) {
        main_menyu_frontcharacter fragment = new main_menyu_frontcharacter();
        Bundle args = new Bundle();
        args.putString(CHARACTER, character);
        args.putString(WEAPON, weapon);
        args.putInt(PlayerLevel, pl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            character = PL.getCharacter(getArguments().getString(CHARACTER));
            weapon = getArguments().getString(WEAPON);
            pl = getArguments().getInt(PlayerLevel);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menyu_frontcharacter, container, false);
        frontcharacter = view.findViewById(R.id.menyu_mmc_img);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onMenyuFrontcharacterSelectedListener) {
            mListener = (onMenyuFrontcharacterSelectedListener) context;
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

   public interface onMenyuFrontcharacterSelectedListener {

    }

    public String toString() {
        return "main_menyu_frontcharacter";
    }
}
