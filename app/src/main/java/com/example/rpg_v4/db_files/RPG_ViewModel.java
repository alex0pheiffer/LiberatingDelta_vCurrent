package com.example.rpg_v4.db_files;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RPG_ViewModel extends AndroidViewModel {

    private RPG_RoomRepository repository;
    private LiveData<List<User_Values>> lUserValues;
    private LiveData<List<User_Characters>> lUserCharacters;
    private LiveData<List<User_Inventory>> lUserInventory;
    private LiveData<List<User_Cards>> lUserCards;
    private LiveData<List<User_Decks>> lUserDecks;
    private LiveData<List<User_EQPlayed>> lUserEQPlayed;
    private LiveData<List<User_Cards>> cardNameSearchResults;

    public RPG_ViewModel(Application application) {
        super(application);
        repository = new RPG_RoomRepository(application);
        lUserValues = repository.getlUserValues();
        System.out.println("inside viewModel: "+lUserValues);
        lUserCharacters = repository.getlUserCharacters();
        lUserDecks = repository.getlUserDecks();
        lUserCards = repository.getlUserCards();
        lUserInventory = repository.getlUserInventory();
        lUserEQPlayed = repository.getlUserEQPlayed();
        cardNameSearchResults = repository.getCardNameSearchResults();
    }

    public LiveData<List<User_EQPlayed>> getlUserEQPlayed() {
        return lUserEQPlayed;
    }
    public LiveData<List<User_Decks>> getlUserDecks() {
        return lUserDecks;
    }
    public LiveData<List<User_Cards>> getlUserCards() {
        return lUserCards;
    }
    public LiveData<List<User_Values>> getlUserValues() {
        return lUserValues;
    }
    public LiveData<List<User_Inventory>> getlUserInventory() {
        return lUserInventory;
    }
    public LiveData<List<User_Characters>> getlUserCharacters() {
        return lUserCharacters;
    }
    public LiveData<List<User_Cards>> getNameCards() {return cardNameSearchResults;}

    //wrapper for repository's insert method so the insert is completely hidden from the UI
    public void insert(User_Values userValues) {
        repository.insert(userValues);
    }
    public void insert(User_Inventory userInventory) {
        repository.insert(userInventory);
    }
    public void insert(User_Decks userDecks) {
        repository.insert(userDecks);
    }
    public void insert(User_EQPlayed userEQPlayed) {
        repository.insert(userEQPlayed);
    }
    public void insert(User_Characters userCharacters) {
        repository.insert(userCharacters);
    }
    public void insert(User_Cards userCards) {
        repository.insert(userCards);
    }


    public void deleteCard(User_Cards userCards) {
        repository.deleteCard(userCards);
    }
    public void deleteDeck(User_Decks userDecks) {
        repository.deleteDeck(userDecks);
    }
    public void deleteInventory(User_Inventory userInventory) {
        repository.deleteInventory(userInventory);
    }
    public void deleteEQPlayed(User_EQPlayed userEQPlayed) {
        repository.deleteEQPlayed(userEQPlayed);
    }

    public void updateFrontChar(User_Values userValues) {
        repository.updateFrontChar(userValues);
    }
    public void updateOkane(User_Values userValues) {
        repository.updateOkane(userValues);
    }
    public void updatePassword(User_Values userValues) {
        repository.updatePassword(userValues);
    }
    public void updateUsername(User_Values userValues) {
        repository.updateUsername(userValues);
    }
    public void updatePhase(User_Values userValues) {
        repository.updatePhase(userValues);
    }
    public void updatePL(User_Values userValues) {
        repository.updatePL(userValues);
    }
    public void updateRegion(User_Values userValues) {
        repository.updateRegion(userValues);
    }
    public void updateLabel(User_Characters userCharacters) {
        repository.updateLabel(userCharacters);
    }
    public void updateLevel(User_Characters userCharacters) {
        repository.updateLevel(userCharacters);
    }
    public void updateRank(User_Characters userCharacters) {
        repository.updateRank(userCharacters);
    }
    public void updateWeapon(User_Characters userCharacters) {
        repository.updateWeapon(userCharacters);
    }
    public void updateDeck(User_Characters userCharacters) {
        repository.updateDeck(userCharacters);
    }
    public void updateItem(User_Characters userCharacters) {
        repository.updateItem(userCharacters);
    }
    public void updateRegion1exp(User_Characters userCharacters) {
        repository.updateRegion1exp(userCharacters);
    }
    public void updateRegion23exp(User_Characters userCharacters) {
        repository.updateRegion23exp(userCharacters);
    }
    public void updateRegion4exp(User_Characters userCharacters) {
        repository.updateRegion4exp(userCharacters);
    }
    public void updateRegion5exp(User_Characters userCharacters) {
        repository.updateRegion5exp(userCharacters);
    }
    public void updateRegion6exp(User_Characters userCharacters) {
        repository.updateRegion6exp(userCharacters);
    }
    public void updateRegion7exp(User_Characters userCharacters) {
        repository.updateRegion7exp(userCharacters);
    }
    public void updateRegion89exp(User_Characters userCharacters) {
        repository.updateRegion89exp(userCharacters);
    }
    public void updateRegion10exp(User_Characters userCharacters) {
        repository.updateRegion10exp(userCharacters);
    }
    public void updateRegion11exp(User_Characters userCharacters) {
        repository.updateRegion11exp(userCharacters);
    }
    public void updateRegion12exp(User_Characters userCharacters) {
        repository.updateRegion12exp(userCharacters);
    }
    public void updateRegion13exp(User_Characters userCharacters) {
        repository.updateRegion13exp(userCharacters);
    }
    public void updateRegion14exp(User_Characters userCharacters) {
        repository.updateRegion14exp(userCharacters);
    }
    public void updateRegion16exp(User_Characters userCharacters) {
        repository.updateRegion16exp(userCharacters);
    }
    public void updateRegion17exp(User_Characters userCharacters) {
        repository.updateRegion17exp(userCharacters);
    }
    public void updateRegion18exp(User_Characters userCharacters) {
        repository.updateRegion18exp(userCharacters);
    }
    public void updateRegion19exp(User_Characters userCharacters) {
        repository.updateRegion19exp(userCharacters);
    }
    public void updateRegion20exp(User_Characters userCharacters) {
        repository.updateRegion20exp(userCharacters);
    }

    public void updateAmount(User_Cards userCards) {
        repository.updateAmount(userCards);
    }
    public void updatePosition(User_Cards userCards) {
        repository.updatePosition(userCards);
    }
    public void updateLabel(User_Decks userDecks) {
        repository.updateLabel(userDecks);
    }
    public void updateChar(User_Decks userDecks) {
        repository.updateChar(userDecks);
    }
    public void updateLen(User_Decks userDecks) {
        repository.updateLen(userDecks);
    }
    public void updateCompleted(User_EQPlayed userEqPlayed) {
        repository.updateCompleted(userEqPlayed);
    }
    public void updateSigItem(User_EQPlayed userEqPlayed) {
        repository.updateSigItem(userEqPlayed);
    }
    public void updateAmount(User_Inventory userInventory) {
        repository.updateAmount(userInventory);
    }
    public void findNameCards(String string) {
        repository.findNameCards(string);
    }
}
