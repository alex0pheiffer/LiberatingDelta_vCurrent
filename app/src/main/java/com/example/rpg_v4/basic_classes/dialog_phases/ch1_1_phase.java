package com.example.rpg_v4.basic_classes.dialog_phases;

import com.example.rpg_v4.R;
import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.Phase;
import com.example.rpg_v4.basic_classes.static_character;
import com.example.rpg_v4.basic_classes.the_static_characters.Grendel1;

public class ch1_1_phase extends Phase {

    public ch1_1_phase() {
        super(1,1);
        setDialog(new String[] {
                "Yeah this is the first dialog",
                "Then theres this",
                "and now this."
        });
        static_character statty = new Grendel1();
        setCharacters(new static_character[] {statty, statty, statty});
        setBackground(new Integer[] {R.drawable.zoomed_region_1_3,R.drawable.zoomed_region_1_3,R.drawable.zoomed_region_1_3});
    }

}
