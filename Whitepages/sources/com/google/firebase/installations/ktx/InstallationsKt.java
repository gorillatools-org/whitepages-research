package com.google.firebase.installations.ktx;

import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.ktx.Firebase;
import kotlin.jvm.internal.Intrinsics;

public final class InstallationsKt {
    public static final FirebaseInstallations getInstallations(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseInstallations instance = FirebaseInstallations.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseInstallations installations(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, "app");
        FirebaseInstallations instance = FirebaseInstallations.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }
}
