package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpg_v4.R;

public class CharacterArrowFragment extends Fragment {
    private static final String ORIENTATION = "orientation";
    private static final String HASEMPTY = "hasempty";

    private boolean isUp;
    private boolean hasEmpty;

    private onCharacterArrowFragmentInteraction mListener;

    public CharacterArrowFragment() {
        // Required empty public constructor
    }

    public static CharacterArrowFragment newInstance(boolean isUp, boolean hasEmpty) {
        CharacterArrowFragment fragment = new CharacterArrowFragment();
        Bundle args = new Bundle();
        args.putBoolean(ORIENTATION, isUp);
        args.putBoolean(HASEMPTY,hasEmpty);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isUp = getArguments().getBoolean(ORIENTATION);
            hasEmpty = getArguments().getBoolean(HASEMPTY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_arrow, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //outro mainmenyu2characters (move mainmenyu icons off)
                //intro mainmenyu2characters (move characters icons on)
                //AGAIN, NOT A NEW ACTIVITY! YOU ARE ADDING FRAGEMENTS
                //((FragmentActivity)getActivity()).setCURRENT_LAYOUT("CHARACTERS_MENYU_LAYOUT");
                mListener.characterArrowPressed(isUp,hasEmpty);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onCharacterArrowFragmentInteraction) {
            mListener = (onCharacterArrowFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onCharacterArrowFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onCharacterArrowFragmentInteraction {
        void characterArrowPressed(boolean isUp, boolean hasEmpty);
    }
}
