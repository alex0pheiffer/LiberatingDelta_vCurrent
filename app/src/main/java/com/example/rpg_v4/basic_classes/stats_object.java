package com.example.rpg_v4.basic_classes;

public class stats_object {

    private int attackA;        //brute strength
                                //VALUE
    private int health;         //the health points amt
                                //VALUE
    private int volatility;     //how likely the character is to increase atk/magic output abruptly
                                //smaller % means less likely to produce magic
                                //PERCENT
    private int evasiveA;       //how likely the character can miss a physical atk
                                //PERCENT
    private int evasiveM;       //how likely the character can miss a magic atk
                                //PRECENT
    private int ADefense;       //the affectiveness of a physical attack
                                //PERCENT
    private int FireDefense;    //the affectiveness of a fire magic attack
                                //PERCENT
    private int WaterDefense;   //the affectiveness of a water magic attack
                                //PERCENT
    private int LandDefense;    //the affectiveness of a land magic attack
                                //PERCENT
    private int AirDefense;     //the affectiveness of an air magic attack
                                //PERCENT
    private static final String[] types = {"Fire","Water","Land","Air","Scatter"};


    public stats_object(int atk, int hp, int vola, int evaA, int evaM, int physdef, int Fdef, int Wdef, int Ldef, int Adef) {
        this.attackA = atk;
        this.health = hp;
        //eva+vola are in 100s... 564 == 5.64% == .0564.. max will always be 10% == 1000 == .1
        this.volatility = vola;
        this.evasiveA = evaA;
        this.evasiveM = evaM;
        this.ADefense = physdef;
        this.FireDefense = Fdef;
        this.WaterDefense = Wdef;
        this.LandDefense = Ldef;
        this.AirDefense = Adef;
    }

    public static String getType(int index) {return types[index];}

    public void addStats(stats_object stats) {
        this.attackA = attackA + stats.getAttackA();
        this.health = health + stats.getHealth();
        this.volatility = volatility + stats.getVolatility();
        this.evasiveA = evasiveA + stats.getEvasiveA();
        this.evasiveM = evasiveM + stats.getEvasiveM();
        this.ADefense = ADefense + stats.getADefense();
        this.FireDefense = FireDefense + stats.getFireDefense();
        this.WaterDefense = WaterDefense + stats.getWaterDefense();
        this.LandDefense = LandDefense + stats.getLandDefense();
        this.AirDefense = AirDefense + stats.getAirDefense();
    }

    public void subtractStats(stats_object stats) {
        this.attackA = attackA - stats.getAttackA();
        this.health = health - stats.getHealth();
        this.volatility = volatility - stats.getVolatility();
        this.evasiveA = evasiveA - stats.getEvasiveA();
        this.evasiveM = evasiveM - stats.getEvasiveM();
        this.ADefense = ADefense - stats.getADefense();
        this.FireDefense = FireDefense - stats.getFireDefense();
        this.WaterDefense = WaterDefense - stats.getWaterDefense();
        this.LandDefense = LandDefense - stats.getLandDefense();
        this.AirDefense = AirDefense - stats.getAirDefense();
    }

    public void setStats(stats_object stats) {
        this.attackA = stats.getAttackA();
        this.health = stats.getHealth();
        this.volatility = stats.getVolatility();
        this.evasiveA = stats.getEvasiveA();
        this.evasiveM = stats.getEvasiveM();
        this.ADefense = stats.getADefense();
        this.FireDefense = stats.getFireDefense();
        this.WaterDefense = stats.getWaterDefense();
        this.LandDefense = stats.getLandDefense();
        this.AirDefense = stats.getAirDefense();
    }

    public void setADefense(int ADefense) {
        this.ADefense = ADefense;
    }

    public void setAirDefense(int airDefense) {
        AirDefense = airDefense;
    }

    public void setAttackA(int attackA) {
        this.attackA = attackA;
    }

    public void setEvasiveA(int evasiveA) {
        this.evasiveA = evasiveA;
    }

    public void setEvasiveM(int evasiveM) {
        this.evasiveM = evasiveM;
    }

    public void setFireDefense(int fireDefense) {
        FireDefense = fireDefense;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLandDefense(int landDefense) {
        LandDefense = landDefense;
    }

    public void setVolatility(int volatility) {
        this.volatility = volatility;
    }

    public void setWaterDefense(int waterDefense) {
        WaterDefense = waterDefense;
    }

    public int getADefense() {
        return ADefense;
    }

    public int getAirDefense() {
        return AirDefense;
    }

    public int getAttackA() {
        return attackA;
    }

    public int getEvasiveA() {
        return evasiveA;
    }

    public int getEvasiveM() {
        return evasiveM;
    }

    public int getFireDefense() {
        return FireDefense;
    }

    public int getHealth() {
        return health;
    }

    public int getLandDefense() {
        return LandDefense;
    }

    public int getWaterDefense() {
        return WaterDefense;
    }

    public int getVolatility() {
        return volatility;
    }

    public String toString() {return "stats";}
}
