package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcelable;

public final class zzk implements Parcelable.Creator {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r1 = 0
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
        L_0x000c:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x0050
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x003c;
                case 5: goto L_0x0037;
                case 6: goto L_0x0030;
                case 7: goto L_0x0026;
                case 8: goto L_0x0021;
                default: goto L_0x001d;
            }
        L_0x001d:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x000c
        L_0x0021:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r11, r1)
            goto L_0x000c
        L_0x0026:
            android.os.Parcelable$Creator<com.google.android.gms.fido.u2f.api.common.ChannelIdValue> r2 = com.google.android.gms.fido.u2f.api.common.ChannelIdValue.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r8 = r1
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r8 = (com.google.android.gms.fido.u2f.api.common.ChannelIdValue) r8
            goto L_0x000c
        L_0x0030:
            android.os.Parcelable$Creator<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r2 = com.google.android.gms.fido.u2f.api.common.RegisteredKey.CREATOR
            java.util.ArrayList r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r11, r1, r2)
            goto L_0x000c
        L_0x0037:
            byte[] r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r11, r1)
            goto L_0x000c
        L_0x003c:
            android.os.Parcelable$Creator r2 = android.net.Uri.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r5 = r1
            android.net.Uri r5 = (android.net.Uri) r5
            goto L_0x000c
        L_0x0046:
            java.lang.Double r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDoubleObject(r11, r1)
            goto L_0x000c
        L_0x004b:
            java.lang.Integer r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readIntegerObject(r11, r1)
            goto L_0x000c
        L_0x0050:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r0)
            com.google.android.gms.fido.u2f.api.common.SignRequestParams r11 = new com.google.android.gms.fido.u2f.api.common.SignRequestParams
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.zzk.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SignRequestParams[i];
    }
}
