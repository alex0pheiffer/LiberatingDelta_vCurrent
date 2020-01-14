package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.battle_character;

public class Splash extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Splash() {
        super("Splash", 0, false, false, false, false, 5, 0, 100);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println("Returns .5 * mAtk or 0");

        int amt = (int)(user.getMatk()*.5);
        if (user.getType() != Characters.getMType(0)) amt = target.hitWMagic(amt,user.getType());
        else amt = 0;

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
