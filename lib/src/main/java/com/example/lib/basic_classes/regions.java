package com.example.lib.basic_classes;

public abstract class regions {
    private String nom;
    private int regionNum;
    private int drawable_background;
    private cityPt[] cityPts;
    private int cityAmt;

    public regions(String name, int regionNum, int drawing, cityPt[] cities) {
        this.nom = name;
        this.drawable_background = drawing;
        this.cityPts = cities;
        this.cityAmt = cityPts.length;
        System.out.println(this.nom+" has "+this.cityAmt+" cities.");
    }

    public int getRegionNum() {
        return regionNum;
    }

    public cityPt getCityPt(int index) {
        return cityPts[index];
    }

    public cityPt getCityPt(String name) {
        cityPt city = null;
        for (int i=0; i<cityAmt; i++) {
            if (name.equals(cityPts[i])) city = cityPts[i];
        }
        return city;
    }

    public int getDrawable_background() {
        return drawable_background;
    }

    public String getNom() {
        return nom;
    }

    public int getCityAmt() { return cityAmt;}

    public void setDrawable_background(int drawable_background) {
        this.drawable_background = drawable_background;
    }

    public String toString() {return nom;}
}
