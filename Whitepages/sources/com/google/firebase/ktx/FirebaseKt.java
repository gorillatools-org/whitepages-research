package com.google.firebase.ktx;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.components.Component;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

public final class FirebaseKt {
    public static final FirebaseApp getApp(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseApp instance = FirebaseApp.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseApp app(Firebase firebase2, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        FirebaseApp instance = FirebaseApp.getInstance(str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(name)");
        return instance;
    }

    public static final FirebaseApp initialize(Firebase firebase2, Context context) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return FirebaseApp.initializeApp(context);
    }

    public static final FirebaseApp initialize(Firebase firebase2, Context context, FirebaseOptions firebaseOptions) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(firebaseOptions, "options");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, firebaseOptions);
        Intrinsics.checkNotNullExpressionValue(initializeApp, "initializeApp(context, options)");
        return initializeApp;
    }

    public static final FirebaseApp initialize(Firebase firebase2, Context context, FirebaseOptions firebaseOptions, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(firebaseOptions, "options");
        Intrinsics.checkNotNullParameter(str, "name");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, firebaseOptions, str);
        Intrinsics.checkNotNullExpressionValue(initializeApp, "initializeApp(context, options, name)");
        return initializeApp;
    }

    public static final FirebaseOptions getOptions(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseOptions options = getApp(Firebase.INSTANCE).getOptions();
        Intrinsics.checkNotNullExpressionValue(options, "Firebase.app.options");
        return options;
    }

    private static final /* synthetic */ <T extends Annotation> Component<CoroutineDispatcher> coroutineDispatcher() {
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<Annotation> cls = Annotation.class;
        Component.Builder<Annotation> builder = Component.builder(Qualified.qualified(cls, CoroutineDispatcher.class));
        Intrinsics.reifiedOperationMarker(4, "T");
        Component.Builder<Annotation> add = builder.add(Dependency.required((Qualified<?>) Qualified.qualified(cls, Executor.class)));
        Intrinsics.needClassReification();
        Component<Annotation> build = add.factory(FirebaseKt$coroutineDispatcher$1.INSTANCE).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder(Qualified.qualif…cher()\n    }\n    .build()");
        return build;
    }
}
