package com.example.rpg_v4.db_files;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rpg_v4.dbCheckerActivity;
import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.adapter_classes.inventory_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InventoryTableAct extends AppCompatActivity {


    private RPG_ViewModel rpgViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_table);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final inventory_adapter adapter = new inventory_adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);


        rpgViewModel.getlUserInventory().observe(this, new Observer<List<User_Inventory>>() {
            @Override
            public void onChanged(@Nullable final List<User_Inventory> vals) {
                // Update the cached copy of the words in the adapter.
                adapter.setlData(vals);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryTableAct.this, dbCheckerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
