package com.facebook.yoga;

public enum YogaGutter {
    COLUMN(0),
    ROW(1),
    ALL(2);
    
    private final int mIntValue;

    private YogaGutter(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
