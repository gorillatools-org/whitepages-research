package com.facebook.yoga;

public enum YogaPositionType {
    STATIC(0),
    RELATIVE(1),
    ABSOLUTE(2);
    
    private final int mIntValue;

    private YogaPositionType(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
