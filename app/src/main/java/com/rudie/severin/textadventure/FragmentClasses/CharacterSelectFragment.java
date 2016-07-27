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

import com.rudie.severin.textadventure.R;

public class CharacterSelectFragment extends Fragment {

    String[] firstNames = new String[] {"Butch", "Max", "Flint", "Gunnar", "Axel",
            "Hunter", "Drake", "Victor", "Ryker"};
    String[] nickNames = new String[] {"'Tank'", "'Dutch'", "'Stone'", "'Maverick'"};
    String[] lastNames = new String[] {"Hazard", "Rock", "Fletcher", "Bronson", "Archer"};

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
}

