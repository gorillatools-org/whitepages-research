package com.google.firebase.remoteconfig.ktx;

import com.google.firebase.FirebaseApp;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

public final class RemoteConfigKt {
    public static /* synthetic */ void getConfigUpdates$annotations(FirebaseRemoteConfig firebaseRemoteConfig) {
    }

    public static final FirebaseRemoteConfig getRemoteConfig(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseRemoteConfig remoteConfig(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, "app");
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseRemoteConfigValue get(FirebaseRemoteConfig firebaseRemoteConfig, String str) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseRemoteConfigValue value = firebaseRemoteConfig.getValue(str);
        Intrinsics.checkNotNullExpressionValue(value, "this.getValue(key)");
        return value;
    }

    public static final FirebaseRemoteConfigSettings remoteConfigSettings(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "init");
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        function1.invoke(builder);
        FirebaseRemoteConfigSettings build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final Flow getConfigUpdates(FirebaseRemoteConfig firebaseRemoteConfig) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfig, "<this>");
        return FlowKt.callbackFlow(new RemoteConfigKt$configUpdates$1(firebaseRemoteConfig, (Continuation) null));
    }
}
