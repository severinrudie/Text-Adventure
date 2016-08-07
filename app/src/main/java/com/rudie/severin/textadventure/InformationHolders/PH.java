package com.rudie.severin.textadventure.InformationHolders;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

/**
 * Created by erikrudie on 7/23/16.
 */
// Parameter Holder, where the unending sea of constants lives
public class PH {

    // BEGIN non-final variables
    public static String CURRENT_CHARACTER = "CURRENT_CHARACTER";
    // END non-final variables

    // BEGIN constants
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

//    public static final String POPUP_ID = "POPUP_ID";   // used to send popupId to a fragment

    public static final String GAMEPLAY_FRAGMENT = "GAMEPLAY_FRAGMENT";
    public static final String STATISTICS_FRAGMENT = "STATISTICS_FRAGMENT";
    public static final String INVENTORY_FRAGMENT = "INVENTORY_FRAGMENT";

    public static final int DEATH_NODE = 10000;
    public static final int HAT_POPUP = 10002;
    public static final int HAT_FLAG = 2;
    public static final int MONTAGE_STRENGTH_POPUP = 10003;
    public static final int MONTAGE_STRENGTH_FLAG = 3;
    public static final int MONTAGE_AGILITY_POPUP = 10004;
    public static final int MONTAGE_AGILITY_FLAG = 4;
    public static final int MONTAGE_COMRADERY_POPUP = 10005;
    public static final int MONTAGE_COMRADERY_FLAG = 5;

    public static final String COOKIE = "cookie";  // used to interpret node/popup images
    // END constants

    // BEGIN image constants

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
    public static final String tbl_inventory_stat_id = "inventory_stat_id";
    public static final String tbl_inventory_character_id = "inventory_character_id";
    public static final String tbl_inventory_acquisition_text = "inventory_acquisition_text";

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
                    tbl_inventory_stat_id + " integer, " +
                    tbl_inventory_character_id + " integer, " +
                    tbl_inventory_acquisition_text + " text " +
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
            new String[] {(String.valueOf(DEATH_NODE)), "You're dead.  Better luck next time.", "NULL", "NULL"},
//            new String[] {"1", "hello there\nwhy dont we be friends", "NULL", "NULL"},
//            new String[] {"1", "You're in a burning field.  You can run away or punch the flames.", "NULL", "NULL"},
//            new String[] {"2", "You ran like a coward.  You are deeply ashamed.", "cookie", "NULL"},
//            new String[] {"3", "You have punched the flames into submission.  You bask in your own glory.", "NULL", "NULL"}
//            new String[] {"1", "The pod rattles around you as it slices through the atmosphere.  Your cybernetics clacking against the steel drop chair.  Through the unnecessary viewing windows you can see the other pods, containing the rest of your team and all your equipment. \n\nYou are getting too old for this, but Democracy needed you again.  Governor-Professor Notevil was captured when the planet of Trapistan rose up against the Democratically elected government.  Only the raw manliness and untamed skill of [player character] and Macho Squad could see this situation brought to a close.\n\nYou are brought back from your reverie as turbulence begins to rock your vehicle.  Through the pointless window you see bursts of Anti-Aircraft fire streaking past.  The equipment pod is hit first, tearing apart as it screams towards the ground.  There is nothing to do but continue your pull-up routine as the enemy artillery keeps hitting every vehicle not containing a player character.  Just before the landing rockets fire you see Duke Hazard's pod catch fire and veer off course.  He was just two space days from retirement.\n\nThe landing restraints dig against your bulging muscles as the vehicle comes to a stop against the surface of Trapistan.  Pulling the straps free you do one final pull-up, finishing your set.  \"One-hundred-thousand\".  Stepping free from the steel coffin you cast your gaze around.  You've landed in an open field surrounded by wheat.  In the distance you can see smoke rising from where you can assume Hawkins' pod came down.  Around you is the debris from the other pods.  Would you like to:", "NULL", "NULL"},
//            new String[] {"2", "With dawn breaking you set your course away from the fields you'd landed in.  You're here for one reason, to rescue &ProfessorFullTitle&, and you'll be damned if the complete obliteration of your team is going to stop you.  Shouldering the few items you have left you head towards the Capital City of Capitalcityton to the Space East. \n\nThe walk is a long one, made longer still as you roll and dive from cover to cover and spend at least 15 minutes working on your war paint.  As you're finishing the final line of reddened mud under your eyes you hear the distinct whine of a hyper-jet engine.  A Spaceplane zooms by overhead and you hear the thudding of &BadMinionPlural& hitting the ground ahead of you.  Peering through the leaves you see four of them, what do you do?", "NULL", "NULL"}

            new String[] {"1", "The pod rattles around you as it slices through the atmosphere.  Your cybernetics clacking against the steel drop chair.  Through the unnecessary viewing windows you can see the other pods, containing the rest of your team and all your equipment. \n\nYou are getting too old for this, but Democracy needed you again.  Governor-Professor Notevil was captured when the planet of Trapistan rose up against the Democratically elected government.  Only the raw manliness and untamed skill of [player character] and Macho Squad could see this situation brought to a close.\n\nYou are brought back from your reverie as turbulence begins to rock your vehicle.  Through the pointless window you see bursts of Anti-Aircraft fire streaking past.  The equipment pod is hit first, tearing apart as it screams towards the ground.  There is nothing to do but continue your pull-up routine as the enemy artillery keeps hitting every vehicle not containing a player character.  Just before the landing rockets fire you see &BuddyFullName&'s pod catch fire and veer off course.  He was just two space days from retirement.\n\nThe landing restraints dig against your bulging muscles as the vehicle comes to a stop against the surface of Trapistan.  Pulling the straps free you do one final pull-up, finishing your set.  \"One-hundred-thousand\".  Stepping free from the steel coffin you cast your gaze around.  You've landed in an open field surrounded by wheat.  In the distance you can see smoke rising from where you can assume Hawkins' pod came down.  Around you is the debris from the other pods.  Would you like to:", "NULL", "NULL"},
            new String[] {"2", "With dawn breaking you set your course away from the fields you'd landed in.  You're here for one reason, to rescue &ProfessorFullTitle&, and you'll be damned if the complete obliteration of your team is going to stop you.  Shouldering the few items you have left you head towards the Capital City of Capitalcityton to the Space East. \n\nThe walk is a long one, made longer still as you roll and dive from cover to cover and spend at least 15 minutes working on your war paint.  As you're finishing the final line of reddened mud under your eyes you hear the distinct whine of a hyper-jet engine.  A Spaceplane zooms by overhead and you hear the thudding of &BadMinionPlural& hitting the ground ahead of you.  Peering through the leaves you see four of them, what do you do?", "NULL", "NULL"}



    };
    // END node details

    // BEGIN choices details
    // These are ingested into the DB upon DB creation
public static final ChoiceData [] choiceDetails = new ChoiceData[]{
//        new ChoiceData("Shoot", 4, 5, 5, 5, 1, 1, 2, 2),
//        new ChoiceData("Evade", 4, 5, 5, 5, -1, -1, 2, 3),
//        new ChoiceData("Punch", 4, 5, 5, 5, -1, 2, 1, 2),
//        new ChoiceData("Search for equipment", 2, 2, 2, 3, 1, -1, -1, -1),
//        new ChoiceData("Check Hazard's pod", 1, 2, 3, 4, 2, -1, 3, -1),
//        new ChoiceData("Continue with the mission", 1, 2, 3, 4, -1, -1, 1, 2),
//        new ChoiceData("Hey I'm on TV!", 5, 6, 6, 7, 1, 2, 3, 4),
//        new ChoiceData("I'm still here!", 7, 8, 2, 3, 3, 4, 5, 6)
//        new ChoiceData("Run away", 1, 2, 1, 2, -1, -1, 2, 2),
//        new ChoiceData("Punch the flames", 1, 3, 3, 4, -1, -1, 1, 2)
            new ChoiceData("Scavange", 1, 2, 1, 1, -1, -1, -1, -1),
            new ChoiceData("Proceed with mission.", 1, 2, 1, -1, -1, -1, -1, -1),

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
            new String[] {"1", "You ran away like a coward", "NULL", "NULL", "-1", "1"},
//            new String[] {"2", "The flame was faster than your weak, tiny legs", "NULL", "NULL", "-1", "-1"},
//            new String[] {"3", "You punch the flames into submission", "NULL", "NULL", "-1", "1"},
//            new String[] {"4", "The flames outbox you", "NULL", "NULL", "10", "" + "-1"}

    };
    // END popup details

    // BEGIN item details
    public static final ItemData[] itemTemplates = new ItemData[] {
//            new ItemData("Flag Bandana", 1, 2, 1, 0, "After some thought you decide that even with your bulging muscles, emergency patriotism bag, and righteous fury you were still outgunned.  Its obvious then that you must scavenge the nearby fields in the hopes of finding anything that might’ve been thrown clear of the equipment pod.  The wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop. \\n\\nIn one of the hardest moments of your long and storied career, you come across one of &BuddyNickname&’s American Flag Bandanas.  With a moment of flexing silence you wrap it around your own head.  He’s suplexing angels now, and if these robots are half the trouble command expects them to be you’ll need all the Patriotism you can get."),
//            new ItemData("MegaLaser 3000", 2, 2, 2, 2, "After some thought you decide that even with your bulging muscles, emergency patriotism bag, and righteous fury you were still outgunned.  Its obvious then that you must scavenge the nearby fields in the hopes of finding anything that might’ve been thrown clear of the equipment pod.  The wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop. \\n\\nWith some dedication, gumption, and the skills you acquired in the volunteer commando firefighters you manage to find something useful while sifting through the wreckage.  You find a Megalaser 3,000 with a full Cased Laser Injection Pod, or CLIP."),
            new ItemData("Hazard's Hat", 2, 2, 2, 2, "While every pod but yours got hit, you did notice that Hazard’s had gone down not too far from the drop zone.  Yeah, it was on fire, and sure parts of the metal had been vaporized, but damnit.  Hazard is one of the most American men you know, and wouldn’t let something like a 40,000 foot drop end him.  Not while there were Space Nazis to defeat.  The walk gives you time to recite the Pledge of Allegiance a few times, but eventually you reach a small stand of trees with the remains of Hazard’s pod littered all around it.  There's almost nothing left.  Some metal plating sits on the ground, flames licking at the heated metal.  A broken jar of military grade flexing oil sits spilling its precious contents into the ground.  But worst of all, sitting on the ground is Duke Hazard’s Cowboy Hat, given to him by the Space President for breaking the galactic record for most consecutive hours singing the national anthem.  You take it, knowing he’d want you to bring it to his wife."),

    };

    public static void cleanItemsForDb() {
        for (ItemData item : itemTemplates) {
            item.setItemName(DBInterfacer.cleanTextForDb(item.getItemName()));
            item.setAcquireText(DBInterfacer.cleanTextForDb(item.getAcquireText()));
        }
    }
    
    // END item details
}



