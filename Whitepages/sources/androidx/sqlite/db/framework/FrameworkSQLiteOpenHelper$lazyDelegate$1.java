package androidx.sqlite.db.framework;

import androidx.sqlite.db.SupportSQLiteCompat$Api16Impl;
import androidx.sqlite.db.SupportSQLiteCompat$Api21Impl;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;
import java.io.File;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class FrameworkSQLiteOpenHelper$lazyDelegate$1 extends Lambda implements Function0 {
    final /* synthetic */ FrameworkSQLiteOpenHelper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FrameworkSQLiteOpenHelper$lazyDelegate$1(FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper) {
        super(0);
        this.this$0 = frameworkSQLiteOpenHelper;
    }

    public final FrameworkSQLiteOpenHelper.OpenHelper invoke() {
        FrameworkSQLiteOpenHelper.OpenHelper openHelper;
        if (this.this$0.name == null || !this.this$0.useNoBackupDirectory) {
            openHelper = new FrameworkSQLiteOpenHelper.OpenHelper(this.this$0.context, this.this$0.name, new FrameworkSQLiteOpenHelper.DBRefHolder((FrameworkSQLiteDatabase) null), this.this$0.callback, this.this$0.allowDataLossOnRecovery);
        } else {
            openHelper = new FrameworkSQLiteOpenHelper.OpenHelper(this.this$0.context, new File(SupportSQLiteCompat$Api21Impl.getNoBackupFilesDir(this.this$0.context), this.this$0.name).getAbsolutePath(), new FrameworkSQLiteOpenHelper.DBRefHolder((FrameworkSQLiteDatabase) null), this.this$0.callback, this.this$0.allowDataLossOnRecovery);
        }
        SupportSQLiteCompat$Api16Impl.setWriteAheadLoggingEnabled(openHelper, this.this$0.writeAheadLoggingEnabled);
        return openHelper;
    }
}
