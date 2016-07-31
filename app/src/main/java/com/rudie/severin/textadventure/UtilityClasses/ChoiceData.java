package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/28/16.
 */

/*
This class holds all information relevent to Choices, and is used to build the database
 */
public class ChoiceData {

    private final String text;
    private final int nodeId;
    private final int connectedSuccessNode;
    private final int connectedFailNode;
    private final int itemRequired;
    private final int itemImproves;
    private final int testType;
    private final int difficulty;
    private final int[] ints;

    public ChoiceData(String text, int nodeId, int connectedSuccess, int connectedFail,
                      int itemRequired, int itemImproves, int testType, int difficulty) {
        this.text = text;
        this.nodeId = nodeId;
        this.connectedSuccessNode = connectedSuccess;
        this.connectedFailNode = connectedFail;
        this.itemRequired = itemRequired;
        this.itemImproves = itemImproves;
        this.testType = testType;
        this.difficulty = difficulty;
        this.ints = new int[] {nodeId, connectedSuccess, connectedFail, itemRequired, itemImproves, testType,
                difficulty};
    }

    public String getText() {
        return text;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getConnectedSuccessNode() {
        return connectedSuccessNode;
    }

    public int getConnectedFailNode() {
        return connectedFailNode;
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
