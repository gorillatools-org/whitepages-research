package com.reactnativecommunity.netinfo.types;

import com.salesforce.marketingcloud.messages.iam.j;

public enum ConnectionType {
    BLUETOOTH("bluetooth"),
    CELLULAR("cellular"),
    ETHERNET("ethernet"),
    NONE("none"),
    UNKNOWN(j.h),
    WIFI("wifi"),
    WIMAX("wimax"),
    VPN("vpn");
    
    public final String label;

    private ConnectionType(String str) {
        this.label = str;
    }
}
