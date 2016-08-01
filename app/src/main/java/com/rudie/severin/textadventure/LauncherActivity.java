package com.rudie.severin.textadventure;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.textadventure.UtilityClasses.DBInterfacer;

public class LauncherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DBInterfacer helper = DBInterfacer.getInstance(this);

        Button newGame = (Button) findViewById(R.id.buttonLauncherNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharacterSelectFragment fragment = new CharacterSelectFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayoutLauncher, fragment);
                transaction.commit();
            }
        });



//        TODO: temp code. resets DB on every new instance
        helper.dropAllTables();
        helper.onCreate(helper.getWritableDatabase());

        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "INSERT INTO table_inventory (inventory_id, inventory_name, inventory_power, " +
                "inventory_type_id, inventory_character_id) " + "VALUES ('1', 'bigGun', '1', '1', '1');";
        db.execSQL(sql);
//        TODO: end temp code

    }

}
