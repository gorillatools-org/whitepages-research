package com.facebook.common.statfs;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StatFsHelper {
    private static final long RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    private static StatFsHelper sStatsFsHelper;
    private final Lock lock = new ReentrantLock();
    private volatile File mExternalPath;
    private volatile StatFs mExternalStatFs = null;
    private volatile boolean mInitialized = false;
    private volatile File mInternalPath;
    private volatile StatFs mInternalStatFs = null;
    private long mLastRestatTime;

    public enum StorageType {
        INTERNAL,
        EXTERNAL
    }

    public static synchronized StatFsHelper getInstance() {
        StatFsHelper statFsHelper;
        synchronized (StatFsHelper.class) {
            try {
                if (sStatsFsHelper == null) {
                    sStatsFsHelper = new StatFsHelper();
                }
                statFsHelper = sStatsFsHelper;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return statFsHelper;
    }

    protected StatFsHelper() {
    }

    private void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean testLowDiskSpace(StorageType storageType, long j) {
        ensureInitialized();
        long availableStorageSpace = getAvailableStorageSpace(storageType);
        return availableStorageSpace <= 0 || availableStorageSpace < j;
    }

    public long getAvailableStorageSpace(StorageType storageType) {
        ensureInitialized();
        maybeUpdateStats();
        StatFs statFs = storageType == StorageType.INTERNAL ? this.mInternalStatFs : this.mExternalStatFs;
        if (statFs != null) {
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        }
        return 0;
    }

    private void maybeUpdateStats() {
        if (this.lock.tryLock()) {
            try {
                if (SystemClock.uptimeMillis() - this.mLastRestatTime > RESTAT_INTERVAL_MS) {
                    updateStats();
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    private void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }

    private StatFs updateStatsHelper(StatFs statFs, File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        if (statFs == null) {
            try {
                statFs = createStatFs(file.getAbsolutePath());
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Throwable th) {
                throw Throwables.propagate(th);
            }
        } else {
            statFs.restat(file.getAbsolutePath());
        }
        return statFs;
    }

    protected static StatFs createStatFs(String str) {
        return new StatFs(str);
    }
}
