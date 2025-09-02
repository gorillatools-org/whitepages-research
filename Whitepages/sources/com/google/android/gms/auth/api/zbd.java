package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

@Deprecated
public final class zbd implements Api.ApiOptions.Optional {
    public static final zbd zba = new zbd(new zbc());
    /* access modifiers changed from: private */
    public final String zbb = null;
    /* access modifiers changed from: private */
    public final boolean zbc;
    /* access modifiers changed from: private */
    public final String zbd;

    public zbd(zbc zbc2) {
        this.zbc = zbc2.zba.booleanValue();
        this.zbd = zbc2.zbb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zbd)) {
            return false;
        }
        zbd zbd2 = (zbd) obj;
        String str = zbd2.zbb;
        return Objects.equal((Object) null, (Object) null) && this.zbc == zbd2.zbc && Objects.equal(this.zbd, zbd2.zbd);
    }

    public final int hashCode() {
        return Objects.hashCode(null, Boolean.valueOf(this.zbc), this.zbd);
    }

    public final Bundle zba() {
        Bundle bundle = new Bundle();
        bundle.putString("consumer_package", (String) null);
        bundle.putBoolean("force_save_dialog", this.zbc);
        bundle.putString("log_session_id", this.zbd);
        return bundle;
    }
}
