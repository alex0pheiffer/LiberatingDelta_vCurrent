package com.example.rpg_v4.db_files.adapter_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.User_Characters;

import java.util.List;

public class characters_adapter extends RecyclerView.Adapter<characters_adapter.dataViewHolder> {

    class dataViewHolder extends RecyclerView.ViewHolder {
        private final TextView dataItemView;

        private dataViewHolder(View itemView) {
            super(itemView);
            dataItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<User_Characters> lData; //Cached copy of words

    public characters_adapter(Context context) {
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
            User_Characters current = (User_Characters)(lData.get(position));
            holder.dataItemView.setText(current.getName()+" lvl:"+current.getLevel());
        }
        else {
            // covers the case of data not being ready yet
            holder.dataItemView.setText("No Data");
        }
    }

    public void setlData(List<User_Characters> vals) {
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