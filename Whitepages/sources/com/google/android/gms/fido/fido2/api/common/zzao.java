package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzao implements Parcelable.Creator {
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
            if (r1 >= r0) goto L_0x005c
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x0057;
                case 3: goto L_0x0052;
                case 4: goto L_0x004d;
                case 5: goto L_0x0046;
                case 6: goto L_0x0041;
                case 7: goto L_0x0037;
                case 8: goto L_0x0032;
                case 9: goto L_0x0028;
                case 10: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r1)
            goto L_0x000e
        L_0x0023:
            java.lang.Long r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLongObject(r13, r1)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r10 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r10 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r10
            goto L_0x000e
        L_0x0032:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0037:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBinding> r2 = com.google.android.gms.fido.fido2.api.common.TokenBinding.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r8 = r1
            com.google.android.gms.fido.fido2.api.common.TokenBinding r8 = (com.google.android.gms.fido.fido2.api.common.TokenBinding) r8
            goto L_0x000e
        L_0x0041:
            java.lang.Integer r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r13, r1)
            goto L_0x000e
        L_0x0046:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r13, r1, r2)
            goto L_0x000e
        L_0x004d:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x0052:
            java.lang.Double r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r13, r1)
            goto L_0x000e
        L_0x0057:
            byte[] r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r13, r1)
            goto L_0x000e
        L_0x005c:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r0)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions r13 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzao.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialRequestOptions[i];
    }
}
