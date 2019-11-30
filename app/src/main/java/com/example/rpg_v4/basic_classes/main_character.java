package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.PL;

public abstract class main_character extends Characters {

    int characterImgDrawable;
    fighting_character mc_fight;
    static_character mc_static;

    public main_character(String nom, String[] descriptstr, Integer[] descriptpl, String[] greet, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, int characterImgDrawable) {
        super(nom, descriptstr, descriptpl, greet, gender, age, height, human, magicalAff, strength, charType);
        this.characterImgDrawable = characterImgDrawable;
    }

    public fighting_character getFightingCharacter() {
        return mc_fight;
    }

    public static_character getStaticCharacter() {
        return mc_static;
    }

    public void setFightingCharacter(fighting_character character) {
        this.mc_fight = character;
    }

    public void setStaticCharacter(static_character character) {
        this.mc_static = character;
    }

    public int getCharacterImgDrawable() {
        return characterImgDrawable;
    }

    public String toString() {return "main_character";}
}
