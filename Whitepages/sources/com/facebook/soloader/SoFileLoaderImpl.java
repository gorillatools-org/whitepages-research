package com.facebook.soloader;

import com.salesforce.marketingcloud.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SoFileLoaderImpl implements SoFileLoader {
    private final String mLocalLdLibraryPath = null;
    private final String mLocalLdLibraryPathNoZips = null;
    private final Method mNativeLoadRuntimeMethod = null;
    private final Runtime mRuntime = null;

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        com.facebook.soloader.LogUtil.e("SoFileLoaderImpl", "Error when loading library: " + r2 + ", library hash is " + getLibHash(r7) + ", LD_LIBRARY_PATH is " + r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(java.lang.String r7, int r8) {
        /*
            r6 = this;
            java.lang.reflect.Method r0 = r6.mNativeLoadRuntimeMethod
            if (r0 != 0) goto L_0x0008
            java.lang.System.load(r7)
            return
        L_0x0008:
            r0 = 4
            r8 = r8 & r0
            if (r8 != r0) goto L_0x000f
            java.lang.String r8 = r6.mLocalLdLibraryPath
            goto L_0x0011
        L_0x000f:
            java.lang.String r8 = r6.mLocalLdLibraryPathNoZips
        L_0x0011:
            r0 = 0
            java.lang.Runtime r1 = r6.mRuntime     // Catch:{ IllegalAccessException -> 0x0092, IllegalArgumentException -> 0x0090, InvocationTargetException -> 0x008c }
            monitor-enter(r1)     // Catch:{ IllegalAccessException -> 0x0092, IllegalArgumentException -> 0x0090, InvocationTargetException -> 0x008c }
            java.lang.reflect.Method r2 = r6.mNativeLoadRuntimeMethod     // Catch:{ all -> 0x007a }
            java.lang.Runtime r3 = r6.mRuntime     // Catch:{ all -> 0x007a }
            java.lang.Class<com.facebook.soloader.SoLoader> r4 = com.facebook.soloader.SoLoader.class
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ all -> 0x007a }
            java.lang.Object[] r4 = new java.lang.Object[]{r7, r4, r8}     // Catch:{ all -> 0x007a }
            java.lang.Object r2 = r2.invoke(r3, r4)     // Catch:{ all -> 0x007a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x007a }
            if (r2 != 0) goto L_0x005b
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0058
            java.lang.String r0 = "SoFileLoaderImpl"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Error when loading library: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = ", library hash is "
            r1.append(r2)
            java.lang.String r7 = r6.getLibHash(r7)
            r1.append(r7)
            java.lang.String r7 = ", LD_LIBRARY_PATH is "
            r1.append(r7)
            r1.append(r8)
            java.lang.String r7 = r1.toString()
            com.facebook.soloader.LogUtil.e(r0, r7)
        L_0x0058:
            return
        L_0x0059:
            r0 = move-exception
            goto L_0x007e
        L_0x005b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r0.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = "nativeLoad() returned error for "
            r0.append(r3)     // Catch:{ all -> 0x0059 }
            r0.append(r7)     // Catch:{ all -> 0x0059 }
            java.lang.String r3 = ": "
            r0.append(r3)     // Catch:{ all -> 0x0059 }
            r0.append(r2)     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0059 }
            com.facebook.soloader.SoLoaderULError r2 = new com.facebook.soloader.SoLoaderULError     // Catch:{ all -> 0x007a }
            r2.<init>(r7, r0)     // Catch:{ all -> 0x007a }
            throw r2     // Catch:{ all -> 0x007a }
        L_0x007a:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L_0x007e:
            monitor-exit(r1)     // Catch:{ all -> 0x0059 }
            throw r0     // Catch:{ IllegalAccessException -> 0x0086, IllegalArgumentException -> 0x0084, InvocationTargetException -> 0x0082 }
        L_0x0080:
            r0 = move-exception
            goto L_0x00b3
        L_0x0082:
            r0 = move-exception
            goto L_0x0094
        L_0x0084:
            r0 = move-exception
            goto L_0x0094
        L_0x0086:
            r0 = move-exception
            goto L_0x0094
        L_0x0088:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x00b3
        L_0x008c:
            r1 = move-exception
        L_0x008d:
            r2 = r0
            r0 = r1
            goto L_0x0094
        L_0x0090:
            r1 = move-exception
            goto L_0x008d
        L_0x0092:
            r1 = move-exception
            goto L_0x008d
        L_0x0094:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r1.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r3 = "nativeLoad() error during invocation for "
            r1.append(r3)     // Catch:{ all -> 0x0080 }
            r1.append(r7)     // Catch:{ all -> 0x0080 }
            java.lang.String r3 = ": "
            r1.append(r3)     // Catch:{ all -> 0x0080 }
            r1.append(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0080 }
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0088 }
            r1.<init>(r0)     // Catch:{ all -> 0x0088 }
            throw r1     // Catch:{ all -> 0x0088 }
        L_0x00b3:
            if (r2 == 0) goto L_0x00df
            java.lang.String r1 = "SoFileLoaderImpl"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error when loading library: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = ", library hash is "
            r3.append(r2)
            java.lang.String r7 = r6.getLibHash(r7)
            r3.append(r7)
            java.lang.String r7 = ", LD_LIBRARY_PATH is "
            r3.append(r7)
            r3.append(r8)
            java.lang.String r7 = r3.toString()
            com.facebook.soloader.LogUtil.e(r1, r7)
        L_0x00df:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoFileLoaderImpl.load(java.lang.String, int):void");
    }

    private String getLibHash(String str) {
        FileInputStream fileInputStream;
        try {
            File file = new File(str);
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[b.v];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    instance.update(bArr, 0, read);
                } else {
                    String format = String.format("%32x", new Object[]{new BigInteger(1, instance.digest())});
                    fileInputStream.close();
                    return format;
                }
            }
        } catch (IOException | SecurityException | NoSuchAlgorithmException e) {
            return e.toString();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
