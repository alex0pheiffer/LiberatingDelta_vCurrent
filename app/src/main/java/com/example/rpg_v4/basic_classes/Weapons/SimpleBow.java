package com.example.rpg_v4.basic_classes.Weapons;

import com.example.rpg_v4.basic_classes.Weapon;
import com.example.rpg_v4.basic_classes.stats_object;

public class SimpleBow extends Weapon {

    private String instanceName;
    private static int numInstance = 0;

    public SimpleBow() {
        super("Simple Bow",
                new stats_object(2,1,0,0,0,0, 0,0,0,0),
                0);
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
