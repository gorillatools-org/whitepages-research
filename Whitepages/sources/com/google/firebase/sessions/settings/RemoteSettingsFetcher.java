package com.google.firebase.sessions.settings;

import android.net.Uri;
import com.google.firebase.sessions.ApplicationInfo;
import java.net.URL;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

public final class RemoteSettingsFetcher implements CrashlyticsSettingsFetcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FIREBASE_PLATFORM = "android";
    private static final String FIREBASE_SESSIONS_BASE_URL_STRING = "firebase-settings.crashlytics.com";
    private final ApplicationInfo appInfo;
    private final String baseUrl;
    private final CoroutineContext blockingDispatcher;

    public RemoteSettingsFetcher(ApplicationInfo applicationInfo, CoroutineContext coroutineContext, String str) {
        Intrinsics.checkNotNullParameter(applicationInfo, "appInfo");
        Intrinsics.checkNotNullParameter(coroutineContext, "blockingDispatcher");
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        this.appInfo = applicationInfo;
        this.blockingDispatcher = coroutineContext;
        this.baseUrl = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RemoteSettingsFetcher(ApplicationInfo applicationInfo, CoroutineContext coroutineContext, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationInfo, coroutineContext, (i & 4) != 0 ? FIREBASE_SESSIONS_BASE_URL_STRING : str);
    }

    public Object doConfigFetch(Map<String, String> map, Function2 function2, Function2 function22, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.blockingDispatcher, new RemoteSettingsFetcher$doConfigFetch$2(this, map, function2, function22, (Continuation) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final URL settingsUrl() {
        return new URL(new Uri.Builder().scheme("https").authority(this.baseUrl).appendPath("spi").appendPath("v2").appendPath("platforms").appendPath(FIREBASE_PLATFORM).appendPath("gmp").appendPath(this.appInfo.getAppId()).appendPath("settings").appendQueryParameter("build_version", this.appInfo.getAndroidAppInfo().getAppBuildVersion()).appendQueryParameter("display_version", this.appInfo.getAndroidAppInfo().getVersionName()).build().toString());
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
