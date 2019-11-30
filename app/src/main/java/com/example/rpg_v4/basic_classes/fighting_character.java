package com.example.rpg_v4.basic_classes;

import android.graphics.drawable.Drawable;

public class fighting_character extends Characters{

    private int fighting_img;
    private String attack_type;
    private boolean isDead;
    private boolean turnSkip;
    private Weapon weapon_equip;
    private inventI item_equip;
    private stats_object stats_static;
    private stats_object buff_stats;
    private stats_object stats;

    public fighting_character(String nom, String[] descriptstr, Integer[] descriptpl, String[] greet, String gender, int age, int height, boolean human, int magicalAff, int strength, String charType, int fightImg, String atkType, Weapon weapon, inventI item, stats_object thestats) {
        super(nom, descriptstr, descriptpl, greet, gender, age, height, human, magicalAff, strength, charType);
        this.fighting_img = fightImg;
        this.attack_type = atkType;
        this.isDead=false;
        this.turnSkip = false;
        this.weapon_equip = weapon;
        this.item_equip = item;
        this.stats_static = thestats;
        setBuffStats();
    }

    public stats_object getStats() {return stats;}
    public int getFighting_img() { return fighting_img;}
    public String getAttack_type() {return attack_type;}
    public boolean getIsDead() {return isDead;}
    public boolean getTurnSkip() {return turnSkip;}
    public void setFighting_img(int img) {this.fighting_img=img;}
    public void setAttack_type(String atk) {this.attack_type=atk;}
    public void setIsDead(boolean ded) {this.isDead = ded;}
    public void setTurnSkip(boolean skip) {this.turnSkip = skip;}
    public boolean isVolatile() {
        int vol = stats.getVolatility();
        int rand = (int)(Math.random()*100);
        if (vol >= rand) return true;
        else return false;
    }
    public void hitWMagic(int amt, String type) {
        //when the attack is purely magical
        int change = 0;
        if (type.equals(stats_object.getType(0))) {
            //Fire
            change = stats.getFireDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(1))) {
            //Water
            change = stats.getWaterDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(2))) {
            //Land
            change = stats.getLandDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(3))) {
            //Air
            change = stats.getAirDefense()*amt/100;
        }
        else if (type.equals(stats_object.getType(4))) {
            //Scatter
            int def = (stats.getAirDefense()+stats.getFireDefense()+stats.getLandDefense()+stats.getWaterDefense())/4;
            change = def*amt/100;
        }
        reduceHealth(change);
    }
    public void hitWPhysical(int amt) {
        //when the attack is purely physical
        int change = stats.getADefense()*amt/100;
        reduceHealth(change);
    }
    public void hitWBoth(int amt, int percentM, String type) {
        //when the attack is both magical and physical... ex a magic sword
        int Mhit = amt*percentM/100;
        int Ahit = amt - Mhit;
        hitWMagic(Mhit, type);
        hitWPhysical(Ahit);
    }
    private void reduceHealth(int amt) {
        if (stats.getHealth()-amt <= 0) {
            stats.setHealth(0);
            setIsDead(true);
        }
        else {
            stats.setHealth(stats.getHealth()-amt);
        }
    }
    private void setBuffStats() {
        stats_object temp = stats_static;
        if (item_equip == null && weapon_equip == null) {
            this.buff_stats = null;
        }
        else if(item_equip == null) {
            this.buff_stats = weapon_equip.getStats();
            temp.addStats(this.buff_stats);
        }
        else if(weapon_equip == null) {
            if (item_equip.getUse_for_buff()) {
                this.buff_stats = null;
            }
            else {
                this.buff_stats = item_equip.getStats();
                temp.addStats(this.buff_stats);
            }
        }
        else {
            if (item_equip.getUse_for_buff()) {
                this.buff_stats = weapon_equip.getStats();
                temp.addStats(this.buff_stats);
            }
            else {
                this.buff_stats = new stats_object(
                         item_equip.getStats().getAttackA()+weapon_equip.getStats().getAttackA(),
                         item_equip.getStats().getHealth()+weapon_equip.getStats().getHealth(),
                        item_equip.getStats().getVolatility()+weapon_equip.getStats().getVolatility(),
                        item_equip.getStats().getEvasiveA()+weapon_equip.getStats().getEvasiveA(),
                        item_equip.getStats().getEvasiveM()+weapon_equip.getStats().getEvasiveM(),
                        item_equip.getStats().getADefense()+weapon_equip.getStats().getADefense(),
                        item_equip.getStats().getFireDefense()+weapon_equip.getStats().getFireDefense(),
                        item_equip.getStats().getWaterDefense()+weapon_equip.getStats().getWaterDefense(),
                        item_equip.getStats().getLandDefense()+weapon_equip.getStats().getLandDefense(),
                        item_equip.getStats().getAirDefense()+weapon_equip.getStats().getAirDefense());
                temp.addStats(this.buff_stats);
            }
        }
        this.stats = temp;
    }

    public void useCard(Card card, fighting_character target) {
        card.preformCard(this, target);
    }

}
