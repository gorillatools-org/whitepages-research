package com.google.android.gms.auth.api.identity;

import android.os.Parcelable;

public final class zbd implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
            r1 = 0
            r2 = 0
            r7 = r1
            r8 = r7
            r11 = r8
            r4 = r2
            r5 = r4
            r6 = r5
            r9 = r6
            r10 = r9
        L_0x000e:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x005f
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0055;
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x0041;
                case 5: goto L_0x003c;
                case 6: goto L_0x0032;
                case 7: goto L_0x0028;
                case 8: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r1)
            goto L_0x000e
        L_0x0023:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r1)
            goto L_0x000e
        L_0x0028:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeyJsonRequestOptions> r2 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r10 = r1
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeyJsonRequestOptions r10 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions) r10
            goto L_0x000e
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeysRequestOptions> r2 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r9 = r1
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasskeysRequestOptions r9 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions) r9
            goto L_0x000e
        L_0x003c:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000e
        L_0x0041:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r1)
            goto L_0x000e
        L_0x0046:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r13, r1)
            goto L_0x000e
        L_0x004b:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$GoogleIdTokenRequestOptions> r2 = com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r5 = r1
            com.google.android.gms.auth.api.identity.BeginSignInRequest$GoogleIdTokenRequestOptions r5 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions) r5
            goto L_0x000e
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.identity.BeginSignInRequest$PasswordRequestOptions> r2 = com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r4 = r1
            com.google.android.gms.auth.api.identity.BeginSignInRequest$PasswordRequestOptions r4 = (com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions) r4
            goto L_0x000e
        L_0x005f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r0)
            com.google.android.gms.auth.api.identity.BeginSignInRequest r13 = new com.google.android.gms.auth.api.identity.BeginSignInRequest
            r3 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.identity.zbd.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new BeginSignInRequest[i];
    }
}
