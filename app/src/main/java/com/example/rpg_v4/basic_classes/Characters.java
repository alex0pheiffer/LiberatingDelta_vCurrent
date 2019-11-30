package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.PL;

public abstract class Characters {
    private String name;
    private String[] description_str;
    private Integer[] description_pl;
    private String[] greeting;
    private String gender;
    private int age;
    private int height;
    private boolean isHuman;
    private int magicalAffinity;
    private int strength;
    private String charType;
    private final static String[] characterTypes = {"Human","DragonA","DragonB","DragonC","DragonD","DragonE","DragonF","DragonG","DragonH","DragonI","DragonJ","DragonK","DragonL","DragonM","DragonN","DragonO","DragonP","Mage","Witch"};

    public Characters(String nom, String[] descript_str, Integer[] descript_pl, String[] greet, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType) {
        this.name = nom;
        this.description_str = descript_str;
        this.description_pl = descript_pl;
        this.greeting = greet;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.isHuman = human;
        this.magicalAffinity = magicalAff;
        this.strength = strength;
        this.charType = charType;
    }

    public static String getAType(int index) {
        return characterTypes[index];
    }

    public String getName() {
        return name;
    }

    public String[] getGreeting() {
        return greeting;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGreeting(String[] greeting) {
        this.greeting = greeting;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

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
