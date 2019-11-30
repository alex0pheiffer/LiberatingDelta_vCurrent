package com.example.rpg_v4.db_files;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_Decks_Table")
public class User_Decks {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name="LABEL")
    //the name of the deck
    private String name;

    @NonNull
    @ColumnInfo(name = "CHAR")
    private String char_equip;

    @NonNull
    @ColumnInfo(name = "LEN")
    //number of cards in the deck
    private int length;

    public User_Decks(String name, String char_equip, int length) {
        this.name = name;
        this.char_equip = char_equip;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getChar_equip() {
        return char_equip;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChar_equip(@NonNull String char_equip) {
        this.char_equip = char_equip;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString() {
        return "decks";
    }
}
