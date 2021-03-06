package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Weapons.basicSword;
import com.example.rpg_v4.basic_classes.fighting_character;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.static_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class Delta extends main_character {

    private fighting_character mc_fight;
    private static_character mc_static;

    public Delta() {
        super("Delta",
                new String[] {"Other Main Character!"},
                new Integer[] {new Integer(1)},
                new String[] {"Hi there."},
                "Male",
                16,
                180,
                false,
                50,
                9,
                getAType(9),
                getMType(1),
                R.drawable.delta1,
                R.drawable.delta1_battle,
                "sword",
                new basicSword(),
                null,
                null,
                new stats_object(5,10,200,0,0,20,50,0,0,0));
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

    public String toString() {return "Delta";}
}
