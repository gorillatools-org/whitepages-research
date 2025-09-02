package com.microsoft.codepush.react;

public enum CodePushUpdateState {
    RUNNING(0),
    PENDING(1),
    LATEST(2);
    
    private final int value;

    private CodePushUpdateState(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
