package com.example.rpg_v4.basic_classes;

public class battle_character {

    boolean isMC;
    fighting_character thisCharacter;
    stats_object item_equip_stats;
    stats_object weapon_equip_stats;

    private boolean isDead;
    private boolean turnSkip;
    private boolean isBuffed;
    private stats_object stats_static;
    private stats_object buff_stats;        //only used if isBuffed is TRUE
    private stats_object stats;

    public battle_character(fighting_character thisCharacter) {
        this.thisCharacter = thisCharacter;
        this.isMC = main_character.class.isAssignableFrom(thisCharacter.getClass());
        this.item_equip_stats = thisCharacter.getItemStats();
        this.weapon_equip_stats = thisCharacter.getWeaponStats();

        this.isDead=false;
        this.turnSkip = false;
        this.stats_static = thisCharacter.getStats();
        setBuffStats();
    }

    public boolean getIsDead() {return isDead;}
    public void setIsDead(boolean ded) {isDead = ded;}
    public boolean getTurnSkip() {return turnSkip;}
    public void setTurnSkip(boolean skip) {turnSkip = skip;}

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
        if (item_equip_stats == null && weapon_equip_stats == null) {
            this.buff_stats = null;
        }
        else if(item_equip_stats == null) {
            this.buff_stats = weapon_equip_stats;
            temp.addStats(this.buff_stats);
        }
        else if(weapon_equip_stats == null) {
            if (thisCharacter.getItemUseForBuff()) {
                this.buff_stats = null;
            }
            else {
                this.buff_stats = item_equip_stats;
                temp.addStats(this.buff_stats);
            }
        }
        else {
            if (thisCharacter.getItemUseForBuff()) {
                this.buff_stats = weapon_equip_stats;
                temp.addStats(this.buff_stats);
            }
            else {
                this.buff_stats = new stats_object(
                        item_equip_stats.getAttackA()+weapon_equip_stats.getAttackA(),
                        item_equip_stats.getHealth()+weapon_equip_stats.getHealth(),
                        item_equip_stats.getVolatility()+weapon_equip_stats.getVolatility(),
                        item_equip_stats.getEvasiveA()+weapon_equip_stats.getEvasiveA(),
                        item_equip_stats.getEvasiveM()+weapon_equip_stats.getEvasiveM(),
                        item_equip_stats.getADefense()+weapon_equip_stats.getADefense(),
                        item_equip_stats.getFireDefense()+weapon_equip_stats.getFireDefense(),
                        item_equip_stats.getWaterDefense()+weapon_equip_stats.getWaterDefense(),
                        item_equip_stats.getLandDefense()+weapon_equip_stats.getLandDefense(),
                        item_equip_stats.getAirDefense()+weapon_equip_stats.getAirDefense());
                temp.addStats(this.buff_stats);
            }
        }
        this.stats = temp;
    }

    public void useCard(Card card, battle_character target) {
        card.preformCard(this, target);
    }

}
