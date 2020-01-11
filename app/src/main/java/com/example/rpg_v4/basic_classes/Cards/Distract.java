package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;

public class Distract extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Distract() {
        super("Distract", 0, false, false, false, false, 1, 0, 10);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

    }

    public void preformEffectTodo(battle_character target) {

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

    public int getNumInstance() {
        return numInstance;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
