package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.PL;

public class deckViewer_deckBar extends Fragment {
    private static final String PlayerLevel = "pl";
    private static final String DeckName = "deck";

    private int pl;
    private PL this_pl;
    private String deckName;
    private Deck this_deck;
    private boolean deckCheck;

    private Button viewDeckBtn;
    private Button editDeckBtn;
    private Button deleteDeckBtn;

    private deckViewerDeckBarListener mListener;

    public deckViewer_deckBar() {
        // Required empty public constructor
    }

    public static deckViewer_deckBar newInstance(int pl, String deck) {
        deckViewer_deckBar fragment = new deckViewer_deckBar();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putString(DeckName, deck);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(pl);
            deckName = getArguments().getString(DeckName);
            //temporary until the deck is imported
            this_deck = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deck_viewer_deck_bar, container, false);
        viewDeckBtn = view.findViewById(R.id.deckViewBar_view_btn);
        editDeckBtn = view.findViewById(R.id.deckViewBar_edit_btn);
        deleteDeckBtn = view.findViewById(R.id.deckViewBar_delete_btn);
        viewDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                mListener.deckViewerBar_ViewDeckPressed(this_deck);
            }
        });
        editDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                mListener.deckViewerBar_EditDeckPressed(this_deck);
            }
        });
        deleteDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                mListener.deckViewerBar_DeleteDeckPressed(this_deck);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof deckViewerDeckBarListener ) {
            mListener = (deckViewerDeckBarListener ) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement deckViewerDeckBarListener ");
        }
    }

    public void importDeck(Deck deck) {
        if (deckName == null) {
            this_deck = deck;
            deckCheck = false;
        }
        else if (deckName.compareTo(deck.getNom()) ==  0) {
            this_deck = deck;
            deckCheck = true;
        }
        else {
            throw new RuntimeException("deck and imported deck do not match");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface deckViewerDeckBarListener {
        void deckViewerBar_ViewDeckPressed(Deck deck);
        void deckViewerBar_EditDeckPressed(Deck deck);
        void deckViewerBar_DeleteDeckPressed(Deck deck);
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
