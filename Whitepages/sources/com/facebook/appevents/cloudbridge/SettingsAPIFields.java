package com.facebook.appevents.cloudbridge;

import com.salesforce.marketingcloud.config.a;

public enum SettingsAPIFields {
    URL(a.i),
    ENABLED("is_enabled"),
    DATASETID("dataset_id"),
    ACCESSKEY("access_key");
    
    private final String rawValue;

    private SettingsAPIFields(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }
}
