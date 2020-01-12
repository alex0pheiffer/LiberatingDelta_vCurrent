package com.example.lib;


import com.example.lib.basic_classes.Card;
import com.example.lib.basic_classes.Cards.DiveLeft;

public class testingMain {

    private static Card myCard;

    public static void main(String[] args) {

        myCard = (Card) new DiveLeft();

        System.out.println("Hello World");
    }
}
