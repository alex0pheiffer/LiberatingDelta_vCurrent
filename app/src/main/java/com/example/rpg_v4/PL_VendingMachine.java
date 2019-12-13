package com.example.rpg_v4;

import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.playerLevel_1;

public class PL_VendingMachine {

    //The Ultimate Chapter List Complete with matching PL's
    private static PL pl_1 = new playerLevel_1();

    private static PL[] PL_List = {pl_1};

    public PL_VendingMachine() {}

    public static PL getPL(int pl) {
        return PL_List[pl-1];
    }

}
