package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

final class zaar extends zac {
    private final WeakReference zaa;

    zaar(zaaw zaaw) {
        this.zaa = new WeakReference(zaaw);
    }

    public final void zab(zak zak) {
        zaaw zaaw = (zaaw) this.zaa.get();
        if (zaaw != null) {
            zaaw.zaa.zal(new zaaq(this, zaaw, zaaw, zak));
        }
    }
}
