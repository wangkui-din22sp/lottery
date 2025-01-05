package com.wang;

public class CountStraightKills {

    int countStraightKills = 1;
    int startingEntryNumber = 0;
    int endingEntryNumber = 0;

    public CountStraightKills(int countStraightKills, int startingEntryNumber, int endingEntryNumber) {
        this.countStraightKills = countStraightKills;
        this.startingEntryNumber = startingEntryNumber;
        this.endingEntryNumber = endingEntryNumber;
    }

    public int getCountStraightKills() {
        return countStraightKills;
    }

    public int getStartingEntryNumber() {
        return startingEntryNumber;
    }

    public int getEndingEntryNumber() {
        return endingEntryNumber;
    }

}
