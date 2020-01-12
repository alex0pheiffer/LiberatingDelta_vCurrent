package com.example.lib.basic_classes;

public abstract class Chapter {
    private int coor_PL;
    private String nom;
    private String region;
    private String city;
    private int drawable_background; //do i have a place for the individual chapter images? or are we just doing region imgs...
    private phase_objects[] phase_order;
    private int phase_amount;

    public Chapter(String name, int pl, String region, String city, int drawing, phase_objects[] phases, int phase_amount) {
        this.nom = name;
        this.coor_PL = pl;
        this.region = region;
        this.city=city;
        this.drawable_background = drawing;
        this.phase_amount = phase_amount;
        this.phase_order = phases;
    }

    /*
    public void setBannedCities(cityPt[] cities) {
        bannedCities = cities;
    }

    public cityPt getBannedCity(int index) {
        return bannedCities[index];
    }

    public boolean isBannedCity(String name) {
        boolean bool= false;
        if (bannedCities!=null) {
            for (int i = 0; i < bannedCities.length; i++) {
                if (name.equals(bannedCities[i].toString())) bool = true;
            }
        }
        return bool;
    }
    */
    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public int getDrawable_background() {
        return drawable_background;
    }

    public String getNom() {
        return nom;
    }

    public int getCoor_PL() {
        return coor_PL;
    }

    public phase_objects getPhase(int index) {
        return phase_order[index];
    }

    public int getPhase_amount() {
        return phase_amount;
    }

    public void setDrawable_background(int drawable_background) {
        this.drawable_background = drawable_background;
    }
}
