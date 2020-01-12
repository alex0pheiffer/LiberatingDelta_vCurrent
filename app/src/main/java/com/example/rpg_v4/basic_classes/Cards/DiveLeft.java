package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.stats_object;

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
        user.addEffectTodo(this, 1);

    }

    public void preformEffectTodo(battle_character target, stats_object effect_stats) {
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
