package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzs implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        Boolean bool = null;
        ArrayList<String> arrayList = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        int i4 = 100;
        boolean z4 = true;
        boolean z5 = true;
        long j8 = -2147483648L;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str6 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    str7 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 5:
                    str8 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 8:
                    str9 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 9:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 11:
                    j8 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    str10 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 15:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 16:
                    z5 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 19:
                    str11 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                    bool = SafeParcelReader.readBooleanObject(parcel2, readHeader);
                    break;
                case 22:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case ConnectionResult.API_DISABLED:
                    arrayList = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                    str12 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 25:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 26:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 27:
                    str13 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION:
                    j5 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 30:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 31:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 32:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 34:
                    j6 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 35:
                    str14 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 36:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 37:
                    j7 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 38:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzr(str5, str6, str7, str8, j, j2, str9, z4, z, j8, str10, j3, i, z5, z2, str11, bool, j4, (List) arrayList, str12, str, str2, str13, z3, j5, i4, str3, i2, j6, str14, str4, j7, i3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
