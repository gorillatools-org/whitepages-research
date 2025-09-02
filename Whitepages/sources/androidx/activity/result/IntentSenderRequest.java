package androidx.activity.result;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class IntentSenderRequest implements Parcelable {
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new IntentSenderRequest$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Intent fillInIntent;
    private final int flagsMask;
    private final int flagsValues;
    private final IntentSender intentSender;

    public int describeContents() {
        return 0;
    }

    public IntentSenderRequest(IntentSender intentSender2, Intent intent, int i, int i2) {
        Intrinsics.checkNotNullParameter(intentSender2, "intentSender");
        this.intentSender = intentSender2;
        this.fillInIntent = intent;
        this.flagsMask = i;
        this.flagsValues = i2;
    }

    public final IntentSender getIntentSender() {
        return this.intentSender;
    }

    public final Intent getFillInIntent() {
        return this.fillInIntent;
    }

    public final int getFlagsMask() {
        return this.flagsMask;
    }

    public final int getFlagsValues() {
        return this.flagsValues;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IntentSenderRequest(android.os.Parcel r4) {
        /*
            r3 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.Class<android.content.IntentSender> r0 = android.content.IntentSender.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r4.readParcelable(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            android.content.IntentSender r0 = (android.content.IntentSender) r0
            java.lang.Class<android.content.Intent> r1 = android.content.Intent.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Parcelable r1 = r4.readParcelable(r1)
            android.content.Intent r1 = (android.content.Intent) r1
            int r2 = r4.readInt()
            int r4 = r4.readInt()
            r3.<init>(r0, r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.IntentSenderRequest.<init>(android.os.Parcel):void");
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeParcelable(this.intentSender, i);
        parcel.writeParcelable(this.fillInIntent, i);
        parcel.writeInt(this.flagsMask);
        parcel.writeInt(this.flagsValues);
    }

    public static final class Builder {
        private Intent fillInIntent;
        private int flagsMask;
        private int flagsValues;
        private final IntentSender intentSender;

        public Builder(IntentSender intentSender2) {
            Intrinsics.checkNotNullParameter(intentSender2, "intentSender");
            this.intentSender = intentSender2;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Builder(android.app.PendingIntent r2) {
            /*
                r1 = this;
                java.lang.String r0 = "pendingIntent"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                android.content.IntentSender r2 = r2.getIntentSender()
                java.lang.String r0 = "pendingIntent.intentSender"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
                r1.<init>((android.content.IntentSender) r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.IntentSenderRequest.Builder.<init>(android.app.PendingIntent):void");
        }

        public final Builder setFillInIntent(Intent intent) {
            this.fillInIntent = intent;
            return this;
        }

        public final Builder setFlags(int i, int i2) {
            this.flagsValues = i;
            this.flagsMask = i2;
            return this;
        }

        public final IntentSenderRequest build() {
            return new IntentSenderRequest(this.intentSender, this.fillInIntent, this.flagsMask, this.flagsValues);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
