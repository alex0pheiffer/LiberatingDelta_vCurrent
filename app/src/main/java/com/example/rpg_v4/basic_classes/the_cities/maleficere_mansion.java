package com.example.rpg_v4.basic_classes.the_cities;

import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.the_chapters.chapter_filler;

public class maleficere_mansion extends cityPt {

    public maleficere_mansion() {
        super("Maleficere Mansion", 0, 0,
                new Chapter[]{ new chapter_filler()},
                "The home that Lithe and her sister Violet grew up in. It's mostly secluded in the woods.",
                1);

    }

}
