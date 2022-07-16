package org.example.intuittwitterapi.model;

public enum Sentiment {
    STRONG_NEGATIVE,
    NEGATIVE,
    NEUTRAL,
    POSITIVE,
    STRONG_POSITIVE;

    public static Sentiment fromInteger(int x) {
        switch(x) {
            case 0:
                return STRONG_NEGATIVE;
            case 1:
                return NEGATIVE;
            case 2:
                return NEUTRAL;
            case 3:
                return POSITIVE;
            case 4:
                return STRONG_POSITIVE;
        }
        return null;
    }

    public static Sentiment fromFloat(float x) {
        if (x < 1){
            return STRONG_NEGATIVE;
        } else if (x < 2){
            return NEGATIVE;
        } else if (x < 3){
            return NEUTRAL;
        } else if (x < 4) {
            return POSITIVE;
        } else if (x < 5) {
            return STRONG_POSITIVE;
        } else {
            return null;
        }
    }
}
