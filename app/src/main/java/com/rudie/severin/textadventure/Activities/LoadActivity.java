package com.rudie.severin.textadventure.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rudie.severin.textadventure.Adapters.LoadAdapter;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.Character;
import com.rudie.severin.textadventure.R;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        List<Character> characters = getUniqueCharacters();
        LoadAdapter adapter = new LoadAdapter(this, characters);
        RecyclerView rvLoad = (RecyclerView) findViewById(R.id.recyclerviewLoad);
        rvLoad.setAdapter(adapter);
        rvLoad.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Character> getUniqueCharacters() {
        DBInterfacer helper = DBInterfacer.getInstance(this);
        List<Integer> charIds = helper.getUniqueCharacterIds();
        List<Character> characters = new ArrayList<>();

        for (Integer i : charIds) {
            characters.add(new Character(i, this));
        }
        return characters;
    }


}
