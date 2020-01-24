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

import com.example.rpg_v4.Main_Menyu_Fragements.CharacterArrowFragment;
import com.example.rpg_v4.Main_Menyu_Fragements.characterViewFragment;
import com.example.rpg_v4.Main_Menyu_Fragements.dedicatedBackBtn;
import com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended;
import com.example.rpg_v4.Main_Menyu_Fragements.deckViewFragment;
import com.example.rpg_v4.Main_Menyu_Fragements.deckViewer_deckBar;
import com.example.rpg_v4.Main_Menyu_Fragements.linearCardViewFragment;
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
import com.example.rpg_v4.basic_classes.abstractDeck;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.inventI;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.str2card;
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
import java.util.Collections;
import java.util.List;

public class MainMenyuActivity extends AppCompatActivity implements main_menyu_region_map_btn.onRegionMapBtnSelectedListener, region_1_fragment.onRegion1SelectedListener, main_menyu_frontcharacter.onMenyuFrontcharacterSelectedListener, menyu_itemsbar.onMenyuItemsBarSelectedListener, main_menyu_regionChapters_fragment.onRegionChaptersSelectedListener, com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended.onChapterExtendedSelectedListener, deckViewFragment.deckRecyclerListener, CharacterArrowFragment.onCharacterArrowFragmentInteraction, dedicatedBackBtn.nonRegionBackButtonListener, deckViewer_deckBar.deckViewerDeckBarListener, characterViewFragment.characterViewFragmentListener, linearCardViewFragment.linearCardViewListener {

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
    private characterViewFragment charViewer;
    private deckViewFragment deckViewerRecycler;
    private deckViewer_deckBar deckViewerBar;
    private linearCardViewFragment cardViewFragment, cardEditFragmentTop, cardEditFragmentBottom;
    private dedicatedBackBtn backBtn;
    private CharacterArrowFragment MMCarrowUp, MMCarrowDown;
    private main_menyu_regionChapters_fragment regionChapterListRecycler;
    private View bufferbackgTop, bufferbackgBottom, bufferbackgLeft, bufferbackgRight, mmc_backbox;
    private abstractDeck theQuickGrabDeck;


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

        public String getDefaultPage(String layout) {
            return pageParse(layout)+"_LAYOUT";
        }

        //returns if the layout (page_page_layout) has two parts to it, making it a subpage (layout == layout_layout)
        public boolean isSubpage(String layout) {
            int underscores = 0;
            for (int i=0; i < layout.length(); i++) {
                if (layout.substring(i,i+1).equals("_")) underscores++;
                if (underscores == 4) return true;
            }
            //only has 3 or less underscores
            return false;
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

    private class dataController {
        //combination of checkUserData and userInventory in order to prevent utmost confusion...

        //mainCharacter data storage...
        private int KatieLevel;
        private int VivLevel;
        private int DeltaLevel;

        private class checkUserData {
            private List<User_Values> lValues;
            private List<User_EQPlayed> lEQPlayed;
            private List<User_Characters> lCharacters;
            private List<User_Cards> lCards;
            private List<User_Decks> lDecks;
            private List<User_Inventory> lInventory;
            private List<User_Cards> deckNameCards;
            private regions cur_region;
            public void setlValues(List<User_Values> vals) {
                this.lValues = vals;
                if (pl < 3 || this_pl == null) {
                    pl = getPL();
                    this_pl = PL_VendingMachine.getPL(pl);
                }
                else if (lValues.get(0).getCur_PL() != pl) {
                    //update our pl, it has changed
                    pl = lValues.get(0).getCur_PL();
                    this_pl = PL_VendingMachine.getPL(pl);
                }
                cur_region = getCurrentRegion();
            }
            public void setlCharacters(List<User_Characters> lCharacters) {this.lCharacters = lCharacters;}
            public void setlEQPlayed(List<User_EQPlayed> lEQPlayed) {this.lEQPlayed = lEQPlayed;}
            public void setlCards(List<User_Cards> vals) { this.lCards = vals; }
            public void setlDecks(List<User_Decks> vals) {this.lDecks = vals;}
            public void setlInventory(List<User_Inventory> vals) {this.lInventory = vals;}
            public void setDeckNameCards(List<User_Cards> vals) {this.deckNameCards = vals;}

            public void addCard(User_Cards userCard) {rpgViewModel.insert(userCard);}
            public void addDeck(User_Decks userDeck) {rpgViewModel.insert(userDeck);}
            public void addInventory(User_Inventory userInventory) {rpgViewModel.insert(userInventory);}
            public void addEQPlayed(User_EQPlayed userEQPlayed) {rpgViewModel.insert(userEQPlayed);}

            public void deleteInventory(User_Inventory userInventory) {rpgViewModel.deleteInventory(userInventory);}
            public void deleteCard(User_Cards userCard) {rpgViewModel.deleteCard(userCard);}
            public void deleteDeck(User_Decks userDeck) {rpgViewModel.deleteDeck(userDeck);}
            public void deleteEQPlayed(User_EQPlayed userEqPlayed) {rpgViewModel.deleteEQPlayed(userEqPlayed);}

            //please remember these are located by ID NUMBER so the given userData must be FROM THE DB, >>NOT<< NEW
            private void updateRegion(User_Values userValues) {rpgViewModel.updateRegion(userValues);}
            private void updatePhase(User_Values userValues) {rpgViewModel.updatePhase(userValues);}
            private void updateFrontChar(User_Values userValues) {rpgViewModel.updateFrontChar(userValues);}
            private void updateOkane(User_Values userValues) {rpgViewModel.updateOkane(userValues);}
            private void updateCompleted(User_EQPlayed userEQPlayed) {rpgViewModel.updateCompleted(userEQPlayed);}
            private void updateLevel(User_Characters userCharacters) {rpgViewModel.updateLevel(userCharacters);}
            private void updateRank(User_Characters userCharacters) {rpgViewModel.updateRank(userCharacters);}
            private void updateDeck(User_Characters userCharacters) {rpgViewModel.updateDeck(userCharacters);}
            private void updateItem(User_Characters userCharacters) {rpgViewModel.updateItem(userCharacters);}
            private void updateVenelandEXP(User_Characters userCharacters) {rpgViewModel.updateRegion1exp(userCharacters);}
            private void updatePietasEXP(User_Characters userCharacters) {rpgViewModel.updateRegion23exp(userCharacters);}
            private void updateStonesEXP(User_Characters userCharacters) {rpgViewModel.updateRegion4exp(userCharacters);}
            private void updateHdrEXP(User_Characters userCharacters) {rpgViewModel.updateRegion5exp(userCharacters);}
            private void updateRegion6EXP(User_Characters userCharacters) {rpgViewModel.updateRegion6exp(userCharacters);}
            private void updateRegion7EXP(User_Characters userCharacters) {rpgViewModel.updateRegion7exp(userCharacters);}
            private void updateRegion89EXP(User_Characters userCharacters) {rpgViewModel.updateRegion89exp(userCharacters);}
            private void updateRegion10EXP(User_Characters userCharacters) {rpgViewModel.updateRegion10exp(userCharacters);}
            private void updateRegion11EXP(User_Characters userCharacters) {rpgViewModel.updateRegion11exp(userCharacters);}
            private void updateNebulaEXP(User_Characters userCharacters) {rpgViewModel.updateRegion12exp(userCharacters);}
            private void updateRegion13EXP(User_Characters userCharacters) {rpgViewModel.updateRegion13exp(userCharacters);}
            private void updateIcecubeEXP(User_Characters userCharacters) {rpgViewModel.updateRegion14exp(userCharacters);}
            private void updateRupesEXP(User_Characters userCharacters) {rpgViewModel.updateRegion16exp(userCharacters);}
            private void updatePetraEXP(User_Characters userCharacters) {rpgViewModel.updateRegion17exp(userCharacters);}
            private void updateJuslynEXP(User_Characters userCharacters) {rpgViewModel.updateRegion18exp(userCharacters);}
            private void updateMaceriaEXP(User_Characters userCharacters) {rpgViewModel.updateRegion19exp(userCharacters);}
            private void updateNorthEXP(User_Characters userCharacters) {rpgViewModel.updateRegion20exp(userCharacters);}
            private void updateAmount(User_Inventory userInventory) {rpgViewModel.updateAmount(userInventory);}
            private void updateChar(User_Decks userDecks) {rpgViewModel.updateChar(userDecks);}
            private void updateLabel(User_Decks userDecks) {rpgViewModel.updateLabel(userDecks);}
            private void updateLen(User_Decks userDecks) {rpgViewModel.updateLen(userDecks);}
            private void updateAmount(User_Cards userCards) {rpgViewModel.updateAmount(userCards);}
            private void updatePosition(User_Cards userCards) {rpgViewModel.updatePosition(userCards);}

            public List<User_Cards> getNameCards(String cardName) {
                rpgViewModel.findNameCards(cardName);
                return deckNameCards;
            }

            public checkUserData() {
                cur_region = new Veneland();
                pl = 1;
            }

            public void fillDecks() {
                thisUserInventory.fillDecks((ArrayList<User_Decks>)lDecks, (ArrayList<User_Cards>)lCards);
            }

            public void removeDeck(String name) {
                //first remove the User_Cards in this deck
                for (int n = 0; n < lCards.size(); n++) {
                    if (lCards.get(n).getDeck().compareTo(name) == 0) {
                        //the card is in the deck
                        deleteCard(lCards.get(n));
                        Log.d("NOTICE","index is modified... might be removing a card");
                        //this index is decreased because lCards is assumed to get modified because the db is modified
                        n--;
                    }
                }
                //remove the actual User_Decks from the db
                User_Decks tempdeck = null;
                for (int n = 0; n < lDecks.size(); n++) {
                    if (lDecks.get(n).getName().compareTo(name) == 0) {
                        tempdeck = lDecks.get(n);
                    }
                }
                if (tempdeck == null) {
                    throw new RuntimeException("Trying to remove deck: "+name+", but it DNE in db");
                }
                deleteDeck(tempdeck);
            }

            private regions getCurrentRegion() {
                return this_pl.getRegion(lValues.get(0).getCur_region());
            }

            private Characters getCur_character() {
                Characters character = new Katherine();
                if (lValues != null) {
                    character = this_pl.getCharacter(lValues.get(0).getFront_char());
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

            public void changeCharacter(Characters character) {
                User_Values value = lValues.get(0);
                value.setFront_char(character.getName());
                updateFrontChar(value);
            }

            public int getKatieLevelExp() {
                return lCharacters.get(0).getLevel();
            }

            public int getDeltaLevelExp() {
                return lCharacters.get(1).getLevel();
            }

            public int getVivLevelExp() {
                return lCharacters.get(2).getLevel();
            }

            public void changeSize(User_Decks deck, int size) {
                deck.setLength(size);
                updateLen(deck);
            }

            public void changeOkane(int okane) {
                User_Values value = lValues.get(0);
                value.setCur_okane(okane);
                updateOkane(value);
            }

            public void changeAmt(User_Cards card, int amt) {
                card.setAmount(amt);
                updateAmount(card);
            }

            public void changeRegionExp(regions region, main_character character, int newEXP) {
                int regionNum = region.getRegionNum();
                //create userCharacters
                User_Characters tempUserCharacter;
                if (character.toString().compareTo(lCharacters.get(0).getName()) == 0) {
                    //character is Katherine
                    tempUserCharacter = lCharacters.get(0);
                }
                else if (character.toString().compareTo(lCharacters.get(1).getName()) == 0) {
                    //character is Delta
                    tempUserCharacter = lCharacters.get(1);
                }
                else if (character.toString().compareTo(lCharacters.get(2).getName()) == 0) {
                    //character is Vivian
                    tempUserCharacter = lCharacters.get(2);
                }
                else {
                    throw new RuntimeException("Character name "+character.toString()+" doesn't match any saved character");
                }

                if (regionNum == 1) {
                    tempUserCharacter.setRegion1exp(newEXP);
                    updateVenelandEXP(tempUserCharacter);
                }
                else if (regionNum == 23) {
                    tempUserCharacter.setRegion23exp(newEXP);
                    updatePietasEXP(tempUserCharacter);
                }
                else if (regionNum == 4) {
                    tempUserCharacter.setRegion4exp(newEXP);
                    updateStonesEXP(tempUserCharacter);
                }
                else if (regionNum == 5) {
                    tempUserCharacter.setRegion5exp(newEXP);
                    updateHdrEXP(tempUserCharacter);
                }
                else if (regionNum == 6) {
                    tempUserCharacter.setRegion6exp(newEXP);
                    updateRegion6EXP(tempUserCharacter);
                }
                else if (regionNum == 7) {
                    tempUserCharacter.setRegion7exp(newEXP);
                    updateRegion7EXP(tempUserCharacter);
                }
                else if (regionNum == 89) {
                    tempUserCharacter.setRegion89exp(newEXP);
                    updateRegion89EXP(tempUserCharacter);
                }
                else if (regionNum == 10) {
                    tempUserCharacter.setRegion10exp(newEXP);
                    updateRegion10EXP(tempUserCharacter);
                }
                else if (regionNum == 11) {
                    tempUserCharacter.setRegion11exp(newEXP);
                    updateRegion11EXP(tempUserCharacter);
                }
                else if (regionNum == 12) {
                    tempUserCharacter.setRegion12exp(newEXP);
                    updateNebulaEXP(tempUserCharacter);
                }
                else if (regionNum == 13) {
                    tempUserCharacter.setRegion13exp(newEXP);
                    updateRegion13EXP(tempUserCharacter);
                }
                else if (regionNum == 14) {
                    tempUserCharacter.setRegion14exp(newEXP);
                    updateIcecubeEXP(tempUserCharacter);
                }
                else if (regionNum == 16) {
                    tempUserCharacter.setRegion16exp(newEXP);
                    updateRupesEXP(tempUserCharacter);
                }
                else if (regionNum == 17) {
                    tempUserCharacter.setRegion17exp(newEXP);
                    updatePetraEXP(tempUserCharacter);
                }
                else if (regionNum == 18) {
                    tempUserCharacter.setRegion18exp(newEXP);
                    updateJuslynEXP(tempUserCharacter);
                }
                else if (regionNum == 19) {
                    tempUserCharacter.setRegion19exp(newEXP);
                    updateJuslynEXP(tempUserCharacter);
                }
                else if (regionNum == 20) {
                    tempUserCharacter.setRegion20exp(newEXP);
                    updateNorthEXP(tempUserCharacter);
                }
                else {
                    throw new RuntimeException("invalid region number of region "+region.getNom());
                }
            }

            public User_Decks getDeck(int index) {
                return lDecks.get(index);
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
            str2card cardConverter = new str2card();

            public userInventory() {
                allCards = new BlankDeck();
                allDecks = new ArrayList<Deck>();
                allWeapons = new ArrayList<Weapon>();
                allNonWeapons = new ArrayList<inventI>();
                allItems = new ArrayList<inventI>();
                allDecksNames = new ArrayList<String>();
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
                    for (User_Cards c : lCards) {
                        //if card is not the same as the previous OR null, create a new card instance
                        if (temp == null || !previous_card.equals(c.getName())) {
                            temp = cardConverter.getCard(c.getName());
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
                                    temp = cardConverter.getPrevious(previous_card);
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
                                        temp = cardConverter.getPrevious(previous_card);
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
                int amt = alphabetizelCards((ArrayList<User_Cards>)userDataChecker.getNameCards(card.toString())).get(0).getAmount();
                User_Cards toAdd = new User_Cards(card.toString(),amt,deck.getNom(),0);
                addUserCardToDeck(toAdd);
                deck.addCard(card);
            }

            public void addCard(Card card) {
                ArrayList<User_Cards> alphaUserCards = alphabetizelCards((ArrayList<User_Cards>)userDataChecker.getNameCards(card.toString()));
                User_Cards newCard = new User_Cards(card.toString(),alphaUserCards.get(0).getAmount()+1,"None",0);
                for (int n=0; n<alphaUserCards.size(); n++) {
                    alphaUserCards.get(n).setAmount(newCard.getAmount());
                    userDataChecker.updateAmount(alphaUserCards.get(n));
                }
                userDataChecker.addCard(newCard);
                allCards.addCard(card);
            }

            public void removeCardfromDeck(Deck deck, Card card) {
                ArrayList<User_Cards> alphaUserCards = alphabetizelCards((ArrayList<User_Cards>)userDataChecker.getNameCards(card.toString()));
                User_Cards toRemove = cardsOfDeck(deck.getNom(),alphaUserCards).get(0);
                //!!!new plan. for each card there will be a "None" card in the db...
                //im assuming fillDecks() will work with this...... you'll need to do an indv test later one to make sure!!!
                removeUserCardFromDeck(toRemove);
                deck.removeCard(card);
            }

            private void removeUserCardFromDeck(User_Cards card) {
                //userDataChecker.deleteCard(card);
                User_Decks userDeck = userDataChecker.getDeck((Collections.binarySearch(allDecksNames,card.getDeck())));
                userDataChecker.changeSize(userDeck,userDeck.getLength()-1);
            }

            private void addUserCardToDeck(User_Cards card) {
                User_Decks userDeck = userDataChecker.getDeck((Collections.binarySearch(allDecksNames,card.getDeck())));
                userDataChecker.changeSize(userDeck,userDeck.getLength()+1);
            }

            public void removeCard(Card card) {
                ArrayList<User_Cards> alphaUserCards = alphabetizelCards((ArrayList<User_Cards>)userDataChecker.getNameCards(card.toString()));
                User_Cards toRemove = cardsOfDeck("None", alphaUserCards).get(0);
                userDataChecker.deleteCard(toRemove);
                alphaUserCards.remove(toRemove);
                int amount = toRemove.getAmount()-1;
                String prev_deck="";
                for (int i = 0; i<alphaUserCards.size(); i++) {
                    //change the amt quantity in all of the cards
                    userDataChecker.changeAmt(alphaUserCards.get(i),amount);
                    //check that the amt in each deck doesn't exceed this new amount
                    if (!prev_deck.equals("") && !prev_deck.equals(alphaUserCards.get(i).getDeck())) {
                        //first, check the previous cards to make sure their deck_amt !> amount
                        int temp_deck_index = Collections.binarySearch(allDecksNames,prev_deck);
                        Deck temp_deck = allDecks.get(temp_deck_index);
                        if (temp_deck.getSudoCard(alphaUserCards.get(i).getName()).getAmount() > amount) {
                            //rmove card from the deck
                            removeUserCardFromDeck(toRemove);
                            temp_deck.removeLastCard(toRemove.getName());
                        }
                    }
                    else if(prev_deck.equals("")) {
                        prev_deck = alphaUserCards.get(i).getDeck();
                    }
                }

                allCards.removeCard(card);
            }

            public ArrayList<User_Cards> cardsOfDeck(String deckName, ArrayList<User_Cards> lCards) {
                ArrayList<User_Cards> cards = new ArrayList<User_Cards>();
                int location = binarySearchlCards(0,lCards.size()-1, deckName, lCards);
                if (location == -1) {
                    return null;
                }
                while (location != 0 && lCards.get(location-1).getDeck().compareTo(deckName) == 0) {
                    //the card before this is also the same card
                    location--;
                }
                while (location != lCards.size()-1 && lCards.get(location).toString().compareTo(deckName) == 0) {
                    cards.add(lCards.get(location));
                    location++;
                }
                return cards;
            }

            public ArrayList<Card> lCards2Cards(ArrayList<User_Cards> lCards) {
                Class tempClass = null;
                Card temp = null;
                ArrayList<Card> cards = new ArrayList<Card>();
                for (User_Cards c : lCards) {
                    temp = cardConverter.getCard(c.getName());
                    cards.add(temp);
                }
                return cards;
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

            //indb == if the deck is already in the db or not
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
                    userDataChecker.addDeck(new User_Decks(addedDeck.getNom(),"None",addedDeck.getCardAmt()));
                }
            }

            public void removeDeck(Deck removeDeck) {
                //all this does is update the deck instances of Cards
                //it doesnt remove the cards from the lists in the deck... hence why it is still used in the following:
                removeDeck.removeAll();
                //this will remove the deck card instances from the db...perhaps theres a better way to do this?
                userDataChecker.removeDeck(removeDeck.getNom());
                //removing deck from relative allDecks object
                allDecks.remove(removeDeck);
                //todo check if a character has this deck equipped... you'll need to modify that too if it's true, db and relative
            }

            private int binarySearchlCards(int startIndex, int endIndex, String deckName, ArrayList<User_Cards> lCards) {
                if (endIndex >= startIndex) {
                    int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
                    // If the element is present at the
                    // middle itself
                    if (lCards.get(mid).getDeck().compareTo(deckName) == 0)
                        return mid;

                    // If element is smaller than mid, then
                    // it can only be present in left subarray
                    if (lCards.get(mid).getDeck().compareTo(deckName) > 0)
                        return binarySearchlCards(startIndex, mid - 1, deckName, lCards);

                    // Else the element can only be present
                    // in right subarray
                    return binarySearchlCards(mid + 1, endIndex, deckName, lCards);
                }

                // We reach here when element is not present
                // in array
                return -1;
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

        dataController() {
            userDataChecker = new checkUserData();
            thisUserInventory = new userInventory();
        }

        public void setlValues(List<User_Values> vals) {userDataChecker.setlValues(vals);}
        public void setlCharacters(List<User_Characters> lCharacters) {
            userDataChecker.setlCharacters(lCharacters);
        }
        public void setlEQPlayed(List<User_EQPlayed> lEQPlayed) {userDataChecker.setlEQPlayed(lEQPlayed);}
        public void setlCards(List<User_Cards> vals) { userDataChecker.setlCards(vals); }
        public void setlDecks(List<User_Decks> vals) {userDataChecker.setlDecks(vals);}
        public void setlInventory(List<User_Inventory> vals) {userDataChecker.setlInventory(vals);}
        public void setDeckNameCards(List<User_Cards> vals) {userDataChecker.setDeckNameCards(vals);}

        public regions getCur_region() {return userDataChecker.getCur_region();}
        public Characters getCur_character() {return userDataChecker.getCur_character();}
        public String getCur_weapon() {return userDataChecker.getCur_weapon();}
        public int getPL() {return userDataChecker.getPL();}
        public void changeCharacter(Characters character) {
            userDataChecker.changeCharacter(character);
        }

        public void fillDecks() {userDataChecker.fillDecks();}
        //an existing card is added to a deck
        public void addCardtoDeck(Deck deck, Card card) {thisUserInventory.addCardtoDeck(deck,card);}
        //an existing card is removed from a deck, but continues to exist
        public void removeCardfromDeck(Deck deck, Card card) {thisUserInventory.removeCardfromDeck(deck,card);}
        //does not add to a deck, card instance is assumed to not exist
        public void addCard(Card card) {thisUserInventory.addCard(card);}
        //an existing card is removed from inventory entirely, including all decks
        public void removeCard(Card card) {thisUserInventory.removeCard(card);}
        //creating a new deck...it is assumed this does not already exist in the db
        public void addDeck(Deck deck) {thisUserInventory.addDeck(deck, false);}
        public void removeDeck(Deck deck) {thisUserInventory.removeDeck(deck);}

        //returns a BlankDeck...not a list
        public BlankDeck getAllCards() {return thisUserInventory.getAllCards();}
        public ArrayList<Deck> getAllDecks() {return thisUserInventory.getAllDecks();}

        public void setKatieLevel(int level) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.getKatieLevelExp());
            }
            else this.KatieLevel = level;
        }
        public void setVivLevel(int level) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.getVivLevelExp());
            }
            else this.VivLevel = level;
        }
        public void setDeltaLevel(int level) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.getDeltaLevelExp());
            }
            else this.DeltaLevel = level;
        }

    }
    dataController myDataController;


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

        myDataController = new dataController();

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);

        setObservers();

        //adding fragments
        FragmentTransaction ft = fragmentManager.beginTransaction();
        mainMenyuRegionMapBtn = main_menyu_region_map_btn.newInstance(myDataController.getCur_region().getNom(), pl);
        itemsBar = menyu_itemsbar.newInstance();
        Log.d("DEBUG",R.drawable.katie1+" "+((main_character)myDataController.getCur_character()).getCharacterImgDrawable());
        characterIcon = main_menyu_frontcharacter.newInstance(myDataController.getCur_character().getName(), myDataController.getCur_weapon(),myDataController.getPL());
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
                myDataController.setlValues(vals);
                if (updateUserValuesCounter==1) {
                    activity_whole.setBackground(getDrawable(myDataController.getCur_region().getDrawable_background()));
                    //filling region fragment list
                    //fillRegions_frag();
                }
            }
        });
        rpgViewModel.getlUserEQPlayed().observe(this,new Observer<List<User_EQPlayed>>() {
            @Override
            public void onChanged(@Nullable final List<User_EQPlayed> vals) {
                updateUserEQPlayedCounter++;
                myDataController.setlEQPlayed(vals);
                if (updateUserEQPlayedCounter==1) {
                    //init UI updates here
                }
            }
        });
        rpgViewModel.getlUserCharacters().observe(this,new Observer<List<User_Characters>>() {
            @Override
            public void onChanged(@Nullable final List<User_Characters> vals) {
                updateUserCharactersCounter++;
                myDataController.setlCharacters(vals);
                if (updateUserCharactersCounter==1) {
                    myDataController.setKatieLevel(0);
                    myDataController.setVivLevel(0);
                    myDataController.setDeltaLevel(0);
                }
            }
        });
        rpgViewModel.getlUserCards().observe(this,new Observer<List<User_Cards>>() {
            @Override
            public void onChanged(@Nullable final List<User_Cards> vals) {
                updateUserCardsCounter++;
                myDataController.setlCards(vals);
                if (updateUserCardsCounter==1) {
                    //init UI updates here
                    if(updateUserDecksCounter==1) {
                        myDataController.fillDecks();
                    }
                }
            }
        } );
        rpgViewModel.getlUserDecks().observe(this,new Observer<List<User_Decks>>() {
            @Override
            public void onChanged(@Nullable final List<User_Decks> vals) {
                updateUserDecksCounter++;
                myDataController.setlDecks(vals);
                if (updateUserDecksCounter==1) {
                    //init UI updates here
                    if(updateUserCardsCounter==1) {
                        myDataController.fillDecks();
                    }
                }
            }
        } );
        rpgViewModel.getlUserInventory().observe(this,new Observer<List<User_Inventory>>() {
            @Override
            public void onChanged(@Nullable final List<User_Inventory> vals) {
                updateUserInventoryCounter++;
                myDataController.setlInventory(vals);
                if (updateUserInventoryCounter==1) {
                    //init UI updates here
                }
            }
        } );
        rpgViewModel.getNameCards().observe(this,new Observer<List<User_Cards>>() {
            @Override
            public void onChanged(@Nullable final List<User_Cards> vals) {
                myDataController.setDeckNameCards(vals);
            }
        } );
    }

    //todo
    public void fillRegions_frag() {
        System.out.println("OUR CUR PL: "+myDataController.getPL());
        regions_frag[0] = region_1_fragment.newInstance(myDataController.getPL());
        //regions_frag[1] = region_2_fragment.newInstance(userDataChecker.getPL());
    }

    public void regionBtnPressed() {
        if (layoutSetter.compareWithCurrent(main_menyu_region_map_btn.getCURRENT_LAYOUT())) {
            boolean regions_match = myDataController.getCur_region().getNom().equals(mainMenyuRegionMapBtn.getRegion().getNom());
            if (regions_match) {
                //terminate the fragment
                System.out.println("Terminating RegionBtn");
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionFragment = region_1_fragment.newInstance(myDataController.getPL());
                //this assumes that all region fragments will have the same CURRENT_LAYOUT
                //BEWARE
                backBtn = dedicatedBackBtn.newInstance(pl,region_1_fragment.getCURRENT_LAYOUT());
                ft.add(R.id.dedicated_back_btn,backBtn);
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
                //previous was: (((RegionFragmentInterface)regionFragment).getCURRENT_LAYOUT());
                layoutSetter.changeLayout(region_1_fragment.getCURRENT_LAYOUT());//menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
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

    /*
    //regionBtns
    public void cityPtPressed(String city) {
        boolean regions_match = myDataController.getCur_region().getNom().equals(((RegionFragmentInterface)regionFragment).getRegion().getNom());
        if (regions_match) {
            //set up the cityPressed layout -- img, info, storeBtn, CH btn
            cityPt town;

            if (city.equals("Maleficere Mansion")) {
                town = this_pl.getCityPt("Maleficere Mansion",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout();
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
            }

            else if (city.equals("Chipper Towne")) {
                town = this_pl.getCityPt("Chipper Towne",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(myDataController.getCur_region().getNom(), town.getNom(),myDataController.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout("REGION_MAP_CITY");
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
            }
        }
        else {
            throw new RuntimeException("mismatch regions");
        }
    }
    */
    //todo change to chBtnPressed
    public void cityPtPressed(String city) {
        boolean regions_match = myDataController.getCur_region().getNom().equals(((RegionFragmentInterface)regionFragment).getRegion().getNom());
        if (regions_match) {

            cityPt town;

            if (city.equals("Maleficere Mansion")) {
                town = this_pl.getCityPt("Maleficere Mansion",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(myDataController.getCur_region().getNom(), town.getNom(),myDataController.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout(main_menyu_regionChapters_fragment.getCURRENT_LAYOUT());
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
            }

            else if (city.equals("Chipper Towne")) {
                town = this_pl.getCityPt("Chipper Towne",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(myDataController.getCur_region().getNom(), town.getNom(),myDataController.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout(main_menyu_regionChapters_fragment.getCURRENT_LAYOUT());
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
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
        backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
    }

    //regionChapterExtendedFragment
    public void onChapterExtendedPressed(Chapter chapter) {
        //lol this probably isnt needed? i dont think this is going to do anything... maybe open an entire window?
    }

    //todo this is dumb. why don't i just use the MM current layout? why do i have bckbtn storing its own?

    public void backBtnPressed(String layout) {
        //backbtn will return you to MAIN_MENYU_LAYOUT orrrr if the layout has an underscore, it will return you thatpage_layout
        //check if the layout is a subpage

        //check that the "real" current layout matches bckbtn's current layout
        if (layout.compareTo(layoutSetter.getCURRENT_LAYOUT()) != 0) {
            throw new RuntimeException("Back Button's layout "+layout+" does NOT match MM layout "+layoutSetter.getCURRENT_LAYOUT());
        }

        boolean isSubpage = layoutSetter.isSubpage(layout);
        if (!isSubpage) {
            //not a subpage, go to mainmenyu
            System.out.println("mm backpressed");
            clearFragments(layout);
            addFragments(layout);
            bufferbackgBottom.setAlpha(1);
            bufferbackgTop.setAlpha(1);
            bufferbackgLeft.setAlpha(1);
            bufferbackgRight.setAlpha(1);
            mmc_backbox.setBackgroundColor(getColor(R.color.genericElectric));
            layoutSetter.changeLayout("MAIN_MENYU_LAYOUT");
        }
        else {
            //is a subpage, go to proper layout.
            Log.d("ERROR","MUST INPUT THE INSTRUCTIONS FOR RETURNING TO THE PROPER SCREEN");
            String defaultPage = layoutSetter.getDefaultPage(layout);
            //characterView
            if (defaultPage.equals(characterViewFragment.getCURRENT_LAYOUT())) {

            }
            //deckView
            else if (defaultPage.equals(deckViewFragment.getCURRENT_LAYOUT())) {

            }
            /*
            //itemView
            else if (defaultPage.equals(itemViewFragment.getCURRENT_LAYOUT())) {

            }
            */
            //set the layout to the previous layout
            layoutSetter.changeLayout(layoutSetter.getPREVIOUS_LAYOUT());

        }
    }

    public void goBtnPressed() {

    }

    //ItemsBarBtns
    public void menyuItemsBarSettingsPressed() {
        //new activity
    }
    public void menyuItemsBarCharactersPressed() {

        Log.d("VIEW_CHANGE","Opening Character Viewer");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        charViewer = characterViewFragment.newInstance(pl,"Katherine",1,0);
        //charViewerBar = ;
        backBtn = dedicatedBackBtn.newInstance(pl,characterViewFragment.getCURRENT_LAYOUT());
        ft.add(R.id.dedicated_back_btn,backBtn);
        ft.add(R.id.whole_container_frag,charViewer);
        //ft.replace(R.id.itemsbar,deckViewerBar);
        ft.remove(mainMenyuRegionMapBtn);
        ft.addToBackStack(null);
        ft.commit();
        deployArrowsMMC(false);
        bufferbackgBottom.setAlpha(0);
        bufferbackgTop.setAlpha(0);
        bufferbackgLeft.setAlpha(0);
        bufferbackgRight.setAlpha(0);
        mmc_backbox.setBackground(null);
        layoutSetter.changeLayout(characterViewFragment.getCURRENT_LAYOUT());//menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter

    }
    public void menyuItemsBarPlotPressed() {
        //new activity
    }
    public void menyuItemsBarDecksPressed() {
        //bring in the charswitch arrows
        //remove the region btn
        //replace the itemsbar with a titlebar flush right (half the screen..)
        //add a backbtn
        //add deck scroller
        //make sure that the middle deck in the deck scroller is the equipped deck. In the case of all char, it can be the last viewed deck... but you'll never open to all characters
        Log.d("VIEW_CHANGE","Opening Deck Viewer");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        deckViewerRecycler = deckViewFragment.newInstance(myDataController.getPL(),myDataController.getAllDecks().get(0).getNom());
        deckViewerBar = deckViewer_deckBar.newInstance(pl,"falseString");
        backBtn = dedicatedBackBtn.newInstance(pl,deckViewFragment.getCURRENT_LAYOUT());
        ft.add(R.id.dedicated_back_btn,backBtn);
        ft.add(R.id.whole_container_frag,deckViewerRecycler);
        ft.replace(R.id.itemsbar,deckViewerBar);
        ft.remove(mainMenyuRegionMapBtn);
        ft.addToBackStack(null);
        ft.commit();
        deployArrowsMMC(true);
        bufferbackgBottom.setAlpha(0);
        bufferbackgTop.setAlpha(0);
        bufferbackgLeft.setAlpha(0);
        bufferbackgRight.setAlpha(0);
        mmc_backbox.setBackground(null);
        layoutSetter.changeLayout(deckViewFragment.getCURRENT_LAYOUT());//menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
        //intro mainmenyu2region (move region icons on)
    }
    public void menyuItemsBarInventoryPressed() {}
    public void menyuItemsBarMapPressed() {
        //okay i think we're opening a new activity... lets see how much this fucks up our data
        Intent intented = new Intent(MainMenyuActivity.this, wholeMapActivity.class);
        Bundle args = new Bundle();
        args.putString("CURRENT_REGION", myDataController.getCur_region().getNom());
        args.putInt("PlayerLevel",pl);
        intented.putExtras(args);
        startActivity(intented);
    }

    //CharacterViewer
    public void characterViewBarStatsPressed(String layout) {}
    public void characterViewBarEquipPressed(String layout) {}
    public void characterViewBarRegionPressed(String layout) {}
    public void characterViewBarRankPressed(String layout) {}
    public void characterViewBarInfoPressed(String layout) {}

    //DecksViewer
    public ArrayList<Deck> getAllDecks() {
        return myDataController.getAllDecks();
    }
    public void deckRecyclerDeckPressed(Deck deck) {
        Log.d("TESTING",deck.getNom()+" was pressed.");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (deckViewerBar != null) {
            Log.d("DEBUG","deckerView is not null");
            ft.remove(deckViewerBar);
        }
        deckViewerBar = deckViewer_deckBar.newInstance(pl,deck.getNom());
        deckViewerBar.importDeck(deck);
        ft.add(R.id.itemsbar,deckViewerBar);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void deckRecyclerAddDeckPressed() {
        Log.d("TESTING","Adding_deck was pressed.");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar);
        }
        //todo change this to be ADDDECKFRAGTHING.getCURRENT_LAYOUT();
        layoutSetter.changeLayout("DECK_VIEW_ADD_DECK");
        //prompt new deck's name
        //if input is blank, assume they dont want to make a new deck
    }
    public int getMMC_rightDist() {
        return characterIcon.getView().getRight();
    }
    public boolean getEmptyCharacter() {
        return characterIcon.getEmptyCharacter();
    }
    public void decksScrolled() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar);
        }
        ft.addToBackStack(null);
        ft.commit();
    }
    //DeckViewerBar
    public void deckViewerBar_ViewDeckPressed(Deck deck) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar);
        }
        reignArrowsMMC();
        ft.remove(characterIcon);
        ft.remove(deckViewerRecycler);
        cardViewFragment = linearCardViewFragment.newInstance(pl, false, deck.getNom());
        theQuickGrabDeck = deck;
        //cardViewFragment.importDeck(deck);
        ft.add(R.id.cardViewRecycler_container_frag,cardViewFragment);
        //create the cardViewButton fragment here
        //ft.add(R.id.cardViewButton_container_frag,cardViewbuttons);
        ft.addToBackStack(null);
        ft.commit();
    }
    public abstractDeck quickGrabDeck() {
        return theQuickGrabDeck;
    }
    public void deckViewerBar_showValidDeckPressed(Deck deck) {}
    public void deckViewerBar_DeleteDeckPressed(Deck deck) {
        //send a confirmation box...pause the lower screens?
    }
    //DeckViewCardView
    public void onListFragmentInteraction(sudoCard card) {}
    public void cardViewLargeBtnEditPressed(abstractDeck deck) {

    }

    //MMC_Character
    public void deployArrowsMMC(boolean hasEmpty) {
        //add the character change arrows
        MMCarrowUp = CharacterArrowFragment.newInstance(true,hasEmpty);
        MMCarrowDown = CharacterArrowFragment.newInstance(false,hasEmpty);
        characterIcon.deployArrowsMMC(MMCarrowUp, MMCarrowDown);
    }
    public void reignArrowsMMC() {
        //character = null
        if (characterIcon.getEmptyCharacter()) {
            Log.d("DECKTESTING","character was null, changing...");
            characterIcon.setPreviousCharacter();
        }
        Characters newCharacter = characterIcon.reignArrowsMMC(MMCarrowUp, MMCarrowDown);
        myDataController.changeCharacter(newCharacter);
    }
    public void characterArrowPressed(boolean isUp, boolean hasEmpty) {
        characterIcon.characterArrowPressed(isUp, hasEmpty);
    }

    private void clearFragments(String layout) {
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
            reignArrowsMMC();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(charViewer);
            ft.remove(backBtn);
            ft.addToBackStack(null);
            ft.commit();
        }
        else if(layoutSetter.compareParsed(layout,"DECK_VIEW_LAYOUT")) {
            reignArrowsMMC();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.remove(deckViewerRecycler);
            ft.remove(backBtn);
            ft.addToBackStack(null);
            ft.commit();
        }
        System.out.println("layouts cleared");
    }

    //adding fragments to the main menyu
    private void addFragments(String layout) {
        if (layoutSetter.compareParsed(layout,"REGION_MAP_LAYOUT")) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.menyu_mmc_frag,characterIcon);
            ft.add(R.id.itemsbar,itemsBar);
            ft.add(R.id.menyu_regionmap_btn_frag,mainMenyuRegionMapBtn);
            ft.addToBackStack(null);
            ft.commit();
        }
        else if (layoutSetter.compareParsed(layout,"CHARACTER_VIEW_LAYOUT")) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.itemsbar,itemsBar);
            ft.add(R.id.menyu_regionmap_btn_frag,mainMenyuRegionMapBtn);
            ft.addToBackStack(null);
            ft.commit();
        }
        else if(layoutSetter.compareParsed(layout,"DECK_VIEW_LAYOUT")) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.itemsbar,itemsBar);
            ft.add(R.id.menyu_regionmap_btn_frag,mainMenyuRegionMapBtn);
            ft.addToBackStack(null);
            ft.commit();
        }
        System.out.println("main_menyu_layout added");

    }

    //key activity things
    //
    @Override
    public void onBackPressed() {
        //do nothing
    }

}
