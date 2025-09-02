package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NetworkCache {
    private final LottieNetworkCacheProvider cacheProvider;

    public NetworkCache(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        this.cacheProvider = lottieNetworkCacheProvider;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair fetch(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = r5.getCachedFile(r6)     // Catch:{ FileNotFoundException -> 0x0053 }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{  }
            r2.<init>(r1)     // Catch:{  }
            java.lang.String r0 = r1.getAbsolutePath()
            java.lang.String r3 = ".zip"
            boolean r0 = r0.endsWith(r3)
            if (r0 == 0) goto L_0x001c
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.ZIP
            goto L_0x002d
        L_0x001c:
            java.lang.String r0 = r1.getAbsolutePath()
            java.lang.String r3 = ".gz"
            boolean r0 = r0.endsWith(r3)
            if (r0 == 0) goto L_0x002b
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.GZIP
            goto L_0x002d
        L_0x002b:
            com.airbnb.lottie.network.FileExtension r0 = com.airbnb.lottie.network.FileExtension.JSON
        L_0x002d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Cache hit for "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = " at "
            r3.append(r6)
            java.lang.String r6 = r1.getAbsolutePath()
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            com.airbnb.lottie.utils.Logger.debug(r6)
            android.util.Pair r6 = new android.util.Pair
            r6.<init>(r0, r2)
            return r6
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkCache.fetch(java.lang.String):android.util.Pair");
    }

    /* access modifiers changed from: package-private */
    public File writeTempCacheFile(String str, InputStream inputStream, FileExtension fileExtension) {
        FileOutputStream fileOutputStream;
        File file = new File(parentDir(), filenameForUrl(str, fileExtension, true));
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    return file;
                }
            }
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void renameTempFile(String str, FileExtension fileExtension) {
        File file = new File(parentDir(), filenameForUrl(str, fileExtension, true));
        File file2 = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean renameTo = file.renameTo(file2);
        Logger.debug("Copying temp file to real file (" + file2 + ")");
        if (!renameTo) {
            Logger.warning("Unable to rename cache file " + file.getAbsolutePath() + " to " + file2.getAbsolutePath() + ".");
        }
    }

    private File getCachedFile(String str) {
        File file = new File(parentDir(), filenameForUrl(str, FileExtension.JSON, false));
        if (file.exists()) {
            return file;
        }
        File file2 = new File(parentDir(), filenameForUrl(str, FileExtension.ZIP, false));
        if (file2.exists()) {
            return file2;
        }
        File file3 = new File(parentDir(), filenameForUrl(str, FileExtension.GZIP, false));
        if (file3.exists()) {
            return file3;
        }
        return null;
    }

    private File parentDir() {
        File cacheDir = this.cacheProvider.getCacheDir();
        if (cacheDir.isFile()) {
            cacheDir.delete();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    private static String filenameForUrl(String str, FileExtension fileExtension, boolean z) {
        String tempExtension = z ? fileExtension.tempExtension() : fileExtension.extension;
        String replaceAll = str.replaceAll("\\W+", "");
        int length = 242 - tempExtension.length();
        if (replaceAll.length() > length) {
            replaceAll = getMD5(replaceAll, length);
        }
        return "lottie_cache_" + replaceAll + tempExtension;
    }

    private static String getMD5(String str, int i) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte valueOf : digest) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return str.substring(0, i);
        }
    }
}
