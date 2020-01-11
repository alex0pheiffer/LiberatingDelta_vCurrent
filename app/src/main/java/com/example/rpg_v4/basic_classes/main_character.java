package com.example.rpg_v4.basic_classes;

public abstract class main_character extends fighting_character {

    private int characterImgDrawable;
    private String[] greetings;
    private int rankEXP;
    private int venelandEXP;
    private int pietasEXP;
    private int stonesEXP;
    private int hdrEXP;
    private int region6EXP;
    private int region7EXP;
    private int region89EXP;
    private int region10EXP;
    private int region11EXP;
    private int nebulaEXP;
    private int region13EXP;
    private int icecubeEXP;
    private int rupesEXP;
    private int petraEXP;
    private int juslynEXP;
    private int maceriaEXP;
    private int northEXP;

    public main_character(String nom, String[] descriptstr, Integer[] descriptpl, String[] greet, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, String magicType, int characterImgDrawable, int fightImg, String atkType, Weapon weapon, inventI item, Deck deck, stats_object thestats) {
        super(nom, descriptstr, descriptpl, gender, age, height, human, magicalAff, strength, charType, magicType, fightImg, atkType, weapon, item, deck, thestats);
        this.greetings = greet;
        this.characterImgDrawable = characterImgDrawable;
    }

    //overwrite
    public fighting_character getFightingCharacter() {return null;}
    public static_character getStaticCharacter() {return null;}
    public void setFightingCharacter(fighting_character character) {}
    public void setStaticCharacter(static_character character) {}

    public void setGreetings(String[] greet) {greetings = greet;}

    public int getCharacterImgDrawable() {
        return characterImgDrawable;
    }

    public String getGreeting(int index) {return greetings[index];}

    public int getGreetingsLength() {return greetings.length;}

    public int getRankEXP() {
        return rankEXP;
    }
    public void setRankEXP(int rankEXP) {
        this.rankEXP = rankEXP;
    }

    //get regionEXP
    //
    public int getVenelandEXP() {
        return venelandEXP;
    }
    public int getPietasEXP() {
        return pietasEXP;
    }
    public int getHdrEXP() {
        return hdrEXP;
    }
    public int getStonesEXP() {
        return stonesEXP;
    }
    public int getRegion6EXP() {
        return region6EXP;
    }
    public int getRegion7EXP() {
        return region7EXP;
    }
    public int getRegion89EXP() {
        return region89EXP;
    }
    public int getRegion10EXP() {
        return region10EXP;
    }
    public int getRegion11EXP() {
        return region11EXP;
    }
    public int getNebulaEXP() {
        return nebulaEXP;
    }
    public int getRegion13EXP() {
        return region13EXP;
    }
    public int getIcecubeEXP() {
        return icecubeEXP;
    }
    public int getRupesEXP() {
        return rupesEXP;
    }
    public int getPetraEXP() {
        return petraEXP;
    }
    public int getJuslynEXP() {
        return juslynEXP;
    }
    public int getMaceriaEXP() {
        return maceriaEXP;
    }
    public int getNorthEXP() {
        return northEXP;
    }

    //set regionEXP
    //
    public void setVenelandEXP(int venelandEXP) {
        this.venelandEXP = venelandEXP;
    }
    public void setPietasEXP(int pietasEXP) {
        this.pietasEXP = pietasEXP;
    }
    public void setStonesEXP(int stonesEXP) {
        this.stonesEXP = stonesEXP;
    }
    public void setHdrEXP(int hdrEXP) {
        this.hdrEXP = hdrEXP;
    }
    public void setRegion6EXP(int region6EXP) {
        this.region6EXP = region6EXP;
    }
    public void setRegion7EXP(int region7EXP) {
        this.region7EXP = region7EXP;
    }
    public void setRegion89EXP(int region89EXP) {
        this.region89EXP = region89EXP;
    }
    public void setRegion10EXP(int region10EXP) {
        this.region10EXP = region10EXP;
    }
    public void setRegion11EXP(int region11EXP) {
        this.region11EXP = region11EXP;
    }
    public void setNebulaEXP(int nebulaEXP) {
        this.nebulaEXP = nebulaEXP;
    }
    public void setRegion13EXP(int region13EXP) {
        this.region13EXP = region13EXP;
    }
    public void setIcecubeEXP(int icecubeEXP) {
        this.icecubeEXP = icecubeEXP;
    }
    public void setRupesEXP(int rupesEXP) {
        this.rupesEXP = rupesEXP;
    }
    public void setPetraEXP(int petraEXP) {
        this.petraEXP = petraEXP;
    }
    public void setJuslynEXP(int juslynEXP) {
        this.juslynEXP = juslynEXP;
    }
    public void setMaceriaEXP(int maceriaEXP) {
        this.maceriaEXP = maceriaEXP;
    }
    public void setNorthEXP(int northEXP) {
        this.northEXP = northEXP;
    }

    public String toString() {return "main_character";}
}
