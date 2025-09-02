package com.google.android.gms.internal.auth;

import java.io.Serializable;
import java.util.Arrays;

final class zzdn implements Serializable, zzdj {
    final Object zza;

    zzdn(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdn)) {
            return false;
        }
        Object obj2 = this.zza;
        Object obj3 = ((zzdn) obj).zza;
        if (obj2 == obj3 || obj2.equals(obj3)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "Suppliers.ofInstance(" + obj + ")";
    }

    public final Object zza() {
        return this.zza;
    }
}
