package com.google.android.gms.internal.fido;

import java.util.Arrays;

public final class zzbj extends zzbk {
    private final String zza;

    zzbj(String str) {
        this.zza = str;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzbk zzbk = (zzbk) obj;
        zzbk.zza();
        String str = this.zza;
        int length = str.length();
        String str2 = ((zzbj) zzbk).zza;
        if (length != str2.length()) {
            return str.length() - str2.length();
        }
        return str.compareTo(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zzbj.class == obj.getClass()) {
            return this.zza.equals(((zzbj) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{3, this.zza});
    }

    public final String toString() {
        String str = this.zza;
        return "\"" + str + "\"";
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return 3;
    }
}
