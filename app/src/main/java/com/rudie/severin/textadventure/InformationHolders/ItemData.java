package com.rudie.severin.textadventure.InformationHolders;

/**
 * Created by erikrudie on 7/28/16.
 */

/*
This class holds all information relevent to Choices, and is used to build the database
 */
public class ItemData {

//    private final int itemId;
    private final String itemName;
    private final int itemPower;
    private final int itemTypeId;
    private final String itemTypeName;
    private final int itemOwnerId;
    private final String itemStatName;
    private final String acquireText;


    public ItemData(String name, int power, int typeId, int ownerId, String acquireText) {
//        this.itemId = id;
        this.itemName = name;
        this.itemPower = power;
        this.itemTypeId = typeId;
        this.itemOwnerId = ownerId;
        this.acquireText = acquireText;
        // TODO: set stat and type name based on type id
        // TODO: remember that type name has its own db
        this.itemTypeName = "TEMPORARY TYPE";
        this.itemStatName = "TEMPORARY STAT";
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
}