package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.basic_classes.regions;
import com.example.rpg_v4.db_files.RPG_ViewModel;
import com.example.rpg_v4.db_files.User_Values;

import java.util.List;

public class wholeMapActivity extends AppCompatActivity {
    //arg values
    private final String CURRENT_REGION = "CURRENT_REGION";
    private final String PlayerLevel = "PlayerLevel";

    private int pl;
    private PL this_pl;
    private RPG_ViewModel rpgViewModel;
    private User_Values userValues;

    private regions current_region;
    private regions selected_region;

    private View backbtn;
    private View veneland;
    private View pietas;
    private View tossedStones;
    private View HDR;
    private View region6;
    private View region7;
    private View region89;
    private View region10;
    private View region11;
    private View Nebula;
    private View region13;
    private View iceCube;
    private View rupes;
    private View petrasepire;
    private View juslyn;
    private View maceriaUnion;
    private View greatNorth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //eventually need to chnage the world map depending on our PL
        //todo remove testremove
        setContentView(R.layout.activity_whole_map_testtoremove);
        Bundle args = getIntent().getExtras();
        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);
        rpgViewModel.getlUserValues().observe(this,new Observer<List<User_Values>>() {
            @Override
            public void onChanged(@Nullable final List<User_Values> vals) {
                // Update the cached copy of the words in the adapter.
                //nothing to update..
            }
        });
        pl = args.getInt(PlayerLevel);
        this_pl = PL_VendingMachine.getPL(this.pl);
        current_region = this.this_pl.getRegion(args.getString(CURRENT_REGION));
        getAndLoadViews();
        
        backbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                regionSelected();
            }
        });

        veneland.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions veneland_region = this_pl.getRegion("Veneland");
                userValues.setCur_region(veneland_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Veneland Pressed");
                regionSelected();
            }
        });

        pietas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                Toast. makeText(getApplicationContext(),"Pietas Unavailable",Toast. LENGTH_SHORT).show();
                /*
                regions pietas_region = this_pl.getRegion("Pietas");
                userValues.setCur_region(pietas_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Pietas Pressed");
                regionSelected();

                 */
            }
        });
        tossedStones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                Toast. makeText(getApplicationContext(),"Tossed Stones Unavailable",Toast. LENGTH_SHORT).show();
                /*
                regions tossedStones_region = this_pl.getRegion("The Tossed Stones");
                userValues.setCur_region(tossedStones_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Tossed Stones Pressed");
                regionSelected();

                 */
            }
        });
        HDR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                Toast. makeText(getApplicationContext(),"HDR Unavailable",Toast. LENGTH_SHORT).show();
                /*
                regions HDR_region= this_pl.getRegion("Human-Dragon Republic");
                userValues.setCur_region(HDR_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("HDR Pressed");
                regionSelected();

                 */
            }
        });
        /*
        region6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region6_region = this_pl.getRegion("");
                userValues.setCur_region(region6_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Region6 Pressed");
                regionSelected();
            }
        });
        region7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region7_region = this_pl.getRegion("");
                userValues.setCur_region(region7_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("region7 Pressed");
                regionSelected();
            }
        });
        region89.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region89_region = this_pl.getRegion("");
                userValues.setCur_region(region89_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Region89 Pressed");
                regionSelected();
            }
        });
        region10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region10_region = this_pl.getRegion("");
                userValues.setCur_region(region10_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Region10 Pressed");
                regionSelected();
            }
        });
        region11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region11_region = this_pl.getRegion("");
                userValues.setCur_region(region11_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Region11 Pressed");
                regionSelected();
            }
        });
        Nebula.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions nebula_region = this_pl.getRegion("Nebula");
                userValues.setCur_region(nebula_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Nebula Pressed");
                regionSelected();
            }
        });
        region13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions region13_region = this_pl.getRegion("");
                userValues.setCur_region(region13_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Region13 Pressed");
                regionSelected();
            }
        });
        iceCube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions iceCube_region = this_pl.getRegion("Ice Cube");
                userValues.setCur_region(iceCube_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Ice Cube Pressed");
                regionSelected();
            }
        });
        rupes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions rupes_region = this_pl.getRegion("Rupes");
                userValues.setCur_region(rupes_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Rupes Pressed");
                regionSelected();
            }
        });
        petrasepire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions petrasepire_region = this_pl.getRegion("Petrasepire");
                userValues.setCur_region(petrasepire_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Petrasepire Pressed");
                regionSelected();
            }
        });
        juslyn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions juslyn_region = this_pl.getRegion("Juslyn");
                userValues.setCur_region(juslyn_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Juslyn Pressed");
                regionSelected();
            }
        });
        maceriaUnion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions maceriaUnion_region = this_pl.getRegion("Maceria Union");
                userValues.setCur_region(maceriaUnion_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Maceria Union Pressed");
                regionSelected();
            }
        });
        greatNorth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateValues();
                regions greatNorth_region = this_pl.getRegion("Great North");
                userValues.setCur_region(greatNorth_region.getNom());
                rpgViewModel.updateRegion(userValues);
                System.out.println("Greath North Pressed");
                regionSelected();
            }
        });

         */
    }

    public void updateValues() {
        userValues = rpgViewModel.getlUserValues().getValue().get(0);
    }

    public void getViews() {
        backbtn = findViewById(R.id.worldmap_backbtn);
        //if (this.this_pl.getWholeMap == R.layout.activity_whole_map)
        veneland = findViewById(R.id.wmVenelandBtn);

        pietas = findViewById(R.id.wmPietasBtn);
        tossedStones = findViewById(R.id.wmTossedStonesBtn);
        HDR = findViewById(R.id.wmHDRBtn);
        /*
        region6 = findViewById(R.id.wmRegion6Btn);
        region7 = findViewById(R.id.wmRegion7Btn);
        region89 = findViewById(R.id.wmRegion89Btn);
        region10 = findViewById(R.id.wmRegion10Btn);
        region11 = findViewById(R.id.wmRegion11Btn);
        Nebula = findViewById(R.id.wmNebulaBtn);
        region13 = findViewById(R.id.wmRegion13Btn);
        iceCube = findViewById(R.id.wmIceCubeBtn);
        rupes = findViewById(R.id.wmRupesBtn);
        petrasepire = findViewById(R.id.wmPetrasepireBtn);
        juslyn = findViewById(R.id.wmJuslynBtn);
        maceriaUnion = findViewById(R.id.wmMaceriaUnionBtn);
        greatNorth = findViewById(R.id.wmGreatNorthBtn);

         */
    }

    public void getAndLoadViews() {
        backbtn = findViewById(R.id.worldmap_backbtn);
        //if (this.this_pl.getWholeMap == R.layout.activity_whole_map)
        veneland = findViewById(R.id.wmVenelandBtn);
        Glide.with(this).load(R.drawable.wm_veneland).into((PolymorphousButton)veneland);

        pietas = findViewById(R.id.wmPietasBtn);
        Glide.with(this).load(R.drawable.wm_pietas).into((PolymorphousButton)pietas);
        tossedStones = findViewById(R.id.wmTossedStonesBtn);
        Glide.with(this).load(R.drawable.wm_tossedstones).into((PolymorphousButton)tossedStones);
        HDR = findViewById(R.id.wmHDRBtn);
        Glide.with(this).load(R.drawable.wm_hdr).into((PolymorphousButton)HDR);
        /*
        region6 = findViewById(R.id.wmRegion6Btn);
        Glide.with(this).load(R.drawable.wm_region6).into((PolymorphousButton)region6);
        region7 = findViewById(R.id.wmRegion7Btn);
        Glide.with(this).load(R.drawable.wm_region7).into((PolymorphousButton)region7);
        region89 = findViewById(R.id.wmRegion89Btn);
        Glide.with(this).load(R.drawable.wm_region89).into((PolymorphousButton)region89);
        region10 = findViewById(R.id.wmRegion10Btn);
        Glide.with(this).load(R.drawable.wm_region10).into((PolymorphousButton)region10);
        region11 = findViewById(R.id.wmRegion11Btn);
        Glide.with(this).load(R.drawable.wm_region11).into((PolymorphousButton)region11);
        Nebula = findViewById(R.id.wmNebulaBtn);
        Glide.with(this).load(R.drawable.wm_nebula).into((PolymorphousButton)Nebula);
        region13 = findViewById(R.id.wmRegion13Btn);
        Glide.with(this).load(R.drawable.wm_region13).into((PolymorphousButton)region13);
        iceCube = findViewById(R.id.wmIceCubeBtn);
        Glide.with(this).load(R.drawable.wm_icecube).into((PolymorphousButton)iceCube);
        rupes = findViewById(R.id.wmRupesBtn);
        Glide.with(this).load(R.drawable.wm_rupes).into((PolymorphousButton)rupes);
        petrasepire = findViewById(R.id.wmPetrasepireBtn);
        Glide.with(this).load(R.drawable.wm_petrasepire).into((PolymorphousButton)petrasepire);
        juslyn = findViewById(R.id.wmJuslynBtn);
        Glide.with(this).load(R.drawable.wm_juslyn).into((PolymorphousButton)juslyn);
        maceriaUnion = findViewById(R.id.wmMaceriaUnionBtn);
        Glide.with(this).load(R.drawable.wm_maceriaunion).into((PolymorphousButton)maceriaUnion);
        greatNorth = findViewById(R.id.wmGreatNorthBtn);
        Glide.with(this).load(R.drawable.wm_greatnorth).into((PolymorphousButton)greatNorth);

         */
    }
    
    public void regionSelected() {
        Intent intented = new Intent(wholeMapActivity.this, MainMenyuActivity.class);
        startActivity(intented);
        finish();
    }
}
