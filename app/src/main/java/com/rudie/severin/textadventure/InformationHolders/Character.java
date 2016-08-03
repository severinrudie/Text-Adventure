package com.rudie.severin.textadventure.InformationHolders;

import android.content.Context;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by erikrudie on 8/3/16.
 */
public class Character {

    private int charId, hp, strength, agility, comradery, atNode;
    private String firstName, nickName, lastName;
    private List<ItemData> inventory;

    public Character(int charId, Context context) {
        this.charId = charId;
        DBInterfacer helper = DBInterfacer.getInstance(context);
        String[] firstNickLast = helper.getCharacterFirstNickLast(charId, context);
        firstName = firstNickLast[0];
        nickName = firstNickLast[1];
        lastName = firstNickLast[2];
        HashMap<Integer, Integer> stats = helper.getStatsForCharacter(charId);
        strength = stats.get(PH.STRENGTH_ID);
        agility = stats.get(PH.AGILITY_ID);
        comradery = stats.get(PH.COMRADERY_ID);
        inventory = helper.getCharacterInventory(charId);
        atNode = helper.getCurrentNode(charId);
    }
    
}
