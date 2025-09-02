package com.google.firebase.sessions;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.google.firebase.FirebaseApp;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.sessions.api.SessionSubscriber;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class SessionEvents {
    public static final SessionEvents INSTANCE = new SessionEvents();
    private static final DataEncoder SESSION_EVENT_ENCODER;

    private SessionEvents() {
    }

    public final DataEncoder getSESSION_EVENT_ENCODER$com_google_firebase_firebase_sessions() {
        return SESSION_EVENT_ENCODER;
    }

    static {
        DataEncoder build = new JsonDataEncoderBuilder().configureWith(AutoSessionEventEncoder.CONFIG).ignoreNullValues(true).build();
        Intrinsics.checkNotNullExpressionValue(build, "JsonDataEncoderBuilder()…lues(true)\n      .build()");
        SESSION_EVENT_ENCODER = build;
    }

    public static /* synthetic */ SessionEvent buildSession$default(SessionEvents sessionEvents, FirebaseApp firebaseApp, SessionDetails sessionDetails, SessionsSettings sessionsSettings, Map map, String str, String str2, int i, Object obj) {
        if ((i & 8) != 0) {
            map = MapsKt.emptyMap();
        }
        return sessionEvents.buildSession(firebaseApp, sessionDetails, sessionsSettings, map, (i & 16) != 0 ? "" : str, (i & 32) != 0 ? "" : str2);
    }

    public final SessionEvent buildSession(FirebaseApp firebaseApp, SessionDetails sessionDetails, SessionsSettings sessionsSettings, Map<SessionSubscriber.Name, ? extends SessionSubscriber> map, String str, String str2) {
        Map<SessionSubscriber.Name, ? extends SessionSubscriber> map2 = map;
        Intrinsics.checkNotNullParameter(firebaseApp, "firebaseApp");
        Intrinsics.checkNotNullParameter(sessionDetails, "sessionDetails");
        Intrinsics.checkNotNullParameter(sessionsSettings, "sessionsSettings");
        Intrinsics.checkNotNullParameter(map2, "subscribers");
        Intrinsics.checkNotNullParameter(str, "firebaseInstallationId");
        Intrinsics.checkNotNullParameter(str2, "firebaseAuthenticationToken");
        return new SessionEvent(EventType.SESSION_START, new SessionInfo(sessionDetails.getSessionId(), sessionDetails.getFirstSessionId(), sessionDetails.getSessionIndex(), sessionDetails.getSessionStartTimestampUs(), new DataCollectionStatus(toDataCollectionState((SessionSubscriber) map2.get(SessionSubscriber.Name.PERFORMANCE)), toDataCollectionState((SessionSubscriber) map2.get(SessionSubscriber.Name.CRASHLYTICS)), sessionsSettings.getSamplingRate()), str, str2), getApplicationInfo(firebaseApp));
    }

    public final ApplicationInfo getApplicationInfo(FirebaseApp firebaseApp) {
        String valueOf;
        Intrinsics.checkNotNullParameter(firebaseApp, "firebaseApp");
        Context applicationContext = firebaseApp.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "firebaseApp.applicationContext");
        String packageName = applicationContext.getPackageName();
        PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 0);
        if (Build.VERSION.SDK_INT >= 28) {
            valueOf = String.valueOf(packageInfo.getLongVersionCode());
        } else {
            valueOf = String.valueOf(packageInfo.versionCode);
        }
        String str = valueOf;
        String applicationId = firebaseApp.getOptions().getApplicationId();
        Intrinsics.checkNotNullExpressionValue(applicationId, "firebaseApp.options.applicationId");
        String str2 = Build.MODEL;
        Intrinsics.checkNotNullExpressionValue(str2, "MODEL");
        String str3 = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(str3, "RELEASE");
        LogEnvironment logEnvironment = LogEnvironment.LOG_ENVIRONMENT_PROD;
        Intrinsics.checkNotNullExpressionValue(packageName, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        String str4 = packageInfo.versionName;
        String str5 = str4 == null ? str : str4;
        String str6 = Build.MANUFACTURER;
        Intrinsics.checkNotNullExpressionValue(str6, "MANUFACTURER");
        ProcessDetailsProvider processDetailsProvider = ProcessDetailsProvider.INSTANCE;
        Context applicationContext2 = firebaseApp.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "firebaseApp.applicationContext");
        ProcessDetails currentProcessDetails = processDetailsProvider.getCurrentProcessDetails(applicationContext2);
        Context applicationContext3 = firebaseApp.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "firebaseApp.applicationContext");
        return new ApplicationInfo(applicationId, str2, BuildConfig.VERSION_NAME, str3, logEnvironment, new AndroidApplicationInfo(packageName, str5, str, str6, currentProcessDetails, processDetailsProvider.getAppProcessDetails(applicationContext3)));
    }

    private final DataCollectionState toDataCollectionState(SessionSubscriber sessionSubscriber) {
        if (sessionSubscriber == null) {
            return DataCollectionState.COLLECTION_SDK_NOT_INSTALLED;
        }
        if (sessionSubscriber.isDataCollectionEnabled()) {
            return DataCollectionState.COLLECTION_ENABLED;
        }
        return DataCollectionState.COLLECTION_DISABLED;
    }
}
