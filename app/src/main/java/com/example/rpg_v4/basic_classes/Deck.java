package com.example.rpg_v4.basic_classes;

import androidx.annotation.Nullable;

import com.example.rpg_v4.basic_classes.Cards.sudoCard;

import java.util.ArrayList;

public class Deck extends inventI {

    private String instanceName;

    private ArrayList<sudoCard> alphaSudoCards;
    private int cardAmt;        //total number of cards in the deck
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

    /*
    public Deck(String name, ArrayList<Card> cards, @Nullable String charHolding) {
        super(name, false, null);
        this.cards = cards;
        if (charHolding != null) {
            this.charHolding = charHolding;
        }

        this.alphabetcards = new ArrayList<Card>();
        alphabetizeDeck(true);
        this.cardAmt = cards.size();
        this.isWeaponSpec = checkWeaponSpec();
        this.charequip = checkCharEquip();
        this.totalWeight = getTotalWeight();
        this.instanceName = getClass().getName()+" "+numInstance;
        this.charequip = charequipers[0];
        numInstance++;
    }
    */

    public Deck(String name, @Nullable String charHolding) {
        super(name, false, null);
        this.alphaSudoCards= new ArrayList<sudoCard>();
        if (charHolding != null) {
            this.charHolding = charHolding;
        }
        this.cardAmt = 0;
        this.charequip = charequipers[0];
        numInstance++;
        this.instanceName = getClass().getName()+" "+numInstance;
    }

    //only sorts the very last card...
    private void alphabetizeDeck() {
        boolean stillLarger = true;
        int i = alphaSudoCards.size()-1;
        int newIndex = 0;
        int j = i;
        stillLarger = true;
        while (stillLarger) {
            j--;
            if (alphaSudoCards.get(j).getCard(0).toString().compareTo(alphaSudoCards.get(i).getCard(0).toString()) <= 0) {
                stillLarger = false;
                newIndex = j+1;
            }
            else if (j==0) {
                stillLarger = false;
                newIndex = 0;
            }
        }
        alphaSudoCards.add(newIndex,alphaSudoCards.get(i));
        alphaSudoCards.remove(i+1);
    }

    public int numCardInstance(Card card) {
        int index = binarySearch(card,0,alphaSudoCards.size()-1);
        if (index != -1) {
            return alphaSudoCards.get(index).getAmount();
        }
        else {
            return 0;
        }
    }

    //typical binary search: starting input... card, 0, alphaSudoCards.size()-1
    private int binarySearch(Card card, int startIndex, int endIndex) {
        String x = card.toString();
        if (endIndex >= startIndex) {
            int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
            // If the element is present at the
            // middle itself
            if (alphaSudoCards.get(mid).getCard(0).toString().compareTo(x) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphaSudoCards.get(mid).getCard(0).toString().compareTo(x) > 0)
                return binarySearch(card, startIndex, mid - 1);

            // Else the element can only be present
            // in right subarray
            return binarySearch(card, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    //typical binary search: starting input... card, 0, alphaSudoCards.size()-1
    private int binarySearch(String cardName, int startIndex, int endIndex) {
        String x = cardName;
        if (endIndex >= startIndex) {
            int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
            // If the element is present at the
            // middle itself
            if (alphaSudoCards.get(mid).getCard(0).toString().compareTo(x) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphaSudoCards.get(mid).getCard(0).toString().compareTo(x) > 0)
                return binarySearch(cardName, startIndex, mid - 1);

            // Else the element can only be present
            // in right subarray
            return binarySearch(cardName, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public String checkCharEquip() {
        String belongs = charequipers[0];
        for (sudoCard card : this.alphaSudoCards) {
            if (card.getCard(0).getIsCharcterSpecific()) {
                if (!belongs.equals(card.getCard(0).getSpecificCharacter())) {
                    if (belongs.equals(charequipers[0])) {
                        belongs = card.getCard(0).getSpecificCharacter();
                    } else {
                        return charequipers[4];
                    }
                }
            }
        }
        return belongs;
    }

    public String checkCharEquip(Card exceptionCard) {
        int index = binarySearch(exceptionCard,0,alphaSudoCards.size()-1);
        if (index == -1) {
            throw new RuntimeException("exception card DNE");
        }
        else if(alphaSudoCards.get(index).getAmount() > 1) {
            //the card being removed wont change the charEquip
            return charequip;
        }
        else {
            String belongs = charequipers[0];
            for (sudoCard card : this.alphaSudoCards) {
                if (!card.getCard(0).getInstanceName().equals(exceptionCard.getInstanceName())) {
                    if (card.getCard(0).getIsCharcterSpecific()) {
                        if (!belongs.equals(card.getCard(0).getSpecificCharacter())) {
                            if (belongs.equals(charequipers[0])) {
                                belongs = card.getCard(0).getSpecificCharacter();
                            } else {
                                return charequipers[4];
                            }
                        }
                    }
                }
            }
            return belongs;
        }
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
        for (sudoCard card : alphaSudoCards) {
            if (card.getCard(0).getIsWeaponSpecific())
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
        //update the charequip if its not already invalid
        if (!charequip.equals(charequipers[4]))
            updateCharEquip(card, true);

        int cardinst = numCardInstance(card);
        if (cardinst == 0) {
            card.addDeckAmt();
        }
        //if the deck isnt invalid, find out if this card makes it invalid
        if (isValid) {
            if (charequip.equals(charequipers[4]))
                isValid = false;
            if (isValid && cardAmt + 1 > MAX_DECK_SIZE) {
                isValid = false;
            }
            if (isValid && totalWeight + card.getWeight() > MAX_WEIGHT) {
                isValid = false;
            }
            if (isValid && cardinst >= MAX_CARD_AMT)
                isValid = false;

        }
        if (card.getIsWeaponSpecific()) {
            Weapon cardWeapon = card.getSpecificWeapon();
            if (isValid && isWeaponSpec) {
                if (!cardWeapon.getCharEquip().equals(charequip))
                    isValid = false;
            }
            else if (isValid){
                //all characters -> char of this weapon
                //this should be redundant because the card's charequip should take care of this
                if (charequip.equals(charequipers[0])) {
                    charequip = cardWeapon.getCharEquip();
                    throw new RuntimeException("card's charequip is all but it's specific weapon is char-spec: "+card.getNom());
                }
                //the card's specific weapon's specific character is >NOT< the same as the card's current specific character
                else if (!card.getSpecificWeapon().getCharEquip().equals(charequip)) {
                    isValid = false;
                }
                isWeaponSpec = true;
            }
            addWeaponSpec(cardWeapon);
        }

        totalWeight=totalWeight+card.getWeight();
        cardAmt++;
        addToSudoDeck(card);
    }

    //when removing a card from the deck
    public void removeCard(Card card) {
        int numinst = numCardInstance(card);
        if (numinst == 1) {
            card.removeDeckAmt();
        }
        if (card.getIsWeaponSpecific()) {
            removeWeaponSpec(card.getSpecificWeapon());
        }
        if (card.getIsCharcterSpecific()) {
            updateCharEquip(card,false);
        }

        if (charequip.equals(charequipers[4])) {
            isValid = false;
        }
        if (isValid && cardAmt > MAX_DECK_SIZE) {
            isValid = false;
        }
        if (isValid && totalWeight > MAX_WEIGHT) {
            isValid = false;
        }
        if (isValid && numinst-1 > MAX_CARD_AMT) {
            isValid = false;
        }

        removeFromSudoDeck(card);

        totalWeight=totalWeight-card.getWeight();
        cardAmt--;
    }

    private void addToSudoDeck(Card card) {
        //check to make sure the deck isn't empty
        int newIndex = -1;
        if(alphaSudoCards.size() == 0) {
            alphaSudoCards.add(new sudoCard(card));
            newIndex = 0;
        }
        else {
            //try to find the sudocard deck for this card
            int index = binarySearch(card, 0, alphaSudoCards.size() - 1);
            System.out.println();
            if (index != -1) {
                alphaSudoCards.get(index).addCard(card);
                newIndex = index;
            } else {
                alphaSudoCards.add(new sudoCard(card));
                alphabetizeDeck();
                //throw new RuntimeException("CHECK!!");
            }
        }
        cardAmt++;
    }

    //when removing a card from your inventory...you will no longer own this card
    private void removeFromSudoDeck(Card card) {
        int index = binarySearch(card, 0, alphaSudoCards.size() - 1);
        //find the sudocard that matches this card
        if (index != -1) {
            throw new RuntimeException("error: card "+card.getNom()+" DNE in allCards");
        }
        else {
            if (alphaSudoCards.get(index).getAmount() < 2 ) {
                //there is only one (or 0) instances of this card in the deck...remove the sudoCard
                alphaSudoCards.remove(index);
            }
            else {
                boolean cardExists = false;
                for (int i = 0; i < alphaSudoCards.get(index).getAmount(); i++) {
                    if (alphaSudoCards.get(index).getCard(i).getInstanceName().equals(card.getInstanceName())) {
                        //the card is the same as this card
                        alphaSudoCards.get(index).removeCard(card);
                        cardExists = true;
                    }
                }
                if (!cardExists) {
                    throw new RuntimeException("card instance "+card.getInstanceName()+" DNE in it's sudocard");
                }
            }
        }
        cardAmt--;
    }

    public void updateCardAmt() {
        int amt = 0;
        if (alphaSudoCards.size() == 0) {
           cardAmt = 0;
        }
        else {
            for (int n = 0; n < alphaSudoCards.size(); n++) {
                amt = amt + alphaSudoCards.get(n).getAmount();
            }
        }
        cardAmt = amt;
    }

    public int getCardAmt() {
        return cardAmt;
    }

    public Card getGeneralCard(int index) {
        return alphaSudoCards.get(index).getCard(0);
    }

    public Card getGeneralCard(String name) {
        int index = binarySearch(name, 0, alphaSudoCards.size() - 1);
        return alphaSudoCards.get(index).getCard(0);
    }

    public sudoCard getSudoCard(int index) {
        return alphaSudoCards.get(index);
    }

    public sudoCard getSudoCard(String name) {
        int index = binarySearch(name, 0, alphaSudoCards.size() - 1);
        return alphaSudoCards.get(index);
    }

    public void removeLastCard(String name) {
        sudoCard tempSudo = getSudoCard(name);
        removeCard(tempSudo.getCard(tempSudo.getAmount()-1));
    }

    //when moving a card in the deck to a differnet position in the deck
    //public boolean moveCard(Card card, int location) {
    //    boolean moved = true;
    //}

    public static int getNumInstance() {
        return numInstance;
    }

    public int getTotalWeight() {
        int total = 0;
        for (int n=0; n<alphaSudoCards.size(); n++) {
            total = total+alphaSudoCards.get(n).getCard(0).getWeight()*alphaSudoCards.get(n).getAmount();
        }
        return total;
    }

    public String getInstanceName() {
        return instanceName;
    }

}
