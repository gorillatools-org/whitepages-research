package com.google.android.gms.internal.common;

import java.util.Arrays;

class zzae extends zzaf {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    zzae(int i) {
    }

    public final zzae zza(Object obj) {
        int i;
        obj.getClass();
        int length = this.zza.length;
        int i2 = this.zzb;
        int i3 = i2 + 1;
        if (i3 >= 0) {
            if (i3 <= length) {
                i = length;
            } else {
                i = (length >> 1) + length + 1;
                if (i < i3) {
                    int highestOneBit = Integer.highestOneBit(i2);
                    i = highestOneBit + highestOneBit;
                }
                if (i < 0) {
                    i = Integer.MAX_VALUE;
                }
            }
            if (i > length || this.zzc) {
                this.zza = Arrays.copyOf(this.zza, i);
                this.zzc = false;
            }
            Object[] objArr = this.zza;
            int i4 = this.zzb;
            this.zzb = i4 + 1;
            objArr[i4] = obj;
            return this;
        }
        throw new IllegalArgumentException("cannot store more than MAX_VALUE elements");
    }
}
