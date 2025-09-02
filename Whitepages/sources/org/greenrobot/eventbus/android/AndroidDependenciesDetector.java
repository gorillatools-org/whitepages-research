package org.greenrobot.eventbus.android;

import java.lang.reflect.InvocationTargetException;

public abstract class AndroidDependenciesDetector {
    public static boolean isAndroidSDKAvailable() {
        try {
            if (Class.forName("android.os.Looper").getDeclaredMethod("getMainLooper", (Class[]) null).invoke((Object) null, (Object[]) null) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public static boolean areAndroidComponentsAvailable() {
        try {
            int i = AndroidComponentsImpl.$r8$clinit;
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static AndroidComponents instantiateAndroidComponents() {
        Class<AndroidComponentsImpl> cls = AndroidComponentsImpl.class;
        try {
            int i = AndroidComponentsImpl.$r8$clinit;
            return cls.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Throwable unused) {
            return null;
        }
    }
}
