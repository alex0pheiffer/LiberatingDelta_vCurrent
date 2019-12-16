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

import java.util.ArrayList;
import java.util.List;



public class regionChaptersRecyclerViewAdapter extends RecyclerView.Adapter<regionChaptersRecyclerViewAdapter.ViewHolder> {

    private final List<Chapter> mChapters;
    private final onRegionChaptersSelectedListener mListener;
    private PL this_pl;

    public regionChaptersRecyclerViewAdapter(List<Chapter> items, onRegionChaptersSelectedListener listener, PL thisPL) {
        System.out.println("set mChapters: "+items);
        //Thread.dumpStack();
        mChapters = items;
        mListener = listener;
        this_pl = thisPL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_regionchapters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        System.out.println("onBindViewHolder");
        if (mChapters != null) {
            System.out.println("setting up holder: "+position);
            holder.mItem = mChapters.get(position);
            holder.plView.setText("PL: " + mChapters.get(position).getCoor_PL());
            holder.mContentView.setText(mChapters.get(position).getNom());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.chapterSelected(this_pl.getRegion(holder.mItem.getRegion()), holder.mItem);
                    }
                }
            });
        }
        else {
            holder.plView.setText("No Chapters");
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("getting size: "+mChapters);
        return mChapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView plView;
        public final TextView mContentView;
        public Chapter mItem;
        public final String CURRENT_LAYOUT = "REGION_MAP_CHAPTER";

        public ViewHolder(View view) {
            super(view);
            System.out.println("new ViewHolder");
            mView = view;
            plView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        public String getCURRENT_LAYOUT() {
            return this.CURRENT_LAYOUT;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
