package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GetPhoneNumberHintIntentRequestCreator")
public class GetPhoneNumberHintIntentRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetPhoneNumberHintIntentRequest> CREATOR = new zbh();
    @SafeParcelable.Field(getter = "getTheme", id = 1)
    private final int zba;

    public static final class Builder {
        private Builder() {
            throw null;
        }

        /* synthetic */ Builder(zbg zbg) {
        }

        public GetPhoneNumberHintIntentRequest build() {
            return new GetPhoneNumberHintIntentRequest(0);
        }
    }

    @SafeParcelable.Constructor
    GetPhoneNumberHintIntentRequest(@SafeParcelable.Param(id = 1) int i) {
        this.zba = i;
    }

    public static Builder builder() {
        return new Builder((zbg) null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetPhoneNumberHintIntentRequest)) {
            return false;
        }
        return Objects.equal(Integer.valueOf(this.zba), Integer.valueOf(((GetPhoneNumberHintIntentRequest) obj).zba));
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zba));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zba;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
