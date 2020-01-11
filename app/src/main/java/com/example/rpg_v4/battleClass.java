package com.example.rpg_v4;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.battle_character;
import com.example.rpg_v4.basic_classes.fighting_character;

import java.util.ArrayList;

public class battleClass {

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
        }

        while (isAlive && !hasWon) {
            //update current character
            character_it = allCharacters.get(0);
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
                    while (chosenCard == null && !chosenTarget) {
                        //wait for input (requires both a chosen Card and a Target)
                    }
                }
                else {
                    //if it's the computer, find the best card.
                    chosenCard = chooseFight();
                    //display the chosen card and what it does for a few seconds
                }
                //the card is executed
                int deaths = 0;
                for (int i = 0; i < chosenTargets.size(); i++) {
                    if (character_it.useCard(chosenCard, chosenTargets.get(i))) deaths++;
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
        }
        return false;
    }

    private Card chooseFight() {

    }

    //call if an ally dies
    public boolean updateAlive() {
        for (int i = 0; i < allies.size(); i++) {
            if (allies.get(i).getIsDead()) {
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
