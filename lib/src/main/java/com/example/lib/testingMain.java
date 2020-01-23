package com.example.lib;


import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.Cards.Distract;
import com.example.lib.basic_classes.Cards.DiveLeft;
import com.example.lib.basic_classes.Cards.RockToss;
import com.example.lib.basic_classes.Cards.Shove;
import com.example.lib.basic_classes.Cards.Splash;
import com.example.lib.basic_classes.Cards.Struggle;
import com.example.lib.basic_classes.Deck;
import com.example.lib.basic_classes.battle_character;
import com.example.lib.basic_classes.fighting_character;
import com.example.lib.basic_classes.main_character;
import com.example.lib.basic_classes.the_MCs.Delta;
import com.example.lib.basic_classes.the_MCs.Katherine;
import com.example.lib.basic_classes.the_MCs.Vivian;

import java.util.ArrayList;

public class testingMain {

    public static void main(String[] args) {
        ArrayList<fighting_character> characters = new ArrayList<fighting_character>();
        ArrayList<Boolean> allies = new ArrayList<Boolean>();

        main_character mc = new Vivian();
        //mc.setHealth(200);
        characters.add(mc);
        fighting_character enemy = new Delta();
        characters.add(enemy);
        //fighting_character enemy2 = new Katherine();
        //characters.add(enemy2);
        allies.add(true); allies.add(false); //allies.add(false);

        Deck myDeck = new Deck("myDeck", mc.getName());
        mc.setDeck(myDeck);
        myDeck.addCard(new RockToss());
        myDeck.addCard(new RockToss());
        myDeck.addCard(new RockToss());
        myDeck.addCard(new Shove());
        myDeck.addCard(new Shove());
        myDeck.addCard(new Shove());
        myDeck.addCard(new Struggle());
        myDeck.addCard(new Struggle());
        myDeck.addCard(new Struggle());
        myDeck.addCard(new Splash());
        myDeck.addCard(new Distract());
        myDeck.addCard(new Splash());
        myDeck.addCard(new Distract());
        myDeck.addCard(new Splash());
        myDeck.addCard(new Distract());

        Deck hisDeck = new Deck("hisDeck", enemy.getName());
        enemy.setDeck(hisDeck);
        //enemy2.setDeck(hisDeck);
        hisDeck.addCard(new RockToss());
        hisDeck.addCard(new RockToss());
        hisDeck.addCard(new RockToss());
        hisDeck.addCard(new RockToss());
        hisDeck.addCard(new RockToss());
        hisDeck.addCard(new Shove());
        hisDeck.addCard(new Shove());
        hisDeck.addCard(new Shove());
        hisDeck.addCard(new Struggle());
        hisDeck.addCard(new Struggle());
        hisDeck.addCard(new Struggle());
        hisDeck.addCard(new Splash());
        hisDeck.addCard(new Splash());
        hisDeck.addCard(new Splash());

        battleClass theBattle = new battleClass(characters, allies);
        theBattle.battle();

    }


}
