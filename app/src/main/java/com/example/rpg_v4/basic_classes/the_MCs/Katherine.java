package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.basic_classes.main_character;

public class Katherine extends main_character {

    public Katherine() {
        super("Katherine",
                new String[] {"The main Character!"},
                new Integer[] {new Integer(1)},
                new String[] {"Yippee!"},
                "Female",
                14,
                163,
                true,
                80,
                5,
                "Mage",
                new Integer[] {com.example.rpg_v4.R.drawable.dummy_mmc_img},
                new Integer[] {new Integer(1)});
    }

    /*
    //if you give a pl, it will tell you what pl the closest character has, rounded down
    public int getMcByPl(int pl) {
        int index =0;
        for (int n=0; n<mc_by_pl.length;n++){
            if(mc_by_pl[n] > pl) {
                index = n;
                break;
            }
        }
        return (int)mc_by_pl[index];
    }
    */

    public String toString() {return "Katherine";}

}
