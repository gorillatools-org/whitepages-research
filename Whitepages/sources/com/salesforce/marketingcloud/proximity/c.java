package com.salesforce.marketingcloud.proximity;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new a();
    private final String a;
    private final String b;
    private final int c;
    private final int d;

    public static final class a implements Parcelable.Creator<c> {
        /* renamed from: a */
        public final c createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new c(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        /* renamed from: a */
        public final c[] newArray(int i) {
            return new c[i];
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(com.salesforce.marketingcloud.messages.Region r4) throws java.lang.IllegalArgumentException {
        /*
            r3 = this;
            java.lang.String r0 = "region"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = r4.id()
            java.lang.String r1 = r4.proximityUuid()
            if (r1 == 0) goto L_0x001b
            int r2 = r4.major()
            int r4 = r4.minor()
            r3.<init>(r0, r1, r2, r4)
            return
        L_0x001b:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Not a beacon region"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.proximity.c.<init>(com.salesforce.marketingcloud.messages.Region):void");
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final int d() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public final String e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) cVar.a) && Intrinsics.areEqual((Object) this.b, (Object) cVar.b) && this.c == cVar.c && this.d == cVar.d;
    }

    public final String f() {
        return this.a;
    }

    public final int g() {
        return this.c;
    }

    public final int h() {
        return this.d;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        int i = this.c;
        int i2 = this.d;
        return "BeaconRegion(id=" + str + ", guid=" + str2 + ", major=" + i + ", minor=" + i2 + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }

    public c(String str, String str2, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "guid");
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
    }

    public final c a(String str, String str2, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "guid");
        return new c(str, str2, i, i2);
    }

    public static /* synthetic */ c a(c cVar, String str, String str2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = cVar.a;
        }
        if ((i3 & 2) != 0) {
            str2 = cVar.b;
        }
        if ((i3 & 4) != 0) {
            i = cVar.c;
        }
        if ((i3 & 8) != 0) {
            i2 = cVar.d;
        }
        return cVar.a(str, str2, i, i2);
    }
}
