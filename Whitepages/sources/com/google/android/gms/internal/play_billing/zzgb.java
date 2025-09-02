package com.google.android.gms.internal.play_billing;

import com.google.android.gms.common.ConnectionResult;

final class zzgb implements zzcw {
    static final zzcw zza = new zzgb();

    private zzgb() {
    }

    public final boolean zza(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            default:
                switch (i) {
                    case 22:
                    case ConnectionResult.API_DISABLED:
                    case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                    case 25:
                    case 26:
                        return true;
                    default:
                        return false;
                }
        }
    }
}
