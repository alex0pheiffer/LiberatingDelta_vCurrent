package com.example.lib.basic_classes.the_regions;

import com.example.lib.basic_classes.cityPt;
import com.example.lib.basic_classes.regions;
import com.example.lib.basic_classes.the_cities.chipper_towne;
import com.example.lib.basic_classes.the_cities.maleficere_mansion;

public class Veneland extends regions {
    //aka region1

    public Veneland() {
        super("Veneland",1, 0,
                new cityPt[]{new maleficere_mansion(),new chipper_towne()});
    }
}
