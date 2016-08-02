package com.rudie.severin.textadventure;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.mock.MockContext;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.PH;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by erikrudie on 7/21/16.
 */
public class DBInterfacerUnitTest  {

    String firstName = "first";
    String lastName = "last";
    String nickName = "nick";
    Map<String, Integer> skills = new HashMap<>();
    int node = -5;
    int backupFor = -10;


    @Test
    public void helperFunction_characterTableUpdated() {
        MockContext context = new MockContext();
        DBInterfacer helper = DBInterfacer.getInstance(context);
        helper.enterCharacterIntoDb(firstName, lastName, nickName, skills, node, backupFor);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT last_insert_rowid();";
        Cursor idCursor = db.rawQuery(sql, null);
        idCursor.moveToFirst();
        int charId = idCursor.getInt(0);
        idCursor.close();


        Cursor cursor = db.rawQuery("SELECT * FROM " + PH.tbl_character + ";", null);
        
        cursor.moveToFirst();
        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_id)), charId);
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_firstname)), firstName);
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_lastname)), lastName);
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_nickname)), nickName);
        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_at_node)), node);
        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_is_backup_for)), backupFor);
        cursor.close();
    }

    @Test
    public void helperFunction_statsTableUpdated() {
        skills.put(PH.STRENGTH, 5);
        skills.put(PH.AGILITY, 6);
        skills.put(PH.COMRADERY, 7);

        MockContext context = new MockContext();
        DBInterfacer helper = DBInterfacer.getInstance(context);
        helper.enterCharacterIntoDb(firstName, lastName, nickName, skills, node, backupFor);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PH.tbl_statistics + ";", null);

        String sql = "SELECT last_insert_rowid();";
        Cursor idCursor = db.rawQuery(sql, null);
        idCursor.moveToFirst();
        int charId = idCursor.getInt(0);
        idCursor.close();


        cursor.moveToFirst();
        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_statistics_character_id)), charId);
        String thisStat = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_name));
        int thisValue = getThisValue(thisStat);
        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_value)), thisValue);
        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_name)), thisStat);
        cursor.close();
    }

    public int getThisValue(String stat) {
        int value = 0;
        if (stat.equals(PH.STRENGTH)) {
            value = skills.get(PH.STRENGTH);
        } else if (stat.equals(PH.AGILITY)) {
            value = skills.get(PH.AGILITY);
        } else if (stat.equals(PH.COMRADERY)) {
            value = skills.get(PH.COMRADERY);
        }
        return value;
    }



}
