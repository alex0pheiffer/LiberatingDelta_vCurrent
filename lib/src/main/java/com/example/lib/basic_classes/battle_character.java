package com.example.lib.basic_classes;

import java.util.ArrayList;

public class battle_character {

    private boolean isMC;
    private boolean isAlly;
    private fighting_character thisCharacter;
    private stats_object item_equip_stats;
    private stats_object weapon_equip_stats;
    private Deck fullDeck;
    private Deck remainingDeck;
    private ArrayList<Card> hand;

    private int currentHP;
    private int timeToTurn;
    private int weightPoints;
    private ArrayList<Card> cardEffectTodo;
    private ArrayList<Integer> waitEffectTodo;

    private boolean isDead;
    private boolean turnSkip;
    private stats_object stats_static;
    private stats_object effect_stats;
    private stats_object stats;

    public battle_character(fighting_character thisCharacter, int timeToTurn, boolean ally) {
        this.thisCharacter = thisCharacter;
        this.currentHP = thisCharacter.getStats().getHealth();
        this.isMC = main_character.class.isAssignableFrom(thisCharacter.getClass());
        this.isAlly = ally;
        this.item_equip_stats = thisCharacter.getItemStats();
        this.weapon_equip_stats = thisCharacter.getWeaponStats();
        this.fullDeck = thisCharacter.getDeck().copy();
        this.remainingDeck = this.fullDeck.copy();
        this.hand = new ArrayList<Card>();

        this.timeToTurn = timeToTurn;
        this.weightPoints = 6;
        this.cardEffectTodo = new ArrayList<Card>();
        this.waitEffectTodo = new ArrayList<Integer>();

        this.isDead=false;
        this.turnSkip = false;
        this.stats_static = thisCharacter.getStats();
        this.effect_stats = new stats_object(0,0,0,0,0,0,0,0,0,0);
    }

    public int getWait() {return timeToTurn;}
    public int reduceWait(int reduction) {
        timeToTurn -= reduction;
        return timeToTurn;
    }
    public int increaseWait(int increase) {
        timeToTurn += increase;
        return timeToTurn;
    }
    public int getWeightPoints() {return weightPoints;}
    public void setWeightPoints(int pts) {
        if (pts > 10) pts = 10;
        weightPoints = pts;
    }
    public boolean getIsDead() {return isDead;}
    public void setIsDead(boolean ded) {isDead = ded;}
    public boolean getTurnSkip() {return turnSkip;}
    public void setTurnSkip(boolean skip) {turnSkip = skip;}
    public boolean getIsAlly() {return isAlly;}

    public boolean isVolatile() {
        int vol = stats.getVolatility();
        int rand = (int)(Math.random()*100);
        if (vol >= rand) return true;
        else return false;
    }
    public void hitWMagic(int amt, String type) {
        //when the attack is purely magical
        int change = 0;
        if (type.equals(stats_object.getType(0))) {
            //Fire
            change = stats.getFireDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(1))) {
            //Water
            change = stats.getWaterDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(2))) {
            //Land
            change = stats.getLandDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(3))) {
            //Air
            change = stats.getAirDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(4))) {
            //Scatter
            int def = (stats.getAirDefense()+stats.getFireDefense()+stats.getLandDefense()+stats.getWaterDefense())/4;
            change = def*amt/100;
        }
        reduceHealth(change);
    }
    public void hitWPhysical(int amt) {
        //when the attack is purely physical
        int change = stats.getADefense()*amt/100;
        reduceHealth(change);
    }
    public void hitWBoth(int amt, int percentM, String type) {
        //when the attack is both magical and physical... ex a magic sword
        int Mhit = amt*percentM/100;
        int Ahit = amt - Mhit;
        hitWMagic(Mhit, type);
        hitWPhysical(Ahit);
    }
    private void reduceHealth(int amt) {
        if (currentHP-amt <= 0) {
            currentHP = 0;
            setIsDead(true);
        }
        else {
            currentHP = currentHP-amt;
        }
    }

    public void removeWeapon() {
        thisCharacter.removeWeapon();
        this.weapon_equip_stats = null;
    }

    public void removeItem() {
        thisCharacter.removeItem();
        this.item_equip_stats = null;
    }

    public void shuffleDeck() {this.remainingDeck.shuffle();}

    public void resetDeck() {
        this.remainingDeck = this.fullDeck.copy();
        this.shuffleDeck();
    }

    //DOES NOT put the card in your hand
    public Card drawCard() {
        if (this.remainingDeck.getCardAmt() == 0) return null;
        Card pulled = this.remainingDeck.getCard(this.remainingDeck.getCardAmt()-1);
        this.remainingDeck.removeCard(pulled);
        return pulled;
    }

    //adds a given card to the user's deck
    //this shuffles the deck..
    public void addCardToDeck(Card card) {
        this.remainingDeck.addCard(card);
        this.shuffleDeck();
    }

    public void drawCardToHand() {
        Card tempCard;
        if (hand.size() < 5) {
            tempCard = drawCard();
            if (tempCard != null) {
                hand.add(tempCard);
            }
        }
    }

    public int getHand() {return hand.size();}
    public Card getHandCard(int index) {
        if ( index < hand.size()) {
            return hand.get(index);
        }
        else return null;
    }

    //returns if the target was killed
    public boolean useCard(Card card, battle_character target) {
        card.preformCard(this, target);
        hand.remove(card);
        drawCardToHand();
        if (target.getIsDead()) return true;
        else return false;
    }

    public void addEffectTodo(Card effect, int turnDuration) {
        cardEffectTodo.add(effect);
        waitEffectTodo.add(turnDuration);
    }

    public void applyEffectTodo() {
        resetEffectStats();
        for (int i = 0; i < cardEffectTodo.size(); i++) {
            cardEffectTodo.get(i).preformEffectTodo(this, effect_stats);
            waitEffectTodo.set(i, waitEffectTodo.get(i) - 1);
            if (waitEffectTodo.get(i) == 0) {
                cardEffectTodo.remove(i);
                waitEffectTodo.remove(i);
                i--;
            }
        }
    }

    public void resetEffectStats() {
        effect_stats.setHealth(0);
        effect_stats.setEvasiveA(0);
        effect_stats.setEvasiveM(0);
        effect_stats.setAttackA(0);
        effect_stats.setADefense(0);
        effect_stats.setFireDefense(0);
        effect_stats.setWaterDefense(0);
        effect_stats.setAirDefense(0);
        effect_stats.setLandDefense(0);
        effect_stats.setVolatility(0);
    }

    public void fillHand() {
        while (hand.size() < 5) {
            drawCardToHand();
        }
    }

    public void clearHand() {
        for (int i = 0; i < hand.size(); i++) {
            hand.set(i,null);
        }
    }
}
