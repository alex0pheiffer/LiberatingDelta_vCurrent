package com.example.rpg_v4;

import com.example.rpg_v4.basic_classes.Chapter;
import com.example.rpg_v4.basic_classes.Characters;
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

public class PL {
    private static final int PL = 0;

    private static Veneland veneland = new Veneland(); private static Pietas pietas = new Pietas(); private static TheTossedStones thetossedstones = new TheTossedStones(); private static HDR hdr = new HDR(); private static Nebula nebula = new Nebula(); private static IceCube icecube = new IceCube();
    private static Rupes rupes = new Rupes(); private static Petrasepire petrasepire = new Petrasepire(); private static Juslyn juslyn = new Juslyn(); private static MaceriaUnion maceriaunion = new MaceriaUnion(); private static GreatNorth greatnorth = new GreatNorth();

    private static regions[] all_regions = {veneland, pietas, thetossedstones, hdr, nebula, icecube, rupes, petrasepire, juslyn, maceriaunion, greatnorth};

    private regions[] region_map_unlocked;
    private String description;
    private Weapon[] unlocked_weapons;             //new weapons you could get
    private Cards[] unlocked_cards;                 //new cards you could get
    private inventI[] unlocked_items;               //new items you could get
    private EQ[] unlocked_eq;                       //new eq you could complete
    private regions[] unlocked_region;              //new region you can explore
    private cityPt[] unlocked_city;                 //new cities you could explore
    private Weapon[] banned_weapons;	            //weapons you cannot use
    private Cards[] banned_cards;		            //cards you cannot use
    private inventI[] banned_items;                 //items you cannot use
    private EQ[] banned_eq;                         //eq you cannot currently complete (diff from closed)
    private String[] banned_region;                 //regions you cannot enter
    private String[] banned_city;                   //cities you cannot enter
    private String[] banned_char;                   //characters you cannot currently play ingame
    private Weapon[] required_weapon;               //a weapon you must use
    private Cards[] required_card;                  //any cards you must use
    private inventI[] required_item;                //an item you must use
    private Characters[] required_char;             //a character you must use
    private regions[] current_region;               //region this level takes place
    private cityPt[] current_city;                  //city this level takes place
    private String[][] dialog_list;                 //dialog in order
    private Characters[][] char_dialog_list;        //characters that say the dialog(corresponds)




    /*
    private static class all_cities {

        cityPt[] all_cities_list;

        public all_cities() {
            all_cities_list = make_all();
        }

        private cityPt[] make_all() {
            int citysAmt = 0;
            for (int i=0; i< all_regions.length; i++) {
                citysAmt = citysAmt + all_regions[i].getCityAmt();
            }
            System.out.println("citysAmt: "+citysAmt);
            cityPt[] citylist = new cityPt[citysAmt];
            int counter = 0;
            for (int i=0; i< all_regions.length; i++) {
                for (int j=0; j<all_regions[i].getCityAmt(); j++) {
                    citylist[counter]=all_regions[i].getCityPt(j);
                    System.out.println(all_regions[i].getCityPt(j).getNom()+" has been added.");
                    counter++;
                }
            }
            System.out.println("length: "+citylist.length);
            return citylist;
        }
        public cityPt[] get_all() {
            return all_cities_list;
        }
    }

    private static all_cities allCities = new all_cities();

    private static cityPt[] all_cities;

    private static Chapter[] all_chapters;

    private static List<Chapter> chipperTowneChapters = new ArrayList<Chapter>();
    private static List<Chapter> maleficereMansionChapters = new ArrayList<Chapter>();

    private static String[] all_MC_nom = {"Katherine","Vivian","Delta"};
    private static main_character[] all_MC;
    private static Characters[] all_characters;

    //tobefixedLATER
    private static final int maxPL = 100;
    public PL() {
        all_cities = allCities.get_all();
        all_chapters = new Chapter[]{new chapter_1()};
        all_MC = new main_character[]{new Katherine(), new Vivian(), new Delta()};
        //all_characters = new Characters[]{};
        chipperTowneChapters.add(new chapter_1()); chipperTowneChapters.add(new chapter_2());
        maleficereMansionChapters.add(new chapter_filler());
    }

    //LET US REMEMBER: THERE IS NO '0' PL; IF PL IS USED AS AN INDEX, YOU SUBTRACT 1

    public static regions getRegion(String name) {
        int the_region = 0;
        for (int i = 0; i < all_regions.length; i++) {
            if (all_regions[i].getNom().equals(name))
                the_region = i;
                break;
        }
        return all_regions[the_region];
    }

    public static cityPt getCityPt(String name, regions region) {
        cityPt the_city = null;
        for (int i = 0; i < region.getCityAmt(); i++) {
            if (region.getCityPt(i).getNom().equals(name)) {
                the_city = region.getCityPt(i);
                break;
            }
        }
        return the_city;
    }

    public static main_character getCharacter(String name) {
        int the_character=0;
        for (int n=0; n<all_MC_nom.length; n++){
            if (name.equals(all_MC_nom[n])) {
                the_character=n;
                break;
            }
        }
        return all_MC[the_character];
    }

    public static Chapter getChapter(int PL) {
        return all_chapters[PL-1];
    }

    public static cityPt[] getAllCities() { return all_cities;}

    public static cityPt[] getAllCitiesRegion(String name) {
        regions region = getRegion(name);
        cityPt[] list = new cityPt[region.getCityAmt()];
        for (int i=0; i<list.length; i++) {
            list[i] = region.getCityPt(i);
        }
        return list;
    }

    public static cityPt[] getCities(int PL, String name) {
        regions region = getRegion(name);
        System.out.println("getCities: "+PL);
        cityPt[] list = new cityPt[region.getCityAmt()];
        for (int i = 0; i < list.length; i++) {
            if ( !(getChapter(PL).isBannedCity(region.getCityPt(i).toString())) ) list[i] = region.getCityPt(i);
        }
        return list;
    }

    public static int region2index(regions region) {
        int index = -1;
        for (int n=0; n<all_regions.length; n++) {
            if (region.getNom().equals(all_regions[n].getNom())) {index = n; break;}
        }
        return index;
    }

    public static boolean regionUnlocked(int PL, String name) {
        //this will need to be modified to take into account that HDR disappears later on.
        if (getRegion(name).getUnlock_level() > PL) return false;
        else return true;
    }

    public static int getMaxPL() {
        return maxPL;
    }
     */


}
