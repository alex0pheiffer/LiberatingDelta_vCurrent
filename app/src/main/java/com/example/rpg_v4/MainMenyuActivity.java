package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.example.rpg_v4.basic_classes.BlankDeck;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Cards.*;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.Weapon;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.inventI;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

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
    //todo: check the max through the db...#rows
    private int MAX_INVENTORY = 100;

    private View activity_whole;
    private TextView backbox_top_text;
    private main_menyu_region_map_btn mainMenyuRegionMapBtn;
    private Fragment regionFragment;
    private chapterExtended chapterExtendedFragment;
    private menyu_itemsbar itemsBar;
    private main_menyu_frontcharacter characterIcon;
    private main_menyu_regionChapters_fragment regionChapterListRecycler;
    private View bufferbackgTop, bufferbackgBottom, bufferbackgLeft, bufferbackgRight, mmc_backbox ;


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

        public boolean compareParsed(String layout1, String layout2) {
            return (pageParse(layout1).equals(pageParse(layout2)));
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
        public void setlCards(List<User_Cards> vals) { this.lCards = vals; }
        public void setlDecks(List<User_Decks> vals) {this.lDecks = vals;}
        public void setlInventory(List<User_Inventory> vals) {this.lInventory = vals;}

        public checkUserData() {
            cur_region = new Veneland();
            pl = 1;
        }

        public void fillDecks() {
            thisUserInventory.fillDecks((ArrayList<User_Decks>)lDecks, (ArrayList<User_Cards>)lCards);
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

    private class userInventory {
        private BlankDeck allCards;
        private ArrayList<Deck> allDecks;
        private ArrayList<String> allDecksNames;
        //please don't load the following unless inventory is opened...the exception is those equipped by characters
        private ArrayList<Weapon> allWeapons;
        private ArrayList<inventI> allNonWeapons;
        private ArrayList<inventI> allItems;

        //todo update this as you add more card classes
        private Class[] cardClasses = {Distract.class,DiveLeft.class,RockToss.class,Shove.class,Splash.class,Struggle.class,TreeHide.class};
        private ArrayList<String> cardClassesNames;

        public userInventory() {
            allCards = new BlankDeck();
            allDecks = new ArrayList<Deck>();
            allWeapons = new ArrayList<Weapon>();
            allNonWeapons = new ArrayList<inventI>();
            allItems = new ArrayList<inventI>();
            cardClassesNames = new ArrayList<String>();
            allDecksNames = new ArrayList<String>();
            for (Class n : cardClasses) {
                cardClassesNames.add(n.getName());
            }
            alphabetizeClassCard();
        }

        public void fillDecks(ArrayList<User_Decks> lDecks, ArrayList<User_Cards> lCards) {
            //the db cards need to be in alphabetical order
            lCards = alphabetizelCards(lCards);

            if (allDecks.size() == 0) {

                for (User_Decks d : lDecks) {
                    String charer;
                    charer = d.getChar_equip();
                    if (d.getChar_equip().equals("None")) {
                        charer = null;
                    }
                    addDeck(new Deck(d.getName(),charer),true);
                }

                String previous_card = "";
                String previous_deck = "";
                int deckAmt = 0;
                Card temp = null;
                int sudoCardIndex = 0;
                Class tempClass = null;
                for (User_Cards c : lCards) {
                    //if card is not the same as the previous OR null, create a new card instance
                    if (temp == null || !previous_card.equals(c.getName())) {
                        tempClass = cardClasses[Collections.binarySearch(cardClassesNames,c.getName())];
                        try{ temp = (Card) tempClass.newInstance(); }
                        catch(Exception e){ throw new RuntimeException("Class "+tempClass.getName()+" is not a valid card."); }
                        sudoCardIndex = allCards.addCard(temp);
                        if (!c.getDeck().equals("None")) {
                            allDecks.get(Collections.binarySearch(allDecksNames,c.getDeck())).addCard(temp);
                        }
                        previous_deck = c.getDeck();
                        deckAmt = 1;
                        previous_card = c.getName();
                    }
                    //same as the previous card
                    else {
                        if (previous_deck.equals(c.getDeck())) {
                            //same deck as the last card
                            if (deckAmt < allCards.getSudoCard(sudoCardIndex).getAmount()) {
                                //this type of card in this deck has less than the number of cards created
                                //>>thus use a card in allCards sudoCard
                                if (!c.getDeck().equals("None")) {
                                    allDecks.get(Collections.binarySearch(allDecksNames,c.getDeck())).addCard(allCards.getSudoCard(sudoCardIndex).getCard(deckAmt));
                                }
                            }
                            else {
                                //the amount of cards in this deck are the same or greater than cards current created
                                //please create more
                                if (deckAmt >= c.getAmount()) {
                                    throw new RuntimeException("cards "+c.getName()+" created exceed the said db amount");
                                }
                                try{ temp = (Card) tempClass.newInstance(); }
                                catch(Exception e){ throw new RuntimeException("Class "+tempClass.getName()+" is not a valid card."); }
                                allCards.addCard(temp);
                                if (!c.getDeck().equals("None")) {
                                    allDecks.get(Collections.binarySearch(allDecksNames,c.getDeck())).addCard(temp);
                                }
                            }
                            deckAmt++;
                        }
                        else {
                            //new (diff) deck from the last card
                            previous_deck = c.getDeck();
                            deckAmt = 1;
                            if (!c.getDeck().equals("None")) {
                                allDecks.get(Collections.binarySearch(allDecksNames,c.getDeck())).addCard(allCards.getSudoCard(sudoCardIndex).getCard(0));
                            }
                            else {
                                //if the deck is "None" you probably need to make a new card...
                                if(c.getAmount() > allCards.getSudoCard(sudoCardIndex).getAmount()) {
                                    //we still need to make a new card
                                    try{ temp = (Card) tempClass.newInstance(); }
                                    catch(Exception e){ throw new RuntimeException("Class "+tempClass.getName()+" is not a valid card."); }
                                    allCards.addCard(temp);
                                }
                            }
                        }
                    }
                }

            }
            else {
                throw new RuntimeException("Cannot fill decks when they're not empty");
            }
        }

        public void addCardtoDeck(Deck deck, Card card) {
            //todo add to db
            deck.addCard(card);
        }

        public void addCard(Card card) {
            //todo add to                                                                                                                                                                                                                                                                                                                                                                                                                                             db
            allCards.addCard(card);
        }

        public void removeCardfromDeck(Deck deck, Card card) {
            //todo remove from db
            deck.removeCard(card);
        }

        public void removeCard(Card card) {
            //todo remove from db
            allCards.removeCard(card);
        }

        private void alphabetizeClassCard() {
            //insertion sort
            String tempStr;
            Class tempCl;
            for (int i = 0; i < cardClasses.length; i++)
            {
                for (int j = i + 1; j < cardClasses.length; j++)
                {
                    if (cardClassesNames.get(i).compareTo(cardClassesNames.get(j))>0)
                    {
                        tempStr = cardClassesNames.get(i);
                        tempCl = cardClasses[i];
                        cardClassesNames.set(i,cardClassesNames.get(j));
                        cardClasses[i] = cardClasses[j];
                        cardClassesNames.set(j, tempStr);
                        cardClasses[j] = tempCl;
                    }
                }
            }
        }

        private ArrayList<User_Cards> alphabetizelCards(ArrayList<User_Cards> lCards) {
            boolean stillLarger = true;
            ArrayList<User_Cards> alphabetcards = new ArrayList<User_Cards>();
            int j;
            int newIndex = 0;
            alphabetcards.add(lCards.get(0));
            for (int i=1; i < lCards.size();i++) {
                alphabetcards.add(lCards.get(i));
                j = i;
                stillLarger = true;
                while (stillLarger) {
                    j--;
                    if (alphabetcards.get(j).getName().compareTo(alphabetcards.get(i).getName()) < 0) {
                        stillLarger = false;
                        newIndex = j+1;
                    }
                    else if (alphabetcards.get(j).getName().compareTo(alphabetcards.get(i).getName()) == 0) {
                        //the names are the same...sort by deck
                        if (alphabetcards.get(j).getDeck().compareTo(alphabetcards.get(i).getDeck()) == 0) {
                            stillLarger = false;
                            newIndex = j+1;
                        }
                        else {
                            while(stillLarger) {
                                if(alphabetcards.get(j).getName().compareTo(alphabetcards.get(i).getName()) == 0) {
                                    if (alphabetcards.get(j).getDeck().compareTo(alphabetcards.get(i).getDeck()) <= 0) {
                                        stillLarger = false;
                                        newIndex = j+1;
                                    }
                                    else if (j==0) {
                                        stillLarger = false;
                                        newIndex = 0;
                                    }
                                }
                                else {
                                    stillLarger = false;
                                    newIndex = j+1;
                                }
                                j--;
                            }
                        }
                    }
                    else if (j==0) {
                        stillLarger = false;
                        newIndex = 0;
                    }
                }
                alphabetcards.add(newIndex,alphabetcards.get(i));
                alphabetcards.remove(i+1);
            }
            return alphabetcards;
        }

        public void addDeck(Deck addedDeck, boolean indb) {
            //insertion sort
            if (allDecks.size() == 0) {
                allDecks.add(addedDeck);
                allDecksNames.add(addedDeck.getNom());
            }
            else {
                boolean stillLarger = true;
                int newIndex = 0;
                int j = allDecks.size();
                stillLarger = true;
                while (stillLarger) {
                    j--;
                    int compare = allDecks.get(j).getInstanceName().compareTo(addedDeck.getInstanceName());
                    if ( compare < 0) {
                        stillLarger = false;
                        newIndex = j+1;
                    }
                    else if(compare == 0) {
                        //eventually you'll need to give the user a dialog box telling them to edit the name of their deck
                        throw new RuntimeException("INVALID DECK NAME: deck name is the same as another deck...");
                    }
                    else if (j==0) {
                        stillLarger = false;
                        newIndex = 0;
                    }
                }
                allDecks.add(newIndex, addedDeck);
                allDecksNames.add(newIndex, addedDeck.getNom());
            }
            if (!indb) {
                //todo add this new deck to the db
            }
        }

        public BlankDeck getAllCards() {
            return allCards;
        }

        public ArrayList<Deck> getAllDecks() {
            return allDecks;
        }

        public ArrayList<Weapon> getAllWeapons() {
            if (allWeapons.size() == 0) {
                //create inventory options
            }
            return allWeapons;
        }

        public ArrayList<inventI> getAllNonWeapons() {
            if (allNonWeapons.size() == 0) {
                //create inventory options
            }
            return allNonWeapons;
        }

        public ArrayList<inventI> getAllItems() {
            if (allWeapons.size() == 0) {
                //create inventory options
            }
            return allItems;
        }

        public void createInventoryOptions() {
            //not finished
            if (allWeapons.size() == 0) {
                //create all Weapons
            }
            if (allNonWeapons.size() == 0) {
                //create all non weapons
            }
            if (allItems.size() == 0) {

                for (int i = 0; i<allWeapons.size(); i++){
                    allItems.add(allWeapons.get(i));
                }
                for (int i = 0; i<allNonWeapons.size(); i++) {
                    allItems.add(allNonWeapons.get(i));
                }
            }
        }

    }
    userInventory thisUserInventory;

    //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menyu);

        activity_whole = findViewById(R.id.main_menyu_background);
        backbox_top_text = findViewById(R.id.mmc_backbox_top);
        bufferbackgTop = findViewById(R.id.false_background_top);
        bufferbackgBottom = findViewById(R.id.false_background_bottom);
        bufferbackgLeft = findViewById(R.id.false_background_left);
        bufferbackgRight = findViewById(R.id.false_background_right);
        mmc_backbox = findViewById(R.id.menyu_mmc_backbox);

        userDataChecker = new checkUserData();
        thisUserInventory = new userInventory();

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
                    if(updateUserDecksCounter==1) {
                        userDataChecker.fillDecks();
                    }
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
                    if(updateUserCardsCounter==1) {
                        userDataChecker.fillDecks();
                    }
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
                bufferbackgBottom.setAlpha(0);
                bufferbackgTop.setAlpha(0);
                bufferbackgLeft.setAlpha(0);
                bufferbackgRight.setAlpha(0);
                mmc_backbox.setBackground(null);
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
        //pressing the back button will always return you to Main Menyu
        System.out.println("mm backpressed");
        clearFragments(layout);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.menyu_mmc_frag,characterIcon);
        ft.add(R.id.itemsbar,itemsBar);
        ft.add(R.id.menyu_regionmap_btn_frag,mainMenyuRegionMapBtn);
        ft.addToBackStack(null);
        ft.commit();
        bufferbackgBottom.setAlpha(1);
        bufferbackgTop.setAlpha(1);
        bufferbackgLeft.setAlpha(1);
        bufferbackgRight.setAlpha(1);
        mmc_backbox.setBackgroundColor(getColor(R.color.genericElectric));
        layoutSetter.changeLayout("MAIN_MENYU_LAYOUT");
    }

    public void goBtnPressed() {

    }

    //ItemsBarBtns
    public void menyuItemsBarSettingsPressed() {}
    public void menyuItemsBarCharactersPressed() {}
    public void menyuItemsBarPlotPressed() {}
    public void menyuItemsBarDecksPressed() {
        //bring in the charswitch arrows
        //remove the region btn
        //replace the itemsbar with a titlebar flush right (half the screen..)
        //add a backbtn
        //add deck scroller
        //make sure that the middle deck in the deck scroller is the equipped deck. In the case of all char, it can be the last viewed deck... but you'll never open to all characters
    }
    public void menyuItemsBarInventoryPressed() {}
    public void menyuItemsBarMapPressed() {
        //okay i think we're opening a new activity... lets see how much this fucks up our data
        Intent intented = new Intent(MainMenyuActivity.this, wholeMapActivity.class);
        Bundle args = new Bundle();
        args.putString("CURRENT_REGION", userDataChecker.getCur_region().getNom());
        args.putInt("PlayerLevel",pl);
        intented.putExtras(args);
        startActivity(intented);
    }

    //DecksViewer


    public void clearFragments(String layout) {
        if (layoutSetter.compareParsed(layout,"REGION_MAP_LAYOUT")) {
            //everything gets removed but the background
            FragmentTransaction ft = fragmentManager.beginTransaction();
            for(Fragment fragment : fragmentManager.getFragments()) {
                if (fragment != null) {
                    Log.d("TRANSACTIONTEST","removing: "+fragment);
                    ft.remove(fragment);
                }
            }
            ft.addToBackStack(null);
            ft.commit();
            backbox_top_text.setText("");
        }
        else if (layoutSetter.compareParsed(layout,"CHARACTER_VIEW_LAYOUT")) {

        }
        System.out.println("layouts cleared");
    }

}
