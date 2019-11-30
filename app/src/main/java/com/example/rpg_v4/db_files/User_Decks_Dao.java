package com.example.rpg_v4.db_files;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface User_Decks_Dao {

    //The DAO associated with the User_Decks_Table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_Decks userDecks);

    @Delete
    void delete(User_Decks userDecks);

    //to update a field with a matching deck
    @Update
    void update(User_Decks userDecks);

    //update LABEL by id
    @Query("UPDATE User_Decks_Table SET LABEL=:name WHERE id=:index")
    void updateLabel(String name, int index);

    //update CHAR by id
    @Query("UPDATE User_Decks_Table SET CHAR=:char_equip WHERE id=:index")
    void updateChar(String char_equip, int index);

    //update LEN by id
    @Query("UPDATE User_Decks_Table SET LEN=:length WHERE id=:index")
    void updateLen(int length, int index);

    //delete the entire table
    @Query("DELETE FROM User_Decks_Table")
    void deleteAll();

    //return list of UserDecks
    @Query("SELECT * FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<User_Decks>> getAll();

    /*
    //return inventory name by id
    @Query("SELECT LABEL FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //return the amt of uses left
    @Query("SELECT LEN FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<Integer>> getLens();

    //returns the characters equipping that deck
    @Query("SELECT CHAR FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<String>> getChars();
     */
}
