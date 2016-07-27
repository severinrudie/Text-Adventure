package com.rudie.severin.textadventure.FragmentClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
            "Hunter", "Drake", "Victor", "Rex", "Ryker"};
    String[] nickNames = new String[] {"'Tank'", "'Dutch'", "'Stone'", "'Maverick'", "'the Cleaner'"};
    String[] lastNames = new String[] {"Hazard", "Rock", "Fletcher", "Bronson", "Archer", "Power"};

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

        return first + " " + nick + " " + last;
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

