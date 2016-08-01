package com.rudie.severin.textadventure.UtilityClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
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
        for (String[] sArray : PH.nodeDetails) {
            insertNodeDetails(sArray[0], sArray[1], sArray[2], sArray[3]);
        }
        for (ChoiceData data : PH.choiceDetails) {
            int[] i = data.getInts();
            insertChoiceDetails(data.getText(), i[0], i[1], i[2], i[3], i[4], i[5], i[6]);
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
        firstName = cleanTextForDb(firstName);
        nickName = cleanTextForDb(nickName);
        lastName = cleanTextForDb(lastName);
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

    public void insertNodeDetails(String nodeId, String text, String image, String animation) {
        text = cleanTextForDb(text);
        String sql = "INSERT INTO " + PH.tbl_nodes + " (" + PH.tbl_nodes_id + ", "
                + PH.tbl_nodes_text + ", " + PH.tbl_nodes_image + ", " + PH.tbl_nodes_animation
                + ") VALUES ('" + nodeId + "', '" + text + "', '" + image + "', '" + animation
                + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertChoiceDetails(String text, int nodeId, int connectedSuccess, int connectedFail, int itemRequired,
                                    int itemImproves, int testType, int difficulty) {
        text = cleanTextForDb(text);
        String sql = "INSERT INTO " + PH.tbl_choice + " (" + PH.tbl_choice_node_id + ", "
                + PH.tbl_choice_text + ", " + PH.tbl_choice_connected_success_node + ", "
                + PH.tbl_choice_connected_fail_node + ", " + PH.tbl_choice_item_type_required + ", "
                + PH.tbl_choice_item_type_improves + ", " + PH.tbl_choice_test_type_id + ", "
                + PH.tbl_choice_test_difficulty + ") VALUES ('" + nodeId + "', '" + text + "', '"
                + connectedSuccess  + "', '" + connectedFail  + "', '" + itemRequired + "', '"
                + itemImproves + "', '" + testType + "', '" + difficulty + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String cleanTextForDb(String string) {
        string = string.replace("'", "''");
        string = string.replace("\"", "\\\"");
        return string;
    }

    // get current node from table_character using character_id
    public int getCurrentNode(int currentCharacterId, Context context) {
        String sql = "SELECT " + PH.tbl_character_at_node + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_id + " = '" + currentCharacterId + "';";
        DBInterfacer helper = DBInterfacer.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int currentNode = cursor.getInt(0);
        cursor.close();
        return currentNode;
    }

    // get node text from table_nodes using node_id
    public String getCurrentNodeText(int currentNode, Context context) {
        String sql = "SELECT " + PH.tbl_nodes_text + " FROM " + PH.tbl_nodes + " WHERE "
                + PH.tbl_nodes_id + " = '" + currentNode + "';";
        DBInterfacer helper = DBInterfacer.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String nodeText = cursor.getString(0);
        cursor.close();
        return nodeText;
    }

    public List<ChoiceData> getAvailableChoices(int nodeId, Context context) {
        String sql = "SELECT * FROM " + PH.tbl_choice + " WHERE " + PH.tbl_choice_node_id + " = '"
                + nodeId + "';";
        DBInterfacer helper = DBInterfacer.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<ChoiceData> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            String text = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_choice_text));
            int connectedSuccessNode = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_connected_success_node));
            int connectedFailNode = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_connected_fail_node));
            int itemReq = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_item_type_required));
            int itemImp = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_item_type_improves));
            int testType = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_test_type_id));
            int difficulty = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_test_difficulty));
            list.add(new ChoiceData(text, nodeId, connectedSuccessNode, connectedFailNode, itemReq,
                    itemImp, testType, difficulty));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

//    public static final String tbl_inventory = "table_inventory";
//    public static final String tbl_inventory_id = "inventory_id";
//    public static final String tbl_inventory_name = "inventory_name";
//    public static final String tbl_inventory_power = "inventory_power";
//    public static final String tbl_inventory_type_id = "inventory_type_id";
//    public static final String tbl_inventory_character_id = "inventory_character_id";

    public List<ItemData> getCharacterInventory (int charId, Context context) {
        String sql = "SELECT * FROM " + PH.tbl_inventory + " WHERE " + PH.tbl_inventory_character_id + " = '"
                + charId + "';";
        DBInterfacer helper = DBInterfacer.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<ItemData> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_id));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_inventory_name));
            int itemPower = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_power));
            int itemTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_type_id));
            int itemOwner = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_character_id));
            list.add(new ItemData(itemId, itemName, itemPower, itemTypeId, itemOwner));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
