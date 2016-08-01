package com.rudie.severin.textadventure.UtilityClasses;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erikrudie on 7/31/16.
 */
public final class CurrentInventoryHolder {

    private CurrentInventoryHolder() {
    }

    private static List<ItemData> currentInventory;
    private static List<Integer> currentItemTypes;
    private static boolean adapterGetNewInventory;

    public static void refreshInventoryFromDb (int charId, Context context) {
        DBInterfacer helper = DBInterfacer.getInstance(context);
        currentInventory = helper.getCharacterInventory(charId, context);
        CurrentInventoryHolder.adapterGetNewInventory = true;

        if (currentItemTypes != null) {
            currentItemTypes.clear();
        } else {
            currentItemTypes = new ArrayList<>();
        }
        for (int i = 0; i < currentInventory.size(); i++) {
            currentItemTypes.add(currentInventory.get(i).getItemTypeId());
        }
    }

    public static List<ItemData> getCurrentInventory() {
        return currentInventory;
    }

    public static boolean getAdapterNewInventoryAndSetFalse() {
        if (adapterGetNewInventory) {
            adapterGetNewInventory = false;
            return true;
        } else {
            return false;
        }
    }

    public static List<Integer> getCurrentItemTypes() {
        return currentItemTypes;
    }
}
