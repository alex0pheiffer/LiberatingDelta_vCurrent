package com.example.rpg_v4.db_files;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rpg_v4.R;
import com.example.rpg_v4.db_files.adapter_classes.values_adapter;

import java.util.List;

public class ValuesTableAct extends AppCompatActivity {

    private RPG_ViewModel rpgViewModel;

    //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_table);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final values_adapter adapter = new values_adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);


        rpgViewModel.getlUserValues().observe(this, new Observer<List<User_Values>>() {
            @Override
            public void onChanged(@Nullable final List<User_Values> vals) {
                // Update the cached copy of the words in the adapter.
                System.out.println("updating values");
                //adapter.setlData(vals);
            }
        });
    }
        /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValuesTableAct.this, dbCheckerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    */
/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User_Values useval = new User_Values(0,0,0,"Thalass",data.getStringExtra(NewValuesAct.EXTRA_REPLY),"pass");
            rpgViewModel.insert(useval);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
 */

}
