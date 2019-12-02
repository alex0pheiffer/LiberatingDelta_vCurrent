package com.example.rpg_v4.basic_classes.the_static_characters;

import com.example.rpg_v4.basic_classes.static_character;

public class staticer_test extends static_character {

    public staticer_test() {
        super("staticer",
                new String[] {"this is just for testing.","Hmph","nanana"},
                new Integer[] {1,2,3},
                "Female",
                43,
                105,
                true,
                50,
                80,
                getAType(0),
                new Integer[] {com.example.rpg_v4.R.drawable.dummycat}
                );
    }

}
