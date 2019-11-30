package com.example.rpg_v4.Main_Menyu_Fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rpg_v4.R;

public class menyu_itemsbar extends Fragment implements ItemsBarFragmentInterface {

    final private String THIS_LAYOUT = "MAIN_MENYU";

    private Button settingsBtn, charactersBtn, plotBtn, decksBtn, inventoryBtn, mapBtn;

    private onMenyuItemsBarSelectedListener mListener;

    public menyu_itemsbar() {
        // Required empty public constructor
    }
    public static menyu_itemsbar newInstance() {
        menyu_itemsbar fragment = new menyu_itemsbar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menyu_itemsbar, container, false);
        settingsBtn = view.findViewById(R.id.menyu_settings_btn);
        charactersBtn = view.findViewById(R.id.menyu_characters_btn);
        plotBtn = view.findViewById(R.id.menyu_missionsplot_btn);
        decksBtn = view.findViewById(R.id.menyu_decks_btn);
        inventoryBtn = view.findViewById(R.id.menyu_inventory_btn);
        mapBtn = view.findViewById(R.id.menyu_map_btn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //outro mainmenyu2settings (move mainmenyu icons off)
                //intro mainmenyu2settings (move settings icons in)
                //THIS IS NOT A NEW ACTIVITY, RATHER WE ARE ADDING IN AND REMOVING MULTIPLE FRAGEMENTS ideally
                //Intent intented = new Intent(MainMenyuActivity.this, SettingsActivity.class);
                //startActivity(intented);
                //finish();
                mListener.menyuItemsBarSettingsPressed();
            }
        });

        charactersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //outro mainmenyu2characters (move mainmenyu icons off)
                //intro mainmenyu2characters (move characters icons on)
                //AGAIN, NOT A NEW ACTIVITY! YOU ARE ADDING FRAGEMENTS
                //((FragmentActivity)getActivity()).setCURRENT_LAYOUT("CHARACTERS_MENYU_LAYOUT");
                mListener.menyuItemsBarCharactersPressed();
            }
        });

        plotBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //THIS IS A NEW ACTIVITY
                //Intent intented = new Intent(MainMenyuActivity.this, PlotActivity.class);
                //startActivity(intented);
                //finish();
                mListener.menyuItemsBarPlotPressed();
            }
        });

        decksBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //not sure if i want this as a fragemnt > new activity or just a new activity period

                //add a pressed animation to lighten then redarken the icon
                //outro mainmenyu2decks (move mainmenyu icons off)
                //intro mainmenyu2decks (move deck icons in)
                //THIS IS NOT A NEW ACTIVITY, RATHER WE ARE ADDING IN AND REMOVING MULTIPLE FRAGEMENTS ideally
                mListener.menyuItemsBarDecksPressed();
            }
        });

        inventoryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //A NEW ACTIVITY
                //Intent intented = new Intent(MainMenyuActivity.this, InventoryActivity.class);
                //startActivity(intented);
                //finish();
                mListener.menyuItemsBarInventoryPressed();
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //add a pressed animation to lighten then redarken the icon
                //THIS IS A NEW ACTIVITY THAT SHOWS THE WHOLE MAP
                //Intent intented = new Intent(MainMenyuActivity.this, MapActivity.class);
                //startActivity(intented);
                //finish();
                mListener.menyuItemsBarMapPressed();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onMenyuItemsBarSelectedListener) {
            mListener = (onMenyuItemsBarSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onMenyuItemsBarSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onMenyuItemsBarSelectedListener {
        public void menyuItemsBarSettingsPressed();
        public void menyuItemsBarCharactersPressed();
        public void menyuItemsBarPlotPressed();
        public void menyuItemsBarDecksPressed();
        public void menyuItemsBarInventoryPressed();
        public void menyuItemsBarMapPressed();
    }

    public void recieveCURRENT_LAYOUT(String PREVIOUS_LAYOUT, String CURRENT_LAYOUT) {
        if (PREVIOUS_LAYOUT.equals(THIS_LAYOUT) && !CURRENT_LAYOUT.equals(THIS_LAYOUT)) {
            //remove the layout from the activity
        }
        else if(!PREVIOUS_LAYOUT.equals(THIS_LAYOUT) && CURRENT_LAYOUT.equals(THIS_LAYOUT)) {
            //add the layout to the activity
        }
    }

    public String toString() {
        return "menyu_itemsbar";
    }
}
