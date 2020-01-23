package com.example.rpg_v4.basic_classes;

public abstract class Characters {
    private String name;
    private String[] description_str;
    private Integer[] description_pl;
    private String gender;      //Female or Male or Neither
    private int age;
    private int height;         //in centimeters
    private boolean isHuman;
    private int magicalAffinity;
    private int strength;
    private String charType;
    private String magicType;
    private final static String[] characterTypes = {"Human","DragonA","DragonB","DragonC","DragonD","DragonE","DragonF","DragonG","DragonH","DragonI","DragonJ","DragonK","DragonL","DragonM","DragonN","DragonO","DragonP","Mage","Witch","Animal"};
    private final static String[] magicTypes = {"None", "Fire", "Water", "Land", "Air", "???"};

    public Characters(String nom, String[] descript_str, Integer[] descript_pl, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, String magicType) {
        this.name = nom;
        this.description_str = descript_str;
        this.description_pl = descript_pl;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.isHuman = human;
        this.magicalAffinity = magicalAff;
        this.strength = strength;
        this.charType = charType;
        this.magicType = magicType;
    }

    public static String getAType(int index) {
        return characterTypes[index];
    }

    public static String getMType(int index) {
        return magicTypes[index];
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getMagicalAffinity() {
        return magicalAffinity;
    }

    public int getStrength() {
        return strength;
    }

    public boolean getIsHuman() {
        return isHuman;
    }

    public String getCharType() {
        return charType;
    }

    public String getMagicType() { return magicType; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public void setMagicType(String type) { this.magicType = type; }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public void setMagicalAffinity(int magicalAffinity) {
        this.magicalAffinity = magicalAffinity;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
