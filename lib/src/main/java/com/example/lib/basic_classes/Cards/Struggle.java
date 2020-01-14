package com.example.lib.basic_classes.Cards;

import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.Characters;
import com.example.lib.basic_classes.battle_character;

public class Struggle extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Struggle() {
        super("Struggle", 0, false, false, false, false, 1, 0, 50);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println("Returns .5 * pAtk");

        int amt = (int)(user.getPatk()*.5);
        amt = target.hitWPhysical(amt);

        System.out.println(target.getNom()+" lost "+amt+" hp.");
        System.out.println(target.getNom()+" : "+target.getHP());

    }

    public void preformEffectTodo(battle_character target) {
        //none
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
