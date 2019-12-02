package com.example.rpg_v4.basic_classes.the_chapters;

import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.phase_objects;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;
import com.example.rpg_v4.basic_classes.dialog_phases.ch1_1_phase;

public class chapter_1 extends Chapter {

    //This chapter is ALL dialog.

    public chapter_1() {
        super("Beginning_pt1",1,"Veneland","Chipper Towne",0,
                new phase_objects[]{new ch1_1_phase()}, 1);
    }
}
