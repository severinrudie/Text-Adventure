package com.rudie.severin.textadventure.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudie.severin.textadventure.Activities.PlayActivity;
import com.rudie.severin.textadventure.InformationHolders.Character;
import com.rudie.severin.textadventure.InformationHolders.ItemData;
import com.rudie.severin.textadventure.InformationHolders.PH;
import com.rudie.severin.textadventure.R;

import java.util.List;

/**
 * Created by erikrudie on 8/2/16.
 */
public class LoadAdapter extends
        RecyclerView.Adapter<LoadAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, characterId, hp, inventorySize, bestSkill;
        private View parent;

        public ViewHolder(View loadView) {
            super(loadView);
//            characterId = (TextView) loadView.findViewById(R.id.textview_charId_loadRecyclerview);
            hp = (TextView) loadView.findViewById(R.id.textview_hp_loadRecyclerview);
            inventorySize = (TextView) loadView.findViewById(R.id.textview_inventorySize_loadRecyclerview);
            bestSkill = (TextView) loadView.findViewById(R.id.textview_bestSkill_loadRecyclerview);
            name = (TextView) loadView.findViewById(R.id.textview_name_loadRecyclerview);
            parent = loadView;
        }
    }

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

        ViewHolder viewHolder = new ViewHolder(loadView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LoadAdapter.ViewHolder viewHolder, int position) {
        final Character character = characters.get(position);

//        characterId = (TextView) itemView.findViewById(R.id.textview_charId_loadRecyclerview);
//        hp = (TextView) itemView.findViewById(R.id.textview_hp_loadRecyclerview);
//        inventorySize = (TextView) itemView.findViewById(R.id.textview_inventorySize_loadRecyclerview);
//        bestSkill = (TextView) itemView.findViewById(R.id.textview_bestSkill_loadRecyclerview);

        TextView name = viewHolder.name;
//        TextView characterId = viewHolder.characterId;
        TextView hp = viewHolder.hp;
        TextView inventorySize = viewHolder.inventorySize;
        TextView bestSkill = viewHolder.bestSkill;

        name.setText(character.getFullName());
//        characterId.setText("" + character.getCharId() + "     ");
        hp.setText("HP: " + character.getHp() + " ");
        inventorySize.setText("Items: " + character.getInventory().size() + " ");
        bestSkill.setText(character.getBestSkillBlurb());

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlayActivity.class);
                intent.putExtra(PH.CURRENT_CHARACTER, character.getCharId());
                getContext().startActivity(intent);
            }
        });

        viewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
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

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return characters.size();
    }

}

