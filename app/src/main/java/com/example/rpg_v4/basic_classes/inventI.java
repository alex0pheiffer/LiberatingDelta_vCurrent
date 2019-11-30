package com.example.rpg_v4.basic_classes;

public abstract class inventI {

    private String nom;
    private stats_object buff;
    private boolean use_for_buff;   //if you have to use the object to get the buff effects, this is TRUE

    public inventI(String name, boolean use, stats_object buff) {
        this.nom = name;
        this.use_for_buff = use;
        this.buff = buff;
    }

    public String getNom() {return nom;}

    public boolean getUse_for_buff() {return use_for_buff;}

    public stats_object getStats() {return buff;}

    public String toString() {return "inventI";}
}
