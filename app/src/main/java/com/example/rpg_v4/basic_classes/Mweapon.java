package com.example.rpg_v4.basic_classes;

public abstract class Mweapon extends Weapon {

    private String type;
    private int mPerc;

    public Mweapon(String name, stats_object buff, String type, int mPerc) {
        super(name, buff, 0);
        this.type = type;
        this.mPerc = mPerc;
    }

    public String getType() {return type;}
    public int getmPerc() {return mPerc;}
    public String toString() {return "Mweapon";}
}
