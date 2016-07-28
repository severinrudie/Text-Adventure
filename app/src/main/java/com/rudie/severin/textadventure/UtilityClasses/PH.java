package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/23/16.
 */
public class PH {

    public static int CURRENT_CHARACTER = 0;

    public static final String STRENGTH = "Strength";
    public static final String AGILITY = "Agility";
    public static final String COMRADERY = "Comradery";
    public static final int STRENGTH_ID = 1;
    public static final int AGILITY_ID = 2;
    public static final int COMRADERY_ID = 3;

    public static final String tbl_choices = "table_choices";
    public static final String tbl_choices_id = "choice_id";
    public static final String tbl_choices_node_id = "node_id";
    public static final String tbl_choices_text = "choice_text";
    public static final String tbl_choices_connected_node = "connected_node";
    public static final String tbl_choices_item_type_required = "item_type_required";
    public static final String tbl_choices_item_type_improves = "item_type_improves";
    public static final String tbl_choices_test_type_id = "test_type_id";
    public static final String tbl_choices_test_difficulty = "test_difficulty";

    public static final String tbl_inventory = "table_inventory";
    public static final String tbl_inventory_id = "item_id";
    public static final String tbl_inventory_name = "item_name";
    public static final String tbl_inventory_power = "item_power";
    public static final String tbl_inventory_type_id = "item_type_id";

    public static final String tbl_statistics = "table_statistics";
    public static final String tbl_statistics_type_id = "stat_type_id";
    public static final String tbl_statistics_stat_name = "stat_name";
    public static final String tbl_statistics_stat_value = "stat_value";
    public static final String tbl_statistics_character_id = "character_id";

    public static final String tbl_nodes = "table_nodes";
    public static final String tbl_nodes_id = "node_id";
    public static final String tbl_nodes_text = "node_text";
    public static final String tbl_nodes_image = "node_image";
    public static final String tbl_nodes_animation = "node_animation";

    public static final String tbl_item_type = "table_item_type";
    public static final String tbl_item_type_id = "item_type_id";
    public static final String tbl_item_type_name = "item_type_name";

    public static final String tbl_character = "table_character";
    public static final String tbl_character_id = "character_id";
    public static final String tbl_character_firstname = "character_first_name";
    public static final String tbl_character_lastname = "character_last_name";
    public static final String tbl_character_nickname = "character_nickname";
    public static final String tbl_character_at_node = "character_at_node";
    public static final String tbl_character_is_backup_for = "character_is_backup_for";

    public static final String[] all_tables = {tbl_choices, tbl_inventory, tbl_statistics,
            tbl_nodes, tbl_character, tbl_item_type};

    public static final String[] create_tables = new String[] {

            "CREATE TABLE " + tbl_choices + " (" +
                    tbl_choices_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_choices_node_id + " integer, " +
                    tbl_choices_text + " text, " +
                    tbl_choices_connected_node + " integer, " +
                    tbl_choices_item_type_required + " integer, " +
                    tbl_choices_item_type_improves + " text, " +
                    tbl_choices_test_type_id + " integer, " +
                    tbl_choices_test_difficulty + " text " +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_inventory +" (" +
                    tbl_inventory_id + " integer PRIMARY KEY AUTOINCREMENT," +
                    tbl_inventory_name + " text," +
                    tbl_inventory_power + " text," +
                    tbl_inventory_type_id + " integer" +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_statistics + " (" +
                    tbl_statistics_type_id + " integer," +
                    tbl_statistics_stat_name + " text," +
                    tbl_statistics_stat_value + " integer," +
                    tbl_statistics_character_id + " integer" +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_nodes + " (" +
                    tbl_nodes_id + " integer," +
                    tbl_nodes_text + " text," +
                    tbl_nodes_image + " text," +
                    tbl_nodes_animation + " text"+
                    ");" +
                    " ",
            "CREATE TABLE " + tbl_item_type + " (" +
                    tbl_item_type_id + " integer PRIMARY KEY AUTOINCREMENT," +
                    tbl_item_type_name + " text" +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_character + " (" +
                    tbl_character_id + " integer PRIMARY KEY AUTOINCREMENT," +
                    tbl_character_firstname + " text," +
                    tbl_character_lastname + " text," +
                    tbl_character_nickname + " text," +
                    tbl_character_at_node + " integer," +
                    tbl_character_is_backup_for + " integer" +
                    ");"};
}
