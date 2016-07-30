package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/28/16.
 */
public class ChoiceData {
//    public void insertChoiceDetails(String text, int nodeId, int connectedNode, int itemRequired,
//                                    int itemImproves, int testType, int difficulty) {
    private final String text;
    private final int nodeId;
    private final int connectedNode;
    private final int itemRequired;
    private final int itemImproves;
    private final int testType;
    private final int difficulty;
    private final int[] ints;

    public ChoiceData(String text, int nodeId, int connectedNode, int itemRequired,
                      int itemImproves, int testType, int difficulty) {
        this.text = text;
        this.nodeId = nodeId;
        this.connectedNode = connectedNode;
        this.itemRequired = itemRequired;
        this.itemImproves = itemImproves;
        this.testType = testType;
        this.difficulty = difficulty;
        this.ints = new int[] {nodeId, connectedNode, itemRequired, itemImproves, testType,
                difficulty};
    }

    public String getText() {
        return text;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getConnectedNode() {
        return connectedNode;
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
