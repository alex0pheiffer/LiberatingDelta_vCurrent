package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.basic_classes.main_character;

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
                com.example.rpg_v4.R.drawable.dummycat);
    }

    public String toString() {return "Vivian";}

}
