package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.storage.db.k;

public enum Attachment implements Parcelable {
    PLATFORM(k.a.b),
    CROSS_PLATFORM("cross-platform");
    
    public static final Parcelable.Creator<Attachment> CREATOR = null;
    private final String zzb;

    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(String str) {
            super(String.format("Attachment %s not supported", new Object[]{str}));
        }
    }

    static {
        CREATOR = new zza();
    }

    private Attachment(String str) {
        this.zzb = str;
    }

    public static Attachment fromString(String str) throws UnsupportedAttachmentException {
        for (Attachment attachment : values()) {
            if (str.equals(attachment.zzb)) {
                return attachment;
            }
        }
        throw new UnsupportedAttachmentException(str);
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
