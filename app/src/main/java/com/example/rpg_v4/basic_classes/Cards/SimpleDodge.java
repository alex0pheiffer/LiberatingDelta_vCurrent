package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class SimpleDodge extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public SimpleDodge() {
        super("Simple Dodge", R.drawable.simpledodge_card, false, false, false, false, 0, 0, 50);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println(target.getNom()+"has increased evasiveness ( P ) by 40% and ( M ) by 20% for 2 turns.");
        preformEffectTodo(target);
        user.addEffectTodo(this, 1);

    }

    public void preformEffectTodo(battle_character target) {

        System.out.println(target.getNom()+"has increased evasiveness ( P ) by 40% and ( M ) by 20%.");

        target.getEffectStats().addStats(new stats_object(0,0,0, 4000, 2000,0,0,0,0,0));
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

    public String getInfo() {
        return "↑ pEva of Self by 40% and mEva by 20% for 2 turns.\n"+
                "Targets: "+getTargetCharAmt()+"\tWeight: "+getWeight()+"\tWait: "+getWait();
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
