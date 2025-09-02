package com.microsoft.codepush.react;

import java.io.File;

public abstract class FileUtils {
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083 A[SYNTHETIC, Splitter:B:34:0x0083] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b A[Catch:{ IOException -> 0x0087 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0090 A[Catch:{ IOException -> 0x0087 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyDirectoryContents(java.lang.String r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "Error closing IO resources."
            java.io.File r1 = new java.io.File
            r1.<init>(r12)
            java.io.File r2 = new java.io.File
            r2.<init>(r13)
            boolean r3 = r2.exists()
            if (r3 != 0) goto L_0x0015
            r2.mkdir()
        L_0x0015:
            java.io.File[] r1 = r1.listFiles()
            int r3 = r1.length
            r4 = 0
            r5 = r4
        L_0x001c:
            if (r5 >= r3) goto L_0x009b
            r6 = r1[r5]
            boolean r7 = r6.isDirectory()
            if (r7 == 0) goto L_0x003a
            java.lang.String r7 = r6.getName()
            java.lang.String r7 = com.microsoft.codepush.react.CodePushUtils.appendPathComponent(r12, r7)
            java.lang.String r6 = r6.getName()
            java.lang.String r6 = com.microsoft.codepush.react.CodePushUtils.appendPathComponent(r13, r6)
            copyDirectoryContents(r7, r6)
            goto L_0x006d
        L_0x003a:
            java.io.File r7 = new java.io.File
            java.lang.String r8 = r6.getName()
            r7.<init>(r2, r8)
            r8 = 8192(0x2000, float:1.14794E-41)
            byte[] r8 = new byte[r8]
            r9 = 0
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ all -> 0x007e }
            r10.<init>(r6)     // Catch:{ all -> 0x007e }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x007a }
            r6.<init>(r10)     // Catch:{ all -> 0x007a }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ all -> 0x0077 }
            r11.<init>(r7)     // Catch:{ all -> 0x0077 }
        L_0x0057:
            int r7 = r6.read(r8)     // Catch:{ all -> 0x0061 }
            if (r7 <= 0) goto L_0x0064
            r11.write(r8, r4, r7)     // Catch:{ all -> 0x0061 }
            goto L_0x0057
        L_0x0061:
            r12 = move-exception
        L_0x0062:
            r9 = r10
            goto L_0x0081
        L_0x0064:
            r10.close()     // Catch:{ IOException -> 0x0070 }
            r6.close()     // Catch:{ IOException -> 0x0070 }
            r11.close()     // Catch:{ IOException -> 0x0070 }
        L_0x006d:
            int r5 = r5 + 1
            goto L_0x001c
        L_0x0070:
            r12 = move-exception
            com.microsoft.codepush.react.CodePushUnknownException r13 = new com.microsoft.codepush.react.CodePushUnknownException
            r13.<init>(r0, r12)
            throw r13
        L_0x0077:
            r12 = move-exception
            r11 = r9
            goto L_0x0062
        L_0x007a:
            r12 = move-exception
            r6 = r9
            r11 = r6
            goto L_0x0062
        L_0x007e:
            r12 = move-exception
            r6 = r9
            r11 = r6
        L_0x0081:
            if (r9 == 0) goto L_0x0089
            r9.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x0089
        L_0x0087:
            r12 = move-exception
            goto L_0x0094
        L_0x0089:
            if (r6 == 0) goto L_0x008e
            r6.close()     // Catch:{ IOException -> 0x0087 }
        L_0x008e:
            if (r11 == 0) goto L_0x009a
            r11.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x009a
        L_0x0094:
            com.microsoft.codepush.react.CodePushUnknownException r13 = new com.microsoft.codepush.react.CodePushUnknownException
            r13.<init>(r0, r12)
            throw r13
        L_0x009a:
            throw r12
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.FileUtils.copyDirectoryContents(java.lang.String, java.lang.String):void");
    }

    public static void deleteDirectoryAtPath(String str) {
        if (str == null) {
            CodePushUtils.log("deleteDirectoryAtPath attempted with null directoryPath");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            deleteFileOrFolderSilently(file);
        }
    }

    public static void deleteFileAtPathSilently(String str) {
        deleteFileOrFolderSilently(new File(str));
    }

    public static void deleteFileOrFolderSilently(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    deleteFileOrFolderSilently(file2);
                } else {
                    file2.delete();
                }
            }
        }
        if (!file.delete()) {
            CodePushUtils.log("Error deleting file " + file.getName());
        }
    }

    public static boolean fileAtPathExists(String str) {
        return new File(str).exists();
    }

    public static void moveFile(File file, String str, String str2) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(str, str2);
        if (!file.renameTo(file3)) {
            throw new CodePushUnknownException("Unable to move file from " + file.getAbsolutePath() + " to " + file3.getAbsolutePath() + ".");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFileToString(java.lang.String r4) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x003b }
            r1.<init>(r4)     // Catch:{ all -> 0x003b }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x003b }
            r4.<init>(r1)     // Catch:{ all -> 0x003b }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0036 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x0036 }
            r2.<init>(r4)     // Catch:{ all -> 0x0036 }
            r1.<init>(r2)     // Catch:{ all -> 0x0036 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0029 }
            r0.<init>()     // Catch:{ all -> 0x0029 }
        L_0x001a:
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x002b
            r0.append(r2)     // Catch:{ all -> 0x0029 }
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ all -> 0x0029 }
            goto L_0x001a
        L_0x0029:
            r0 = move-exception
            goto L_0x003f
        L_0x002b:
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0029 }
            r1.close()
            r4.close()
            return r0
        L_0x0036:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x003f
        L_0x003b:
            r4 = move-exception
            r1 = r0
            r0 = r4
            r4 = r1
        L_0x003f:
            if (r1 == 0) goto L_0x0044
            r1.close()
        L_0x0044:
            if (r4 == 0) goto L_0x0049
            r4.close()
        L_0x0049:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.FileUtils.readFileToString(java.lang.String):java.lang.String");
    }

    private static String validateFileName(String str, File file) {
        String str2 = file.getCanonicalPath() + File.separator;
        String canonicalPath = new File(str2, str).getCanonicalPath();
        if (canonicalPath.startsWith(str2)) {
            return canonicalPath;
        }
        throw new IllegalStateException("File is outside extraction target directory.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0099 A[SYNTHETIC, Splitter:B:47:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a1 A[Catch:{ IOException -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a6 A[Catch:{ IOException -> 0x009d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void unzipFile(java.io.File r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Error closing IO resources."
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0094 }
            r2.<init>(r9)     // Catch:{ all -> 0x0094 }
            java.io.BufferedInputStream r9 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0091 }
            r9.<init>(r2)     // Catch:{ all -> 0x0091 }
            java.util.zip.ZipInputStream r3 = new java.util.zip.ZipInputStream     // Catch:{ all -> 0x008f }
            r3.<init>(r9)     // Catch:{ all -> 0x008f }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0021 }
            r1.<init>(r10)     // Catch:{ all -> 0x0021 }
            boolean r10 = r1.exists()     // Catch:{ all -> 0x0021 }
            if (r10 == 0) goto L_0x0025
            deleteFileOrFolderSilently(r1)     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r10 = move-exception
            r1 = r3
            goto L_0x0097
        L_0x0025:
            r1.mkdirs()     // Catch:{ all -> 0x0021 }
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch:{ all -> 0x0021 }
        L_0x002c:
            java.util.zip.ZipEntry r4 = r3.getNextEntry()     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x007e
            java.lang.String r5 = r4.getName()     // Catch:{ all -> 0x0021 }
            java.lang.String r5 = validateFileName(r5, r1)     // Catch:{ all -> 0x0021 }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x0021 }
            r6.<init>(r5)     // Catch:{ all -> 0x0021 }
            boolean r5 = r4.isDirectory()     // Catch:{ all -> 0x0021 }
            if (r5 == 0) goto L_0x0049
            r6.mkdirs()     // Catch:{ all -> 0x0021 }
            goto L_0x006c
        L_0x0049:
            java.io.File r5 = r6.getParentFile()     // Catch:{ all -> 0x0021 }
            boolean r7 = r5.exists()     // Catch:{ all -> 0x0021 }
            if (r7 != 0) goto L_0x0056
            r5.mkdirs()     // Catch:{ all -> 0x0021 }
        L_0x0056:
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x0021 }
            r5.<init>(r6)     // Catch:{ all -> 0x0021 }
        L_0x005b:
            int r7 = r3.read(r10)     // Catch:{ all -> 0x0067 }
            r8 = -1
            if (r7 == r8) goto L_0x0069
            r8 = 0
            r5.write(r10, r8, r7)     // Catch:{ all -> 0x0067 }
            goto L_0x005b
        L_0x0067:
            r10 = move-exception
            goto L_0x007a
        L_0x0069:
            r5.close()     // Catch:{ all -> 0x0021 }
        L_0x006c:
            long r4 = r4.getTime()     // Catch:{ all -> 0x0021 }
            r7 = 0
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x002c
            r6.setLastModified(r4)     // Catch:{ all -> 0x0021 }
            goto L_0x002c
        L_0x007a:
            r5.close()     // Catch:{ all -> 0x0021 }
            throw r10     // Catch:{ all -> 0x0021 }
        L_0x007e:
            r3.close()     // Catch:{ IOException -> 0x0088 }
            r9.close()     // Catch:{ IOException -> 0x0088 }
            r2.close()     // Catch:{ IOException -> 0x0088 }
            return
        L_0x0088:
            r9 = move-exception
            com.microsoft.codepush.react.CodePushUnknownException r10 = new com.microsoft.codepush.react.CodePushUnknownException
            r10.<init>(r0, r9)
            throw r10
        L_0x008f:
            r10 = move-exception
            goto L_0x0097
        L_0x0091:
            r10 = move-exception
            r9 = r1
            goto L_0x0097
        L_0x0094:
            r10 = move-exception
            r9 = r1
            r2 = r9
        L_0x0097:
            if (r1 == 0) goto L_0x009f
            r1.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x009f
        L_0x009d:
            r9 = move-exception
            goto L_0x00aa
        L_0x009f:
            if (r9 == 0) goto L_0x00a4
            r9.close()     // Catch:{ IOException -> 0x009d }
        L_0x00a4:
            if (r2 == 0) goto L_0x00b0
            r2.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00b0
        L_0x00aa:
            com.microsoft.codepush.react.CodePushUnknownException r10 = new com.microsoft.codepush.react.CodePushUnknownException
            r10.<init>(r0, r9)
            throw r10
        L_0x00b0:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.FileUtils.unzipFile(java.io.File, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeStringToFile(java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ all -> 0x0010 }
            r1.<init>(r3)     // Catch:{ all -> 0x0010 }
            r1.print(r2)     // Catch:{ all -> 0x000d }
            r1.close()
            return
        L_0x000d:
            r2 = move-exception
            r0 = r1
            goto L_0x0011
        L_0x0010:
            r2 = move-exception
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.FileUtils.writeStringToFile(java.lang.String, java.lang.String):void");
    }
}
