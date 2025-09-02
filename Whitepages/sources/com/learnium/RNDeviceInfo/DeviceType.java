package com.learnium.RNDeviceInfo;

import com.salesforce.marketingcloud.messages.iam.j;

public enum DeviceType {
    HANDSET("Handset"),
    TABLET("Tablet"),
    TV("Tv"),
    UNKNOWN(j.h);
    
    private final String value;

    private DeviceType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
