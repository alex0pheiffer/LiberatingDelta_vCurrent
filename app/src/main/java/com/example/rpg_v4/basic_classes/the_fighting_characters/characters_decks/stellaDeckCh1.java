package com.example.rpg_v4.basic_classes.the_fighting_characters.characters_decks;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Cards.BoldTackle;
import com.example.rpg_v4.basic_classes.Cards.StellaSpark;
import com.example.rpg_v4.basic_classes.Deck;

import java.util.ArrayList;

public class stellaDeckCh1 extends Deck {

    private ArrayList<Card> the_cards;

    public stellaDeckCh1() {
        super("Stella_1",null);

        //arraylist
        the_cards = new ArrayList<Card>();
        //adding the cards
        Card card1 = new BoldTackle();
        the_cards.add(card1);
        Card card2 = new BoldTackle();
        the_cards.add(card2);
        Card card3 = new BoldTackle();
        the_cards.add(card3);
        Card card4 = new BoldTackle();
        the_cards.add(card4);
        Card card5 = new BoldTackle();
        the_cards.add(card5);
        Card card6 = new BoldTackle();
        the_cards.add(card6);
        Card card7 = new BoldTackle();
        the_cards.add(card7);
        Card card8 = new BoldTackle();
        the_cards.add(card8);
        Card card9 = new StellaSpark();
        the_cards.add(card9);
        Card card10 = new StellaSpark();
        the_cards.add(card10);
        Card card11 = new StellaSpark();
        the_cards.add(card11);
        Card card12 = new StellaSpark();
        the_cards.add(card12);
        Card card13 = new StellaSpark();
        the_cards.add(card13);
        Card card14 = new StellaSpark();
        the_cards.add(card14);
        Card card15 = new StellaSpark();
        the_cards.add(card15);
        Card card16 = new StellaSpark();
        //make my deck
        for (Card n : the_cards) {
            this.addCard(n);
        }
    }
}
