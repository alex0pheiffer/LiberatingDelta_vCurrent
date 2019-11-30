package com.example.rpg_v4.db_files.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.User_EQPlayed;

import java.util.List;

public class eqplayed_adapter extends RecyclerView.Adapter<eqplayed_adapter.dataViewHolder> {

    class dataViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataItemView;

        private dataViewHolder(View itemView) {
            super(itemView);
            dataItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<User_EQPlayed> lData; //Cached copy of words

    public eqplayed_adapter(Context context) {
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
            User_EQPlayed current = (User_EQPlayed)(lData.get(position));
            holder.dataItemView.setText(current.getName()+" comp:"+current.getCompleted());
        }
        else {
            // covers the case of data not being ready yet
            holder.dataItemView.setText("No Data");
        }
    }

    public void setlData(List<User_EQPlayed> vals) {
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