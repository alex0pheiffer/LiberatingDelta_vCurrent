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
public interface User_EQPlayed_Dao {

    //The DAO associated with the User_EQPlayed_Table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_EQPlayed UserEQPlayed);

    //to update a field with a matching EqPlayed
    @Update
    void update(User_EQPlayed UserEQPlayed);

    //update COMP by id
    @Query("UPDATE User_EQPlayed_Table SET COMP=:completed WHERE id=:index")
    void updateComp(int completed, int index);

    //update SIGITEM by id
    @Query("UPDATE User_EQPlayed_Table SET SIGITEM=:sig_item_collected WHERE id=:index")
    void updateSigItem(int sig_item_collected, int index);

    @Delete
    void delete(User_EQPlayed UserEQPlayed);

    //delete the entire table
    @Query("DELETE FROM User_EQPlayed_Table")
    void deleteAll();

    //return the list of User_EQPlayed objects
    @Query("SELECT * FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<User_EQPlayed>> getAll();

    /*
    //return the list of all LABELs
    @Query("SELECT LABEL FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //returns a whole list of COMPs
    @Query("SELECT COMP FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<Integer>> getComps();

    //returns a whole list of SIGITEMs
    @Query("SELECT SIGITEM FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<Integer>> getSigItems();
     */

}
