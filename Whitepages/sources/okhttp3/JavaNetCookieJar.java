package okhttp3;

import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cookie;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;

public final class JavaNetCookieJar implements CookieJar {
    private final CookieHandler cookieHandler;

    public JavaNetCookieJar(CookieHandler cookieHandler2) {
        Intrinsics.checkNotNullParameter(cookieHandler2, "cookieHandler");
        this.cookieHandler = cookieHandler2;
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        Intrinsics.checkNotNullParameter(list, "cookies");
        ArrayList arrayList = new ArrayList();
        for (Cookie cookieToString : list) {
            arrayList.add(Internal.cookieToString(cookieToString, true));
        }
        try {
            this.cookieHandler.put(httpUrl.uri(), MapsKt.mapOf(TuplesKt.to("Set-Cookie", arrayList)));
        } catch (IOException e) {
            Platform platform = Platform.Companion.get();
            StringBuilder sb = new StringBuilder();
            sb.append("Saving cookies failed for ");
            HttpUrl resolve = httpUrl.resolve("/...");
            Intrinsics.checkNotNull(resolve);
            sb.append(resolve);
            platform.log(sb.toString(), 5, e);
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        Intrinsics.checkNotNullParameter(httpUrl, "url");
        try {
            Map<String, List<String>> map = this.cookieHandler.get(httpUrl.uri(), MapsKt.emptyMap());
            Intrinsics.checkNotNullExpressionValue(map, "cookieHeaders");
            ArrayList arrayList = null;
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                List<String> list = (List) next.getValue();
                if (StringsKt.equals("Cookie", str, true) || StringsKt.equals("Cookie2", str, true)) {
                    Intrinsics.checkNotNullExpressionValue(list, "value");
                    if (!list.isEmpty()) {
                        for (String str2 : list) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            Intrinsics.checkNotNullExpressionValue(str2, "header");
                            arrayList.addAll(decodeHeaderAsJavaNetCookies(httpUrl, str2));
                        }
                    }
                }
            }
            if (arrayList == null) {
                return CollectionsKt.emptyList();
            }
            List<Cookie> unmodifiableList = Collections.unmodifiableList(arrayList);
            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "Collections.unmodifiableList(cookies)");
            return unmodifiableList;
        } catch (IOException e) {
            Platform platform = Platform.Companion.get();
            StringBuilder sb = new StringBuilder();
            sb.append("Loading cookies failed for ");
            HttpUrl resolve = httpUrl.resolve("/...");
            Intrinsics.checkNotNull(resolve);
            sb.append(resolve);
            platform.log(sb.toString(), 5, e);
            return CollectionsKt.emptyList();
        }
    }

    private final List<Cookie> decodeHeaderAsJavaNetCookies(HttpUrl httpUrl, String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int delimiterOffset = Util.delimiterOffset(str, ";,", i, length);
            int delimiterOffset2 = Util.delimiterOffset(str, '=', i, delimiterOffset);
            String trimSubstring = Util.trimSubstring(str, i, delimiterOffset2);
            if (!StringsKt.startsWith$default(trimSubstring, "$", false, 2, (Object) null)) {
                if (delimiterOffset2 < delimiterOffset) {
                    str2 = Util.trimSubstring(str, delimiterOffset2 + 1, delimiterOffset);
                } else {
                    str2 = "";
                }
                if (StringsKt.startsWith$default(str2, "\"", false, 2, (Object) null) && StringsKt.endsWith$default(str2, "\"", false, 2, (Object) null)) {
                    str2 = str2.substring(1, str2.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                arrayList.add(new Cookie.Builder().name(trimSubstring).value(str2).domain(httpUrl.host()).build());
            }
            i = delimiterOffset + 1;
        }
        return arrayList;
    }
}
