package com.example.lib.basic_classes.the_cities;

import com.example.lib.basic_classes.Chapter;
import com.example.lib.basic_classes.cityPt;
import com.example.lib.basic_classes.the_chapters.chapter_1;
import com.example.lib.basic_classes.the_chapters.chapter_2;

public class chipper_towne extends cityPt {

    public chipper_towne() {
        super("Chipper Towne", 0, 0,
                new Chapter[]{new chapter_1(), new chapter_2()},
                "One of the most bustling cities in the region. The city that Lithe first starts in.",
                1);

    }

}
