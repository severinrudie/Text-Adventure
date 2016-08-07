package com.rudie.severin.textadventure.InformationHolders;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

/**
 * Created by erikrudie on 7/28/16.
 */

/*
This class holds all information relevent to Choices, and is used to build the database
 */
public class ItemData {

//    private final int itemId;
    private String itemName;
    private final int itemPower;
    private final int itemTypeId;
    private final String itemTypeName;
    private final int itemOwnerId;
    private final int itemStatId;
    private final String itemStatName;
    private String acquireText;


    public ItemData(String name, int power, int typeId, int statId, int ownerId, String acquireText) {
//        this.itemId = id;
//        this.itemName = DBInterfacer.cleanTextForDb(name);
        this.itemName = name;
        this.itemPower = power;
        this.itemTypeId = typeId;
        this.itemOwnerId = ownerId;
//        this.acquireText = DBInterfacer.cleanTextForDb(acquireText);
        this.acquireText = acquireText;
        // TODO: set type name based on type id
        // TODO: remember that type name has its own db
        this.itemTypeName = "TEMPORARY TYPE";
        this.itemStatId = statId;
        if (statId == PH.STRENGTH_ID) {
            this.itemStatName = "Strength";
        } else if (statId == PH.AGILITY_ID) {
            this.itemStatName = "Agility";
        } else if (statId == PH.COMRADERY_ID) {
            this.itemStatName = "Comradery";
        } else {
            this.itemStatName = PH.NULL;
        }
    }

//    public int getItemId() {
//        return itemId;
//    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPower() {
        return itemPower;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public int getItemOwnerId() {
        return itemOwnerId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public String getItemStatName() {
        return itemStatName;
    }

    public String getAcquireText() {
        return acquireText;
    }

    public int getItemStatId() {
        return itemStatId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setAcquireText(String acquireText) {
        this.acquireText = acquireText;
    }
}
