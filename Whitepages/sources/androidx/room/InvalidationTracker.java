package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.firebase.perf.FirebasePerformance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public class InvalidationTracker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String[] TRIGGERS = {"UPDATE", FirebasePerformance.HttpMethod.DELETE, "INSERT"};
    /* access modifiers changed from: private */
    public AutoCloser autoCloser;
    private volatile SupportSQLiteStatement cleanupStatement;
    private final RoomDatabase database;
    private volatile boolean initialized;
    private final InvalidationLiveDataContainer invalidationLiveDataContainer;
    private MultiInstanceInvalidationClient multiInstanceInvalidationClient;
    private final ObservedTableTracker observedTableTracker;
    private final SafeIterableMap observerMap;
    private final AtomicBoolean pendingRefresh = new AtomicBoolean(false);
    public final Runnable refreshRunnable;
    private final Map shadowTablesMap;
    private final Object syncTriggersLock;
    private final Map tableIdLookup;
    private final String[] tablesNames;
    private final Object trackerLock;
    private final Map viewTables;

    public InvalidationTracker(RoomDatabase roomDatabase, Map map, Map map2, String... strArr) {
        String str;
        Intrinsics.checkNotNullParameter(roomDatabase, "database");
        Intrinsics.checkNotNullParameter(map, "shadowTablesMap");
        Intrinsics.checkNotNullParameter(map2, "viewTables");
        Intrinsics.checkNotNullParameter(strArr, "tableNames");
        this.database = roomDatabase;
        this.shadowTablesMap = map;
        this.viewTables = map2;
        this.observedTableTracker = new ObservedTableTracker(strArr.length);
        this.invalidationLiveDataContainer = new InvalidationLiveDataContainer(roomDatabase);
        this.observerMap = new SafeIterableMap();
        this.syncTriggersLock = new Object();
        this.trackerLock = new Object();
        this.tableIdLookup = new LinkedHashMap();
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase = str2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            this.tableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str3 = (String) this.shadowTablesMap.get(strArr[i]);
            if (str3 != null) {
                Intrinsics.checkNotNullExpressionValue(locale, "US");
                str = str3.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase(locale)");
            } else {
                str = null;
            }
            if (str != null) {
                lowerCase = str;
            }
            strArr2[i] = lowerCase;
        }
        this.tablesNames = strArr2;
        for (Map.Entry entry : this.shadowTablesMap.entrySet()) {
            Locale locale2 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale2, "US");
            String lowerCase2 = ((String) entry.getValue()).toLowerCase(locale2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            if (this.tableIdLookup.containsKey(lowerCase2)) {
                Intrinsics.checkNotNullExpressionValue(locale2, "US");
                String lowerCase3 = ((String) entry.getKey()).toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
                Map map3 = this.tableIdLookup;
                map3.put(lowerCase3, MapsKt.getValue(map3, lowerCase2));
            }
        }
        this.refreshRunnable = new InvalidationTracker$refreshRunnable$1(this);
    }

    public final RoomDatabase getDatabase$room_runtime_release() {
        return this.database;
    }

    public final Map getTableIdLookup$room_runtime_release() {
        return this.tableIdLookup;
    }

    public final AtomicBoolean getPendingRefresh() {
        return this.pendingRefresh;
    }

    public final SupportSQLiteStatement getCleanupStatement$room_runtime_release() {
        return this.cleanupStatement;
    }

    public final SafeIterableMap getObserverMap$room_runtime_release() {
        return this.observerMap;
    }

    public final void setAutoCloser$room_runtime_release(AutoCloser autoCloser2) {
        Intrinsics.checkNotNullParameter(autoCloser2, "autoCloser");
        this.autoCloser = autoCloser2;
        autoCloser2.setAutoCloseCallback(new InvalidationTracker$$ExternalSyntheticLambda0(this));
    }

    public final void internalInit$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        synchronized (this.trackerLock) {
            if (this.initialized) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            supportSQLiteDatabase.execSQL("PRAGMA temp_store = MEMORY;");
            supportSQLiteDatabase.execSQL("PRAGMA recursive_triggers='ON';");
            supportSQLiteDatabase.execSQL("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            syncTriggers$room_runtime_release(supportSQLiteDatabase);
            this.cleanupStatement = supportSQLiteDatabase.compileStatement("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            this.initialized = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void onAutoCloseCallback$room_runtime_release() {
        synchronized (this.trackerLock) {
            this.initialized = false;
            this.observedTableTracker.resetTriggerState();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void startMultiInstanceInvalidation$room_runtime_release(Context context, String str, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(intent, "serviceIntent");
        this.multiInstanceInvalidationClient = new MultiInstanceInvalidationClient(context, str, intent, this, this.database.getQueryExecutor());
    }

    private final void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        String str = this.tablesNames[i];
        for (String str2 : TRIGGERS) {
            String str3 = "DROP TRIGGER IF EXISTS " + Companion.getTriggerName$room_runtime_release(str, str2);
            Intrinsics.checkNotNullExpressionValue(str3, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase.execSQL(str3);
        }
    }

    private final void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.tablesNames[i];
        for (String str2 : TRIGGERS) {
            String str3 = "CREATE TEMP TRIGGER IF NOT EXISTS " + Companion.getTriggerName$room_runtime_release(str, str2) + " AFTER " + str2 + " ON `" + str + "` BEGIN UPDATE " + "room_table_modification_log" + " SET " + "invalidated" + " = 1" + " WHERE " + "table_id" + " = " + i + " AND " + "invalidated" + " = 0" + "; END";
            Intrinsics.checkNotNullExpressionValue(str3, "StringBuilder().apply(builderAction).toString()");
            supportSQLiteDatabase.execSQL(str3);
        }
    }

    public void addObserver(Observer observer) {
        ObserverWrapper observerWrapper;
        Intrinsics.checkNotNullParameter(observer, "observer");
        String[] resolveViews = resolveViews(observer.getTables$room_runtime_release());
        ArrayList arrayList = new ArrayList(resolveViews.length);
        int length = resolveViews.length;
        int i = 0;
        while (i < length) {
            String str = resolveViews[i];
            Map map = this.tableIdLookup;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            Integer num = (Integer) map.get(lowerCase);
            if (num != null) {
                arrayList.add(num);
                i++;
            } else {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
        }
        int[] intArray = CollectionsKt.toIntArray(arrayList);
        ObserverWrapper observerWrapper2 = new ObserverWrapper(observer, intArray, resolveViews);
        synchronized (this.observerMap) {
            observerWrapper = (ObserverWrapper) this.observerMap.putIfAbsent(observer, observerWrapper2);
        }
        if (observerWrapper == null && this.observedTableTracker.onAdded(Arrays.copyOf(intArray, intArray.length))) {
            syncTriggers$room_runtime_release();
        }
    }

    private final String[] resolveViews(String[] strArr) {
        Set createSetBuilder = SetsKt.createSetBuilder();
        for (String str : strArr) {
            Map map = this.viewTables;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (map.containsKey(lowerCase)) {
                Map map2 = this.viewTables;
                Intrinsics.checkNotNullExpressionValue(locale, "US");
                String lowerCase2 = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                Object obj = map2.get(lowerCase2);
                Intrinsics.checkNotNull(obj);
                createSetBuilder.addAll((Collection) obj);
            } else {
                createSetBuilder.add(str);
            }
        }
        Object[] array = SetsKt.build(createSetBuilder).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        return (String[]) array;
    }

    public void removeObserver(Observer observer) {
        ObserverWrapper observerWrapper;
        Intrinsics.checkNotNullParameter(observer, "observer");
        synchronized (this.observerMap) {
            observerWrapper = (ObserverWrapper) this.observerMap.remove(observer);
        }
        if (observerWrapper != null) {
            ObservedTableTracker observedTableTracker2 = this.observedTableTracker;
            int[] tableIds$room_runtime_release = observerWrapper.getTableIds$room_runtime_release();
            if (observedTableTracker2.onRemoved(Arrays.copyOf(tableIds$room_runtime_release, tableIds$room_runtime_release.length))) {
                syncTriggers$room_runtime_release();
            }
        }
    }

    public final boolean ensureInitialization$room_runtime_release() {
        if (!this.database.isOpen()) {
            return false;
        }
        if (!this.initialized) {
            this.database.getOpenHelper().getWritableDatabase();
        }
        if (this.initialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public void refreshVersionsAsync() {
        if (this.pendingRefresh.compareAndSet(false, true)) {
            AutoCloser autoCloser2 = this.autoCloser;
            if (autoCloser2 != null) {
                autoCloser2.incrementCountAndEnsureDbIsOpen();
            }
            this.database.getQueryExecutor().execute(this.refreshRunnable);
        }
    }

    public final void notifyObserversByTableNames(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "tables");
        synchronized (this.observerMap) {
            try {
                for (Map.Entry entry : this.observerMap) {
                    Intrinsics.checkNotNullExpressionValue(entry, "(observer, wrapper)");
                    ObserverWrapper observerWrapper = (ObserverWrapper) entry.getValue();
                    if (!((Observer) entry.getKey()).isRemote$room_runtime_release()) {
                        observerWrapper.notifyByTableNames$room_runtime_release(strArr);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
    }

    public final void syncTriggers$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
        Lock closeLock$room_runtime_release;
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        if (!supportSQLiteDatabase.inTransaction()) {
            try {
                closeLock$room_runtime_release = this.database.getCloseLock$room_runtime_release();
                closeLock$room_runtime_release.lock();
                synchronized (this.syncTriggersLock) {
                    int[] tablesToSync = this.observedTableTracker.getTablesToSync();
                    if (tablesToSync == null) {
                        closeLock$room_runtime_release.unlock();
                        return;
                    }
                    Companion.beginTransactionInternal$room_runtime_release(supportSQLiteDatabase);
                    try {
                        int length = tablesToSync.length;
                        int i = 0;
                        int i2 = 0;
                        while (i < length) {
                            int i3 = tablesToSync[i];
                            int i4 = i2 + 1;
                            if (i3 == 1) {
                                startTrackingTable(supportSQLiteDatabase, i2);
                            } else if (i3 == 2) {
                                stopTrackingTable(supportSQLiteDatabase, i2);
                            }
                            i++;
                            i2 = i4;
                        }
                        supportSQLiteDatabase.setTransactionSuccessful();
                        supportSQLiteDatabase.endTransaction();
                        Unit unit = Unit.INSTANCE;
                        closeLock$room_runtime_release.unlock();
                    } finally {
                        supportSQLiteDatabase.endTransaction();
                    }
                }
            } catch (IllegalStateException e) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
            } catch (SQLiteException e2) {
                Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e2);
            } catch (Throwable th) {
                closeLock$room_runtime_release.unlock();
                throw th;
            }
        }
    }

    public final void syncTriggers$room_runtime_release() {
        if (this.database.isOpen()) {
            syncTriggers$room_runtime_release(this.database.getOpenHelper().getWritableDatabase());
        }
    }

    public static final class ObserverWrapper {
        private final Observer observer;
        private final Set singleTableSet;
        private final int[] tableIds;
        private final String[] tableNames;

        public ObserverWrapper(Observer observer2, int[] iArr, String[] strArr) {
            Set set;
            Intrinsics.checkNotNullParameter(observer2, "observer");
            Intrinsics.checkNotNullParameter(iArr, "tableIds");
            Intrinsics.checkNotNullParameter(strArr, "tableNames");
            this.observer = observer2;
            this.tableIds = iArr;
            this.tableNames = strArr;
            if (!(strArr.length == 0)) {
                set = SetsKt.setOf(strArr[0]);
            } else {
                set = SetsKt.emptySet();
            }
            this.singleTableSet = set;
            if (iArr.length != strArr.length) {
                throw new IllegalStateException("Check failed.");
            }
        }

        public final int[] getTableIds$room_runtime_release() {
            return this.tableIds;
        }

        public final void notifyByTableInvalidStatus$room_runtime_release(Set set) {
            Set set2;
            Intrinsics.checkNotNullParameter(set, "invalidatedTablesIds");
            int[] iArr = this.tableIds;
            int length = iArr.length;
            if (length != 0) {
                int i = 0;
                if (length != 1) {
                    Set createSetBuilder = SetsKt.createSetBuilder();
                    int[] iArr2 = this.tableIds;
                    int length2 = iArr2.length;
                    int i2 = 0;
                    while (i < length2) {
                        int i3 = i2 + 1;
                        if (set.contains(Integer.valueOf(iArr2[i]))) {
                            createSetBuilder.add(this.tableNames[i2]);
                        }
                        i++;
                        i2 = i3;
                    }
                    set2 = SetsKt.build(createSetBuilder);
                } else if (set.contains(Integer.valueOf(iArr[0]))) {
                    set2 = this.singleTableSet;
                } else {
                    set2 = SetsKt.emptySet();
                }
            } else {
                set2 = SetsKt.emptySet();
            }
            if (!set2.isEmpty()) {
                this.observer.onInvalidated(set2);
            }
        }

        public final void notifyByTableNames$room_runtime_release(String[] strArr) {
            Set set;
            Intrinsics.checkNotNullParameter(strArr, "tables");
            int length = this.tableNames.length;
            if (length != 0) {
                if (length == 1) {
                    int length2 = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length2) {
                            set = SetsKt.emptySet();
                            break;
                        } else if (StringsKt.equals(strArr[i], this.tableNames[0], true)) {
                            set = this.singleTableSet;
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    Set createSetBuilder = SetsKt.createSetBuilder();
                    for (String str : strArr) {
                        for (String str2 : this.tableNames) {
                            if (StringsKt.equals(str2, str, true)) {
                                createSetBuilder.add(str2);
                            }
                        }
                    }
                    set = SetsKt.build(createSetBuilder);
                }
            } else {
                set = SetsKt.emptySet();
            }
            if (!set.isEmpty()) {
                this.observer.onInvalidated(set);
            }
        }
    }

    public static abstract class Observer {
        private final String[] tables;

        public abstract boolean isRemote$room_runtime_release();

        public abstract void onInvalidated(Set set);

        public Observer(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "tables");
            this.tables = strArr;
        }

        public final String[] getTables$room_runtime_release() {
            return this.tables;
        }
    }

    public static final class ObservedTableTracker {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private boolean needsSync;
        private final long[] tableObservers;
        private final int[] triggerStateChanges;
        private final boolean[] triggerStates;

        public ObservedTableTracker(int i) {
            this.tableObservers = new long[i];
            this.triggerStates = new boolean[i];
            this.triggerStateChanges = new int[i];
        }

        public final boolean onAdded(int... iArr) {
            boolean z;
            Intrinsics.checkNotNullParameter(iArr, "tableIds");
            synchronized (this) {
                try {
                    z = false;
                    for (int i : iArr) {
                        long[] jArr = this.tableObservers;
                        long j = jArr[i];
                        jArr[i] = 1 + j;
                        if (j == 0) {
                            this.needsSync = true;
                            z = true;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            }
            return z;
        }

        public final boolean onRemoved(int... iArr) {
            boolean z;
            Intrinsics.checkNotNullParameter(iArr, "tableIds");
            synchronized (this) {
                try {
                    z = false;
                    for (int i : iArr) {
                        long[] jArr = this.tableObservers;
                        long j = jArr[i];
                        jArr[i] = j - 1;
                        if (j == 1) {
                            this.needsSync = true;
                            z = true;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            }
            return z;
        }

        public final void resetTriggerState() {
            synchronized (this) {
                Arrays.fill(this.triggerStates, false);
                this.needsSync = true;
                Unit unit = Unit.INSTANCE;
            }
        }

        public final int[] getTablesToSync() {
            synchronized (this) {
                try {
                    if (!this.needsSync) {
                        return null;
                    }
                    long[] jArr = this.tableObservers;
                    int length = jArr.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        int i3 = i2 + 1;
                        int i4 = 1;
                        boolean z = jArr[i] > 0;
                        boolean[] zArr = this.triggerStates;
                        if (z != zArr[i2]) {
                            int[] iArr = this.triggerStateChanges;
                            if (!z) {
                                i4 = 2;
                            }
                            iArr[i2] = i4;
                        } else {
                            this.triggerStateChanges[i2] = 0;
                        }
                        zArr[i2] = z;
                        i++;
                        i2 = i3;
                    }
                    this.needsSync = false;
                    int[] iArr2 = (int[]) this.triggerStateChanges.clone();
                    return iArr2;
                } finally {
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

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTriggerName$room_runtime_release(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "tableName");
            Intrinsics.checkNotNullParameter(str2, "triggerType");
            return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
        }

        public final void beginTransactionInternal$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
            if (supportSQLiteDatabase.isWriteAheadLoggingEnabled()) {
                supportSQLiteDatabase.beginTransactionNonExclusive();
            } else {
                supportSQLiteDatabase.beginTransaction();
            }
        }
    }
}
