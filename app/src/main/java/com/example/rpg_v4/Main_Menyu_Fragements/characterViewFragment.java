package com.example.rpg_v4.Main_Menyu_Fragements;

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
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.custom_drawables.defDrawable;
import com.example.rpg_v4.custom_drawables.evaDrawable;

public class characterViewFragment extends Fragment {

    private final String CURRENT_LAYOUT = "CHARACTER_VIEW_LAYOUT";

    private static final String PlayerLevel = "PlayerLevel";
    private static final String CHARACTER = "CHAR";
    private static final String CHARACTERLVL = "LVL";
    private static final String CHARACTEREXP = "EXP";

    private TextView characterName, characterLvl, characterHP, characterAtk, characterPEva, characterMEva, characterType, characterEXP;
    private View expCanvas, atkCanvas, pEvaCanvas, mEvaCanvas, defCanvas, characterViewBar;

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

        //store Views
        characterName = view.findViewById(R.id.characterView_characterName);
        characterLvl = view.findViewById(R.id.characterView_characterLevel);
        characterHP = view.findViewById(R.id.characterView_hpStat);
        characterAtk = view.findViewById(R.id.characterView_atkStat);
        characterPEva = view.findViewById(R.id.characterView_pEvaPercent);
        characterMEva = view.findViewById(R.id.characterView_mEvaPercent);
        characterType = view.findViewById(R.id.characterView_typeStat);
        characterEXP = view.findViewById(R.id.characterView_expStat);
        expCanvas = view.findViewById(R.id.characterView_expCanvas);
        pEvaCanvas = view.findViewById(R.id.characterView_pEvaCanvas);
        mEvaCanvas = view.findViewById(R.id.characterView_mEvaCanvas);
        defCanvas = view.findViewById(R.id.characterView_defenseCanvas);
        characterViewBar = view.findViewById(R.id.characterView_barFragment);

        //update Views
        characterName.setText(this_character.getName());
        characterLvl.setText(""+this_character_lvl);
        characterHP.setText(""+this_character.getStats().getHealth());
        characterAtk.setText("Atk : "+this_character.getStats().getAttackA());
        characterPEva.setText(""+this_character.getStats().getEvasiveA());
        characterMEva.setText(""+this_character.getStats().getEvasiveM());
        characterType.setText(""+this_character.getMagicType());
        characterEXP.setText(this_character_exp+" / "+PL_VendingMachine.exp2next(this_character_lvl));
        pEvaCanvas.setBackground(new evaDrawable(this_character.getStats().getEvasiveA()));
        mEvaCanvas.setBackground(new evaDrawable(this_character.getStats().getEvasiveM()));
        defCanvas.setBackground(new defDrawable(this_character.getStats().getADefense(),this_character.getStats().getFireDefense(),this_character.getStats().getWaterDefense(),this_character.getStats().getLandDefense(),this_character.getStats().getAirDefense()));

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

    public String getCURRENT_LAYOUT() {
        return CURRENT_LAYOUT;
    }

    public interface characterViewFragmentListener {
        //void onFragmentInteraction(Uri uri);
    }
}
