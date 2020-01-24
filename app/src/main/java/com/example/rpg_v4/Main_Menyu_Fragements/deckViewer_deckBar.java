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
import android.widget.TextView;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.PL;

public class deckViewer_deckBar extends Fragment {
    private static final String PlayerLevel = "pl";
    private static final String DeckName = "deck";

    private static final String CURRENT_LAYOUT = "DECK_VIEW_SELECT";

    private int pl;
    private PL this_pl;
    private String deckName;
    private Deck this_deck;
    private boolean deckCheck;

    private Button viewDeckBtn;
    private Button showValidDeckBtn;
    private Button deleteDeckBtn;
    private TextView deckNameTextView;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deck_viewer_deck_bar, container, false);
        viewDeckBtn = view.findViewById(R.id.deckViewBar_view_btn);
        showValidDeckBtn = view.findViewById(R.id.deckViewBar_valid_btn);
        deleteDeckBtn = view.findViewById(R.id.deckViewBar_delete_btn);
        deckNameTextView = view.findViewById(R.id.deckViewBar_name);

        viewDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck && this_deck != null) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                if (this_deck != null) mListener.deckViewerBar_ViewDeckPressed(this_deck);
            }
        });
        showValidDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck && this_deck != null) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                if (this_deck != null) mListener.deckViewerBar_showValidDeckPressed(this_deck);
            }
        });
        deleteDeckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!deckCheck && this_deck != null) {
                    if (deckName.compareTo(this_deck.getNom()) ==  0) deckCheck = true;
                    else throw new RuntimeException("deck and imported deck do not match");
                }
                if (this_deck != null) mListener.deckViewerBar_DeleteDeckPressed(this_deck);
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
        Log.d("DEBUG","Deck Imported to deckViewer: "+deck.getNom());
        if (deckName == null) {
            this_deck = deck;
            deckCheck = false;
        }
        else if (deckName.compareTo(deck.getNom()) ==  0) {
            this_deck = deck;
            deckCheck = true;
        }
        else {
            throw new RuntimeException("deck: "+deckName+" and imported deck: "+deck.getNom()+" do not match");
        }
        if (deckNameTextView != null) {
            deckNameTextView.setText(this_deck.getNom());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface deckViewerDeckBarListener {
        void deckViewerBar_ViewDeckPressed(Deck deck);
        void deckViewerBar_showValidDeckPressed(Deck deck);
        void deckViewerBar_DeleteDeckPressed(Deck deck);
        void deckRecyclerDeckPressed(Deck deck);
    }

    public static String getCURRENT_LAYOUT() {
        return CURRENT_LAYOUT;
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
