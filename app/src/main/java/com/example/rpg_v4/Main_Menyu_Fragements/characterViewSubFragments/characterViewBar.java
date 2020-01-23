package com.example.rpg_v4.Main_Menyu_Fragements.characterViewSubFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.PL;

public class characterViewBar extends Fragment {
    private static final String PlayerLevel = "PL";
    //private static final String ARG_PARAM2 = "param2";

    private int pl;
    private PL this_pl;
    //private String mParam2;

    private Button stats, equip, region, rank, info;
    private final String statsStr = "CHARACTER_VIEW_STATS";
    private final String equipStr = "CHARACTER_VIEW_EQUIP";
    private final String regionStr = "CHARACTER_VIEW_REGION";
    private final String rankStr = "CHARACTER_VIEW_RANK";
    private final String infoStr = "CHARACTER_VIEW_INFO";

    private characterViewBarListener mListener;

    public characterViewBar() {
        // Required empty public constructor
    }

    public static characterViewBar newInstance(int pl) {
        characterViewBar fragment = new characterViewBar();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            this_pl = PL_VendingMachine.getPL(pl);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_view_bar, container, false);
        stats = view.findViewById(R.id.characterViewBar_stats);
        equip = view.findViewById(R.id.characterViewBar_equip);
        region = view.findViewById(R.id.characterViewBar_region);
        rank = view.findViewById(R.id.characterViewBar_rank);
        info = view.findViewById(R.id.characterViewBar_info);
        stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.characterViewBarStatsPressed(statsStr);
            }
        });
        equip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.characterViewBarEquipPressed(equipStr);
            }
        });
        region.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.characterViewBarRegionPressed(regionStr);
            }
        });
        rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.characterViewBarRankPressed(rankStr);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.characterViewBarInfoPressed(infoStr);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof characterViewBarListener) {
            mListener = (characterViewBarListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement characterViewBarListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public String getStatsStr() {
        return statsStr;
    }
    public String getEquipStr() {
        return equipStr;
    }
    public String getInfoStr() {
        return infoStr;
    }
    public String getRankStr() {
        return rankStr;
    }
    public String getRegionStr() {
        return regionStr;
    }

    public interface characterViewBarListener{
        void characterViewBarStatsPressed(String layout);
        void characterViewBarEquipPressed(String layout);
        void characterViewBarRegionPressed(String layout);
        void characterViewBarRankPressed(String layout);
        void characterViewBarInfoPressed(String layout);
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
