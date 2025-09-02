package com.facebook.appevents.codeless.internal;

import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.internal.Intrinsics;

public final class UnityReflection {
    public static final UnityReflection INSTANCE = new UnityReflection();
    private static final String TAG = UnityReflection.class.getCanonicalName();
    private static Class unityPlayer;

    private UnityReflection() {
    }

    private final Class getUnityPlayerClass() {
        Class<?> cls = Class.forName("com.unity3d.player.UnityPlayer");
        Intrinsics.checkNotNullExpressionValue(cls, "forName(UNITY_PLAYER_CLASS)");
        return cls;
    }

    public static final void sendMessage(String str, String str2, String str3) {
        Class<String> cls = String.class;
        try {
            if (unityPlayer == null) {
                unityPlayer = INSTANCE.getUnityPlayerClass();
            }
            Class cls2 = unityPlayer;
            Class cls3 = null;
            if (cls2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unityPlayer");
                cls2 = null;
            }
            Method method = cls2.getMethod("UnitySendMessage", new Class[]{cls, cls, cls});
            Class cls4 = unityPlayer;
            if (cls4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("unityPlayer");
            } else {
                cls3 = cls4;
            }
            method.invoke(cls3, new Object[]{str, str2, str3});
        } catch (Exception e) {
            Log.e(TAG, "Failed to send message to Unity", e);
        }
    }

    public static final void captureViewHierarchy() {
        sendMessage("UnityFacebookSDKPlugin", "CaptureViewHierarchy", "");
    }

    public static final void sendEventMapping(String str) {
        sendMessage("UnityFacebookSDKPlugin", "OnReceiveMapping", str);
    }
}
