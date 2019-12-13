package com.example.rpg_v4.basic_classes;

import android.graphics.drawable.Drawable;

public abstract class fighting_character extends Characters{

    private int fighting_img;
    private String attack_type;
    private Weapon weapon_equip;
    private inventI item_equip;
    private stats_object stats;

    public fighting_character(String nom, String[] descriptstr, Integer[] descriptpl,String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, int fightImg, String atkType, Weapon weapon, inventI item, stats_object thestats) {
        super(nom, descriptstr, descriptpl, gender, age, height, human, magicalAff, strength, charType);
        this.fighting_img = fightImg;
        this.attack_type = atkType;
        this.weapon_equip = weapon;
        this.item_equip = item;
        this.stats = thestats;
    }

    public stats_object getStats() {return stats;}
    public stats_object getWeaponStats() {return weapon_equip.getStats();}
    public stats_object getItemStats() {return item_equip.getStats();}
    public boolean getItemUseForBuff() {return item_equip.getUse_for_buff();}
    public int getFighting_img() { return fighting_img;}
    public String getAttack_type() {return attack_type;}
    public void setFighting_img(int img) {this.fighting_img=img;}
    public void setAttack_type(String atk) {this.attack_type=atk;}


}
