package com.example.lib.basic_classes.Cards;

import com.example.lib.basic_classes.Card;

import java.util.ArrayList;

public class sudoCard {
    private int amount;
    private ArrayList<Card> cards;

    public sudoCard(Card card) {
        this.cards = new ArrayList<Card>();
        this.addCard(card);
    }

    public void addCard(Card card) {
        if (this.cards.size() != 0) {
            if (card.getNom().equals(this.cards.get(0).getNom())) {
                //if card is of the same type as the other cards
                this.cards.add(card);
                this.amount++;
            }
            else {
                throw new RuntimeException("sudoCard mismatch: trying to add "+card.getNom()+" to "+this.cards.get(0).getNom());
            }
        }
        else {
            this.cards.add(card);
            this.amount = 1;
        }
    }

    public void removeCard(Card card) {
        boolean cardExists = false;
        if (this.cards.size() != 0) {
            for (int n = 0; n < this.cards.size(); n++) {
                if (this.cards.get(n).getInstanceName().equals(card.getInstanceName())) {
                    cardExists = true;
                    this.cards.remove(n);
                    this.amount--;
                }
            }
            if (!cardExists) {
                throw new RuntimeException("card "+card.getInstanceName()+" doesn't exist in sudocard deck");
            }
        }
        else {
            throw new RuntimeException("cannot remove card "+card.getInstanceName()+" from sudocards when sudocards are empty");
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amt) {
        this.amount = amt;
    }

    public Card getCard(int index) {
        return this.cards.get(index);
    }
}