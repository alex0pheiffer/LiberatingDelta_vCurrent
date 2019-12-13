package com.example.rpg_v4.basic_classes.the_chapters;

import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.phase_objects;
import com.example.rpg_v4.basic_classes.the_cities.maleficere_mansion;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;

public class chapter_filler extends Chapter {

    //This chapter is ALL dialog.

    public chapter_filler() {
        super("maleficereMansionChapter!",5,"Veneland","Maleficere Mansion",0,
                new phase_objects[] {}, 0);
    }
}
