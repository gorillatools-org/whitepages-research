package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UvmEntryCreator")
public class UvmEntry extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UvmEntry> CREATOR = new zzba();
    @SafeParcelable.Field(getter = "getUserVerificationMethod", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getKeyProtectionType", id = 2)
    private final short zzb;
    @SafeParcelable.Field(getter = "getMatcherProtectionType", id = 3)
    private final short zzc;

    public static final class Builder {
        private int zza;
        private short zzb;
        private short zzc;

        public UvmEntry build() {
            return new UvmEntry(this.zza, this.zzb, this.zzc);
        }

        public Builder setKeyProtectionType(short s) {
            this.zzb = s;
            return this;
        }

        public Builder setMatcherProtectionType(short s) {
            this.zzc = s;
            return this;
        }

        public Builder setUserVerificationMethod(int i) {
            this.zza = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    UvmEntry(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) short s, @SafeParcelable.Param(id = 3) short s2) {
        this.zza = i;
        this.zzb = s;
        this.zzc = s2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof UvmEntry)) {
            return false;
        }
        UvmEntry uvmEntry = (UvmEntry) obj;
        if (this.zza == uvmEntry.zza && this.zzb == uvmEntry.zzb && this.zzc == uvmEntry.zzc) {
            return true;
        }
        return false;
    }

    public short getKeyProtectionType() {
        return this.zzb;
    }

    public short getMatcherProtectionType() {
        return this.zzc;
    }

    public int getUserVerificationMethod() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Short.valueOf(this.zzb), Short.valueOf(this.zzc));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getUserVerificationMethod());
        SafeParcelWriter.writeShort(parcel, 2, getKeyProtectionType());
        SafeParcelWriter.writeShort(parcel, 3, getMatcherProtectionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
