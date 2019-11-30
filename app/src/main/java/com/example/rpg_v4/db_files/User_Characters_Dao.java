package com.example.rpg_v4.db_files;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface User_Characters_Dao {
    //The DAO associated with the User_Characters_Table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_Characters userCharacters);

    //to update a field with a matching user
    @Update
    void update(User_Characters userCharacters);

    //update LABEL by id
    @Query("UPDATE User_Characters_Table SET LABEL=:name WHERE id=:index")
    void updateLabel(String name, int index);

    //update LVL and EXP by id
    @Query("UPDATE User_Characters_Table SET LVL=:level, EXP=:exp2next WHERE id=:index")
    void updateExp(int level, int exp2next, int index);

    //update WEAPON by id
    @Query("UPDATE User_Characters_Table SET WEAPON=:weapon_equip WHERE id=:index")
    void updateWeapon(String weapon_equip, int index);

    //update DECK by id
    @Query("UPDATE User_Characters_Table SET DECK=:deck_equip WHERE id=:index")
    void updateDeck(String deck_equip, int index);

    //update ITEM by id
    @Query("UPDATE User_Characters_Table SET ITEM=:item_equip WHERE id=:index")
    void updateItem(String item_equip, int index);

    //delete the entire table
    @Query("DELETE FROM User_Characters_Table")
    void deleteAll();

    //return the list USer_Characters
    @Query("SELECT * FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<User_Characters>> getAll();

    /*
    //return the list of all LABELs
    @Query("SELECT LABEL FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //returns a whole list of LEVELs
    @Query("SELECT LVL FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<Integer>> getLvls();

    //returns a whole list of EXPs (% comp after that level)
    @Query("SELECT EXP FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<Integer>> getExps();

    //returns a whole list of WEPAONs
    @Query("SELECT WEAPON FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<String>> getWeapons();

    //returns a whole list of DECKs
    @Query("SELECT DECK FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<String>> getDecks();

    //returns a whole list of ITEMs
    @Query("SELECT ITEM FROM User_Characters_Table ORDER BY id ASC")
    LiveData<List<String>> getItems();
     */
}
