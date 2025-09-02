package androidx.room;

import android.content.Context;
import android.content.Intent;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

public class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    public final List autoMigrationSpecs;
    public final List callbacks;
    public final Context context;
    public final String copyFromAssetPath;
    public final File copyFromFile;
    public final Callable copyFromInputStream;
    public final RoomDatabase.JournalMode journalMode;
    public final RoomDatabase.MigrationContainer migrationContainer;
    private final Set migrationNotRequiredFrom;
    public final boolean multiInstanceInvalidation;
    public final Intent multiInstanceInvalidationServiceIntent;
    public final String name;
    public final Executor queryExecutor;
    public final boolean requireMigration;
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;
    public final List typeConverters;

    public DatabaseConfiguration(Context context2, String str, SupportSQLiteOpenHelper.Factory factory, RoomDatabase.MigrationContainer migrationContainer2, List list, boolean z, RoomDatabase.JournalMode journalMode2, Executor executor, Executor executor2, Intent intent, boolean z2, boolean z3, Set set, String str2, File file, Callable callable, RoomDatabase.PrepackagedDatabaseCallback prepackagedDatabaseCallback, List list2, List list3) {
        RoomDatabase.JournalMode journalMode3 = journalMode2;
        Executor executor3 = executor;
        Executor executor4 = executor2;
        Intent intent2 = intent;
        List list4 = list2;
        List list5 = list3;
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(factory, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(migrationContainer2, "migrationContainer");
        Intrinsics.checkNotNullParameter(journalMode3, "journalMode");
        Intrinsics.checkNotNullParameter(executor3, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor4, "transactionExecutor");
        Intrinsics.checkNotNullParameter(list4, "typeConverters");
        Intrinsics.checkNotNullParameter(list5, "autoMigrationSpecs");
        this.context = context2;
        this.name = str;
        this.sqliteOpenHelperFactory = factory;
        this.migrationContainer = migrationContainer2;
        this.callbacks = list;
        this.allowMainThreadQueries = z;
        this.journalMode = journalMode3;
        this.queryExecutor = executor3;
        this.transactionExecutor = executor4;
        this.multiInstanceInvalidationServiceIntent = intent2;
        this.requireMigration = z2;
        this.allowDestructiveMigrationOnDowngrade = z3;
        this.migrationNotRequiredFrom = set;
        this.copyFromAssetPath = str2;
        this.copyFromFile = file;
        this.copyFromInputStream = callable;
        this.typeConverters = list4;
        this.autoMigrationSpecs = list5;
        this.multiInstanceInvalidation = intent2 != null;
    }

    public boolean isMigrationRequired(int i, int i2) {
        if ((i > i2 && this.allowDestructiveMigrationOnDowngrade) || !this.requireMigration) {
            return false;
        }
        Set set = this.migrationNotRequiredFrom;
        if (set == null || !set.contains(Integer.valueOf(i))) {
            return true;
        }
        return false;
    }
}
