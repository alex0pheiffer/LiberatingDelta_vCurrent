package com.example.rpg_v4.basic_classes;

public abstract class phase_objects {
    //to be extended by fights and phases
    int coorPL;
    int phaseNumber;

    public phase_objects(int pl, int phase) {
        this.coorPL = pl;
        this.phaseNumber = phase;
    }

    public int getPL() { return coorPL; }
    public int getPhase() { return phaseNumber; }
    public String toString() { return "phase_objects";}
}
