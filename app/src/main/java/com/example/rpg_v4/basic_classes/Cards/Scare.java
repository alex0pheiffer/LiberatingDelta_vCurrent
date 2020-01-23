package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class Scare extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Scare() {
        super("Scare", R.drawable.scare_card, false, false, false, false, 0, 0, 20);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println(target.getNom()+"has decreased defense ( P ) by 10% for 3 turns.");
        preformEffectTodo(target);
        target.addEffectTodo(this, 2);

    }

    public void preformEffectTodo(battle_character target) {

        System.out.println(target.getNom()+"has has decreased defense ( P ) by 10%.");

        target.getEffectStats().subtractStats(new stats_object(0,0,0, 0, 0,10,0,0,0,0));
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
