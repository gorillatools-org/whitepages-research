package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DirectApkSoSource extends SoSource implements RecoverableSoSource {
    private final Map mDepsCache = new HashMap();
    private final Set mDirectApkLdPaths;
    private final Map mLibsInApkCache = new HashMap();

    public DirectApkSoSource(Context context) {
        this.mDirectApkLdPaths = getDirectApkLdPaths(context);
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        if (SoLoader.sSoFileLoader != null) {
            for (String str2 : this.mDirectApkLdPaths) {
                Set set = (Set) this.mLibsInApkCache.get(str2);
                if (TextUtils.isEmpty(str2) || set == null || !set.contains(str)) {
                    LogUtil.v("SoLoader", str + " not found on " + str2);
                } else {
                    loadDependencies(str2, str, i, threadPolicy);
                    try {
                        i |= 4;
                        SoLoader.sSoFileLoader.load(str2 + File.separator + str, i);
                        LogUtil.d("SoLoader", str + " found on " + str2);
                        return 1;
                    } catch (UnsatisfiedLinkError e) {
                        LogUtil.w("SoLoader", str + " not found on " + str2 + " flag: " + i, e);
                    }
                }
            }
            return 0;
        }
        throw new IllegalStateException("SoLoader.init() not yet called");
    }

    public boolean isValid() {
        return !this.mDirectApkLdPaths.isEmpty();
    }

    static Set getDirectApkLdPaths(Context context) {
        HashSet hashSet = new HashSet();
        String fallbackApkLdPath = getFallbackApkLdPath(context.getApplicationInfo().sourceDir);
        if (fallbackApkLdPath != null) {
            hashSet.add(fallbackApkLdPath);
        }
        if (context.getApplicationInfo().splitSourceDirs != null) {
            for (String fallbackApkLdPath2 : context.getApplicationInfo().splitSourceDirs) {
                String fallbackApkLdPath3 = getFallbackApkLdPath(fallbackApkLdPath2);
                if (fallbackApkLdPath3 != null) {
                    hashSet.add(fallbackApkLdPath3);
                }
            }
        }
        return hashSet;
    }

    private static String getFallbackApkLdPath(String str) {
        String[] supportedAbis = SysUtil.getSupportedAbis();
        String str2 = "empty";
        if (str == null || str.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot compute fallback path, apk path is ");
            if (str == null) {
                str2 = "null";
            }
            sb.append(str2);
            LogUtil.w("SoLoader", sb.toString());
            return null;
        } else if (supportedAbis == null || supportedAbis.length == 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Cannot compute fallback path, supportedAbis is ");
            if (supportedAbis == null) {
                str2 = "null";
            }
            sb2.append(str2);
            LogUtil.w("SoLoader", sb2.toString());
            return null;
        } else {
            return str + "!/lib/" + supportedAbis[0];
        }
    }

    private void loadDependencies(String str, String str2, int i, StrictMode.ThreadPolicy threadPolicy) {
        Set<String> depsFromCache = getDepsFromCache(str, str2);
        if (depsFromCache == null) {
            buildLibDepsCache(str, str2);
            depsFromCache = getDepsFromCache(str, str2);
        }
        if (depsFromCache != null) {
            for (String loadDependency : depsFromCache) {
                SoLoader.loadDependency(loadDependency, i, threadPolicy);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepare(int i) {
        prepare();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        r2 = (r2 = r1.indexOf(33)) + 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void prepare() {
        /*
            r8 = this;
            java.util.Set r0 = r8.mDirectApkLdPaths
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x008d
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x002d
            r2 = 33
            int r2 = r1.indexOf(r2)
            if (r2 < 0) goto L_0x002d
            int r2 = r2 + 2
            int r3 = r1.length()
            if (r2 >= r3) goto L_0x002d
            java.lang.String r2 = r1.substring(r2)
            goto L_0x002e
        L_0x002d:
            r2 = 0
        L_0x002e:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x0035
            goto L_0x0006
        L_0x0035:
            java.util.zip.ZipFile r3 = new java.util.zip.ZipFile
            java.lang.String r4 = getApkPathFromLdPath(r1)
            r3.<init>(r4)
            java.util.Enumeration r4 = r3.entries()     // Catch:{ all -> 0x007e }
        L_0x0042:
            boolean r5 = r4.hasMoreElements()     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0080
            java.lang.Object r5 = r4.nextElement()     // Catch:{ all -> 0x007e }
            java.util.zip.ZipEntry r5 = (java.util.zip.ZipEntry) r5     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0042
            int r6 = r5.getMethod()     // Catch:{ all -> 0x007e }
            if (r6 != 0) goto L_0x0042
            java.lang.String r6 = r5.getName()     // Catch:{ all -> 0x007e }
            boolean r6 = r6.startsWith(r2)     // Catch:{ all -> 0x007e }
            if (r6 == 0) goto L_0x0042
            java.lang.String r6 = r5.getName()     // Catch:{ all -> 0x007e }
            java.lang.String r7 = ".so"
            boolean r6 = r6.endsWith(r7)     // Catch:{ all -> 0x007e }
            if (r6 == 0) goto L_0x0042
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x007e }
            int r6 = r2.length()     // Catch:{ all -> 0x007e }
            int r6 = r6 + 1
            java.lang.String r5 = r5.substring(r6)     // Catch:{ all -> 0x007e }
            r8.appendLibsInApkCache(r1, r5)     // Catch:{ all -> 0x007e }
            goto L_0x0042
        L_0x007e:
            r0 = move-exception
            goto L_0x0084
        L_0x0080:
            r3.close()
            goto L_0x0006
        L_0x0084:
            r3.close()     // Catch:{ all -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x008c:
            throw r0
        L_0x008d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.DirectApkSoSource.prepare():void");
    }

    private void buildLibDepsCache(String str, String str2) {
        String apkPathFromLdPath = getApkPathFromLdPath(str);
        ZipFile zipFile = new ZipFile(apkPathFromLdPath);
        try {
            String libraryPathInApk = getLibraryPathInApk(str, str2);
            ZipEntry entry = zipFile.getEntry(libraryPathInApk);
            if (entry == null) {
                LogUtil.e("SoLoader", libraryPathInApk + " not found in " + apkPathFromLdPath);
                zipFile.close();
                return;
            }
            buildLibDepsCacheImpl(str, zipFile, entry, str2);
            zipFile.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void buildLibDepsCacheImpl(String str, ZipFile zipFile, ZipEntry zipEntry, String str2) {
        ElfZipFileChannel elfZipFileChannel = new ElfZipFileChannel(zipFile, zipEntry);
        try {
            for (String str3 : NativeDeps.getDependencies(str2, elfZipFileChannel)) {
                if (!str3.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
                    appendDepsCache(str, str2, str3);
                }
            }
            elfZipFileChannel.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getName() {
        return "DirectApkSoSource";
    }

    public String toString() {
        return getName() + "[root = " + this.mDirectApkLdPaths.toString() + ']';
    }

    private void appendLibsInApkCache(String str, String str2) {
        synchronized (this.mLibsInApkCache) {
            try {
                if (!this.mLibsInApkCache.containsKey(str)) {
                    this.mLibsInApkCache.put(str, new HashSet());
                }
                ((Set) this.mLibsInApkCache.get(str)).add(str2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void appendDepsCache(String str, String str2, String str3) {
        synchronized (this.mDepsCache) {
            try {
                String str4 = str + str2;
                if (!this.mDepsCache.containsKey(str4)) {
                    this.mDepsCache.put(str4, new HashSet());
                }
                ((Set) this.mDepsCache.get(str4)).add(str3);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private Set getDepsFromCache(String str, String str2) {
        Set set;
        synchronized (this.mDepsCache) {
            set = (Set) this.mDepsCache.get(str + str2);
        }
        return set;
    }

    private static String getApkPathFromLdPath(String str) {
        return str.substring(0, str.indexOf(33));
    }

    private static String getLibraryPathInApk(String str, String str2) {
        int indexOf = str.indexOf(33);
        return str.substring(indexOf + 2) + File.separator + str2;
    }

    public SoSource recover(Context context) {
        DirectApkSoSource directApkSoSource = new DirectApkSoSource(context);
        try {
            directApkSoSource.prepare();
            return directApkSoSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
