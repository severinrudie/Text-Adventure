package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/23/16.
 */
// Parameter Holder, where the unending sea of constants lives
public class PH {

    // BEGIN non-final variables
    public static String CURRENT_CHARACTER = "CURRENT_CHARACTER";
    // END non-final variables

    // BEGIN character insertion constants
    public static final String STRENGTH = "Strength";
    public static final String AGILITY = "Agility";
    public static final String COMRADERY = "Comradery";
    public static final int STRENGTH_ID = 1;
    public static final int AGILITY_ID = 2;
    public static final int COMRADERY_ID = 3;
    public static final int STARTING_HP = 10;
    // END character insertion constants

    // BEGIN db constants
    public static final String tbl_choice = "table_choice";
    public static final String tbl_choice_id = "choice_id";
    public static final String tbl_choice_node_id = "node_id";
    public static final String tbl_choice_text = "choice_text";
    public static final String tbl_choice_connected_success_node = "connected_success_node";
    public static final String tbl_choice_connected_fail_node = "connected_fail_node";
    public static final String tbl_choice_item_type_required = "item_type_required";
    public static final String tbl_choice_item_type_improves = "item_type_improves";
    public static final String tbl_choice_test_type_id = "test_type_id";
    public static final String tbl_choice_test_difficulty = "test_difficulty";

    public static final String tbl_inventory = "table_inventory";
    public static final String tbl_inventory_id = "inventory_id";
    public static final String tbl_inventory_name = "inventory_name";
    public static final String tbl_inventory_power = "inventory_power";
    public static final String tbl_inventory_type_id = "inventory_type_id";
    public static final String tbl_inventory_character_id = "inventory_character_id";

    public static final String tbl_statistics = "table_statistic";
    public static final String tbl_statistics_type_id = "stat_type_id";
    public static final String tbl_statistics_stat_name = "stat_name";
    public static final String tbl_statistics_stat_value = "stat_value";
    public static final String tbl_statistics_character_id = "character_id";

    public static final String tbl_nodes = "table_node";
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
    public static final String tbl_character_hp = "character_hp";

    public static final String[] all_tables = {tbl_choice, tbl_inventory, tbl_statistics,
            tbl_nodes, tbl_character, tbl_item_type};

    public static final String[] create_tables = new String[] {

            "CREATE TABLE " + tbl_choice + " (" +
                    tbl_choice_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_choice_node_id + " integer, " +
                    tbl_choice_text + " text, " +
                    tbl_choice_connected_success_node + " integer, " +
                    tbl_choice_connected_fail_node + " integer, " +
                    tbl_choice_item_type_required + " integer, " +
                    tbl_choice_item_type_improves + " integer, " +
                    tbl_choice_test_type_id + " integer, " +
                    tbl_choice_test_difficulty + " integer " +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_inventory +" (" +
                    tbl_inventory_id + " integer PRIMARY KEY AUTOINCREMENT," +
                    tbl_inventory_name + " text," +
                    tbl_inventory_power + " text," +
                    tbl_inventory_type_id + " integer" +
                    tbl_inventory_character_id + " integer" +
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
                    tbl_nodes_id + " integer PRIMARY KEY," +
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
                    tbl_character_is_backup_for + " integer," +
                    tbl_character_hp + " integer" +
                    ");"};
    // END db constants

    // BEGIN node details
    // These are ingested into the DB upon DB creation
    public static final String NULL = "NULL";
    
    public static final String [][] nodeDetails = new String[][] {
            new String[] {"1", "You've landed in a burning field.", NULL, NULL},
            new String[] {"2", "You's in a drop pod", NULL, NULL},
            new String[] {"3", "You's out of da drop pod", NULL, NULL},
            new String[] {"4", "You's in a tree patch", NULL, NULL}
    };
    // END node details

    // BEGIN choices details
    // These are ingested into the DB upon DB creation
public static final ChoiceData [] choiceDetails = new ChoiceData[]{
        new ChoiceData("Search for equipment", 1, 2, 3, -1, -1, -1, -1),
        new ChoiceData("Check Hazard's pod", 1, 3, 4, -1, -1, -1, -1),
        new ChoiceData("Continue with the mission", 1, 4, 5, -1, -1, -1, -1),
        new ChoiceData("Hey I'm on TV!", 5, 6, 7, 1, 2, 3, 4),
        new ChoiceData("I'm still here!", 7, 2, 3, 3, 4, 5, 6)
    };
    // END choices details
}
