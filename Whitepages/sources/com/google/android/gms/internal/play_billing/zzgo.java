package com.google.android.gms.internal.play_billing;

public enum zzgo implements zzcu {
    BROADCAST_ACTION_UNSPECIFIED(0),
    PURCHASES_UPDATED_ACTION(1),
    LOCAL_PURCHASES_UPDATED_ACTION(2),
    ALTERNATIVE_BILLING_ACTION(3);
    
    private static final zzcv zze = null;
    private final int zzg;

    static {
        zze = new zzgm();
    }

    private zzgo(int i) {
        this.zzg = i;
    }

    public final String toString() {
        return Integer.toString(this.zzg);
    }
}
