package com.facebook.soloader;

import android.os.StrictMode;
import com.facebook.soloader.MinElf;
import com.facebook.soloader.observer.ObserverHolder;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class NativeDeps {
    private static final int LIB_PREFIX_LEN = 3;
    private static final int LIB_PREFIX_SUFFIX_LEN = (3 + 3);
    private static final int LIB_SUFFIX_LEN = 3;
    private static final HashSet STANDARD_SYSTEM_LIBS = new HashSet() {
        {
            add("libEGL.so");
            add("libGLESv2.so");
            add("libGLESv3.so");
            add("libOpenSLES.so");
            add("libandroid.so");
            add("libc.so");
            add("libdl.so");
            add("libjnigraphics.so");
            add("liblog.so");
            add("libm.so");
            add("libstdc++.so");
            add("libz.so");
        }
    };
    private static byte[] sEncodedDeps = null;
    private static volatile boolean sInitialized = false;
    private static Map sPrecomputedDeps = null;
    private static List sPrecomputedLibs = null;
    private static volatile boolean sUseDepsFileAsync = false;
    private static final ReentrantReadWriteLock sWaitForDepsFileLock = new ReentrantReadWriteLock();

    public static void loadDependencies(String str, ElfByteChannel elfByteChannel, int i, StrictMode.ThreadPolicy threadPolicy) {
        String[] dependencies = getDependencies(str, elfByteChannel);
        LogUtil.d("SoLoader", "Loading " + str + "'s dependencies: " + Arrays.toString(dependencies));
        for (String str2 : dependencies) {
            if (!str2.startsWith(RemoteSettings.FORWARD_SLASH_STRING) && !STANDARD_SYSTEM_LIBS.contains(str2)) {
                SoLoader.loadDependency(str2, i, threadPolicy);
            }
        }
    }

    public static String[] getDependencies(String str, ElfByteChannel elfByteChannel) {
        Throwable th;
        boolean z = SoLoader.SYSTRACE_LIBRARY_LOADING;
        if (z) {
            Api18TraceUtils.beginTraceSection("soloader.NativeDeps.getDependencies[", str, "]");
        }
        ObserverHolder.onGetDependenciesStart();
        try {
            String[] awaitGetDepsFromPrecomputedDeps = awaitGetDepsFromPrecomputedDeps(str);
            if (awaitGetDepsFromPrecomputedDeps != null) {
                ObserverHolder.onGetDependenciesEnd((Throwable) null);
                if (z) {
                    Api18TraceUtils.endSection();
                }
                return awaitGetDepsFromPrecomputedDeps;
            }
            String[] extract_DT_NEEDED = MinElf.extract_DT_NEEDED(elfByteChannel);
            ObserverHolder.onGetDependenciesEnd((Throwable) null);
            if (z) {
                Api18TraceUtils.endSection();
            }
            return extract_DT_NEEDED;
        } catch (MinElf.ElfError e) {
            throw SoLoaderULErrorFactory.create(str, e);
        } catch (Error | RuntimeException e2) {
            th = e2;
            throw th;
        } catch (Throwable th2) {
            ObserverHolder.onGetDependenciesEnd(th);
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Api18TraceUtils.endSection();
            }
            throw th2;
        }
    }

    private static String[] awaitGetDepsFromPrecomputedDeps(String str) {
        if (sInitialized) {
            return tryGetDepsFromPrecomputedDeps(str);
        }
        if (!sUseDepsFileAsync) {
            return null;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sWaitForDepsFileLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            String[] tryGetDepsFromPrecomputedDeps = tryGetDepsFromPrecomputedDeps(str);
            reentrantReadWriteLock.readLock().unlock();
            return tryGetDepsFromPrecomputedDeps;
        } catch (Throwable th) {
            sWaitForDepsFileLock.readLock().unlock();
            throw th;
        }
    }

    private static boolean libIsAtOffset(String str, int i) {
        int i2;
        int i3 = LIB_PREFIX_LEN;
        while (true) {
            int length = str.length();
            i2 = LIB_SUFFIX_LEN;
            if (i3 < length - i2 && i < sEncodedDeps.length && (str.codePointAt(i3) & 255) == sEncodedDeps[i]) {
                i3++;
                i++;
            }
        }
        return i3 == str.length() - i2;
    }

    private static int hashLib(String str) {
        int i = 5381;
        for (int i2 = LIB_PREFIX_LEN; i2 < str.length() - LIB_SUFFIX_LEN; i2++) {
            i = str.codePointAt(i2) + (i << 5) + i;
        }
        return i;
    }

    private static int getOffsetForLib(String str) {
        List<Integer> list = (List) sPrecomputedDeps.get(Integer.valueOf(hashLib(str)));
        if (list == null) {
            return -1;
        }
        for (Integer intValue : list) {
            int intValue2 = intValue.intValue();
            if (libIsAtOffset(str, intValue2)) {
                return intValue2;
            }
        }
        return -1;
    }

    private static String getLibString(int i) {
        if (i >= sPrecomputedLibs.size()) {
            return null;
        }
        int intValue = ((Integer) sPrecomputedLibs.get(i)).intValue();
        int i2 = intValue;
        while (true) {
            byte[] bArr = sEncodedDeps;
            if (i2 >= bArr.length || bArr[i2] <= 32) {
                int i3 = (i2 - intValue) + LIB_PREFIX_SUFFIX_LEN;
                char[] cArr = new char[i3];
                cArr[0] = 'l';
                cArr[1] = 'i';
                cArr[2] = 'b';
            } else {
                i2++;
            }
        }
        int i32 = (i2 - intValue) + LIB_PREFIX_SUFFIX_LEN;
        char[] cArr2 = new char[i32];
        cArr2[0] = 'l';
        cArr2[1] = 'i';
        cArr2[2] = 'b';
        for (int i4 = 0; i4 < i32 - LIB_PREFIX_SUFFIX_LEN; i4++) {
            cArr2[LIB_PREFIX_LEN + i4] = (char) sEncodedDeps[intValue + i4];
        }
        cArr2[i32 - 3] = '.';
        cArr2[i32 - 2] = 's';
        cArr2[i32 - 1] = 'o';
        return new String(cArr2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] getDepsForLibAtOffset(int r6, int r7) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r6 = r6 + r7
            int r7 = LIB_PREFIX_SUFFIX_LEN
            int r6 = r6 - r7
            r7 = 0
            r1 = r7
            r2 = r1
        L_0x000c:
            byte[] r3 = sEncodedDeps
            int r4 = r3.length
            r5 = 0
            if (r6 >= r4) goto L_0x003e
            byte r3 = r3[r6]
            r4 = 10
            if (r3 == r4) goto L_0x003e
            r4 = 32
            if (r3 != r4) goto L_0x002b
            if (r2 == 0) goto L_0x003a
            java.lang.String r1 = getLibString(r1)
            if (r1 != 0) goto L_0x0025
            return r5
        L_0x0025:
            r0.add(r1)
            r1 = r7
            r2 = r1
            goto L_0x003a
        L_0x002b:
            r2 = 48
            if (r3 < r2) goto L_0x003d
            r2 = 57
            if (r3 <= r2) goto L_0x0034
            goto L_0x003d
        L_0x0034:
            int r1 = r1 * 10
            int r3 = r3 + -48
            int r1 = r1 + r3
            r2 = 1
        L_0x003a:
            int r6 = r6 + 1
            goto L_0x000c
        L_0x003d:
            return r5
        L_0x003e:
            if (r2 == 0) goto L_0x004a
            java.lang.String r6 = getLibString(r1)
            if (r6 != 0) goto L_0x0047
            return r5
        L_0x0047:
            r0.add(r6)
        L_0x004a:
            boolean r6 = r0.isEmpty()
            if (r6 == 0) goto L_0x0051
            return r5
        L_0x0051:
            int r6 = r0.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r6 = r0.toArray(r6)
            java.lang.String[] r6 = (java.lang.String[]) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.NativeDeps.getDepsForLibAtOffset(int, int):java.lang.String[]");
    }

    static String[] tryGetDepsFromPrecomputedDeps(String str) {
        int offsetForLib;
        if (sInitialized && str.length() > LIB_PREFIX_SUFFIX_LEN && (offsetForLib = getOffsetForLib(str)) != -1) {
            return getDepsForLibAtOffset(offsetForLib, str.length());
        }
        return null;
    }
}
