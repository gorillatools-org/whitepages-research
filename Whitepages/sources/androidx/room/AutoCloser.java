package androidx.room;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AutoCloser {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private long autoCloseTimeoutInMs;
    private final Runnable autoCloser;
    private SupportSQLiteDatabase delegateDatabase;
    public SupportSQLiteOpenHelper delegateOpenHelper;
    private final Runnable executeAutoCloser;
    private final Executor executor;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long lastDecrementRefCountTimeStamp;
    private final Object lock = new Object();
    private boolean manuallyClosed;
    private Runnable onAutoCloseCallback;
    private int refCount;

    public AutoCloser(long j, TimeUnit timeUnit, Executor executor2) {
        Intrinsics.checkNotNullParameter(timeUnit, "autoCloseTimeUnit");
        Intrinsics.checkNotNullParameter(executor2, "autoCloseExecutor");
        this.autoCloseTimeoutInMs = timeUnit.toMillis(j);
        this.executor = executor2;
        this.lastDecrementRefCountTimeStamp = SystemClock.uptimeMillis();
        this.executeAutoCloser = new AutoCloser$$ExternalSyntheticLambda0(this);
        this.autoCloser = new AutoCloser$$ExternalSyntheticLambda1(this);
    }

    public final SupportSQLiteOpenHelper getDelegateOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.delegateOpenHelper;
        if (supportSQLiteOpenHelper != null) {
            return supportSQLiteOpenHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delegateOpenHelper");
        return null;
    }

    public final void setDelegateOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper, "<set-?>");
        this.delegateOpenHelper = supportSQLiteOpenHelper;
    }

    public final SupportSQLiteDatabase getDelegateDatabase$room_runtime_release() {
        return this.delegateDatabase;
    }

    /* access modifiers changed from: private */
    public static final void executeAutoCloser$lambda$0(AutoCloser autoCloser2) {
        Intrinsics.checkNotNullParameter(autoCloser2, "this$0");
        autoCloser2.executor.execute(autoCloser2.autoCloser);
    }

    /* access modifiers changed from: private */
    public static final void autoCloser$lambda$3(AutoCloser autoCloser2) {
        Unit unit;
        Intrinsics.checkNotNullParameter(autoCloser2, "this$0");
        synchronized (autoCloser2.lock) {
            try {
                if (SystemClock.uptimeMillis() - autoCloser2.lastDecrementRefCountTimeStamp >= autoCloser2.autoCloseTimeoutInMs) {
                    if (autoCloser2.refCount == 0) {
                        Runnable runnable = autoCloser2.onAutoCloseCallback;
                        if (runnable != null) {
                            runnable.run();
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit != null) {
                            SupportSQLiteDatabase supportSQLiteDatabase = autoCloser2.delegateDatabase;
                            if (supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen()) {
                                supportSQLiteDatabase.close();
                            }
                            autoCloser2.delegateDatabase = null;
                            Unit unit2 = Unit.INSTANCE;
                            return;
                        }
                        throw new IllegalStateException("onAutoCloseCallback is null but it should have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void init(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper, "delegateOpenHelper");
        setDelegateOpenHelper(supportSQLiteOpenHelper);
    }

    public final Object executeRefCountingFunction(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        try {
            return function1.invoke(incrementCountAndEnsureDbIsOpen());
        } finally {
            decrementCountAndScheduleClose();
        }
    }

    public final SupportSQLiteDatabase incrementCountAndEnsureDbIsOpen() {
        synchronized (this.lock) {
            this.handler.removeCallbacks(this.executeAutoCloser);
            this.refCount++;
            if (!this.manuallyClosed) {
                SupportSQLiteDatabase supportSQLiteDatabase = this.delegateDatabase;
                if (supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen()) {
                    return supportSQLiteDatabase;
                }
                SupportSQLiteDatabase writableDatabase = getDelegateOpenHelper().getWritableDatabase();
                this.delegateDatabase = writableDatabase;
                return writableDatabase;
            }
            throw new IllegalStateException("Attempting to open already closed database.");
        }
    }

    public final void decrementCountAndScheduleClose() {
        synchronized (this.lock) {
            try {
                int i = this.refCount;
                if (i > 0) {
                    int i2 = i - 1;
                    this.refCount = i2;
                    if (i2 == 0) {
                        if (this.delegateDatabase != null) {
                            this.handler.postDelayed(this.executeAutoCloser, this.autoCloseTimeoutInMs);
                        } else {
                            return;
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                throw new IllegalStateException("ref count is 0 or lower but we're supposed to decrement");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void closeDatabaseIfOpen() {
        synchronized (this.lock) {
            try {
                this.manuallyClosed = true;
                SupportSQLiteDatabase supportSQLiteDatabase = this.delegateDatabase;
                if (supportSQLiteDatabase != null) {
                    supportSQLiteDatabase.close();
                }
                this.delegateDatabase = null;
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isActive() {
        return !this.manuallyClosed;
    }

    public final void setAutoCloseCallback(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "onAutoClose");
        this.onAutoCloseCallback = runnable;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
