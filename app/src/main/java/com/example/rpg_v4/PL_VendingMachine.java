package com.example.rpg_v4;

import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.playerLevel_1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PL_VendingMachine {

    //The Ultimate Chapter List Complete with matching PL's
    private static PL pl_1 = new playerLevel_1();

    private static PL[] PL_List = {pl_1};

    public PL_VendingMachine() {}

    public static PL getPL(int pl) {
        return PL_List[pl-1];
    }


    //stuff regarding character levels
    //
    public static int getInitLevel(int totalLvlExp) {
        int level = 1;
        int upperbound = 100;
        int upperboundExp = exp2next(upperbound);
        if (totalLvlExp > upperboundExp) {
            throw new RuntimeException("Cannot be greater than level 100");
        }
        if (totalLvlExp == 0) {
            return level;
        }
        boolean stillGoing = true;
        while (stillGoing) {
            //level is current
            totalLvlExp = totalLvlExp - exp2next(level);
            if (totalLvlExp < 0) {
                stillGoing = false;
            }
            //level is the exact next level
            else if (totalLvlExp == 0) {
                level++;
                stillGoing = false;
            }
            //level is yet higher
            else {
                level++;
            }
        }
        return level;
    }

    public static int exp2next(double level) {
        return (int) (4*(Math.pow(level, 3))/5);
    }
}
