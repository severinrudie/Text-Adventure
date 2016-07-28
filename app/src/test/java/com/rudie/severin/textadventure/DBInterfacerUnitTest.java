package com.rudie.severin.textadventure;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import android.widget.TextView;

import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;
import com.rudie.severin.textadventure.UtilityClasses.PH;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by erikrudie on 7/21/16.
 */
public class DBInterfacerUnitTest  {

//    String firstName = "first";
//    String lastName = "last";
//    String nickName = "nick";
//    Map<String, Integer> skills = new HashMap<>();
//    int node = -5;
//    int backupFor = -10;
//    int charId;
//    @Test
//    public void enterCharacterIntoDb_appearsInDb() {
//        skills.put(PH.STRENGTH, 5);
//        skills.put(PH.AGILITY, 6);
//        skills.put(PH.COMRADERY, 7);
//
//        DBInterfacer helper = DBInterfacer.getInstance();
//        helper.dropAllTables();
//
//        helper.enterCharacterIntoDb(firstName, lastName, nickName, skills, node, backupFor);
//
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        String sql = "SELECT last_insert_rowid();";
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToFirst();
//        charId = cursor.getInt(0);
//        cursor.close();
//
//        helperFunction_characterTableUpdated(db.rawQuery("SELECT * FROM " + PH.tbl_character + ";", null), charId);
//        helperFunction_statsTableUpdated(db.rawQuery("SELECT * FROM " + PH.tbl_statistics + ";", null), charId);
//    }
//
//    @Test
//    public void helperFunction_characterTableUpdated(Cursor cursor, int id) {
//        cursor.moveToFirst();
//        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_id)), id);
//        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_firstname)), firstName);
//        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_lastname)), lastName);
//        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_nickname)), nickName);
//        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_at_node)), node);
//        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_character_is_backup_for)), backupFor);
//    }
//
//    @Test
//    public void helperFunction_statsTableUpdated(Cursor cursor, int id) {
//        cursor.moveToFirst();
//        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_statistics_character_id)), id);
//        String thisStat = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_name));
//        int thisValue = getThisValue(thisStat);
//        assertEquals(cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_value)), thisValue);
//        assertEquals(cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_statistics_stat_name)), thisStat);
//    }
//
//    public int getThisValue(String stat) {
//        int value = 0;
//        if (stat.equals(PH.STRENGTH)) {
//            value = skills.get(PH.STRENGTH);
//        } else if (stat.equals(PH.AGILITY)) {
//            value = skills.get(PH.AGILITY);
//        } else if (stat.equals(PH.COMRADERY)) {
//            value = skills.get(PH.COMRADERY);
//        }
//        return value;
//    }



}
