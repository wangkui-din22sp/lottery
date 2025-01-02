package com.wang;

public class CountStraightEvenNumber {
    byte countStraightEvenNumber = 1;
    long startingEntryNumber = 0;
    long endingEntryNumber = 0;

    public CountStraightEvenNumber(byte countStraightEvenNumber, long startingEntryNumber, long endingEntryNumber) {
        this.countStraightEvenNumber = countStraightEvenNumber;
        this.startingEntryNumber = startingEntryNumber;
        this.endingEntryNumber = endingEntryNumber;
    }

    public byte getCountStraightEvenNumber() {
        return countStraightEvenNumber;
    }

    public long getStartingEntryNumber() {
        return startingEntryNumber;
    }

    public long getEndingEntryNumber() {
        return endingEntryNumber;
    }

}
