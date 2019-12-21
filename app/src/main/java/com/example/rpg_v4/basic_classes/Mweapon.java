package com.example.rpg_v4.basic_classes;

public abstract class Mweapon extends Weapon {

    private String type;
    private int mPerc;
                                        //"Fire","Water","Land","Air","Match"
    private final String[] mTypes = {stats_object.getType(0),stats_object.getType(1),stats_object.getType(2),stats_object.getType(3),"Match"};

    public Mweapon(String name, stats_object buff, int Mtype, int mPerc) {
        super(name, buff, 0);
        //if 4 ("match") the magic type is to match that of the char-user
        this.type = mTypes[Mtype];
        this.mPerc = mPerc;
    }

    public String getType() {return type;}
    public int getmPerc() {return mPerc;}
    public String toString() {return "Mweapon";}
}
