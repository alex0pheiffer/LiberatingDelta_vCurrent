package com.example.rpg_v4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rpg_v4.basic_classes.PL;
import com.example.rpg_v4.db_files.RPG_ViewModel;
import com.example.rpg_v4.db_files.User_Values;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText loginBox, passBox;
    private TextView loginMsgText;
    private RPG_ViewModel rpgViewModel;
    private boolean newUser;
    
    private class checkLogin {
        private List<User_Values> lValues;
        private final String default_user = "none";
        private final String default_pass = "none";
        public checkLogin() {
            System.out.println("CHECKLOGIN CREATED");
            sendMessage("Checklogin Created");
        }
        public void setlValues(List<User_Values> vals) {
            lValues = vals;
        }
        public boolean checkForDefault() {
            //returns if the first (only) db entry does not have a set user and password
            boolean defaultt = false;
            if (lValues.get(0).getUsername().equals(default_user)) {
                defaultt = true;
            }
            else if(lValues.get(0).getPassword().equals(default_pass)) {
                defaultt = true;
            }
            return defaultt;
        }
        public boolean loginValidity(String username, String password) {
            //to check if the given NEW user and pass are VALID usernames and passwords
            //REQUIREMENTS:
            //longer than 2 characters each; cannot == "none"
            if (lValues.size() > 1)
                System.out.println("HUMAN_ERROR: MORE THAN ONE USERNAME");
            boolean validity = true;
            if (username.equals(default_user)) {
                validity = false;
            }
            else if(password.equals(default_pass)) {
                validity = false;
            }
            else if(username.length() < 3) {
                validity = false;
            }
            else if(password.length() < 3) {
                validity = false;
            }
            return validity;
        }
        public boolean loginOK(String username, String password) {
            boolean loginOK = true;
            /*
            if (checkForDefault()) {
                loginOK = false;
            }
             */
            if (!(username.equals(lValues.get(0).getUsername()))) {
                loginOK = false;
            }
            else if (!(password.equals(lValues.get(0).getPassword()))) {
                loginOK = false;
            }
            return loginOK;
        }
        public List<User_Values> getlValues() {
            return lValues;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessage("MainActivityCreated");
        //create once to trigger setters...
        //final PL staticplayerlevel = new PL();

        final checkLogin loginChecker = new checkLogin();

        rpgViewModel = new ViewModelProvider(this).get(RPG_ViewModel.class);
        sendMessage("Viewmodel Created");

        rpgViewModel.getlUserValues().observe(this,new Observer<List<User_Values>>() {
            @Override
            public void onChanged(@Nullable final List<User_Values> vals) {
                // Update the cached copy of the words in the adapter.
                loginChecker.setlValues(vals);
            }
        });

        loginBox = findViewById(R.id.loginBox);
        passBox = findViewById(R.id.passwordBox);
        loginMsgText = findViewById(R.id.loginMsgText);

        final Button button = findViewById(R.id.loginSubmitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intented = new Intent(MainActivity.this, MainMenyuActivity.class);
                if (TextUtils.isEmpty(loginBox.getText()) || TextUtils.isEmpty(passBox.getText())) {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_valid,
                            Toast.LENGTH_LONG).show();
                }
                else {
                    String user = loginBox.getText().toString();
                    String pass = passBox.getText().toString();

                    if (!newUser) {
                        if (loginChecker.checkForDefault()) {
                            newUser = true;
                        } else {
                            if (!(loginChecker.loginOK(user, pass))) {
                                //the login is not correct
                                loginMsgText.setTextColor(getResources().getColor(R.color.genericIncorrect));
                                loginMsgText.setText(R.string.loginNoUser);
                            } else {
                                startActivity(intented);
                                finish();
                            }
                        }
                    }
                    if (newUser) {
                        //the user is new: input is a new username and password
                        //username REQUIREMENTS: cannot == "none"
                        //password  REQUIREMENTS: cannot == "none"
                        if (!loginChecker.loginValidity(user,pass)) {
                            loginMsgText.setTextColor(getResources().getColor(R.color.genericIncorrect));
                            loginMsgText.setText(R.string.loginNewUserRequirements);
                        }
                        else {
                            //good username and password:
                            //save them by changing the current values in the User_Values list
                            //NOTE: UPDATING THE USERNAME REQUIRES YOU TO PUT THE NEW USERNAME IN THE PASSWORD SLOT OF THE USER_VALUES OBJECT
                            User_Values newLogin = loginChecker.getlValues().get(0);
                            newLogin.setUsername(user);
                            newLogin.setPassword(pass);
                            rpgViewModel.updateUsername(newLogin);
                            rpgViewModel.updatePassword(newLogin);
                            newUser = false;
                            System.out.println("before open: "+rpgViewModel.getlUserValues().getValue());
                            startActivity(intented);
                            System.out.println("before close: "+rpgViewModel.getlUserValues().getValue());
                            finish();
                            System.out.println("after close: "+rpgViewModel.getlUserValues().getValue());
                        }
                    }
                }
            }
        });

    }

    public void sendMessage(String message){
        Intent intent = new Intent();
        intent.setClassName("com.example.twoactivitycrash", "com.example.twoactivitycrash.MyBroadcastReceiver");
        intent.setAction("com.example.twoactivitycrash.MyBroadcastReceiver");
        intent.putExtra("MESSAGE_A", message);
        sendBroadcast(intent);
    }
}
