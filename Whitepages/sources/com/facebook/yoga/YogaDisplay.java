package com.facebook.yoga;

public enum YogaDisplay {
    FLEX(0),
    NONE(1),
    CONTENTS(2);
    
    private final int mIntValue;

    private YogaDisplay(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
