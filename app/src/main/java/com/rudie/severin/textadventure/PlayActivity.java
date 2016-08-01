package com.rudie.severin.textadventure;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rudie.severin.textadventure.UtilityClasses.ChoiceAdapter;
import com.rudie.severin.textadventure.UtilityClasses.ChoiceData;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;
import com.rudie.severin.textadventure.UtilityClasses.ItemData;
import com.rudie.severin.textadventure.UtilityClasses.PH;

import java.util.List;

public class PlayActivity extends AppCompatActivity {

    private List<ChoiceData> choiceList;
    private RecyclerView rvChoices;
    private ChoiceAdapter adapter;
    private DBInterfacer helper;
    private TextView textviewText;

// inventory needs to be used by the adapter, but updated once every time PlayActivity.setNewNode
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

//        Some basic information regarding the current node is collected here, but most associated
//        logic is found in the setNewNode method
        helper = DBInterfacer.getInstance(this);
        int currentNode = helper.getCurrentNode(currentCharacterId, this);
        textviewText = (TextView) findViewById(R.id.textviewPlayContent);
        rvChoices = (RecyclerView) findViewById(R.id.recyclerviewPlayChoices);
        setNewNode(currentNode);


//  TODO:      make unavailable RBs unclickable

        Button setNextNode = (Button) findViewById(R.id.buttonPlayContinue);
        setNextNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelectedButtonPos() != -1) {
                    int selectedButtonPos = adapter.getSelectedButtonPos();
//                    int nextNode = choiceList.get(selectedButtonPos).getConnectedNode();
//                    setNewNode(nextNode);
                }
            }
        });


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

}
