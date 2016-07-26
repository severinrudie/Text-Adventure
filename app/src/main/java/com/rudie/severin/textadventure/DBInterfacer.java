package com.rudie.severin.textadventure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rudie.severin.textadventure.UtilityClasses.PH;

/**
 * Created by erikrudie on 7/23/16.
 */

/*
All SQL queries are made from this class
 */

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
        for (String string : PH.all_tables) {
            db.execSQL("DROP TABLE IF EXISTS " + string);
        }
        this.onCreate(db);
    }


}
