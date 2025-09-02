package com.google.android.gms.internal.fido;

import java.io.IOException;

final class zzbc extends zzbe {
    final char[] zza = new char[512];

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzbc(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            com.google.android.gms.internal.fido.zzbb r4 = new com.google.android.gms.internal.fido.zzbb
            java.lang.String r5 = "base16()"
            java.lang.String r0 = "0123456789ABCDEF"
            char[] r0 = r0.toCharArray()
            r4.<init>(r5, r0)
            r5 = 0
            r3.<init>(r4, r5)
            r5 = 512(0x200, float:7.175E-43)
            char[] r5 = new char[r5]
            r3.zza = r5
            char[] r5 = r4.zzf
            int r5 = r5.length
            r0 = 16
            r1 = 0
            if (r5 != r0) goto L_0x0023
            r5 = 1
            goto L_0x0024
        L_0x0023:
            r5 = r1
        L_0x0024:
            com.google.android.gms.internal.fido.zzam.zzc(r5)
        L_0x0027:
            r5 = 256(0x100, float:3.59E-43)
            if (r1 >= r5) goto L_0x0044
            char[] r5 = r3.zza
            int r0 = r1 >>> 4
            char r0 = r4.zza(r0)
            r5[r1] = r0
            char[] r5 = r3.zza
            r0 = r1 | 256(0x100, float:3.59E-43)
            r2 = r1 & 15
            char r2 = r4.zza(r2)
            r5[r0] = r2
            int r1 = r1 + 1
            goto L_0x0027
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbc.<init>(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzam.zze(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i3] & 255;
            appendable.append(this.zza[b]);
            appendable.append(this.zza[b | 256]);
        }
    }
}
