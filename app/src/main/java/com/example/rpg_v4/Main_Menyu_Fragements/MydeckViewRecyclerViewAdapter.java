package com.example.rpg_v4.Main_Menyu_Fragements;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public MydeckViewRecyclerViewAdapter(deckRecyclerListener listener) {
        mListener = listener;
        allDecks = mListener.getAllDecks();
        allDecks.add(new Deck(ADD_DECK,null));
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
        holder.mItem = allDecks.get(position);
        if (allDecks.get(position).getNom().compareTo(ADD_DECK) == 0) {
            //this deck is the add deck
            holder.mIsValid.setText("Add New Deck");
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DECKPUSH","view: "+(int) (holder.mView.getLeft() / density)+", char: "+(int) (mmc_right_dist / density));
                    boolean isEmptyCharacter = mListener.getEmptyCharacter();
                    if (isEmptyCharacter || (int)(holder.mView.getLeft()/density)+55>(int)(mmc_right_dist/ density)) {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.deckRecyclerAddDeckPressed();
                        }
                    }
                }
            });
        }
        else {
            holder.mName.setText(allDecks.get(position).getNom());
            if (allDecks.get(position).getIsValid())
                holder.mIsValid.setText("Valid");
            else
                holder.mIsValid.setText("Invalid");
            holder.mCharacter.setText(allDecks.get(position).getCharequip());
            holder.mSize.setText("" + allDecks.get(position).getCardAmt());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DECKPUSH","view: "+(int) (holder.mView.getLeft() / density)+", char: "+(int) (mmc_right_dist / density));
                    boolean isEmptyCharacter = mListener.getEmptyCharacter();
                    if (isEmptyCharacter || (int) (holder.mView.getLeft() / density) + 55 > (int) (mmc_right_dist / density)) {
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
        return allDecks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mIsValid;
        public final TextView mCharacter;
        public final TextView mSize;
        public Deck mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.deckView_name);
            mIsValid = (TextView) view.findViewById(R.id.deckView_valid);
            mCharacter = (TextView) view.findViewById(R.id.deckView_character);
            mSize = (TextView) view.findViewById(R.id.deckView_size);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}

