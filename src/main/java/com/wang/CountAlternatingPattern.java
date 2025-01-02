package com.wang;

public class CountAlternatingPattern {

    byte countAlternatingPattern = 1;
    long startingEntryNumber = 0;
    long endingEntryNumber = 0;

    public CountAlternatingPattern(byte countAlternatingPattern, long startingEntryNumber, long endingEntryNumber) {
        this.countAlternatingPattern = countAlternatingPattern;
        this.startingEntryNumber = startingEntryNumber;
        this.endingEntryNumber = endingEntryNumber;
    }

    public byte getCountAlternatingPattern() {
        return countAlternatingPattern;
    }

    public long getStartingEntryNumber() {
        return startingEntryNumber;
    }

    public long getEndingEntryNumber() {
        return endingEntryNumber;
    }

}
