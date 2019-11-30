package com.example.rpg_v4.db_files.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.User_Decks;

import java.util.List;

public class decks_adapter extends RecyclerView.Adapter<decks_adapter.dataViewHolder> {

    class dataViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataItemView;

        private dataViewHolder(View itemView) {
            super(itemView);
            dataItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<User_Decks> lData; //Cached copy of words

    public decks_adapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new dataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(dataViewHolder holder, int position) {
        if (lData != null) {
            User_Decks current = (User_Decks)(lData.get(position));
            holder.dataItemView.setText(current.getName()+" crds:"+current.getLength());
        }
        else {
            // covers the case of data not being ready yet
            holder.dataItemView.setText("No Data");
        }
    }

    public void setlData(List<User_Decks> vals) {
        lData = vals;
        notifyDataSetChanged();
    }
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means intially, it's null, and we can't return null
    @Override
    public int getItemCount() {
        if (lData != null) {
            return lData.size();
        }
        else {
            return 0;
        }
    }
}