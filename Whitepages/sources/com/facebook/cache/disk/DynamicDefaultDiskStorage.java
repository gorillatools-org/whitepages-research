package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileUtils;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DynamicDefaultDiskStorage implements DiskStorage {
    private static final Class TAG = DynamicDefaultDiskStorage.class;
    private final String mBaseDirectoryName;
    private final Supplier mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    volatile State mCurrentState = new State((File) null, (DiskStorage) null);
    private final int mVersion;

    static class State {
        public final DiskStorage delegate;
        public final File rootDirectory;

        State(File file, DiskStorage diskStorage) {
            this.delegate = diskStorage;
            this.rootDirectory = file;
        }
    }

    public DynamicDefaultDiskStorage(int i, Supplier supplier, String str, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = i;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = supplier;
        this.mBaseDirectoryName = str;
    }

    public boolean isExternal() {
        try {
            return get().isExternal();
        } catch (IOException unused) {
            return false;
        }
    }

    public BinaryResource getResource(String str, Object obj) {
        return get().getResource(str, obj);
    }

    public boolean contains(String str, Object obj) {
        return get().contains(str, obj);
    }

    public boolean touch(String str, Object obj) {
        return get().touch(str, obj);
    }

    public void purgeUnexpectedResources() {
        try {
            get().purgeUnexpectedResources();
        } catch (IOException e) {
            FLog.e(TAG, "purgeUnexpectedResources", (Throwable) e);
        }
    }

    public DiskStorage.Inserter insert(String str, Object obj) {
        return get().insert(str, obj);
    }

    public Collection getEntries() {
        return get().getEntries();
    }

    public long remove(DiskStorage.Entry entry) {
        return get().remove(entry);
    }

    public long remove(String str) {
        return get().remove(str);
    }

    public void clearAll() {
        get().clearAll();
    }

    /* access modifiers changed from: package-private */
    public synchronized DiskStorage get() {
        try {
            if (shouldCreateNewStorage()) {
                deleteOldStorageIfNecessary();
                createStorage();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return (DiskStorage) Preconditions.checkNotNull(this.mCurrentState.delegate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.rootDirectory;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldCreateNewStorage() {
        /*
            r2 = this;
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState
            com.facebook.cache.disk.DiskStorage r1 = r0.delegate
            if (r1 == 0) goto L_0x0013
            java.io.File r0 = r0.rootDirectory
            if (r0 == 0) goto L_0x0013
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r0 = 0
            goto L_0x0014
        L_0x0013:
            r0 = 1
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DynamicDefaultDiskStorage.shouldCreateNewStorage():boolean");
    }

    /* access modifiers changed from: package-private */
    public void deleteOldStorageIfNecessary() {
        if (this.mCurrentState.delegate != null && this.mCurrentState.rootDirectory != null) {
            FileTree.deleteRecursively(this.mCurrentState.rootDirectory);
        }
    }

    private void createStorage() {
        File file = new File((File) this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        createRootDirectoryIfNecessary(file);
        this.mCurrentState = new State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
    }

    /* access modifiers changed from: package-private */
    public void createRootDirectoryIfNecessary(File file) {
        try {
            FileUtils.mkdirs(file);
            FLog.d(TAG, "Created cache directory %s", (Object) file.getAbsolutePath());
        } catch (FileUtils.CreateDirectoryException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, "createRootDirectoryIfNecessary", e);
            throw e;
        }
    }
}
