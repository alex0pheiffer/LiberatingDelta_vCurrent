package com.example.lib.basic_classes.dialog_phases;

import com.example.lib.basic_classes.Phase;
import com.example.lib.basic_classes.static_character;
import com.example.lib.basic_classes.the_static_characters.staticer_test;

public class ch1_1_phase extends Phase {

    public ch1_1_phase() {
        super(1,1);
        setDialog(new String[] {
                "Yeah this is the first dialog",
                "Then theres this",
                "and now this."
        });
        static_character statty = new staticer_test();
        setCharacters(new static_character[] {statty, statty, statty});
        setBackground(new Integer[] {0,0,0});
    }

}
