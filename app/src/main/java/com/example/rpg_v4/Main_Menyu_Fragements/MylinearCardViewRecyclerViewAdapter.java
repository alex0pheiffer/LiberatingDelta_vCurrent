package com.example.rpg_v4.Main_Menyu_Fragements;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Cards.sudoCard;
import com.example.rpg_v4.basic_classes.abstractDeck;

import java.util.List;

public class MylinearCardViewRecyclerViewAdapter extends RecyclerView.Adapter<MylinearCardViewRecyclerViewAdapter.ViewHolder> {

    private final abstractDeck deck;
    private boolean isHalf;
    private Activity activityy;
    private final linearCardViewFragment.linearCardViewListener mListener;

    public MylinearCardViewRecyclerViewAdapter(abstractDeck deck, linearCardViewFragment.linearCardViewListener listener, boolean isHalf, Activity activity) {
        this.deck = deck;
        this.isHalf = isHalf;
        this.activityy = activity;
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_linearcardview_large, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = deck.getSudoCard(position);
        holder.mIdView.setText(deck.getSudoCard(position).getCard(0).getNom());
        holder.mContentView.setText(deck.getSudoCard(position).getCard(0).getInfo());
        holder.cardView.setImageDrawable(activityy.getDrawable(deck.getSudoCard(position).getCard(0).getCardImg()));
        holder.cardAmtRing.setText(""+deck.getSudoCard(0).getAmount());

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
        return deck.getSudoCardAmt();
    }

    /*
    public void updateDeck(abstractDeck deck) {
        //possibly add a few lines to ensure this IS NOT MODIFIED AFTER THE RECYCLER IS VISIBLE.
        this.deck = deck;
    }
    */

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public TextView cardAmtRing;
        public ImageView cardView;
        public sudoCard mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            if (isHalf) {
                cardView = (ImageView) view.findViewById(R.id.SmallCardViewCard);
                cardAmtRing = (TextView) view.findViewById(R.id.SmallCardViewNumber);
                mIdView = (TextView) view.findViewById(R.id.SmallCardViewTitle);
                mContentView = (TextView) view.findViewById(R.id.SmallCardViewInfo);
            }
            else {
                cardView = (ImageView) view.findViewById(R.id.LargeCardViewCard);
                cardAmtRing = (TextView) view.findViewById(R.id.LargeCardViewNumber);
                mIdView = (TextView) view.findViewById(R.id.LargeCardViewTitle);
                mContentView = (TextView) view.findViewById(R.id.LargeCardViewInfo);
            }

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
