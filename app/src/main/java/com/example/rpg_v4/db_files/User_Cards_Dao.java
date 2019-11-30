package com.example.rpg_v4.db_files;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface User_Cards_Dao {

    //The DAO associated with the User_Cards table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_Cards userCards);

    //to update a field with a matching user
    @Delete
    void delete(User_Cards userCards);

    //delete the entire table
    @Query("DELETE FROM User_Cards_Table")
    void deleteAll();

    //returns a list of User_Cards
    @Query("SELECT * FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<User_Cards>> getAll();

    //updating the AMT by order id
    @Query("UPDATE User_Cards_Table SET AMT=:amount WHERE id =:index")
    void updateAmount(int amount, int index);

    //updating the POS by order id
    //please note if you want to change decks, create a new card entity
    @Query("UPDATE User_Cards_Table SET POS=:position WHERE id=:index")
    void updatePosition(int position, int index);

    /*
    //returns a whole list of LABELs (the name of the card)
    @Query("SELECT LABEL FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //returns a whole list of AMTs (how many of this card is in the user's inventory)
    @Query("SELECT AMT FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<Integer>> getAmts();

    //returns a whole list of DECKs
    @Query("SELECT DECK FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<String>> getDecks();

    //returns a whole list of POSs (Position of the card in the deck)
    @Query("SELECT POS FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<Integer>> getPoss();
    */


}
