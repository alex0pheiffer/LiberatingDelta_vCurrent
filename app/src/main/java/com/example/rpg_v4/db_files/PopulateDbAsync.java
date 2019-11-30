package com.example.rpg_v4.db_files;

import android.os.AsyncTask;

public class PopulateDbAsync extends AsyncTask<Void,Void,Void> {

    private final User_Values_Dao userValues;
    private final User_Characters_Dao userCharacters;
    private final User_Cards_Dao userCards;
    private final User_Decks_Dao userDecks;
    private final User_EQPlayed_Dao userEQPlayed;
    private final User_Inventory_Dao userInventory;

    PopulateDbAsync(RPG_RoomDatabase db) {
        userValues = db.UserValuesDao();
        userCharacters = db.UserCharactersDao();
        userCards = db.UserCardsDao();
        userDecks = db.UserDecksDao();
        userEQPlayed = db.UserEQPlayedDao();
        userInventory = db.UserInventoryDao();
    }

    private void removeAll() {
        userValues.deleteAll();
        userCharacters.deleteAll();
        userCards.deleteAll();
        userDecks.deleteAll();
        userEQPlayed.deleteAll();
        userInventory.deleteAll();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        this.removeAll();
        User_Values useval = new User_Values(1, 0, 0,"Katie","none","none","Veneland");
        userValues.insert(useval);
        User_Characters usechar = new User_Characters("Katie", 0,0,"SimpleStaff", "BasicDeck","PotionA");
        userCharacters.insert(usechar);
        usechar = new User_Characters("Delta",0,0,"SimpleSword","none","none");
        userCharacters.insert(usechar);
        usechar = new User_Characters("Viv",0,0,"SimpleBow","none","none");
        userCharacters.insert(usechar);
        User_Cards usecard = new User_Cards("RockToss",3,"BasicDeck",0);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"BasicDeck",1);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"BasicDeck",2);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",3);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",4);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",5);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",6);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",7);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",8);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",9);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",10);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",11);
        userCards.insert(usecard);
        usecard = new User_Cards("DiveLeft",3,"BasicDeck",12);
        userCards.insert(usecard);
        usecard = new User_Cards("DiveLeft",3,"BasicDeck",13);
        userCards.insert(usecard);
        usecard = new User_Cards("DiveLeft",3,"BasicDeck",14);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",15);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",16);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",17);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",18);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",19);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",20);
        userCards.insert(usecard);
        User_Decks usedeck = new User_Decks("BasicDeck","Thalass",21);
        userDecks.insert(usedeck);
        User_EQPlayed useEQP = new User_EQPlayed("ChallengeA",0,0);
        userEQPlayed.insert(useEQP);
        User_Inventory useinv = new User_Inventory("SimpleStaff","weapon",1);
        userInventory.insert(useinv);
        useinv = new User_Inventory("SimpleSword","weapon",1);
        userInventory.insert(useinv);
        useinv = new User_Inventory("SimpleBow","weapon",1);
        userInventory.insert(useinv);
        useinv = new User_Inventory("BasicDeck","deck",1);
        userInventory.insert(useinv);
        useinv = new User_Inventory("PotionA","consumable",5);
        userInventory.insert(useinv);
        return null;
    }
}