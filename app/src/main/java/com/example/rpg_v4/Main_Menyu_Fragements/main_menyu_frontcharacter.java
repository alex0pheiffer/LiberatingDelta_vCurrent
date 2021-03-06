package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.main_character;

public class main_menyu_frontcharacter extends Fragment {
    private static final String CHARACTER = "character";
    private static final String WEAPON = "weapon";
    private static final String PlayerLevel = "pl";

    private main_character character;
    private main_character previous_character;
    private String weapon;
    private int pl;
    private PL this_pl;
    private ImageView frontcharacter;

    private onMenyuFrontcharacterSelectedListener mListener;

    public main_menyu_frontcharacter() {
    }

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
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(pl);
            character = this_pl.getCharacter(getArguments().getString(CHARACTER));
            weapon = getArguments().getString(WEAPON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menyu_frontcharacter, container, false);
        Log.d("DEBUG",""+character.getCharacterImgDrawable());
        frontcharacter = view.findViewById(R.id.menyu_mmc_img);
        frontcharacter.setImageDrawable(getActivity().getDrawable(character.getCharacterImgDrawable()));

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

    //changes the char showing
    //isUp --> do we shift the characters up or down
    //hasEmpty --> can it return an "empty" slot character
    public void characterArrowPressed(boolean isUp, boolean hasEmpty) {
        previous_character = character;
        if (character == null) {
            character = this_pl.rotateCharacter("empty", isUp, hasEmpty);
        }
        else {
            character = this_pl.rotateCharacter(character.getName(), isUp, hasEmpty);
        }
        if (character != null) {
            Log.d("CHARARROW", "charArrowPressed; " + character.getName() + " showing");
            //todo change to character's image
            //frontcharacter.setImageDrawable(getActivity().getDrawable(character.getCharacterImgDrawable()));
            frontcharacter.setImageDrawable(getActivity().getDrawable(character.getCharacterImgDrawable()));
        }
        else {
            Log.d("CHARARROW", "charArrowPressed; " + "null" + " showing");
            frontcharacter.setImageDrawable(null);
        }
    }

    public void setPreviousCharacter() {
        //todo change to character's image
        character = previous_character;
        frontcharacter.setImageDrawable(getActivity().getDrawable(character.getCharacterImgDrawable()));
    }

    public boolean getEmptyCharacter() {
        if (character == null) return true;
        else return false;
    }

    public void deployArrowsMMC(Fragment arrowUp, Fragment arrowDown) {
    FragmentManager fm = getChildFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.mmc_upArrow,arrowUp);
    ft.add(R.id.mmc_downArrow,arrowDown);
    ft.addToBackStack(null);
    ft.commit();
    }

    public main_character reignArrowsMMC(Fragment arrowUp, Fragment arrowDown) {
        if (character == null) {
            throw new RuntimeException("Cannot reign when character DNE");
        }
        else {
            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(arrowUp);
            ft.remove(arrowDown);
            ft.addToBackStack(null);
            ft.commit();
        }
        return character;
    }

    public interface onMenyuFrontcharacterSelectedListener {

    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
