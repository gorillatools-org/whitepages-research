package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

public enum PublicKeyCredentialType implements Parcelable {
    PUBLIC_KEY("public-key");
    
    public static final Parcelable.Creator<PublicKeyCredentialType> CREATOR = null;
    private final String zzb;

    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(String str) {
            super(str);
        }
    }

    static {
        CREATOR = new zzaq();
    }

    private PublicKeyCredentialType(String str) {
        this.zzb = "public-key";
    }

    public static PublicKeyCredentialType fromString(String str) throws UnsupportedPublicKeyCredTypeException {
        for (PublicKeyCredentialType publicKeyCredentialType : values()) {
            if (str.equals(publicKeyCredentialType.zzb)) {
                return publicKeyCredentialType;
            }
        }
        throw new UnsupportedPublicKeyCredTypeException(String.format("PublicKeyCredentialType %s not supported", new Object[]{str}));
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
