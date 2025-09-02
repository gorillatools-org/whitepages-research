package androidx.sqlite.db;

import android.app.ActivityManager;
import android.database.Cursor;
import android.net.Uri;
import kotlin.jvm.internal.Intrinsics;

public final class SupportSQLiteCompat$Api19Impl {
    public static final SupportSQLiteCompat$Api19Impl INSTANCE = new SupportSQLiteCompat$Api19Impl();

    private SupportSQLiteCompat$Api19Impl() {
    }

    public static final Uri getNotificationUri(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Uri notificationUri = cursor.getNotificationUri();
        Intrinsics.checkNotNullExpressionValue(notificationUri, "cursor.notificationUri");
        return notificationUri;
    }

    public static final boolean isLowRamDevice(ActivityManager activityManager) {
        Intrinsics.checkNotNullParameter(activityManager, "activityManager");
        return activityManager.isLowRamDevice();
    }
}
