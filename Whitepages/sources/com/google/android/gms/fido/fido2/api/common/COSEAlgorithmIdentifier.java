package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public class COSEAlgorithmIdentifier implements Parcelable {
    public static final Parcelable.Creator<COSEAlgorithmIdentifier> CREATOR = new zzp();
    private final Algorithm zza;

    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(int i) {
            super("Algorithm with COSE value " + i + " not supported");
        }
    }

    COSEAlgorithmIdentifier(Algorithm algorithm) {
        this.zza = (Algorithm) Preconditions.checkNotNull(algorithm);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.google.android.gms.fido.fido2.api.common.RSAAlgorithm} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.google.android.gms.fido.fido2.api.common.EC2Algorithm[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: com.google.android.gms.fido.fido2.api.common.EC2Algorithm} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: com.google.android.gms.fido.fido2.api.common.RSAAlgorithm} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.google.android.gms.fido.fido2.api.common.RSAAlgorithm} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: com.google.android.gms.fido.fido2.api.common.RSAAlgorithm} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier fromCoseValue(int r7) throws com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier.UnsupportedAlgorithmIdentifierException {
        /*
            com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier r0 = new com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier
            com.google.android.gms.fido.fido2.api.common.RSAAlgorithm r1 = com.google.android.gms.fido.fido2.api.common.RSAAlgorithm.LEGACY_RS1
            int r1 = r1.getAlgoValue()
            if (r7 != r1) goto L_0x000d
            com.google.android.gms.fido.fido2.api.common.RSAAlgorithm r7 = com.google.android.gms.fido.fido2.api.common.RSAAlgorithm.RS1
            goto L_0x0033
        L_0x000d:
            com.google.android.gms.fido.fido2.api.common.RSAAlgorithm[] r1 = com.google.android.gms.fido.fido2.api.common.RSAAlgorithm.values()
            int r2 = r1.length
            r3 = 0
            r4 = r3
        L_0x0014:
            if (r4 >= r2) goto L_0x0023
            r5 = r1[r4]
            int r6 = r5.getAlgoValue()
            if (r6 != r7) goto L_0x0020
            r7 = r5
            goto L_0x0033
        L_0x0020:
            int r4 = r4 + 1
            goto L_0x0014
        L_0x0023:
            com.google.android.gms.fido.fido2.api.common.EC2Algorithm[] r1 = com.google.android.gms.fido.fido2.api.common.EC2Algorithm.values()
            int r2 = r1.length
        L_0x0028:
            if (r3 >= r2) goto L_0x003a
            r4 = r1[r3]
            int r5 = r4.getAlgoValue()
            if (r5 != r7) goto L_0x0037
            r7 = r4
        L_0x0033:
            r0.<init>(r7)
            return r0
        L_0x0037:
            int r3 = r3 + 1
            goto L_0x0028
        L_0x003a:
            com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException r0 = new com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier.fromCoseValue(int):com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof COSEAlgorithmIdentifier) && this.zza.getAlgoValue() == ((COSEAlgorithmIdentifier) obj).zza.getAlgoValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public int toCoseValue() {
        return this.zza.getAlgoValue();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zza.getAlgoValue());
    }
}
