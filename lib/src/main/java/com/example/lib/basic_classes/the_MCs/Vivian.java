package com.example.lib.basic_classes.the_MCs;

import com.example.lib.basic_classes.Weapons.basicSword;
import com.example.lib.basic_classes.fighting_character;
import com.example.lib.basic_classes.main_character;
import com.example.lib.basic_classes.static_character;
import com.example.lib.basic_classes.stats_object;

public class Vivian extends main_character {

    private fighting_character mc_fight;
    private static_character mc_static;

    public Vivian() {
        super("Vivian",
                new String[] {"Main Character's Sister"},
                new Integer[] {new Integer(1)},
                new String[] {"Hello."},
                "Female",
                17,
                170,
                true,
                65,
                7,
                getAType(17),
                getMType(3),
                0,
                0,
                "dagger",
                new basicSword(),
                null,
                null,
                new stats_object(3,10,0,0,130,100,100,100,100,100));
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

    public String toString() {return "Vivian";}

}
