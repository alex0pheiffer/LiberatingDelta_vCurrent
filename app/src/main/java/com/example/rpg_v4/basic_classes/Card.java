package com.example.rpg_v4.basic_classes;

public abstract class Card {

    private String nom;
    private int cardImg;
    private boolean isWeaponSpecific;
    private boolean isComboSpecific;
    private boolean isCharacterSpecific;
    private boolean alliesSupport;
    private int targetCharAmt;
    private int weight; //how powerful the card is: 0-5
    private int wait;   //"points" the char must wait after card use

    public Card(String name, int img, boolean weapon, boolean combo, boolean character, boolean allies, int targetAmt, int weight, int wait) {
        this.nom = name;
        this.cardImg = img;
        this.isWeaponSpecific = weapon;
        this.isComboSpecific = combo;
        this.isCharacterSpecific = character;
        this.alliesSupport = allies;
        this.targetCharAmt = targetAmt;
        this.weight = weight;
        this.wait = wait;
    }

    public String getNom() {
        return nom;
    }

    public int getWait() {
        return wait;
    }

    public int getCardImg() {
        return cardImg;
    }

    public int getWeight() {
        return weight;
    }

    public boolean getAliesSupport() {
        return alliesSupport;
    }

    public int getTargetCharAmt() {
        return targetCharAmt;
    }

    public boolean getIsWeaponSpecific() {
        return isWeaponSpecific;
    }

    public boolean getIsComboSpecific() {
        return isComboSpecific;
    }

    public boolean getIsCharcterSpecific() {
        return isCharacterSpecific;
    }

    public void preformCard(fighting_character user, fighting_character target) {}

    public String toString() {return "Card";}
}
