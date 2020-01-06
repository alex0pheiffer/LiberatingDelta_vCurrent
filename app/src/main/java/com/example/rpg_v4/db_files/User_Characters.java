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
    @ColumnInfo(name = "RANK")
    private int rank;

    @NonNull
    @ColumnInfo(name = "WEAPON")
    private String weapon_equip;

    @NonNull
    @ColumnInfo(name = "DECK")
    private String deck_equip;

    @NonNull
    @ColumnInfo(name = "ITEM")
    private String item_equip;

    @NonNull
    @ColumnInfo(name = "REGION1EXP")
    private int region1exp;

    @NonNull
    @ColumnInfo(name = "REGION23EXP")
    private int region23exp;

    @NonNull
    @ColumnInfo(name = "REGION4EXP")
    private int region4exp;

    @NonNull
    @ColumnInfo(name = "REGION5EXP")
    private int region5exp;

    @NonNull
    @ColumnInfo(name = "REGION6EXP")
    private int region6exp;

    @NonNull
    @ColumnInfo(name = "REGION7EXP")
    private int region7exp;

    @NonNull
    @ColumnInfo(name = "REGION89EXP")
    private int region89exp;

    @NonNull
    @ColumnInfo(name = "REGION10EXP")
    private int region10exp;

    @NonNull
    @ColumnInfo(name = "REGION11EXP")
    private int region11exp;

    @NonNull
    @ColumnInfo(name = "REGION12EXP")
    private int region12exp;

    @NonNull
    @ColumnInfo(name = "REGION13EXP")
    private int region13exp;

    @NonNull
    @ColumnInfo(name = "REGION14EXP")
    private int region14exp;

    @NonNull
    @ColumnInfo(name = "REGION16EXP")
    private int region16exp;

    @NonNull
    @ColumnInfo(name = "REGION17EXP")
    private int region17exp;

    @NonNull
    @ColumnInfo(name = "REGION18EXP")
    private int region18exp;

    @NonNull
    @ColumnInfo(name = "REGION19EXP")
    private int region19exp;

    @NonNull
    @ColumnInfo(name = "REGION20EXP")
    private int region20exp;

    public User_Characters(String name, int level, int rank, String weapon_equip, String deck_equip, String item_equip,
                           int region1exp, int region23exp, int region4exp, int region5exp, int region6exp, int region7exp,
                           int region89exp, int region10exp, int region11exp, int region12exp, int region13exp, int region14exp,
                           int region16exp, int region17exp, int region18exp, int region19exp, int region20exp) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.weapon_equip = weapon_equip;
        this.deck_equip = deck_equip;
        this.item_equip = item_equip;
        this.region1exp = region1exp;
        this.region23exp = region23exp;
        this.region4exp = region4exp;
        this.region5exp = region5exp;
        this.region6exp = region6exp;
        this.region7exp = region7exp;
        this.region89exp = region89exp;
        this.region10exp = region10exp;
        this.region11exp = region11exp;
        this.region12exp = region12exp;
        this.region13exp = region13exp;
        this.region14exp = region14exp;
        this.region16exp = region16exp;
        this.region17exp = region17exp;
        this.region18exp = region18exp;
        this.region19exp = region19exp;
        this.region20exp = region20exp;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
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
    
    //get regionEXP
    //
    public int getRegion1exp() {
        return region1exp;
    }
    public int getRegion23exp() {
        return region23exp;
    }
    public int getRegion5exp() {
        return region5exp;
    }
    public int getRegion4exp() {
        return region4exp;
    }
    public int getRegion6exp() {
        return region6exp;
    }
    public int getRegion7exp() {
        return region7exp;
    }
    public int getRegion89exp() {
        return region89exp;
    }
    public int getRegion10exp() {
        return region10exp;
    }
    public int getRegion11exp() {
        return region11exp;
    }
    public int getRegion12exp() {
        return region12exp;
    }
    public int getRegion13exp() {
        return region13exp;
    }
    public int getRegion14exp() {
        return region14exp;
    }
    public int getRegion16exp() {
        return region16exp;
    }
    public int getRegion17exp() {
        return region17exp;
    }
    public int getRegion18exp() {
        return region18exp;
    }
    public int getRegion19exp() {
        return region19exp;
    }
    public int getRegion20exp() {
        return region20exp;
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setItem_equip(@NonNull String item_equip) {
        this.item_equip = item_equip;
    }

    public void setWeapon_equip(@NonNull String weapon_equip) {
        this.weapon_equip = weapon_equip;
    }
    
    //set regionEXP
    //
    public void setRegion1exp(int region1exp) {
        this.region1exp = region1exp;
    }
    public void setRegion23exp(int region23exp) {
        this.region23exp = region23exp;
    }
    public void setRegion4exp(int region4exp) {
        this.region4exp = region4exp;
    }
    public void setRegion5exp(int region5exp) {
        this.region5exp = region5exp;
    }
    public void setRegion6exp(int region6EXP) {
        this.region6exp = region6EXP;
    }
    public void setRegion7exp(int region7EXP) {
        this.region7exp = region7EXP;
    }
    public void setRegion89exp(int region89EXP) {
        this.region89exp = region89EXP;
    }
    public void setRegion10exp(int region10EXP) {
        this.region10exp = region10EXP;
    }
    public void setRegion11exp(int region11EXP) {
        this.region11exp = region11EXP;
    }
    public void setRegion12exp(int nebulaEXP) {
        this.region12exp = nebulaEXP;
    }
    public void setRegion13exp(int region13EXP) {
        this.region13exp = region13EXP;
    }
    public void setRegion14exp(int region14exp) {
        this.region14exp = region14exp;
    }
    public void setRegion16exp(int region16exp) {
        this.region16exp = region16exp;
    }
    public void setRegion17exp(int region17exp) {
        this.region17exp = region17exp;
    }
    public void setRegion18exp(int region18exp) {
        this.region18exp = region18exp;
    }
    public void setRegion19exp(int region19exp) {
        this.region19exp = region19exp;
    }
    public void setRegion20exp(int region20exp) {
        this.region20exp = region20exp;
    }

    public String toString() {
        return "characters";
    }
}
