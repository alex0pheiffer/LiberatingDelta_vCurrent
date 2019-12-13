package com.example.rpg_v4.Main_Menyu_Fragements;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_regionChapters_fragment.onRegionChaptersSelectedListener;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.PL;

import java.util.List;



public class regionChaptersRecyclerViewAdapter extends RecyclerView.Adapter<regionChaptersRecyclerViewAdapter.ViewHolder> {

    private final List<Chapter> mValues;
    private final onRegionChaptersSelectedListener mListener;
    private PL this_pl;

    public regionChaptersRecyclerViewAdapter(List<Chapter> items, onRegionChaptersSelectedListener listener, PL thisPL) {
        System.out.println("set mValues: "+items);
        mValues = items;
        mListener = listener;
        this_pl = thisPL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_regionchapters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.plView.setText("PL: "+mValues.get(position).getCoor_PL());
        holder.mContentView.setText(mValues.get(position).getNom());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.chapterSelected(this_pl.getRegion(holder.mItem.getRegion()),holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("getting size: "+mValues);
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView plView;
        public final TextView mContentView;
        public Chapter mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            plView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}



/*
public class regionChaptersRecyclerViewAdapter extends RecyclerView.Adapter<regionChaptersRecyclerViewAdapter.ViewHolder> {

    private final List<regionChapterItem> mValues;
    private final onRegionChaptersSelectedListener mListener;

    public regionChaptersRecyclerViewAdapter(List<regionChapterItem> items, onRegionChaptersSelectedListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_regionchapters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public regionChapterItem mItem;

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
*/