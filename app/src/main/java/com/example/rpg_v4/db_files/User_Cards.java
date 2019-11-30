package com.example.rpg_v4.db_files;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_Cards_Table")
public class User_Cards {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "AMT")
    private int amount;

    @NonNull
    @ColumnInfo(name = "LABEL")
    private String name;

    @NonNull
    @ColumnInfo(name = "DECK")
    private String deck;

    @NonNull
    @ColumnInfo(name = "POS")
    private int position;

    public User_Cards(String name, int amount, String deck, int position) {
        this.name = name;
        this.amount = amount;
        this.deck = deck;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDeck() {
        return deck;
    }

    public int getAmount() {
        return amount;
    }

    public int getPosition() {
        return position;
    }

    public void setDeck(@NonNull String deck) {
        this.deck = deck;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return "cards";
    }
}
