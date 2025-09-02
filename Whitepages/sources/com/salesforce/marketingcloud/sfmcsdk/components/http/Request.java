package com.salesforce.marketingcloud.sfmcsdk.components.http;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.salesforce.marketingcloud.config.a;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class Request {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    public static final String GET = "GET";
    public static final String PATCH = "PATCH";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final int RESPONSE_REQUEST_FAILED = -100;
    private final int connectionTimeout;
    private final List<String> headers;
    private final String method;
    private final String name;
    private final long rateLimit;
    private final String requestBody;
    private String tag;
    private final String url;

    @Target({ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Method {
    }

    public Request(String str, String str2, int i, String str3, List<String> list, String str4, long j, String str5) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(list, "headers");
        Intrinsics.checkNotNullParameter(str4, "name");
        this.method = str;
        this.requestBody = str2;
        this.connectionTimeout = i;
        this.url = str3;
        this.headers = list;
        this.name = str4;
        this.rateLimit = j;
        this.tag = str5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Request(String str, String str2, int i, String str3, List list, String str4, long j, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, str3, list, str4, j, (i2 & 128) != 0 ? null : str5);
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getRequestBody() {
        return this.requestBody;
    }

    public final int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public final String getUrl() {
        return this.url;
    }

    public final List<String> getHeaders() {
        return this.headers;
    }

    public final String getName() {
        return this.name;
    }

    public final long getRateLimit() {
        return this.rateLimit;
    }

    public final String getTag() {
        return this.tag;
    }

    public final void setTag(String str) {
        this.tag = str;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final Builder toBuilder$sfmcsdk_release() {
        Builder headers2 = new Builder().method(this.method).url(this.url).connectionTimeout(this.connectionTimeout).name(this.name).headers(this.headers);
        String str = this.requestBody;
        if (str != null) {
            headers2.requestBody(str);
        }
        return headers2;
    }

    public static final class Builder {
        private int connectionTimeout = Request.DEFAULT_CONNECTION_TIMEOUT;
        private List<String> headers;
        private Map<String, String> headersMap = new LinkedHashMap();
        private String method;
        private String name;
        private long rateLimit;
        private String requestBody;
        private String tag;
        private String url;

        public final Builder method(String str) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            this.method = str;
            return this;
        }

        public final Builder requestBody(String str) {
            this.requestBody = str;
            return this;
        }

        public final Builder url(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            this.url = str;
            return this;
        }

        public final Builder url(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            Intrinsics.checkNotNullParameter(str2, a.j);
            if (StringsKt.endsWith$default(str, RemoteSettings.FORWARD_SLASH_STRING, false, 2, (Object) null)) {
                str = str.substring(0, str.length() - 1);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            this.url = new URL(str + str2).toString();
            return this;
        }

        public final Builder name(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
            return this;
        }

        public final Builder rateLimit(long j) {
            this.rateLimit = TimeUnit.SECONDS.toMillis(j);
            return this;
        }

        public final Builder connectionTimeout(int i) {
            this.connectionTimeout = i;
            return this;
        }

        public final Builder headers(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers = list;
            return this;
        }

        public final Builder addOrReplaceHeader(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.headersMap.put(str, StringsKt.trim(str2).toString());
            return this;
        }

        public final Builder tag(String str) {
            this.tag = str;
            return this;
        }

        public final Request build() {
            List list;
            List<String> list2 = this.headers;
            if (list2 == null || (list = CollectionsKt.toMutableList((Collection) list2)) == null) {
                list = new ArrayList();
            }
            for (Map.Entry next : this.headersMap.entrySet()) {
                list.add((String) next.getKey());
                list.add((String) next.getValue());
            }
            String str = this.method;
            if (str != null) {
                String str2 = this.url;
                if (str2 != null) {
                    int i = this.connectionTimeout;
                    String str3 = this.requestBody;
                    List list3 = CollectionsKt.toList(list);
                    if (list3 != null) {
                        String str4 = this.name;
                        if (str4 != null) {
                            return new Request(str, str3, i, str2, list3, str4, this.rateLimit, this.tag);
                        }
                        throw new IllegalStateException("Required value was null.");
                    }
                    throw new IllegalStateException("Required value was null.");
                }
                throw new IllegalStateException("Required value was null.");
            }
            throw new IllegalStateException("Required value was null.");
        }
    }
}
