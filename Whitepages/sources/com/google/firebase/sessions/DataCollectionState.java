package com.google.firebase.sessions;

import com.google.firebase.encoders.json.NumberedEnum;

public enum DataCollectionState implements NumberedEnum {
    COLLECTION_UNKNOWN(0),
    COLLECTION_SDK_NOT_INSTALLED(1),
    COLLECTION_ENABLED(2),
    COLLECTION_DISABLED(3),
    COLLECTION_DISABLED_REMOTE(4),
    COLLECTION_SAMPLED(5);
    
    private final int number;

    private DataCollectionState(int i) {
        this.number = i;
    }

    public int getNumber() {
        return this.number;
    }
}
