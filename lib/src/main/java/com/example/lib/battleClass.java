package com.example.lib;

import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.battle_character;
import com.example.lib.basic_classes.fighting_character;
import com.example.lib.basic_classes.str2card;

import java.util.Scanner;
import java.util.ArrayList;

public class battleClass {

    Scanner scanner = new Scanner(System.in);


    ArrayList<battle_character> allCharacters;
    ArrayList<battle_character> allies;
    ArrayList<battle_character> enemies;
    boolean isAlive = true;
    boolean hasWon = false;


    battle_character character_it;
    Card chosenCard = null;
    ArrayList<battle_character> chosenTargets;


    //in the order in which you'd like the characters to fight init (turns)
    public battleClass(ArrayList<fighting_character> characters, ArrayList<Boolean> allyList) {
        allCharacters = new ArrayList<battle_character>();
        allies = new ArrayList<battle_character>();
        enemies = new ArrayList<battle_character>();
        battle_character curChar;
        for (int i = 0; i < characters.size(); i++) {
            curChar= new battle_character(characters.get(i),i*(int)(Math.random()*100),allyList.get(i));
            allCharacters.add(curChar);
            if (allyList.get(i)) {
                allies.add(curChar);
            }
            else enemies.add(curChar);
        }
    }

    public battleClass(ArrayList<fighting_character> characters, ArrayList<Integer> initTime, ArrayList<Boolean> allyList) {
        allCharacters = new ArrayList<battle_character>();
        for (int i = 0; i < characters.size(); i++) {
            allCharacters.add(new battle_character(characters.get(i),initTime.get(i),allyList.get(i)));
        }

    }

    //returns whether the player won or not
    public boolean battle() {

        chosenTargets = new ArrayList<battle_character>();

        //setting up the battle...
        for (int n = 0; n < allCharacters.size(); n++) {
            allCharacters.get(n).shuffleDeck();
            allCharacters.get(n).fillHand();
            System.out.println("Character "+allCharacters.get(n).getNom()+": \tHP: "+allCharacters.get(n).getStatsStatic().getHealth());
            System.out.println("AtkP: "+allCharacters.get(n).getStatsStatic().getAttackA()+" DefA: "+allCharacters.get(n).getStatsStatic().getADefense());
        }

        while (isAlive && !hasWon) {
            //update current character
            character_it = allCharacters.get(0);
            chosenTargets.clear();

            System.out.println("It's now "+character_it.getNom()+"'s turn!");

            character_it.applyEffectTodo();
            character_it.setWeightPoints(character_it.getWeightPoints()+1);
            //check if character is still alive
            if (!character_it.getIsDead() && !character_it.getTurnSkip()) {
                //character_it picks a card
                //character_it picks a target (or selection of targets)
                //card is executed for every selected target
                if (character_it.getIsAlly()) {
                    //if it's an ally, wait for the user to select a card and target...
                    chosenCard = null;
                    boolean chosenTarget = false;
                    while (chosenCard == null || !chosenTarget) {
                        //wait for input (requires both a chosen Card and a Target)


                        System.out.println("Please choose a card to play: ");
                        for (int c = 0; c < character_it.getHand(); c++) {
                            System.out.println("\t"+character_it.getHandCard(c));
                        }
                        System.out.println();
                        boolean switchUnhappy = true;
                        String card = "";
                        while (switchUnhappy) {
                            card = scanner.nextLine();
                            for (int i = 0; i < character_it.getHand(); i++) {
                                if (character_it.getHandCard(i).getNom().equals(card)) {
                                    chosenCard = character_it.getHandCard(i);
                                }
                            }
                            if (chosenCard == null) {
                                //the input was invalid
                                System.out.println("Invalid. Please input again...");
                            }
                            else switchUnhappy = false;
                        }

                        System.out.println("The card "+chosenCard.getNom()+" takes "+chosenCard.getTargetCharAmt()+" targets.");
                        int moreTargets = 0;
                        while (moreTargets < chosenCard.getTargetCharAmt() && moreTargets < enemies.size()) {
                            System.out.println("Who would you like to attack?");
                            if (moreTargets == 0) {
                                for (int c = 0; c < enemies.size(); c++) {
                                    System.out.println("\t" + enemies.get(c).getNom() + " HP: " + enemies.get(c).getHP());
                                }
                            }
                            System.out.println();
                            switchUnhappy = true;
                            String target = "";
                            while (switchUnhappy) {
                                target = scanner.nextLine();
                                for (int i = 0; i < enemies.size(); i++) {
                                    if (enemies.get(i).getNom().equals(target)) {
                                        chosenTargets.add(enemies.get(i));
                                    }
                                }
                                if (chosenCard == null) {
                                    //the input was invalid
                                    System.out.println("Invalid. Please input again...");
                                }
                                else switchUnhappy = false;
                            }
                            moreTargets++;
                        }
                        chosenTarget = true;
                    }
                }
                else {
                    //if it's the computer, find the best card.
                    chosenCard = chooseFight();
                    //display the chosen card and what it does for a few seconds

                    System.out.println(character_it.getNom()+" has played "+chosenCard.getNom()+".");

                }
                //the card is executed
                int deaths = 0;
                for (int i = 0; i < chosenTargets.size(); i++) {

                    System.out.println(chosenCard.getNom()+" has been used on "+chosenTargets.get(i).getNom()+"!");

                    if (character_it.useCard(chosenCard, chosenTargets.get(i))) {
                        deaths++;

                        //if the dead char is an ally
                        if (chosenTargets.get(i).getIsAlly()) {
                            updateAlive();
                        }
                        //if the dead character is an enemy
                        else updateWon();

                        System.out.println(chosenTargets.get(i).getNom()+" has died!");
                        System.out.println("isAlive = "+isAlive);
                        System.out.println("hasWon = "+hasWon);

                    }
                }
                //updating weight
                //if the target(s) died, (+2 weight) * (# of dead enemies)
                character_it.setWeightPoints(character_it.getWeightPoints()+(2*deaths));
                //( ? ) if card was <4 weight, +1 weight
                if (chosenCard.getWeight() < 4) character_it.setWeightPoints(character_it.getWeightPoints()+1);
                character_it.setWeightPoints(character_it.getWeightPoints()-chosenCard.getWeight());
            }
            //check if character is dead
            if (character_it.getIsDead()) {
                //remove the character from allCharacters
                if (character_it.getIsAlly()) {
                    if (updateAlive()) {
                        allCharacters.remove(0);
                        allies.remove(character_it);
                    }
                }
                else {
                    if (!updateWon()) {
                        allCharacters.remove(0);
                        enemies.remove(character_it);
                    }
                }
            }
            //shift the timeToTurn character list
            int decreaseWait = allCharacters.get(1).getWait();
            allCharacters.get(0).increaseWait(chosenCard.getWait());
            allCharacters.add(allCharacters.get(0));
            allCharacters.remove(0);
            for (int i = 0; i < allCharacters.size(); i++) {
                allCharacters.get(i).reduceWait(decreaseWait);
                //and move them on the screen
            }
            //clear the target list
            chosenTargets.clear();
            //turnSkip is false
            character_it.setTurnSkip(false);
        }
        return false;
    }

    //the computer decides which card is best to use.
    private Card chooseFight() {
        //first, determine which would produce the best amount of damage.
        int maxMaxCard = 0;
        int maxMaxDamage = 0;
        ArrayList<Integer> maxMaxTargets = new ArrayList<Integer>();

        int cTargets = 0;
        boolean dead = false;
        //list of the index in the opponents list
        int maxCardDamage;


        for (int c = 0; c < character_it.getHand(); c++) {

            ArrayList<battle_character> opponents = new ArrayList<battle_character>();
            for (int b = 0; b < allies.size(); b++) {
                opponents.add(new battle_character(allies.get(b)));
            }

            System.out.println("opponent is testing their options....\n");
            System.out.println("opponents: ");
            for (int i=0; i < opponents.size(); i++) {
                System.out.println("\t"+opponents.get(i).getNom());
            }


            ArrayList<Integer> cTargetList = new ArrayList<Integer>();
            cTargets = character_it.getHandCard(c).getTargetCharAmt();
            maxCardDamage = 0;

            System.out.println("test "+c+": "+character_it.getHandCard(c));
            System.out.println("requires "+cTargets+" targets.");

            //the number of targets the card requires is greater than the number of targets available.
            if (cTargets >= opponents.size()) {
                //no need to check different potential targets: automatically selects all.

                System.out.println("more targets req than available.");

                //test each target to see if anyone dies, and to find the max HP damage done
                for (int i = 0; i < opponents.size(); i++) {

                    System.out.println("card used on "+opponents.get(i));

                    dead = character_it.testCard(character_it.getHandCard(c), opponents.get(i));

                    if (dead) {

                        System.out.println("Opponent died.");

                        for (int j = 0; j < opponents.size(); j++) {
                            chosenTargets.add(allies.get(j));
                        }
                        return character_it.getHandCard(c);
                    }
                    //change maxDamage is this damage is greater
                    if (maxCardDamage < allies.get(i).getHP()-opponents.get(i).getHP()) {
                        maxCardDamage = allies.get(i).getHP()-opponents.get(i).getHP();
                    }
                }
                //update the target list for this card
                for (int i = 0; i < opponents.size(); i++) {
                    cTargetList.add(i);
                }
            }
            else {
                //just loop through the opponents and see if anyone dies...

                System.out.println("targets required <= targets available");

                for (int a = 0; a < opponents.size(); a++) {

                    System.out.println("card used on "+opponents.get(a));

                    dead = character_it.testCard(character_it.getHandCard(c),opponents.get(a));
                    if (dead) {

                        System.out.println("Opponent died.");

                        //firstly, add the char that dies.
                        chosenTargets.add(allies.get(a));
                        for (int j = 0; j < cTargets-1; j++) {
                            //dont wanna repeat the char we already put in
                            if (j == a) j++;
                            //add another random character
                            chosenTargets.add(allies.get(j));
                        }
                        return character_it.getHandCard(c);
                    }
                    //change maxDamage is this damage is greater
                    if (maxCardDamage < allies.get(a).getHP()-opponents.get(a).getHP()) {
                        maxCardDamage = allies.get(a).getHP()-opponents.get(a).getHP();
                        //add the character to the list to be attacked
                        if (cTargetList.size() < cTargets) {
                            cTargetList.add(a);
                        }
                        else {
                            cTargetList.remove(0);
                            cTargetList.add(a);
                        }
                    }
                    else if (cTargetList.size() < cTargets) {
                        cTargetList.add(0, a);
                    }
                }
            }

            //is this max larger than maxMax?

            System.out.println("maxDmg: "+maxCardDamage+" maxMaxDmg: "+maxMaxDamage);

            if (maxCardDamage > maxMaxDamage) {

                System.out.println(character_it.getHandCard(c)+"'s damage is greater. It is the new saved maxCard.");

                maxMaxCard = c;
                maxMaxDamage = maxCardDamage;
                maxMaxTargets = cTargetList;
            }

        }
        //otherwise... return the max damage card
        for (int j = 0; j < maxMaxTargets.size(); j++) {
            chosenTargets.add(j, allies.get(maxMaxTargets.get(j)));
        }
        return character_it.getHandCard(maxMaxCard);
    }

    //call if an ally dies
    public boolean updateAlive() {
        for (int i = 0; i < allies.size(); i++) {
            if (!allies.get(i).getIsDead()) {
                isAlive = true;
                return true;
            }
        }
        isAlive = false;
        return false;
    }

    public boolean updateWon() {
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).getIsDead()) {
                hasWon = false;
                return false;
            }
        }
        hasWon = true;
        return true;
    }
}
