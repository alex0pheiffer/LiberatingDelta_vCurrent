package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.basic_classes.Cards.sudoCard;

import java.util.ArrayList;

public class Deck implements abstractDeck {

    private String name;
    private String instanceName;

    private ArrayList<sudoCard> alphabetcards;
    private ArrayList<Card> allCards;   //this is in no particular order. its just a list of all the cards.
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

    public Deck(String name, String charHolding, ArrayList<sudoCard> cards) {
        this.name = name;
        this.alphabetcards= new ArrayList<sudoCard>();
        this.allCards = new ArrayList<Card>();
        if (charHolding != null) {
            this.charHolding = charHolding;
        }
        this.cardAmt = 0;
        this.charequip = charequipers[0];
        numInstance++;
        this.instanceName = getClass().getName()+" "+numInstance;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.get(i).getAmount(); j++) {
                this.addCard(cards.get(i).getCard(j));
            }
        }
    }

    public Deck(String name, String charHolding) {
        this.name = name;
        this.alphabetcards= new ArrayList<sudoCard>();
        this.allCards = new ArrayList<Card>();
        if (charHolding != null) {
            this.charHolding = charHolding;
        }
        this.cardAmt = 0;
        this.charequip = charequipers[0];
        numInstance++;
        this.instanceName = getClass().getName()+" "+numInstance;
    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    private int binarySearch(String cardName, int startIndex, int endIndex) {
        String x = cardName;
        if (endIndex >= startIndex) {
            int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
            // If the element is present at the
            // middle itself
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) == 0)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) > 0)
                return binarySearch(cardName, startIndex, mid - 1);

            // Else the element can only be present
            // in right subarray
            return binarySearch(cardName, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    public int binarySearch(Card card, int startIndex, int endIndex) {
        String x = card.toString();

        //System.out.println("--- bi : "+card.toString()+" ---");
        //System.out.println("s: "+startIndex+"\te:"+endIndex);

        if (endIndex >= startIndex) {
            int mid = startIndex + (int)(((double)(endIndex - startIndex) / 2)+.5);
            //System.out.println("mid: "+mid);
            // If the element is present at the
            // middle itself
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) == 0) {
                //System.out.println("is mid : "+alphabetcards.get(mid).getCard(0).toString());
                return mid;
            }

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (alphabetcards.get(mid).getCard(0).toString().compareTo(x) > 0) {
                //System.out.println("is smaller : "+alphabetcards.get(mid).getCard(0).toString());
                return binarySearch(card, startIndex, mid - 1);
            }

            // Else the element can only be present
            // in right subarray
            //System.out.println("is larger: "+alphabetcards.get(mid).getCard(0).toString());
            return binarySearch(card, mid + 1, endIndex);
        }

        // We reach here when element is not present
        // in array
        return -1;
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

    private String checkCharEquip() {
        String belongs = charequipers[0];
        for (sudoCard card : this.alphabetcards) {
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

    private String checkCharEquip(Card exceptionCard) {
        int index = binarySearch(exceptionCard,0,alphabetcards.size()-1);
        if (index == -1) {
            throw new RuntimeException("exception card DNE");
        }
        else if(alphabetcards.get(index).getAmount() > 1) {
            //the card being removed wont change the charEquip
            return charequip;
        }
        else {
            String belongs = charequipers[0];
            for (sudoCard card : this.alphabetcards) {
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

    private void updateCharEquip(Card card, boolean adding) {
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

    private boolean checkWeaponSpec() {
        for (sudoCard card : alphabetcards) {
            if (card.getCard(0).getIsWeaponSpecific())
                return true;
        }
        return false;
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

    private void addWeaponSpec(Weapon weep) {
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

    private void removeWeaponSpec(Weapon weep) {
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
    public int addCard(Card card) {
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
        allCards.add(card);
        return 0;
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
        allCards.remove(card);

        totalWeight=totalWeight-card.getWeight();
        cardAmt--;
    }

    private void addToSudoDeck(Card card) {
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
                alphabetizeDeck();
                //throw new RuntimeException("CHECK!!");
            }
        }
        cardAmt++;
    }

    //when removing a card from your inventory...you will no longer own this card
    private void removeFromSudoDeck(Card card) {

        //System.out.println("_________");
        //System.out.println(card.toString());
        //System.out.println("_________");
        /*
        for (int i = 0; i < alphabetcards.size(); i++) {
            for (int j = 0; j < alphabetcards.get(i).getAmount(); j++) {
                System.out.println(alphabetcards.get(i).getCard(j).toString());
            }
        }
        */

        int index = binarySearch(card, 0, alphabetcards.size() - 1);
        //find the sudocard that matches this card
        //System.out.println("index: "+index);
        if (index == -1) {
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
    }

    public sudoCard getSudoCard(String name) {
        int index = binarySearch(name, 0, alphabetcards.size() - 1);
        return alphabetcards.get(index);
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

    public Card getGeneralCard(int index) {
        return alphabetcards.get(index).getCard(0);
    }

    public Card getGeneralCard(String name) {
        int index = binarySearch(name, 0, alphabetcards.size() - 1);
        return alphabetcards.get(index).getCard(0);
    }

    //updates the total amount individual cards: cardAmt
    public int getCardAmt() {
        return allCards.size();
    }

    public boolean getIsValid() {return isValid;}

    public String getCharequip() {return charequip;}

    public String getCharHolding() {return charHolding;}

    public void removeLastCard(String name) {
        sudoCard tempSudo = getSudoCard(name);
        removeCard(tempSudo.getCard(tempSudo.getAmount()-1));
    }

    //take note that this function doesnt clear allCards nor alphabetCards
    public void removeAll() {
        sudoCard temp;
        for (int n = 0; n < alphabetcards.size(); n++) {
            temp = alphabetcards.get(n);
            //int numinst = numCardInstance(temp);
            //if (numinst == 1) {
            temp.getCard(0).removeDeckAmt();
            //}
        }
    }

    //shuffles the allCards list
    public void shuffle() {
        int newIndex = 0;
        Card tempCard;
        for (int i=0; i < allCards.size(); i++) {
            newIndex = (int)(Math.random()*allCards.size());
            tempCard = allCards.get(newIndex);
            allCards.set(newIndex, allCards.get(i));
            allCards.set(i,tempCard);
        }
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
        for (int n=0; n<alphabetcards.size(); n++) {
            total = total+alphabetcards.get(n).getCard(0).getWeight()*alphabetcards.get(n).getAmount();
        }
        return total;
    }

    public Deck copy() {
        return new Deck(this.name+"_c",charHolding, alphabetcards);
    }

    public String getNom() {
        return name;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

}
