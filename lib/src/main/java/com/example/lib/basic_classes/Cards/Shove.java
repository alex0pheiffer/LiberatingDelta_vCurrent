package com.example.lib.basic_classes.Cards;

import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.battle_character;

public class Shove extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Shove() {
        super("Shove", 0, false, false, false, false, 1, 0, 30);
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