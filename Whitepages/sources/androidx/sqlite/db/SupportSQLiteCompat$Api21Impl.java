package androidx.sqlite.db;

import android.content.Context;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

public final class SupportSQLiteCompat$Api21Impl {
    public static final SupportSQLiteCompat$Api21Impl INSTANCE = new SupportSQLiteCompat$Api21Impl();

    private SupportSQLiteCompat$Api21Impl() {
    }

    public static final File getNoBackupFilesDir(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File noBackupFilesDir = context.getNoBackupFilesDir();
        Intrinsics.checkNotNullExpressionValue(noBackupFilesDir, "context.noBackupFilesDir");
        return noBackupFilesDir;
    }
}
