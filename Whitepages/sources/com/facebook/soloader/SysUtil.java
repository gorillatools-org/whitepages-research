package com.facebook.soloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

public abstract class SysUtil {
    public static int findAbiScore(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (str2 != null && str.equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            LogUtil.e("SysUtil", "Enable write permission failed: " + parentFile);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        return MarshmallowSysdeps.getSupportedAbis();
    }

    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) {
        LollipopSysdeps.fallocateIfSupported(fileDescriptor, j);
    }

    public static void dumbDelete(File file) {
        Stack stack = new Stack();
        stack.push(file);
        ArrayList arrayList = new ArrayList();
        while (!stack.isEmpty()) {
            File file2 = (File) stack.pop();
            if (!file2.isDirectory()) {
                deleteOrThrow(file2);
            } else {
                arrayList.add(file2);
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File push : listFiles) {
                        stack.push(push);
                    }
                }
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            deleteOrThrow((File) arrayList.get(size));
        }
    }

    @DoNotOptimize
    @TargetApi(21)
    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            try {
                if (is64Bit()) {
                    treeSet.add("arm64-v8a");
                    treeSet.add("x86_64");
                } else {
                    treeSet.add("armeabi-v7a");
                    treeSet.add("x86");
                }
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (treeSet.contains(str)) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            } catch (ErrnoException e) {
                LogUtil.e("SysUtil", String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", new Object[]{Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()}));
                return Build.SUPPORTED_ABIS;
            }
        }

        @DoNotOptimize
        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
            int i;
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (ErrnoException e) {
                if (e.errno != OsConstants.EOPNOTSUPP && (i = e.errno) != OsConstants.ENOSYS && i != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }

        @DoNotOptimize
        public static boolean is64Bit() throws ErrnoException {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    @DoNotOptimize
    @TargetApi(23)
    private static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            if (is64Bit()) {
                treeSet.add("arm64-v8a");
                treeSet.add("x86_64");
            } else {
                treeSet.add("armeabi-v7a");
                treeSet.add("x86");
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @DoNotOptimize
        public static boolean is64Bit() {
            return Process.is64Bit();
        }

        public static boolean isSupportedDirectLoad(Context context, int i) {
            if (i == 2) {
                return true;
            }
            return isDisabledExtractNativeLibs(context);
        }

        public static boolean isDisabledExtractNativeLibs(Context context) {
            return context != null && (context.getApplicationInfo().flags & 268435456) == 0;
        }
    }

    public static void mkdirOrThrow(File file) {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    static int copyBytes(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i - i2));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    public static void fsyncAll(File file) {
        RandomAccessFile randomAccessFile;
        Stack stack = new Stack();
        stack.push(file);
        while (!stack.isEmpty()) {
            File file2 = (File) stack.pop();
            if (file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File push : listFiles) {
                        stack.push(push);
                    }
                } else {
                    throw new IOException("cannot list directory " + file2);
                }
            } else if (!file2.getPath().endsWith("_lock")) {
                try {
                    randomAccessFile = new RandomAccessFile(file2, "r");
                    randomAccessFile.getFD().sync();
                    randomAccessFile.close();
                } catch (IOException e) {
                    LogUtil.e("SysUtil", "Syncing failed for " + file2 + ": " + e.getMessage());
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                continue;
            }
        }
        return;
        throw th;
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }

    public static boolean is64Bit() {
        return MarshmallowSysdeps.is64Bit();
    }

    public static boolean isSupportedDirectLoad(Context context, int i) {
        return MarshmallowSysdeps.isSupportedDirectLoad(context, i);
    }

    public static FileLocker getOrCreateLockOnDir(File file, File file2) {
        boolean z;
        try {
            return getFileLocker(file2);
        } catch (FileNotFoundException e) {
            z = true;
            if (file.setWritable(true)) {
                FileLocker fileLocker = getFileLocker(file2);
                if (!file.setWritable(false)) {
                    LogUtil.w("SysUtil", "error removing " + file.getCanonicalPath() + " write permission");
                }
                return fileLocker;
            }
            throw e;
        } catch (Throwable th) {
            th = th;
        }
        if (z && !file.setWritable(false)) {
            LogUtil.w("SysUtil", "error removing " + file.getCanonicalPath() + " write permission");
        }
        throw th;
    }

    public static FileLocker getFileLocker(File file) {
        return FileLocker.lock(file);
    }

    @DoNotOptimize
    public static String getClassLoaderLdLoadLibrary() {
        ClassLoader classLoader = SoLoader.class.getClassLoader();
        if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", (Class[]) null).invoke((BaseDexClassLoader) classLoader, (Object[]) null);
            } catch (Exception e) {
                LogUtil.e("SysUtil", "Cannot call getLdLibraryPath", e);
                return null;
            }
        } else {
            throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
        }
    }

    @DoNotOptimize
    public static Method getNativeLoadRuntimeMethod() {
        Class<String> cls = String.class;
        if (Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{cls, ClassLoader.class, cls});
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception e) {
            LogUtil.w("SysUtil", "Cannot get nativeLoad method", e);
            return null;
        }
    }
}
