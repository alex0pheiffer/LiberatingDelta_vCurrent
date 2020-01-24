package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.stats_object;

public class Distract extends Card {

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public Distract() {
        super("Distract", R.drawable.distract_card, false, false, false, false, 1, 2, 10);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println(target.getNom()+" has lost a ( 1 ) turn!");

        target.addEffectTodo(this, 1);
    }

    public void preformEffectTodo(battle_character target) {

        System.out.println("Turn is skipped.");

        target.setTurnSkip(true);
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
        return "Opponent will lose a turn.\n"+
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
