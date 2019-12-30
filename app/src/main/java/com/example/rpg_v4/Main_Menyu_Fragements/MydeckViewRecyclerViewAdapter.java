package com.example.rpg_v4.Main_Menyu_Fragements;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rpg_v4.Main_Menyu_Fragements.deckViewFragment.deckRecyclerListener;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Deck;

import java.util.List;

public class MydeckViewRecyclerViewAdapter extends RecyclerView.Adapter<MydeckViewRecyclerViewAdapter.ViewHolder> {

    private float density;
    private float mmc_right_dist;

    private final String ADD_DECK = "__adding";
    private final String BLANK_ITEM = "__blank";
    private final List<Deck> allDecks;
    private final deckRecyclerListener mListener;

    public MydeckViewRecyclerViewAdapter(deckRecyclerListener listener, List<Deck> allDecks) {
        mListener = listener;
        this.allDecks = allDecks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_deckview, parent, false);

        density = view.getResources().getDisplayMetrics().density;
        mmc_right_dist = mListener.getMMC_rightDist();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = allDecks.get((int)(position % allDecks.size()));
        if (allDecks.get((int)(position % allDecks.size())).getNom().compareTo(ADD_DECK) == 0) {
            //this deck is the add deck
            holder.mName.setText("");
            holder.mCharacter.setText("");
            holder.mSize.setText("");
            holder.mIsValid.setText("Add New Deck");
            holder.recyclerDeckViewerLinearLayer.setBackgroundColor(holder.mView.getResources().getColor(R.color.genericBlue));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DECKPUSH","view: "+(int) (holder.mView.getLeft() / density)+", char: "+(int) (mmc_right_dist / density));
                    boolean isEmptyCharacter = mListener.getEmptyCharacter();
                    if (isEmptyCharacter || (int)(holder.mView.getLeft()/density)+60 >(int)(mmc_right_dist/ density)) {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.deckRecyclerAddDeckPressed();
                        }
                    }
                }
            });
        }
        else if (allDecks.get((int)(position % allDecks.size())).getNom().compareTo(BLANK_ITEM) == 0) {
            //this deck is a filler item
            holder.recyclerDeckViewerLinearLayer.setBackground(null);
            holder.mName.setText("");
            holder.mCharacter.setText("");
            holder.mSize.setText("");
            holder.mIsValid.setText("");
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DECKPUSH","nothing");
                }
            });
        }
        else {
            holder.mName.setText(allDecks.get((int)(position % allDecks.size())).getNom());
            holder.recyclerDeckViewerLinearLayer.setBackgroundColor(holder.mView.getResources().getColor(R.color.genericBlue));
            if (allDecks.get((int)(position % allDecks.size())).getIsValid())
                holder.mIsValid.setText("Valid");
            else
                holder.mIsValid.setText("Invalid");
            holder.mCharacter.setText(allDecks.get((int)(position % allDecks.size())).getCharequip());
            holder.mSize.setText("" + allDecks.get((int)(position % allDecks.size())).getCardAmt());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DECKPUSH","view: "+(int) (holder.mView.getLeft() / density)+", char: "+(int) (mmc_right_dist / density));
                    boolean isEmptyCharacter = mListener.getEmptyCharacter();
                    if (isEmptyCharacter || (int) (holder.mView.getLeft() / density) + 60 > (int) (mmc_right_dist / density)) {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.deckRecyclerDeckPressed(holder.mItem);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mIsValid;
        public final TextView mCharacter;
        public final TextView mSize;
        public final LinearLayout recyclerDeckViewerLinearLayer;
        public Deck mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.deckView_name);
            mIsValid = (TextView) view.findViewById(R.id.deckView_valid);
            mCharacter = (TextView) view.findViewById(R.id.deckView_character);
            mSize = (TextView) view.findViewById(R.id.deckView_size);
            recyclerDeckViewerLinearLayer = (LinearLayout) view.findViewById(R.id.recyclerDeckViewerLinearLayer);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}

