package androidx.core.os;

import android.content.Context;
import android.os.UserManager;

public abstract class UserManagerCompat {
    public static boolean isUserUnlocked(Context context) {
        return Api24Impl.isUserUnlocked(context);
    }

    static class Api24Impl {
        static boolean isUserUnlocked(Context context) {
            return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
        }
    }
}
