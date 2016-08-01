package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/28/16.
 */

/*
This class holds all information relevent to Choices, and is used to build the database
 */
public class ItemData {

    private final int itemId;
    private final String itemName;
    private final int itemPower;
    private final int itemTypeId;
    private final int itemOwnerId;

    public ItemData(int id, String name, int power, int typeId, int ownerId) {
        this.itemId = id;
        this.itemName = name;
        this.itemPower = power;
        this.itemTypeId = typeId;
        this.itemOwnerId = ownerId;
    }

    public int getItemId() {
        return itemId;
    }

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
}
