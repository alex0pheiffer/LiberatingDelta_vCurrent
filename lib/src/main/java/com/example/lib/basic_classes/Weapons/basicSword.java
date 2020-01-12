package com.example.lib.basic_classes.Weapons;

import com.example.lib.basic_classes.Weapon;
import com.example.lib.basic_classes.stats_object;

public class basicSword extends Weapon {

    private String instanceName;
    private static int numInstance = 0;

    public basicSword() {
        super("Basic Sword",
                new stats_object(2,1,0,0,0,0, 0,0,0,0),
                0);
        instanceName = getClass().getName()+" "+numInstance;
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
