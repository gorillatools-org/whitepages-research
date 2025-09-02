package okhttp3.internal.http;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.FirebasePerformance;
import kotlin.jvm.internal.Intrinsics;

public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public final boolean invalidatesCache(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return Intrinsics.areEqual((Object) str, (Object) "POST") || Intrinsics.areEqual((Object) str, (Object) "PATCH") || Intrinsics.areEqual((Object) str, (Object) "PUT") || Intrinsics.areEqual((Object) str, (Object) FirebasePerformance.HttpMethod.DELETE) || Intrinsics.areEqual((Object) str, (Object) "MOVE");
    }

    public static final boolean requiresRequestBody(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return Intrinsics.areEqual((Object) str, (Object) "POST") || Intrinsics.areEqual((Object) str, (Object) "PUT") || Intrinsics.areEqual((Object) str, (Object) "PATCH") || Intrinsics.areEqual((Object) str, (Object) "PROPPATCH") || Intrinsics.areEqual((Object) str, (Object) "REPORT");
    }

    public static final boolean permitsRequestBody(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return !Intrinsics.areEqual((Object) str, (Object) "GET") && !Intrinsics.areEqual((Object) str, (Object) FirebasePerformance.HttpMethod.HEAD);
    }

    public final boolean redirectsWithBody(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return Intrinsics.areEqual((Object) str, (Object) "PROPFIND");
    }

    public final boolean redirectsToGet(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return !Intrinsics.areEqual((Object) str, (Object) "PROPFIND");
    }
}
