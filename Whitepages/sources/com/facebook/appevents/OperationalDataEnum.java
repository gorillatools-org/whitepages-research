package com.facebook.appevents;

public enum OperationalDataEnum {
    IAPParameters("iap_parameters");
    
    private final String value;

    private OperationalDataEnum(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
