package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;

public class BoldTackle extends Card {

    /*
    A card made for Stella
     */

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public BoldTackle() {
        super("Bold Tackle", R.drawable.boldtackle_card, false, true, false, false, 1, 1, 10);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println("Returns .7 * aAtk");

        int amt = (int)(user.getPatk()*.7);
        amt = target.hitWPhysical(amt);

        System.out.println(target.getNom()+" lost "+amt+" hp.");
        System.out.println(target.getNom()+" : "+target.getHP());
    }

    public void preformEffectTodo(battle_character target) {

        //nothing to do here
    }

    public String getSpecificCharacter() {
        return "Stella";
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
        return "Attack Opponent with .7*pAtk.\n"+
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
