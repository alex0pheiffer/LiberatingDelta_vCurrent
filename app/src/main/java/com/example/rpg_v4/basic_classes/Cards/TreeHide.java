package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class TreeHide extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public TreeHide() {
        super("Tree Hide", 0, false, false, false, false, 1, 0, 50);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println(target.getNom()+"has increased evasiveness ( P,M ) by 20% for 3 turns.");
        preformEffectTodo(target);
        user.addEffectTodo(this, 1);

    }

    public void preformEffectTodo(battle_character target) {
        System.out.println(target.getNom() + "has increased evasiveness ( P,M ) by 20%.");

        target.getEffectStats().addStats(new stats_object(0, 0, 0, 2000, 2000, 0, 0, 0, 0, 0));
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
        return "↑ Self's mEva and pEva by 20% for 3 turns.\n"+
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
