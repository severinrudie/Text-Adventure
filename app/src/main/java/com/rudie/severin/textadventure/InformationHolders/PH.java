package com.rudie.severin.textadventure.InformationHolders;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

//            new String[] {"1", "The pod rattles around you as it slices through the atmosphere.  Your cybernetics clacking against the steel drop chair.  Through the unnecessary viewing windows you can see the other pods, containing the rest of your team and all your equipment. \n\nYou are getting too old for this, but Democracy needed you again.  Governor-Professor Notevil was captured when the planet of Trapistan rose up against the Democratically elected government.  Only the raw manliness and untamed skill of [player character] and Macho Squad could see this situation brought to a close.\n\nYou are brought back from your reverie as turbulence begins to rock your vehicle.  Through the pointless window you see bursts of Anti-Aircraft fire streaking past.  The equipment pod is hit first, tearing apart as it screams towards the ground.  There is nothing to do but continue your pull-up routine as the enemy artillery keeps hitting every vehicle not containing a player character.  Just before the landing rockets fire you see &BuddyFullName&'s pod catch fire and veer off course.  He was just two space days from retirement.\n\nThe landing restraints dig against your bulging muscles as the vehicle comes to a stop against the surface of Trapistan.  Pulling the straps free you do one final pull-up, finishing your set.  \"One-hundred-thousand\".  Stepping free from the steel coffin you cast your gaze around.  You've landed in an open field surrounded by wheat.  In the distance you can see smoke rising from where you can assume Hawkins' pod came down.  Around you is the debris from the other pods.  Would you like to:", "NULL", "NULL"},
//            new String[] {"2", "With dawn breaking you set your course away from the fields you'd landed in.  You're here for one reason, to rescue &ProfessorFullTitle&, and you'll be damned if the complete obliteration of your team is going to stop you.  Shouldering the few items you have left you head towards the Capital City of Capitalcityton to the Space East. \n\nThe walk is a long one, made longer still as you roll and dive from cover to cover and spend at least 15 minutes working on your war paint.  As you're finishing the final line of reddened mud under your eyes you hear the distinct whine of a hyper-jet engine.  A Spaceplane zooms by overhead and you hear the thudding of &BadMinionPlural& hitting the ground ahead of you.  Peering through the leaves you see four of them, what do you do?", "NULL", "NULL"}
            new String[] {"1", "The pod rattles around you as it slices through the atmosphere.  Your cybernetics clacking against the steel drop chair.  Through the unnecessary viewing windows you can see the other pods, containing the rest of your team and all your equipment. \n\nYou are getting too old for this, but Democracy needed you again.  Governor-Professor Notevil was captured when the planet of Trapistan rose up against the Democratically elected government.  Only the raw manliness and untamed skill of [player character] and Macho Squad could see this situation brought to a close.\n\nYou are brought back from your reverie as turbulence begins to rock your vehicle.  Through the pointless window you see bursts of Anti-Aircraft fire streaking past.  The equipment pod is hit first, tearing apart as it screams towards the ground.  There is nothing to do but continue your pull-up routine as the enemy artillery keeps hitting every vehicle not containing a player character.  Just before the landing rockets fire you see &BuddyFullName&'s pod catch fire and veer off course.  He was just two space days from retirement.\n\nThe landing restraints dig against your bulging muscles as the vehicle comes to a stop against the surface of Trapistan.  Pulling the straps free you do one final pull-up, finishing your set.  \\\\\"One-hundred-thousand\\\\\".  Stepping free from the steel coffin you cast your gaze around.  You've landed in an open field surrounded by wheat.  In the distance you can see smoke rising from where you can assume Hawkins' pod came down.  Around you is the debris from the other pods.  Would you like to:", "PodsLanding", "NULL"},
            new String[] {"3", "With dawn breaking you set your course away from the fields you'd landed in.  You're here for one reason, to rescue &ProfessorFullTitle&, and you'll be damned if the complete obliteration of your team is going to stop you.  Shouldering the few items you have left you head towards the Capital City of Capitalcityton to the Space East. \n\nThe walk is a long one, made longer still as you roll and dive from cover to cover and spend at least 15 minutes working on your war paint.  As you're finishing the final line of reddened mud under your eyes you hear the distinct whine of a hyper-jet engine.  A Spaceplane zooms by overhead and you hear the thudding of &BadMinionPlural& hitting the ground ahead of you.  Peering through the leaves you see four of them, what do you do?", "NULL", "NULL"},
            new String[] {"5", "With the pods, &BadMinionPlural&, and &BuddyNickname& behind you, Capitalcityton starts to stretch before you.  Despite being the largest city on an entire planet you are able to traverse its radius rather easily, finding yourself in the heart of the city.  Standing within this heart, the Professorial Mansion stands as a testament to both Democracy, and Science.  This is the heart of Freedom on Trapistan, and the dwelling of &ProfessorFullTitle&.  A sprawling complex of unregulated research labs and self guided law-making.  However, all the justice this opulent domicile stands for is marred by the &BadMinionPlural&.  Judging from the &BadMinionSlang& patrolling around the &ProfessorShort& is probably inside.  You have a few options..", "NULL", "NULL"},
            new String[] {"7", "You decide that you have to use techniques you learned in the Hypergreen Berets to remain in a state of constant readiness.  Standing inconspicuously at a cart across the street peddling protein shakes, you begin your squat routine while keeping an eye on the mansion.  When you're only at four-thousand-twenty-seven you spot movement.  A convoy of armored vehicles pull up to the front door of the mansion, and you spot the professor being moved down towards them while surrounded by &BadMinionPlural&.  Time is short, and the sooner this mission is over the sooner you can continue leg day.", "ProfessorNotevil", "NULL"},
            new String[] {"8", "The man inside looks up with a surprised look on his face. \"&ProfessorFullTitle&, my name is &PlayerCharacter& and I'm here to rescue you.  For Democracy.\"  The automated light on your back projects a waving Space American flag on the surface behind you.  \"Oh thank science, you're here!\" Says the &ProfessorTitleShort&.  \"But please, call me Judas.  Professor Notevil is my father.\"  He says with a warm smile.  Completely trusting this obviously friendly and allied person you go to open the door.  As your back is turned the &ProfessorTitleShort& strikes.  Using his powers of Judo, he flips you out of the car, and into the waiting arms of some waiting &BadMinionPlural&.", "NULL", "NULL"},
        new String[] {"9", "You slowly regain consciousness, straining your impressive muscles against the steel restraints.  You quickly take stock of the situation.  You are tied down to a chair in a plain, unadorned room.  Across from you is the only feature, a large television stretching from across the entire wall.  A row of &BadMinionPlural& stand in front of the television, weapons held ready.  The television flickers on, showing the smiling face of &ProfessorFullTitle&.  \"Hello &PlayerCharacter&, I'm so glad you could join us.  I'd love to stay and chat but there are two other heroes I'm currently set to monologue to.\"  The screen flickers off, and the &BadMinionPlural& shoulder their weapons, aim at you, and..\n\nTHE WALL WITH THE SCREEN BLOWS UP\n\n&BadMinionPlural& scatter as the force of the explosion sends some flying.  Through the haze of building debris that miraculously left you with only superficial damage you can make out a group of people fighting their way through the &BadMinionPlural&.  But one of the &BadMinionSlang& is undistracted, and coming your way..", "ProfessorNotevil", "NULL"}


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
//            new ChoiceData("Scavange", 1, 2, 1, 1, -1, -1, -1, -1),
//            new ChoiceData("Proceed with mission.", 1, 2, 1, -1, -1, -1, -1, -1),
            new ChoiceData("Proceed with mission.", 1, 3, 2, -1, -1, -1, -1, -1),
            new ChoiceData("Scavenge the wreckage.", 1, 3, 1, -1, -1, -1, -1, -1),
            new ChoiceData("Shoot the Robosoldiers", 3, 5, 5, 5, 1, -1, -1, -1),
            new ChoiceData("Punch the problem", 3, 5, 5, 5, -1, 2, 1, 2),
            new ChoiceData("Evade the patrol", 3, 5, 5, 5, -1, -1, 2, 2),
            new ChoiceData("Ambush the convoy", 7, 8, 8, 8, -1, 1, 2, 3),
            new ChoiceData("Punch the problem", 7, 8, 8, 8, -1, 2, 1, 3),
            new ChoiceData("Sweet talk the guards", 7, 8, 8, 8, -1, 3, 3, 3)

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
//            new String[] {"1", "You ran away like a coward", "NULL", "NULL", "-1", "1"},
//            new String[] {"2", "The flame was faster than your weak, tiny legs", "NULL", "NULL", "-1", "-1"},
//            new String[] {"3", "You punch the flames into submission", "NULL", "NULL", "-1", "1"},
//            new String[] {"4", "The flames outbox you", "NULL", "NULL", "10", "" + "-1"}

            new String[] {"1", "Scavange Popup Placeholder Text", "PodsScavange", "NULL", "-1", "1"},
            new String[] {"2", "The fires in the field start to fall back behind you as you proceed away from the crash site.  They weren't supposed to know you were coming, but they were ready.  Somebody dropped the ball.  Whether it was intel, mission control, or Colonel Notaspy somebody was leaking information to the enemy.", "NULL", "NULL", "-1", "-1"},
            new String[] {"3", "You steady the Megalaser 3,000 against a nearby branch, letting the capacitors build to full charge as you move the scope reticule towards the enemy.  One of the &BadMinionPlural& starts to look quizzically at the initial target laser upon his chest, right before you let loose a burst.  As the first hostile hit the ground the others whirled about, trying to locate their attacker.  The bright red bolts struck too fast for them to react however, proving once again that nothing can stop a Space American armed with the greatest weapons Space America can buy from Space Belgium.", "RoboBodies", "NULL", "-1", "-1"},
            new String[] {"4", "The &BadMinionPlural& aren't the most observant enemy you've ever faced.  Even the Quasar Qommunists pay more attention as they search an area.  As their patrol approached your location, you let out a battle cry and charged.  \"DEMOCRACY!!!\"  You scream as you grab the first &BadMinion& by the wrists, and swing him like a giant club into the others.  Hydraulics, gears, and blinking lights of questionable usefulness fly in all directions as you beat the patrol, with the patrol until the only thing moving in the area is a registered voter.", "RoboBodies", "NULL", "-1", "-1"},
            new String[] {"5", "You wait while the patrol moves closer and closer to your position, making mental note to make physical note of their lack of caution in the after action report.  As one of the soldiers gets particularly close you grab its arm and try to pull it into the trees with you.  However you get the feeling that the &BadMinion& is far heavier, and stronger than you'd anticipated as you flew through the air, colliding another &BadMinion&.  Thinking quickly you switch to Antimatter Cobra Style, and land a series of blows no generic, nameless foe could withstand.  In short order you find yourself alone in the field, with a reminder that the &BadMinionPlural& are dangerous", "RoboBodies", "NULL", "3", "-1"},
            new String[] {"6", "As much as ridding Trapistan, and the Galaxy of a few &BadMinionPlural& would help Space America, you have a mission.  Saving &ProfessorFullTitle& quickly means that these &BadMinionSlang& get a pardon.  For today.  Using all of the training you got in Space Commando Camp, you throw a rock at a in another direction and watch as they search in its vicinity for the next ten to forty-five minutes.", "NULL", "NULL", "-1", "-1"},
            new String[] {"7", "The &BadMinionPlural& are a blight on the galaxy, but one that will have to wait.  You've got a mission to do.  Summoning all of your strength, skill, and raw aptitude you duck under the tree branches and sprint past the &BadMinionPlural& while their backs are turned.  However you find yourself distracted naming all of the Space States and their Space Capitals, not noticing the dry branch until your foot falls on top of it.  Their knock-off Inferiorlaser 1,500s still smart as they burn through your standard issue Tank Top.  Thinking fast you plant your foot on a nearby log and push off, jumping through the air and over a nearby fence.  With some breathing room you are easily able to outpace the &BadMinionPlural&.", "NULL", "NULL", "3", "-1"},
            new String[] {"8", "You wait and get a feel for the patrols.  While a lesser man may sit and catalog such routes for hours, you have neither the time, nor the cowardice to sit and wait.  When you are certain of an opening in the patrols six minutes later, you get to work.  An intricate plan takes form.  While their backs are turned you sprint up to the door and pull on the handle.  However, the door does not open, despite your herculean strength.  To the side of the door you notice a horizontal bar at waist height.  Diving into your memory you recall three minutes prior when a &BadMinion& had curled it, resulting in the door opening.  Seizing it you curl with all your might, but the bar doesn't budge.  As you strain against the unmoving object the nearby &BadMinion& slowly turns back around.  Seeing you struggling to open the door it raises its Inferiorlaser 1,500 and burns a hole in your skin-tight, unarmored tank top.  Abandoning the bar you seize the &BadMinion&, and throw him across the street, following up with an elbow drop to finish it.  You find yourself right back where you'd started..", "NULL", "NULL", "3", "-1"},
            new String[] {"9", "It's probably going to be a long wait, you'll need to find something to occupy your time.", "NULL", "NULL", "-1", "-1"},
            new String[] {"10", "The &ProfessorTitleShort& is being slowly loaded into the vehicle as you concoct a scheme so diabolical that it borders on Qommunist.  With some cardboard taken from a passing salesman and arts and crafts supplies appropriated from a nearby school bus you have everything you need.  In only a few minutes you have completed a simple sign stating \"&BadMinionPlural& Detour\", complete with macaroni pasted around it for added authenticity.  With the trap set you need only wait in the indicated alleyway.  It doesn't take long for the convoy to pull in.  Using all of your cunning and skill you drop down from the fire escape above the &ProfessorTitleShort&'s vehicle, crashing through the roof and finding yourself alone with him.", "NULL", "NULL", "-1", "-1"},
            new String[] {"11", "Seeing the &ProfessorTitleShort& being loaded into the convoy you spring into action.  Paint from the store behind you, boxes from behind the shipping company across the street, and twine from the purse of that kind old lady..  In no time flat your disguise is complete, even your own mother, may she rest in Space Heaven, would swear that you were a filthy &BadMinionSlang&.  You stride up to the convoy stating \"Beep, Br1an told me to come here and do evil things.\"  The &BadMinionPlural& were buying it, only too late do you remember that &BadMinionPlural& pronounced it EVIL.  The nearest &BadMinion& struck first with its Hydroelectric Stun Pulse, knocking you off of your feet.  You sprang back up, shedding the painted cardboard boxes which had provided your cover.  With a shout of \"LIBERTY!\" you charge forward, delivering a double-clothesline to the waiting &BadMinionPlural&, before ducking into the main vehicle.", "NULL", "NULL", "4", "-1"},
        new String[] {"12", "The &BadMinionPlural& are keeping close watch on the convoy, so this will require a delicate touch.  You wait for them to load the &ProfessorTitleShort& into the middle vehicle, and start heading your way down the street.  As they get closer and closer you flex, preparing for the most devious operation of your storied career.  When the lead car got within 20 yards you sprung into action.  Hefting the protein shake cart above your head, you throw it towards the convoy.  With the lead vehicle and most of the guards incapacitated it is a simple matter of cross-chopping the last &BadMinion&, ripping the door off the middle vehicle, and climbing in to save the &ProfessorTitleShort&.", "NULL", "NULL", "-1", "-1"},
        new String[] {"13", "Plan after plan rockets through your head.  Training is great, but it can never prepare you for being out in the field.  The medicine ball those kids down the street are playing with could be useful, but from the way they're throwing it around it's probably only 80lbs.  In the park nearby are some men playing polo, but damnit this is time for combat, not namby pamby Space European games.  That's when you spot it.  Down the street a few dozen yards, some people playing True Football.  The ball is set up for kickoff, lined up perfectly with the Professorial mansion.  The convoy is pulling away, theres no time to wait.  Why. WHY did it have to be on Leg Day!?!  Gritting your teeth you push forward for the &ProfessorTitleShort&, for Space America.  Pushing the players out of the way you charge in, feeling the muscles in your calves pulling.  You grit your teeth and kick.  The ball flies true, striking the driver of the &ProfessorTitleShort&'s vehicle and sending it careening into a nearby building.  Saying a quick thank you to George Blanda you charge forward, hopping into the car.", "NULL", "NULL", "4", "-1"},
        new String[] {"14", "The &BadMinionPlural& loaded the &ProfessorTitleShort& into the back of the middle vehicle, and started filing into their own.  Seeing that time was short, you stride up to the &BadMinion& standing outside the &ProfessorTitleShort&'s vehicle.  Looking over the Robotic minion's chassis you note an important symbol.  \"Hey buddy, I need to talk to the man\" you say, motioning towards the vehicle.  The guard is understandably wary, until you reveal the hastily scrawled drawing you put on your arm.  \"Bro!  I'm from Robot Epsilon Tau Binary!\"  The string of freshly drawn Binary glistens in the morning sun, and you can see the machine eyes of the &BadMinion& soften as he reveals his own.  There is a short exchange of fairly awkward small-talk before he ushers you into the vehicle.", "NULL", "NULL", "-1", "-1"},
        new String[] {"15", "You saunter up to the &BadMinion& who is ushering the &ProfessorTitleShort& into the car.  \"Hey, fellow Robotic soldier, the Command Unit has sent me to interrogate the prisoner.\"  You can see the literal gears turning within its head as it considers what you've said, and your distinct lack of robotic exoskeleton.  After a few short moments, the &BadMinion& raises its Inferiorlaser 1,500 and fires a shot into your side.  Abandoning the facade you grab the &BadMinion& by the wrists and swing it wide around, dashing it against the vehicle.  Wasting no time you jump into the door, moving in to rescue your target.", "NULL", "NULL", "4", "-1"},
        new String[] {"16", "You pass in and out of consciousness as you are repeatedly beaten.  Several Robosoldiers fall to your iron biceps, but there are always two more to replace them.", "NULL", "NULL", "-1", "-1"},

        };
    // END popup details

    // BEGIN item details
    public static final ItemData[] itemTemplates = new ItemData[] {
//            new ItemData("Flag Bandana", 1, 2, STRENGTH_ID, 0, "After some thought you decide that even with your bulging muscles, emergency patriotism bag, and righteous fury you were still outgunned.  Its obvious then that you must scavenge the nearby fields in the hopes of finding anything that might’ve been thrown clear of the equipment pod.  The wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop. \\n\\nIn one of the hardest moments of your long and storied career, you come across one of &BuddyNickname&’s American Flag Bandanas.  With a moment of flexing silence you wrap it around your own head.  He’s suplexing angels now, and if these robots are half the trouble command expects them to be you’ll need all the Patriotism you can get."),
//            new ItemData("MegaLaser 3000", 2, 2, AGILITY_ID, 0, "After some thought you decide that even with your bulging muscles, emergency patriotism bag, and righteous fury you were still outgunned.  Its obvious then that you must scavenge the nearby fields in the hopes of finding anything that might’ve been thrown clear of the equipment pod.  The wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop. \\n\\nWith some dedication, gumption, and the skills you acquired in the volunteer commando firefighters you manage to find something useful while sifting through the wreckage.  You find a Megalaser 3,000 with a full Cased Laser Injection Pod, or CLIP."),
            new ItemData("Hazard's Hat", 2, 2, COMRADERY_ID, 0, "While every pod but yours got hit, you did notice that Hazard’s had gone down not too far from the drop zone.  Yeah, it was on fire, and sure parts of the metal had been vaporized, but damnit.  Hazard is one of the most American men you know, and wouldn’t let something like a 40,000 foot drop end him.  Not while there were Space Nazis to defeat.  The walk gives you time to recite the Pledge of Allegiance a few times, but eventually you reach a small stand of trees with the remains of Hazard’s pod littered all around it.  There's almost nothing left.  Some metal plating sits on the ground, flames licking at the heated metal.  A broken jar of military grade flexing oil sits spilling its precious contents into the ground.  But worst of all, sitting on the ground is Duke Hazard’s Cowboy Hat, given to him by the Space President for breaking the galactic record for most consecutive hours singing the national anthem.  You take it, knowing he’d want you to bring it to his wife."),

    };

    // items are cleaned once before the game runs, then never again
    public static void cleanItemsForDb() {
        for (ItemData item : itemTemplates) {
            item.setItemName(DBInterfacer.cleanTextForDb(item.getItemName()));
            item.setAcquireText(DBInterfacer.cleanTextForDb(item.getAcquireText()));
        }
    }
    public static void replaceNames() {
        Map<String, String> nameReplacements = populateNameReplacements();
        Set<String> keys = nameReplacements.keySet();

        for (String[] nodeArray : nodeDetails) {
            for (String key : keys) {
                nodeArray[1] = nodeArray[1].replace(key, nameReplacements.get(key));
            }
        }
        for (ChoiceData choiceData : choiceDetails) {
            for (String key: keys) {
                choiceData.setText(choiceData.getText().replace(key, nameReplacements.get(key)));
            }
        }
        for (String[] popupArray : popupDetails) {
            for (String key: keys) {
                popupArray[1] = popupArray[1].replace(key, nameReplacements.get(key));
            }
        }
        for (ItemData item : itemTemplates) {
            for (String key: keys) {
                item.setItemName(item.getItemName().replace(key, nameReplacements.get(key)));
                item.setAcquireText(item.getAcquireText().replace(key, nameReplacements.get(key)));
            }
        }
    }

    private static Map<String, String> populateNameReplacements() {
        Map<String, String> nameReplacements = new HashMap<>();

        nameReplacements.put("&ProfessorFullTitle&", "Governor-Professor Notevil");
        nameReplacements.put("&ProfessorTitleShort&", "Professor");
        nameReplacements.put("&BadMinion&", "RoboTron");
        nameReplacements.put("&BadMinionPlural&", "RoboTronitons");
        nameReplacements.put("&BadMinionSlang&", "Bots");
        nameReplacements.put("&BuddyFullName&", "Duke Ramierez");
        nameReplacements.put("&BuddyNickname&", "Duke");

        return nameReplacements;
    }

    // END item details
}



