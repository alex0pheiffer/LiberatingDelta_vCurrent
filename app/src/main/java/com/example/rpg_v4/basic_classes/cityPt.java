package com.example.rpg_v4.basic_classes;

public abstract class cityPt {
    private String nom;
    private String region;
    private int drawable_background;
    private int drawable_map_icon;
    private Chapter[] chapters;
    private int chapterAmt;
    private String description;
    private int plDiscover;     //at what pl does this city get unlocked?

    public cityPt(String name, int drawing, int icon, Chapter[] list, String descript, int unlock) {
        this.nom = name;
        this.drawable_background = drawing;
        this.drawable_map_icon = icon;
        this.chapters = list;
        this.chapterAmt = chapters.length;
        this.description = descript;
        this.plDiscover = unlock;
    }

    public int getPlDiscover() { return plDiscover; }

    public Chapter getChapter(int index) {
        return chapters[index];
    }

    public Chapter getChapter(String name) {
        Chapter chapter = null;
        for (int i=0; i<chapterAmt; i++) {
            if (name.equals(chapters[i].getNom())) chapter = chapters[i];
        }
        return chapter;
    }

    public int getChapterAmt() {return chapterAmt;}

    public String getNom() {
        return nom;
    }

    public int getDrawable_background() {
        return drawable_background;
    }

    public int getDrawable_map_icon() {
        return drawable_map_icon;
    }

    public String getDescription() {
        return description;
    }

    public String toString() { return getNom(); }
}
