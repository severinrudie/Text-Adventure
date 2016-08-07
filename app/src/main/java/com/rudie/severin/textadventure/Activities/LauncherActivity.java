package com.rudie.severin.textadventure.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rudie.severin.textadventure.Activities.LoadActivity;
import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.ImageConstructor;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.R;

public class LauncherActivity extends AppCompatActivity
        implements CharacterSelectFragment.OnCharacterCreatedListener {

    private boolean fragmentCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        TextView titleMain1 = (TextView) findViewById(R.id.titlemain1);
        TextView titleMain2 = (TextView) findViewById(R.id.titlemain2);
        Typeface font = Typeface.createFromAsset(getAssets(), "BLADRMF_.TTF");
        titleMain1.setTypeface(font);
        titleMain2.setTypeface(font);
//        titleMain1.setTextScaleX((float) 0.7 );
//        titleMain2.setTextScaleX((float) 0.7 );


        ImageConstructor imageConstructor = ImageConstructor.getInstance();
        imageConstructor.giveContext(this);

        DBInterfacer helper = DBInterfacer.getInstance(this);

        Button newGame = (Button) findViewById(R.id.buttonLauncherNewGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Create the fragment and show it as a dialog.
                    FragmentManager manager = getSupportFragmentManager();
                    CharacterSelectFragment newFragment = CharacterSelectFragment.newInstance();
                    newFragment.show(manager, "dialog");
//                }
            }
        });

        Button loadGame = (Button) findViewById(R.id.buttonLauncherLoadSave);
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the fragment and show it as a dialog.
                Intent intent = new Intent(getBaseContext(), LoadActivity.class);
                startActivity(intent);
//                }
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
//        ItemData item = new ItemData("Bigass Sword", 10, 2, 1, "You got a bigass sword!");
//        helper.addItemToInventory(item);
//        TODO: end temp code

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fragmentCreated) {
            FragmentManager manager = this.getSupportFragmentManager();
            CharacterSelectFragment fragment = (CharacterSelectFragment) manager.getFragments().get(0);
            manager.beginTransaction().remove(fragment).commit();
            fragmentCreated = false;
        }
    }

    @Override
    public void closeFragmentOnResume() {
        fragmentCreated = true;
    }
}
