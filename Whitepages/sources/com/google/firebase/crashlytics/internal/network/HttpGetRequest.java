package com.google.firebase.crashlytics.internal.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpGetRequest {
    private static final int DEFAULT_TIMEOUT_MS = 10000;
    private static final String METHOD_GET = "GET";
    private static final int READ_BUFFER_SIZE = 8192;
    private final Map<String, String> headers = new HashMap();
    private final Map<String, String> queryParams;
    private final String url;

    public HttpGetRequest(String str, Map<String, String> map) {
        this.url = str;
        this.queryParams = map;
    }

    public HttpGetRequest header(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public HttpGetRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.crashlytics.internal.network.HttpResponse execute() throws java.io.IOException {
        /*
            r5 = this;
            com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers.checkBlockingThread()
            r0 = 0
            java.lang.String r1 = r5.url     // Catch:{ all -> 0x0087 }
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.queryParams     // Catch:{ all -> 0x0087 }
            java.lang.String r1 = r5.createUrlWithParams(r1, r2)     // Catch:{ all -> 0x0087 }
            com.google.firebase.crashlytics.internal.Logger r2 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0087 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            r3.<init>()     // Catch:{ all -> 0x0087 }
            java.lang.String r4 = "GET Request URL: "
            r3.append(r4)     // Catch:{ all -> 0x0087 }
            r3.append(r1)     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0087 }
            r2.v(r3)     // Catch:{ all -> 0x0087 }
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x0087 }
            r2.<init>(r1)     // Catch:{ all -> 0x0087 }
            java.net.URLConnection r1 = r2.openConnection()     // Catch:{ all -> 0x0087 }
            javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ all -> 0x0087 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setReadTimeout(r2)     // Catch:{ all -> 0x0062 }
            r1.setConnectTimeout(r2)     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch:{ all -> 0x0062 }
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.headers     // Catch:{ all -> 0x0062 }
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x0062 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0062 }
        L_0x0046:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0064
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0062 }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x0062 }
            java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x0062 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0062 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0062 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0062 }
            r1.addRequestProperty(r4, r3)     // Catch:{ all -> 0x0062 }
            goto L_0x0046
        L_0x0062:
            r2 = move-exception
            goto L_0x0089
        L_0x0064:
            r1.connect()     // Catch:{ all -> 0x0062 }
            int r2 = r1.getResponseCode()     // Catch:{ all -> 0x0062 }
            java.io.InputStream r3 = r1.getInputStream()     // Catch:{ all -> 0x0062 }
            if (r3 == 0) goto L_0x0079
            java.lang.String r0 = r5.readStream(r3)     // Catch:{ all -> 0x0076 }
            goto L_0x0079
        L_0x0076:
            r2 = move-exception
            r0 = r3
            goto L_0x0089
        L_0x0079:
            if (r3 == 0) goto L_0x007e
            r3.close()
        L_0x007e:
            r1.disconnect()
            com.google.firebase.crashlytics.internal.network.HttpResponse r1 = new com.google.firebase.crashlytics.internal.network.HttpResponse
            r1.<init>(r2, r0)
            return r1
        L_0x0087:
            r2 = move-exception
            r1 = r0
        L_0x0089:
            if (r0 == 0) goto L_0x008e
            r0.close()
        L_0x008e:
            if (r1 == 0) goto L_0x0093
            r1.disconnect()
        L_0x0093:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.network.HttpGetRequest.execute():com.google.firebase.crashlytics.internal.network.HttpResponse");
    }

    private String createUrlWithParams(String str, Map<String, String> map) throws UnsupportedEncodingException {
        String createParamsString = createParamsString(map);
        if (createParamsString.isEmpty()) {
            return str;
        }
        if (str.contains("?")) {
            if (!str.endsWith("&")) {
                createParamsString = "&" + createParamsString;
            }
            return str + createParamsString;
        }
        return str + "?" + createParamsString;
    }

    private String createParamsString(Map<String, String> map) throws UnsupportedEncodingException {
        String str;
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        Map.Entry next = it.next();
        sb.append((String) next.getKey());
        sb.append("=");
        sb.append(next.getValue() != null ? URLEncoder.encode((String) next.getValue(), "UTF-8") : "");
        while (it.hasNext()) {
            Map.Entry next2 = it.next();
            sb.append("&");
            sb.append((String) next2.getKey());
            sb.append("=");
            if (next2.getValue() != null) {
                str = URLEncoder.encode((String) next2.getValue(), "UTF-8");
            } else {
                str = "";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }
}
