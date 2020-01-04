package com.example.rpg_v4.basic_classes.the_regions;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.the_cities.chipper_towne;
import com.example.rpg_v4.basic_classes.the_cities.maleficere_mansion;

public class Veneland extends regions {
    //aka region1

    public Veneland() {
        super("Veneland",1, R.drawable.zoomed_region_1_3,
                new cityPt[]{new maleficere_mansion(),new chipper_towne()});
    }
}
