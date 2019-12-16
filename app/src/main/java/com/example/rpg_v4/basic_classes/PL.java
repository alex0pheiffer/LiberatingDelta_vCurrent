package com.example.rpg_v4.basic_classes;

import com.example.rpg_v4.basic_classes.Card;
import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.ExtraQuest;
import com.example.rpg_v4.basic_classes.Weapon;
import com.example.rpg_v4.basic_classes.cityPt;
import com.example.rpg_v4.basic_classes.inventI;
import com.example.rpg_v4.basic_classes.main_character;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.basic_classes.the_MCs.Delta;
import com.example.rpg_v4.basic_classes.the_MCs.Katherine;
import com.example.rpg_v4.basic_classes.the_MCs.Vivian;
import com.example.rpg_v4.basic_classes.the_chapters.chapter_1;
import com.example.rpg_v4.basic_classes.the_chapters.chapter_2;
import com.example.rpg_v4.basic_classes.the_chapters.chapter_filler;
import com.example.rpg_v4.basic_classes.the_regions.GreatNorth;
import com.example.rpg_v4.basic_classes.the_regions.HDR;
import com.example.rpg_v4.basic_classes.the_regions.IceCube;
import com.example.rpg_v4.basic_classes.the_regions.Juslyn;
import com.example.rpg_v4.basic_classes.the_regions.MaceriaUnion;
import com.example.rpg_v4.basic_classes.the_regions.Nebula;
import com.example.rpg_v4.basic_classes.the_regions.Petrasepire;
import com.example.rpg_v4.basic_classes.the_regions.Pietas;
import com.example.rpg_v4.basic_classes.the_regions.Rupes;
import com.example.rpg_v4.basic_classes.the_regions.TheTossedStones;
import com.example.rpg_v4.basic_classes.the_regions.Veneland;

import java.util.ArrayList;
import java.util.List;

public abstract class PL {
    private int PL;
    private Chapter coorChapter;

    private Veneland veneland = new Veneland(); private Pietas pietas = new Pietas(); private TheTossedStones thetossedstones = new TheTossedStones(); private HDR hdr = new HDR(); private Nebula nebula = new Nebula(); private IceCube icecube = new IceCube();
    private Rupes rupes = new Rupes(); private Petrasepire petrasepire = new Petrasepire(); private Juslyn juslyn = new Juslyn(); private MaceriaUnion maceriaunion = new MaceriaUnion(); private GreatNorth greatnorth = new GreatNorth();

    //private regions[] all_regions = {veneland, pietas, thetossedstones, hdr, nebula, icecube, rupes, petrasepire, juslyn, maceriaunion, greatnorth};
    private regions[] all_regions; //remember that REGIONS WILL CHANGE AS THE GEOGRAPHY CHANGES

    private regions current_region;               //region this level takes place
    private cityPt current_city;                  //city this level takes place
    private String[] all_MC_nom;           //the mc's which will be on the char screen IN ORDER  ie "Katherine","Vivian","Delta"
    private main_character[] all_MC;       //should be in the same order as the above
    private Characters[] all_characters;

    private String description;
    private Weapon[] unlocked_weapons;             //new weapons you could get
    private Card[] unlocked_cards;                 //new cards you could get
    private inventI[] unlocked_items;               //new items you could get
    private ExtraQuest[] unlocked_eq;               //new eq you could complete
    private regions unlocked_region;              //new region you can explore
    private cityPt[] unlocked_city;                 //new cities you could explore
    private Weapon[] banned_weapons;	            //weapons you cannot use
    private Card[] banned_cards;		            //cards you cannot use
    private inventI[] banned_items;                 //items you cannot use
    private ExtraQuest[] banned_eq;                 //eq you cannot currently complete (diff from closed)
    private String[] banned_region;                 //regions you cannot enter
    private String[] banned_city;                   //cities you cannot enter
    private String[] banned_char;                   //characters you cannot currently play ingame
    private Weapon[] required_weapon;               //a weapon you must use
    private Card[] required_card;                  //any cards you must use
    private inventI[] required_item;                //an item you must use
    private Characters[] required_char;             //a character you must use

    //should be in descending order (top == highest pl)
    private List<Chapter> chipperTowneChapters = new ArrayList<Chapter>();
    private List<Chapter> maleficereMansionChapters = new ArrayList<Chapter>();

    public PL(int PL, Chapter coorChapter, regions[] all_regions, regions current_region,
              cityPt current_city, String[] all_MC_nom, main_character[] all_MC, Characters[] all_characters,
              String description, Weapon[] unlocked_weapons,
              Card[] unlocked_cards, inventI[] unlocked_items, ExtraQuest[] unlocked_eq,
              regions unlocked_region, cityPt[] unlocked_city, Weapon[] banned_weapons,
              Card[] banned_cards, inventI[] banned_items, ExtraQuest[] banned_eq, String[] banned_region,
              String[] banned_city, String[] banned_char, Weapon[] required_weapon, Card[] required_card,
              inventI[] required_item, Characters[] required_char){

        this.PL = PL;
        this.coorChapter = coorChapter;

        this.all_regions = all_regions;
        this.current_region = current_region;
        this.current_city = current_city;
        this.all_MC_nom = all_MC_nom;
        this.all_MC = all_MC;
        this.all_characters = all_characters;

        this.description = description;
        this.unlocked_weapons = unlocked_weapons;
        this.unlocked_cards = unlocked_cards;
        this.unlocked_items = unlocked_items;
        this.unlocked_eq = unlocked_eq;
        this.unlocked_region= unlocked_region;
        this.unlocked_city = unlocked_city;
        this.banned_weapons = banned_weapons;
        this.banned_cards = banned_cards;
        this.banned_items = banned_items;
        this.banned_eq = banned_eq;
        this.banned_region = banned_region;
        this.banned_city = banned_city;
        this.banned_char = banned_char;
        this.required_weapon = required_weapon;
        this.required_card = required_card;
        this.required_item = required_item;
        this.required_char = required_char;

    }

    //LET US REMEMBER: THERE IS NO '0' PL; IF PL IS USED AS AN INDEX, YOU SUBTRACT 1

    public regions getRegion(String name) {
        int the_region = 0;
        for (int i = 0; i < all_regions.length; i++) {
            if (all_regions[i].getNom().equals(name))
                the_region = i;
                break;
        }
        return all_regions[the_region];
    }

    public cityPt getCityPt(String name, regions region) {
        cityPt the_city = null;
        for (int i = 0; i < region.getCityAmt(); i++) {
            if (region.getCityPt(i).getNom().equals(name)) {
                the_city = region.getCityPt(i);
                break;
            }
        }
        return the_city;
    }

    public main_character getCharacter(String name) {
        int the_character=0;
        for (int n=0; n<all_MC_nom.length; n++){
            if (name.equals(all_MC_nom[n])) {
                the_character=n;
                break;
            }
        }
        return all_MC[the_character];
    }

    //public cityPt[] getAllCities() { return all_cities;}

    //returns all cities in that region
    public cityPt[] getAllCitiesRegion(String name) {
        regions region = getRegion(name);
        cityPt[] list = new cityPt[region.getCityAmt()];
        for (int i=0; i<list.length; i++) {
            list[i] = region.getCityPt(i);
        }
        return list;
    }

    //the cities that can be see on screen (you can press the icon, doesn't mean you can play any of the chapters... (aka city may be banned)
    public cityPt[] getCities(String name) {
        regions region = getRegion(name);
        cityPt[] list = new cityPt[region.getCityAmt()];
        for (int i = 0; i < list.length; i++) {
            if ( this.PL >= region.getCityPt(i).getPlDiscover() ) list[i] = region.getCityPt(i);
        }
        return list;
    }

    private int region2index(regions region) {
        int index = -1;
        for (int n=0; n<all_regions.length; n++) {
            if (region.getNom().equals(all_regions[n].getNom())) {index = n; break;}
        }
        return index;
    }

    public Chapter getChapter() {
        return this.coorChapter;
    }
}
