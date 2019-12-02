package com.example.rpg_v4.basic_classes;

public abstract class ExtraQuest extends Chapter {

    //the coorPL is the PL which this EQ gets unlocked.
    int capPL;  //the PL which the EQ is locked
    String objective;

    public ExtraQuest(String name, int startPL, String region, String city, int drawing, phase_objects[] phases, int phaseAmt, int capPL, String objective) {
        super(name,startPL,region,city,drawing,phases,phaseAmt);
        this.capPL = capPL;
        this.objective = objective;
    }

}
