package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.basic_classes.Cards.sudoCard;

import java.util.ArrayList;

public interface abstractDeck{

    //returns the deck's name
    String getNom();

    //only sorts the very last card...
    int alphabetizeDeck();

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    int binarySearch(Card card, int startIndex, int endIndex);

    int numCardInstance(Card card);

    //when bringing a new card into the deck  ---- returns the new index of the sudoCard, will usually be ignored
    int addCard(Card card);
        //todo replace with a mitigation...pop up window...
        //todo also probably check that the decksize isn't full before beginning a mission...


    //when removing a card from your inventory...you will no longer own this card
    void removeCard(Card card);

    //for accessing the allCards list...
    Card getCard(int index);

    //note: when you add a new card, it will give you an index... but it will change if you add another card so be careful
    sudoCard getSudoCard(int index);

    sudoCard getSudoCard(Card card);

    //returns the amount of sudocard bundles
    int getSudoCardAmt();

    int getSudoCardIndex(Card card);

    //updates the total amount individual cards: cardAmt
    int getCardAmt();
}
