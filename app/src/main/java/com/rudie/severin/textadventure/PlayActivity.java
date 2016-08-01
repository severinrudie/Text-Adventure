package com.rudie.severin.textadventure;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rudie.severin.textadventure.UtilityClasses.ChoiceAdapter;
import com.rudie.severin.textadventure.UtilityClasses.ChoiceData;
import com.rudie.severin.textadventure.UtilityClasses.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;
import com.rudie.severin.textadventure.UtilityClasses.PH;

import java.util.HashMap;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private List<ChoiceData> choiceList;
    private RecyclerView rvChoices;
    private ChoiceAdapter adapter;
    private DBInterfacer helper;
    private TextView textviewText;

// Inventory needs to be used by the adapter, but updated once every time PlayActivity.setNewNode
// is called.  Private static List is maintained in InventoryActivity.  This is flushed and rebuilt
// by a sql query from DBInterfacer every time the inventory is changed.  All other activities get
// inventory information from InventoryActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        int currentCharacterId = getIntent().getIntExtra(PH.CURRENT_CHARACTER, -1);
        if (currentCharacterId == -1) {
            Log.e("SEVCRASH: ", "currentCharacterId is set to -1");
            finish();
        }
// New character, so new inventory is loaded from the DB.  This also sets a boolean that lets
// ChoiceAdapter know to update its knowlege of the inventory the next time it binds a view
        CurrentInventoryAndStats.refreshFromDb(currentCharacterId, this);

//  Some basic information regarding the current node is collected here, but most associated
//  logic is found in the setNewNode method
        helper = DBInterfacer.getInstance(this);
        int currentNode = helper.getCurrentNode(currentCharacterId, this);
        textviewText = (TextView) findViewById(R.id.textviewPlayContent);
        rvChoices = (RecyclerView) findViewById(R.id.recyclerviewPlayChoices);
        setNewNode(currentNode);

        Button setNextNode = (Button) findViewById(R.id.buttonPlayContinue);
        setNextNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelectedButtonPos() != -1) {
                    int selectedButtonPos = adapter.getSelectedButtonPos();
                    ChoiceData selectedChoice = choiceList.get(selectedButtonPos);
                    int testType = selectedChoice.getTestType();
                    int testDifficulty = selectedChoice.getDifficulty();
                    HashMap<Integer, Integer> charStats = CurrentInventoryAndStats.getCurrentStats();
                    int testedValue = charStats.get(testType);
                    testedValue += CurrentInventoryAndStats.getBestValueForTest(testType);
                    if ((testType == -1) || testedValue >= testDifficulty ) {
                        // TODO: this currently checks if your stat is high enough, but does not factor in item improvements to stats
                        int nextNode = choiceList.get(selectedButtonPos).getConnectedSuccessNode();
                        setNewNode(nextNode);
                    } else {
                        int nextNode = choiceList.get(selectedButtonPos).getConnectedFailNode();
                        setNewNode(nextNode);
                    }
                }
            }
        });  // END setNextNode.setOnClickListener


    }  // end onCreate

    private void setNewNode(int nodeId) {
        // image
        // animation
        if (choiceList == null) {
            choiceList = helper.getAvailableChoices(nodeId, this);
            adapter = new ChoiceAdapter(this, choiceList);
            rvChoices.setAdapter(adapter);
            rvChoices.setLayoutManager(new LinearLayoutManager(this));
        } else {
            choiceList.clear();
            choiceList.addAll(helper.getAvailableChoices(nodeId, this));
            adapter.notifyDataSetChanged();
        }
        String nodeText = helper.getCurrentNodeText(nodeId, this);
        textviewText.setText(nodeText);
    }


    // BEGIN getters and setters

}
