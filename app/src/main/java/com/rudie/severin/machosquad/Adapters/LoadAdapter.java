package com.rudie.severin.machosquad.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudie.severin.machosquad.Activities.PlayActivity;
import com.rudie.severin.machosquad.DatabaseClasses.DBInterfacer;
import com.rudie.severin.machosquad.EventBus.RefreshLoadListBus;
import com.rudie.severin.machosquad.InformationHolders.Character;
import com.rudie.severin.machosquad.InformationHolders.PH;
import com.rudie.severin.machosquad.R;

import java.util.List;


public class LoadAdapter extends
  RecyclerView.Adapter<LoadAdapter.ViewHolder> {

  private List<Character> characters;
  private Context mContext;
  public LoadAdapter(Context context, List<Character> characterList) {
    characters = characterList;
    mContext = context;
  }

  private Context getContext() {
    return mContext;
  }

  @Override
  public LoadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View loadView = inflater.inflate(R.layout.recycleritem_load_layout, parent, false);

    ViewHolder viewHolder = new ViewHolder(loadView, context);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(LoadAdapter.ViewHolder viewHolder, int position) {
    final Character character = characters.get(position);

    TextView name = viewHolder.name;
    TextView hp = viewHolder.hp;
    TextView inventorySize = viewHolder.inventorySize;
    TextView bestSkill = viewHolder.bestSkill;

    name.setText(character.getFullName());
    hp.setText("HP: " + character.getHp() + " ");
    inventorySize.setText("Items: " + character.getInventory().size() + " ");
    bestSkill.setText(character.getBestSkillBlurb());

    viewHolder.parent.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getContext(), PlayActivity.class);
        intent.putExtra(PH.CURRENT_CHARACTER, character.getCharId());
        ((Activity)mContext).finish();
        getContext().startActivity(intent);
      }
    });

    viewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case DialogInterface.BUTTON_POSITIVE:
                DBInterfacer.getInstance(getContext())
                  .deleteCharacterFromDb(character.getCharId());
                RefreshLoadListBus bus = RefreshLoadListBus.getInstance();
                bus.refreshLoadList();
                break;

              case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
            }
          }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Would you like to delete this saved game?")
          .setIcon(R.drawable.ic_delete_black_24dp)
          .setTitle("Delete Character")
          .setPositiveButton("Yes", dialogClickListener)
          .setNegativeButton("No", dialogClickListener);
        AlertDialog alert = builder.create();
        alert.show();


        return true;
      }
    });

  }

  @Override
  public int getItemCount() {
    return characters.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name, characterId, hp, inventorySize, bestSkill;
    private View parent;

    public ViewHolder(View loadView, Context context) {
      super(loadView);
      hp = (TextView) loadView.findViewById(R.id.textview_hp_loadRecyclerview);
      inventorySize = (TextView) loadView.findViewById(R.id.textview_inventorySize_loadRecyclerview);
      bestSkill = (TextView) loadView.findViewById(R.id.textview_bestSkill_loadRecyclerview);
      name = (TextView) loadView.findViewById(R.id.textview_name_loadRecyclerview);
      parent = loadView;
    }
  }


}



