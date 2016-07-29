package com.rudie.severin.textadventure;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;
import com.rudie.severin.textadventure.UtilityClasses.PH;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        int currentCharacterId = getIntent().getIntExtra(PH.CURRENT_CHARACTER, -1);
        if (currentCharacterId == -1) {
            Log.d("SEVCRASH: ", "currentCharacterId is set to -1");
            finish();
        }

        int currentNode = getCurrentNode(currentCharacterId);
        String nodeText = getCurrentNodeText(currentNode);

        TextView textviewText = (TextView) findViewById(R.id.textviewPlayContent);
        textviewText.setText(nodeText);



    }  // end onCreate

    private int getCurrentNode(int currentCharacterId) {
        // get current node from table_character using character_id
        String sql = "SELECT " + PH.tbl_character_at_node + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_id + " = '" + currentCharacterId + "';";
        DBInterfacer helper = DBInterfacer.getInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int currentNode = cursor.getInt(0);
        cursor.close();
        return currentNode;
    }

    private String getCurrentNodeText(int currentNode) {
        // get node text from table_nodes using node_id
        String sql = "SELECT " + PH.tbl_nodes_text + " FROM " + PH.tbl_nodes + " WHERE "
                + PH.tbl_nodes_id + " = '" + currentNode + "';";
        DBInterfacer helper = DBInterfacer.getInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String nodeText = cursor.getString(0);
        cursor.close();
        return nodeText;
    }

}
