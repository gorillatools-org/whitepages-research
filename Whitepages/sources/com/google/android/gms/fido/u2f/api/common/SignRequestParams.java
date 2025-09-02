package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SafeParcelable.Class(creator = "SignRequestParamsCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public class SignRequestParams extends RequestParams {
    public static final Parcelable.Creator<SignRequestParams> CREATOR = new zzk();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    @SafeParcelable.Field(getter = "getRequestId", id = 2)
    private final Integer zza;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    private final Double zzb;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    private final Uri zzc;
    @SafeParcelable.Field(getter = "getDefaultSignChallenge", id = 5)
    private final byte[] zzd;
    @SafeParcelable.Field(getter = "getRegisteredKeys", id = 6)
    private final List zze;
    @SafeParcelable.Field(getter = "getChannelIdValue", id = 7)
    private final ChannelIdValue zzf;
    @SafeParcelable.Field(getter = "getDisplayHint", id = 8)
    private final String zzg;
    private final Set zzh;

    public static final class Builder {
        Integer zza;
        Double zzb;
        Uri zzc;
        byte[] zzd;
        List zze;
        ChannelIdValue zzf;
        String zzg;

        public SignRequestParams build() {
            return new SignRequestParams(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public Builder setAppId(Uri uri) {
            this.zzc = uri;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.zzf = channelIdValue;
            return this;
        }

        public Builder setDefaultSignChallenge(byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        public Builder setDisplayHint(String str) {
            this.zzg = str;
            return this;
        }

        public Builder setRegisteredKeys(List<RegisteredKey> list) {
            this.zze = list;
            return this;
        }

        public Builder setRequestId(Integer num) {
            this.zza = num;
            return this;
        }

        public Builder setTimeoutSeconds(Double d) {
            this.zzb = d;
            return this;
        }
    }

    @SafeParcelable.Constructor
    SignRequestParams(@SafeParcelable.Param(id = 2) Integer num, @SafeParcelable.Param(id = 3) Double d, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) byte[] bArr, @SafeParcelable.Param(id = 6) List list, @SafeParcelable.Param(id = 7) ChannelIdValue channelIdValue, @SafeParcelable.Param(id = 8) String str) {
        this.zza = num;
        this.zzb = d;
        this.zzc = uri;
        this.zzd = bArr;
        boolean z = false;
        Preconditions.checkArgument(list != null && !list.isEmpty(), "registeredKeys must not be null or empty");
        this.zze = list;
        this.zzf = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it.next();
            Preconditions.checkArgument((registeredKey.getAppId() == null && uri == null) ? false : true, "registered key has null appId and no request appId is provided");
            registeredKey.getChallengeValue();
            Preconditions.checkArgument(true, "register request has null challenge and no default challenge isprovided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        this.zzh = hashSet;
        Preconditions.checkArgument((str == null || str.length() <= 80) ? true : z, "Display Hint cannot be longer than 80 characters");
        this.zzg = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignRequestParams)) {
            return false;
        }
        SignRequestParams signRequestParams = (SignRequestParams) obj;
        return Objects.equal(this.zza, signRequestParams.zza) && Objects.equal(this.zzb, signRequestParams.zzb) && Objects.equal(this.zzc, signRequestParams.zzc) && Arrays.equals(this.zzd, signRequestParams.zzd) && this.zze.containsAll(signRequestParams.zze) && signRequestParams.zze.containsAll(this.zze) && Objects.equal(this.zzf, signRequestParams.zzf) && Objects.equal(this.zzg, signRequestParams.zzg);
    }

    public Set<Uri> getAllAppIds() {
        return this.zzh;
    }

    public Uri getAppId() {
        return this.zzc;
    }

    public ChannelIdValue getChannelIdValue() {
        return this.zzf;
    }

    public byte[] getDefaultSignChallenge() {
        return this.zzd;
    }

    public String getDisplayHint() {
        return this.zzg;
    }

    public List<RegisteredKey> getRegisteredKeys() {
        return this.zze;
    }

    public Integer getRequestId() {
        return this.zza;
    }

    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzc, this.zzb, this.zze, this.zzf, this.zzg, Integer.valueOf(Arrays.hashCode(this.zzd)));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 2, getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getAppId(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 5, getDefaultSignChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getChannelIdValue(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
