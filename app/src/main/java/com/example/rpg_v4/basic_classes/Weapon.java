package com.example.rpg_v4.basic_classes;

public abstract class Weapon extends inventI{

    public Weapon(String name, stats_object buff) {
        super(name, false, buff);
    }

    public String toString() {return "Weapon";}
}
