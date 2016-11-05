package com.rudie.severin.machosquad.InformationHolders;

import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public static final String GUN = "Gun";
    public static final String WEAPON = "Weapon";
    public static final String PANACHE = "Panache";
    public static final String FLAG = "Flag";
    public static final String[] ITEM_NAMES = new String[] {GUN, WEAPON, PANACHE, FLAG};

    public static final int ITEM_TYPE_GUN = 1;
    public static final int ITEM_TYPE_WEAPON = 2;
    public static final int ITEM_TYPE_PANACHE = 3;
    public static final int ITEM_TYPE_FLAG = 4;
    public static final int[] ITEM_TYPE_IDS = new int[] {ITEM_TYPE_GUN, ITEM_TYPE_WEAPON, ITEM_TYPE_PANACHE, ITEM_TYPE_FLAG};

//    1 = Gun
//    2 = Strength
//    3 = Comraderie
//    4 = Flag

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
    public static final int VICTORY_NODE = 10006;
    public static final int PARTIAL_VICTORY_NODE = 10007;
    public static final int HAT_POPUP = 10002;
    public static final int HAT_FLAG = 2;
    public static final int MONTAGE_STRENGTH_POPUP = 10003;
    public static final int MONTAGE_STRENGTH_FLAG = 3;
    public static final int MONTAGE_AGILITY_POPUP = 10004;
    public static final int MONTAGE_AGILITY_FLAG = 4;
    public static final int MONTAGE_COMRADERY_POPUP = 10005;
    public static final int MONTAGE_COMRADERY_FLAG = 5;
    public static final int MECHASPIDER_DEAD = 6;
    public static final int MECHASPIDER_POPUP = 25;
    public static final int GAME_OVER_FLAG = 7;

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

    public static final String[] content_tables_only = {tbl_choice,
      tbl_nodes, tbl_item_type, tbl_popup};

    public static final String[] create_tables = new String[] {

      "CREATE TABLE IF NOT EXISTS " + tbl_choice + " (" +
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
      "CREATE TABLE IF NOT EXISTS " + tbl_inventory +" (" +
        tbl_inventory_id + " integer PRIMARY KEY AUTOINCREMENT, " +
        tbl_inventory_name + " text, " +
        tbl_inventory_power + " integer, " +
        tbl_inventory_type_id + " integer, " +
        tbl_inventory_stat_id + " integer, " +
        tbl_inventory_character_id + " integer, " +
        tbl_inventory_acquisition_text + " text " +
        ");" +
        "",
      "CREATE TABLE IF NOT EXISTS " + tbl_statistics + " (" +
        tbl_statistics_type_id + " integer, " +
        tbl_statistics_stat_name + " text, " +
        tbl_statistics_stat_value + " integer, " +
        tbl_statistics_character_id + " integer" +
        ");" +
        "",
      "CREATE TABLE IF NOT EXISTS " + tbl_nodes + " (" +
        tbl_nodes_id + " integer PRIMARY KEY, " +
        tbl_nodes_text + " text, " +
        tbl_nodes_image + " text, " +
        tbl_nodes_animation + " text"+
        ");" +
        " ",
      "CREATE TABLE IF NOT EXISTS " + tbl_item_type + " (" +
        tbl_item_type_id + " integer PRIMARY KEY AUTOINCREMENT, " +
        tbl_item_type_name + " text" +
        ");" +
        "",
      "CREATE TABLE IF NOT EXISTS " + tbl_character + " (" +
        tbl_character_id + " integer PRIMARY KEY AUTOINCREMENT, " +
        tbl_character_firstname + " text, " +
        tbl_character_lastname + " text, " +
        tbl_character_nickname + " text, " +
        tbl_character_at_node + " integer, " +
        tbl_character_is_backup_for + " integer, " +
        tbl_character_hp + " integer" +
        ");",
      "CREATE TABLE IF NOT EXISTS " + tbl_popup + " (" +
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
      new String[] {(String.valueOf(DEATH_NODE)), "You've died!  Who will save Trapistan now!?!", "NULL", "NULL"},
      new String[] {(String.valueOf(VICTORY_NODE)), "Trapistan has been freed from Space Fascist control, and &ProfessorFullTitle& has been brought to Democratic justice.\n\nOnce again, the galaxy is in Macho Squad's debt.", "BuffEagle", "NULL"},
      new String[] {(String.valueOf(PARTIAL_VICTORY_NODE)), "Trapistan is no longer under the control of Space Fascists, and &ProfessorFullTitle& has been vaporized with acceptable losses.\n\nOnce again, the galaxy is in Macho Squad's debt.", "BuffEagle", "NULL"},
      new String[] {"1", "The pod rattles around you as it slices through the atmosphere, your cybernetics clacking against the steel drop chair.  You're getting too old for this, but Democracy needs you one more time.  Governor-Professor Notevil of the planet Trapistan has been captured, and only the raw machismo and untamed skill of &PlayerCharacter& and Macho Squad can save him.\n\nTurbulence shakes your vehicle, and through the structurally unnecessary window you see bursts of Anti-Aircraft fire streaking past.  The equipment pod is hit first, tearing apart as it screams towards the ground, but there is nothing to do but continue your pull-up routine as fire rakes your squad’s drop pods. You see &BuddyFullName&`s pod catch flame and veer off course just before your landing rockets fire.  He was just two space days from retirement.\n\nThe landing restraints dig against your bulging muscles as you crash to a stop.  Pulling the straps free, you do one final pull-up to finish your set.  ``One-hundred-thousand.``  Stepping free from the steel coffin you cast your gaze around.  You`ve landed in an open field surrounded by wheat.  In the distance you can see smoke rising from where you can assume Hawkins` pod came down.  Around you is the debris from the other pods.  Would you like to:", "PodsLanding", "NULL"},
      new String[] {"3", "With dawn breaking, you set your course away from the fields.  You`re here to rescue &ProfessorFullTitle&, and you`ll be damned if the complete obliteration of your team is going to slow you down.  Shouldering the few items you have left, you head towards the Capital City of Capitalcityton, due Space East of your position. \n\nThe walk is a long one, and made longer still by the nearly thirty minutes you spend applying war paint.  As you`re finishing the final line of reddened mud under your eyes and considering whether or not to shout a bestial roar, you hear the distinct whine of a hyper-jet engine.  A Spaceplane zooms by overhead and you hear the thudding of &BadMinionPlural& hitting the ground ahead of you.  Peering through the leaves you see four of them, what do you do?", "NULL", "NULL"},
      new String[] {"5", "With the pods, &BadMinionPlural&, and &BuddyNickname& behind you, Capitalcityton begins to stretch out to your front.  Despite being the largest city on the entire planet, you are able to enter it rather easily, soon finding yourself in the heart of the city.  The Professorial Mansion stands near the center as a testament to both Democracy and Science.  This is both the home of Freedom on Trapistan and the dwelling of &ProfessorFullTitle&, a sprawling complex of unregulated research labs and self guided law-making.  However, the justice that this esteemed domicile stands for is marred by the &BadMinionPlural&.  Judging from the &BadMinionSlang& patrolling around, the &ProfessorTitleShort& is probably inside.  You have a few options..", "NULL", "NULL"},
      new String[] {"7", "You decide that you have to use techniques you learned in the Hypergreen Berets to remain in a state of constant readiness.  Standing inconspicuously at a street vendor peddling protein shakes, you begin your squat routine while keeping an eye on the mansion.  You only make it to four-thousand-twenty-seven before you spot movement.  A convoy of armored vehicles pull up to the front door of the mansion, and you spot the professor being moved down towards them. He is surrounded by &BadMinionPlural&.  Time is short, and the sooner this mission is over the sooner you can continue leg day.", "NULL", "NULL"},
      new String[] {"8", "The man inside looks up at you with a surprised expression. ``&ProfessorFullTitle&, my name is &PlayerCharacter&, and I`m here to rescue you.  For Democracy.``  The automated light on your back projects a waving Space American flag on the surface behind you.  ``Oh thank science, you`re here! But please, call me Judas,`` He says with a warm smile and a wink, ``Professor Notevil is my father.`` Completely trusting this obviously friendly and allied person you go to open the door.  As your back is turned the &ProfessorTitleShort& strikes.  Using his powers of Judo, he flips you out of the car, and into the waiting arms of the arrayed &BadMinionPlural&.", "NULL", "NULL"},
      new String[] {"9", "You slowly regain consciousness. Straining your impressive muscles against the steel restraints, you quickly take stock of the situation.  You are tied down to a chair in a plain, unadorned room.  Across from you is the only feature, a large television stretching across the entire wall.  A row of &BadMinionPlural& stands in front of the television, weapons at the ready.  The television flickers on, showing the smiling face of &ProfessorFullTitle&.  ``Hello FIRSTNAME, I`m so glad you could join us.  I`d love to stay and chat but there are two other heroes I`m set to monologue to shortly.  So, tata!.``  He laughs effeminately as the screen flickers off, and the &BadMinionPlural& aim their weapons at you, and...\n\nTHE WALL EXPLODES\n\n&BadMinionPlural& scatter as the force of the explosion sends some flying.  Through the haze of debris that miraculously left you with only superficial, and attractive, damage, you can make out a group of people fighting their way through the &BadMinionPlural&.  But one of the &BadMinionSlang& sets his sights on you, and is coming your way..", "ProfessorNotevil", "NULL"},
      new String[] {"10", "With the threat neutralized you take a look around.  The remaining &BadMinionPlural& have been eliminated by your rescuers.  A closer inspection reveals a ragtag group of misfits, fighting for liberty.  You are able to tell this by their motley assortment of gear, and general punk type attire. ``&PlayerCharacter&, we`re the Resistance.  We`re here to save you, and bring democratic voting back to Trapistan!``  Their energetic leader proclaims.  With a curt nod and some stoic pride in Space America, the lot of you proceed out through the newly exploded exit.\n\nThe rest of the escape follows much the same plan as the beginning.  Several offending walls are demolished, and there are plenty of firefights with the &BadMinionSlang&.  You are fairly certain that there were some tearful goodbyes and sacrifices, but since they weren`t player characters or named NPCs you glossed over them.  Finally after a climactic battle in which you had to smash a &BadMinion& with a Diving Double Axe Handle, you come across a room marked `Hangar`.  You see a variety of vehicles: a few fliers, an armored personnel carrier, and a set of hover-cycles.  Striding past the practical but unsexy armored vehicles and the unfairly effective air skimmers, you jump into the thematically much cooler hover-cycles and take to the sky-streets.", "NULL", "NULL"},
      new String[] {"11", "You and the Resistance fighters scramble into three hover-cycles and punch it out of the hanger.  The vehicles clear the bay and scream out into Capitalcityton proper.  Lines of &BadMinionPlural& fire ineffectually at your group as you zip off towards freedom... and revenge.  The wind whips through your tousled hair as you turn in your seat and spot the next threat to freedom.  Barrelling down the avenue towards you is one of Trapistan`s greatest weapons: a Mechaspider. The fearsome vehicle crashes along main street, its legs crushing cars, smashing through buildings, and generally causing extreme and mostly unnecessary property damage. \n\nHow do you respond?", "Mechaspider", "NULL"},
      new String[] {"12", "With a final creaking, mechanical sigh the Mechaspider collapses to the ground, andn the distance you can hear the sounds of &BadMinionPlural& making their way towards the scene of battle.  The Resistance fighters are fist pumping, high fiving, and otherwise generally celebrating this victory over tyranny, evil, and Notevil.  Casting your gaze over the scene you realize that you have a choice to make.  You can either stay and salvage whatever useful materials might be left in the Mechaspider, or you can regroup with the Resistance and get to freeing Trapistan from its oppressors.", "NULL", "NULL"},
      new String[] {"13", "The hover-cycles hum as they descend through the Resistance HQ roof hatch.  As you land you are greeted by a haggard looking man in a torn military jacket. ``Star Major, you have no idea how much of a relief it is to see Macho Squad here.`` \n\nYou are ushered through winding hallways.  The building itself is run down, little more than a disused warehouse, but everywhere are stacked munitions, vehicles, and armed registered voters. ``It`s been two weeks since the local newspaper, voice of the people, ran a story about &ProfessorFullTitle&.  It would seem that his Stellar Birth Certificate was forged.  &ProfessorNameShort& wasn`t even born in Space America.  He`s from Space Canada.``  You nod knowingly. Space Canada has long since been a breeding ground for un-American ideas like environmental protection and drum circles.  \n\n``Not long after that came to light he flooded the streets with his &BadMinionPlural&, shutting down anybody who resisted.  We`ve been trying to take him out for days, but he`s locked himself away in that mansion, and the only way to override those locks is by curling 400lbs.``  You look forlornly at the ground before replying.  ``The only man I know who could curl 400lbs is dead.  &BuddyFullName& went down with the rest of my squad.``", "NULL", "NULL"},
      new String[] {"14", "The Resistance leader gives you a final nod.  ``We`re working on a final plan, but we need some time.  Just enough time, in fact, for a Macho Squad training routine.``  He guides you to the HQ building`s gymnasium, and lets you get to work.", "NULL", "NULL"},
      new String[] {"15", "You feel good about your progress. Maybe if you can better yourself you can better Trapistan, and Space America too.  You`re pulled out of thought by the Resistance leader.  ``Star Major, we have a plan.  The &ProfessorTitleShort& is plotting to kill Democracy across the Space Nation.  Now that we can get into his Professorial Mansion we can put a stop to it.  But first we`ll have to draw out his elite Skull Battalion.  We`ve been working on a couple of options...``", "NULL", "NULL"},
      new String[] {"16", "The Skull Battalion has been drawn out.  Your display was enough to draw their full ire, and the time has come to end this.  &ProfessorFullTitle& has been working against Space America, against Democracy, and it's Macho Squad`s job to take him out.  Using back roads and secret tunnels, you position yourself near the Professorial Mansion.  Even with the Skull Battalion gone the place is locked down tight, and you`ll have to decide how you`re going to assault it.", "NULL", "NULL"},
      new String[] {"17", "Readying your hover-cycle you activate your communicatron.  ``Alright everyone, we have one job: stop &ProfessorFullTitle&.  Begin the assault.`` Resistance troops pour out from the surrounding buildings, starting the attack in earnest.", "NULL", "NULL"},
      new String[] {"18", "The front doors of the Professorial Mansion stretch above you as you get into position.  On your word the assault begins, with Resistance forces hitting the building from all sides.  You and &BuddyNickname& lock forearms again in a display of raw manliness.  No more running, no more hiding.  Now it was time to enforce Democracy.", "NULL", "NULL"},
      new String[] {"19", "&BuddyNickname& walks up to the locking mechanism, eyes the curling bar and smirks.  As he holds the door open you enter the building, a small team of Resistance eggheads in tow, &BuddyNickname& bringing up the rear.  Security inside is light, they didn`t expect Macho Squad or the raw curling power of &BuddyFullName&.  You make your way to the command and control room, dispatching the guards with ease.  Your hacker pushes his small rimmed glasses up his face, sits down, cracks his knuckles and gets to work.  \n\n``All I have to do is brute force the back door, reverse the polarity of the code buffer and..  There, complete control.``  The furious typing dies down as he enters one final command.  ``I`ve set the curl weight on the door controls to 1lbs.  &ProfessorFullTitle& is about to have a real bad day.``  He says as he lights up a cigarette, and you see a tide of people surging towards the Mansion on the monitors.", "HackTheButton", "NULL"},
      new String[] {"20", "You see &ProfessorFullTitle& stealthily duck through an open doorway, and you bolt into hot pursuit.  His &BadMinionPlural& guards try to stop you but are thwarted by the arrival of angry citizens, which is both timely and implausible.  Ignoring the melee you charge headfirst after your foe.  Just as you round a corner you catch sight of him heading towards a large set of blast doors labled \"Reactor Control\".", "NULL", "NULL"},
      new String[] {"21", "&ProfessorFullTitle& glances back from the sealed door towards you, his back still turned.  \"I should’ve known you’d find a way to turn the Registered Voters against me….  But no matter.  Soon you won’t be around to stop ANY villainous characters!\"  With a flourish &ProfessorNameShort& whirls around, his Mayoral Cape flying to the ground, and reveals a pair of Quantum Nunchucks gripped in his hands.  &ProfessorFullTitle& stares hatred towards you, a dare to engage him in an impractical but undeniably cool nunchuck fight.  You briefly consider shooting him in the chest, but no, to truly show your superiority you must defeat him in single combat.  For Space America. ", "NULL", "NULL"},
      new String[] {"22", "The fight is done and &ProfessorFullTitle& is subdued, but you get a feeling that this isn’t over.  Maybe it’s the red emergency lighting that has flipped on, or maybe it’s the Professor’s cackling and statement that \"This isn’t over….\".  &BuddyFullName& rushes up behind you, the nerds in tow.  \"&PlayerCharacter&!  The Professor has set the planet’s Solar Power Plant to overload!  We have less than two minutes to get out of here!\"  Looking to your side you see the Escape Craft Hangar, while in front of you sits the now sealed door to the Reactor Control….", "NULL", "NULL"}
    };
    // END node details

    // BEGIN choices details
    // These are ingested into the DB upon DB creation
    public static final ChoiceData [] choiceDetails = new ChoiceData[]{
      new ChoiceData("Return to main menu", DEATH_NODE, -1, 55, -1, -1, -1, -1, GAME_OVER_FLAG),
      new ChoiceData("Return to main menu", VICTORY_NODE, -1, 55, -1, -1, -1, -1, GAME_OVER_FLAG),
      new ChoiceData("Return to main menu", PARTIAL_VICTORY_NODE, -1, 55, -1, -1, -1, -1, GAME_OVER_FLAG),
      new ChoiceData("Proceed with mission.", 1, 3, 2, -1, -1, -1, -1, -1),
      new ChoiceData("Scavenge the wreckage.", 1, 3, 1, -1, -1, -1, -1, -1),
      new ChoiceData("Shoot the Robosoldiers", 3, 5, 3, -1, 1, -1, -1, -1),
      new ChoiceData("Punch the problem", 3, 5, 4, 5, -1, 2, 1, 2),
      new ChoiceData("Evade the patrol", 3, 5, 6, 5, -1, -1, 2, 2),
      new ChoiceData("Ambush the convoy", 7, 8, 10, 11, -1, 1, 2, 3),
      new ChoiceData("Punch the problem", 7, 8, 12, 13, -1, 2, 1, 3),
      new ChoiceData("Sweet talk the guards", 7, 8, 14, 15, -1, 3, 3, 3),
      new ChoiceData("Try to get in", 5, 5, 8, -1, -1, -1, -1, -1),
      new ChoiceData("Wait for an opening", 5, 7, 9, -1, -1, -1, -1, -1),
      new ChoiceData("Continue", 8, 9, 16, -1, -1, -1, -1, -1),
      new ChoiceData("Punch the problem", 9, 10, 17, 18, -1, 2, 1, 3),
      new ChoiceData("Fight together", 9, 10, 19, -1, 3, -1, -1, -1),
      new ChoiceData("Continue", 10, 11, 20, -1, -1, -1, -1, -1),
      new ChoiceData("Shoot the problem", 11, 12, 21, 22, -1, 1, 2, 4),
      new ChoiceData("Command the charge", 11, 12, 23, 24, -1, 3, 3, 3),
      new ChoiceData("Loot the Mechaspider", 12, 13, 25, -1, -1, -1, -1, MECHASPIDER_DEAD),
      new ChoiceData("Regroup and retreat", 12, 13, 26, -1, -1, -1, -1, -1),
      new ChoiceData("Continue", 13, 14, 27, -1, -1, -1, -1, -1),
      new ChoiceData("Train Strength", 14, 15, 28, -1, -1, -1, -1, MONTAGE_STRENGTH_FLAG),
      new ChoiceData("Train Agility", 14, 15, 29, -1, -1, -1, -1, MONTAGE_AGILITY_FLAG),
      new ChoiceData("Train Comradery", 14, 15, 30, -1, -1, -1, -1, MONTAGE_COMRADERY_FLAG),
      new ChoiceData("Assault the Fuel Depot", 15, 16, 31, -1, -1, -1, -1, -1),
      new ChoiceData("Display Patriotism", 15, 16, 32, -1, 4, -1, -1, -1),
      new ChoiceData("Assault the hoverpad", 16, 17, 33, -1, -1, -1, -1, -1),
      new ChoiceData("Assault the front door", 16, 18, 34, -1, -1, -1, -1, -1),
      new ChoiceData("Sneak in to the Mansion", 16, 16, 35, -1, -1, -1, -1, -1),
      new ChoiceData("Shoot the guards", 17, 19, 36, 37, -1, 1, 2, 3),
      new ChoiceData("Punch the problem", 17, 19, 38, 39, -1, 2, 1, 3),
      new ChoiceData("Punch the problem", 18, 19, 40, 41, -1, 2, 1, 4),
      new ChoiceData("Think tactically", 18, 19, 42, 43, -1, 3, 3, 4),
      new ChoiceData("Continue", 19, 20, 44, -1, -1, -1, -1, -1),
      new ChoiceData("Shoot the Lock", 20, 21, 45, 46, -1, 1, 2, 3),
      new ChoiceData("Hit the Lock", 20, 21, 47, 48, -1, -1, 1, 3),
      new ChoiceData("Out Nunchuck Him", 21, 22, 49, 50, -1, 1, 2, 4),
      new ChoiceData("Break His Nunchucks", 21, 22, 51, 52, -1, 2, 1, 4),
      new ChoiceData("Save the planet", 22, VICTORY_NODE, 53, -1, -1, -1, -1, -1),
      new ChoiceData("Escape", 22, PARTIAL_VICTORY_NODE, 54, -1, -1, -1, -1, -1),
      // When adding new choices, make sure they don't overlap with current numbers
    };
    // END choices details

    // BEGIN popup details
    public static final String [][] popupDetails = new String[][] {
      new String[] {"1", "Scavenge Popup Placeholder Text", "PodsScavange", "NULL", "-1", "1"},
      new String[] {"2", "The fires in the field start to fall back behind you as you proceed away from the crash site.  They weren`t supposed to know you were coming, but they were ready.  Somebody dropped the ball.  Whether it was intel, mission control, or Colonel Notaspy, somebody was leaking information to the enemy.", "NULL", "NULL", "-1", "-1"},
      new String[] {"3", "You steady the Megalaser 3,000 against a nearby branch, letting the capacitors build to full charge as you move the scope reticule towards the enemy.  One of the &BadMinionPlural& starts to look quizzically at the initial target laser upon his chest, right before you let loose a burst.  As the first hostile hit the ground the others whirled about, trying to locate their attacker.  The bright red bolts struck too fast for them to react however, proving once again that nothing can stop a Space American armed with the greatest weapons Space America can buy from Space Belgium.", "RoboBodies", "NULL", "-1", "-1"},
      new String[] {"4", "The &BadMinionPlural& aren`t the most observant enemy you`ve ever faced.  Even the Quasar Qommunists pay more attention as they search an area.  As their patrol approached your location, you let out a battle cry and charged.  `DEMOCRACY!!!`  You scream as you grab the first &BadMinion& by the wrists, and swing him like a giant club into the others.  Hydraulics, gears, and blinking lights of questionable usefulness fly in all directions as you beat the patrol, with the patrol until the only thing moving in the area is a registered voter.", "RoboBodies", "NULL", "-1", "-1"},
      new String[] {"5", "You wait while the patrol moves closer and closer to your position.  As one of the soldiers gets particularly close you grab its arm and try to pull it into the trees with you.  However you get the feeling that the &BadMinion& is far heavier and stronger than you`d anticipated as you flew through the air, colliding another &BadMinion&.  \n\nThinking quickly you switch to Antimatter Cobra Style, and land a series of blows no generic, nameless foe could withstand.  In short order you find yourself alone in the field, with a reminder that the &BadMinionPlural& are dangerous", "RoboBodies", "NULL", "3", "-1"},
      new String[] {"6", "As much as ridding Trapistan, and the Galaxy of a few &BadMinionPlural& would help Space America, you have a mission.  Saving &ProfessorFullTitle& quickly means that these &BadMinionSlang& get a pardon.  For today.  Using all of the training you got in Space Commando Camp, you throw a rock at a in another direction and watch as they search in its vicinity for the next ten to forty-five minutes.", "NULL", "NULL", "-1", "-1"},
      new String[] {"7", "The &BadMinionPlural& are a blight on the galaxy, but one that will have to wait.  You`ve got a mission to do.  Summoning all of your strength, skill, and raw aptitude you duck under the tree branches and sprint past the &BadMinionPlural& while their backs are turned.  However you find yourself distracted naming all of the Space States and their Space Capitals, not noticing the dry branch until your foot falls on top of it.  Their knock-off Inferiorlaser 1,500s still smart as they burn through your standard issue Tank Top.  Thinking fast you plant your foot on a nearby log and push off, jumping through the air and over a nearby fence.  With some breathing room you are easily able to outpace the &BadMinionPlural&.", "NULL", "NULL", "3", "-1"},
      new String[] {"8", "While a lesser man may sit and catalog such routes for hours, you have neither the time nor the cowardice to sit and wait.  When you are certain of an opening in the patrols after six minutes of observation, you get to work, an intricate plan taking form.  \n\nWhile their backs are turned you sprint up to the door and pull on the handle.  However, the door does not open despite your herculean strength.  To the side of the door you notice a horizontal bar at waist height.  Diving into your memory you recall three minutes prior when a &BadMinion& had curled it, resulting in the door opening.  As you strain against the unmoving object a nearby &BadMinion& slowly turns back around.  Seeing you struggling to open the door it raises its Inferiorlaser 1,500 and burns a hole in your skin-tight, unarmored tank top.  Abandoning the bar you seize the &BadMinion&, and throw him across the street, following up with an elbow drop to finish it.  You find yourself right back where you`d started..", "NULL", "NULL", "3", "-1"},
      new String[] {"9", "It`s probably going to be a long wait, you`ll need to find something to occupy your time.", "NULL", "NULL", "-1", "-1"},
      new String[] {"10", "The &ProfessorTitleShort& is being slowly loaded into the vehicle as you concoct a scheme so diabolical that it borders on Qommunist.  With some cardboard taken from a passing salesman and arts and crafts supplies appropriated from a nearby school bus you have everything you need.  In only a few minutes you have completed a simple sign stating `&BadMinionPlural& Detour`, complete with macaroni pasted around it for added authenticity.  With the trap set you need only wait in the indicated alleyway.  It doesn`t take long for the convoy to pull in.  Using all of your cunning and skill you drop down from the fire escape above the &ProfessorTitleShort&`s vehicle, crashing through the roof and finding yourself alone with him.", "NULL", "NULL", "-1", "-1"},
      new String[] {"11", "Seeing the &ProfessorTitleShort& being loaded into the convoy you spring into action.  Paint from the store behind you, boxes from behind the shipping company across the street, and twine from the purse of that kind old lady..  In no time flat your disguise is complete, even your own mother, may she rest in Space Heaven, would swear that you were a filthy &BadMinionSlang&.  You stride up to the convoy stating ``Beep, Br1an told me to come here and do evil things.``  The &BadMinionPlural& were buying it, but only too late do you remember that &BadMinionPlural& pronounced it EVIL.  \n\nThe nearest &BadMinion& struck first with its Hydroelectric Stun Pulse, knocking you off of your feet.  You sprang back up, shedding the painted cardboard boxes which had provided your cover.  With a shout of ``LIBERTY!`` you charge forward, delivering a double-clothesline to the waiting &BadMinionPlural&, then ducking into the main vehicle.", "NULL", "NULL", "4", "-1"},
      new String[] {"12", "The &BadMinionPlural& are keeping close watch on the convoy, so this will require a delicate touch.  You wait for them to load the &ProfessorTitleShort& into the middle vehicle, and start heading your way down the street.  As they get closer and closer you flex, preparing for the most devious operation of your storied career.  When the lead car got within 20 yards you sprung into action.  Hefting the protein shake cart above your head, you throw it towards the convoy.  With the lead vehicle and most of the guards incapacitated it is a simple matter of cross-chopping the last &BadMinion&, ripping the door off the middle vehicle, and climbing in to save the &ProfessorTitleShort&.", "NULL", "NULL", "-1", "-1"},
      new String[] {"13", "Plan after plan rockets through your head.  Training is great, but it can never prepare you for being out in the field.  The medicine ball those kids down the street are playing with could be useful, but from the way they`re throwing it around it`s probably only 80lbs.  In the park nearby are some men playing polo, but damnit this is time for combat, not namby pamby Space European games.  That`s when you spot it.  Down the street a few dozen yards, some people playing True Football.  The ball is set up for kickoff, lined up perfectly with the Professorial mansion.  The convoy is pulling away, theres no time to wait.  Why. WHY did it have to be on Leg Day!?!  Gritting your teeth you push forward for the &ProfessorTitleShort&, for Space America.  \n\nPushing the players out of the way you charge in, feeling the muscles in your calves pulling.  You grit your teeth and kick.  The ball flies true, striking the driver of the &ProfessorTitleShort&`s vehicle and sending it careening into a nearby building.  Saying a quick thank you to George Blanda you charge forward, hopping into the car.", "NULL", "NULL", "4", "-1"},
      new String[] {"14", "The &BadMinionPlural& loaded the &ProfessorTitleShort& into the back of the middle vehicle, and started filing into their own.  Seeing that time was short, you stride up to the &BadMinion& standing outside the &ProfessorTitleShort&`s vehicle.  Looking over the Robotic minion`s chassis you note an important symbol.  `Hey buddy, I need to talk to the man` you say, motioning towards the vehicle.  The guard is understandably wary, until you reveal the hastily scrawled drawing you put on your arm.  `Bro!  I`m from Robot Epsilon Tau Binary!`  The string of freshly drawn Binary glistens in the morning sun, and you can see the machine eyes of the &BadMinion& soften as he reveals his own.  There is a short exchange of fairly awkward small-talk before he ushers you into the vehicle.", "NULL", "NULL", "-1", "-1"},
      new String[] {"15", "You saunter up to the &BadMinion& who is ushering the &ProfessorTitleShort& into the car.  `Hey, fellow Robotic soldier, the Command Unit has sent me to interrogate the prisoner.`  You can see the literal gears turning within its head as it considers what you`ve said, and your distinct lack of robotic exoskeleton.  After a few short moments, the &BadMinion& raises its Inferiorlaser 1,500 and fires a shot into your side.  Abandoning the facade you grab the &BadMinion& by the wrists and swing it wide around, dashing it against the vehicle.  Wasting no time you jump into the door, moving in to rescue your target.", "NULL", "NULL", "4", "-1"},
      new String[] {"16", "You pass in and out of consciousness as you are repeatedly beaten.  Several Robosoldiers fall to your iron biceps, but there are always two more to replace them.", "NULL", "NULL", "-1", "-1"},
      new String[] {"17", "Blowing up a wall, a clever distraction.  The restraints binding you to the chair may be steel, but something like tensile material strength and human limits never stopped Macho Squad in the past.  Not when Space America needs you.  Screaming your rage you pull with all your strength.  The restraints tear free and you seize the chair, throwing it at the offending &BadMinionSlang&.  It strikes it in the chest, scattering pieces of robot, Inferiorlaser 1,500, and justices around the room.", "RoboBodies", "NULL", "-1", "-1"},
      new String[] {"18", "This is not how Macho Squad dies.  You clear the haze from your mind and focus on freeing yourself from the chair.  Straining with all of your might you can hear the metal start to strain and twist before you.  Your reverie is broken as the &BadMinionSlang& fires his weapon, the laser cutting into your shoulder.  Only a flesh wound.  WIth the pain feeding your rage you are able to summon the last few PSI required.  The restraints go flying across the room as you leap into a charge, tackling the &BadMinion& that had shot you.  Fists of fury pound the offending automaton into unrecognizable pieces.", "NULL", "NULL", "3", "1"},
      new String[] {"19", "No shackles can keep Macho Squad down for long.  You strain against the steel, hearing the metal begin to give way.  But you have no time, the &BadMinion& in front of you starts to raise its weapon.  Thinking quickly you call out to the people who are currently attacking the &BadMinionSlang&.  One of them turns, catching sight of the enemy taking aim at you and the American Flag Bandana around your head.  The man dives between the threat and you as the &BadMinion& fires his Inferiorlaser 1,500.  The patriot falls like only an unnamed non-player character can, a single tear shedding from his eye and his arm raising in a salute to the flag.  His sacrifice will be memorialized with your victory.  \n\nWith a scream of rage you tear free of the chair, hurling it at the &BadMinionSlang&.  The chair catches it in the upper arm, throwing it off balance as you follow up with a full body tackle.", "NULL", "NULL", "-1", "-1"},
      new String[] {"20", "The keys are located rather conveniently in the ignition, maybe too conveniently.  Not one to walk into a trap, you hotwire them, just to be on the safe side.", "NULL", "NULL", "-1", "-1"},
      new String[] {"21", "The hover-cycles dodge and weave through traffic as the Mechaspider fires flamethrowers, grappling hooks, and poorly designed sawblade launchers at you.  After a time you come to realize that despite being in a vehicle designed for speed and maneuverability, you`re going to have to face this Mechaspider like a man.  For Space America.  Grabbing the parking brake you pull a full 180 turn and start racing back in towards the Mechaspider.  With superhuman agility you dodge the incoming attacks, focusing your attention on the glowing orange spots visible at the Mechaspider`s leg joints and eyes.  \n\nDrawing your weapon you line up your shots, firing off several bursts into the vulnerable locations.  One by one the legs pop off of the colossal Mechaspider.  You pull the hover-cycle around, dodging a final burst from the flame spewers as you aim one final round.  The laser pierces the last eye of the Mechaspider, which slumps to the ground motionless.", "NULL", "NULL", "-1", "-1"},
      new String[] {"22", "You bob in and out of traffic as the terrifying mechanical arachnid pursues you down the main avenue of Capitalcityton.  You pull the Plasma Pulsator from the cycle`s holster and start lining up a shot.  At the last minute, just as you`re about to pull the trigger you hear a car horn.  A large truck is screaming towards you from the side.  You barely have enough time to drop the Pulsator and dodge out of the way.  Down your weapon you have only one choice remaining.  You wait for the Mechaspider to fire one of its grappling hooks at you.  Pulling to the side at the last minute, you grab the cable, accelerating back towards the hostile robot.  As you rocket past its face, you throw the grappling hook, which passes easily through the glowing eyes of the Mechaspider.", "NULL", "NULL", "3", "-1"},
      new String[] {"23", "The hover-cycle hums beneath you as you dodge the various attacks spewing forth from the Mechaspider.  It doesn`t take long to come to the realization that you weren`t going to be able to lose this foe, it would have to die.  Thinking fast you motion off to the other two hover-cycles and buck your vehicle back and up.  The Mechaspider rears up, reaching out to try and swat you out of the sky.  It doesn`t notice the other two hover-cycles cutting in towards its legs.  Striking with their ragtag assortment of weapons (one of which MAY be a hockey stick) the Resistance fighters manage to sever some of the legs.  The Mechaspider roars in pain, lashing out at their offending gnats.  Taking advantage of its distraction you swoop in towards its face, jumping free of the vehicle and grabbing one of its mandibles.  Bracing yourself against its body you pull with all your strength, tearing the piece off of the Mechaspider, and driving it down through its face.", "NULL", "NULL", "-1", "-1"},
      new String[] {"24", "The Mechaspider bears down on you from behind, knocking aside cars, and the odd building that stand in its way.  Thinking quickly you formulate a plan.  Communicating with the Resistance members with hand signals that are made unnecessary due to your radios you relay your idea.  As you round a corner, the other cycles split off down alleyways, preparing for their part in this.  When the Mechaspider rounds the corner they jet out, using grappling hooks to snare its legs and bring it down.  Gunning the engine on your hover-cycle, you start barrelling towards the &BadMinionPlural&` face.  In a last ditch effort it fires a final barrage of ordnance towards you.  One of the sawblades catches you along the cheek, giving you an appropriately manly wound.  At the last moment before impact you leap from the hover-cycle letting it collide with, and destroy, the wicked creation.", "NULL", "NULL", "4", "-1"},
      new String[] {"25", "Mechaspiders are not built in bulk, they require intensely strong power sources to properly animate.  Making your way over to it, you pry away the plating from its core and seize the core they had been using.  Hefting it above your head you admire the Fusion Powered Extendable Patriotism Unit.  You`d thought these were still in prototype testing.  As you admire the device a &BadMinion& patrol car rounds the corner, firing off its Inferiorlaser.  You duck back as a beam sears into your side.  Now is not the time for reverie, there`s a planet to free.", "NULL", "NULL", "2", "6"},
      new String[] {"26", "Yelling for the Resistance fighters to group up, you cast one final glance at the downed Mechaspider.  `One less enemy for Democracy.`  You hear the screams of &BadMinion& reinforcements approaching. You jump onto one of the remaining hover-cycles and gun it, following the Resistance fighters back to their base.", "NULL", "NULL", "-1", "-1"},
      new String[] {"27", "`Luckily.` Says the Resistance leader. `We have the two best members of Macho Squad with us.`  The door in front of you opens.  Lounging against the wall you see &BuddyFullName&.  He looks up at you, smiles, and says the longest sentence you`ve ever heard him utter.  `I got thrown free of the wreckage.` You nod knowingly, he doesn`t need to say more as you share a patriotic handshake.", "BuddyLounging", "NULL", "-1", "-1"},
      new String[] {"28", "The view changes rapidly between various short sequences of you practicing activities.  There is a shot of you throwing a medicine ball back and forth between sit ups with &BuddyNickname&.  You dodging between swinging obstacles in a course.  Finally, you sharing a laugh with some men around a conspicuously placed water cooler.", "NULL", "NULL", "-1", "" + MONTAGE_STRENGTH_FLAG},
      new String[] {"29", "The view changes rapidly between various short sequences of you practicing activities.  There is a shot of you throwing a medicine ball back and forth between sit ups with &BuddyNickname&.  You dodging between swinging obstacles in a course.  Finally, you sharing a laugh with some men around a conspicuously placed water cooler.", "NULL", "NULL", "-1", "" + MONTAGE_AGILITY_FLAG},
      new String[] {"30", "The view changes rapidly between various short sequences of you practicing activities.  There is a shot of you throwing a medicine ball back and forth between sit ups with &BuddyNickname&.  You dodging between swinging obstacles in a course.  Finally, you sharing a laugh with some men around a conspicuously placed water cooler.", "NULL", "NULL", "-1", "" + MONTAGE_COMRADERY_FLAG},
      new String[] {"31", "You stand outside the Fuel depot, a cigar perched on your lips.  Between you, &BuddyNickname&, and the Resistance fighters it had been a simple matter of clearing the &BadMinionSlang& out and rigging the place up.  You take a few puffs off the cigar and draw it out of your mouth.  `This oughta bring them out. with a bang!`  You say to nobody in particular since you are standing alone.  You flick the cigar into a pool of Bio-Fusionaline, and watch as the flames race into the cleared depot.  A smile crosses your face as you leave, seeing the banners of the Skull Battalion already moving away from the Professorial Mansion.", "NULL", "NULL", "-1", "-1"},
      new String[] {"32", "You stride out to the ledge, looking down.  Security wasn`t tight, this building wasn`t some kind of military facility.  It was just the biggest structure in town.  Reaching down you open up your Emergency Patriotism Kit.  Pushing aside the various firecrackers, freeze-dried apple pie, and miniature flags you grasp the Fusion Powered Extendable Patriotism Unit.  Pressing the button on the side you watch as a large Space American flag shot out of one end, the rod extending to give you all the length you needed for waving.  You plant one foot steadfastly on the ledge, and using all of your muscle you wave it, you wave it for Trapistan.  You wave it for Space America.  Below you can hear the enemy`s response coming.  The Skull Battalion is in full mobilization, but you won`t be there.  You eye the hover-cycle parked next to you.  You`ve got a planet to liberate.", "EmergencyFlag", "NULL", "-1", "-1"},
      new String[] {"33", "You sound the attack.  The Resistance fighters pour in around the Professorial Mansion, clearing the way for you and a small team to infiltrate via the hoverpad on the roof.", "NULL", "NULL", "-1", "-1"},
      new String[] {"34", "Quickly analyzing the situation you direct the Resistance fighters to attack all of the entrances.  Meanwhile &BuddyNickname& and you would strike the front door.  They`d never see it coming.", "NULL", "NULL", "-1", "-1"},
      new String[] {"35", "You drop down low and move towards the sewers entrance.  You pry up the grating, staring into the dank abyss as it occurs to you.  You`re Macho Squad.  Democracy won`t be saved by sneaking, now was the time for action.  Reckless, unnecessary action.", "NULL", "NULL", "-1", "-1"},
      new String[] {"36", "You gun the hover-cycle, coming in low over the hoverpad.  A squad of &BadMinionSlang& line up to fire, but don`t see &BuddyNickname& coming in from behind.  He smashes into the back of their line, grabbing one &BadMinionSlang& in each hand and wielding them like nunchucks.  You line up your weapon, picking off the remaining hostiles with ease.", "NULL", "NULL", "-1", "-1"},
      new String[] {"37", "The hover-cycle roars as you rush through the air up towards the hoverpad, however your way is barred.  A group of &BadMinionPlural& block the way.  You start evasive maneuvers, barrel rolling your way through arcs of Inferiorlaser 1,500 fire.  But you`re not quite fast enough.  Their barrage melts the control column of the bike, sending you careening down into the pad.  Luckily you walk away from the crash with little more than a few scratches and superficial wounds.  Nothing that stops you from tearing apart the remaining &BadMinionSlang&.", "NULL", "NULL", "4", "-1"},
      new String[] {"38", "You zoom up towards the hoverpad, the hover-cycle humming between your legs.  As you crest the edge you bring it down skillfully and quickly onto the pad.  The waiting squad of &BadMinionPlural& turn towards you and start advancing, weapons drawn.  You can`t help but smile as you heft the hover-cycle above your head, arms glistening in the sunlight.  The bike sails through the air, crashing into the squad and smashing them to pieces.", "NULL", "NULL", "-1", "-1"},
      new String[] {"39", "You fly the hover-cycle down towards the waiting hoverpad.  A squad of &BadMinionPlural& waiting for you as you sail down from the sun.  Winding up, you throw the object in your hand towards the first of the &BadMinionSlang&.  The baseball catches it in the head, and it crumples to the ground incapacitated.  You have little time to celebrate as the others open fire, sending your vehicle crashing down to the pad.  You pull yourself from the wreckage as you see &BuddyNickname& come up from the other side, descending on the unwary &BadMinionSlang& like a predatory bird.", "NULL", "NULL", "4", "-1"},
      new String[] {"40", "You rush up towards the front door, which is flanked by several &BadMinionPlural&.  They turn to face you, trying desperately to respond in time to prevent your vicious assault.  They fail.  One after the other the &BadMinionSlang& fall to your blows, till you are left with the final, terrified foe.  You walk up to it and deliver the last shot.  A literally Bionic Elbow drives it down to the ground, and leaves you and &BuddyNickname& alone to enter the Mansion.", "NULL", "NULL", "-1", "-1"},
      new String[] {"41", "A squad of &BadMinionSlang& are covering the front door as you approach.  Grabbing a nearby fruit stand, you start wheeling it towards the enemy to provide yourself with cover.  Their Inferiorlasers hum with power as the beams cut into your barricade, and eventually into you.  But it serves well enough, allowing you to close the distance and engage them in honorable hand-to-hand combat.  Up close the &BadMinionSlang& stand no chance, falling quickly to the Saluting Eagle Style.", "NULL", "NULL", "5", "-1"},
      new String[] {"42", "The front door is guarded by a team of &BadMinionPlural&, but thats just fine.  You prepared for this.  Grabbing the waiting Resistance member by the wrists you mimic &BuddyNickname&, swinging the man around and tossing him through the air towards the enemy.  Soaring Eagle Style.  It works every time.  With the distance closed you and your team make quick work of the guards, leaving you ready to enter the Mansion.", "NULL", "NULL", "-1", "-1"},
      new String[] {"43", "You spend some time concocting a brilliant plan.  Directing a squad of Resistance troopers to the other side of the street, you instruct them to attack while you distract the &BadMinionSlang&.  Stepping free from the cover you execute your brilliant plan to draw their attention.  `Facism is dumb!` you yell.  The &BadMinionSlang& immediately open fire, striking you several times before the Resistance fighters are able to quell them.  Your plan seems to have worked too well.", "NULL", "NULL", "5", "-1"},
      new String[] {"44", "Your victory is cut short as you hear a crash to your side, the telltale sound of a coward trying to slink away.", "NULL", "NULL", "-1", "-1"},
      new String[] {"45", "With little time, you hold your weapon out in one hand in a manner that is not actually conducive to accuracy but looks extremely cool.  Squeezing the trigger, your shot strikes the control panel, causing the blast doors to seal with a heavy thunk, leaving the Professor trapped.", "NULL", "NULL", "-1", "-1"},
      new String[] {"46", "A pair of &BadMinionPlural& poke their robotic heads out of the Reactor Control, and open fire on your position.  With reckless disregard for the health of yourself or the citizens behind you, you take careful aim at the control panel.  Ignoring the grazing wounds taken, you blast the lock to kingdom come, sealing the Professor in with you.", "NULL", "NULL", "5", "-1"},
      new String[] {"47", "There's no time, the professor is nearly at the door and you need to stop him.  Reaching into your Emergency Patriotism Kit you pull forth the Space American Self Inflating Teamwork Tool.  Feeling it's comforting weight in your hand, you take aim, and throw it.  The football sails through the air, striking the `Close and Permanently Lock` button on the control panel just before the Professor could make it through.", "NULL", "NULL", "-1", "-1"},
      new String[] {"48", "You flash back to High School, where coach warned you about injuring yourself further.  There’s no time. You take the Space American Self Inflating Teamwork Tool and pass it to a citizen.  They stand the football on end, ready for your kickoff.  Just as The Professor is about to reach the door, you stride forward and send the ball flying.  Searing pain shoots up your leg from that old Space Football injury, but the ball itself strikes the control panel, locking him in with you.", "NULL", "NULL", "5", "-1"},
      new String[] {"49", "You reach to your belt, and draw your own Quantum Nunchucks just in time to deflect his first attack.  Sparks, curses, and dimensional anomalies fly in all directions as the weapons strike each other.  &ProfessorNameShort& is skilled, but he’s not the leader of Macho Squad.  After an initial couple of harrowing strikes you come back stronger than before, and send his weapons flying to the ground.", "NULL", "NULL", "-1", "-1"},
      new String[] {"50", "As you lower your weapon and reach for the Quantum Nunchucks on your belt &ProfessorNameShort& strikes out, catching you across the chin.  Your life flashes before your eyes, a slideshow of success after success.  Graduating first in your Platoon at Kindergarten.  Catching 800 game winning touchdowns.  Signing up for the Space Army at 16.5.  But this is how it ends.  Everything going dark because you were too slow.", "NULL", "NULL", "100", "-1"},
      new String[] {"51", "As you begin to lower your weapon to get your own Nunchucks you see him move.  Acting fast you throw yourself into the Professor, wrestling to gain control of the Quantum Nunchucks.  With a grunt of extreme Democratic force, you pull the two ends of the weapon away from each other.  The Titanoluminumcrete rods groan and pop under the raw power of Macho Squad’s undisputed leader.  With a crack it gives way, sending red, white, and blue sparks showering around the area.", "NULL", "NULL", "-1", "-1"},
      new String[] {"52", "You bolt forward as fast as you can, seizing a handhold on the Quantum Nunchucks.  For what seems like minutes you sway back and forth, fighting for control of this apocalyptically powerful weapon.  Just as it looks like you might get the upper hand however, &ProfessorFullTitle& smirks a wicked grin, and stomps his foot into the ground.  A small set of Quantum Nunchucks shoot forth from the tip of his dress shoes.  With a cowardly kick to the shins the fight changes, and the Professor gets the upper hand.  The last thing you see is the crackling blue energy of the Quantum Nunchucks as they descend towards your face.", "NULL", "NULL", "100", "-1"},
      new String[] {"53", "Your mission was to save people, and you’ll die before you let runaway scientists with no regard for safety kill more innocent people.  Motioning to &BuddyFullName&, you take your position at the door.  Using the full physical might of Macho squad helped by the wheezing verbal support of the Nerds, you manage to pry the blast doors open.  With only 1 second left on the clock, you press the “Undo” button, reversing the overload and saving Trapistan to Vote another day.", "NULL", "NULL", "-1", "-1"},
      new String[] {"54", "Your mission was to liberate the Professor, and given the beat down recently applied you can safely say that he’s been liberated from something.  Some days Democracy can thrive on the belief of its people.  Sometimes it requires the sacrifice of an entire planet.  Taking &BuddyFullName& with you, Macho Squad flees to Space just before the Solar Power Plant goes critical, annihilating the entire world.", "NULL", "NULL", "-1", "-1"},
      new String[] {"55", "Thanks for playing!", "NULL", "NULL", "-1", "" + GAME_OVER_FLAG}
      // When adding new popups, make sure they don't overlap with current numbers
    };
    // END popup details

    // BEGIN item details
    public static final ItemData[] itemTemplates = new ItemData[] {
      new ItemData("Flag Bandana", 1, ITEM_TYPE_FLAG, STRENGTH_ID, 0, "Wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop, and you move to salvage what you can. \n\nIn one of the hardest moments of your long and storied career, you come across one of &BuddyNickname&’s American Flag Bandanas.  With a moment of flexing silence you wrap it around your own head.  He’s suplexing angels now, and if these robots are half the trouble command expects them to be you’ll need all the Patriotism you can get."),
      new ItemData("MegaLaser 3000", 2, ITEM_TYPE_GUN, AGILITY_ID, 0, "Wreckage covers most of the nearby fields, pieces burning poetically against the night sky backdrop, and you move to salvage what you can. \n\nWith some dedication, gumption, and the skills you acquired in the volunteer commando firefighters, you manage to find something useful while sifting through the wreckage.  You find a Megalaser 3,000 with a full Cased Laser Injection Pod, or CLIP."),
      new ItemData("Hazard`s Hat", 2, ITEM_TYPE_PANACHE, COMRADERY_ID, 0, "You noticed that Hazard’s pod had gone down close to your drop zone. It was on fire, and parts of the metal had been vaporized, but damnit, Hazard is one of the most American men you know and he wouldn’t let something like a 40,000 foot drop end him. Not while there are Space Nazis to defeat.  \n\nYou reach a small stand of trees with the remains of Hazard’s pod littered all around it.  There`s almost nothing left, just some plating sitting on the ground and flames licking at the heated metal.  A broken jar of military grade flexing oil spills its precious contents into the ground.  But worst of all, sitting on the ground is Duke Hazard’s Cowboy Hat, given to him by the Space President.  You take it, knowing he’d want you to bring it to his wife."),
      new ItemData("Brass Knuckles", 1, ITEM_TYPE_WEAPON, STRENGTH_ID, 0, "You find some scrap metal in serviceable enough condition to be wrapped into crude brass knuckles.  Internally you give thanks to your Space American ingenuity, then move on.")
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
        nameReplacements.put("&ProfessorNameShort&", "Judas");
        nameReplacements.put("&BadMinion&", "RoboTron");
        nameReplacements.put("&BadMinionPlural&", "RoboTrons");
        nameReplacements.put("&BadMinionSlang&", "Bots");
        nameReplacements.put("&BuddyFullName&", "Duke Ramierez");
        nameReplacements.put("&BuddyNickname&", "Duke");

        return nameReplacements;
    }

    // END item details
}



