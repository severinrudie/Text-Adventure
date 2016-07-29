package com.rudie.severin.textadventure.UtilityClasses;

/**
 * Created by erikrudie on 7/28/16.
 */
public class ChoiceData {
//    public void insertChoiceDetails(String text, int nodeId, int connectedNode, int itemRequired,
//                                    int itemImproves, int testType, int difficulty) {
    private String text;
    private int nodeId;
    private int connectedNode;
    private int itemRequired;
    private int itemImproves;
    private int testType;
    private int difficulty;
    private int[] ints;

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

    public int[] getInts() {
        return ints;
    }
}
