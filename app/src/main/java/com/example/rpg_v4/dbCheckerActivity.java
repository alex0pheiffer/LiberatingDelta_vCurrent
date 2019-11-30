package com.example.rpg_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rpg_v4.db_files.CardsTableAct;
import com.example.rpg_v4.db_files.CharactersTableAct;
import com.example.rpg_v4.db_files.DecksTableAct;
import com.example.rpg_v4.db_files.EQPlayedTableAct;
import com.example.rpg_v4.db_files.InventoryTableAct;
import com.example.rpg_v4.db_files.ValuesTableAct;

public class dbCheckerActivity extends AppCompatActivity {

    TextView valuesBtn, charactersBtn, inventoryBtn, cardsBtn, decksBtn, eqplayedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_checker);

        valuesBtn = findViewById(R.id.btnValues);
        charactersBtn = findViewById(R.id.btnCharacters);
        inventoryBtn = findViewById(R.id.btnInventory);
        cardsBtn = findViewById(R.id.btnCards);
        decksBtn = findViewById(R.id.btnDecks);
        eqplayedBtn = findViewById(R.id.btnEQPlayed);


        valuesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, ValuesTableAct.class);
                startActivity(a);
                finish();
            }
        });
        charactersBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, CharactersTableAct.class);
                startActivity(a);
                finish();
            }
        });
        inventoryBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, InventoryTableAct.class);
                startActivity(a);
                finish();
            }
        });
        cardsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, CardsTableAct.class);
                startActivity(a);
                finish();
            }
        });
        decksBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, DecksTableAct.class);
                startActivity(a);
                finish();
            }
        });
        eqplayedBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(dbCheckerActivity.this, EQPlayedTableAct.class);
                startActivity(a);
                finish();
            }
        });
    }
}
