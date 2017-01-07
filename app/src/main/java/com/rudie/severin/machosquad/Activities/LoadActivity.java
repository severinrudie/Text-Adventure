package com.rudie.severin.machosquad.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rudie.severin.machosquad.Adapters.LoadAdapter;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.EventBus.RefreshLoadListBus;
import com.rudie.severin.machosquad.InformationHolders.Character;
import com.rudie.severin.machosquad.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class LoadActivity extends AppCompatActivity {

  List<Character> characters;
  RefreshLoadListBus refreshLoadListBus;
  CompositeDisposable compositeDisposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load);

    refreshLoadList();

    Toast.makeText(this, "Long press a saved game to delete it", Toast.LENGTH_SHORT).show();

    compositeDisposable = new CompositeDisposable();
    refreshLoadListBus = RefreshLoadListBus.getInstance();
    compositeDisposable.add(refreshLoadListBus.getSubject().subscribe(event -> refreshLoadList()));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    compositeDisposable.clear();
  }

  public void refreshLoadList() {
    characters = getUniqueCharacters();
    LoadAdapter adapter = new LoadAdapter(this, characters);
    RecyclerView rvLoad = (RecyclerView) findViewById(R.id.recyclerviewLoad);
    rvLoad.setAdapter(adapter);
    rvLoad.setLayoutManager(new LinearLayoutManager(this));
      onPostResume();
  }

  @Override
  protected void onPostResume() {
      super.onPostResume();
      TextView textView = (TextView) findViewById(R.id.textView_noCharactersFound_loadActivity);
      if (characters.size() > 0) {
        textView.setVisibility(View.GONE);
      } else {
        textView.setVisibility(View.VISIBLE);
      }
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
