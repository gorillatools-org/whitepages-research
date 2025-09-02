package com.google.android.gms.internal.play_billing;

public final class zzfe extends RuntimeException {
    public zzfe(zzec zzec) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzdc zza() {
        return new zzdc(getMessage());
    }
}
