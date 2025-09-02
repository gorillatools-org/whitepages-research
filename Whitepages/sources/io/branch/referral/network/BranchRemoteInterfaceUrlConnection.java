package io.branch.referral.network;

import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import io.branch.referral.PrefHelper;
import io.branch.referral.network.BranchRemoteInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class BranchRemoteInterfaceUrlConnection extends BranchRemoteInterface {
    private final Branch branch;
    private String lastRequestId = "";
    private int lastResponseCode = -1;
    private String lastResponseMessage = "";
    private PrefHelper prefHelper;
    private int retryLimit;

    public BranchRemoteInterfaceUrlConnection(Branch branch2) {
        this.branch = branch2;
        PrefHelper instance = PrefHelper.getInstance(branch2.getApplicationContext());
        this.prefHelper = instance;
        this.retryLimit = instance.getRetryCount();
    }

    public BranchRemoteInterface.BranchResponse doRestfulGet(String str) {
        return doRestfulGet(str, 0);
    }

    public BranchRemoteInterface.BranchResponse doRestfulPost(String str, JSONObject jSONObject) {
        return doRestfulPost(str, jSONObject, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        io.branch.referral.BranchLogger.e(getNetworkErrorMessage(r0, r9, r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0173, code lost:
        r3.disconnect();
        resetStats();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:53:0x00e8, B:58:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0104 A[SYNTHETIC, Splitter:B:58:0x0104] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0124 A[SYNTHETIC, Splitter:B:67:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0134 A[SYNTHETIC, Splitter:B:72:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0154 A[SYNTHETIC, Splitter:B:81:0x0154] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0173  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulGet(java.lang.String r9, int r10) {
        /*
            r8 = this;
            java.lang.String r0 = "?"
            io.branch.referral.Branch r1 = r8.branch
            android.content.Context r1 = r1.getApplicationContext()
            io.branch.referral.PrefHelper r1 = io.branch.referral.PrefHelper.getInstance(r1)
            r2 = -113(0xffffffffffffff8f, float:NaN)
            r3 = 0
            int r4 = r1.getTimeout()     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            int r5 = r1.getConnectTimeout()     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            boolean r6 = r9.contains(r0)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            if (r6 == 0) goto L_0x002f
            java.lang.String r0 = "&"
            goto L_0x002f
        L_0x0020:
            r9 = move-exception
            goto L_0x0171
        L_0x0023:
            r0 = move-exception
            goto L_0x00e8
        L_0x0026:
            r0 = move-exception
            goto L_0x00f9
        L_0x0029:
            r0 = move-exception
            goto L_0x0130
        L_0x002c:
            r0 = move-exception
            goto L_0x0160
        L_0x002f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r6.<init>()     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r6.append(r9)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r6.append(r0)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            java.lang.String r0 = "retryNumber"
            r6.append(r0)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            java.lang.String r0 = "="
            r6.append(r0)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r6.append(r10)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            java.lang.String r0 = r6.toString()     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            java.net.URL r6 = new java.net.URL     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r6.<init>(r0)     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            java.net.URLConnection r0 = r6.openConnection()     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ SocketException -> 0x002c, SocketTimeoutException -> 0x0029, InterruptedIOException -> 0x0026, IOException -> 0x0023 }
            r0.setConnectTimeout(r5)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r0.setReadTimeout(r4)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            io.branch.referral.Defines$HeaderKey r4 = io.branch.referral.Defines$HeaderKey.RequestId     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            java.lang.String r4 = r4.getKey()     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            java.lang.String r4 = r0.getHeaderField(r4)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            int r5 = r0.getResponseCode()     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r6 = 500(0x1f4, float:7.0E-43)
            if (r5 < r6) goto L_0x00a7
            int r6 = r8.retryLimit     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            if (r10 >= r6) goto L_0x00a7
            int r3 = r1.getRetryInterval()     // Catch:{ InterruptedException -> 0x0092 }
            long r3 = (long) r3     // Catch:{ InterruptedException -> 0x0092 }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x0092 }
            goto L_0x009a
        L_0x007b:
            r9 = move-exception
            r3 = r0
            goto L_0x0171
        L_0x007f:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x00e8
        L_0x0083:
            r2 = move-exception
            r3 = r0
            r0 = r2
            goto L_0x00f9
        L_0x0088:
            r2 = move-exception
            r3 = r0
            r0 = r2
            goto L_0x0130
        L_0x008d:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x0160
        L_0x0092:
            r3 = move-exception
            java.lang.String r3 = r8.getNetworkErrorMessage(r3, r9, r10)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            io.branch.referral.BranchLogger.e(r3)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
        L_0x009a:
            int r10 = r10 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r9 = r8.doRestfulGet(r9, r10)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r0.disconnect()
            r8.resetStats()
            return r9
        L_0x00a7:
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x00c1
            java.io.InputStream r6 = r0.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00bf }
            if (r6 == 0) goto L_0x00c1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r6 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x00bf }
            java.io.InputStream r7 = r0.getErrorStream()     // Catch:{ FileNotFoundException -> 0x00bf }
            java.lang.String r7 = r8.getResponseString(r7)     // Catch:{ FileNotFoundException -> 0x00bf }
            r6.<init>(r7, r5)     // Catch:{ FileNotFoundException -> 0x00bf }
            goto L_0x00db
        L_0x00bf:
            r6 = move-exception
            goto L_0x00cf
        L_0x00c1:
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r6 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x00bf }
            java.io.InputStream r7 = r0.getInputStream()     // Catch:{ FileNotFoundException -> 0x00bf }
            java.lang.String r7 = r8.getResponseString(r7)     // Catch:{ FileNotFoundException -> 0x00bf }
            r6.<init>(r7, r5)     // Catch:{ FileNotFoundException -> 0x00bf }
            goto L_0x00db
        L_0x00cf:
            java.lang.String r6 = r8.getNetworkErrorMessage(r6, r9, r10)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            io.branch.referral.BranchLogger.e(r6)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r6 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r6.<init>(r3, r5)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
        L_0x00db:
            java.lang.String r3 = com.google.android.gms.common.util.Strings.emptyToNull(r4)     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r6.requestId = r3     // Catch:{ SocketException -> 0x008d, SocketTimeoutException -> 0x0088, InterruptedIOException -> 0x0083, IOException -> 0x007f, all -> 0x007b }
            r0.disconnect()
            r8.resetStats()
            return r6
        L_0x00e8:
            java.lang.String r9 = r8.getNetworkErrorMessage(r0, r9, r10)     // Catch:{ all -> 0x0020 }
            io.branch.referral.BranchLogger.e(r9)     // Catch:{ all -> 0x0020 }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x0020 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x0020 }
            r9.<init>(r2, r10)     // Catch:{ all -> 0x0020 }
            throw r9     // Catch:{ all -> 0x0020 }
        L_0x00f9:
            java.lang.String r2 = r8.getNetworkErrorMessage(r0, r9, r10)     // Catch:{ all -> 0x0020 }
            io.branch.referral.BranchLogger.e(r2)     // Catch:{ all -> 0x0020 }
            int r2 = r8.retryLimit     // Catch:{ all -> 0x0020 }
            if (r10 >= r2) goto L_0x0124
            int r0 = r1.getRetryInterval()     // Catch:{ InterruptedException -> 0x010d }
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x010d }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x010d }
            goto L_0x0115
        L_0x010d:
            r0 = move-exception
            java.lang.String r0 = r8.getNetworkErrorMessage(r0, r9, r10)     // Catch:{ all -> 0x0020 }
            io.branch.referral.BranchLogger.e(r0)     // Catch:{ all -> 0x0020 }
        L_0x0115:
            int r10 = r10 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r9 = r8.doRestfulGet(r9, r10)     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x0123
            r3.disconnect()
            r8.resetStats()
        L_0x0123:
            return r9
        L_0x0124:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x0020 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x0020 }
            r0 = -120(0xffffffffffffff88, float:NaN)
            r9.<init>(r0, r10)     // Catch:{ all -> 0x0020 }
            throw r9     // Catch:{ all -> 0x0020 }
        L_0x0130:
            int r2 = r8.retryLimit     // Catch:{ all -> 0x0020 }
            if (r10 >= r2) goto L_0x0154
            int r0 = r1.getRetryInterval()     // Catch:{ InterruptedException -> 0x013d }
            long r0 = (long) r0     // Catch:{ InterruptedException -> 0x013d }
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x013d }
            goto L_0x0145
        L_0x013d:
            r0 = move-exception
            java.lang.String r0 = r8.getNetworkErrorMessage(r0, r9, r10)     // Catch:{ all -> 0x0020 }
            io.branch.referral.BranchLogger.e(r0)     // Catch:{ all -> 0x0020 }
        L_0x0145:
            int r10 = r10 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r9 = r8.doRestfulGet(r9, r10)     // Catch:{ all -> 0x0020 }
            if (r3 == 0) goto L_0x0153
            r3.disconnect()
            r8.resetStats()
        L_0x0153:
            return r9
        L_0x0154:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x0020 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x0020 }
            r0 = -111(0xffffffffffffff91, float:NaN)
            r9.<init>(r0, r10)     // Catch:{ all -> 0x0020 }
            throw r9     // Catch:{ all -> 0x0020 }
        L_0x0160:
            java.lang.String r9 = r8.getNetworkErrorMessage(r0, r9, r10)     // Catch:{ all -> 0x0020 }
            io.branch.referral.BranchLogger.e(r9)     // Catch:{ all -> 0x0020 }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r9 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x0020 }
            java.lang.String r10 = r0.getMessage()     // Catch:{ all -> 0x0020 }
            r9.<init>(r2, r10)     // Catch:{ all -> 0x0020 }
            throw r9     // Catch:{ all -> 0x0020 }
        L_0x0171:
            if (r3 == 0) goto L_0x0179
            r3.disconnect()
            r8.resetStats()
        L_0x0179:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulGet(java.lang.String, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: javax.net.ssl.HttpsURLConnection} */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0284, code lost:
        r11.disconnect();
        resetStats();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x028b, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0296, code lost:
        throw new io.branch.referral.network.BranchRemoteInterface.BranchRemoteException(-113, r3.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0297, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0298, code lost:
        r5 = r3;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        java.lang.Thread.sleep((long) r6.getRetryInterval());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02b0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02b2, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        io.branch.referral.BranchLogger.e(getNetworkErrorMessage(r0, r2, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02ba, code lost:
        r2 = doRestfulPost(r2, r5, r4 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02bf, code lost:
        if (r11 != null) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02c1, code lost:
        r11.disconnect();
        resetStats();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02c7, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02c8, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02d3, code lost:
        throw new io.branch.referral.network.BranchRemoteInterface.BranchRemoteException(-120, r3.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02d4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02d5, code lost:
        r5 = r3;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0312, code lost:
        if (r11 != null) goto L_0x0314;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0314, code lost:
        r11.disconnect();
        resetStats();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x031a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0088, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0089, code lost:
        r2 = r0;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008e, code lost:
        r3 = r0;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0092, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0093, code lost:
        r3 = r0;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0097, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0098, code lost:
        r5 = r3;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009e, code lost:
        r5 = r3;
        r11 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0212, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0213, code lost:
        r2 = r0;
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0218, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0219, code lost:
        r11 = null;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x021d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x021e, code lost:
        r11 = null;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0230, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        io.branch.referral.BranchLogger.e(getNetworkErrorMessage(r3, r2, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0239, code lost:
        if ((r3 instanceof android.os.NetworkOnMainThreadException) == false) goto L_0x0250;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x023b, code lost:
        io.branch.referral.BranchLogger.e("Cannot make network request on main thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x024b, code lost:
        throw new io.branch.referral.network.BranchRemoteInterface.BranchRemoteException(-121, r3.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x024c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x024d, code lost:
        r2 = r0;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x025b, code lost:
        throw new io.branch.referral.network.BranchRemoteInterface.BranchRemoteException(-122, r3.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x025c, code lost:
        io.branch.referral.BranchLogger.e(getNetworkErrorMessage(r3, r2, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0265, code lost:
        if (r4 >= r1.retryLimit) goto L_0x028b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        java.lang.Thread.sleep((long) r6.getRetryInterval());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0271, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0273, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        io.branch.referral.BranchLogger.e(getNetworkErrorMessage(r0, r2, r4));
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:81:0x0230, B:92:0x0267] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x028b A[SYNTHETIC, Splitter:B:103:0x028b] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02a6 A[SYNTHETIC, Splitter:B:110:0x02a6] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02c8 A[SYNTHETIC, Splitter:B:121:0x02c8] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x02e4 A[SYNTHETIC, Splitter:B:128:0x02e4] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0306 A[SYNTHETIC, Splitter:B:139:0x0306] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0088 A[ExcHandler: all (r0v19 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:10:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008d A[ExcHandler: Exception (r0v18 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:10:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0092 A[ExcHandler: IOException (r0v17 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:10:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0212 A[ExcHandler: all (r0v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:6:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0218 A[ExcHandler: Exception (r0v9 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:6:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x021d A[ExcHandler: IOException (r0v8 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:6:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x023b A[Catch:{ InterruptedException -> 0x0271, all -> 0x024c }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0250 A[Catch:{ InterruptedException -> 0x0271, all -> 0x024c }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0267 A[SYNTHETIC, Splitter:B:92:0x0267] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.network.BranchRemoteInterface.BranchResponse doRestfulPost(java.lang.String r17, org.json.JSONObject r18, int r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            java.lang.String r5 = "application/json"
            io.branch.referral.Branch r6 = r1.branch
            android.content.Context r6 = r6.getApplicationContext()
            io.branch.referral.PrefHelper r6 = io.branch.referral.PrefHelper.getInstance(r6)
            int r7 = r6.getTimeout()
            int r8 = r6.getConnectTimeout()
            java.lang.String r9 = "retryNumber"
            r3.put(r9, r4)     // Catch:{ JSONException -> 0x0022 }
            goto L_0x0050
        L_0x0022:
            r0 = move-exception
            r9 = r0
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Caught JSONException, retry number: "
            r10.append(r11)
            r10.append(r4)
            java.lang.String r11 = " "
            r10.append(r11)
            java.lang.String r11 = r9.getMessage()
            r10.append(r11)
            java.lang.String r11 = " stacktrace: "
            r10.append(r11)
            java.lang.String r9 = io.branch.referral.BranchLogger.stackTraceToString(r9)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            io.branch.referral.BranchLogger.e(r9)
        L_0x0050:
            r9 = 102(0x66, float:1.43E-43)
            r10 = 1
            android.net.TrafficStats.setThreadStatsTag(r9)     // Catch:{ SocketTimeoutException -> 0x02d4, InterruptedIOException -> 0x0297, IOException -> 0x021d, Exception -> 0x0218, all -> 0x0212 }
            java.net.URL r9 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x0229, InterruptedIOException -> 0x0222, IOException -> 0x021d, Exception -> 0x0218, all -> 0x0212 }
            r9.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x0229, InterruptedIOException -> 0x0222, IOException -> 0x021d, Exception -> 0x0218, all -> 0x0212 }
            java.net.URLConnection r9 = r9.openConnection()     // Catch:{ SocketTimeoutException -> 0x0229, InterruptedIOException -> 0x0222, IOException -> 0x021d, Exception -> 0x0218, all -> 0x0212 }
            javax.net.ssl.HttpsURLConnection r9 = (javax.net.ssl.HttpsURLConnection) r9     // Catch:{ SocketTimeoutException -> 0x0229, InterruptedIOException -> 0x0222, IOException -> 0x021d, Exception -> 0x0218, all -> 0x0212 }
            r9.setConnectTimeout(r8)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.setReadTimeout(r7)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.setDoInput(r10)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.setDoOutput(r10)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.Defines$Jsonkey r7 = io.branch.referral.Defines$Jsonkey.QRCodeTag     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r8 = r7.getKey()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            boolean r8 = r2.contains(r8)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r12 = "Accept"
            java.lang.String r13 = "Content-Type"
            if (r8 == 0) goto L_0x00a3
            java.lang.String r5 = "application/x-www-form-urlencoded"
            r9.setRequestProperty(r13, r5)     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r5 = "image/*"
            r9.setRequestProperty(r12, r5)     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            goto L_0x00a9
        L_0x0088:
            r0 = move-exception
            r2 = r0
            r11 = r9
            goto L_0x0312
        L_0x008d:
            r0 = move-exception
            r3 = r0
            r11 = r9
            goto L_0x0230
        L_0x0092:
            r0 = move-exception
            r3 = r0
            r11 = r9
            goto L_0x025c
        L_0x0097:
            r0 = move-exception
            r5 = r3
            r11 = r9
        L_0x009a:
            r3 = r0
            goto L_0x029b
        L_0x009d:
            r0 = move-exception
            r5 = r3
            r11 = r9
        L_0x00a0:
            r3 = r0
            goto L_0x02d9
        L_0x00a3:
            r9.setRequestProperty(r13, r5)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.setRequestProperty(r12, r5)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
        L_0x00a9:
            java.lang.String r5 = "POST"
            r9.setRequestMethod(r5)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.io.OutputStream r8 = r9.getOutputStream()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r5.<init>(r8)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r8 = r18.toString()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r5.write(r8)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r5.flush()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r5.close()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.Defines$HeaderKey r5 = io.branch.referral.Defines$HeaderKey.RequestId     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r5 = r5.getKey()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r5 = r9.getHeaderField(r5)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r1.lastRequestId = r5     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            int r8 = r9.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r1.lastResponseCode = r8     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r12 = r9.getResponseMessage()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r1.lastResponseMessage = r12     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r12.<init>()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r13 = "lastResponseMessage "
            r12.append(r13)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r13 = r1.lastResponseMessage     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r12.append(r13)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            java.lang.String r12 = r12.toString()     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.BranchLogger.d(r12)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r12 = 500(0x1f4, float:7.0E-43)
            if (r8 < r12) goto L_0x0119
            int r12 = r1.retryLimit     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            if (r4 >= r12) goto L_0x0119
            int r5 = r6.getRetryInterval()     // Catch:{ InterruptedException -> 0x0103 }
            long r7 = (long) r5     // Catch:{ InterruptedException -> 0x0103 }
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x0103 }
            goto L_0x010c
        L_0x0103:
            r0 = move-exception
            r5 = r0
            java.lang.String r5 = r1.getNetworkErrorMessage(r5, r2, r4)     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.BranchLogger.e(r5)     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
        L_0x010c:
            int r4 = r4 + 1
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r2 = r1.doRestfulPost(r2, r3, r4)     // Catch:{ SocketTimeoutException -> 0x009d, InterruptedIOException -> 0x0097, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.disconnect()
            r16.resetStats()
            return r2
        L_0x0119:
            r12 = 200(0xc8, float:2.8E-43)
            java.lang.String r13 = "\nObject: "
            java.lang.String r14 = "\nRetry number: "
            java.lang.String r15 = "\nResponse Message: "
            java.lang.String r10 = "\nResponse Code: "
            if (r8 == r12) goto L_0x0178
            java.io.InputStream r12 = r9.getErrorStream()     // Catch:{ FileNotFoundException -> 0x0174 }
            if (r12 == 0) goto L_0x0178
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.<init>()     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r12 = "Branch Networking Error: \nURL: "
            r7.append(r12)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r2)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            int r10 = r1.lastResponseCode     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r15)     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r10 = r1.lastResponseMessage     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r14)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r4)     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r10 = "\nFinal attempt: true\nrequestId: "
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r10 = r1.lastRequestId     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r13)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.append(r1)     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException -> 0x0174 }
            io.branch.referral.BranchLogger.e(r7)     // Catch:{ FileNotFoundException -> 0x0174 }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r7 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ FileNotFoundException -> 0x0174 }
            java.io.InputStream r10 = r9.getErrorStream()     // Catch:{ FileNotFoundException -> 0x0174 }
            java.lang.String r10 = r1.getResponseString(r10)     // Catch:{ FileNotFoundException -> 0x0174 }
            r7.<init>(r10, r8)     // Catch:{ FileNotFoundException -> 0x0174 }
            goto L_0x0209
        L_0x0174:
            r0 = move-exception
            r3 = r0
            goto L_0x01fc
        L_0x0178:
            java.lang.String r7 = r7.getKey()     // Catch:{  }
            boolean r7 = r2.contains(r7)     // Catch:{  }
            if (r7 == 0) goto L_0x01b3
            java.io.InputStream r7 = r9.getInputStream()     // Catch:{  }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch:{  }
            java.io.ByteArrayOutputStream r12 = new java.io.ByteArrayOutputStream     // Catch:{  }
            r12.<init>()     // Catch:{  }
            android.graphics.Bitmap$CompressFormat r11 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{  }
            r3 = 100
            r7.compress(r11, r3, r12)     // Catch:{  }
            byte[] r3 = r12.toByteArray()     // Catch:{  }
            r7 = 0
            java.lang.String r3 = android.util.Base64.encodeToString(r3, r7)     // Catch:{  }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r7 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{  }
            r7.<init>(r3, r8)     // Catch:{  }
            goto L_0x01c1
        L_0x01a5:
            r0 = move-exception
            r5 = r18
            r3 = r0
            r11 = r9
            goto L_0x029b
        L_0x01ac:
            r0 = move-exception
            r5 = r18
            r3 = r0
            r11 = r9
            goto L_0x02d9
        L_0x01b3:
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r3 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{  }
            java.io.InputStream r7 = r9.getInputStream()     // Catch:{  }
            java.lang.String r7 = r1.getResponseString(r7)     // Catch:{  }
            r3.<init>(r7, r8)     // Catch:{  }
            r7 = r3
        L_0x01c1:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{  }
            r3.<init>()     // Catch:{  }
            java.lang.String r11 = "Branch Networking Success\nURL: "
            r3.append(r11)     // Catch:{  }
            r3.append(r2)     // Catch:{  }
            r3.append(r10)     // Catch:{  }
            int r10 = r1.lastResponseCode     // Catch:{  }
            r3.append(r10)     // Catch:{  }
            r3.append(r15)     // Catch:{  }
            java.lang.String r10 = r1.lastResponseMessage     // Catch:{  }
            r3.append(r10)     // Catch:{  }
            r3.append(r14)     // Catch:{  }
            r3.append(r4)     // Catch:{  }
            java.lang.String r10 = "\nrequestId: "
            r3.append(r10)     // Catch:{  }
            java.lang.String r10 = r1.lastRequestId     // Catch:{  }
            r3.append(r10)     // Catch:{  }
            r3.append(r13)     // Catch:{  }
            r3.append(r1)     // Catch:{  }
            java.lang.String r3 = r3.toString()     // Catch:{  }
            io.branch.referral.BranchLogger.v(r3)     // Catch:{  }
            goto L_0x0209
        L_0x01fc:
            java.lang.String r3 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.BranchLogger.e(r3)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r7 = new io.branch.referral.network.BranchRemoteInterface$BranchResponse     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r3 = 0
            r7.<init>(r3, r8)     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
        L_0x0209:
            r7.requestId = r5     // Catch:{ SocketTimeoutException -> 0x01ac, InterruptedIOException -> 0x01a5, IOException -> 0x0092, Exception -> 0x008d, all -> 0x0088 }
            r9.disconnect()
            r16.resetStats()
            return r7
        L_0x0212:
            r0 = move-exception
            r3 = 0
            r2 = r0
            r11 = r3
            goto L_0x0312
        L_0x0218:
            r0 = move-exception
            r3 = 0
            r11 = r3
            r3 = r0
            goto L_0x0230
        L_0x021d:
            r0 = move-exception
            r3 = 0
            r11 = r3
            r3 = r0
            goto L_0x025c
        L_0x0222:
            r0 = move-exception
            r3 = 0
            r5 = r18
        L_0x0226:
            r11 = r3
            goto L_0x009a
        L_0x0229:
            r0 = move-exception
            r3 = 0
            r5 = r18
        L_0x022d:
            r11 = r3
            goto L_0x00a0
        L_0x0230:
            java.lang.String r2 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r2)     // Catch:{ all -> 0x024c }
            boolean r2 = r3 instanceof android.os.NetworkOnMainThreadException     // Catch:{ all -> 0x024c }
            if (r2 == 0) goto L_0x0250
            java.lang.String r2 = "Cannot make network request on main thread."
            io.branch.referral.BranchLogger.e(r2)     // Catch:{ all -> 0x024c }
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r2 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x024c }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x024c }
            r4 = -121(0xffffffffffffff87, float:NaN)
            r2.<init>(r4, r3)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x024c:
            r0 = move-exception
            r2 = r0
            goto L_0x0312
        L_0x0250:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r2 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x024c }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x024c }
            r4 = -122(0xffffffffffffff86, float:NaN)
            r2.<init>(r4, r3)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x025c:
            java.lang.String r5 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r5)     // Catch:{ all -> 0x024c }
            int r5 = r1.retryLimit     // Catch:{ all -> 0x024c }
            if (r4 >= r5) goto L_0x028b
            int r3 = r6.getRetryInterval()     // Catch:{ InterruptedException -> 0x0271 }
            long r5 = (long) r3     // Catch:{ InterruptedException -> 0x0271 }
            java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x0271 }
        L_0x026f:
            r3 = 1
            goto L_0x027b
        L_0x0271:
            r0 = move-exception
            r3 = r0
            java.lang.String r3 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r3)     // Catch:{ all -> 0x024c }
            goto L_0x026f
        L_0x027b:
            int r4 = r4 + r3
            r5 = r18
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r2 = r1.doRestfulPost(r2, r5, r4)     // Catch:{ all -> 0x024c }
            if (r11 == 0) goto L_0x028a
            r11.disconnect()
            r16.resetStats()
        L_0x028a:
            return r2
        L_0x028b:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r2 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x024c }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x024c }
            r4 = -113(0xffffffffffffff8f, float:NaN)
            r2.<init>(r4, r3)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x0297:
            r0 = move-exception
            r5 = r3
            r3 = 0
            goto L_0x0226
        L_0x029b:
            java.lang.String r7 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r7)     // Catch:{ all -> 0x024c }
            int r7 = r1.retryLimit     // Catch:{ all -> 0x024c }
            if (r4 >= r7) goto L_0x02c8
            int r3 = r6.getRetryInterval()     // Catch:{ InterruptedException -> 0x02b0 }
            long r6 = (long) r3     // Catch:{ InterruptedException -> 0x02b0 }
            java.lang.Thread.sleep(r6)     // Catch:{ InterruptedException -> 0x02b0 }
        L_0x02ae:
            r3 = 1
            goto L_0x02ba
        L_0x02b0:
            r0 = move-exception
            r3 = r0
            java.lang.String r3 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r3)     // Catch:{ all -> 0x024c }
            goto L_0x02ae
        L_0x02ba:
            int r4 = r4 + r3
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r2 = r1.doRestfulPost(r2, r5, r4)     // Catch:{ all -> 0x024c }
            if (r11 == 0) goto L_0x02c7
            r11.disconnect()
            r16.resetStats()
        L_0x02c7:
            return r2
        L_0x02c8:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r2 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x024c }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x024c }
            r4 = -120(0xffffffffffffff88, float:NaN)
            r2.<init>(r4, r3)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x02d4:
            r0 = move-exception
            r5 = r3
            r3 = 0
            goto L_0x022d
        L_0x02d9:
            java.lang.String r7 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r7)     // Catch:{ all -> 0x024c }
            int r7 = r1.retryLimit     // Catch:{ all -> 0x024c }
            if (r4 >= r7) goto L_0x0306
            int r3 = r6.getRetryInterval()     // Catch:{ InterruptedException -> 0x02ee }
            long r6 = (long) r3     // Catch:{ InterruptedException -> 0x02ee }
            java.lang.Thread.sleep(r6)     // Catch:{ InterruptedException -> 0x02ee }
        L_0x02ec:
            r3 = 1
            goto L_0x02f8
        L_0x02ee:
            r0 = move-exception
            r3 = r0
            java.lang.String r3 = r1.getNetworkErrorMessage(r3, r2, r4)     // Catch:{ all -> 0x024c }
            io.branch.referral.BranchLogger.e(r3)     // Catch:{ all -> 0x024c }
            goto L_0x02ec
        L_0x02f8:
            int r4 = r4 + r3
            io.branch.referral.network.BranchRemoteInterface$BranchResponse r2 = r1.doRestfulPost(r2, r5, r4)     // Catch:{ all -> 0x024c }
            if (r11 == 0) goto L_0x0305
            r11.disconnect()
            r16.resetStats()
        L_0x0305:
            return r2
        L_0x0306:
            io.branch.referral.network.BranchRemoteInterface$BranchRemoteException r2 = new io.branch.referral.network.BranchRemoteInterface$BranchRemoteException     // Catch:{ all -> 0x024c }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x024c }
            r4 = -111(0xffffffffffffff91, float:NaN)
            r2.<init>(r4, r3)     // Catch:{ all -> 0x024c }
            throw r2     // Catch:{ all -> 0x024c }
        L_0x0312:
            if (r11 == 0) goto L_0x031a
            r11.disconnect()
            r16.resetStats()
        L_0x031a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterfaceUrlConnection.doRestfulPost(java.lang.String, org.json.JSONObject, int):io.branch.referral.network.BranchRemoteInterface$BranchResponse");
    }

    private void resetStats() {
        this.lastRequestId = "";
        this.lastResponseCode = -1;
        this.lastResponseMessage = "";
    }

    private String getResponseString(InputStream inputStream) {
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        bufferedReader.close();
                        return sb.toString();
                    }
                }
            } catch (IOException e) {
                BranchLogger.d(e.getMessage());
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getNetworkErrorMessage(Exception exc, String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Branch Networking Error: \nURL: ");
        sb.append(str);
        sb.append("\nResponse Code: ");
        sb.append(this.lastResponseCode);
        sb.append("\nResponse Message: ");
        sb.append(this.lastResponseMessage);
        sb.append("\nCaught exception type: ");
        sb.append(exc.getClass().getCanonicalName());
        sb.append("\nRetry number: ");
        sb.append(i);
        sb.append("\nrequestId: ");
        sb.append(this.lastRequestId);
        sb.append("\nFinal attempt: ");
        sb.append(i >= this.retryLimit);
        sb.append("\nObject: ");
        sb.append(this);
        sb.append("\nException Message: ");
        sb.append(exc.getMessage());
        sb.append("\nStacktrace: ");
        sb.append(BranchLogger.stackTraceToString(exc));
        return sb.toString();
    }
}
