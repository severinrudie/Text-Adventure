package com.rudie.severin.textadventure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;

public class LauncherActivity extends AppCompatActivity
        implements CharacterSelectFragment.OnCharacterCreatedListener {

    private boolean fragmentCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DBInterfacer helper = DBInterfacer.getInstance(this);

        Button newGame = (Button) findViewById(R.id.buttonLauncherNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCreated = true;
                CharacterSelectFragment fragment = new CharacterSelectFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayoutLauncher, fragment);
                transaction.commit();
                // TODO: set fragment tag.  delete it onresume if fragmentCreated is true;
            }
        });

//        TODO: temp code. resets DB on every new instance
        helper.dropAllTables();
        helper.onCreate(helper.getWritableDatabase());

//        TODO: temp code.  gives player 1 a weapon
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String sql = "INSERT INTO table_inventory (inventory_id, inventory_name, inventory_power, " +
//                "inventory_type_id, inventory_character_id) " + "VALUES ('1', 'bigGun', '1', '1', '1');";
//        db.execSQL(sql);
//        TODO: end temp code

    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (fragmentCreated) {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//
//            fragmentCreated = false;
//        }
        if (fragmentCreated) {
            FragmentManager manager = this.getSupportFragmentManager();
            CharacterSelectFragment fragment = (CharacterSelectFragment) manager.getFragments().get(0);
            manager.beginTransaction().remove(fragment).commit();
            fragmentCreated = false;
        }
    }

    @Override
    public void closeFragment() {
//        FragmentManager manager = this.getSupportFragmentManager();
//        CharacterSelectFragment fragment = (CharacterSelectFragment) manager.getFragments().get(0);
//        manager.beginTransaction().remove(fragment).commit();
        fragmentCreated = true;
    }
}
