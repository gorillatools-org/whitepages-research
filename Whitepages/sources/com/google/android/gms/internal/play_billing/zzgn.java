package com.google.android.gms.internal.play_billing;

final class zzgn implements zzcw {
    static final zzcw zza = new zzgn();

    private zzgn() {
    }

    public final boolean zza(int i) {
        zzgo zzgo = zzgo.BROADCAST_ACTION_UNSPECIFIED;
        return (i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : zzgo.ALTERNATIVE_BILLING_ACTION : zzgo.LOCAL_PURCHASES_UPDATED_ACTION : zzgo.PURCHASES_UPDATED_ACTION : zzgo.BROADCAST_ACTION_UNSPECIFIED) != null;
    }
}
