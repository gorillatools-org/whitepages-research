package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public final class FileLocker implements Closeable {
    private final FileLock mLock;
    private final FileOutputStream mLockFileOutputStream;

    public static FileLocker lock(File file) {
        return new FileLocker(file);
    }

    private FileLocker(File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        this.mLockFileOutputStream = fileOutputStream;
        try {
            FileLock lock = fileOutputStream.getChannel().lock();
            if (lock == null) {
                fileOutputStream.close();
            }
            this.mLock = lock;
        } catch (Throwable th) {
            this.mLockFileOutputStream.close();
            throw th;
        }
    }

    public void close() {
        try {
            FileLock fileLock = this.mLock;
            if (fileLock != null) {
                fileLock.release();
            }
        } finally {
            this.mLockFileOutputStream.close();
        }
    }
}
