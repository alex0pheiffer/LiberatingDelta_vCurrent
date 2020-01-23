package com.example.rpg_v4.basic_classes.Cards;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.battle_character;

public class StellaSpark extends Card {


    /*
    A card made for Stella
     */

    private static int deckAmt;
    private String instanceName;
    private static int numInstance = 0;

    public StellaSpark() {
        super("Stella Spark", R.drawable.stellaspark_card, false, true, false, false, 1, 2, 25);
        instanceName = getClass().getSimpleName()+" "+numInstance;
        numInstance++;
    }

    public void preformCard(battle_character user, battle_character target) {

        System.out.println("Returns 1 * mAtk or 0");

        int amt = (int)(user.getMatk());
        if (user.getType() == Characters.getMType(1)) amt = target.hitWMagic(amt,user.getType());
        else amt = 0;

        System.out.println(target.getNom()+" lost "+amt+" hp.");
        System.out.println(target.getNom()+" : "+target.getHP());
    }

    public void preformEffectTodo(battle_character target) {
        //nothing to do
    }

    public String getComboSpecific() {
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
