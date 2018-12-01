package com.pronunciation_match.pronunciationmatch;

public enum Tone {
    FIRST, SECOND, THIRD, FOURTH, NEUTRAL;

    public static Tone getTone(char num) {
        switch (num) {
            case '1':
                return FIRST;
            case '2':
                return SECOND;
            case '3':
                return THIRD;
            case '4':
                return FOURTH;
            default:
                return NEUTRAL;
        }
    }
}
