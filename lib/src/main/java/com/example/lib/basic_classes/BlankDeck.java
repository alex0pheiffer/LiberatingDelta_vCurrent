package com.example.lib.basic_classes;

import com.example.lib.basic_classes.Cards.sudoCard;

import java.util.ArrayList;

public class BlankDeck implements abstractDeck{

    private String name;
    public ArrayList<sudoCard> alphabetcards;
    public ArrayList<Card> allCards;   //this is in no particular order. its just a list of all the cards.
    public int cardAmt;
    private final int MAX_DECK_SIZE = 200;

    public BlankDeck() {
        this.name = "BlankDeck";
        this.alphabetcards = new ArrayList<sudoCard>();
        this.allCards = new ArrayList<Card>();
        cardAmt = 0;
    }


    public String getNom() {
        return name;
    }

    //only sorts the very last card...
    public int alphabetizeDeck() {
        boolean stillLarger = true;
        int i = alphabetcards.size()-1;
        int newIndex = 0;
        int j = i;
        stillLarger = true;
        while (stillLarger) {
            j--;
            if (alphabetcards.get(j).getCard(0).toString().compareTo(alphabetcards.get(i).getCard(0).toString()) <= 0) {
                stillLarger = false;
                newIndex = j+1;
            }
            else if (j==0) {
                stillLarger = false;
                newIndex = 0;
            }
        }
        alphabetcards.add(newIndex,alphabetcards.get(i));
        alphabetcards.remove(i+1);
        return newIndex;
    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    public int binarySearch(Card card, int startIndex, int endIndex) {
        String x = card.toString();
        if (endIndex >= startIndex) {
            int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
            // If the element is present at the
            // middle itself
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) > 0)
                return binarySearch(card, startIndex, mid - 1);

            // Else the element can only be present
            // in right subarray
            return binarySearch(card, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public int numCardInstance(Card card) {
        int index = binarySearch(card,0,alphabetcards.size()-1);
        if (index != -1) {
            return alphabetcards.get(index).getAmount();
        }
        else {
            return 0;
        }
    }

    //will be overwritten by real decks
    //when bringing a new card into the deck  ---- returns the new index of the sudoCard, will usually be ignored
    public int addCard(Card card) {
        //todo replace with a mitigation...pop up window...
        //todo also probably check that the decksize isn't full before beginning a mission...
        if (cardAmt++ >= MAX_DECK_SIZE)
            throw new RuntimeException("adding too many cards...");

        //check to make sure the deck isn't empty
        int newIndex = -1;
        if(alphabetcards.size() == 0) {
            alphabetcards.add(new sudoCard(card));
            newIndex = 0;
        }
        else {
            //try to find the sudocard deck for this card
            int index = binarySearch(card, 0, alphabetcards.size() - 1);
            if (index != -1) {
                alphabetcards.get(index).addCard(card);
                newIndex = index;
            } else {
                alphabetcards.add(new sudoCard(card));
                newIndex = alphabetizeDeck();
                //throw new RuntimeException("CHECK!!");
            }
        }
        cardAmt++;
        allCards.add(card);
        return newIndex;
    }

    //will be overwritten by real decks
    //when removing a card from your inventory...you will no longer own this card
    public void removeCard(Card card) {
        int index = binarySearch(card, 0, alphabetcards.size() - 1);
        //find the sudocard that matches this card
        if (index != -1) {
            throw new RuntimeException("error: card "+card.getNom()+" DNE in allCards");
        }
        else {
            if (alphabetcards.get(index).getAmount() < 2 ) {
                //there is only one (or 0) instances of this card in the deck...remove the sudoCard
                alphabetcards.remove(index);
            }
            else {
                boolean cardExists = false;
                for (int i = 0; i < alphabetcards.get(index).getAmount(); i++) {
                    if (alphabetcards.get(index).getCard(i).getInstanceName().equals(card.getInstanceName())) {
                        //the card is the same as this card
                        alphabetcards.get(index).removeCard(card);
                        cardExists = true;
                    }
                }
                if (!cardExists) {
                    throw new RuntimeException("card instance "+card.getInstanceName()+" DNE in it's sudocard");
                }
            }
        }
        cardAmt--;
        allCards.remove(card);
    }

    //for accessing the allCards list...
    public Card getCard(int index) {
        return allCards.get(index);
    }

    //note: when you add a new card, it will give you an index... but it will change if you add another card so be careful
    public sudoCard getSudoCard(int index) {
        return alphabetcards.get(index);
    }

    public sudoCard getSudoCard(Card card) {
        int index = binarySearch(card,0,alphabetcards.size()-1);
        if (index != -1) {
            return alphabetcards.get(index);
        }
        else return null;
    }

    //returns the amount of sudocard bundles
    public int getSudoCardAmt() {
        return alphabetcards.size();
    }

    public int getSudoCardIndex(Card card) {
        return binarySearch(card,0,alphabetcards.size()-1);
    }


    //only applicable to BlankDeck
    //updates the total amount individual cards: cardAmt
    public void updateCardAmt() {
        int amt = 0;
        for (int i = 0; i < alphabetcards.size(); i++) {
            amt = amt+alphabetcards.get(i).getAmount();
        }
        cardAmt = amt;
    }

    //updates the total amount individual cards: cardAmt
    public int getCardAmt() {
        return allCards.size();
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
