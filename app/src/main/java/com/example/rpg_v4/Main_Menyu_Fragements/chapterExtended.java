package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpg_v4.PL_VendingMachine;
import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.PL;

public class chapterExtended extends Fragment {
    private static final String PlayerLevel = "PL";

    private int pl;
    private PL this_pl;
    private Chapter this_chapter;

    private TextView chapterExtendedDescription;

    private onChapterExtendedSelectedListener mListener;

    public chapterExtended() {
        // Required empty public constructor
    }

    public static chapterExtended newInstance(int pl) {
        chapterExtended fragment = new chapterExtended();
        Bundle args = new Bundle();
        args.putInt(PlayerLevel, pl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pl = getArguments().getInt(PlayerLevel);
            this_pl = PL_VendingMachine.getPL(this.pl);
            this_chapter = this_pl.getChapter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapter_extended, container, false);
        chapterExtendedDescription = view.findViewById(R.id.chapterExtendedDescription);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onChapterExtendedSelectedListener) {
            mListener = (onChapterExtendedSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onChapterExtendedSelectedListener {
        void onChapterExtendedPressed(Chapter chapter);
    }

    String mTag = this.toString();
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
