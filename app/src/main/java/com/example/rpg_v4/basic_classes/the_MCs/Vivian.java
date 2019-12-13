package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.basic_classes.Weapons.basicSword;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class Vivian extends main_character {

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
                com.example.rpg_v4.R.drawable.dummycat,
                0,
                "dagger",
                new basicSword(),
                null,
                new stats_object(3,10,0,0,3,1,1,1,1,1));
    }

    public String toString() {return "Vivian";}

}
