package com.example.rpg_v4.basic_classes.the_MCs;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Weapons.basicSword;
import com.example.rpg_v4.basic_classes.fighting_character;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.static_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class Katherine extends main_character {

    private fighting_character mc_fight;
    private static_character mc_static;

    public Katherine() {
        super("Katherine",
                new String[] {"The main Character!"},
                new Integer[] {Integer.valueOf(1)},
                new String[] {"Yippee!"},
                "Female",
                14,
                163,
                true,
                80,
                5,
                getAType(17),
                getMType(0),
                R.drawable.katie1,
                R.drawable.katie1_battle,
                "bow",
                null,
                null,
                null,
                new stats_object(3,10,0,100,0,10,0,0,0,0));
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

    public fighting_character getFightingCharacter() {
        return mc_fight;
    }

    public static_character getStaticCharacter() {
        return mc_static;
    }

    public void setFightingCharacter(fighting_character character) {
        this.mc_fight = character;
    }

    public void setStaticCharacter(static_character character) {
        this.mc_static = character;
    }

    public String toString() {return "Katherine";}

}
