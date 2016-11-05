package com.rudie.severin.machosquad.FragmentClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rudie.severin.machosquad.Activities.PlayActivity;
import com.rudie.severin.machosquad.Adapters.CharacterStatisticsAdapter;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.InformationHolders.PH;
import com.rudie.severin.machosquad.R;
import com.rudie.severin.machosquad.Utility.OnStartDragListener;
import com.rudie.severin.machosquad.Utility.SimpleItemTouchHelperCallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CharacterSelectFragment extends DialogFragment
  implements OnStartDragListener {
  OnCharacterCreatedListener mCallback;
  Context mContext;
  List<String> mStatistics;

  ItemTouchHelper mItemTouchHelper;
  String[] firstNames = new String[]{"Butch", "Max", "Flint", "Gunner", "Axel",
    "Hunter", "Drake", "Victor", "Rex", "Ryker", "Lance", "Dirk", "Brick", "Rick", "Chuck",
    "Flash", "Slash", "Smash", "Crash", "Beef", "Rock", "Biff", "Buff", "Lloyd", "Brock", "Guy",
    "Slab", "Drake", "Blake", "Rip", "Zap", "Hack", "Hunk", "Chunk", "Blast", "Rage", "Mace", "Mac",
    "Crunk", "Dick", "Rod", "Ken", "Kirk", "Clint", "Flint", "Buck", "Volt", "Bolt", "Stack", "Butch",
    "Splint", "Bull", "Fist", "Doug", "Blade", "Slag", "Dash", "Flak", "Punch", "Bud", "Pork",
    "Wolf", "Frag", "Blitz", "Sid", "Hulk", "Max", "Spike", "Clutch", "Scab", "Mitch", "Wayne", "Spud",
    "Lug", "Vic", "Stud", "Skid", "Stag", "Pike", "Bash", "Rush", "Thrust", "Wedge", "Clench", "Fritz",
    "Punt", "Edge", "Smug", "Shunt", "Clamp"};
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
  String[] lastNames = new String[]{"Hazard", "Rock", "Fletcher", "Bronson", "Archer", "Power",
    "Beef", "Blast", "Blow", "Buff", "Bulk", "Bullet", "Burp", "Crumple", "Fist", "Hard",
    "Iron", "Lamp", "Large", "Plank", "Pork", "Rock", "Slam", "Steel", "Thorn", "Thud", "Thunder",
    "Vander", "Hamcrest"};
  String[] prefixNames = new String[]{"Mac", "Mc", "Van", "Von", "O'"};

  public static CharacterSelectFragment newInstance() {
    return new CharacterSelectFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_character_select, container, false);

    RecyclerView rvStats = (RecyclerView) view.findViewById(R.id.recyclerview_stats_characterSelectFragment);

    mStatistics = Arrays.asList(new String[]{"Strength", "Agility", "Comradery"});
    CharacterStatisticsAdapter adapter = new CharacterStatisticsAdapter(getContext(), mStatistics, this);
    rvStats.setAdapter(adapter);
    rvStats.setLayoutManager(new LinearLayoutManager(getContext()));

    ItemTouchHelper.Callback callback =
      new SimpleItemTouchHelperCallback(adapter);
    mItemTouchHelper = new ItemTouchHelper(callback);
    mItemTouchHelper.attachToRecyclerView(rvStats);

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

          int currentCharacterId = passCharacterToDb(editText, mStatistics.get(0),
            mStatistics.get(1), mStatistics.get(2));

          DBInterfacer helper = DBInterfacer.getInstance(getActivity());
          helper.giveCharacterStartingInventory(currentCharacterId);
          // TODO: this currently goes to PlayActivity, but once animations are in it will direct
          // TODO: there for the opening animation instead
          Intent intent = new Intent(getActivity(), PlayActivity.class);
          intent.putExtra(PH.CURRENT_CHARACTER, currentCharacterId);
          startActivity(intent);
          try {
            mCallback = (OnCharacterCreatedListener) mContext;
            mCallback.closeFragmentOnResume();
          } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString()
              + " must implement PopupCompleteListener");
          }
        } else {
          Toast.makeText(getActivity(), "You gotta enter a name first!", Toast.LENGTH_SHORT).show();
        }
      }
    });

    return view;
  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    mItemTouchHelper.startDrag(viewHolder);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setRetainInstance(true);
  }

  public String generateRandomName() {
    String first = firstNames[(int) (Math.random() * firstNames.length)];
    String nick = nickNames[(int) (Math.random() * nickNames.length)];
    String last = lastNames[(int) (Math.random() * lastNames.length)];
    double random = Math.random();
    if (random < .3) {
      String lastPrefix = prefixNames[(int) (Math.random() * (prefixNames.length))];
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
  public int passCharacterToDb(EditText editText, String top, String mid, String bot) {
    String[] names = getNames(editText);
    String firstName = names[0];
    String nickName = names[1];
    String lastName = names[2];

    HashMap<String, Integer> skills = getSkills(top, mid, bot);

    DBInterfacer db = DBInterfacer.getInstance(this.getContext());
    int currentCharacterId = db.enterCharacterIntoDb(firstName, nickName, lastName, skills, 1, null);
    return currentCharacterId;
  }

  public String[] getNames(EditText editText) {
    String[] names = editText.getText().toString().split(" ");
    String firstName = names[0];
    String nickName = PH.NULL;
    String lastName = PH.NULL;
    if (names.length > 1) {
      lastName = names[names.length - 1];
    }
    if (names.length > 2) {
      nickName = names[1];
      for (int i = 2; i < names.length - 1; i++) {
        nickName += " " + names[i];
      }
    }
    return new String[]{firstName, nickName, lastName};
  }

  public HashMap<String, Integer> getSkills(String top, String mid, String bot) {
    HashMap<String, Integer> skillMap = new HashMap<>();
    skillMap.put(top, 3);
    skillMap.put(mid, 2);
    skillMap.put(bot, 1);
    return skillMap;
  }

  public interface OnCharacterCreatedListener {
    void closeFragmentOnResume();
  }


}

