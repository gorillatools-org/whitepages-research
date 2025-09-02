package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zax implements zabz {
    final /* synthetic */ zaaa zaa;

    /* synthetic */ zax(zaaa zaaa, zaw zaw) {
        this.zaa = zaaa;
    }

    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaj = connectionResult;
            zaaa.zap(this.zaa);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zab(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            zaaa.zao(this.zaa, bundle);
            this.zaa.zaj = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(this.zaa);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zac(int i, boolean z) {
        this.zaa.zam.lock();
        try {
            zaaa zaaa = this.zaa;
            if (!zaaa.zal && zaaa.zak != null) {
                if (zaaa.zak.isSuccess()) {
                    this.zaa.zal = true;
                    this.zaa.zae.onConnectionSuspended(i);
                }
            }
            this.zaa.zal = false;
            zaaa.zan(this.zaa, i, z);
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
