package com.example.rpg_v4.db_files;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_Characters_Table")
public class User_Characters {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "LABEL")
    private String name;

    @NonNull
    @ColumnInfo(name = "LVL")
    private int level;

    @NonNull
    @ColumnInfo(name = "EXP")
    private int exp2next;

    @NonNull
    @ColumnInfo(name = "WEAPON")
    private String weapon_equip;

    @NonNull
    @ColumnInfo(name = "DECK")
    private String deck_equip;

    @NonNull
    @ColumnInfo(name = "ITEM")
    private String item_equip;

    public User_Characters(String name, int level, int exp2next, String weapon_equip, String deck_equip, String item_equip) {
        this.name = name;
        this.level = level;
        this.exp2next = exp2next;
        this.weapon_equip = weapon_equip;
        this.deck_equip = deck_equip;
        this.item_equip = item_equip;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getExp2next() {
        return exp2next;
    }

    public int getLevel() {
        return level;
    }

    @NonNull
    public String getDeck_equip() {
        return deck_equip;
    }

    @NonNull
    public String getItem_equip() {
        return item_equip;
    }

    @NonNull
    public String getWeapon_equip() {
        return weapon_equip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDeck_equip(@NonNull String deck_equip) {
        this.deck_equip = deck_equip;
    }

    public void setExp2next(int exp2next) {
        this.exp2next = exp2next;
    }

    public void setItem_equip(@NonNull String item_equip) {
        this.item_equip = item_equip;
    }

    public void setWeapon_equip(@NonNull String weapon_equip) {
        this.weapon_equip = weapon_equip;
    }

    public String toString() {
        return "characters";
    }
}
