package com.rudie.severin.textadventure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rudie.severin.textadventure.UtilityClasses.PH;

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
        db.execSQL(PH.create_tables);
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

    public void enterCharacterIntoDB(String firstName, String nickName, String lastName, Map<String,
            Integer> skills, Integer atNode, Integer backUpFor) {

        String sql = "INSERT INTO " + PH.tbl_character + " (" + PH.tbl_character_firstname
                + ", " + PH.tbl_character_nickname + ", " + PH.tbl_character_lastname + ", "
                + PH.tbl_character_at_node + ", " + PH.tbl_character_is_backup_for + ") VALUES ('" +
                firstName + "', '" + nickName + "', '" + lastName + "', '" + atNode + "', '" + backUpFor
                + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

        // TODO: insert skills too
    }



}
