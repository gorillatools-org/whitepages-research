package com.microsoft.codepush.react;

import java.io.IOException;
import org.json.JSONObject;

public class CodePushUpdateManager {
    private String mDocumentsDirectory;

    public CodePushUpdateManager(String str) {
        this.mDocumentsDirectory = str;
    }

    private String getUnzippedFolderPath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), "unzipped");
    }

    private String getDocumentsDirectory() {
        return this.mDocumentsDirectory;
    }

    private String getCodePushPath() {
        String appendPathComponent = CodePushUtils.appendPathComponent(getDocumentsDirectory(), "CodePush");
        return CodePush.isUsingTestConfiguration() ? CodePushUtils.appendPathComponent(appendPathComponent, "TestPackages") : appendPathComponent;
    }

    private String getStatusFilePath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), "codepush.json");
    }

    public JSONObject getCurrentPackageInfo() {
        String statusFilePath = getStatusFilePath();
        if (!FileUtils.fileAtPathExists(statusFilePath)) {
            return new JSONObject();
        }
        try {
            return CodePushUtils.getJsonObjectFromFile(statusFilePath);
        } catch (IOException e) {
            throw new CodePushUnknownException("Error getting current package info", e);
        }
    }

    public void updateCurrentPackageInfo(JSONObject jSONObject) {
        try {
            CodePushUtils.writeJsonToFile(jSONObject, getStatusFilePath());
        } catch (IOException e) {
            throw new CodePushUnknownException("Error updating current package info", e);
        }
    }

    public String getCurrentPackageFolderPath() {
        String optString = getCurrentPackageInfo().optString("currentPackage", (String) null);
        if (optString == null) {
            return null;
        }
        return getPackageFolderPath(optString);
    }

    public String getCurrentPackageBundlePath(String str) {
        JSONObject currentPackage;
        String currentPackageFolderPath = getCurrentPackageFolderPath();
        if (currentPackageFolderPath == null || (currentPackage = getCurrentPackage()) == null) {
            return null;
        }
        String optString = currentPackage.optString("bundlePath", (String) null);
        if (optString == null) {
            return CodePushUtils.appendPathComponent(currentPackageFolderPath, str);
        }
        return CodePushUtils.appendPathComponent(currentPackageFolderPath, optString);
    }

    public String getPackageFolderPath(String str) {
        return CodePushUtils.appendPathComponent(getCodePushPath(), str);
    }

    public String getCurrentPackageHash() {
        return getCurrentPackageInfo().optString("currentPackage", (String) null);
    }

    public String getPreviousPackageHash() {
        return getCurrentPackageInfo().optString("previousPackage", (String) null);
    }

    public JSONObject getCurrentPackage() {
        String currentPackageHash = getCurrentPackageHash();
        if (currentPackageHash == null) {
            return null;
        }
        return getPackage(currentPackageHash);
    }

    public JSONObject getPreviousPackage() {
        String previousPackageHash = getPreviousPackageHash();
        if (previousPackageHash == null) {
            return null;
        }
        return getPackage(previousPackageHash);
    }

    public JSONObject getPackage(String str) {
        try {
            return CodePushUtils.getJsonObjectFromFile(CodePushUtils.appendPathComponent(getPackageFolderPath(str), "app.json"));
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:126:0x0235 A[SYNTHETIC, Splitter:B:126:0x0235] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x023d A[Catch:{ IOException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0242 A[Catch:{ IOException -> 0x0239 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0247 A[Catch:{ IOException -> 0x0239 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void downloadPackage(org.json.JSONObject r26, java.lang.String r27, com.microsoft.codepush.react.DownloadProgressCallback r28, java.lang.String r29) {
        /*
            r25 = this;
            r0 = r26
            r1 = r27
            r2 = r29
            java.lang.String r3 = "Error closing IO resources."
            java.lang.String r4 = "packageHash"
            r5 = 0
            java.lang.String r4 = r0.optString(r4, r5)
            r6 = r25
            java.lang.String r7 = r6.getPackageFolderPath(r4)
            java.lang.String r8 = "app.json"
            java.lang.String r8 = com.microsoft.codepush.react.CodePushUtils.appendPathComponent(r7, r8)
            boolean r9 = com.microsoft.codepush.react.FileUtils.fileAtPathExists(r7)
            if (r9 == 0) goto L_0x0024
            com.microsoft.codepush.react.FileUtils.deleteDirectoryAtPath(r7)
        L_0x0024:
            java.lang.String r9 = "downloadUrl"
            java.lang.String r9 = r0.optString(r9, r5)
            java.net.URL r10 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0222, all -> 0x021d }
            r10.<init>(r9)     // Catch:{ MalformedURLException -> 0x0222, all -> 0x021d }
            java.net.URLConnection r10 = r10.openConnection()     // Catch:{ MalformedURLException -> 0x0222, all -> 0x021d }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ MalformedURLException -> 0x0222, all -> 0x021d }
            java.lang.String r11 = "Accept-Encoding"
            java.lang.String r12 = "identity"
            r10.setRequestProperty(r11, r12)     // Catch:{ MalformedURLException -> 0x0216, all -> 0x0211 }
            java.io.BufferedInputStream r11 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x0216, all -> 0x0211 }
            java.io.InputStream r12 = r10.getInputStream()     // Catch:{ MalformedURLException -> 0x0216, all -> 0x0211 }
            r11.<init>(r12)     // Catch:{ MalformedURLException -> 0x0216, all -> 0x0211 }
            int r12 = r10.getContentLength()     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            long r12 = (long) r12     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.io.File r14 = new java.io.File     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.lang.String r15 = r25.getCodePushPath()     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            r14.<init>(r15)     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            r14.mkdirs()     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.io.File r15 = new java.io.File     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.lang.String r5 = "download.zip"
            r15.<init>(r14, r5)     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            r5.<init>(r15)     // Catch:{ MalformedURLException -> 0x0208, all -> 0x0202 }
            java.io.BufferedOutputStream r14 = new java.io.BufferedOutputStream     // Catch:{ MalformedURLException -> 0x01f9, all -> 0x01f3 }
            r6 = 262144(0x40000, float:3.67342E-40)
            r14.<init>(r5, r6)     // Catch:{ MalformedURLException -> 0x01f9, all -> 0x01f3 }
            r17 = r9
            byte[] r9 = new byte[r6]     // Catch:{ MalformedURLException -> 0x01f0, all -> 0x01ed }
            r6 = 4
            r19 = r3
            byte[] r3 = new byte[r6]     // Catch:{ MalformedURLException -> 0x01c0, all -> 0x01b8 }
            r20 = 0
            r22 = r7
            r6 = r20
        L_0x0078:
            r0 = 0
            r20 = r4
            r4 = 262144(0x40000, float:3.67342E-40)
            int r2 = r11.read(r9, r0, r4)     // Catch:{ MalformedURLException -> 0x01c0, all -> 0x01b8 }
            if (r2 < 0) goto L_0x00ca
            r23 = 4
            int r18 = (r6 > r23 ? 1 : (r6 == r23 ? 0 : -1))
            if (r18 >= 0) goto L_0x00ae
            r4 = r0
        L_0x008a:
            if (r4 >= r2) goto L_0x00ae
            int r0 = (int) r6
            int r0 = r0 + r4
            r23 = r8
            r8 = 4
            if (r0 < r8) goto L_0x0094
            goto L_0x00b1
        L_0x0094:
            byte r16 = r9[r4]     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            r3[r0] = r16     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            int r4 = r4 + 1
            r8 = r23
            r0 = 0
            goto L_0x008a
        L_0x009e:
            r0 = move-exception
            r16 = r5
            r5 = r14
            r2 = r19
            goto L_0x0233
        L_0x00a6:
            r0 = move-exception
            r16 = r5
            r5 = r10
            r2 = r19
            goto L_0x0228
        L_0x00ae:
            r23 = r8
            r8 = 4
        L_0x00b1:
            long r0 = (long) r2     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            long r6 = r6 + r0
            r0 = 0
            r14.write(r9, r0, r2)     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            com.microsoft.codepush.react.DownloadProgress r0 = new com.microsoft.codepush.react.DownloadProgress     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            r0.<init>(r12, r6)     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            r1 = r28
            r1.call(r0)     // Catch:{ MalformedURLException -> 0x00a6, all -> 0x009e }
            r1 = r27
            r2 = r29
            r4 = r20
            r8 = r23
            goto L_0x0078
        L_0x00ca:
            r23 = r8
            int r1 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x01c8
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ MalformedURLException -> 0x01c0, all -> 0x01b8 }
            int r1 = r1.getInt()     // Catch:{ MalformedURLException -> 0x01c0, all -> 0x01b8 }
            r2 = 1347093252(0x504b0304, float:1.36238899E10)
            r3 = 1
            if (r1 != r2) goto L_0x00e0
            r1 = r3
            goto L_0x00e1
        L_0x00e0:
            r1 = r0
        L_0x00e1:
            r14.close()     // Catch:{ IOException -> 0x01af }
            r5.close()     // Catch:{ IOException -> 0x01af }
            r11.close()     // Catch:{ IOException -> 0x01af }
            r10.disconnect()     // Catch:{ IOException -> 0x01af }
            if (r1 == 0) goto L_0x01a0
            java.lang.String r1 = r25.getUnzippedFolderPath()
            com.microsoft.codepush.react.FileUtils.unzipFile(r15, r1)
            com.microsoft.codepush.react.FileUtils.deleteFileOrFolderSilently(r15)
            java.lang.String r2 = "hotcodepush.json"
            java.lang.String r2 = com.microsoft.codepush.react.CodePushUtils.appendPathComponent(r1, r2)
            boolean r4 = com.microsoft.codepush.react.FileUtils.fileAtPathExists(r2)
            if (r4 == 0) goto L_0x0117
            java.lang.String r5 = r25.getCurrentPackageFolderPath()
            r6 = r22
            com.microsoft.codepush.react.CodePushUpdateUtils.copyNecessaryFilesFromCurrentPackage(r2, r5, r6)
            java.io.File r5 = new java.io.File
            r5.<init>(r2)
            r5.delete()
            goto L_0x0119
        L_0x0117:
            r6 = r22
        L_0x0119:
            com.microsoft.codepush.react.FileUtils.copyDirectoryContents(r1, r6)
            com.microsoft.codepush.react.FileUtils.deleteFileAtPathSilently(r1)
            r1 = r27
            java.lang.String r2 = com.microsoft.codepush.react.CodePushUpdateUtils.findJSBundleInUpdateContents(r6, r1)
            if (r2 == 0) goto L_0x0184
            boolean r1 = com.microsoft.codepush.react.FileUtils.fileAtPathExists(r23)
            if (r1 == 0) goto L_0x0138
            java.io.File r1 = new java.io.File
            r5 = r23
            r1.<init>(r5)
            r1.delete()
            goto L_0x013a
        L_0x0138:
            r5 = r23
        L_0x013a:
            if (r4 == 0) goto L_0x0144
            java.lang.String r1 = "Applying diff update."
            com.microsoft.codepush.react.CodePushUtils.log((java.lang.String) r1)
        L_0x0141:
            r1 = r29
            goto L_0x014a
        L_0x0144:
            java.lang.String r1 = "Applying full update."
            com.microsoft.codepush.react.CodePushUtils.log((java.lang.String) r1)
            goto L_0x0141
        L_0x014a:
            if (r1 == 0) goto L_0x014d
            r0 = r3
        L_0x014d:
            java.lang.String r3 = com.microsoft.codepush.react.CodePushUpdateUtils.getSignatureFilePath(r6)
            boolean r3 = com.microsoft.codepush.react.FileUtils.fileAtPathExists(r3)
            if (r0 == 0) goto L_0x016a
            if (r3 == 0) goto L_0x0162
            r0 = r20
            com.microsoft.codepush.react.CodePushUpdateUtils.verifyFolderHash(r6, r0)
            com.microsoft.codepush.react.CodePushUpdateUtils.verifyUpdateSignature(r6, r0, r1)
            goto L_0x017c
        L_0x0162:
            com.microsoft.codepush.react.CodePushInvalidUpdateException r0 = new com.microsoft.codepush.react.CodePushInvalidUpdateException
            java.lang.String r1 = "Error! Public key was provided but there is no JWT signature within app bundle to verify. Possible reasons, why that might happen: \n1. You've been released CodePush bundle update using version of CodePush CLI that is not support code signing.\n2. You've been released CodePush bundle update without providing --privateKeyPath option."
            r0.<init>(r1)
            throw r0
        L_0x016a:
            r0 = r20
            if (r3 == 0) goto L_0x0177
            java.lang.String r1 = "Warning! JWT signature exists in codepush update but code integrity check couldn't be performed because there is no public key configured. Please ensure that public key is properly configured within your application."
            com.microsoft.codepush.react.CodePushUtils.log((java.lang.String) r1)
            com.microsoft.codepush.react.CodePushUpdateUtils.verifyFolderHash(r6, r0)
            goto L_0x017c
        L_0x0177:
            if (r4 == 0) goto L_0x017c
            com.microsoft.codepush.react.CodePushUpdateUtils.verifyFolderHash(r6, r0)
        L_0x017c:
            java.lang.String r0 = "bundlePath"
            r3 = r26
            com.microsoft.codepush.react.CodePushUtils.setJSONValueForKey(r3, r0, r2)
            goto L_0x01ab
        L_0x0184:
            com.microsoft.codepush.react.CodePushInvalidUpdateException r0 = new com.microsoft.codepush.react.CodePushInvalidUpdateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Update is invalid - A JS bundle file named \""
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "\" could not be found within the downloaded contents. Please check that you are releasing your CodePush updates using the exact same JS bundle file name that was shipped with your app's binary."
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x01a0:
            r3 = r26
            r1 = r27
            r6 = r22
            r5 = r23
            com.microsoft.codepush.react.FileUtils.moveFile(r15, r6, r1)
        L_0x01ab:
            com.microsoft.codepush.react.CodePushUtils.writeJsonToFile(r3, r5)
            return
        L_0x01af:
            r0 = move-exception
            com.microsoft.codepush.react.CodePushUnknownException r1 = new com.microsoft.codepush.react.CodePushUnknownException
            r2 = r19
            r1.<init>(r2, r0)
            throw r1
        L_0x01b8:
            r0 = move-exception
            r2 = r19
        L_0x01bb:
            r16 = r5
        L_0x01bd:
            r5 = r14
            goto L_0x0233
        L_0x01c0:
            r0 = move-exception
            r2 = r19
        L_0x01c3:
            r16 = r5
            r5 = r10
            goto L_0x0228
        L_0x01c8:
            r2 = r19
            com.microsoft.codepush.react.CodePushUnknownException r0 = new com.microsoft.codepush.react.CodePushUnknownException     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            r1.<init>()     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            java.lang.String r3 = "Received "
            r1.append(r3)     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            r1.append(r6)     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            java.lang.String r3 = " bytes, expected "
            r1.append(r3)     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            r1.append(r12)     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            java.lang.String r1 = r1.toString()     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            r0.<init>(r1)     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
            throw r0     // Catch:{ MalformedURLException -> 0x01eb, all -> 0x01e9 }
        L_0x01e9:
            r0 = move-exception
            goto L_0x01bb
        L_0x01eb:
            r0 = move-exception
            goto L_0x01c3
        L_0x01ed:
            r0 = move-exception
            r2 = r3
            goto L_0x01bb
        L_0x01f0:
            r0 = move-exception
            r2 = r3
            goto L_0x01c3
        L_0x01f3:
            r0 = move-exception
            r2 = r3
            r16 = r5
            r5 = 0
            goto L_0x0233
        L_0x01f9:
            r0 = move-exception
            r2 = r3
            r17 = r9
            r16 = r5
            r5 = r10
            r14 = 0
            goto L_0x0228
        L_0x0202:
            r0 = move-exception
            r2 = r3
            r5 = 0
        L_0x0205:
            r16 = 0
            goto L_0x0233
        L_0x0208:
            r0 = move-exception
            r2 = r3
            r17 = r9
            r5 = r10
        L_0x020d:
            r14 = 0
            r16 = 0
            goto L_0x0228
        L_0x0211:
            r0 = move-exception
            r2 = r3
            r5 = 0
        L_0x0214:
            r11 = 0
            goto L_0x0205
        L_0x0216:
            r0 = move-exception
            r2 = r3
            r17 = r9
            r5 = r10
        L_0x021b:
            r11 = 0
            goto L_0x020d
        L_0x021d:
            r0 = move-exception
            r2 = r3
            r5 = 0
            r10 = 0
            goto L_0x0214
        L_0x0222:
            r0 = move-exception
            r2 = r3
            r17 = r9
            r5 = 0
            goto L_0x021b
        L_0x0228:
            com.microsoft.codepush.react.CodePushMalformedDataException r1 = new com.microsoft.codepush.react.CodePushMalformedDataException     // Catch:{ all -> 0x0230 }
            r3 = r17
            r1.<init>((java.lang.String) r3, (java.net.MalformedURLException) r0)     // Catch:{ all -> 0x0230 }
            throw r1     // Catch:{ all -> 0x0230 }
        L_0x0230:
            r0 = move-exception
            r10 = r5
            goto L_0x01bd
        L_0x0233:
            if (r5 == 0) goto L_0x023b
            r5.close()     // Catch:{ IOException -> 0x0239 }
            goto L_0x023b
        L_0x0239:
            r0 = move-exception
            goto L_0x024b
        L_0x023b:
            if (r16 == 0) goto L_0x0240
            r16.close()     // Catch:{ IOException -> 0x0239 }
        L_0x0240:
            if (r11 == 0) goto L_0x0245
            r11.close()     // Catch:{ IOException -> 0x0239 }
        L_0x0245:
            if (r10 == 0) goto L_0x0251
            r10.disconnect()     // Catch:{ IOException -> 0x0239 }
            goto L_0x0251
        L_0x024b:
            com.microsoft.codepush.react.CodePushUnknownException r1 = new com.microsoft.codepush.react.CodePushUnknownException
            r1.<init>(r2, r0)
            throw r1
        L_0x0251:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateManager.downloadPackage(org.json.JSONObject, java.lang.String, com.microsoft.codepush.react.DownloadProgressCallback, java.lang.String):void");
    }

    public void installPackage(JSONObject jSONObject, boolean z) {
        String optString = jSONObject.optString("packageHash", (String) null);
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        String optString2 = currentPackageInfo.optString("currentPackage", (String) null);
        if (optString == null || !optString.equals(optString2)) {
            if (z) {
                String currentPackageFolderPath = getCurrentPackageFolderPath();
                if (currentPackageFolderPath != null) {
                    FileUtils.deleteDirectoryAtPath(currentPackageFolderPath);
                }
            } else {
                String previousPackageHash = getPreviousPackageHash();
                if (previousPackageHash != null && !previousPackageHash.equals(optString)) {
                    FileUtils.deleteDirectoryAtPath(getPackageFolderPath(previousPackageHash));
                }
                CodePushUtils.setJSONValueForKey(currentPackageInfo, "previousPackage", currentPackageInfo.optString("currentPackage", (String) null));
            }
            CodePushUtils.setJSONValueForKey(currentPackageInfo, "currentPackage", optString);
            updateCurrentPackageInfo(currentPackageInfo);
        }
    }

    public void rollbackPackage() {
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        FileUtils.deleteDirectoryAtPath(getCurrentPackageFolderPath());
        CodePushUtils.setJSONValueForKey(currentPackageInfo, "currentPackage", currentPackageInfo.optString("previousPackage", (String) null));
        CodePushUtils.setJSONValueForKey(currentPackageInfo, "previousPackage", (Object) null);
        updateCurrentPackageInfo(currentPackageInfo);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.io.FileOutputStream} */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007d A[SYNTHETIC, Splitter:B:44:0x007d] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0085 A[Catch:{ IOException -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008a A[Catch:{ IOException -> 0x0081 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x008f A[Catch:{ IOException -> 0x0081 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void downloadAndReplaceCurrentBundle(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r0 = "Error closing IO resources."
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0071, all -> 0x006c }
            r2.<init>(r10)     // Catch:{ MalformedURLException -> 0x0071, all -> 0x006c }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ MalformedURLException -> 0x0071, all -> 0x006c }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ MalformedURLException -> 0x0071, all -> 0x006c }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x0068, all -> 0x0064 }
            java.io.InputStream r4 = r2.getInputStream()     // Catch:{ MalformedURLException -> 0x0068, all -> 0x0064 }
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0068, all -> 0x0064 }
            java.io.File r4 = new java.io.File     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            java.lang.String r11 = r9.getCurrentPackageBundlePath(r11)     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            r4.<init>(r11)     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            r4.delete()     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            r11.<init>(r4)     // Catch:{ MalformedURLException -> 0x005f, all -> 0x005c }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ MalformedURLException -> 0x0057, all -> 0x0055 }
            r5 = 262144(0x40000, float:3.67342E-40)
            r4.<init>(r11, r5)     // Catch:{ MalformedURLException -> 0x0057, all -> 0x0055 }
            byte[] r1 = new byte[r5]     // Catch:{ MalformedURLException -> 0x003f }
        L_0x0031:
            r6 = 0
            int r7 = r3.read(r1, r6, r5)     // Catch:{ MalformedURLException -> 0x003f }
            if (r7 < 0) goto L_0x0041
            r4.write(r1, r6, r7)     // Catch:{ MalformedURLException -> 0x003f }
            goto L_0x0031
        L_0x003c:
            r10 = move-exception
            r1 = r4
            goto L_0x007b
        L_0x003f:
            r1 = move-exception
            goto L_0x0075
        L_0x0041:
            r4.close()     // Catch:{ IOException -> 0x004e }
            r11.close()     // Catch:{ IOException -> 0x004e }
            r3.close()     // Catch:{ IOException -> 0x004e }
            r2.disconnect()     // Catch:{ IOException -> 0x004e }
            return
        L_0x004e:
            r10 = move-exception
            com.microsoft.codepush.react.CodePushUnknownException r11 = new com.microsoft.codepush.react.CodePushUnknownException
            r11.<init>(r0, r10)
            throw r11
        L_0x0055:
            r10 = move-exception
            goto L_0x007b
        L_0x0057:
            r4 = move-exception
            r8 = r4
            r4 = r1
            r1 = r8
            goto L_0x0075
        L_0x005c:
            r10 = move-exception
            r11 = r1
            goto L_0x007b
        L_0x005f:
            r11 = move-exception
            r4 = r1
        L_0x0061:
            r1 = r11
            r11 = r4
            goto L_0x0075
        L_0x0064:
            r10 = move-exception
            r11 = r1
            r3 = r11
            goto L_0x007b
        L_0x0068:
            r11 = move-exception
            r3 = r1
        L_0x006a:
            r4 = r3
            goto L_0x0061
        L_0x006c:
            r10 = move-exception
            r11 = r1
            r2 = r11
            r3 = r2
            goto L_0x007b
        L_0x0071:
            r11 = move-exception
            r2 = r1
            r3 = r2
            goto L_0x006a
        L_0x0075:
            com.microsoft.codepush.react.CodePushMalformedDataException r5 = new com.microsoft.codepush.react.CodePushMalformedDataException     // Catch:{ all -> 0x003c }
            r5.<init>((java.lang.String) r10, (java.net.MalformedURLException) r1)     // Catch:{ all -> 0x003c }
            throw r5     // Catch:{ all -> 0x003c }
        L_0x007b:
            if (r1 == 0) goto L_0x0083
            r1.close()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0083
        L_0x0081:
            r10 = move-exception
            goto L_0x0093
        L_0x0083:
            if (r11 == 0) goto L_0x0088
            r11.close()     // Catch:{ IOException -> 0x0081 }
        L_0x0088:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ IOException -> 0x0081 }
        L_0x008d:
            if (r2 == 0) goto L_0x0099
            r2.disconnect()     // Catch:{ IOException -> 0x0081 }
            goto L_0x0099
        L_0x0093:
            com.microsoft.codepush.react.CodePushUnknownException r11 = new com.microsoft.codepush.react.CodePushUnknownException
            r11.<init>(r0, r10)
            throw r11
        L_0x0099:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateManager.downloadAndReplaceCurrentBundle(java.lang.String, java.lang.String):void");
    }

    public void clearUpdates() {
        FileUtils.deleteDirectoryAtPath(getCodePushPath());
    }
}
