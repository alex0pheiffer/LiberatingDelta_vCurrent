package com.example.rpg_v4.db_files;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface User_Values_Dao {
    //The DAO associated with the User_Values table

    //add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User_Values userValues);

    //to update a field with a matching user
    @Update
    void update(User_Values userValues);

    //update USERNAME by USERNAME
    @Query("UPDATE User_Values_Table SET USERNAME=:username WHERE id=:index")
    void updateUsername(String username, int index);

    //update PASSWORD by USERNAME
    @Query("UPDATE User_Values_Table SET PASSWORD=:password WHERE id=:index")
    void updatePassword(String password, int index);

    //update OKANE by USERNAME
    @Query("UPDATE User_Values_Table SET OKANE=:cur_okane WHERE id=:index")
    void updateOkane(int cur_okane, int index);

    //update PHASE by USERNAME
    @Query("UPDATE User_Values_Table SET PHASE=:cur_phase WHERE id=:index")
    void updatePhase(int cur_phase, int index);

    //update PL by USERNAME
    @Query("UPDATE User_Values_Table SET PL=:cur_PL WHERE id=:index")
    void updatePL(int cur_PL, int index);

    //update FRONTCHAR by USERNAME
    @Query("UPDATE User_Values_Table SET FRONTCHAR=:front_char WHERE id=:index")
    void updateFrontChar(String front_char, int index);

    //update REGION by USERNAME
    @Query("UPDATE User_Values_Table SET REGION=:cur_region WHERE id=:index")
    void updateRegion(String cur_region, int index);

    //delete the entire table
    @Query("DELETE FROM User_Values_Table")
    void deleteAll();

    //return the list of all the usernames
    @Query("SELECT USERNAME FROM User_Values_Table")
    LiveData<List<String>> getUsernames();

    //alphabetize the rows by username
    @Query("SELECT * FROM User_Values_Table ORDER BY username ASC")
    LiveData<List<User_Values>> getAlphabetizedUser_Values();

}
