package com.example.rpg_v4.db_files;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_Values_Table")
public class User_Values {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "PL")
    private int cur_PL;

    @NonNull
    @ColumnInfo(name = "PHASE")
    private int cur_phase;

    @NonNull
    @ColumnInfo(name = "OKANE")
    private int cur_okane;

    @NonNull
    @ColumnInfo(name = "FRONTCHAR")
    private String front_char;

    @NonNull
    @ColumnInfo(name = "USERNAME")
    private String username;

    @NonNull
    @ColumnInfo(name = "PASSWORD")
    private String password;

    @NonNull
    @ColumnInfo(name="REGION")
    private String cur_region;


    public User_Values(int cur_PL, int cur_phase, int cur_okane, String front_char, String username, String password, String cur_region) {
        this.cur_PL = cur_PL;
        this.cur_phase = cur_phase;
        this.cur_okane = cur_okane;
        this.front_char = front_char;
        this.username = username;
        this.password = password;
        this.cur_region=cur_region;
    }

    public int getCur_PL() {
        return cur_PL;
    }

    public void setCur_PL(int cur_PL) {
        this.cur_PL = cur_PL;
    }

    public int getCur_okane() {
        return cur_okane;
    }

    public void setCur_phase(int cur_phase) {
        this.cur_phase = cur_phase;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setFront_char(@NonNull String front_char) {
        this.front_char = front_char;
    }

    public int getCur_phase() {
        return cur_phase;
    }

    public String getFront_char() {
        return front_char;
    }

    public String getUsername() {
        return username;
    }

    public void setCur_okane(int cur_okane) {
        this.cur_okane = cur_okane;
    }

    public String getPassword() {
        return password;
    }

    public void setCur_region(@NonNull String cur_region) {
        this.cur_region = cur_region;
    }

    public void setId(int index) {id=index;}

    public int getId() {return id;}

    @NonNull
    public String getCur_region() {
        return cur_region;
    }

    public String toString() {
        return "values";
    }
}
