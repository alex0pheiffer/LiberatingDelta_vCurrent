package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended;
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_frontcharacter;
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_regionChapters_fragment;
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_region_map_btn;
import com.example.rpg_v4.Main_Menyu_Fragements.menyu_itemsbar;
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.RegionFragmentInterface;
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.region_1_fragment;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.the_MCs.Katherine;
import com.example.rpg_v4.basic_classes.the_cities.chipper_towne;
import com.example.rpg_v4.basic_classes.the_cities.maleficere_mansion;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;
import com.example.rpg_v4.db_files.RPG_ViewModel;
import com.example.rpg_v4.db_files.User_Cards;
import com.example.rpg_v4.db_files.User_Characters;
import com.example.rpg_v4.db_files.User_Decks;
import com.example.rpg_v4.db_files.User_EQPlayed;
import com.example.rpg_v4.db_files.User_Inventory;
import com.example.rpg_v4.db_files.User_Values;

import java.util.List;

public class MainMenyuActivity extends AppCompatActivity implements main_menyu_region_map_btn.onRegionMapBtnSelectedListener, region_1_fragment.onRegion1SelectedListener, main_menyu_frontcharacter.onMenyuFrontcharacterSelectedListener, menyu_itemsbar.onMenyuItemsBarSelectedListener, main_menyu_regionChapters_fragment.onRegionChaptersSelectedListener, com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended.onChapterExtendedSelectedListener {

    private int pl;
    private PL this_pl;

    private RPG_ViewModel rpgViewModel;
    private int updateUserValuesCounter;
    private int updateUserEQPlayedCounter;
    private int updateUserCharactersCounter;
    private int updateUserCardsCounter;
    private int updateUserDecksCounter;
    private int updateUserInventoryCounter;

    private View activity_whole;
    private TextView backbox_top_text;
    private main_menyu_region_map_btn mainMenyuRegionMapBtn;
    private Fragment regionFragment;
    private chapterExtended chapterExtendedFragment;
    private menyu_itemsbar itemsBar;
    private main_menyu_frontcharacter characterIcon;
    private main_menyu_regionChapters_fragment regionChapterListRecycler;

    private class layoutClass {
        private String CURRENT_LAYOUT;
        private String PREVIOUS_LAYOUT;

        layoutClass() {
            CURRENT_LAYOUT = "MAIN_MENYU_LAYOUT";
        }

        public boolean requestChange(String NEXT_LAYOUT) {
            String parsedNext = pageParse(NEXT_LAYOUT);
            String parsedCurrent = pageParse(CURRENT_LAYOUT);
            if (parsedNext.equals(parsedCurrent) || parsedNext.equals("MAIN_MENYU") || parsedCurrent.equals("MAIN_MENYU")) {
                return true;
            }
            else return false;
        }

        public void changeLayout(String NEXT_LAYOUT) {
            if (requestChange(NEXT_LAYOUT)) {
                this.PREVIOUS_LAYOUT = CURRENT_LAYOUT;
                this.CURRENT_LAYOUT = NEXT_LAYOUT;
                Log.d("LAYOUT","Layout Changed.");
            }
            else {
                throw new RuntimeException("mismatch layout attempt");
            }
        }

        private String pageParse(String layout) {
            boolean firstUnderscore = false, secondUnderscore = false;
            String parsed = "";
            while (!secondUnderscore) {
                if (layout.substring(0,1).equals("_")) {
                    if (firstUnderscore) {
                        secondUnderscore = true;
                    }
                    else {
                        firstUnderscore = true;
                    }
                }
                if (!secondUnderscore) {
                    parsed = parsed + layout.substring(0, 1);
                    layout = layout.substring(1);
                }
            }
            return parsed;
        }

        public boolean compareWithCurrent(String compare_layout) {
            return this.CURRENT_LAYOUT.equals(compare_layout);
        }

        public String getCURRENT_LAYOUT() {
            return this.CURRENT_LAYOUT;
        }

        public String getPREVIOUS_LAYOUT() {
            return this.PREVIOUS_LAYOUT;
        }
    }
    private layoutClass layoutSetter = new layoutClass();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment[] regions_frag = new Fragment[18];

    private class checkUserData {
        private List<User_Values> lValues;
        private List<User_EQPlayed> lEQPlayed;
        private List<User_Characters> lCharacters;
        private List<User_Cards> lCards;
        private List<User_Decks> lDecks;
        private List<User_Inventory> lInventory;
        private regions cur_region;
        public void setlValues(List<User_Values> vals) {
            this.lValues = vals;
            if (pl < 3 || this_pl == null) {
                pl = getPL();
                this_pl = PL_VendingMachine.getPL(pl);
            }
            cur_region = getCurrentRegion();
        }
        public void setlCharacters(List<User_Characters> lCharacters) {this.lCharacters = lCharacters;}
        public void setlEQPlayed(List<User_EQPlayed> lEQPlayed) {this.lEQPlayed = lEQPlayed;}
        public void setlCards(List<User_Cards> vals) {this.lCards = vals;}
        public void setlDecks(List<User_Decks> vals) {this.lDecks = vals;}
        public void setlInventory(List<User_Inventory> vals) {this.lInventory = vals;}

        public checkUserData() {
            cur_region = new Veneland();
            pl = 1;
        }

        private regions getCurrentRegion() {
            return this_pl.getRegion(lValues.get(0).getCur_region());
        }

        private Characters getCur_character() {
            Characters character = new Katherine();
            if (lValues != null) {
                this_pl.getCharacter(lValues.get(0).getFront_char());
            }
            return character;
        }
        //change this to be a real weapon object plz
        private String getCur_weapon() {
            String weapon = "default";
            if (lCharacters != null) {
                for (int n = 0; n < lCharacters.size(); n++) {
                    if (lCharacters.get(n).getName().equals(getCur_character().getName())) {
                        weapon = lCharacters.get(n).getWeapon_equip();
                        break;
                    }
                }
            }
            return weapon;
        }

        public User_Values changeCharacter(Characters character) {
            return new User_Values(lValues.get(0).getCur_PL(), lValues.get(0).getCur_phase(),lValues.get(0).getCur_okane(),character.getName(),lValues.get(0).getUsername(),lValues.get(0).getPassword(),lValues.get(0).getCur_region());
        }

        public User_Values changeOkane(int okane) {
            return new User_Values(lValues.get(0).getCur_PL(), lValues.get(0).getCur_phase(),okane,lValues.get(0).getFront_char(),lValues.get(0).getUsername(),lValues.get(0).getPassword(),lValues.get(0).getCur_region());
        }

        public regions getCur_region() {
            return cur_region;
        }

        public int getPL() {
            if (lValues != null) return lValues.get(0).getCur_PL();
            else return 1;
        }
    }
    checkUserData userDataChecker;


    //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menyu);

        activity_whole = findViewById(R.id.main_menyu_background);
        backbox_top_text = findViewById(R.id.mmc_backbox_top);

        userDataChecker = new checkUserData();

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);

        setObservers();

        //adding fragments
        FragmentTransaction ft = fragmentManager.beginTransaction();
        mainMenyuRegionMapBtn = main_menyu_region_map_btn.newInstance(userDataChecker.getCur_region().getNom(), pl);
        itemsBar = menyu_itemsbar.newInstance();
        characterIcon = main_menyu_frontcharacter.newInstance(userDataChecker.getCur_character().getName(), userDataChecker.getCur_weapon(),userDataChecker.getPL());
        ft.add(R.id.menyu_regionmap_btn_frag,mainMenyuRegionMapBtn);
        ft.add(R.id.itemsbar,itemsBar);
        ft.add(R.id.menyu_mmc_frag,characterIcon);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void setObservers() {
        updateUserValuesCounter=0;
        updateUserCardsCounter=0;
        updateUserCharactersCounter=0;
        updateUserDecksCounter=0;
        updateUserEQPlayedCounter=0;
        updateUserInventoryCounter=0;
        rpgViewModel.getlUserValues().observe(this, new Observer<List<User_Values>>() {
            @Override
            public void onChanged(@Nullable final List<User_Values> vals) {
                updateUserValuesCounter++;
                userDataChecker.setlValues(vals);
                if (updateUserValuesCounter==1) {
                    activity_whole.setBackground(getDrawable(userDataChecker.getCur_region().getDrawable_background()));
                    //filling region fragment list
                    //fillRegions_frag();
                }
            }
        });
        rpgViewModel.getlUserEQPlayed().observe(this,new Observer<List<User_EQPlayed>>() {
            @Override
            public void onChanged(@Nullable final List<User_EQPlayed> vals) {
                updateUserEQPlayedCounter++;
                userDataChecker.setlEQPlayed(vals);
                if (updateUserEQPlayedCounter==1) {
                    //init UI updates here
                }
            }
        });
        rpgViewModel.getlUserCharacters().observe(this,new Observer<List<User_Characters>>() {
            @Override
            public void onChanged(@Nullable final List<User_Characters> vals) {
                updateUserCharactersCounter++;
                userDataChecker.setlCharacters(vals);
                if (updateUserCharactersCounter==1) {
                    //init UI updates here
                }
            }
        });
        rpgViewModel.getlUserCards().observe(this,new Observer<List<User_Cards>>() {
            @Override
            public void onChanged(@Nullable final List<User_Cards> vals) {
                updateUserCardsCounter++;
                userDataChecker.setlCards(vals);
                if (updateUserCardsCounter==1) {
                    //init UI updates here
                }
            }
        } );
        rpgViewModel.getlUserDecks().observe(this,new Observer<List<User_Decks>>() {
            @Override
            public void onChanged(@Nullable final List<User_Decks> vals) {
                updateUserDecksCounter++;
                userDataChecker.setlDecks(vals);
                if (updateUserDecksCounter==1) {
                    //init UI updates here
                }
            }
        } );
        rpgViewModel.getlUserInventory().observe(this,new Observer<List<User_Inventory>>() {
            @Override
            public void onChanged(@Nullable final List<User_Inventory> vals) {
                updateUserInventoryCounter++;
                userDataChecker.setlInventory(vals);
                if (updateUserInventoryCounter==1) {
                    //init UI updates here
                }
            }
        } );
    }

    //todo
    public void fillRegions_frag() {
        System.out.println("OUR CUR PL: "+userDataChecker.getPL());
        regions_frag[0] = region_1_fragment.newInstance(userDataChecker.getPL());
        //regions_frag[1] = region_2_fragment.newInstance(userDataChecker.getPL());
    }

    public void setCharacter(Characters character) {
        //first, update our db
        //User_Values changecharacter = userDataChecker.changeCharacter(character);
        //rpgViewModel.updateFrontChar(changecharacter);
        //then change our char that appears

    }

    public void regionBtnPressed() {
        if (layoutSetter.compareWithCurrent(mainMenyuRegionMapBtn.getCURRENT_LAYOUT())) {
            boolean regions_match = userDataChecker.getCur_region().getNom().equals(mainMenyuRegionMapBtn.getRegion().getNom());
            if (regions_match) {
                //terminate the fragment
                System.out.println("Terminating RegionBtn");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionFragment = region_1_fragment.newInstance(userDataChecker.getPL());
                ft.add(R.id.menyu_regionmap_btn_frag,regionFragment);
                ft.remove(characterIcon);
                ft.remove(itemsBar);
                ft.remove(mainMenyuRegionMapBtn);
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout(((RegionFragmentInterface)regionFragment).getCURRENT_LAYOUT());//menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
                //intro mainmenyu2region (move region icons on)
            }
            else {
                throw new RuntimeException("mismatch regions");
            }
        }
        else {
            throw new RuntimeException("mismatch CURRENT_LAYOUT");
        }
    }

    //regionBtns
    public void cityPtPressed(String city) {
        boolean regions_match = userDataChecker.getCur_region().getNom().equals(((RegionFragmentInterface)regionFragment).getRegion().getNom());
        if (regions_match) {

            cityPt town;

            if (city.equals("Maleficere Mansion")) {
                town = new maleficere_mansion();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(userDataChecker.getCur_region().getNom(), town.getNom(),userDataChecker.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout("REGION_MAP_CITY");
            }

            else if (city.equals("Chipper Towne")) {
                town = new chipper_towne();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(userDataChecker.getCur_region().getNom(), town.getNom(),userDataChecker.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout("REGION_MAP_CITY");
            }

        }
        else {
            throw new RuntimeException("mismatch regions");
        }
    }

    //regionChapterListFragment
    public void chapterSelected(regions region, Chapter chapter) {
        //change the backbox_top to display the chapter name+img
        //change backbox_bottom from recycleviewer to a display of
        //show go button...and determine if go is available or not
        FragmentTransaction ft = fragmentManager.beginTransaction();
        chapterExtendedFragment = chapterExtended.newInstance(pl);
        ft.replace(R.id.mmc_backbox_bottom,chapterExtendedFragment);
        backbox_top_text.setText(chapter.getNom());
        ft.addToBackStack(null);
        ft.commit();
        layoutSetter.changeLayout("REGION_MAP_CHAPTER");
    }

    //regionChapterExtendedFragment
    public void onChapterExtendedPressed(Chapter chapter) {
        //lol this probably isnt needed? i dont think this is going to do anything... maybe open an entire window?
    }

    public void backBtnPressed(String layout) {
        //pressing the back button will always
    }

    //ItemsBarBtns
    public void menyuItemsBarSettingsPressed() {}
    public void menyuItemsBarCharactersPressed() {}
    public void menyuItemsBarPlotPressed() {}
    public void menyuItemsBarDecksPressed() {}
    public void menyuItemsBarInventoryPressed() {}
    public void menyuItemsBarMapPressed() {}


}
