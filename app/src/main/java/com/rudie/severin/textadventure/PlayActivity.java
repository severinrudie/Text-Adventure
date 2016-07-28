package com.rudie.severin.textadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rudie.severin.textadventure.UtilityClasses.PH;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        int currentCharacterId = getIntent().getIntExtra(PH.CURRENT_CHARACTER, -1);
        if (currentCharacterId == -1) {
            Log.d("SEVCRASH: ", "currentCharacterId is currently set to -1");
            finish();
        }





    }  // end onCreate
}
