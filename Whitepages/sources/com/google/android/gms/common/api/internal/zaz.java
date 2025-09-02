package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zaz implements zabz {
    final /* synthetic */ zaaa zaa;

    /* synthetic */ zaz(zaaa zaaa, zay zay) {
        this.zaa = zaaa;
    }

    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            this.zaa.zak = connectionResult;
            zaaa.zap(this.zaa);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zab(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            this.zaa.zak = ConnectionResult.RESULT_SUCCESS;
            zaaa.zap(this.zaa);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zac(int i, boolean z) {
        this.zaa.zam.lock();
        try {
            zaaa zaaa = this.zaa;
            if (zaaa.zal) {
                zaaa.zal = false;
                zaaa.zan(this.zaa, i, z);
            } else {
                zaaa.zal = true;
                this.zaa.zad.onConnectionSuspended(i);
            }
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
