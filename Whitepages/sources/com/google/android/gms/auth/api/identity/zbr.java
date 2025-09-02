package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

public final class zbr implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
        L_0x000e:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x005a
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0055;
                case 2: goto L_0x0050;
                case 3: goto L_0x004b;
                case 4: goto L_0x0046;
                case 5: goto L_0x003c;
                case 6: goto L_0x0037;
                case 7: goto L_0x0032;
                case 8: goto L_0x002d;
                case 9: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r1)
            goto L_0x000e
        L_0x0023:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredential> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredential.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r11 = r1
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredential r11 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredential) r11
            goto L_0x000e
        L_0x002d:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0032:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0037:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x003c:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r7 = r1
            android.net.Uri r7 = (android.net.Uri) r7
            goto L_0x000e
        L_0x0046:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x004b:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0050:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0055:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x005a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r0)
            com.google.android.gms.auth.api.identity.SignInCredential r13 = new com.google.android.gms.auth.api.identity.SignInCredential
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zbr.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SignInCredential[i];
    }
}
