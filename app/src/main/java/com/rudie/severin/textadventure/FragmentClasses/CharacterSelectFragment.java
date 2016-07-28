package com.rudie.severin.textadventure.FragmentClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rudie.severin.textadventure.DBInterfacer;
import com.rudie.severin.textadventure.R;

import java.util.HashMap;

public class CharacterSelectFragment extends Fragment {

    String[] firstNames = new String[] {"Butch", "Max", "Flint", "Gunner", "Axel",
            "Hunter", "Drake", "Victor", "Rex", "Ryker", "Lance","Dirk","Brick","Rick","Chuck",
            "Flash","Slash","Smash","Crash","Beef","Rock","Biff","Buff","Lloyd","Brock","Guy",
            "Slab","Drake","Blake","Rip","Zap","Hack","Hunk","Chunk","Blast","Rage","Mace","Mac",
            "Crunk","Dick","Rod","Ken","Kirk","Clint","Flint","Buck","Volt","Bolt","Stack","Butch",
            "Splint","Bull","Fist","Doug","Blade","Slag","Dash","Flak","Punch","Bud","Pork",
            "Wolf","Frag","Blitz","Sid","Hulk","Max","Spike","Clutch","Scab","Mitch","Wayne","Spud",
            "Lug","Vic","Stud","Skid","Stag","Pike","Bash","Rush","Thrust","Wedge","Clench","Fritz",
            "Punt","Edge","Smug","Shunt","Clamp"};
    String[] nickNames = new String[]{"Tank", "Dutch", "Stone", "Maverick", "Cleaner", "Thunder",
            "Boulder", "Vander", "Hard", "Light", "Iron", "Steel", "Storm", "Golden", "Stern", "Power",
            "Ripping", "Shatter", "Blaster", "Slaughter", "Lion", "Dragon", "Hammer", "Doom",
            "Gryphon", "Muscle", "Shining", "Fire", "Blazing", "Glory", "Mangle",
            "Strangler", "Gristle", "Manly", "Killer", "Razor", "Gleaming", "Lethal", "Royal",
            "Mighty", "Heavy", "Falcon", "Phoenix", "Saber", "Rocket", "War", "Anger", "Mega",
            "Holy", "Burning", "Mondo", "Battle", "Hulking", "Beefy", "Tackle", "Strong", "Brawny",
            "Burly", "Wonder", "Strapping", "Rugged", "Meaty", "Thick", "Bulky", "Sunder", "Husky",
            "Oxen", "Anvil", "Bold", "Brave", "Smashing", "Eagle", "Steak", "Ham", "Roaring",
            "Macho", "Noble", "Righteous", "Pickle", "Sizzle", "Quick", "Lightning", "Mortar"};

//    String theNickNames = new String[] {};

    String[] lastNames = new String[] {"Hazard", "Rock", "Fletcher", "Bronson", "Archer", "Power",
            "Beef","Blast","Blow","Buff","Bulk","Bullet","Burp","Crumple","Fist","Hard",
            "Iron","Lamp","Large","Plank","Pork","Rock","Slam","Steel","Thorn","Thud","Thunder",
            "Vander"};
    String[] prefixNames = new String[] {"Mac", "Mc", "Van", "Von", "O"};

    String STRENGTH = "Strength";
    String AGILITY = "Agility";
    String COMRADERY = "Comradery";

    TextView topSkill, midSkill, botSkill;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_select, container, false);

        topSkill = (TextView) view.findViewById(R.id.textviewCharacterFragmentTop);
        midSkill = (TextView) view.findViewById(R.id.textviewCharacterFragmentMid);
        botSkill = (TextView) view.findViewById(R.id.textviewCharacterFragmentBot);

        ImageButton topDown = (ImageButton) view.findViewById(R.id.buttonCharacterSelectFragmentTopDown);
        topDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapSkills(topSkill, midSkill);
            }
        });
        final ImageButton midDown = (ImageButton) view.findViewById(R.id.buttonCharacterSelectFragmentMidDown);
        midDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapSkills(midSkill, botSkill);
            }
        });
        ImageButton midUp = (ImageButton) view.findViewById(R.id.buttonCharacterSelectFragmentMidUp);
        midUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapSkills(midSkill, topSkill);
            }
        });
        ImageButton botUp = (ImageButton) view.findViewById(R.id.buttonCharacterSelectFragmentBotUp);
        botUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapSkills(botSkill, midSkill);
            }
        });

        final EditText editText = (EditText) view.findViewById(R.id.edittextCharacterSelectFragment);
        editText.setText(generateRandomName());

        Button randomize = (Button) view.findViewById(R.id.buttonCharacterSelectFragmentRandomizeName);
        randomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(generateRandomName());
            }
        });

        final Button beginGame = (Button) view.findViewById(R.id.buttonCharacterSelectFragmentBeginGame);
        beginGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().length() > 0) {
                    passCharacterToDb(editText, topSkill, midSkill, botSkill);
                } else {
//                    TODO: make a toast or something if the field is blank
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    public String generateRandomName() {
        String first = firstNames[(int) (Math.random()*firstNames.length)];
        String nick = nickNames[(int) (Math.random()*nickNames.length)];
        String last = lastNames[(int) (Math.random()*lastNames.length)];
        double random = Math.random();
        if (random < .3) {
            String lastPrefix = prefixNames[(int) (Math.random()*(prefixNames.length))];
            lastPrefix += last;
            last = lastPrefix;
        } else if (random < .6) {
            String nickPrefix = "the ";
            nickPrefix += nick;
            nick = nickPrefix;
        }
        return first + " '" + nick + "' " + last;
    }

    public void swapSkills(TextView from, TextView to) {
        String temp = to.getText().toString();
        to.setText(from.getText().toString());
        from.setText(temp);
    }

//    getNames and getSkills contain all logic for deciding names/skills.  Node here is set to 0
//    (the first node) and backup for to null (this is a new character).  This method only passes
//    information to the DBInterfacer
    public void passCharacterToDb(EditText editText, TextView top, TextView mid, TextView bot) {
        String[] names = getNames(editText);
        String firstName = names[0];
        String nickName = names[1];
        String lastName = names[2];

        HashMap<String, Integer> skills = getSkills(top, mid, bot);

        DBInterfacer db = DBInterfacer.getInstance(this.getContext());
        db.enterCharacterIntoDB(firstName, nickName, lastName, skills, 0, null);
    }

    public String[] getNames(EditText editText) {
        String[] names = editText.getText().toString().split(" ");
        String firstName = names[0];
        String nickName = "";
        String lastName = "";
        if (names.length > 1) {
            lastName = names[names.length - 1];
        }
        if (names.length > 2) {
            nickName = names[1];
            for (int i = 2; i < names.length - 1; i++) {
                nickName += " " + names[i];
            }
        }
//        apostraphes will be added on to nicknames after withdrawal from the DB
        nickName = nickName.replace("'", "");
        nickName = nickName.replace("\"", "");
        return new String[] {firstName, nickName, lastName};
    }

    public HashMap<String, Integer> getSkills(TextView top, TextView mid, TextView bot) {
        HashMap<String, Integer> skillMap = new HashMap<>();
        skillMap.put(top.getText().toString(), 3);
        skillMap.put(mid.getText().toString(), 2);
        skillMap.put(bot.getText().toString(), 1);
        return skillMap;
    }



}

