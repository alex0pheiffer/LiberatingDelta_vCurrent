package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_frontcharacter;
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_regionChapters_fragment;
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_region_map_btn;
import com.example.rpg_v4.Main_Menyu_Fragements.menyu_itemsbar;
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.RegionFragmentInterface;
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.region_1_fragment;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.the_MCs.Katherine;
import com.example.rpg_v4.basic_classes.the_cities.chipper_towne;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;
import com.example.rpg_v4.db_files.RPG_ViewModel;
import com.example.rpg_v4.db_files.User_Cards;
import com.example.rpg_v4.db_files.User_Characters;
import com.example.rpg_v4.db_files.User_Decks;
import com.example.rpg_v4.db_files.User_EQPlayed;
import com.example.rpg_v4.db_files.User_Inventory;
import com.example.rpg_v4.db_files.User_Values;

import java.util.List;

public class MainMenyuActivity extends AppCompatActivity implements main_menyu_region_map_btn.onRegionMapBtnSelectedListener, region_1_fragment.onRegion1SelectedListener, main_menyu_frontcharacter.onMenyuFrontcharacterSelectedListener, menyu_itemsbar.onMenyuItemsBarSelectedListener, main_menyu_regionChapters_fragment.onRegionChaptersSelectedListener {

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
    private TextView regionLabel, regionBtn,testText;
    private main_menyu_region_map_btn mainMenyuRegionMapBtn;
    private Fragment regionFragment;
    private menyu_itemsbar itemsBar;
    private main_menyu_frontcharacter characterIcon;
    private main_menyu_regionChapters_fragment regionChapterListRecycler;

    private String CURRENT_LAYOUT;  //is this mainmenyu, regionmenyu, settingsmenyu, charviewmenyu, decksmenyu
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
            this.lValues = vals; cur_region = getCurrentRegion();
            if (pl < 3 || this_pl == null) {
                pl = getPL();
                this_pl = PL_VendingMachine.getPL(pl);
            }
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

        CURRENT_LAYOUT= "MAIN_MENYU_LAYOUT";
        activity_whole = findViewById(R.id.main_menyu_background);

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

    public void fillRegions_frag() {
        System.out.println("OUR CUR PL: "+userDataChecker.getPL());
        regions_frag[0] = region_1_fragment.newInstance(userDataChecker.getPL());
        //regions_frag[1] = region_2_fragment.newInstance(userDataChecker.getPL());
    }

    public boolean checkLayout(String layout) {
        return this.CURRENT_LAYOUT.equals(layout);
    }

    public void setCURRENT_LAYOUT(String CURRENT_LAYOUT) {
        //this.CURRENT_LAYOUT = CURRENT_LAYOUT;
    }

    public String getCURRENT_LAYOUT() {
        return CURRENT_LAYOUT;
    }

    public void setCharacter(Characters character) {
        //first, update our db
        //User_Values changecharacter = userDataChecker.changeCharacter(character);
        //rpgViewModel.updateFrontChar(changecharacter);
        //then change our char that appears

    }

    public void regionBtnPressed() {
        if (CURRENT_LAYOUT.equals(mainMenyuRegionMapBtn.getCURRENT_LAYOUT())) {
            boolean regions_match = userDataChecker.getCur_region().getNom().equals(mainMenyuRegionMapBtn.getRegion().getNom());
            if (regions_match) {
                //terminate the fragment
                System.out.println("Terminating RegionBtn");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionFragment = region_1_fragment.newInstance(userDataChecker.getPL());
                ft.add(R.id.menyu_regionmap_btn_frag,regionFragment);
                ft.remove(characterIcon);
                ft.remove(mainMenyuRegionMapBtn);
                ft.addToBackStack(null);
                ft.commit();
                CURRENT_LAYOUT=((RegionFragmentInterface)regionFragment).getCURRENT_LAYOUT();//menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
                //intro mainmenyu2region (move region icons on)
            }
            else {
                throw new RuntimeException("mismatch regions");
            }
        }
    }

    //region1Btns
    public void MaleficereMansionPressed() {}
    public void ChipperTownePressed() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(userDataChecker.getCur_region().getNom(), new chipper_towne().getNom(),userDataChecker.getPL());
        //you will need to check if this holder is EMPTY by using our CURRENT_LAYOUT variable
        //MAKE BETTER FUCKING USE OF THIS. BE ORGANIZED
        ft.add(R.id.mmc_backbox_bottom,regionChapterListRecycler);
        ft.addToBackStack(null);
        ft.commit();
    }

    //ItemsBarBtns
    public void menyuItemsBarSettingsPressed() {}
    public void menyuItemsBarCharactersPressed() {}
    public void menyuItemsBarPlotPressed() {}
    public void menyuItemsBarDecksPressed() {}
    public void menyuItemsBarInventoryPressed() {}
    public void menyuItemsBarMapPressed() {}

    //regionChapterListFragment
    public void chapterSelected(regions region, Chapter chapter) {}
}
