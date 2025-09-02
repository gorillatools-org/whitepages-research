package com.google.firebase.messaging.ktx;

import com.google.firebase.ktx.Firebase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class MessagingKt {
    public static final FirebaseMessaging getMessaging(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseMessaging instance = FirebaseMessaging.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final RemoteMessage remoteMessage(String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(str, "to");
        Intrinsics.checkNotNullParameter(function1, "init");
        RemoteMessage.Builder builder = new RemoteMessage.Builder(str);
        function1.invoke(builder);
        RemoteMessage build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }
}
