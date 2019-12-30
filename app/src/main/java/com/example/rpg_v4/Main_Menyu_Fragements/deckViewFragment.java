package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.PL;

import java.util.ArrayList;
import java.util.List;


public class deckViewFragment extends Fragment {

    private static final String PlayerLevel = "PL";
    private static final String CURRENT_DECK = "CURRENT_DECK";

    private final String ADD_DECK = "__adding";
    private final String BLANK_ITEM = "__blank";
    private List<Deck> allDecks;

    private int mColumnCount = 1;
    private int pl;
    private PL this_pl;
    private String current;
    private deckRecyclerListener mListener;

    public deckViewFragment() {
    }

    public static deckViewFragment newInstance(int pl, String currentDeck) {
        deckViewFragment fragment = new deckViewFragment();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        args.putString(CURRENT_DECK,currentDeck);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(this.pl);
            current = getArguments().getString(CURRENT_DECK);
        }
        allDecks = mListener.getAllDecks();
        allDecks.add(new Deck(ADD_DECK,null));
        allDecks.add(0,new Deck(BLANK_ITEM, null));

        while (allDecks.size() < 6) {
            //make the deck size 6 (5 decks)
            allDecks.add(new Deck(BLANK_ITEM,null));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deckview_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MydeckViewRecyclerViewAdapter(mListener, allDecks));
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    //the following is not really used rn,... but itll be needed for inf scroll?
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    super.onScrolled(recyclerView, dx, dy);
                    int firstItemVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if (firstItemVisible != 0 && firstItemVisible % allDecks.size() == 0) {
                        recyclerView.getLayoutManager().scrollToPosition(0);
                    }
                    //this is needed...
                    mListener.decksScrolled();
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof deckRecyclerListener) {
            mListener = (deckRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement deckRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface deckRecyclerListener {
        ArrayList<Deck> getAllDecks();
        void deckRecyclerDeckPressed(Deck deck);
        void deckRecyclerAddDeckPressed();
        int getMMC_rightDist();
        boolean getEmptyCharacter();
        void decksScrolled();
    }
}
