package com.example.rpg_v4;

import com.example.rpg_v4.basic_classes.PL;

public class PL_VendingMachine {

    //The Ultimate Chapter List Complete with matching PL's

    private static PL[] PL_List = {};

    public PL_VendingMachine() {}

    public static PL getPL(int pl) {
        return PL_List[pl-1];
    }

}
