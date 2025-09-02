package com.salesforce.marketingcloud.util;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.LocationServices;

public class d {
    private static Boolean a;
    private static Boolean b;

    private d() {
    }

    public static boolean a() {
        if (b == null) {
            try {
                Class.forName("org.altbeacon.beacon.BeaconManager");
                b = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                b = Boolean.FALSE;
            }
        }
        return b.booleanValue();
    }

    public static boolean b() {
        if (a == null) {
            try {
                Api<Api.ApiOptions.NoOptions> api = LocationServices.API;
                a = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                a = Boolean.FALSE;
            }
        }
        return a.booleanValue();
    }
}
