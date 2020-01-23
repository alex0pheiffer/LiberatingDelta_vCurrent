package com.example.rpg_v4.basic_classes.the_static_characters;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.static_character;

public class Grendel1 extends static_character {

    public Grendel1() {
        super("???",
                new String[] {"Grendel Hi","Hmph","Nanana"},
                new Integer[] {1,2,3},
                "Male",
                35,
                105,
                false,
                50,
                80,
                getAType(17),
                getMType(4),
                new Integer[] {R.drawable.grendel1}
                );
    }

}
