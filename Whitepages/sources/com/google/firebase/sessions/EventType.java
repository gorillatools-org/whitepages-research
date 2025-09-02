package com.google.firebase.sessions;

import com.google.firebase.encoders.json.NumberedEnum;

public enum EventType implements NumberedEnum {
    EVENT_TYPE_UNKNOWN(0),
    SESSION_START(1);
    
    private final int number;

    private EventType(int i) {
        this.number = i;
    }

    public int getNumber() {
        return this.number;
    }
}
