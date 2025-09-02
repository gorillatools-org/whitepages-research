package com.google.android.gms.internal.play_billing;

final class zzfs extends zzfr {
    zzfs() {
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i, byte[] bArr, int i2, int i3) {
        while (r10 < i3 && bArr[r10] >= 0) {
            i2 = r10 + 1;
        }
        if (r10 >= i3) {
            return 0;
        }
        while (r10 < i3) {
            int i4 = r10 + 1;
            byte b = bArr[r10];
            if (b < 0) {
                if (b < -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        r10 += 2;
                        if (bArr[i4] > -65) {
                        }
                    }
                } else if (b < -16) {
                    if (i4 >= i3 - 1) {
                        return zzfu.zza(bArr, i4, i3);
                    }
                    int i5 = r10 + 2;
                    byte b2 = bArr[i4];
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        r10 += 3;
                        if (bArr[i5] > -65) {
                        }
                    }
                } else if (i4 >= i3 - 2) {
                    return zzfu.zza(bArr, i4, i3);
                } else {
                    int i6 = r10 + 2;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && (((b << 28) + (b3 + 112)) >> 30) == 0) {
                        int i7 = r10 + 3;
                        if (bArr[i6] <= -65) {
                            r10 += 4;
                            if (bArr[i7] > -65) {
                            }
                        }
                    }
                }
                return -1;
            }
            r10 = i4;
        }
        return 0;
    }
}
