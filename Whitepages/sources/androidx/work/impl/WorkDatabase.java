package androidx.work.impl;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class WorkDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final WorkDatabase create(Context context, Executor executor, boolean z) {
        return Companion.create(context, executor, z);
    }

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WorkDatabase create(Context context, Executor executor, boolean z) {
            RoomDatabase.Builder builder;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(executor, "queryExecutor");
            Class<WorkDatabase> cls = WorkDatabase.class;
            if (z) {
                builder = Room.inMemoryDatabaseBuilder(context, cls).allowMainThreadQueries();
            } else {
                builder = Room.databaseBuilder(context, cls, "androidx.work.workdb").openHelperFactory(new WorkDatabase$Companion$$ExternalSyntheticLambda0(context));
            }
            return (WorkDatabase) builder.setQueryExecutor(executor).addCallback(CleanupCallback.INSTANCE).addMigrations(Migration_1_2.INSTANCE).addMigrations(new RescheduleMigration(context, 2, 3)).addMigrations(Migration_3_4.INSTANCE).addMigrations(Migration_4_5.INSTANCE).addMigrations(new RescheduleMigration(context, 5, 6)).addMigrations(Migration_6_7.INSTANCE).addMigrations(Migration_7_8.INSTANCE).addMigrations(Migration_8_9.INSTANCE).addMigrations(new WorkMigration9To10(context)).addMigrations(new RescheduleMigration(context, 10, 11)).addMigrations(Migration_11_12.INSTANCE).addMigrations(Migration_12_13.INSTANCE).addMigrations(Migration_15_16.INSTANCE).fallbackToDestructiveMigration().build();
        }

        /* access modifiers changed from: private */
        public static final SupportSQLiteOpenHelper create$lambda$0(Context context, SupportSQLiteOpenHelper.Configuration configuration) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            SupportSQLiteOpenHelper.Configuration.Builder builder = SupportSQLiteOpenHelper.Configuration.Companion.builder(context);
            builder.name(configuration.name).callback(configuration.callback).noBackupDirectory(true).allowDataLossOnRecovery(true);
            return new FrameworkSQLiteOpenHelperFactory().create(builder.build());
        }
    }
}
