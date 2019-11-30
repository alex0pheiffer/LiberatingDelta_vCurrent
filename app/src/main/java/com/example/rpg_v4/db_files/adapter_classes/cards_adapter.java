package com.example.rpg_v4.db_files.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.User_Cards;

import java.util.List;

public class cards_adapter extends RecyclerView.Adapter<cards_adapter.dataViewHolder> {

    class dataViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataItemView;

        private dataViewHolder(View itemView) {
            super(itemView);
            dataItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<User_Cards> lData; //Cached copy of words

    public cards_adapter(Context context) {
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
            User_Cards current = (User_Cards)(lData.get(position));
            holder.dataItemView.setText(current.getName() + " [" + current.getPosition()+"]");
        }
        else {
            // covers the case of data not being ready yet
            holder.dataItemView.setText("No Data");
        }
    }

    public void setlData(List<User_Cards> vals) {
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