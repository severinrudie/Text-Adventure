package com.rudie.severin.textadventure.InformationHolders;

import android.content.Context;

import com.rudie.severin.textadventure.DatabaseClasses.DBInterfacer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by erikrudie on 8/3/16.
 */
public class Character {

    private int charId, hp, strength, agility, comradery, atNode;
    private String firstName, nickName, lastName, fullName, bestSkillBlurb;
    private List<ItemData> inventory;

    public Character(int charId, Context context) {
        this.charId = charId;
        DBInterfacer helper = DBInterfacer.getInstance(context);
        String[] firstNickLast = helper.getCharacterFirstNickLast(charId, context);
        this.firstName = firstNickLast[0];
        this.nickName = firstNickLast[1];
        this.lastName = firstNickLast[2];
        HashMap<Integer, Integer> stats = helper.getStatsForCharacter(charId);
        this.strength = stats.get(PH.STRENGTH_ID);
        this.agility = stats.get(PH.AGILITY_ID);
        this.comradery = stats.get(PH.COMRADERY_ID);
        this.inventory = helper.getCharacterInventory(charId);
        this.atNode = helper.getCurrentNode(charId);
        this.hp = helper.getCharacterHp(charId);
        fullName = firstName + " " + nickName + " " + lastName;

        if (Math.max(Math.max(strength, agility), comradery) == strength) {
            bestSkillBlurb = "Strong as an ox";
        } else if (Math.max(Math.max(strength, agility), comradery) == agility) {
            bestSkillBlurb = "Swift and agile";
        } else {
            bestSkillBlurb = "Widely loved";
        }
    }

    public String getFullName() {
        return fullName;
    }

    public int getCharId() {
        return charId;
    }

    public int getHp() {
        return hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getComradery() {
        return comradery;
    }

    public int getAtNode() {
        return atNode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ItemData> getInventory() {
        return inventory;
    }

    public String getBestSkillBlurb() {
        return bestSkillBlurb;
    }
}
