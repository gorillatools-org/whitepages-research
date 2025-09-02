package okhttp3;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public interface CookieJar {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final CookieJar NO_COOKIES = new Companion.NoCookies();

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static final class NoCookies implements CookieJar {
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                Intrinsics.checkNotNullParameter(httpUrl, "url");
                Intrinsics.checkNotNullParameter(list, "cookies");
            }

            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                Intrinsics.checkNotNullParameter(httpUrl, "url");
                return CollectionsKt.emptyList();
            }
        }
    }
}
