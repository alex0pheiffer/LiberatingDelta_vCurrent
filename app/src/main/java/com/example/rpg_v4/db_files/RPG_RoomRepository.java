package com.example.rpg_v4.db_files;


import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RPG_RoomRepository {

    private User_Values_Dao userValuesDao;
    private User_Characters_Dao userCharactersDao;
    private User_Inventory_Dao userInventoryDao;
    private User_Cards_Dao userCardsDao;
    private User_Decks_Dao userDecksDao;
    private User_EQPlayed_Dao userEQPlayedDao;
    private LiveData<List<User_Values>> lUserValues;
    private LiveData<List<User_Characters>> lUserCharacters;
    private LiveData<List<User_Inventory>> lUserInventory;
    private LiveData<List<User_Cards>> lUserCards;
    private LiveData<List<User_Decks>> lUserDecks;
    private LiveData<List<User_EQPlayed>> lUserEQPlayed;
    private MutableLiveData<List<User_Cards>> cardNameSearchResults = new MutableLiveData<>();

    public RPG_RoomRepository(Application application) {
        RPG_RoomDatabase db = RPG_RoomDatabase.getDatabase(application);
        userValuesDao = db.UserValuesDao();
        userCharactersDao = db.UserCharactersDao();
        userInventoryDao = db.UserInventoryDao();
        userCardsDao = db.UserCardsDao();
        userDecksDao = db.UserDecksDao();
        userEQPlayedDao = db.UserEQPlayedDao();
        lUserValues = userValuesDao.getAlphabetizedUser_Values();
        System.out.println("inside repostiory: "+lUserValues);
        lUserCharacters = userCharactersDao.getAll();
        lUserCards = userCardsDao.getAll();
        lUserDecks = userDecksDao.getAll();
        lUserInventory = userInventoryDao.getAll();
        lUserEQPlayed = userEQPlayedDao.getAll();
    }

    //getter methods for the full lists
    public LiveData<List<User_Characters>> getlUserCharacters() {
        return lUserCharacters;
    }
    public LiveData<List<User_Inventory>> getlUserInventory() {
        return lUserInventory;
    }
    public LiveData<List<User_Values>> getlUserValues() {
        return lUserValues;
    }
    public LiveData<List<User_Cards>> getlUserCards() {
        return lUserCards;
    }
    public LiveData<List<User_Decks>> getlUserDecks() {
        return lUserDecks;
    }
    public LiveData<List<User_EQPlayed>> getlUserEQPlayed() {
        return lUserEQPlayed;
    }
    public LiveData<List<User_Cards>> getCardNameSearchResults() {return cardNameSearchResults;}

    //add wrapper for insert()
    //MUST BE CALLED ON A NON-UI thread
    public void insert (User_Values userValues) {
        new insertAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void insert(User_Characters userCharacters) {
        new insertAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void insert(User_Cards userCards) {
        new insertAsyncTaskCards(userCardsDao).execute(userCards);
    }
    public void insert(User_Decks userDecks) {
        new insertAsyncTaskDecks(userDecksDao).execute(userDecks);
    }
    public void insert(User_EQPlayed userEQPlayed) {
        new insertAsyncTaskEQPlayed(userEQPlayedDao).execute(userEQPlayed);
    }
    public void insert(User_Inventory userInventory) {
        new insertAsyncTaskInventory(userInventoryDao).execute(userInventory);
    }
    public void updateFrontChar(User_Values userValues) {
        new updateFrontCharAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updateRegion(User_Values userValues) {
        new updateRegionAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updateOkane(User_Values userValues) {
        new updateOkaneAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updatePassword(User_Values userValues) {
        new updatePasswordAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updateUsername(User_Values userValues) {
        new updateUsernameAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updatePhase(User_Values userValues) {
        new updatePhaseAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updatePL(User_Values userValues) {
        new updatePLAsyncTaskValues(userValuesDao).execute(userValues);
    }
    public void updateLabel(User_Characters userCharacters) {
        new updateLabelAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateLevel(User_Characters userCharacters) {
        new updateLevelAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRank(User_Characters userCharacters) {
        new updateRankAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateWeapon(User_Characters userCharacters) {
        new updateWeaponAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateDeck(User_Characters userCharacters) {
        new updateDeckAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateItem(User_Characters userCharacters) {
        new updateItemAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion1exp(User_Characters userCharacters) {
        new updateRegion1expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion23exp(User_Characters userCharacters) {
        new updateRegion23expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion4exp(User_Characters userCharacters) {
        new updateRegion4expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion5exp(User_Characters userCharacters) {
        new updateRegion5expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion6exp(User_Characters userCharacters) {
        new updateRegion6expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion7exp(User_Characters userCharacters) {
        new updateRegion7expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion89exp(User_Characters userCharacters) {
        new updateRegion89expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion10exp(User_Characters userCharacters) {
        new updateRegion10expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion11exp(User_Characters userCharacters) {
        new updateRegion11expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion12exp(User_Characters userCharacters) {
        new updateRegion12expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion13exp(User_Characters userCharacters) {
        new updateRegion13expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion14exp(User_Characters userCharacters) {
        new updateRegion14expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion16exp(User_Characters userCharacters) {
        new updateRegion16expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion17exp(User_Characters userCharacters) {
        new updateRegion17expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion18exp(User_Characters userCharacters) {
        new updateRegion18expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion19exp(User_Characters userCharacters) {
        new updateRegion19expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateRegion20exp(User_Characters userCharacters) {
        new updateRegion20expAsyncTaskCharacters(userCharactersDao).execute(userCharacters);
    }
    public void updateAmount(User_Cards userCards) {
        new updateAmountAsyncTaskCards(userCardsDao).execute(userCards);
    }
    public void updatePosition(User_Cards userCards) {
        new updatePositionAsyncTaskCards(userCardsDao).execute(userCards);
    }
    public void updateLabel(User_Decks userDecks) {
        new updateLabelAsyncTaskDecks(userDecksDao).execute(userDecks);
    }
    public void updateChar(User_Decks userDecks) {
        new updateCharAsyncTaskDecks(userDecksDao).execute(userDecks);
    }
    public void updateLen(User_Decks userDecks) {
        new updateLenAsyncTaskDecks(userDecksDao).execute(userDecks);
    }
    public void updateCompleted(User_EQPlayed userEqPlayed) {
        new updateCompletedAsyncTaskEQPlayed(userEQPlayedDao).execute(userEqPlayed);
    }
    public void updateSigItem(User_EQPlayed userEqPlayed) {
        new updateSigItemAsyncTaskEQPlayed(userEQPlayedDao).execute(userEqPlayed);
    }
    public void updateAmount(User_Inventory userInventory) {
        new updateAmountAsyncTaskInventory(userInventoryDao).execute(userInventory);
    }
    public void deleteCard(User_Cards userCards) {
        new deleteAsyncTaskCards(userCardsDao).execute(userCards);
    }
    public void deleteDeck(User_Decks userDecks) {
        new deleteAsyncTaskDecks(userDecksDao).execute(userDecks);
    }
    public void deleteInventory(User_Inventory userInventory) {
        new deleteAsyncTaskInventory(userInventoryDao).execute(userInventory);
    }
    public void deleteEQPlayed(User_EQPlayed userEQPlayed) {
        new deleteAsyncTaskEQPlayed(userEQPlayedDao).execute(userEQPlayed);
    }
    //returns a list of Cards with this card name
    public void findNameCards(String name) {
        new getCardNameAsyncTaskCard(userCardsDao).execute(name);
    }
    //insertAsyncTask classes
    private static class insertAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        insertAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        insertAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskCards extends AsyncTask<User_Cards, Void, Void> {

        private User_Cards_Dao mAsyncTaskDao;

        insertAsyncTaskCards(User_Cards_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Cards... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskDecks extends AsyncTask<User_Decks, Void, Void> {

        private User_Decks_Dao mAsyncTaskDao;

        insertAsyncTaskDecks(User_Decks_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Decks... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskEQPlayed extends AsyncTask<User_EQPlayed, Void, Void> {

        private User_EQPlayed_Dao mAsyncTaskDao;

        insertAsyncTaskEQPlayed(User_EQPlayed_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_EQPlayed... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTaskInventory extends AsyncTask<User_Inventory, Void, Void> {

        private User_Inventory_Dao mAsyncTaskDao;

        insertAsyncTaskInventory(User_Inventory_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Inventory... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //deleteAsyncTask classes
    private static class deleteAsyncTaskInventory extends AsyncTask<User_Inventory, Void, Void> {

        private User_Inventory_Dao mAsyncTaskDao;

        deleteAsyncTaskInventory(User_Inventory_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Inventory... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTaskCards extends AsyncTask<User_Cards, Void, Void> {

        private User_Cards_Dao mAsyncTaskDao;

        deleteAsyncTaskCards(User_Cards_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Cards... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTaskDecks extends AsyncTask<User_Decks, Void, Void> {

        private User_Decks_Dao mAsyncTaskDao;

        deleteAsyncTaskDecks(User_Decks_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Decks... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTaskEQPlayed extends AsyncTask<User_EQPlayed, Void, Void> {

        private User_EQPlayed_Dao mAsyncTaskDao;

        deleteAsyncTaskEQPlayed(User_EQPlayed_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_EQPlayed... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    //UPDATE VALUES:
    //
    private static class updateFrontCharAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updateFrontCharAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updateFrontChar(params[0].getFront_char(),params[0].getId());
            return null;
        }
    }
    private static class updateRegionAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updateRegionAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updateRegion(params[0].getCur_region(),params[0].getId());
            return null;
        }
    }
    //note:if you want to update the login, you must set the username, and then the password, using the new username as the key
    private static class updateUsernameAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updateUsernameAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updateUsername(params[0].getUsername(),params[0].getId());
            return null;
        }
    }
    private static class updatePasswordAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updatePasswordAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updatePassword(params[0].getPassword(),params[0].getId());
            return null;
        }
    }
    private static class updateOkaneAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updateOkaneAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updateOkane(params[0].getCur_okane(),params[0].getId());
            return null;
        }
    }
    private static class updatePhaseAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updatePhaseAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updatePhase(params[0].getCur_phase(),params[0].getId());
            return null;
        }
    }
    private static class updatePLAsyncTaskValues extends AsyncTask<User_Values, Void, Void> {

        private User_Values_Dao mAsyncTaskDao;

        updatePLAsyncTaskValues(User_Values_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Values... params) {
            mAsyncTaskDao.updatePL(params[0].getCur_PL(),params[0].getId());
            return null;
        }
    }

    //UPDATE CHARACTERS:
    //
    private static class updateLabelAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateLabelAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateLabel(params[0].getName(),params[0].getId());
            return null;
        }
    }
    private static class updateLevelAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateLevelAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateLevel(params[0].getLevel(),params[0].getId());
            return null;
        }
    }
    private static class updateRankAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRankAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRank(params[0].getRank(),params[0].getId());
            return null;
        }
    }
    private static class updateWeaponAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateWeaponAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateWeapon(params[0].getWeapon_equip(),params[0].getId());
            return null;
        }
    }
    private static class updateDeckAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateDeckAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateDeck(params[0].getDeck_equip(),params[0].getId());
            return null;
        }
    }
    private static class updateItemAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateItemAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateItem(params[0].getItem_equip(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion1expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion1expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion1exp(params[0].getRegion1exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion23expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion23expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion23exp(params[0].getRegion23exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion4expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion4expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion4exp(params[0].getRegion4exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion5expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion5expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion5exp(params[0].getRegion5exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion6expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion6expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion6exp(params[0].getRegion6exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion7expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion7expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion7exp(params[0].getRegion7exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion89expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion89expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion89exp(params[0].getRegion89exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion10expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion10expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion10exp(params[0].getRegion10exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion11expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion11expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion11exp(params[0].getRegion11exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion12expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion12expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion12exp(params[0].getRegion12exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion13expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion13expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion13exp(params[0].getRegion13exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion14expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion14expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion14exp(params[0].getRegion14exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion16expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion16expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion16exp(params[0].getRegion16exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion17expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion17expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion17exp(params[0].getRegion17exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion18expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion18expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion18exp(params[0].getRegion18exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion19expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion19expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion19exp(params[0].getRegion19exp(),params[0].getId());
            return null;
        }
    }
    private static class updateRegion20expAsyncTaskCharacters extends AsyncTask<User_Characters, Void, Void> {

        private User_Characters_Dao mAsyncTaskDao;

        updateRegion20expAsyncTaskCharacters(User_Characters_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Characters... params) {
            mAsyncTaskDao.updateRegion20exp(params[0].getRegion20exp(),params[0].getId());
            return null;
        }
    }

    //UPDATE CARDS:
    //
    private static class updateAmountAsyncTaskCards extends AsyncTask<User_Cards, Void, Void> {

        private User_Cards_Dao mAsyncTaskDao;

        updateAmountAsyncTaskCards(User_Cards_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Cards... params) {
            mAsyncTaskDao.updateAmount(params[0].getAmount(),params[0].getId());
            return null;
        }
    }
    private static class updatePositionAsyncTaskCards extends AsyncTask<User_Cards, Void, Void> {

        private User_Cards_Dao mAsyncTaskDao;

        updatePositionAsyncTaskCards(User_Cards_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Cards... params) {
            mAsyncTaskDao.updatePosition(params[0].getPosition(),params[0].getId());
            return null;
        }
    }

    //UPDATE DECK:
    //
    private static class updateLabelAsyncTaskDecks extends AsyncTask<User_Decks, Void, Void> {

        private User_Decks_Dao mAsyncTaskDao;

        updateLabelAsyncTaskDecks(User_Decks_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Decks... params) {
            mAsyncTaskDao.updateLabel(params[0].getName(),params[0].getId());
            return null;
        }
    }
    private static class updateCharAsyncTaskDecks extends AsyncTask<User_Decks, Void, Void> {

        private User_Decks_Dao mAsyncTaskDao;

        updateCharAsyncTaskDecks(User_Decks_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Decks... params) {
            mAsyncTaskDao.updateChar(params[0].getChar_equip(),params[0].getId());
            return null;
        }
    }
    private static class updateLenAsyncTaskDecks extends AsyncTask<User_Decks, Void, Void> {

        private User_Decks_Dao mAsyncTaskDao;

        updateLenAsyncTaskDecks(User_Decks_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Decks... params) {
            mAsyncTaskDao.updateLen(params[0].getLength(),params[0].getId());
            return null;
        }
    }

    //UPDATE EQPLAYED:
    //
    private static class updateCompletedAsyncTaskEQPlayed extends AsyncTask<User_EQPlayed, Void, Void> {

        private User_EQPlayed_Dao mAsyncTaskDao;

        updateCompletedAsyncTaskEQPlayed(User_EQPlayed_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_EQPlayed... params) {
            mAsyncTaskDao.updateComp(params[0].getCompleted(),params[0].getId());
            return null;
        }
    }
    private static class updateSigItemAsyncTaskEQPlayed extends AsyncTask<User_EQPlayed, Void, Void> {

        private User_EQPlayed_Dao mAsyncTaskDao;

        updateSigItemAsyncTaskEQPlayed(User_EQPlayed_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_EQPlayed... params) {
            mAsyncTaskDao.updateSigItem(params[0].getSig_item_collected(),params[0].getId());
            return null;
        }
    }

    //UPDATE INVENTORY:
    //
    private static class updateAmountAsyncTaskInventory extends AsyncTask<User_Inventory, Void, Void> {

        private User_Inventory_Dao mAsyncTaskDao;

        updateAmountAsyncTaskInventory(User_Inventory_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User_Inventory... params) {
            mAsyncTaskDao.updateAmount(params[0].getAmount(),params[0].getId());
            return null;
        }
    }

    //FIND CARDS:
    //
    private static class getCardNameAsyncTaskCard extends AsyncTask<String, Void, List<User_Cards>> {
        private User_Cards_Dao mAsyncTaskDao;
        private RPG_RoomRepository delegate = null;

        getCardNameAsyncTaskCard(User_Cards_Dao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<User_Cards> doInBackground(final String... params) {
            return mAsyncTaskDao.getCardName(params[0]).getValue();
        }

        @Override
        protected void onPostExecute(List<User_Cards> result) {
            delegate.getNameAsyncTaskCardFinished(result);
        }
    }
    private void getNameAsyncTaskCardFinished(List<User_Cards> results) {
        cardNameSearchResults.setValue(results);
    }
}
