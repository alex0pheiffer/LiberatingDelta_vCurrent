package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.regions;

import java.util.List;

public class main_menyu_regionChapters_fragment extends Fragment implements MenyuRegionChaptersFragmentInterface {

    private static final String REGION = "this_region";
    private static final String CITYPT = "this_cityPt";
    private static final String PlayerLevel = "pl";

    private regions this_region;
    private cityPt this_cityPt;
    private int pl;
    private PL this_pl;
    private List<Chapter> chapters;
    private boolean[] chapters_open;
    private onRegionChaptersSelectedListener mListener;

    public main_menyu_regionChapters_fragment() {}

    public static main_menyu_regionChapters_fragment newInstance(String region, String cityPt, int pl) {
        main_menyu_regionChapters_fragment fragment = new main_menyu_regionChapters_fragment();
        Bundle args = new Bundle();
        args.putString(REGION, region);
        args.putString(CITYPT, cityPt);
        args.putInt(PlayerLevel, pl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(pl);
            this_region = this_pl.getRegion(getArguments().getString(REGION));
            this_cityPt = this_pl.getCityPt(getArguments().getString(CITYPT),this_region);
            System.out.println("region: "+this_region+", city: "+this_cityPt);
            System.out.println("setChapters+ "+this_cityPt.getChapterAmt());
            for (int i=0; i<this_cityPt.getChapterAmt(); i++) {
                chapters.add(i,this_cityPt.getChapter(i));
            }
            System.out.println(chapters.get(0).getNom()+" "+chapters.get(1).getNom());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regionchapters_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new regionChaptersRecyclerViewAdapter(chapters, mListener, this_pl));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onRegionChaptersSelectedListener) {
            mListener = (onRegionChaptersSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onRegionChaptersSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setChapterFragmentCityPt(cityPt city) {
        this_cityPt = city;
        chapters.clear();
        for(int i=0; i<city.getChapterAmt(); i++) {
            chapters.add(i,this_cityPt.getChapter(i));
        }
    }

    public interface onRegionChaptersSelectedListener {
        public void chapterSelected(regions region, Chapter chapter);
    }
}
