package com.example.rpg_v4.basic_classes;

public abstract class fighting_character extends Characters{

    private int fighting_img;
    private String attack_type;
    private Weapon weapon_equip;
    private inventI item_equip;
    private Deck deck_equip;
    private stats_object stats;

    //make sure to use the list under Characters for the magicType string.
    public fighting_character(String nom, String[] descriptstr, Integer[] descriptpl,String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, String magicType, int fightImg, String atkType, Weapon weapon, inventI item, Deck deck, stats_object thestats) {
        super(nom, descriptstr, descriptpl, gender, age, height, human, magicalAff, strength, charType, magicType);
        this.fighting_img = fightImg;
        this.attack_type = atkType;
        this.weapon_equip = weapon;
        this.item_equip = item;
        this.deck_equip = deck;
        this.stats = thestats;
    }

    public stats_object getStats() {return stats;}
    public Weapon getWeapon() {return weapon_equip;}
    public void removeWeapon() {weapon_equip = null;}
    public inventI getItem() {return item_equip;}
    public void removeItem() {item_equip = null;}
    public Deck getDeck() {return deck_equip;}
    public stats_object getWeaponStats() {
        if (weapon_equip != null) return weapon_equip.getStats();
        else return null;
    }
    public stats_object getItemStats() {
        if (item_equip != null) return item_equip.getStats();
        else return null;
    }
    public boolean getItemUseForBuff() {return item_equip.getUse_for_buff();}
    public int getFighting_img() { return fighting_img;}
    public String getAttack_type() {return attack_type;}
    public void setFighting_img(int img) {this.fighting_img=img;}
    public void setAttack_type(String atk) {this.attack_type=atk;}
    public void setWeapon(Weapon weap) {this.weapon_equip = weap;}
    public void setItem(inventI item) {this.item_equip = item;}
    public void setDeck(Deck deck) {this.deck_equip = deck;}


}
