package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rpg_v4.basic_classes.Characters;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.db_files.RPG_ViewModel;
import com.example.rpg_v4.db_files.User_Characters;
import com.example.rpg_v4.db_files.User_EQPlayed;
import com.example.rpg_v4.db_files.User_Values;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private View activity_whole;
    private RPG_ViewModel rpgViewModel;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment[] regions_frag = new Fragment[18];

    private class checkUserData {
        private List<User_Values> lValues;
        private List<User_EQPlayed> lEQPlayed;
        private List<User_Characters> lCharacters;
        private final String default_user = "none";
        private final String default_pass = "none";
        public void setlValues(List<User_Values> vals) {
            lValues = vals;
        }
        public void setlCharacters(List<User_Characters> lCharacters) {this.lCharacters = lCharacters;}
        public void setlEQPlayed(List<User_EQPlayed> lEQPlayed) {this.lEQPlayed = lEQPlayed;}

        public checkUserData(RPG_ViewModel rpgViewModel) {
            System.out.println("checkuserdata_created");
            System.out.println("VM: "+rpgViewModel);
            lValues = rpgViewModel.getlUserValues().getValue();
            System.out.println("userValues:"+rpgViewModel.getlUserValues().getValue());
            lEQPlayed = rpgViewModel.getlUserEQPlayed().getValue();
            lCharacters = rpgViewModel.getlUserCharacters().getValue();
        }

        /*
        public regions getCurrentRegion() {
            //returns the current region. Current region can only be changed from the MAP activity, and after certain dialog in Chapters
            //tl;dr wont be changed in this activity
            return PL.getRegion(lValues.get(0).getCur_region());
        }
        */

        public User_Values getlValues() {
            if (lValues.size() > 1) {
                System.out.println("Error. User_Values > 1");
            }
            return lValues.get(0);
        }

        public User_Values changeCharacter(Characters character) {
             return new User_Values(lValues.get(0).getCur_PL(), lValues.get(0).getCur_phase(),lValues.get(0).getCur_okane(),character.getName(),lValues.get(0).getUsername(),lValues.get(0).getPassword(),lValues.get(0).getCur_region());
        }

        public User_Values changeOkane(int okane) {
             return new User_Values(lValues.get(0).getCur_PL(), lValues.get(0).getCur_phase(),okane,lValues.get(0).getFront_char(),lValues.get(0).getUsername(),lValues.get(0).getPassword(),lValues.get(0).getCur_region());
        }

    }

    public SettingsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

            activity_whole = findViewById(R.id.main_menyu_background);
            //region_map_btn_fragment =

            rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);
            System.out.println("in open: "+rpgViewModel.getlUserValues().getValue());
            final checkUserData userDataChecker = new checkUserData(rpgViewModel);

            rpgViewModel.getlUserValues().observe(this,new Observer<List<User_Values>>() {
                @Override
                public void onChanged(@Nullable final List<User_Values> vals) {
                    // charviewmenyu would change this
                    //settings may change this
                    userDataChecker.setlValues(vals);
                }
            });
            rpgViewModel.getlUserEQPlayed().observe(this,new Observer<List<User_EQPlayed>>() {
                @Override
                public void onChanged(@Nullable final List<User_EQPlayed> vals) {
                    // charviewmenyu would change this
                    //settings may change this
                    userDataChecker.setlEQPlayed(vals);
                }
            });
            rpgViewModel.getlUserCharacters().observe(this,new Observer<List<User_Characters>>() {
                @Override
                public void onChanged(@Nullable final List<User_Characters> vals) {
                    // charviewmenyu would change this
                    //settings may change this
                    userDataChecker.setlCharacters(vals);
                }
            });

        }




}
