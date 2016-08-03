package com.rudie.severin.textadventure.InformationHolders;

import android.content.Context;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by erikrudie on 7/31/16.
 */
public final class CurrentInventoryAndStats {

    private CurrentInventoryAndStats() {
    }

    private static List<ItemData> currentInventory;
    private static List<Integer> currentItemTypes;
    private static boolean adapterGetNewInventory;
    private static HashMap<Integer, Integer> currentStats;

    // Sets currentInventory, currentItemTypes, and currentStats for the selected character
    public static void refreshFromDb(int charId, Context context) {
        DBInterfacer helper = DBInterfacer.getInstance(context);
        currentInventory = helper.getCharacterInventory(charId);
        CurrentInventoryAndStats.adapterGetNewInventory = true;

        if (currentItemTypes != null) {
            currentItemTypes.clear();
        } else {
            currentItemTypes = new ArrayList<>();
        }
        for (int i = 0; i < currentInventory.size(); i++) {
            currentItemTypes.add(currentInventory.get(i).getItemTypeId());
        }
        currentStats = helper.getStatsForCharacter(charId);
        System.out.println("");
    }

    // BEGIN getters and setters
    public static boolean getAdapterNewInventoryAndSetFalse() {
        if (adapterGetNewInventory) {
            adapterGetNewInventory = false;
            return true;
        } else {
            return false;
        }
    }

    public static List<ItemData> getCurrentInventory() {
        return currentInventory;
    }

    public static List<Integer> getCurrentItemTypes() {
        return currentItemTypes;
    }

    public static HashMap<Integer, Integer> getCurrentStats() {
        return currentStats;
    }

    public static int getBestValueForTest(int testType) {
        int highestValue = 0;
        for (int i = 0; i < currentInventory.size(); i++) {
            if (currentInventory.get(i).getItemTypeId() == testType
                    || currentInventory.get(i).getItemPower() > highestValue) {
                highestValue = currentInventory.get(i).getItemPower();
            }
        }
        return highestValue;
    }

    // returns a template item not owned by the current character.  The popup fragment then displays
    // the items acquisition text and adds it to the current inventory
    public static ItemData getRandomItem() {
        List<String> ownedItemNames = new ArrayList<>();
        for (ItemData item : currentInventory) {
            ownedItemNames.add(item.getItemName());
        }
        while(true) {
            int index = (int) (Math.random() * PH.itemTemplates.length);
            if (!ownedItemNames.contains(PH.itemTemplates[index].getItemName())) {
                return PH.itemTemplates[index];
            }
        }

    }

    // END getters and setters
}
