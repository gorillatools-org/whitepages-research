package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcelable;

public final class zzak implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
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
            r12 = r11
            r13 = r12
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0079
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x006f;
                case 3: goto L_0x0065;
                case 4: goto L_0x0060;
                case 5: goto L_0x0059;
                case 6: goto L_0x0054;
                case 7: goto L_0x004d;
                case 8: goto L_0x0043;
                case 9: goto L_0x003e;
                case 10: goto L_0x0034;
                case 11: goto L_0x002f;
                case 12: goto L_0x0025;
                default: goto L_0x0021;
            }
        L_0x0021:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x0010
        L_0x0025:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r13 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions r13 = (com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions) r13
            goto L_0x0010
        L_0x002f:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0034:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.TokenBinding> r2 = com.google.android.gms.fido.fido2.api.common.TokenBinding.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r11 = r1
            com.google.android.gms.fido.fido2.api.common.TokenBinding r11 = (com.google.android.gms.fido.fido2.api.common.TokenBinding) r11
            goto L_0x0010
        L_0x003e:
            java.lang.Integer r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r15, r1)
            goto L_0x0010
        L_0x0043:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria> r2 = com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r9 = r1
            com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria r9 = (com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria) r9
            goto L_0x0010
        L_0x004d:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.CREATOR
            java.util.ArrayList r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r15, r1, r2)
            goto L_0x0010
        L_0x0054:
            java.lang.Double r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r15, r1)
            goto L_0x0010
        L_0x0059:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters.CREATOR
            java.util.ArrayList r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r15, r1, r2)
            goto L_0x0010
        L_0x0060:
            byte[] r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r15, r1)
            goto L_0x0010
        L_0x0065:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r4 = r1
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity r4 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity) r4
            goto L_0x0010
        L_0x006f:
            android.os.Parcelable$Creator<com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity> r2 = com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r3 = r1
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity r3 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity) r3
            goto L_0x0010
        L_0x0079:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions r15 = new com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions
            r2 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.zzak.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialCreationOptions[i];
    }
}
