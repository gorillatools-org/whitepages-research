package com.google.android.gms.measurement.internal;

enum zzan {
    UNSET('0'),
    REMOTE_DEFAULT('1'),
    REMOTE_DELEGATION('2'),
    MANIFEST('3'),
    INITIALIZATION('4'),
    API('5'),
    CHILD_ACCOUNT('6'),
    TCF('7'),
    REMOTE_ENFORCED_DEFAULT('8'),
    FAILSAFE('9');
    
    /* access modifiers changed from: private */
    public final char zzl;

    private zzan(char c) {
        this.zzl = c;
    }

    public static zzan zzb(char c) {
        for (zzan zzan : values()) {
            if (zzan.zzl == c) {
                return zzan;
            }
        }
        return UNSET;
    }
}
