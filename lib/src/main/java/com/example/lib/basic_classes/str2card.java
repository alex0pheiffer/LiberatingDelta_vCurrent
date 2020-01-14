package com.example.lib.basic_classes;

import com.example.lib.basic_classes.Cards.Distract;
import com.example.lib.basic_classes.Cards.DiveLeft;
import com.example.lib.basic_classes.Cards.RockToss;
import com.example.lib.basic_classes.Cards.Shove;
import com.example.lib.basic_classes.Cards.Splash;
import com.example.lib.basic_classes.Cards.Struggle;
import com.example.lib.basic_classes.Cards.TreeHide;

import java.util.ArrayList;
import java.util.Collections;

public class str2card {

    //todo update this as you add more card classes
    private Class[] cardClasses = {
            Distract.class,
            DiveLeft.class,
            RockToss.class,
            Shove.class,
            Splash.class,
            Struggle.class,
            TreeHide.class};
    private ArrayList<String> cardClassesNames;
    private Card previousCard;

    public str2card() {
        cardClassesNames = new ArrayList<String>();
        alphabetizeClassCard();
        for (Class n : cardClasses) {
            cardClassesNames.add(n.getSimpleName());
        }
        previousCard = null;
    }

    private void alphabetizeClassCard() {
        //insertion sort
        String tempStr;
        Class tempCl;
        for (int i = 0; i < cardClasses.length; i++)
        {
            for (int j = i + 1; j < cardClasses.length; j++)
            {
                if (cardClassesNames.get(i).compareTo(cardClassesNames.get(j))>0)
                {
                    tempStr = cardClassesNames.get(i);
                    tempCl = cardClasses[i];
                    cardClassesNames.set(i,cardClassesNames.get(j));
                    cardClasses[i] = cardClasses[j];
                    cardClassesNames.set(j, tempStr);
                    cardClasses[j] = tempCl;
                }
            }
        }
    }


    public Card getCard(String str) {
        Card card;
        Class tempClass = null;
        tempClass = cardClasses[Collections.binarySearch(cardClassesNames,str)];
        try{ card = (Card) tempClass.newInstance(); }
        catch(Exception e){ throw new RuntimeException("Class "+tempClass.getName()+" is not a valid card."); }
        return card;
    }

    public Card getPrevious(String str) {
        if (str.equals(previousCard.getNom())) return previousCard;
        else throw new RuntimeException("given previous card "+str+" does not match str2card's previous card "+previousCard.getNom());
    }

}
