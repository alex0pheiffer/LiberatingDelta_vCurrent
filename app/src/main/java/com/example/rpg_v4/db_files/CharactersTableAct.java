package com.example.rpg_v4.db_files;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rpg_v4.dbCheckerActivity;
import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.adapter_classes.characters_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CharactersTableAct extends AppCompatActivity {


    private RPG_ViewModel rpgViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_table);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final characters_adapter adapter = new characters_adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);

        rpgViewModel.getlUserCharacters().observe(this, new Observer<List<User_Characters>>() {
            @Override
            public void onChanged(@Nullable final List<User_Characters> vals) {
                // Update the cached copy of the words in the adapter.
                adapter.setlData(vals);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CharactersTableAct.this, dbCheckerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
