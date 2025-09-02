package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "SignResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class SignResponseData extends ResponseData {
    public static final Parcelable.Creator<SignResponseData> CREATOR = new zzl();
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_KEY_HANDLE = "keyHandle";
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_SIGNATURE_DATA = "signatureData";
    @SafeParcelable.Field(getter = "getKeyHandle", id = 2)
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getClientDataString", id = 3)
    private final String zzb;
    @SafeParcelable.Field(getter = "getSignatureData", id = 4)
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getApplication", id = 5)
    private final byte[] zzd;

    @Deprecated
    public SignResponseData(byte[] bArr, String str, byte[] bArr2) {
        this(bArr, str, bArr2, new byte[0]);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignResponseData)) {
            return false;
        }
        SignResponseData signResponseData = (SignResponseData) obj;
        if (!Arrays.equals(this.zza, signResponseData.zza) || !Objects.equal(this.zzb, signResponseData.zzb) || !Arrays.equals(this.zzc, signResponseData.zzc) || !Arrays.equals(this.zzd, signResponseData.zzd)) {
            return false;
        }
        return true;
    }

    public String getClientDataString() {
        return this.zzb;
    }

    public byte[] getKeyHandle() {
        return this.zza;
    }

    public byte[] getSignatureData() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zza)), this.zzb, Integer.valueOf(Arrays.hashCode(this.zzc)), Integer.valueOf(Arrays.hashCode(this.zzd)));
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.zza, 11));
            jSONObject.put(JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(this.zzb.getBytes(), 11));
            jSONObject.put(JSON_RESPONSE_DATA_SIGNATURE_DATA, Base64.encodeToString(this.zzc, 11));
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        zzaj zza2 = zzak.zza(this);
        zzbf zzd2 = zzbf.zzd();
        byte[] bArr = this.zza;
        zza2.zzb(JSON_RESPONSE_DATA_KEY_HANDLE, zzd2.zze(bArr, 0, bArr.length));
        zza2.zzb("clientDataString", this.zzb);
        zzbf zzd3 = zzbf.zzd();
        byte[] bArr2 = this.zzc;
        zza2.zzb(JSON_RESPONSE_DATA_SIGNATURE_DATA, zzd3.zze(bArr2, 0, bArr2.length));
        zzbf zzd4 = zzbf.zzd();
        byte[] bArr3 = this.zzd;
        zza2.zzb("application", zzd4.zze(bArr3, 0, bArr3.length));
        return zza2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getKeyHandle(), false);
        SafeParcelWriter.writeString(parcel, 3, getClientDataString(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getSignatureData(), false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public SignResponseData(@SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) byte[] bArr2, @SafeParcelable.Param(id = 5) byte[] bArr3) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = (String) Preconditions.checkNotNull(str);
        this.zzc = (byte[]) Preconditions.checkNotNull(bArr2);
        this.zzd = (byte[]) Preconditions.checkNotNull(bArr3);
    }
}
