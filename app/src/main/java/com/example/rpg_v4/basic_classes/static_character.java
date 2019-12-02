package com.example.rpg_v4.basic_classes;

public class static_character extends Characters {

    private Integer[] expressions;

    public static_character(String nom, String[] descriptstr, Integer[] descriptpl, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, Integer[] expression_img) {
        super(nom, descriptstr, descriptpl, gender, age, height, human, magicalAff, strength, charType);
        expressions = expression_img;
    }

    public String toString() { return "static "+ super.getName(); }
}
