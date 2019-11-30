package com.example.rpg_v4.db_files;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_Inventory_Table")
public class User_Inventory {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "LABEL")
    //what is the name of this item
    private String name;

    @NonNull
    @ColumnInfo(name = "TYPE")
    //is it a weapon? item? etc
    private String type;

    @NonNull
    @ColumnInfo(name = "AMT")
    //how many uses are left? if inf put -1
    private int amount;

    public User_Inventory(String name, String type, int amount) {
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String toString() {
        return "inventory";
    }
}
