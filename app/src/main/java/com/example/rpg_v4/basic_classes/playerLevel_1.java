package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.basic_classes.the_MCs.Katherine;
import com.example.rpg_v4.basic_classes.the_chapters.chapter_1;
import com.example.rpg_v4.basic_classes.the_cities.chipper_towne;
import com.example.rpg_v4.basic_classes.the_regions.GreatNorth;
import com.example.rpg_v4.basic_classes.the_regions.HDR;
import com.example.rpg_v4.basic_classes.the_regions.IceCube;
import com.example.rpg_v4.basic_classes.the_regions.Juslyn;
import com.example.rpg_v4.basic_classes.the_regions.MaceriaUnion;
import com.example.rpg_v4.basic_classes.the_regions.Nebula;
import com.example.rpg_v4.basic_classes.the_regions.Petrasepire;
import com.example.rpg_v4.basic_classes.the_regions.Pietas;
import com.example.rpg_v4.basic_classes.the_regions.Rupes;
import com.example.rpg_v4.basic_classes.the_regions.TheTossedStones;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;

import java.util.ArrayList;
import java.util.List;

public class playerLevel_1 extends PL {

    public playerLevel_1() {
        super(1,
                new chapter_1(),
                new regions[] { new Veneland(), new Pietas(), new TheTossedStones(), new HDR(),
                                new Nebula(), new IceCube(), new Rupes(), new Petrasepire(),
                                new Juslyn(), new MaceriaUnion(), new GreatNorth()},
                new Veneland(),
                new chipper_towne(),
                new String[] {"Katherine"},
                new main_character[] {new Katherine()},
                new Characters[] {new Katherine()},
                "a test of sotrs",
                new Weapon[] {null},
                new Card[] {null},
                new inventI[] {null},
                new ExtraQuest[] {null},
                null,
                new cityPt[] {null},
                new Weapon[] {null},
                new Card[] {null},
                new inventI[] {null},
                new ExtraQuest[] {null},
                new String[] {null},
                new String[] {null},
                new String[] {null},
                new Weapon[] {null},
                new Card[] {null},
                new inventI[] {null},
                new Characters[] {new Katherine()});
    }

}
