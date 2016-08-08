package com.rudie.severin.textadventure.DatabaseClasses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.rudie.severin.textadventure.InformationHolders.ChoiceData;
import com.rudie.severin.textadventure.InformationHolders.CurrentInventoryAndStats;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.InformationHolders.PH;

import java.util.ArrayList;
import java.util.HashMap;
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
        PH.cleanItemsForDb();
        PH.replaceNames();

        for (String string : PH.create_tables) {
            db.execSQL(string);
        }
        for (String[] sArray : PH.nodeDetails) {
            insertNodeDetails(db, sArray[0], sArray[1], sArray[2], sArray[3]);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (ChoiceData data : PH.choiceDetails) {
            int[] i = data.getInts();
            insertChoiceDetails(db, data.getText(), i[0], i[1], i[2], i[3], i[4], i[5], i[6], i[7]);
        }
        for (String[] sArray : PH.popupDetails) {
            insertPopupDetails(db, sArray[0], sArray[1], sArray[2], sArray[3], sArray[4], sArray[5]);
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
        String cleanedBackUp;
        if (backUpFor == null) {
            cleanedBackUp = PH.NULL;
        } else {
            cleanedBackUp = backUpFor.toString();
        }
        String sql = "INSERT INTO " + PH.tbl_character + " (" + PH.tbl_character_firstname
                + ", " + PH.tbl_character_nickname + ", " + PH.tbl_character_lastname + ", "
                + PH.tbl_character_at_node + ", " + PH.tbl_character_is_backup_for + ", "
                + PH.tbl_character_hp + ") VALUES ('" + firstName + "', '" + nickName + "', '"
                + lastName + "', '" + atNode + "', '" + cleanedBackUp + "', '" + PH.STARTING_HP + "');";
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

    public void insertNodeDetails(SQLiteDatabase db, String nodeId, String text, String image, String animation) {
        text = cleanTextForDb(text);
        String sql = "INSERT INTO " + PH.tbl_nodes + " (" + PH.tbl_nodes_id + ", "
                + PH.tbl_nodes_text + ", " + PH.tbl_nodes_image + ", " + PH.tbl_nodes_animation
                + ") VALUES ('" + nodeId + "', '" + text + "', '" + image + "', '" + animation
                + "');";
//        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertChoiceDetails(SQLiteDatabase db, String text, int nodeId, int toNode,
                                    int successPopup, int failPopup, int itemRequired,
                                    int itemImproves, int testType, int difficulty) {
        text = cleanTextForDb(text);
        String sql = "INSERT INTO " + PH.tbl_choice + " (" + PH.tbl_choice_node_id + ", "
                + PH.tbl_choice_to_node_id + ", "
                + PH.tbl_choice_text + ", " + PH.tbl_choice_connected_success_popup + ", "
                + PH.tbl_choice_connected_fail_popup + ", " + PH.tbl_choice_item_type_required + ", "
                + PH.tbl_choice_item_type_improves + ", " + PH.tbl_choice_test_type_id + ", "
                + PH.tbl_choice_test_difficulty + ") VALUES ('" + nodeId + "', '" + toNode + "', '" + text + "', '"
                + successPopup  + "', '" + failPopup  + "', '" + itemRequired + "', '"
                + itemImproves + "', '" + testType + "', '" + difficulty + "');";
//        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertPopupDetails(SQLiteDatabase db, String popId, String text, String image, String animation,
                                   String damageTaken, String itemReceived) {
        text = cleanTextForDb(text);
        String sql = "INSERT INTO " + PH.tbl_popup + " (" + PH.tbl_popup_id + ", "
                + PH.tbl_popup_text + ", " + PH.tbl_popup_image + ", " + PH.tbl_popup_animation + ", "
                + PH.tbl_popup_damage + ", " + PH.tbl_popup_item + ") VALUES ('" + popId + "', '"
                + text + "', '" + image + "', '" + animation + "', '" + damageTaken + "', '"
                + itemReceived + "');";
//        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public static String cleanTextForDb(String string) {
        string = string.replace("'", "''");
        string = string.replace("\"", "\\\"");
        return string;
    }

    // get current node from table_character using character_id
    public int getCurrentNode(int currentCharacterId) {
        String sql = "SELECT " + PH.tbl_character_at_node + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_id + " = '" + currentCharacterId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int currentNode = cursor.getInt(0);
        cursor.close();
        return currentNode;
    }

    // get node text from table_nodes using node_id
    public String getCurrentNodeText(int currentNode) {
        String sql = "SELECT " + PH.tbl_nodes_text + " FROM " + PH.tbl_nodes + " WHERE "
                + PH.tbl_nodes_id + " = '" + currentNode + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String nodeText = cursor.getString(0);
        cursor.close();
        return nodeText;
    }

    public String getCurrentNodeImage(int currentNode) {
        String sql = "SELECT " + PH.tbl_nodes_image + " FROM " + PH.tbl_nodes + " WHERE "
                + PH.tbl_nodes_id + " = '" + currentNode + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String nodeImage = cursor.getString(0);
        cursor.close();
        return nodeImage;
    }

    public List<ChoiceData> getAvailableChoices(int nodeId, Context context) {
        String sql = "SELECT * FROM " + PH.tbl_choice + " WHERE " + PH.tbl_choice_node_id + " = '"
                + nodeId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<ChoiceData> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int toNode = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_to_node_id));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_choice_text));
            int connectedSuccessPopup = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_connected_success_popup));
            int connectedFailPopup = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_connected_fail_popup));
            int itemReq = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_item_type_required));
            int itemImp = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_item_type_improves));
            int testType = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_test_type_id));
            int difficulty = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_choice_test_difficulty));
            list.add(new ChoiceData(text, nodeId, toNode, connectedSuccessPopup, connectedFailPopup, itemReq,
                    itemImp, testType, difficulty));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<ItemData> getCharacterInventory (int charId) {
        String sql = "SELECT * FROM " + PH.tbl_inventory + " WHERE " + PH.tbl_inventory_character_id + " = '"
                + charId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<ItemData> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
//            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_id));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_inventory_name));
            int itemPower = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_power));
            int itemTypeId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_type_id));
            int itemStatId = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_stat_id));
            int itemOwner = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_inventory_character_id));
            String acquisitionText = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_inventory_acquisition_text));
            list.add(new ItemData(itemName, itemPower, itemTypeId, itemStatId, itemOwner,
                    acquisitionText));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public HashMap<Integer, Integer> getStatsForCharacter (int charId) {
        HashMap<Integer, Integer> statMap = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        statMap.put(PH.STRENGTH_ID, getSingleCharacterStat(charId, PH.STRENGTH_ID));
        statMap.put(PH.AGILITY_ID, getSingleCharacterStat(charId, PH.AGILITY_ID));
        statMap.put(PH.COMRADERY_ID, getSingleCharacterStat(charId, PH.COMRADERY_ID));

        return statMap;
    }

//    private int getSingleCharacterStat(int charId, SQLiteDatabase db, int statId) {
    private int getSingleCharacterStat(int charId, int statId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + PH.tbl_statistics_stat_value + " FROM " + PH.tbl_statistics
                + " WHERE " + PH.tbl_statistics_character_id + " = '" + charId + "' AND "
                + PH.tbl_statistics_type_id + " = '" + statId + "';";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int value = cursor.getInt(0);
        cursor.close();
        return value;
    }

    public String[] getCharacterFirstNickLast(int charId, Context context) {
        String[] firstNickLast = new String[]{PH.NULL, PH.NULL, PH.NULL};
        String sql = "SELECT " + PH.tbl_character_firstname + ", " + PH.tbl_character_nickname + ", "
                + PH.tbl_character_lastname + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_id + " = '" + charId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        firstNickLast[0] = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_firstname));
        firstNickLast[1] = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_nickname));
        firstNickLast[2] = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_character_lastname));
        firstNickLast = removeNullNames(firstNickLast);
        return firstNickLast;
    }

    private String[] removeNullNames(String[] firstNickLast) {
        if (firstNickLast[1].equals(PH.NULL)) {
            firstNickLast[1] = firstNickLast[0];
        }
        if (firstNickLast[2].equals(PH.NULL)) {
            firstNickLast[2] = firstNickLast[0];
        }
        return firstNickLast;
    }

    public Bundle getPopupData(int popupId) {
        String sql = "SELECT * FROM " + PH.tbl_popup + " WHERE " + PH.tbl_popup_id
                + " = '" + popupId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String text = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_popup_text));
        int damage = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_popup_damage));
        int item = cursor.getInt(cursor.getColumnIndexOrThrow(PH.tbl_popup_item));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(PH.tbl_popup_image));
        Bundle bundle = new Bundle();
        bundle.putString(PH.tbl_popup_text, text);
        bundle.putInt(PH.tbl_popup_damage, damage);
        bundle.putInt(PH.tbl_popup_item, item);
        bundle.putString(PH.tbl_popup_image, image);
        return bundle;
    }

    public void setCharacterAtNode(int newNode, int charId) {
        String sql = "UPDATE " + PH.tbl_character + " SET " + PH.tbl_character_at_node + " = "
                + newNode + " WHERE " + PH.tbl_character_id + " = '" + charId + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        System.out.println("");
    }

    public void setCharacterDamageDealt(int damageDealt, int charId) {
        int hp = getCharacterHp(charId);
        hp -= damageDealt;
        String sql = "UPDATE " + PH.tbl_character + " SET " + PH.tbl_character_hp + " = "
                + hp + " WHERE " + PH.tbl_character_id + " = '" + charId + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public int getCharacterHp(int charId) {
        String sql = "SELECT " + PH.tbl_character_hp + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_id + " = '" + charId + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int hp = cursor.getInt(0);
        cursor.close();
        return hp;
    }

    public void upgradeCharacterStat(int charId, int statId) {
        int value = getSingleCharacterStat(charId, statId);
        value ++;
        String sql = "UPDATE " + PH.tbl_statistics + " SET " + PH.tbl_statistics_stat_value + " = "
                + value + " WHERE " + PH.tbl_statistics_character_id + " = '" + charId + "' AND "
                + PH.tbl_statistics_type_id + " = '" + statId + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addItemToInventory(ItemData item) {
        String name = item.getItemName();
        int power = item.getItemPower();
        int type = item.getItemTypeId();
        int statId = item.getItemStatId();
        int charId = item.getItemOwnerId();
        String acquireText = item.getAcquireText();

        String sql = "INSERT INTO " + PH.tbl_inventory + " (" + PH.tbl_inventory_name + ", "
                + PH.tbl_inventory_power + ", " + PH.tbl_inventory_type_id + ", " + PH.tbl_inventory_stat_id + ", "
                + PH.tbl_inventory_character_id + ", " + PH.tbl_inventory_acquisition_text
                + ") VALUES ('" + name + "', '" + power + "', '" + type + "', '" + statId + "', '" + charId + "', '"
                + acquireText + "');";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void giveCharacterStartingInventory(int charId) {
        ItemData knife = new ItemData("Laser Knife", 0, 0, 0, charId, "");
        ItemData nunchucks = new ItemData("Quantum Nunchucks", 0, 0, 0, charId, "");

        addItemToInventory(knife);
        addItemToInventory(nunchucks);
    }

    public List<Integer> getUniqueCharacterIds() {
        String sql = "SELECT " + PH.tbl_character_id + " FROM " + PH.tbl_character + " WHERE "
                + PH.tbl_character_is_backup_for + " = '" + PH.NULL + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<Integer> charIds = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int newId = cursor.getInt(0);
            charIds.add(newId);
            cursor.moveToNext();
        }
        return charIds;
    }

}
