package com.example.rpg_v4.db_files;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_EQPlayed_Table")
public class User_EQPlayed {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "LABEL")
    private String name;

    @NonNull
    @ColumnInfo(name = "COMP")
    //this number ranges from 0 to inf
    private int completed;

    @NonNull
    @ColumnInfo(name = "SIGITEM")
    //this number ranges from 0 to inf
    private int sig_item_collected;

    public User_EQPlayed(String name, int completed, int sig_item_collected) {
        this.name = name;
        this.completed = completed;
        this.sig_item_collected = sig_item_collected;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCompleted() {
        return completed;
    }

    public int getSig_item_collected() {
        return sig_item_collected;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setSig_item_collected(int sig_item_collected) {
        this.sig_item_collected = sig_item_collected;
    }

    public String toString() {
        return "eqplayed";
    }
}
