package com.salesforce.marketingcloud.registration;

import android.os.Build;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class f {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e = "Android";
    private final String f;
    private final String g;

    public f(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "deviceId");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str3, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        this.a = str;
        this.b = str2;
        this.c = str3;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%s %s", Arrays.copyOf(new Object[]{Build.MANUFACTURER, Build.MODEL}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        this.d = format;
        String str4 = Build.VERSION.RELEASE;
        this.f = str4 == null ? "Unknown Release" : str4;
        String sdkVersionName = MarketingCloudSdk.getSdkVersionName();
        Intrinsics.checkNotNullExpressionValue(sdkVersionName, "getSdkVersionName(...)");
        this.g = sdkVersionName;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.b;
    }

    public final String e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) fVar.a) && Intrinsics.areEqual((Object) this.b, (Object) fVar.b) && Intrinsics.areEqual((Object) this.c, (Object) fVar.c);
    }

    public final String f() {
        return this.a;
    }

    public final String g() {
        return this.d;
    }

    public final String h() {
        return this.e;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public final String i() {
        return this.f;
    }

    public final String j() {
        return this.g;
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        String str3 = this.c;
        return "RegistrationMeta(deviceId=" + str + ", appId=" + str2 + ", appVersion=" + str3 + ")";
    }

    public final f a(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "deviceId");
        Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(str3, RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        return new f(str, str2, str3);
    }

    public static /* synthetic */ f a(f fVar, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fVar.a;
        }
        if ((i & 2) != 0) {
            str2 = fVar.b;
        }
        if ((i & 4) != 0) {
            str3 = fVar.c;
        }
        return fVar.a(str, str2, str3);
    }
}
