package com.example.rpg_v4.db_files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rpg_v4.R;

public class NewValuesAct extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditValueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_values);

        mEditValueView = findViewById(R.id.edit_value);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditValueView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String input = mEditValueView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, input);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
