package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Weapons.basicSword;
import com.example.rpg_v4.basic_classes.fighting_character;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.static_character;
import com.example.rpg_v4.basic_classes.stats_object;

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
                R.drawable.vivian1,
                R.drawable.katie1_battle,
                "dagger",
                new basicSword(),
                null,
                null,
                new stats_object(3,10,0,0,130,10,10,10,10,10));
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
