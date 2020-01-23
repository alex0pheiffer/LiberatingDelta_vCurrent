package com.example.rpg_v4.basic_classes.the_fighting_characters;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Deck;
import com.example.rpg_v4.basic_classes.fighting_character;
import com.example.rpg_v4.basic_classes.stats_object;
import com.example.rpg_v4.basic_classes.the_fighting_characters.characters_decks.stellaDeckCh1;

public class Stella_ch1 extends fighting_character {

    public Stella_ch1() {
        super("Stella",
                new String[] {"Mreeww","Mew"},
                new Integer[] {1,2},
                "Female",
                7,
                67,
                false,
                80,
                30,
                getAType(19),
                getMType(1),
                R.drawable.stella1_battle,
                "claws",
                null,
                null,
                new stellaDeckCh1(),
                new stats_object(2,10,0,100,100,10,50,0,20,20));
    }
}
