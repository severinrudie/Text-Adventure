package com.rudie.severin.textadventure.InformationHolders;

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
    public static final String NULL = "NULL";
    public static final String FIRSTNAME = "FIRSTNAME";  // \
    public static final String NICKNAME = "NICKNAME";    //  - used to replace names
    public static final String LASTNAME = "LASTNAME";    // /
    public static final String POPUP_ID = "POPUP_ID";   // used to send popupId to a fragment
    public static final String GAMEPLAY_FRAGMENT = "GAMEPLAY_FRAGMENT";
    public static final String STATISTICS_FRAGMENT = "STATISTICS_FRAGMENT";
    public static final String INVENTORY_FRAGMENT = "INVENTORY_FRAGMENT";
    // END character insertion constants

    // BEGIN image constants
    public static final String COOKIE = "cookie";
    // END image constants

    // BEGIN db constants
    public static final String tbl_choice = "table_choice";
    public static final String tbl_choice_id = "choice_id";
    public static final String tbl_choice_node_id = "node_id";
    public static final String tbl_choice_to_node_id = "to_node_id";
    public static final String tbl_choice_text = "choice_text";
    public static final String tbl_choice_connected_success_popup = "connected_success_popup";
    public static final String tbl_choice_connected_fail_popup = "connected_fail_popup";
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

    public static final String tbl_popup = "table_popup";
    public static final String tbl_popup_id = "popup_id";
    public static final String tbl_popup_text = "popup_text";
    public static final String tbl_popup_image = "popup_image";
    public static final String tbl_popup_animation = "popup_animation";
    public static final String tbl_popup_damage = "popup_damage";
    public static final String tbl_popup_item = "popup_item";

    public static final String[] all_tables = {tbl_choice, tbl_inventory, tbl_statistics,
            tbl_nodes, tbl_character, tbl_item_type, tbl_popup};

    public static final String[] create_tables = new String[] {

            "CREATE TABLE " + tbl_choice + " (" +
                    tbl_choice_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_choice_node_id + " integer, " +
                    tbl_choice_to_node_id + " integer, " +
                    tbl_choice_text + " text, " +
                    tbl_choice_connected_success_popup + " integer, " +
                    tbl_choice_connected_fail_popup + " integer, " +
                    tbl_choice_item_type_required + " integer, " +
                    tbl_choice_item_type_improves + " integer, " +
                    tbl_choice_test_type_id + " integer, " +
                    tbl_choice_test_difficulty + " integer " +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_inventory +" (" +
                    tbl_inventory_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_inventory_name + " text, " +
                    tbl_inventory_power + " integer, " +
                    tbl_inventory_type_id + " integer, " +
                    tbl_inventory_character_id + " integer " +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_statistics + " (" +
                    tbl_statistics_type_id + " integer, " +
                    tbl_statistics_stat_name + " text, " +
                    tbl_statistics_stat_value + " integer, " +
                    tbl_statistics_character_id + " integer" +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_nodes + " (" +
                    tbl_nodes_id + " integer PRIMARY KEY, " +
                    tbl_nodes_text + " text, " +
                    tbl_nodes_image + " text, " +
                    tbl_nodes_animation + " text"+
                    ");" +
                    " ",
            "CREATE TABLE " + tbl_item_type + " (" +
                    tbl_item_type_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_item_type_name + " text" +
                    ");" +
                    "",
            "CREATE TABLE " + tbl_character + " (" +
                    tbl_character_id + " integer PRIMARY KEY AUTOINCREMENT, " +
                    tbl_character_firstname + " text, " +
                    tbl_character_lastname + " text, " +
                    tbl_character_nickname + " text, " +
                    tbl_character_at_node + " integer, " +
                    tbl_character_is_backup_for + " integer, " +
                    tbl_character_hp + " integer" +
                    ");",
            "CREATE TABLE " + tbl_popup + " (" +
                    tbl_popup_id + " integer PRIMARY KEY, " +
                    tbl_popup_text + " text, " +
                    tbl_popup_image + " text, " +
                    tbl_popup_animation + " text, "+
                    tbl_popup_damage + " integer, " +
                    tbl_popup_item + " integer"+
                    ");" +
                    " "};
    // END db constants

    // BEGIN node details
    // These are ingested into the DB upon DB creation

    
    public static final String [][] nodeDetails = new String[][] {
//            new String[] {"5", "Locate Prof", "3", "NULL"},
//            new String[] {"4", "Incoming Patrol", "NULL", "NULL"},
//            new String[] {"1", "You've landed in a burning field.", NULL, NULL},
//            new String[] {"1", "\"NICKNAME, get in here! Dammit Sergeant LASTNAME, you're a loose cannon! You're the best damn marine I've got, and God knows we need you.  But dammit FIRSTNAME, you have to do things by the book!\"", NULL, NULL},
//            new String[] {"2", "You's searching for equipment", NULL, NULL},
//            new String[] {"3", "You's out of da drop pod. Win", NULL, NULL},
//            new String[] {"4", "You's in a tree patch. Fail", NULL, NULL}
            new String[] {"1", "You're in a burning field.  You can run away or punch the flames.", "NULL", "NULL"},
            new String[] {"2", "You ran like a coward.  You are deeply ashamed.", "cookie", "NULL"},
            new String[] {"3", "You have punched the flames into submission.  You bask in your own glory.", "NULL", "NULL"}
    };
    // END node details

    // BEGIN choices details
    // These are ingested into the DB upon DB creation
public static final ChoiceData [] choiceDetails = new ChoiceData[]{
//        new ChoiceData("Shoot", 4, 5, 5, 5, 1, 1, 2, 2),
//        new ChoiceData("Evade", 4, 5, 5, 5, -1, -1, 2, 3),
//        new ChoiceData("Punch", 4, 5, 5, 5, -1, 2, 1, 2),
        new ChoiceData("Search for equipment", 2, 2, 2, 3, 1, -1, -1, -1),
//        new ChoiceData("Check Hazard's pod", 1, 2, 3, 4, 2, -1, 3, -1),
//        new ChoiceData("Continue with the mission", 1, 2, 3, 4, -1, -1, 1, 2),
//        new ChoiceData("Hey I'm on TV!", 5, 6, 6, 7, 1, 2, 3, 4),
//        new ChoiceData("I'm still here!", 7, 8, 2, 3, 3, 4, 5, 6)
            new ChoiceData("Run away", 1, 2, 1, 2, -1, -1, 2, 2),
            new ChoiceData("Punch the flames", 1, 3, 3, 4, -1, -1, 1, 2)
    };
    // END choices details

    // BEGIN popup details
    public static final String [][] popupDetails = new String[][] {
//            new String[] {"5", "Locate Prof", "3", "NULL"},
//            new String[] {"4", "Incoming Patrol", "NULL", "NULL"},
//            new String[] {"1", "You succeed!", NULL, NULL},
//            new String[] {"2", "You fail!", NULL, NULL},
//            new String[] {"3", "You live!", NULL, NULL},
//            new String[] {"4", "You die!", NULL, NULL},
//            new String[] {"5", "this is the fifth!", "NULL", "NULL"}
            new String[] {"1", "You ran away like a coward", "NULL", "NULL", "-1", "-1"},
            new String[] {"2", "The flame was faster than your weak, tiny legs", "NULL", "NULL", "-1", "-1"},
            new String[] {"3", "You punch the flames into submission", "NULL", "NULL", "-1", "-1"},
            new String[] {"4", "The flames outbox you", "NULL", "NULL", "3", "-1"}
    };
    // END popup details
}
