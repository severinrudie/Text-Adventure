package com.rudie.severin.textadventure.UtilityClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

/**
 * Created by erikrudie on 7/23/16.
 */

/*
All SQL queries are made from this class
 */

//    REMINDER: NICKNAMES ARE STORED W/O APOSTRAPHES OR QUOTATION MARKS

public class DBInterfacer extends SQLiteOpenHelper {

    private Context mContext;

    private static final String DATABASE_NAME = "TEXT_GAME_DB";
    private static final int DATABASE_VERSION = 1;

    private static DBInterfacer DB;

    private DBInterfacer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public static DBInterfacer getInstance(Context context) {
        if (DB == null) {
            DB = new DBInterfacer(context);
        }
        return DB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String string : PH.create_tables) {
            db.execSQL(string);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAllTables();
        this.onCreate(db);
    }

    public void dropAllTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        for (String string : PH.all_tables) {
            db.execSQL("DROP TABLE IF EXISTS " + string);
        }
    }

    // Coordinates entering character and stat information into the DB, then returns the new
    // character ID
    public int enterCharacterIntoDb(String firstName, String nickName, String lastName, Map<String,
            Integer> skills, Integer atNode, Integer backUpFor) {

        // this inserts character stats, then returns character ID to allow skill insertions
        int charId = insertCharacterDetails(firstName, nickName, lastName, atNode, backUpFor);
        insertCharacterSkills(charId, skills);
        return charId;
    }

    // This method returns the character ID of the inserted row after inserting character details
    public int insertCharacterDetails(String firstName, String nickName, String lastName,
                                       Integer atNode, Integer backUpFor) {
        String sql = "INSERT INTO " + PH.tbl_character + " (" + PH.tbl_character_firstname
                + ", " + PH.tbl_character_nickname + ", " + PH.tbl_character_lastname + ", "
                + PH.tbl_character_at_node + ", " + PH.tbl_character_is_backup_for + ", "
                + PH.tbl_character_hp + ") VALUES ('" + firstName + "', '" + nickName + "', '"
                + lastName + "', '" + atNode + "', '" + backUpFor + "', '" + PH.STARTING_HP + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

        sql = "SELECT last_insert_rowid();";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int charID = cursor.getInt(0);
        cursor.close();
        return charID;
    }

    // This loops over three arrays (each of size 3), inserting numbers for strength, agility,
    // and comradery
    public void insertCharacterSkills(Integer charId, Map<String, Integer> skills) {
        String[] skillNames = new String[]{PH.STRENGTH, PH.AGILITY, PH.COMRADERY};
        int[] skillIds = new int[] {PH.STRENGTH_ID, PH.AGILITY_ID, PH.COMRADERY_ID};
        int[] skillValues = new int[] {skills.get(PH.STRENGTH), skills.get(PH.AGILITY),
                skills.get(PH.COMRADERY)};

        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < skillNames.length; i++) {
            String sql = skillInsertionConstructor(charId, skillNames[i], skillIds[i], skillValues[i]);
            db.execSQL(sql);
        }
    }

    public String skillInsertionConstructor(int charID, String skillName, int skillId, int value) {
        String sql = "INSERT INTO " + PH.tbl_statistics + " (" + PH.tbl_statistics_character_id
                + ", " + PH.tbl_statistics_stat_name + ", " + PH.tbl_statistics_stat_value + ", "
                + PH.tbl_statistics_type_id + ") VALUES ('" + charID + "', '" + skillName + "', '"
                + value + "', '" + skillId + "');";
        return sql;
    }

}
