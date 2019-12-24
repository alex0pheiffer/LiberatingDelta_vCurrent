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
public interface User_Inventory_Dao {

    //The DAO associated with the User_Inventory table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_Inventory userInventory);

    //removes an item
    @Delete
    void delete(User_Inventory userInventory);

    //to update a field with a matching user
    @Update
    void update(User_Inventory userInventory);

    //update AMT by id
    @Query("UPDATE User_Inventory_Table SET AMT=:amount WHERE id=:index")
    void updateAmount(int amount, int index);

    //delete the entire table
    @Query("DELETE FROM User_Inventory_Table")
    void deleteAll();

    //return User_Inventory object list
    @Query("SELECT * FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<User_Inventory>> getAll();

    /*
    //return inventory name by id
    @Query("SELECT LABEL FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //return the amt of uses left
    @Query("SELECT AMT FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<Integer>> getAmts();

    //returns the type of item
    @Query("SELECT TYPE FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<String>> getTypes();
     */
}
