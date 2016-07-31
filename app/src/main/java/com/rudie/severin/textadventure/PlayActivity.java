package com.rudie.severin.textadventure;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.rudie.severin.textadventure.UtilityClasses.ChoiceAdapter;
import com.rudie.severin.textadventure.UtilityClasses.ChoiceData;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;
import com.rudie.severin.textadventure.UtilityClasses.PH;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    List<ChoiceData> choiceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        int currentCharacterId = getIntent().getIntExtra(PH.CURRENT_CHARACTER, -1);
        if (currentCharacterId == -1) {
            Log.d("SEVCRASH: ", "currentCharacterId is set to -1");
            finish();
        }

        DBInterfacer helper = DBInterfacer.getInstance(this);
        // get current nodeId and node.text from the DB
        int currentNode = helper.getCurrentNode(currentCharacterId, this);
        String nodeText = helper.getCurrentNodeText(currentNode, this);

        TextView textviewText = (TextView) findViewById(R.id.textviewPlayContent);
        textviewText.setText(nodeText);


        // RecyclerView code
        RecyclerView rvChoices = (RecyclerView) findViewById(R.id.recyclerviewPlayChoices);

        choiceList = helper.getAvailableChoices(currentNode, this);
        ChoiceAdapter adapter = new ChoiceAdapter(this, choiceList);
        rvChoices.setAdapter(adapter);
        rvChoices.setLayoutManager(new LinearLayoutManager(this));



    }  // end onCreate

}
