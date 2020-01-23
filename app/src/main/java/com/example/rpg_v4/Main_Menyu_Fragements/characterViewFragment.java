package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.Main_Menyu_Fragements.characterViewSubFragments.characterViewBar;
import com.example.rpg_v4.Main_Menyu_Fragements.characterViewSubFragments.characterViewStatsSubFragment;
import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.custom_drawables.defDrawable;
import com.example.rpg_v4.custom_drawables.evaDrawable;

public class characterViewFragment extends Fragment {

    private static final String CURRENT_LAYOUT = "CHARACTER_VIEW_LAYOUT";

    private static final String PlayerLevel = "PlayerLevel";
    private static final String CHARACTER = "CHAR";
    private static final String CHARACTERLVL = "LVL";
    private static final String CHARACTEREXP = "EXP";


    private characterViewStatsSubFragment statsFrag;
    /*
    private characterViewEquipSubFragment equipFrag;
    private characterViewRegionSubFragment regionFrag;
    private characterViewRankSubFragment rankFrag;
    private characterViewInfoSubFragment infoFrag;
    */
    private characterViewBar bar;


    private TextView characterName, characterLvl;

    private int pl;
    private PL this_pl;
    private String frontCharacter;
    private int this_character_lvl;
    private int this_character_exp;
    private main_character this_character;

    private characterViewFragmentListener mListener;

    public characterViewFragment() {
        // Required empty public constructor
    }

    public static characterViewFragment newInstance(int pl, String character, int characterLvl, int characterExp) {
        characterViewFragment fragment = new characterViewFragment();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putString(CHARACTER, character);
        args.putInt(CHARACTERLVL,characterLvl);
        args.putInt(CHARACTEREXP, characterExp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(pl);
            frontCharacter = getArguments().getString(CHARACTER);
            this_character_lvl = getArguments().getInt(CHARACTERLVL);
            this_character_exp = getArguments().getInt(CHARACTEREXP);
            this_character = this_pl.getCharacter(frontCharacter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_view, container, false);

        //Create subFragments
        statsFrag = characterViewStatsSubFragment.newInstance("str1","str2");
        bar = characterViewBar.newInstance(pl);

        //add subfragments
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.characterViewSubFragment,statsFrag);
        ft.add(R.id.characterView_barFragment,bar);
        ft.addToBackStack(null);
        ft.commit();

        //store Views
        characterName = view.findViewById(R.id.characterView_characterName);
        characterLvl = view.findViewById(R.id.characterView_characterLevel);

        //update Views
        characterName.setText(this_character.getName());
        characterLvl.setText(""+this_character_lvl);
        statsFrag.setHP(this_character.getStats().getHealth());
        statsFrag.setAtk(this_character.getStats().getAttackA());
        statsFrag.setPEva(this_character.getStats().getEvasiveA());
        statsFrag.setMEva(this_character.getStats().getEvasiveM());
        statsFrag.setMagicType(this_character.getMagicType());
        statsFrag.setEXP(this_character_exp+" / "+PL_VendingMachine.exp2next(this_character_lvl));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof characterViewFragmentListener) {
            mListener = (characterViewFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement characterViewFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static String getCURRENT_LAYOUT() {
        return CURRENT_LAYOUT;
    }

    /*
    public void setCURRENT_LAYOUT(String layout) {
        CURRENT_LAYOUT = layout;
    }
    */

    public interface characterViewFragmentListener {
        //void onFragmentInteraction(Uri uri);
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
