package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.concurrent.futures.ResolvableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public abstract class ProfileVerifier {
    private static final Object SYNC_OBJ = new Object();
    private static CompilationStatus sCompilationStatus = null;
    private static final ResolvableFuture sFuture = ResolvableFuture.create();

    static CompilationStatus writeProfileVerification(Context context, boolean z) {
        boolean z2;
        boolean z3;
        Cache cache;
        Cache cache2;
        CompilationStatus compilationStatus;
        if (!z && (compilationStatus = sCompilationStatus) != null) {
            return compilationStatus;
        }
        synchronized (SYNC_OBJ) {
            if (!z) {
                try {
                    CompilationStatus compilationStatus2 = sCompilationStatus;
                    if (compilationStatus2 != null) {
                        return compilationStatus2;
                    }
                } catch (IOException unused) {
                    return setCompilationStatus(131072, z2, z3);
                } catch (Throwable th) {
                    throw th;
                }
            }
            int i = Build.VERSION.SDK_INT;
            int i2 = 0;
            if (i >= 28) {
                if (i != 30) {
                    File file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length = file.length();
                    z2 = file.exists() && length > 0;
                    File file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    long length2 = file2.length();
                    z3 = file2.exists() && length2 > 0;
                    try {
                        long packageLastUpdateTime = getPackageLastUpdateTime(context);
                        File file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            cache = Cache.readFromFile(file3);
                        } else {
                            cache = null;
                        }
                        if (cache != null && cache.mPackageLastUpdateTime == packageLastUpdateTime) {
                            int i3 = cache.mResultCode;
                            if (i3 != 2) {
                                i2 = i3;
                                if (z && z3 && i2 != 1) {
                                    i2 = 2;
                                }
                                if (cache != null && cache.mResultCode == 2 && i2 == 1 && length < cache.mInstalledCurrentProfileSize) {
                                    i2 = 3;
                                }
                                cache2 = new Cache(1, i2, packageLastUpdateTime, length2);
                                if (cache == null || !cache.equals(cache2)) {
                                    cache2.writeOnFile(file3);
                                }
                                CompilationStatus compilationStatus3 = setCompilationStatus(i2, z2, z3);
                                return compilationStatus3;
                            }
                        }
                        if (z2) {
                            i2 = 1;
                        } else if (z3) {
                            i2 = 2;
                        }
                        i2 = 2;
                        i2 = 3;
                        cache2 = new Cache(1, i2, packageLastUpdateTime, length2);
                        try {
                            cache2.writeOnFile(file3);
                        } catch (IOException unused2) {
                            i2 = 196608;
                        }
                        CompilationStatus compilationStatus32 = setCompilationStatus(i2, z2, z3);
                        return compilationStatus32;
                    } catch (PackageManager.NameNotFoundException unused3) {
                        return setCompilationStatus(65536, z2, z3);
                    }
                }
            }
            CompilationStatus compilationStatus4 = setCompilationStatus(262144, false, false);
            return compilationStatus4;
        }
    }

    private static CompilationStatus setCompilationStatus(int i, boolean z, boolean z2) {
        CompilationStatus compilationStatus = new CompilationStatus(i, z, z2);
        sCompilationStatus = compilationStatus;
        sFuture.set(compilationStatus);
        return sCompilationStatus;
    }

    private static long getPackageLastUpdateTime(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime;
        }
        return packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    static class Cache {
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        Cache(int i, int i2, long j, long j2) {
            this.mSchema = i;
            this.mResultCode = i2;
            this.mPackageLastUpdateTime = j;
            this.mInstalledCurrentProfileSize = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Cache)) {
                return false;
            }
            Cache cache = (Cache) obj;
            if (this.mResultCode == cache.mResultCode && this.mPackageLastUpdateTime == cache.mPackageLastUpdateTime && this.mSchema == cache.mSchema && this.mInstalledCurrentProfileSize == cache.mInstalledCurrentProfileSize) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize)});
        }

        /* access modifiers changed from: package-private */
        public void writeOnFile(File file) {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.mSchema);
                dataOutputStream.writeInt(this.mResultCode);
                dataOutputStream.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream.writeLong(this.mInstalledCurrentProfileSize);
                dataOutputStream.close();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        static Cache readFromFile(File file) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }
    }

    public static class CompilationStatus {
        private final boolean mHasCurrentProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        CompilationStatus(int i, boolean z, boolean z2) {
            this.mResultCode = i;
            this.mHasCurrentProfile = z2;
            this.mHasReferenceProfile = z;
        }
    }

    private static class Api33Impl {
        static PackageInfo getPackageInfo(PackageManager packageManager, Context context) throws PackageManager.NameNotFoundException {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0));
        }
    }
}
