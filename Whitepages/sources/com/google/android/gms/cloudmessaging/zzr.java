package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

final class zzr extends zzs {
    zzr(int i, int i2, Bundle bundle) {
        super(i, i2, bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            zzd((Object) null);
        } else {
            zzc(new zzt(4, "Invalid response to one way request", (Throwable) null));
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return true;
    }
}
