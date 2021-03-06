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
        User_Values useval = new User_Values(1, 0, 0,"Katherine","none","none","Veneland");
        userValues.insert(useval);
        //it's very important that the order of the characters is KATIE, DELTA, VIVIAN
        User_Characters usechar = new User_Characters("Katherine", 0,0,"SimpleStaff", "BasicDeck","PotionA",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        userCharacters.insert(usechar);
        usechar = new User_Characters("Delta",0,0,"SimpleSword","none","none",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        userCharacters.insert(usechar);
        usechar = new User_Characters("Vivian",0,0,"SimpleBow","none","none",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        userCharacters.insert(usechar);
        User_Cards usecard = new User_Cards("RockToss",3,"BasicDeck",0);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"BasicDeck",1);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"BasicDeck",2);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"None",0);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"None",1);
        userCards.insert(usecard);
        usecard = new User_Cards("RockToss",3,"None",2);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",3);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",4);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"BasicDeck",5);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"None",3);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"None",4);
        userCards.insert(usecard);
        usecard = new User_Cards("Shove",3,"None",5);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",6);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",7);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"BasicDeck",8);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"None",6);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"None",7);
        userCards.insert(usecard);
        usecard = new User_Cards("Distract",3,"None",8);
        userCards.insert(usecard);
        /*
        usecard = new User_Cards("TreeHide",3,"BasicDeck",9);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",10);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",11);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",9);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",10);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",11);
        userCards.insert(usecard);
        */
        usecard = new User_Cards("Scare",3,"BasicDeck",9);
        userCards.insert(usecard);
        usecard = new User_Cards("Scare",3,"BasicDeck",10);
        userCards.insert(usecard);
        usecard = new User_Cards("Scare",3,"BasicDeck",11);
        userCards.insert(usecard);
        usecard = new User_Cards("Scare",3,"None",9);
        userCards.insert(usecard);
        usecard = new User_Cards("Scare",3,"None",10);
        userCards.insert(usecard);
        usecard = new User_Cards("Scare",3,"None",11);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"BasicDeck",12);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"BasicDeck",13);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"BasicDeck",14);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"None",12);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"None",13);
        userCards.insert(usecard);
        usecard = new User_Cards("SimpleDodge",3,"None",14);
        userCards.insert(usecard);
        /*
        usecard = new User_Cards("Splash",3,"BasicDeck",15);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",16);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",17);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",15);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",16);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",17);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",18);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",19);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",20);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",18);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",19);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",20);
        userCards.insert(usecard);
        */
        User_Decks usedeck = new User_Decks("BasicDeck","Katherine",15);
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