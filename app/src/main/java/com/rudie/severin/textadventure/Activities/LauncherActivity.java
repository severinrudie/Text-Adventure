package com.rudie.severin.textadventure.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.koushikdutta.ion.Ion;
import com.rudie.severin.textadventure.Activities.LoadActivity;
import com.rudie.severin.textadventure.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;
import com.rudie.severin.textadventure.InformationHolders.ImageConstructor;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;
import io.fabric.sdk.android.Fabric;

public class LauncherActivity extends AppCompatActivity
        implements CharacterSelectFragment.OnCharacterCreatedListener {

    private boolean fragmentCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Answers(), new Crashlytics());
        setContentView(R.layout.activity_launcher);

        ImageView imageView = (ImageView) findViewById(R.id.imageView_buffEagle_launcherActivity);
        String url = ImageConstructor.getInstance().getDrawable("BuffEagle");
        Ion.with(imageView)
//                .placeholder(R.color.colorPrimary)
//                .error(R.color.colorAccent)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(url)
                .withBitmapInfo();
//                    .setCallback(NotFoundImageLoader.handleNotFound(holder.photo, mContext));

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
        // TODO: make this async
        helper.verifyDbExistsOrCreate();

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

////        TODO: temp code. resets DB on every new instance
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.dropAllTables(db);
        System.out.println("");
        helper.onCreate(helper.getWritableDatabase());
//
////        TODO: temp code.  gives player 1 a weapon
////        SQLiteDatabase db = helper.getWritableDatabase();
////        String sql = "INSERT INTO table_inventory (inventory_id, inventory_name, inventory_power, " +
////                "inventory_type_id, inventory_character_id) " + "VALUES ('1', 'bigGun', '1', '1', '1');";
////        db.execSQL(sql);
////        ItemData item = new ItemData("Bigass Sword", 10, 2, 1, "You got a bigass sword!");
////        helper.addItemToInventory(item);
////        TODO: end temp code

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
