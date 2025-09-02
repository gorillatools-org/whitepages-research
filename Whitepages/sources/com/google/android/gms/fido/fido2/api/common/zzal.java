package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzal implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x005e
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0059;
                case 2: goto L_0x0054;
                case 3: goto L_0x004f;
                case 4: goto L_0x0045;
                case 5: goto L_0x003b;
                case 6: goto L_0x0031;
                case 7: goto L_0x0027;
                case 8: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r12, r1)
            goto L_0x000d
        L_0x0022:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x0027:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r9 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs r9 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs) r9
            goto L_0x000d
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r8 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse r8 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse) r8
            goto L_0x000d
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r7 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse r7 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse) r7
            goto L_0x000d
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r6 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse r6 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse) r6
            goto L_0x000d
        L_0x004f:
            byte[] r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r12, r1)
            goto L_0x000d
        L_0x0054:
            java.lang.String r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x0059:
            java.lang.String r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x005e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r12, r0)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredential r12 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredential
            r2 = r12
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzal.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredential[i];
    }
}
