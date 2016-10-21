package com.rudie.severin.machosquad.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.koushikdutta.ion.Ion;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.FragmentClasses.CharacterSelectFragment;
import com.rudie.severin.machosquad.InformationHolders.ImageConstructor;
import com.rudie.severin.machosquad.R;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.concurrency.AsyncTask;

public class LauncherActivity extends AppCompatActivity
  implements CharacterSelectFragment.OnCharacterCreatedListener {

  private boolean fragmentCreated;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Fabric.with(this, new Answers(), new Crashlytics());
    setContentView(R.layout.activity_launcher);

    ImageView imageView = (ImageView) findViewById(R.id.imageView_buffEagle_launcherActivity);

    String url = "Dummy URL to load error image";

    Ion.with(imageView)
      .error(R.drawable.buff_bird)
      .placeholder(R.drawable.buff_bird)
      .load(url);

    TextView titleMain1 = (TextView) findViewById(R.id.titlemain1);
    TextView titleMain2 = (TextView) findViewById(R.id.titlemain2);
    Typeface font = Typeface.createFromAsset(getAssets(), "BLADRMF_.TTF");
    titleMain1.setTypeface(font);
    titleMain2.setTypeface(font);

    ImageConstructor imageConstructor = ImageConstructor.getInstance();
    imageConstructor.giveContext(this);

    CreateDbAsyncTask asyncTask = new CreateDbAsyncTask();
    asyncTask.execute();

    Button newGame = (Button) findViewById(R.id.buttonLauncherNewGame);
    newGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        CharacterSelectFragment newFragment = CharacterSelectFragment.newInstance();
        newFragment.show(manager, "dialog");
      }
    });

    Button loadGame = (Button) findViewById(R.id.buttonLauncherLoadSave);
    loadGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), LoadActivity.class);
        startActivity(intent);
//                }
      }
    });

  }

  private class CreateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
      DBInterfacer helper = DBInterfacer.getInstance(getBaseContext());
      helper.verifyDbExistsOrCreate();
      return null;
    }
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
