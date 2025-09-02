package com.salesforce.marketingcloud.location;

import android.annotation.SuppressLint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"ShiftFlags"})
public final class b {
    public static final a f = new a((DefaultConstructorMarker) null);
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 4;
    private final String a;
    private final float b;
    private final double c;
    private final double d;
    private final int e;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.salesforce.marketingcloud.location.b$b  reason: collision with other inner class name */
    public @interface C0020b {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface c {
    }

    public b(String str, float f2, double d2, double d3, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.a = str;
        this.b = f2;
        this.c = d2;
        this.d = d3;
        this.e = i2;
    }

    public final String a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final double c() {
        return this.c;
    }

    public final double d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) bVar.a) && Float.compare(this.b, bVar.b) == 0 && Double.compare(this.c, bVar.c) == 0 && Double.compare(this.d, bVar.d) == 0 && this.e == bVar.e;
    }

    public final String f() {
        return this.a;
    }

    public final double g() {
        return this.c;
    }

    public final double h() {
        return this.d;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + Float.hashCode(this.b)) * 31) + Double.hashCode(this.c)) * 31) + Double.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
    }

    public final float i() {
        return this.b;
    }

    public final int j() {
        return this.e;
    }

    public String toString() {
        String str = this.a;
        float f2 = this.b;
        double d2 = this.c;
        double d3 = this.d;
        int i2 = this.e;
        return "GeofenceRegion(id=" + str + ", radius=" + f2 + ", latitude=" + d2 + ", longitude=" + d3 + ", transition=" + i2 + ")";
    }

    public final b a(String str, float f2, double d2, double d3, int i2) {
        Intrinsics.checkNotNullParameter(str, "id");
        return new b(str, f2, d2, d3, i2);
    }

    public static /* synthetic */ b a(b bVar, String str, float f2, double d2, double d3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = bVar.a;
        }
        if ((i3 & 2) != 0) {
            f2 = bVar.b;
        }
        float f3 = f2;
        if ((i3 & 4) != 0) {
            d2 = bVar.c;
        }
        double d4 = d2;
        if ((i3 & 8) != 0) {
            d3 = bVar.d;
        }
        double d5 = d3;
        if ((i3 & 16) != 0) {
            i2 = bVar.e;
        }
        return bVar.a(str, f3, d4, d5, i2);
    }
}
