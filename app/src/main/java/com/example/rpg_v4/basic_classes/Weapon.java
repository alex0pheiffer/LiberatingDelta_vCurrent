package com.example.rpg_v4.basic_classes;

public abstract class Weapon extends inventI{

    private boolean isCharSpec;
    private String charequip;
    private final String[] charequipers = {"All", "Vivian", "Katherine", "Delta"};

    public Weapon(String name, stats_object buff, int charequip) {
        super(name, false, buff);
        this.charequip = charequipers[charequip];
        if (charequip == 0) {
            this.isCharSpec = false;
        }
        else {
            this.isCharSpec = true;
        }
    }

    public String getCharEquip() {
        return charequip;
    }

    public String toString() {return getClass().getSimpleName();}
}
