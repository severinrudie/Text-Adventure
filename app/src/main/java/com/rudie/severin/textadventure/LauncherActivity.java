package com.rudie.severin.textadventure;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;

import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;

public class LauncherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DBInterfacer db = DBInterfacer.getInstance(this);

        Button newGame = (Button) findViewById(R.id.buttonLauncherNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharacterSelectFragment fragment = new CharacterSelectFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayoutLauncher, fragment);
                transaction.commit();
            }
        });



//        TODO: temp code. resets DB on every new instance
        db.dropAllTables();
        db.onCreate(db.getWritableDatabase());
//        TODO: end temp code

    }



}
