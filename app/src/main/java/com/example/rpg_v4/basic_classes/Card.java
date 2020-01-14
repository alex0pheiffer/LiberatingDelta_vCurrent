package com.example.rpg_v4.basic_classes;

public abstract class Card {

    private String nom;
    private String instanceName;
    private static int deckAmt;        //number of decks this TYPE of card is in
    private int cardImg;
    private boolean isWeaponSpecific;
    private boolean isComboSpecific;
    private boolean isCharacterSpecific;
    private boolean alliesSupport;
    private int targetCharAmt;
    private int weight; //how powerful the card is: 0-5
    private int wait;   //"points" the char must wait after card use
    private final String[] charequipers = {"Vivian", "Katherine", "Delta"};

    public Card(String name, int img, boolean weapon, boolean combo, boolean character, boolean allies, int targetAmt, int weight, int wait) {
        this.nom = name;
        this.instanceName = this.getClass().getSimpleName(); //instanceName must be overwritten for subclasses!! and concat instanceNum to end.
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

    public String getInstanceName() {
        return instanceName;
    }

    public static int getDeckAmt() {
        return deckAmt;
    }

    public static void addDeckAmt() {
        deckAmt++;
    }

    public static void removeDeckAmt() {
        deckAmt--;
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

    public String getSpecificCharacter() {
        if (isCharacterSpecific) {
            System.out.println("Warning: This method should be overwritten");
            return null;
        }
        else {
            return null;
        }
    }

    public Weapon getSpecificWeapon() {
        if (isWeaponSpecific) {
            System.out.println("Warning: This method should be overwritten");
            return null;
        }
        else {
            return null;
        }
    }

    //should be over written
    public void preformCard(battle_character user, battle_character target) {}

    //should be overwritten
    public void preformEffectTodo(battle_character target) {}

    public String toString() {return this.getClass().getSimpleName();}
}
