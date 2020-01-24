package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.abstractDeck;


public class cardViewLargeButtons extends Fragment {
    private static final String PlayerLevel = "pl";
    private static final String DeckNamey = "deckname";
    private static final String CURRENT_LAYOUT1 = "DECK_VIEW_VIEW_CARD";

    private int pl;
    private PL this_pl;
    private String deckName;

    private Button sortByCharBtn, editBtn;
    private TextView cardAmt;

    private cardViewLargeButtonListener mListener;

    public cardViewLargeButtons() {
        // Required empty public constructor
    }

    public static cardViewLargeButtons newInstance(int pl, String deck) {
        cardViewLargeButtons fragment = new cardViewLargeButtons();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putString(DeckNamey, deck);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            deckName = getArguments().getString(DeckNamey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_view_large_buttons, container, false);
        cardAmt = view.findViewById(R.id.cardViewLargeBtnAmt);
        sortByCharBtn = view.findViewById(R.id.cardViewLargeBtnSort);
        editBtn = view.findViewById(R.id.cardViewLargeBtnEdit);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof cardViewLargeButtonListener) {
            mListener = (cardViewLargeButtonListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement cardViewLargeButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface cardViewLargeButtonListener{
        //void cardViewLargeBtnCharSortPressed();
        void cardViewLargeBtnEditPressed(abstractDeck deck);
    }
}
