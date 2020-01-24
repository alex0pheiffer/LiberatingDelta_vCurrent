package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Cards.sudoCard;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.abstractDeck;

import java.util.List;

public class linearCardViewFragment extends Fragment {

    //todo... see how shadowverse and hearthstone do their deck editing... how can you see what the card does? like if i want to view the card's information before adding it to the deck, how would i do that?
      
    private static final String PlayerLevel = "pl";
    private static final String ViewerSize = "viewerSize";
    private static final String DeckNamey = "deckname";
    private static final String CURRENT_LAYOUT1 = "DECK_VIEW_VIEW_CARD";
    private static final String CURRENT_LAYOUT2 = "DECK_VIEW_EDIT_CARD";
    private int mColumnCount = 1;

    private int pl;
    private PL this_pl;
    private boolean isHalf; //is this an editing card viewer or a whole card viewer (are there 1 or 2 on the screen)...wants to know how big it'll be
    private String deckName;
    private abstractDeck deck;

    private linearCardViewListener mListener;

    public linearCardViewFragment() {
    }

    public static linearCardViewFragment newInstance(int pl, boolean isHalf, String deckname) {
        linearCardViewFragment fragment = new linearCardViewFragment();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putBoolean(ViewerSize, isHalf);
        args.putString(DeckNamey, deckname);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.pl = getArguments().getInt(PlayerLevel);
            this.this_pl = PL_VendingMachine.getPL(this.pl);
            this.isHalf = getArguments().getBoolean(ViewerSize);
            this.deckName = getArguments().getString(DeckNamey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_linearcardview_list, container, false);
        if (this.deck == null) {
            abstractDeck temp = mListener.quickGrabDeck();
            if (temp.getNom().compareTo(deckName) == 0) this.deck = temp;
            else {
                throw new RuntimeException("ERROR: given deck and quickgrab deck are not the same");
            }
        }
        Log.d("DEBUG","grabbed "+this.deck.getNom());

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MylinearCardViewRecyclerViewAdapter(deck, mListener,isHalf,getActivity()));
            //((MylinearCardViewRecyclerViewAdapter)recyclerView.getAdapter()).updateDeck(this.deck);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof linearCardViewListener) {
            mListener = (linearCardViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement linearCardViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*
    public void importDeck(abstractDeck deck) {
        this.deck = deck;

    }
    */

    public interface linearCardViewListener {
        //possibly pop up information about a given card
        void onListFragmentInteraction(sudoCard sudo);
        abstractDeck quickGrabDeck();
    }

    public static String getCURRENT_LAYOUT(boolean edit) {
        if (edit) {
            return CURRENT_LAYOUT2;
        }
        else {
            return CURRENT_LAYOUT1;
        }
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
