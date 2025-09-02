package androidx.sqlite.util;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProcessLock {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Map threadLocksMap = new HashMap();
    private FileChannel lockChannel;
    private final File lockFile;
    private final boolean processLock;
    private final Lock threadLock;

    public ProcessLock(String str, File file, boolean z) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(file, "lockDir");
        this.processLock = z;
        File file2 = new File(file, str + ".lck");
        this.lockFile = file2;
        Companion companion = Companion;
        String absolutePath = file2.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "lockFile.absolutePath");
        this.threadLock = companion.getThreadLock(absolutePath);
    }

    public static /* synthetic */ void lock$default(ProcessLock processLock2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = processLock2.processLock;
        }
        processLock2.lock(z);
    }

    public final void lock(boolean z) {
        this.threadLock.lock();
        if (z) {
            try {
                File parentFile = this.lockFile.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileChannel channel = new FileOutputStream(this.lockFile).getChannel();
                channel.lock();
                this.lockChannel = channel;
            } catch (IOException e) {
                this.lockChannel = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", e);
            }
        }
    }

    public final void unlock() {
        try {
            FileChannel fileChannel = this.lockChannel;
            if (fileChannel != null) {
                fileChannel.close();
            }
        } catch (IOException unused) {
        }
        this.threadLock.unlock();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Lock getThreadLock(String str) {
            Lock lock;
            synchronized (ProcessLock.threadLocksMap) {
                try {
                    Map access$getThreadLocksMap$cp = ProcessLock.threadLocksMap;
                    Object obj = access$getThreadLocksMap$cp.get(str);
                    if (obj == null) {
                        obj = new ReentrantLock();
                        access$getThreadLocksMap$cp.put(str, obj);
                    }
                    lock = (Lock) obj;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return lock;
        }
    }
}
