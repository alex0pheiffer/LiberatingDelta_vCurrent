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

    //update LVL by id
    @Query("UPDATE User_Characters_Table SET LVL=:exp WHERE id=:index")
    void updateLevel(int exp, int index);

    //update RANK by id
    @Query("UPDATE User_Characters_Table SET RANK=:exp WHERE id=:index")
    void updateRank(int exp, int index);

    //update WEAPON by id
    @Query("UPDATE User_Characters_Table SET WEAPON=:weapon_equip WHERE id=:index")
    void updateWeapon(String weapon_equip, int index);

    //update DECK by id
    @Query("UPDATE User_Characters_Table SET DECK=:deck_equip WHERE id=:index")
    void updateDeck(String deck_equip, int index);

    //update ITEM by id
    @Query("UPDATE User_Characters_Table SET ITEM=:item_equip WHERE id=:index")
    void updateItem(String item_equip, int index);

    //update REGION1EXP by id
    @Query("UPDATE User_Characters_Table SET REGION1EXP=:exp WHERE id=:index")
    void updateRegion1exp(int exp, int index);

    //update REGION23EXP by id
    @Query("UPDATE User_Characters_Table SET REGION23EXP=:exp WHERE id=:index")
    void updateRegion23exp(int exp, int index);

    //update REGION4EXP by id
    @Query("UPDATE User_Characters_Table SET REGION4EXP=:exp WHERE id=:index")
    void updateRegion4exp(int exp, int index);

    //update REGION5EXP by id
    @Query("UPDATE User_Characters_Table SET REGION5EXP=:exp WHERE id=:index")
    void updateRegion5exp(int exp, int index);

    //update REGION6EXP by id
    @Query("UPDATE User_Characters_Table SET REGION6EXP=:exp WHERE id=:index")
    void updateRegion6exp(int exp, int index);

    //update REGION7EXP by id
    @Query("UPDATE User_Characters_Table SET REGION7EXP=:exp WHERE id=:index")
    void updateRegion7exp(int exp, int index);

    //update REGION89EXP by id
    @Query("UPDATE User_Characters_Table SET REGION89EXP=:exp WHERE id=:index")
    void updateRegion89exp(int exp, int index);

    //update REGION10EXP by id
    @Query("UPDATE User_Characters_Table SET REGION10EXP=:exp WHERE id=:index")
    void updateRegion10exp(int exp, int index);

    //update REGION11EXP by id
    @Query("UPDATE User_Characters_Table SET REGION11EXP=:exp WHERE id=:index")
    void updateRegion11exp(int exp, int index);

    //update REGION12EXP by id
    @Query("UPDATE User_Characters_Table SET REGION12EXP=:exp WHERE id=:index")
    void updateRegion12exp(int exp, int index);

    //update REGION13EXP by id
    @Query("UPDATE User_Characters_Table SET REGION13EXP=:exp WHERE id=:index")
    void updateRegion13exp(int exp, int index);

    //update REGION14EXP by id
    @Query("UPDATE User_Characters_Table SET REGION14EXP=:exp WHERE id=:index")
    void updateRegion14exp(int exp, int index);

    //update REGION16EXP by id
    @Query("UPDATE User_Characters_Table SET REGION16EXP=:exp WHERE id=:index")
    void updateRegion16exp(int exp, int index);

    //update REGION17EXP by id
    @Query("UPDATE User_Characters_Table SET REGION17EXP=:exp WHERE id=:index")
    void updateRegion17exp(int exp, int index);

    //update REGION18EXP by id
    @Query("UPDATE User_Characters_Table SET REGION18EXP=:exp WHERE id=:index")
    void updateRegion18exp(int exp, int index);

    //update REGION19EXP by id
    @Query("UPDATE User_Characters_Table SET REGION19EXP=:exp WHERE id=:index")
    void updateRegion19exp(int exp, int index);

    //update REGION20EXP by id
    @Query("UPDATE User_Characters_Table SET REGION20EXP=:exp WHERE id=:index")
    void updateRegion20exp(int exp, int index);

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
