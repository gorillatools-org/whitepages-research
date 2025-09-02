package com.google.android.gms.internal.play_billing;

enum zza {
    RESPONSE_CODE_UNSPECIFIED(-999),
    SERVICE_TIMEOUT(-3),
    FEATURE_NOT_SUPPORTED(-2),
    SERVICE_DISCONNECTED(-1),
    OK(0),
    USER_CANCELED(1),
    SERVICE_UNAVAILABLE(2),
    BILLING_UNAVAILABLE(3),
    ITEM_UNAVAILABLE(4),
    DEVELOPER_ERROR(5),
    ERROR(6),
    ITEM_ALREADY_OWNED(7),
    ITEM_NOT_OWNED(8),
    EXPIRED_OFFER_TOKEN(11),
    NETWORK_ERROR(12);
    
    private static final zzal zzp = null;
    private final int zzr;

    static {
        zzak zzak = new zzak();
        for (zza zza : values()) {
            zzak.zza(Integer.valueOf(zza.zzr), zza);
        }
        zzp = zzak.zzb();
    }

    private zza(int i) {
        this.zzr = i;
    }

    static zza zza(int i) {
        zzal zzal = zzp;
        Integer valueOf = Integer.valueOf(i);
        if (!zzal.containsKey(valueOf)) {
            return RESPONSE_CODE_UNSPECIFIED;
        }
        return (zza) zzal.get(valueOf);
    }
}
