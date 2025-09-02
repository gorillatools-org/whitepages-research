package androidx.activity.result;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class IntentSenderRequest$Companion$CREATOR$1 implements Parcelable.Creator {
    IntentSenderRequest$Companion$CREATOR$1() {
    }

    public IntentSenderRequest createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "inParcel");
        return new IntentSenderRequest(parcel);
    }

    public IntentSenderRequest[] newArray(int i) {
        return new IntentSenderRequest[i];
    }
}
