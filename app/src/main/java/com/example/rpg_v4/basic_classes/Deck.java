package com.example.rpg_v4.basic_classes;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Deck extends inventI {

    private String instanceName;

    private ArrayList<Card> cards;
    private ArrayList<Card> alphabetcards;
    private int cardAmt;
    private int totalWeight;
    private String charequip;   //what char can validly hold this deck
    private String charHolding; //what character __has__ this deck equipped?
    private boolean isValid;
    private boolean isWeaponSpec;
    private ArrayList<specWeapon> specificWeapons; //the weapons that cards may refer to in this deck
    private class specWeapon {
        private int cardAmt;
        private Weapon weapon;

        public specWeapon(Weapon weapon) {
            this.weapon = weapon;
            cardAmt = 1;
        }

        public void addCard() {
            cardAmt++;
        }

        public int removeCard() {
            cardAmt--;
            return cardAmt;
        }

        public int getCardAmt() {
            return this.cardAmt;
        }

        public Weapon getWeapon() {
            return this.weapon;
        }
    }
    private static int numInstance = 0;
    private final int MAX_CARD_AMT = 3;
    private final int MAX_DECK_SIZE = 30;
    private final int MAX_WEIGHT = 50;

    private final String[] charequipers = {"All", "Vivian", "Katherine", "Delta", "Invalid"};

    public Deck(String name, ArrayList<Card> cards, @Nullable String charHolding) {
        super(name, false, null);
        this.cards = cards;
        this.charHolding = charHolding;
        this.alphabetcards = new ArrayList<Card>();
        alphabetizeDeck(true);
        this.cardAmt = cards.size();
        this.isWeaponSpec = checkWeaponSpec();
        this.charequip = checkCharEquip();
        this.totalWeight = getTotalWeight();
        this.instanceName = getClass().getName()+" "+numInstance;
        numInstance++;
    }

    public void alphabetizeDeck(boolean first) {
        //insertion sort
        boolean stillLarger = true;
        int j;
        int newIndex = 0;
        if (first) {
            alphabetcards.add(cards.get(0));
        }
        for (int i=1; i < cardAmt;i++) {
            if (first) {
                alphabetcards.add(cards.get(i));
            }
            j = i;
            stillLarger = true;
            while (stillLarger) {
                j--;
                if (alphabetcards.get(j).getInstanceName().compareTo(alphabetcards.get(i).getInstanceName()) > 0) {
                    stillLarger = false;
                    newIndex = j+1;
                }
                if (j==0) {
                    stillLarger = false;
                    newIndex = 0;
                }
            }
            alphabetcards.add(newIndex,alphabetcards.get(i));
            alphabetcards.remove(i+1);
        }
    }

    //tells you the amount of this card in the deck. if the card is in the deck, it will be counted in the value
    //if the card is NOT in the deck yet, it will not be counted in the count
    public int numCardInstance(Card card) {
        int amount = 0;
        int location = binarySearch(card, 0, alphabetcards.size() - 1);
        if (location == 0) {
            return amount;
        }
        String x = card.toString();
        while (alphabetcards.get(location-1).toString().compareTo(x) == 0) {
            //the card before this is also the same card
            location--;
        }
        while (alphabetcards.get(location).toString().compareTo(x) == 0) {
            amount++;
            location++;
        }
        return amount;

    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    private int binarySearch(Card card, int startIndex, int endIndex) {
        String x = card.toString();
        if (endIndex >= startIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;

            // If the element is present at the
            // middle itself
            if (alphabetcards.get(mid).toString().compareTo(x) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphabetcards.get(mid).toString().compareTo(x) < 0)
                return binarySearch(card, startIndex, mid - 1);

            // Else the element can only be present
            // in right subarray
            return binarySearch(card, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public String checkCharEquip() {
        String belongs = charequipers[0];
        for (Card card : this.cards) {
            if (card.getIsCharcterSpecific()) {
                if (!belongs.equals(card.getSpecificCharacter())) {
                    if (belongs.equals(charequipers[0])) {
                        belongs = card.getSpecificCharacter();
                    } else {
                        return charequipers[4];
                    }
                }
            }
        }
        return belongs;
    }

    public String checkCharEquip(Card exceptionCard) {
        String belongs = charequipers[0];
        for (Card card : this.cards) {
            if (!card.getInstanceName().equals(exceptionCard.getInstanceName())) {
                if (card.getIsCharcterSpecific()) {
                    if (!belongs.equals(card.getSpecificCharacter())) {
                        if (belongs.equals(charequipers[0])) {
                            belongs = card.getSpecificCharacter();
                        } else {
                            return charequipers[4];
                        }
                    }
                }
            }
        }
        return belongs;
    }

    public void updateCharEquip(Card card, boolean adding) {
        if (card.getIsCharcterSpecific()) {
            if (adding && !this.charequip.equals(card.getSpecificCharacter())) {
                if (this.charequip.equals(charequipers[0])) {
                    this.charequip = card.getSpecificCharacter();
                } else {
                    this.charequip = charequipers[4];
                }
            }
            if (!adding && this.charequip.equals(card.getSpecificCharacter())) {
                if (this.charequip.equals(charequipers[0])) {
                    throw new RuntimeException("Card is CharSpec but Deck is not");
                } else {
                    charequip = checkCharEquip(card);
                }
            }
            else if(!adding && this.charequip.equals(charequipers[4])) {
                charequip = checkCharEquip(card);
            }
        }
    }

    public boolean checkWeaponSpec() {
        for (Card card : cards) {
            if (card.getIsWeaponSpecific())
                return true;
        }
        return false;
    }

    public void addWeaponSpec(Weapon weep) {
        if (specificWeapons == null) {
            specificWeapons = new ArrayList<specWeapon>();
            specificWeapons.add(new specWeapon(weep));
        }
        else {
            boolean bool = false;
            for (specWeapon a : specificWeapons) {
                if (a.getWeapon().getNom().equals(weep.getNom())) {
                    bool = true;
                    a.addCard();
                }
            }
            if (!bool) {
                specificWeapons.add(new specWeapon(weep));
            }
        }
    }

    public void removeWeaponSpec(Weapon weep) {
        if (specificWeapons == null) {
            throw new RuntimeException("Removing "+weep.getNom()+" from non-weapon-specific deck");
        }
        else {
            boolean bool = false;
            int index = 0;
            for (specWeapon a : specificWeapons) {
                if (a.getWeapon().getNom().equals(weep.getNom())) {
                    bool = true;
                    a.removeCard();
                    if (a.getCardAmt() == 0) {
                        specificWeapons.remove(index);
                    }
                }
                index++;
            }
            if (!bool) {
                throw new RuntimeException("Card's weapon DNE");
            }
        }
    }

    //when bringing a new card into the deck
    public void addCard(Card card) {
        //return true if the card is added,
        //return false if the card could not be added

        //update the charequip if its not already invalid
        if (!charequip.equals(charequipers[4]))
            updateCharEquip(card, true);

        //if the deck isnt invalid, find out if this card makes it invalid
        if (isValid) {
            if (charequip.equals(charequipers[4]))
                isValid = false;
            if (isValid && cards.size()+1>MAX_DECK_SIZE) {
                isValid = false;
            }
            if (isValid && totalWeight+card.getWeight() > MAX_WEIGHT) {
                isValid = false;
            }
            if (isValid && numCardInstance(card) >= MAX_CARD_AMT)
                isValid = false;
            if (isValid && card.getIsWeaponSpecific()) {
                Weapon cardWeapon = card.getSpecificWeapon();
                if (isWeaponSpec) {
                    if (!cardWeapon.getCharEquip().equals(charequip))
                        isValid = false;
                    else {
                        addWeaponSpec(cardWeapon);
                    }
                }
                else {
                    //all characters -> char of this weapon
                    if (charequip.equals(charequipers[0])) {
                        charequip = cardWeapon.getCharEquip();
                    }
                    //the card's specific weapon's specific character is >NOT< the same as the card's current specific character
                    else if (!card.getSpecificWeapon().getCharEquip().equals(charequip)) {
                        isValid = false;
                    }
                    isWeaponSpec = true;
                    addWeaponSpec(cardWeapon);
                }
            }
        }

        totalWeight=totalWeight+card.getWeight();
        cards.add(card);
        cardAmt++;
        alphabetcards.add(card);
        alphabetizeDeck(false);
    }

    //when removing a card from the deck
    public void removeCard(Card card) {
        //return true if the card is removed,
        //return false if the card could not be removed
        if (card.getIsWeaponSpecific()) {
            removeWeaponSpec(card.getSpecificWeapon());
        }
        if (card.getIsCharcterSpecific()) {
            updateCharEquip(card,false);
        }

        if (charequip.equals(charequipers[4])) {
            isValid = false;
        }
        if (isValid && cards.size() > MAX_DECK_SIZE) {
            isValid = false;
        }
        if (isValid && totalWeight > MAX_WEIGHT) {
            isValid = false;
        }
        if (isValid && numCardInstance(card)-1 > MAX_CARD_AMT) {
            isValid = false;
        }

        cards.remove(card);

        totalWeight=totalWeight-card.getWeight();
        cardAmt--;
    }

    public void updateCardAmt() {
        cardAmt = cards.size();
    }

    //todo? is this going to be a thing... order really doesnt matter?
    //when moving a card in the deck to a differnet position in the deck
    //public boolean moveCard(Card card, int location) {
    //    boolean moved = true;


    //}

    public static int getNumInstance() {
        return numInstance;
    }

    public int getTotalWeight() {
        int total = 0;
        for (int n=0; n<cards.size(); n++) {
            total = total+cards.get(n).getWeight();
        }
        return total;
    }

    public String getInstanceName() {
        return instanceName;
    }

}
