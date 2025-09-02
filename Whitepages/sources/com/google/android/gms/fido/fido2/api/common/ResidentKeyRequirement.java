package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum ResidentKeyRequirement implements Parcelable {
    RESIDENT_KEY_DISCOURAGED("discouraged"),
    RESIDENT_KEY_PREFERRED("preferred"),
    RESIDENT_KEY_REQUIRED("required");
    
    public static final Parcelable.Creator<ResidentKeyRequirement> CREATOR = null;
    private final String zzb;

    public static class UnsupportedResidentKeyRequirementException extends Exception {
        public UnsupportedResidentKeyRequirementException(String str) {
            super(String.format("Resident key requirement %s not supported", new Object[]{str}));
        }
    }

    static {
        CREATOR = new zzas();
    }

    private ResidentKeyRequirement(String str) {
        this.zzb = str;
    }

    public static ResidentKeyRequirement fromString(String str) throws UnsupportedResidentKeyRequirementException {
        for (ResidentKeyRequirement residentKeyRequirement : values()) {
            if (str.equals(residentKeyRequirement.zzb)) {
                return residentKeyRequirement;
            }
        }
        throw new UnsupportedResidentKeyRequirementException(str);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.zzb;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzb);
    }
}
