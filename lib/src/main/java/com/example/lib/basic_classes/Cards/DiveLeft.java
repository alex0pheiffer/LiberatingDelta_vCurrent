package com.example.lib.basic_classes.Cards;

import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.battle_character;
import com.example.lib.basic_classes.stats_object;

public class DiveLeft extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public DiveLeft() {
        super("Dive Left", 0, false, false, false, false, 0, 0, 50);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println(target.getNom()+"has increased evasiveness ( P,M ) by 40% for 2 turns.");
        preformEffectTodo(target);
        user.addEffectTodo(this, 1);

    }

    public void preformEffectTodo(battle_character target) {

        System.out.println(target.getNom()+"has increased evasiveness ( P,M ) by 40%.");

        target.getEffectStats().addStats(new stats_object(0,0,0, 4000, 4000,0,0,0,0,0));
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
