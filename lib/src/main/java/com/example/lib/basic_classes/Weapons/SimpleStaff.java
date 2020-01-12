package com.example.lib.basic_classes.Weapons;

import com.example.lib.basic_classes.Mweapon;
import com.example.lib.basic_classes.stats_object;

public class SimpleStaff extends Mweapon {

    private String instanceName;
    private static int numInstance = 0;

    public SimpleStaff() {
        super("Simple Staff",
                new stats_object(2,1,0,0,0,0, 0,0,0,0),
                4,
                100);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public int getNumInstance() {
        return numInstance;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
