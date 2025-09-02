package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.Attachment;

final class zza implements Parcelable.Creator {
    zza() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return Attachment.fromString(parcel.readString());
        } catch (Attachment.UnsupportedAttachmentException e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Attachment[i];
    }
}
