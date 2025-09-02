package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteCompat$Api19Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import com.google.android.gms.actions.SearchIntents;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class RoomDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private Map autoMigrationSpecs = new LinkedHashMap();
    private final Map backingFieldMap;
    private SupportSQLiteOpenHelper internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    private final InvalidationTracker invalidationTracker = createInvalidationTracker();
    protected List mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal suspendingTransactionId = new ThreadLocal();
    private final Map typeConverters;
    private boolean writeAheadLoggingEnabled;

    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        }

        public void onDestructiveMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        }
    }

    public static abstract class PrepackagedDatabaseCallback {
    }

    /* access modifiers changed from: protected */
    public abstract InvalidationTracker createInvalidationTracker();

    /* access modifiers changed from: protected */
    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    public RoomDatabase() {
        Map synchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(synchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = synchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
        return null;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.internalOpenHelper;
        if (supportSQLiteOpenHelper != null) {
            return supportSQLiteOpenHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalOpenHelper");
        return null;
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock readLock = this.readWriteLock.readLock();
        Intrinsics.checkNotNullExpressionValue(readLock, "readWriteLock.readLock()");
        return readLock;
    }

    public void init(DatabaseConfiguration databaseConfiguration) {
        Intrinsics.checkNotNullParameter(databaseConfiguration, "configuration");
        this.internalOpenHelper = createOpenHelper(databaseConfiguration);
        Set requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            int i = -1;
            if (it.hasNext()) {
                Class cls = (Class) it.next();
                int size = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = size - 1;
                        if (cls.isAssignableFrom(databaseConfiguration.autoMigrationSpecs.get(size).getClass())) {
                            bitSet.set(size);
                            i = size;
                            break;
                        } else if (i2 < 0) {
                            break;
                        } else {
                            size = i2;
                        }
                    }
                }
                if (i >= 0) {
                    this.autoMigrationSpecs.put(cls, databaseConfiguration.autoMigrationSpecs.get(i));
                } else {
                    throw new IllegalArgumentException(("A required auto migration spec (" + cls.getCanonicalName() + ") is missing in the database configuration.").toString());
                }
            } else {
                int size2 = databaseConfiguration.autoMigrationSpecs.size() - 1;
                if (size2 >= 0) {
                    while (true) {
                        int i3 = size2 - 1;
                        if (!bitSet.get(size2)) {
                            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                        } else if (i3 < 0) {
                            break;
                        } else {
                            size2 = i3;
                        }
                    }
                }
                for (Migration migration : getAutoMigrations(this.autoMigrationSpecs)) {
                    if (!databaseConfiguration.migrationContainer.contains(migration.startVersion, migration.endVersion)) {
                        databaseConfiguration.migrationContainer.addMigrations(migration);
                    }
                }
                SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) unwrapOpenHelper(SQLiteCopyOpenHelper.class, getOpenHelper());
                if (sQLiteCopyOpenHelper != null) {
                    sQLiteCopyOpenHelper.setDatabaseConfiguration(databaseConfiguration);
                }
                AutoClosingRoomOpenHelper autoClosingRoomOpenHelper = (AutoClosingRoomOpenHelper) unwrapOpenHelper(AutoClosingRoomOpenHelper.class, getOpenHelper());
                if (autoClosingRoomOpenHelper != null) {
                    this.autoCloser = autoClosingRoomOpenHelper.autoCloser;
                    getInvalidationTracker().setAutoCloser$room_runtime_release(autoClosingRoomOpenHelper.autoCloser);
                }
                boolean z = databaseConfiguration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
                getOpenHelper().setWriteAheadLoggingEnabled(z);
                this.mCallbacks = databaseConfiguration.callbacks;
                this.internalQueryExecutor = databaseConfiguration.queryExecutor;
                this.internalTransactionExecutor = new TransactionExecutor(databaseConfiguration.transactionExecutor);
                this.allowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
                this.writeAheadLoggingEnabled = z;
                if (databaseConfiguration.multiInstanceInvalidationServiceIntent != null) {
                    if (databaseConfiguration.name != null) {
                        getInvalidationTracker().startMultiInstanceInvalidation$room_runtime_release(databaseConfiguration.context, databaseConfiguration.name, databaseConfiguration.multiInstanceInvalidationServiceIntent);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                }
                Map requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                for (Map.Entry entry : requiredTypeConverters.entrySet()) {
                    Class cls2 = (Class) entry.getKey();
                    Iterator it2 = ((List) entry.getValue()).iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Class cls3 = (Class) it2.next();
                            int size3 = databaseConfiguration.typeConverters.size() - 1;
                            if (size3 >= 0) {
                                while (true) {
                                    int i4 = size3 - 1;
                                    if (cls3.isAssignableFrom(databaseConfiguration.typeConverters.get(size3).getClass())) {
                                        bitSet2.set(size3);
                                        break;
                                    } else if (i4 < 0) {
                                        break;
                                    } else {
                                        size3 = i4;
                                    }
                                }
                            }
                            size3 = -1;
                            if (size3 >= 0) {
                                this.typeConverters.put(cls3, databaseConfiguration.typeConverters.get(size3));
                            } else {
                                throw new IllegalArgumentException(("A required type converter (" + cls3 + ") for " + cls2.getCanonicalName() + " is missing in the database configuration.").toString());
                            }
                        }
                    }
                }
                int size4 = databaseConfiguration.typeConverters.size() - 1;
                if (size4 >= 0) {
                    while (true) {
                        int i5 = size4 - 1;
                        if (!bitSet2.get(size4)) {
                            throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                        } else if (i5 >= 0) {
                            size4 = i5;
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public List getAutoMigrations(Map map) {
        Intrinsics.checkNotNullParameter(map, "autoMigrationSpecs");
        return CollectionsKt.emptyList();
    }

    private final Object unwrapOpenHelper(Class cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Map getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    public Set getRequiredAutoMigrationSpecs() {
        return SetsKt.emptySet();
    }

    public boolean isOpen() {
        Boolean bool;
        boolean isOpen;
        AutoCloser autoCloser2 = this.autoCloser;
        if (autoCloser2 != null) {
            isOpen = autoCloser2.isActive();
        } else {
            SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
            if (supportSQLiteDatabase != null) {
                isOpen = supportSQLiteDatabase.isOpen();
            } else {
                bool = null;
                return Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE);
            }
        }
        bool = Boolean.valueOf(isOpen);
        return Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE);
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && isMainThread$room_runtime_release()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                cancellationSignal = null;
            }
            return roomDatabase.query(supportSQLiteQuery, cancellationSignal);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery, SearchIntents.EXTRA_QUERY);
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return getOpenHelper().getWritableDatabase().query(supportSQLiteQuery, cancellationSignal);
        }
        return getOpenHelper().getWritableDatabase().query(supportSQLiteQuery);
    }

    public SupportSQLiteStatement compileStatement(String str) {
        Intrinsics.checkNotNullParameter(str, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(str);
    }

    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser2 = this.autoCloser;
        if (autoCloser2 == null) {
            internalBeginTransaction();
        } else {
            autoCloser2.executeRefCountingFunction(new RoomDatabase$beginTransaction$1(this));
        }
    }

    /* access modifiers changed from: private */
    public final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        getInvalidationTracker().syncTriggers$room_runtime_release(writableDatabase);
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    public void endTransaction() {
        AutoCloser autoCloser2 = this.autoCloser;
        if (autoCloser2 == null) {
            internalEndTransaction();
        } else {
            autoCloser2.executeRefCountingFunction(new RoomDatabase$endTransaction$1(this));
        }
    }

    /* access modifiers changed from: private */
    public final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            getInvalidationTracker().refreshVersionsAsync();
        }
    }

    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "body");
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public Object runInTransaction(Callable callable) {
        Intrinsics.checkNotNullParameter(callable, "body");
        beginTransaction();
        try {
            Object call = callable.call();
            setTransactionSuccessful();
            return call;
        } finally {
            endTransaction();
        }
    }

    /* access modifiers changed from: protected */
    public void internalInitInvalidationTracker(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        getInvalidationTracker().internalInit$room_runtime_release(supportSQLiteDatabase);
    }

    public boolean inTransaction() {
        return getOpenHelper().getWritableDatabase().inTransaction();
    }

    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        public final JournalMode resolve$room_runtime_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this != AUTOMATIC) {
                return this;
            }
            Object systemService = context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            if (!isLowRamDevice((ActivityManager) systemService)) {
                return WRITE_AHEAD_LOGGING;
            }
            return TRUNCATE;
        }

        private final boolean isLowRamDevice(ActivityManager activityManager) {
            return SupportSQLiteCompat$Api19Impl.isLowRamDevice(activityManager);
        }
    }

    public static class Builder {
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout = -1;
        private List autoMigrationSpecs = new ArrayList();
        private final List callbacks = new ArrayList();
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable copyFromInputStream;
        private SupportSQLiteOpenHelper.Factory factory;
        private JournalMode journalMode = JournalMode.AUTOMATIC;
        private final Class klass;
        private final MigrationContainer migrationContainer = new MigrationContainer();
        private Set migrationStartAndEndVersions;
        private Set migrationsNotRequiredFrom = new LinkedHashSet();
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private Executor queryExecutor;
        private boolean requireMigration = true;
        private Executor transactionExecutor;
        private final List typeConverters = new ArrayList();

        public Builder(Context context2, Class cls, String str) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(cls, "klass");
            this.context = context2;
            this.klass = cls;
            this.name = str;
        }

        public Builder openHelperFactory(SupportSQLiteOpenHelper.Factory factory2) {
            this.factory = factory2;
            return this;
        }

        public Builder addMigrations(Migration... migrationArr) {
            Intrinsics.checkNotNullParameter(migrationArr, "migrations");
            if (this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrationArr) {
                Set set = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set);
                set.add(Integer.valueOf(migration.startVersion));
                Set set2 = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set2);
                set2.add(Integer.valueOf(migration.endVersion));
            }
            this.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(migrationArr, migrationArr.length));
            return this;
        }

        public Builder allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public Builder setQueryExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            this.queryExecutor = executor;
            return this;
        }

        public Builder fallbackToDestructiveMigration() {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder addCallback(Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callbacks.add(callback);
            return this;
        }

        public RoomDatabase build() {
            Executor executor = this.queryExecutor;
            if (executor == null && this.transactionExecutor == null) {
                Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
                this.transactionExecutor = iOThreadExecutor;
                this.queryExecutor = iOThreadExecutor;
            } else if (executor != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor;
            } else if (executor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set<Number> set = this.migrationStartAndEndVersions;
            if (set != null) {
                Intrinsics.checkNotNull(set);
                for (Number intValue : set) {
                    int intValue2 = intValue.intValue();
                    if (this.migrationsNotRequiredFrom.contains(Integer.valueOf(intValue2))) {
                        throw new IllegalArgumentException(("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + intValue2).toString());
                    }
                }
            }
            AutoClosingRoomOpenHelperFactory autoClosingRoomOpenHelperFactory = this.factory;
            if (autoClosingRoomOpenHelperFactory == null) {
                autoClosingRoomOpenHelperFactory = new FrameworkSQLiteOpenHelperFactory();
            }
            if (autoClosingRoomOpenHelperFactory != null) {
                if (this.autoCloseTimeout > 0) {
                    if (this.name != null) {
                        long j = this.autoCloseTimeout;
                        TimeUnit timeUnit = this.autoCloseTimeUnit;
                        if (timeUnit != null) {
                            Executor executor2 = this.queryExecutor;
                            if (executor2 != null) {
                                autoClosingRoomOpenHelperFactory = new AutoClosingRoomOpenHelperFactory(autoClosingRoomOpenHelperFactory, new AutoCloser(j, timeUnit, executor2));
                            } else {
                                throw new IllegalArgumentException("Required value was null.");
                            }
                        } else {
                            throw new IllegalArgumentException("Required value was null.");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
                    }
                }
                String str = this.copyFromAssetPath;
                if (!(str == null && this.copyFromFile == null && this.copyFromInputStream == null)) {
                    if (this.name != null) {
                        int i = 0;
                        int i2 = str == null ? 0 : 1;
                        File file = this.copyFromFile;
                        int i3 = file == null ? 0 : 1;
                        Callable callable = this.copyFromInputStream;
                        if (callable != null) {
                            i = 1;
                        }
                        if (i2 + i3 + i == 1) {
                            autoClosingRoomOpenHelperFactory = new SQLiteCopyOpenHelperFactory(str, file, callable, autoClosingRoomOpenHelperFactory);
                        } else {
                            throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                    }
                }
            } else {
                autoClosingRoomOpenHelperFactory = null;
            }
            SQLiteCopyOpenHelperFactory sQLiteCopyOpenHelperFactory = autoClosingRoomOpenHelperFactory;
            if (sQLiteCopyOpenHelperFactory != null) {
                Context context2 = this.context;
                String str2 = this.name;
                MigrationContainer migrationContainer2 = this.migrationContainer;
                List list = this.callbacks;
                boolean z = this.allowMainThreadQueries;
                JournalMode resolve$room_runtime_release = this.journalMode.resolve$room_runtime_release(context2);
                Executor executor3 = this.queryExecutor;
                if (executor3 != null) {
                    Executor executor4 = this.transactionExecutor;
                    if (executor4 != null) {
                        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context2, str2, sQLiteCopyOpenHelperFactory, migrationContainer2, list, z, resolve$room_runtime_release, executor3, executor4, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, (PrepackagedDatabaseCallback) null, this.typeConverters, this.autoMigrationSpecs);
                        RoomDatabase roomDatabase = (RoomDatabase) Room.getGeneratedImplementation(this.klass, "_Impl");
                        roomDatabase.init(databaseConfiguration);
                        return roomDatabase;
                    }
                    throw new IllegalArgumentException("Required value was null.");
                }
                throw new IllegalArgumentException("Required value was null.");
            }
            throw new IllegalArgumentException("Required value was null.");
        }
    }

    public static class MigrationContainer {
        private final Map migrations = new LinkedHashMap();

        private final void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            Map map = this.migrations;
            Integer valueOf = Integer.valueOf(i);
            Object obj = map.get(valueOf);
            if (obj == null) {
                obj = new TreeMap();
                map.put(valueOf, obj);
            }
            TreeMap treeMap = (TreeMap) obj;
            if (treeMap.containsKey(Integer.valueOf(i2))) {
                Log.w("ROOM", "Overriding migration " + treeMap.get(Integer.valueOf(i2)) + " with " + migration);
            }
            treeMap.put(Integer.valueOf(i2), migration);
        }

        public Map getMigrations() {
            return this.migrations;
        }

        public List findMigrationPath(int i, int i2) {
            if (i == i2) {
                return CollectionsKt.emptyList();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        private final List findUpMigrationPath(List list, boolean z, int i, int i2) {
            Set set;
            boolean z2;
            Integer num;
            do {
                if (z) {
                    if (i >= i2) {
                        return list;
                    }
                } else if (i <= i2) {
                    return list;
                }
                TreeMap treeMap = (TreeMap) this.migrations.get(Integer.valueOf(i));
                if (treeMap == null) {
                    return null;
                }
                if (z) {
                    set = treeMap.descendingKeySet();
                } else {
                    set = treeMap.keySet();
                }
                Iterator it = set.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        continue;
                        break;
                    }
                    num = (Integer) it.next();
                    if (!z) {
                        Intrinsics.checkNotNullExpressionValue(num, "targetVersion");
                        int intValue = num.intValue();
                        if (i2 <= intValue && intValue < i) {
                            break;
                        }
                    } else {
                        int i3 = i + 1;
                        Intrinsics.checkNotNullExpressionValue(num, "targetVersion");
                        int intValue2 = num.intValue();
                        if (i3 <= intValue2 && intValue2 <= i2) {
                            break;
                        }
                    }
                }
                Object obj = treeMap.get(num);
                Intrinsics.checkNotNull(obj);
                list.add(obj);
                i = num.intValue();
                z2 = true;
                continue;
            } while (z2);
            return null;
        }

        public final boolean contains(int i, int i2) {
            Map migrations2 = getMigrations();
            if (!migrations2.containsKey(Integer.valueOf(i))) {
                return false;
            }
            Map map = (Map) migrations2.get(Integer.valueOf(i));
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            return map.containsKey(Integer.valueOf(i2));
        }

        public void addMigrations(Migration... migrationArr) {
            Intrinsics.checkNotNullParameter(migrationArr, "migrations");
            for (Migration addMigration : migrationArr) {
                addMigration(addMigration);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
