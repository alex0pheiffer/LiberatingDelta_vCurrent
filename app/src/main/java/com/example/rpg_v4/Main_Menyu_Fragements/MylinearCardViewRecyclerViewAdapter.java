package com.example.rpg_v4.Main_Menyu_Fragements;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.abstractDeck;

import java.util.List;

public class MylinearCardViewRecyclerViewAdapter extends RecyclerView.Adapter<MylinearCardViewRecyclerViewAdapter.ViewHolder> {

    private final abstractDeck deck;
    private boolean isHalf;
    private final linearCardViewFragment.linearCardViewListener mListener;

    public MylinearCardViewRecyclerViewAdapter(abstractDeck deck, linearCardViewFragment.linearCardViewListener listener, boolean isHalf) {
        this.deck = deck;
        this.isHalf = isHalf;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (isHalf) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_linearcardview_small, parent, false);
        }
        else {
            //todo uncomment once card layout is completed
            view = null;
            //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_linearcardview_large, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = deck.getCard(position);
        //holder.mIdView.setText(deck.getCard(position));
        holder.mContentView.setText(deck.getCard(position).getNom());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return deck.getCardAmt();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Card mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
