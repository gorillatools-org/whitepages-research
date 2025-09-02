package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Objects;

@KeepForSdk
@SafeParcelable.Class(creator = "ApiMetadataCreator")
public final class ApiMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApiMetadata> CREATOR = zza.zza();
    private static final ApiMetadata zza = newBuilder().build();
    @SafeParcelable.Field(getter = "getComplianceOptions", id = 1)
    private final ComplianceOptions zzb;

    @KeepForSdk
    public static final class Builder {
        private ComplianceOptions zza;

        @KeepForSdk
        public ApiMetadata build() {
            return new ApiMetadata(this.zza);
        }

        @KeepForSdk
        public Builder setComplianceOptions(ComplianceOptions complianceOptions) {
            this.zza = complianceOptions;
            return this;
        }
    }

    @SafeParcelable.Constructor
    ApiMetadata(@SafeParcelable.Param(id = 1) ComplianceOptions complianceOptions) {
        this.zzb = complianceOptions;
    }

    @KeepForSdk
    public static final ApiMetadata fromComplianceOptions(ComplianceOptions complianceOptions) {
        Builder newBuilder = newBuilder();
        newBuilder.setComplianceOptions(complianceOptions);
        return newBuilder.build();
    }

    @KeepForSdk
    public static final ApiMetadata getEmptyInstance() {
        return zza;
    }

    @KeepForSdk
    public static Builder newBuilder() {
        return new Builder();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ApiMetadata)) {
            return false;
        }
        return Objects.equals(this.zzb, ((ApiMetadata) obj).zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "ApiMetadata(complianceOptions=" + valueOf + ")";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(-204102970);
        ComplianceOptions complianceOptions = this.zzb;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, complianceOptions, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
