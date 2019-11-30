package com.example.rpg_v4.basic_classes;

public abstract class Weapon extends inventI{

    stats_object buff;

    public Weapon() {
        super("Weapon");
    }

    public stats_object getStats() {return buff;}

    public String toString() {return "Weapon";}
}
