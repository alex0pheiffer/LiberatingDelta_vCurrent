package com.example.lib.basic_classes.the_chapters;

import com.example.lib.basic_classes.Chapter;
import com.example.lib.basic_classes.dialog_phases.ch1_1_phase;
import com.example.lib.basic_classes.phase_objects;

public class chapter_1 extends Chapter {

    //This chapter is ALL dialog.

    public chapter_1() {
        super("Beginning_pt1",1,"Veneland","Chipper Towne",0,
                new phase_objects[]{new ch1_1_phase()}, 1);
    }
}
