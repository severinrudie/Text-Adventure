package com.rudie.severin.textadventure.UtilityClasses;

import android.content.Context;

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
        currentInventory = helper.getCharacterInventory(charId, context);
        CurrentInventoryAndStats.adapterGetNewInventory = true;

        if (currentItemTypes != null) {
            currentItemTypes.clear();
        } else {
            currentItemTypes = new ArrayList<>();
        }
        for (int i = 0; i < currentInventory.size(); i++) {
            currentItemTypes.add(currentInventory.get(i).getItemTypeId());
        }
        currentStats = helper.getStatsForCharacter(charId, context);
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
    // END getters and setters
}
