package com.rudie.severin.textadventure.InformationHolders;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

/**
 * Created by erikrudie on 7/28/16.
 */

/*
This class holds all information relevent to Choices, and is used to build the database
 */
public class ChoiceData {

    private final String text;
    private final int nodeId;
    private final int toNode;
    private final int connectedSuccessPopup;
    private final int connectedFailPopup;
    private final int itemRequired;
    private final int itemImproves;
    private final int testType;
    private final int difficulty;
    private final int[] ints;

    public ChoiceData(String text, int nodeId, int toNode, int connectedSuccess, int connectedFail,
                      int itemRequired, int itemImproves, int testType, int difficulty) {
        this.text = text;
        this.nodeId = nodeId;
        this.toNode = toNode;
        this.connectedSuccessPopup = connectedSuccess;
        this.connectedFailPopup = connectedFail;
        this.itemRequired = itemRequired;
        this.itemImproves = itemImproves;
        this.testType = testType;
        this.difficulty = difficulty;
        this.ints = new int[] {nodeId, toNode, connectedSuccess, connectedFail, itemRequired, itemImproves, testType,
                difficulty};
    }

    public String getText() {
        return text;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getToNode() {
        return toNode;
    }

    public int getConnectedSuccessPopup() {
        return connectedSuccessPopup;
    }

    public int getConnectedFailPopup() {
        return connectedFailPopup;
    }

    public int getItemRequired() {
        return itemRequired;
    }

    public int getItemImproves() {
        return itemImproves;
    }

    public int getTestType() {
        return testType;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int[] getInts() {
        return ints;
    }
}
