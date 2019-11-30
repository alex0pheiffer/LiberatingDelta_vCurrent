package com.example.rpg_v4.basic_classes;

public abstract class Phase extends phase_objects{

    String[] dialog;
    static_character[] characters;
    Integer[] img_type; //for the static character's image type
    Integer[] background; //because we put id's in here

    public Phase(int pl, int phase) {
        super(pl, phase);
    }

    public String getDialog(int index) { return dialog[index]; }
    public static_character getCharacter(int index) { return characters[index]; }
    public int getImg_type(int index) { return img_type[index]; }
    public int getBackground(int index) { return background[index]; }
    public void setDialog(String[] dialog) { this.dialog = dialog; }
    public void setCharacters(static_character[] characters) { this.characters = characters; }
    public void setImg_type(Integer[] imgs) { this.img_type = imgs; }
    public void setBackground(Integer[] background) { this.background = background; }
    public String toString() {return "phase "+getPL()+":"+getPhase();}
}
