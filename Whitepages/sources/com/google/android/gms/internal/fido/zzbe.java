package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;

class zzbe extends zzbf {
    final zzbb zzb;
    final Character zzc;

    zzbe(zzbb zzbb, Character ch) {
        this.zzb = zzbb;
        if (ch == null || !zzbb.zzb('=')) {
            this.zzc = ch;
            return;
        }
        throw new IllegalArgumentException(zzan.zza("Padding character %s was already in alphabet", ch));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzbe) {
            zzbe zzbe = (zzbe) obj;
            if (this.zzb.equals(zzbe.zzb)) {
                Character ch = this.zzc;
                Character ch2 = zzbe.zzc;
                if (ch == ch2) {
                    return true;
                }
                if (ch == null || !ch.equals(ch2)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = this.zzb.hashCode();
        Character ch = this.zzc;
        if (ch == null) {
            i = 0;
        } else {
            i = ch.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzam.zze(0, i2, bArr.length);
        while (i3 < i2) {
            zzc(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzb(int i) {
        zzbb zzbb = this.zzb;
        return zzbb.zzc * zzbh.zza(i, zzbb.zzd, RoundingMode.CEILING);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzam.zze(i, i + i2, bArr.length);
        int i3 = 0;
        zzam.zzc(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzbb zzbb = this.zzb;
            appendable.append(zzbb.zza(zzbb.zza & ((int) (j >>> (i5 - i3)))));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable.append('=');
                i3 += this.zzb.zzb;
            }
        }
    }

    zzbe(String str, String str2, Character ch) {
        this(new zzbb(str, str2.toCharArray()), ch);
    }
}
