package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;
import com.facebook.hermes.intl.Constants;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import com.facebook.soloader.recovery.DefaultRecoveryStrategyFactory;
import com.facebook.soloader.recovery.RecoveryStrategy;
import com.facebook.soloader.recovery.RecoveryStrategyFactory;
import com.salesforce.marketingcloud.b;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SoLoader {
    static final boolean SYSTRACE_LIBRARY_LOADING = true;
    private static ExternalSoMapping externalSoMapping = null;
    private static boolean isEnabled = true;
    private static int sAppType = 0;
    static Context sApplicationContext = null;
    private static int sFlags;
    private static final Map sInvokingJniForLibrary = new HashMap();
    private static final Set sLoadedAndJniInvoked = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final Set sLoadedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final Map sLoadingLibraries = new HashMap();
    private static RecoveryStrategyFactory sRecoveryStrategyFactory = null;
    static SoFileLoader sSoFileLoader;
    private static volatile SoSource[] sSoSources = null;
    private static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    private static final AtomicInteger sSoSourcesVersion = new AtomicInteger(0);

    private static int makeRecoveryFlags(int i) {
        return (i & b.u) != 0 ? 1 : 0;
    }

    public static void init(Context context, int i) throws IOException {
        init(context, i, (SoFileLoader) null);
    }

    public static void init(Context context, int i, SoFileLoader soFileLoader) {
        if (isInitialized()) {
            LogUtil.w("SoLoader", "SoLoader already initialized");
            return;
        }
        LogUtil.w("SoLoader", "Initializing SoLoader: " + i);
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            boolean initEnableConfig = initEnableConfig(context);
            isEnabled = initEnableConfig;
            if (initEnableConfig) {
                int appType = getAppType(context);
                sAppType = appType;
                if ((i & 128) == 0 && SysUtil.isSupportedDirectLoad(context, appType)) {
                    i |= 8;
                }
                initSoLoader(context, soFileLoader, i);
                initSoSources(context, i);
                LogUtil.v("SoLoader", "Init SoLoader delegate");
                NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
            } else {
                initDummySoSource();
                LogUtil.v("SoLoader", "Init System Loader delegate");
                NativeLoader.initIfUninitialized(new SystemDelegate());
            }
            LogUtil.w("SoLoader", "SoLoader initialized: " + i);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0, (SoFileLoader) null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init(Context context, ExternalSoMapping externalSoMapping2) {
        synchronized (SoLoader.class) {
            externalSoMapping = externalSoMapping2;
        }
        init(context, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean initEnableConfig(android.content.Context r5) {
        /*
            com.facebook.soloader.ExternalSoMapping r0 = externalSoMapping
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = 0
            java.lang.String r2 = r5.getPackageName()     // Catch:{ Exception -> 0x001a }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ Exception -> 0x0018 }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo(r2, r3)     // Catch:{ Exception -> 0x0018 }
            android.os.Bundle r0 = r5.metaData     // Catch:{ Exception -> 0x0018 }
            goto L_0x0037
        L_0x0018:
            r5 = move-exception
            goto L_0x001c
        L_0x001a:
            r5 = move-exception
            r2 = r0
        L_0x001c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected issue with package manager ("
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = ")"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = "SoLoader"
            com.facebook.soloader.LogUtil.w(r3, r2, r5)
        L_0x0037:
            if (r0 == 0) goto L_0x0043
            java.lang.String r5 = "com.facebook.soloader.enabled"
            boolean r5 = r0.getBoolean(r5, r1)
            if (r5 == 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            r1 = 0
        L_0x0043:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.initEnableConfig(android.content.Context):boolean");
    }

    private static void initSoSources(Context context, int i) {
        if (sSoSources == null) {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (sSoSources != null) {
                    reentrantReadWriteLock.writeLock().unlock();
                    return;
                }
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                boolean z = true;
                boolean z2 = (i & 512) != 0;
                boolean z3 = (i & 1024) != 0;
                if (z2) {
                    addSystemLoadWrapperSoSource(context, arrayList);
                } else if (z3) {
                    addSystemLibSoSource(arrayList);
                    arrayList.add(0, new DirectSplitSoSource(Constants.SENSITIVITY_BASE));
                } else {
                    addSystemLibSoSource(arrayList);
                    if (context != null) {
                        if ((i & 1) != 0) {
                            addApplicationSoSource(arrayList, getApplicationSoSourceFlags());
                            LogUtil.d("SoLoader", "Adding exo package source: lib-main");
                            arrayList.add(0, new ExoSoSource(context, "lib-main"));
                        } else {
                            if (SysUtil.isSupportedDirectLoad(context, sAppType)) {
                                addDirectApkSoSource(context, arrayList);
                            }
                            addApplicationSoSource(arrayList, getApplicationSoSourceFlags());
                            if ((i & b.v) == 0) {
                                z = false;
                            }
                            addBackupSoSource(context, arrayList, z);
                        }
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                int makePrepareFlags = makePrepareFlags();
                int length = soSourceArr.length;
                while (true) {
                    int i2 = length - 1;
                    if (length > 0) {
                        LogUtil.i("SoLoader", "Preparing SO source: " + soSourceArr[i2]);
                        boolean z4 = SYSTRACE_LIBRARY_LOADING;
                        if (z4) {
                            Api18TraceUtils.beginTraceSection("SoLoader", "_", soSourceArr[i2].getClass().getSimpleName());
                        }
                        soSourceArr[i2].prepare(makePrepareFlags);
                        if (z4) {
                            Api18TraceUtils.endSection();
                        }
                        length = i2;
                    } else {
                        sSoSources = soSourceArr;
                        sSoSourcesVersion.getAndIncrement();
                        LogUtil.i("SoLoader", "init finish: " + sSoSources.length + " SO sources prepared");
                        sSoSourcesLock.writeLock().unlock();
                        return;
                    }
                }
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }
    }

    private static void initDummySoSource() {
        if (sSoSources == null) {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (sSoSources != null) {
                    reentrantReadWriteLock.writeLock().unlock();
                    return;
                }
                sSoSources = new SoSource[0];
                reentrantReadWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }
    }

    private static int getApplicationSoSourceFlags() {
        int i = sAppType;
        if (i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        throw new RuntimeException("Unsupported app type, we should not reach here");
    }

    private static void addDirectApkSoSource(Context context, ArrayList arrayList) {
        DirectApkSoSource directApkSoSource = new DirectApkSoSource(context);
        LogUtil.d("SoLoader", "validating/adding directApk source: " + directApkSoSource.toString());
        if (directApkSoSource.isValid()) {
            arrayList.add(0, directApkSoSource);
        }
    }

    private static void addApplicationSoSource(ArrayList arrayList, int i) {
        ApplicationSoSource applicationSoSource = new ApplicationSoSource(sApplicationContext, i);
        LogUtil.d("SoLoader", "Adding application source: " + applicationSoSource.toString());
        arrayList.add(0, applicationSoSource);
    }

    private static void addBackupSoSource(Context context, ArrayList arrayList, boolean z) {
        if ((sFlags & 8) == 0) {
            arrayList.add(0, new BackupSoSource(context, "lib-main", !z));
        }
    }

    private static void addSystemLibSoSource(ArrayList arrayList) {
        String str = SysUtil.is64Bit() ? "/system/lib64:/vendor/lib64" : "/system/lib:/vendor/lib";
        String str2 = System.getenv("LD_LIBRARY_PATH");
        if (str2 != null && !str2.equals("")) {
            str = str2 + ":" + str;
        }
        for (String str3 : new HashSet(Arrays.asList(str.split(":")))) {
            LogUtil.d("SoLoader", "adding system library source: " + str3);
            arrayList.add(new DirectorySoSource(new File(str3), 2));
        }
    }

    private static void addSystemLoadWrapperSoSource(Context context, ArrayList arrayList) {
        SystemLoadWrapperSoSource systemLoadWrapperSoSource = new SystemLoadWrapperSoSource();
        LogUtil.d("SoLoader", "adding systemLoadWrapper source: " + systemLoadWrapperSoSource);
        arrayList.add(0, systemLoadWrapperSoSource);
    }

    private static int makePrepareFlags() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            int i = sFlags;
            int i2 = (i & 2) != 0 ? 1 : 0;
            if ((i & 256) != 0) {
                i2 |= 4;
            }
            if ((i & 128) == 0) {
                i2 |= 8;
            }
            reentrantReadWriteLock.writeLock().unlock();
            return i2;
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    private static synchronized void initSoLoader(Context context, SoFileLoader soFileLoader, int i) {
        synchronized (SoLoader.class) {
            if (context != null) {
                try {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext == null) {
                        LogUtil.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.ApplicationSoSource fallbacks to: " + context.getApplicationInfo().nativeLibraryDir);
                    } else {
                        context = applicationContext;
                    }
                    sApplicationContext = context;
                    sRecoveryStrategyFactory = new DefaultRecoveryStrategyFactory(context, makeRecoveryFlags(i));
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (soFileLoader == null && sSoFileLoader != null) {
                return;
            }
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
            } else {
                sSoFileLoader = new InstrumentedSoFileLoader(new SoFileLoaderImpl());
            }
        }
    }

    private static int getAppType(Context context) {
        int i = sAppType;
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        if (context == null) {
            LogUtil.d("SoLoader", "context is null, fallback to THIRD_PARTY_APP appType");
            return 1;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i3 = applicationInfo.flags;
        if ((i3 & 1) != 0) {
            i2 = (i3 & 128) != 0 ? 3 : 2;
        }
        LogUtil.d("SoLoader", "ApplicationInfo.flags is: " + applicationInfo.flags + " appType is: " + i2);
        return i2;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    public static boolean loadLibrary(String str) {
        return isEnabled ? loadLibrary(str, 0) : NativeLoader.loadLibrary(str);
    }

    public static boolean loadLibrary(String str, int i) {
        Boolean loadLibraryOnNonAndroid = loadLibraryOnNonAndroid(str);
        if (loadLibraryOnNonAndroid != null) {
            return loadLibraryOnNonAndroid.booleanValue();
        }
        if (!isEnabled) {
            return NativeLoader.loadLibrary(str);
        }
        if (sAppType != 2) {
        }
        return loadLibraryOnAndroid(str, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        com.facebook.soloader.observer.ObserverHolder.onLoadLibraryEnd(r3, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryOnAndroid(java.lang.String r3, int r4) {
        /*
            com.facebook.soloader.ExternalSoMapping r0 = externalSoMapping
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = r0.mapLibName(r3)
            goto L_0x000d
        L_0x0009:
            java.lang.String r0 = com.facebook.soloader.MergedSoMapping.mapLibName(r3)
        L_0x000d:
            if (r0 == 0) goto L_0x0011
            r1 = r0
            goto L_0x0012
        L_0x0011:
            r1 = r3
        L_0x0012:
            com.facebook.soloader.observer.ObserverHolder.onLoadLibraryStart(r3, r0, r4)
            java.lang.String r1 = java.lang.System.mapLibraryName(r1)     // Catch:{ all -> 0x0022 }
            r2 = 0
            boolean r3 = loadLibraryBySoName(r1, r3, r0, r4, r2)     // Catch:{ all -> 0x0022 }
            com.facebook.soloader.observer.ObserverHolder.onLoadLibraryEnd(r2, r3)
            return r3
        L_0x0022:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r4 = move-exception
            r0 = 0
            com.facebook.soloader.observer.ObserverHolder.onLoadLibraryEnd(r3, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryOnAndroid(java.lang.String, int):boolean");
    }

    private static Boolean loadLibraryOnNonAndroid(String str) {
        Boolean valueOf;
        if (sSoSources != null) {
            return null;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        boolean contains = sLoadedLibraries.contains(str);
                        boolean z = !contains;
                        if (!contains) {
                            System.loadLibrary(str);
                        }
                        valueOf = Boolean.valueOf(z);
                    }
                    reentrantReadWriteLock.readLock().unlock();
                    return valueOf;
                }
            }
            reentrantReadWriteLock.readLock().unlock();
            return null;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        com.facebook.soloader.observer.ObserverHolder.onLoadDependencyEnd(r1, false);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void loadDependency(java.lang.String r1, int r2, android.os.StrictMode.ThreadPolicy r3) {
        /*
            com.facebook.soloader.observer.ObserverHolder.onLoadDependencyStart(r1, r2)
            r2 = r2 | 1
            r0 = 0
            boolean r1 = loadLibraryBySoNameImpl(r1, r0, r0, r2, r3)     // Catch:{ all -> 0x000e }
            com.facebook.soloader.observer.ObserverHolder.onLoadDependencyEnd(r0, r1)
            return
        L_0x000e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r2 = move-exception
            r3 = 0
            com.facebook.soloader.observer.ObserverHolder.onLoadDependencyEnd(r1, r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadDependency(java.lang.String, int, android.os.StrictMode$ThreadPolicy):void");
    }

    private static boolean loadLibraryBySoName(String str, String str2, String str3, int i, StrictMode.ThreadPolicy threadPolicy) {
        RecoveryStrategy recoveryStrategy = null;
        while (true) {
            try {
                return loadLibraryBySoNameImpl(str, str2, str3, i, threadPolicy);
            } catch (UnsatisfiedLinkError e) {
                recoveryStrategy = recover(str, e, recoveryStrategy);
            }
        }
    }

    private static RecoveryStrategy recover(String str, UnsatisfiedLinkError unsatisfiedLinkError, RecoveryStrategy recoveryStrategy) {
        LogUtil.w("SoLoader", "Running a recovery step for " + str + " due to " + unsatisfiedLinkError.toString());
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        if (recoveryStrategy == null) {
            try {
                recoveryStrategy = getRecoveryStrategy();
                if (recoveryStrategy == null) {
                    LogUtil.w("SoLoader", "No recovery strategy");
                    throw unsatisfiedLinkError;
                }
            } catch (NoBaseApkException e) {
                LogUtil.e("SoLoader", "Base APK not found during recovery", e);
                throw e;
            } catch (Exception e2) {
                LogUtil.e("SoLoader", "Got an exception during recovery, will throw the initial error instead", e2);
                throw unsatisfiedLinkError;
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }
        if (recoverLocked(unsatisfiedLinkError, recoveryStrategy)) {
            sSoSourcesVersion.getAndIncrement();
            reentrantReadWriteLock.writeLock().unlock();
            return recoveryStrategy;
        }
        reentrantReadWriteLock.writeLock().unlock();
        LogUtil.w("SoLoader", "Failed to recover");
        throw unsatisfiedLinkError;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        com.facebook.soloader.observer.ObserverHolder.onRecoveryEnd(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean recoverLocked(java.lang.UnsatisfiedLinkError r1, com.facebook.soloader.recovery.RecoveryStrategy r2) {
        /*
            com.facebook.soloader.observer.ObserverHolder.onRecoveryStart(r2)
            com.facebook.soloader.SoSource[] r0 = sSoSources     // Catch:{ all -> 0x000e }
            boolean r1 = r2.recover(r1, r0)     // Catch:{ all -> 0x000e }
            r2 = 0
            com.facebook.soloader.observer.ObserverHolder.onRecoveryEnd(r2)
            return r1
        L_0x000e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r2 = move-exception
            com.facebook.soloader.observer.ObserverHolder.onRecoveryEnd(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.recoverLocked(java.lang.UnsatisfiedLinkError, com.facebook.soloader.recovery.RecoveryStrategy):boolean");
    }

    private static synchronized RecoveryStrategy getRecoveryStrategy() {
        RecoveryStrategy recoveryStrategy;
        synchronized (SoLoader.class) {
            RecoveryStrategyFactory recoveryStrategyFactory = sRecoveryStrategyFactory;
            recoveryStrategy = recoveryStrategyFactory == null ? null : recoveryStrategyFactory.get();
        }
        return recoveryStrategy;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0164, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0167, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0168, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0169, code lost:
        r2.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0172, code lost:
        return !r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0175, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0179, code lost:
        sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0182, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005c, code lost:
        r2 = sSoSourcesLock;
        r2.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
        if (r3 != false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006c, code lost:
        if (r0.contains(r9) == false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006e, code lost:
        if (r11 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0070, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0071, code lost:
        r2.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0078, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007c, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007d, code lost:
        if (r3 != false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        com.facebook.soloader.LogUtil.d("SoLoader", "About to load: " + r9);
        doLoadLibraryBySoName(r9, r10, r12, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        com.facebook.soloader.LogUtil.d("SoLoader", "Loaded: " + r9);
        r0.add(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b2, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b3, code lost:
        r10 = r9.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b7, code lost:
        if (r10 == null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d0, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r9, r10.substring(r10.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d1, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d2, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d6, code lost:
        if ((r12 & 16) != 0) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d8, code lost:
        if (r11 == null) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00de, code lost:
        if (android.text.TextUtils.isEmpty(r10) != false) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e6, code lost:
        if (sLoadedAndJniInvoked.contains(r10) == false) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ea, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00ed, code lost:
        r11 = SYSTRACE_LIBRARY_LOADING;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ef, code lost:
        if (r11 == false) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f3, code lost:
        if (externalSoMapping != null) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f5, code lost:
        com.facebook.soloader.Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", r10, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        com.facebook.soloader.LogUtil.d("SoLoader", "About to invoke JNI_OnLoad for merged library " + r10 + ", which was merged into " + r9);
        r12 = externalSoMapping;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011c, code lost:
        if (r12 == null) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x011e, code lost:
        r12.invokeJniOnload(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0122, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0124, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0126, code lost:
        com.facebook.soloader.MergedSoMapping.invokeJniOnload(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0129, code lost:
        sLoadedAndJniInvoked.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x012e, code lost:
        if (r11 == false) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0132, code lost:
        if (externalSoMapping != null) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0134, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x015b, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r10 + "', which has been merged into '" + r9 + "'.  See comment for details.", r11);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r9, java.lang.String r10, java.lang.String r11, int r12, android.os.StrictMode.ThreadPolicy r13) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r1 = 0
            if (r0 != 0) goto L_0x0010
            java.util.Set r0 = sLoadedAndJniInvoked
            boolean r0 = r0.contains(r10)
            if (r0 == 0) goto L_0x0010
            return r1
        L_0x0010:
            java.util.Set r0 = sLoadedLibraries
            boolean r2 = r0.contains(r9)
            if (r2 == 0) goto L_0x001b
            if (r11 != 0) goto L_0x001b
            return r1
        L_0x001b:
            java.lang.Class<com.facebook.soloader.SoLoader> r2 = com.facebook.soloader.SoLoader.class
            monitor-enter(r2)
            boolean r3 = r0.contains(r9)     // Catch:{ all -> 0x0029 }
            r4 = 1
            if (r3 == 0) goto L_0x002e
            if (r11 != 0) goto L_0x002c
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            return r1
        L_0x0029:
            r9 = move-exception
            goto L_0x0183
        L_0x002c:
            r3 = r4
            goto L_0x002f
        L_0x002e:
            r3 = r1
        L_0x002f:
            java.util.Map r5 = sLoadingLibraries     // Catch:{ all -> 0x0029 }
            boolean r6 = r5.containsKey(r9)     // Catch:{ all -> 0x0029 }
            if (r6 == 0) goto L_0x003c
            java.lang.Object r5 = r5.get(r9)     // Catch:{ all -> 0x0029 }
            goto L_0x0045
        L_0x003c:
            java.lang.Object r6 = new java.lang.Object     // Catch:{ all -> 0x0029 }
            r6.<init>()     // Catch:{ all -> 0x0029 }
            r5.put(r9, r6)     // Catch:{ all -> 0x0029 }
            r5 = r6
        L_0x0045:
            java.util.Map r6 = sInvokingJniForLibrary     // Catch:{ all -> 0x0029 }
            boolean r7 = r6.containsKey(r10)     // Catch:{ all -> 0x0029 }
            if (r7 == 0) goto L_0x0052
            java.lang.Object r6 = r6.get(r10)     // Catch:{ all -> 0x0029 }
            goto L_0x005b
        L_0x0052:
            java.lang.Object r7 = new java.lang.Object     // Catch:{ all -> 0x0029 }
            r7.<init>()     // Catch:{ all -> 0x0029 }
            r6.put(r10, r7)     // Catch:{ all -> 0x0029 }
            r6 = r7
        L_0x005b:
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            java.util.concurrent.locks.ReentrantReadWriteLock r2 = sSoSourcesLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r2.readLock()
            r7.lock()
            monitor-enter(r5)     // Catch:{ all -> 0x0175 }
            if (r3 != 0) goto L_0x00d2
            boolean r7 = r0.contains(r9)     // Catch:{ all -> 0x0079 }
            if (r7 == 0) goto L_0x007d
            if (r11 != 0) goto L_0x007c
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r9 = r2.readLock()
            r9.unlock()
            return r1
        L_0x0079:
            r9 = move-exception
            goto L_0x0177
        L_0x007c:
            r3 = r4
        L_0x007d:
            if (r3 != 0) goto L_0x00d2
            java.lang.String r1 = "SoLoader"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            r7.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            java.lang.String r8 = "About to load: "
            r7.append(r8)     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            r7.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            java.lang.String r7 = r7.toString()     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            com.facebook.soloader.LogUtil.d(r1, r7)     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            doLoadLibraryBySoName(r9, r10, r12, r13)     // Catch:{ UnsatisfiedLinkError -> 0x00b2 }
            java.lang.String r13 = "SoLoader"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0079 }
            r1.<init>()     // Catch:{ all -> 0x0079 }
            java.lang.String r7 = "Loaded: "
            r1.append(r7)     // Catch:{ all -> 0x0079 }
            r1.append(r9)     // Catch:{ all -> 0x0079 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0079 }
            com.facebook.soloader.LogUtil.d(r13, r1)     // Catch:{ all -> 0x0079 }
            r0.add(r9)     // Catch:{ all -> 0x0079 }
            goto L_0x00d2
        L_0x00b2:
            r9 = move-exception
            java.lang.String r10 = r9.getMessage()     // Catch:{ all -> 0x0079 }
            if (r10 == 0) goto L_0x00d1
            java.lang.String r11 = "unexpected e_machine:"
            boolean r11 = r10.contains(r11)     // Catch:{ all -> 0x0079 }
            if (r11 == 0) goto L_0x00d1
            java.lang.String r11 = "unexpected e_machine:"
            int r11 = r10.lastIndexOf(r11)     // Catch:{ all -> 0x0079 }
            java.lang.String r10 = r10.substring(r11)     // Catch:{ all -> 0x0079 }
            com.facebook.soloader.SoLoader$WrongAbiError r11 = new com.facebook.soloader.SoLoader$WrongAbiError     // Catch:{ all -> 0x0079 }
            r11.<init>(r9, r10)     // Catch:{ all -> 0x0079 }
            throw r11     // Catch:{ all -> 0x0079 }
        L_0x00d1:
            throw r9     // Catch:{ all -> 0x0079 }
        L_0x00d2:
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            monitor-enter(r6)     // Catch:{ all -> 0x0175 }
            r12 = r12 & 16
            if (r12 != 0) goto L_0x0168
            if (r11 == 0) goto L_0x0168
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x00ea }
            if (r11 != 0) goto L_0x00ed
            java.util.Set r11 = sLoadedAndJniInvoked     // Catch:{ all -> 0x00ea }
            boolean r11 = r11.contains(r10)     // Catch:{ all -> 0x00ea }
            if (r11 == 0) goto L_0x00ed
            goto L_0x0168
        L_0x00ea:
            r9 = move-exception
            goto L_0x0173
        L_0x00ed:
            boolean r11 = SYSTRACE_LIBRARY_LOADING     // Catch:{ all -> 0x00ea }
            if (r11 == 0) goto L_0x00fc
            com.facebook.soloader.ExternalSoMapping r12 = externalSoMapping     // Catch:{ all -> 0x00ea }
            if (r12 != 0) goto L_0x00fc
            java.lang.String r12 = "MergedSoMapping.invokeJniOnload["
            java.lang.String r13 = "]"
            com.facebook.soloader.Api18TraceUtils.beginTraceSection(r12, r10, r13)     // Catch:{ all -> 0x00ea }
        L_0x00fc:
            java.lang.String r12 = "SoLoader"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            r13.<init>()     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            java.lang.String r0 = "About to invoke JNI_OnLoad for merged library "
            r13.append(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            r13.append(r10)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            java.lang.String r0 = ", which was merged into "
            r13.append(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            r13.append(r9)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            java.lang.String r13 = r13.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            com.facebook.soloader.LogUtil.d(r12, r13)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            com.facebook.soloader.ExternalSoMapping r12 = externalSoMapping     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            if (r12 == 0) goto L_0x0126
            r12.invokeJniOnload(r10)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            goto L_0x0129
        L_0x0122:
            r9 = move-exception
            goto L_0x015c
        L_0x0124:
            r11 = move-exception
            goto L_0x0138
        L_0x0126:
            com.facebook.soloader.MergedSoMapping.invokeJniOnload(r10)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
        L_0x0129:
            java.util.Set r12 = sLoadedAndJniInvoked     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            r12.add(r10)     // Catch:{ UnsatisfiedLinkError -> 0x0124 }
            if (r11 == 0) goto L_0x0168
            com.facebook.soloader.ExternalSoMapping r9 = externalSoMapping     // Catch:{ all -> 0x00ea }
            if (r9 != 0) goto L_0x0168
            com.facebook.soloader.Api18TraceUtils.endSection()     // Catch:{ all -> 0x00ea }
            goto L_0x0168
        L_0x0138:
            java.lang.RuntimeException r12 = new java.lang.RuntimeException     // Catch:{ all -> 0x0122 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0122 }
            r13.<init>()     // Catch:{ all -> 0x0122 }
            java.lang.String r0 = "Failed to call JNI_OnLoad from '"
            r13.append(r0)     // Catch:{ all -> 0x0122 }
            r13.append(r10)     // Catch:{ all -> 0x0122 }
            java.lang.String r10 = "', which has been merged into '"
            r13.append(r10)     // Catch:{ all -> 0x0122 }
            r13.append(r9)     // Catch:{ all -> 0x0122 }
            java.lang.String r9 = "'.  See comment for details."
            r13.append(r9)     // Catch:{ all -> 0x0122 }
            java.lang.String r9 = r13.toString()     // Catch:{ all -> 0x0122 }
            r12.<init>(r9, r11)     // Catch:{ all -> 0x0122 }
            throw r12     // Catch:{ all -> 0x0122 }
        L_0x015c:
            boolean r10 = SYSTRACE_LIBRARY_LOADING     // Catch:{ all -> 0x00ea }
            if (r10 == 0) goto L_0x0167
            com.facebook.soloader.ExternalSoMapping r10 = externalSoMapping     // Catch:{ all -> 0x00ea }
            if (r10 != 0) goto L_0x0167
            com.facebook.soloader.Api18TraceUtils.endSection()     // Catch:{ all -> 0x00ea }
        L_0x0167:
            throw r9     // Catch:{ all -> 0x00ea }
        L_0x0168:
            monitor-exit(r6)     // Catch:{ all -> 0x00ea }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r9 = r2.readLock()
            r9.unlock()
            r9 = r3 ^ 1
            return r9
        L_0x0173:
            monitor-exit(r6)     // Catch:{ all -> 0x00ea }
            throw r9     // Catch:{ all -> 0x0175 }
        L_0x0175:
            r9 = move-exception
            goto L_0x0179
        L_0x0177:
            monitor-exit(r5)     // Catch:{ all -> 0x0079 }
            throw r9     // Catch:{ all -> 0x0175 }
        L_0x0179:
            java.util.concurrent.locks.ReentrantReadWriteLock r10 = sSoSourcesLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r10 = r10.readLock()
            r10.unlock()
            throw r9
        L_0x0183:
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    private static void doLoadLibraryBySoName(String str, String str2, int i, StrictMode.ThreadPolicy threadPolicy) {
        boolean z;
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                reentrantReadWriteLock.readLock().unlock();
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    if (str2 != null) {
                        Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str2, "]");
                    }
                    Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
                }
                try {
                    reentrantReadWriteLock.readLock().lock();
                    for (SoSource loadLibraryFromSoSource : sSoSources) {
                        if (loadLibraryFromSoSource(loadLibraryFromSoSource, str, i, threadPolicy)) {
                            if (SYSTRACE_LIBRARY_LOADING) {
                                if (str2 != null) {
                                    Api18TraceUtils.endSection();
                                }
                                Api18TraceUtils.endSection();
                            }
                            if (z) {
                                StrictMode.setThreadPolicy(threadPolicy);
                                return;
                            }
                            return;
                        }
                    }
                    throw SoLoaderDSONotFoundError.create(str, sApplicationContext, sSoSources);
                } catch (IOException e) {
                    SoLoaderULError soLoaderULError = new SoLoaderULError(str, e.toString());
                    soLoaderULError.initCause(e);
                    throw soLoaderULError;
                } catch (Throwable th) {
                    if (SYSTRACE_LIBRARY_LOADING) {
                        if (str2 != null) {
                            Api18TraceUtils.endSection();
                        }
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    throw th;
                }
            } else {
                LogUtil.e("SoLoader", "Could not load: " + str + " because SoLoader is not initialized");
                throw new UnsatisfiedLinkError("SoLoader not initialized, couldn't find DSO to load: " + str);
            }
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        com.facebook.soloader.observer.ObserverHolder.onSoSourceLoadLibraryEnd(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryFromSoSource(com.facebook.soloader.SoSource r0, java.lang.String r1, int r2, android.os.StrictMode.ThreadPolicy r3) {
        /*
            com.facebook.soloader.observer.ObserverHolder.onSoSourceLoadLibraryStart(r0)
            int r0 = r0.loadLibrary(r1, r2, r3)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x000b
            r0 = 1
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            r1 = 0
            com.facebook.soloader.observer.ObserverHolder.onSoSourceLoadLibraryEnd(r1)
            return r0
        L_0x0011:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            com.facebook.soloader.observer.ObserverHolder.onSoSourceLoadLibraryEnd(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryFromSoSource(com.facebook.soloader.SoSource, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        boolean z = true;
        if (sSoSources != null) {
            return true;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                z = false;
            }
            reentrantReadWriteLock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }
}
