package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class UniversalResourceAnalyser {
    private static UniversalResourceAnalyser instance;
    /* access modifiers changed from: private */
    public static JSONObject skipURLFormats;
    private final JSONObject DEFAULT_SKIP_URL_LIST;
    private final ArrayList acceptURLFormats;

    public static UniversalResourceAnalyser getInstance(Context context) {
        if (instance == null) {
            instance = new UniversalResourceAnalyser(context);
        }
        return instance;
    }

    private UniversalResourceAnalyser(Context context) {
        JSONObject jSONObject = new JSONObject();
        this.DEFAULT_SKIP_URL_LIST = jSONObject;
        try {
            jSONObject.putOpt("version", 0);
            JSONArray jSONArray = new JSONArray();
            jSONObject.putOpt("uri_skip_list", jSONArray);
            jSONArray.put("^fb\\d+:((?!campaign_ids).)*$");
            jSONArray.put("^li\\d+:");
            jSONArray.put("^pdk\\d+:");
            jSONArray.put("^twitterkit-.*:");
            jSONArray.put("^com\\.googleusercontent\\.apps\\.\\d+-.*:\\/oauth");
            jSONArray.put("^(?i)(?!(http|https):).*(:|:.*\\b)(password|o?auth|o?auth.?token|access|access.?token)\\b");
            jSONArray.put("^(?i)((http|https):\\/\\/).*[\\/|?|#].*\\b(password|o?auth|o?auth.?token|access|access.?token)\\b");
        } catch (JSONException e) {
            BranchLogger.d(e.getMessage());
        }
        skipURLFormats = retrieveSkipURLFormats(context);
        this.acceptURLFormats = new ArrayList();
    }

    private JSONObject retrieveSkipURLFormats(Context context) {
        PrefHelper instance2 = PrefHelper.getInstance(context);
        JSONObject jSONObject = new JSONObject();
        String string = instance2.getString("skip_url_format_key");
        if (TextUtils.isEmpty(string) || "bnc_no_value".equals(string)) {
            return this.DEFAULT_SKIP_URL_LIST;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            BranchLogger.d(e.getMessage());
            return jSONObject;
        }
    }

    /* access modifiers changed from: package-private */
    public void checkAndUpdateSkipURLFormats(Context context) {
        try {
            new UrlSkipListUpdateTask(context).executeTask(new Void[0]);
        } catch (Exception e) {
            BranchLogger.d(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public String getStrippedURL(String str) {
        String str2;
        try {
            JSONArray optJSONArray = skipURLFormats.optJSONArray("uri_skip_list");
            if (optJSONArray != null) {
                int i = 0;
                while (true) {
                    if (i >= optJSONArray.length()) {
                        break;
                    }
                    try {
                        str2 = optJSONArray.getString(i);
                        if (Pattern.compile(str2).matcher(str).find()) {
                            break;
                        }
                        i++;
                    } catch (JSONException e) {
                        BranchLogger.d(e.getMessage());
                    }
                }
            }
            str2 = null;
            if (str2 == null) {
                if (this.acceptURLFormats.size() <= 0) {
                    return str;
                }
                Iterator it = this.acceptURLFormats.iterator();
                while (it.hasNext()) {
                    if (str.matches((String) it.next())) {
                        return str;
                    }
                }
            }
            return str2;
        } catch (Exception unused) {
            return str;
        }
    }

    private static class UrlSkipListUpdateTask extends BranchAsyncTask {
        private final int TIME_OUT;
        private final PrefHelper prefHelper;

        private UrlSkipListUpdateTask(Context context) {
            this.TIME_OUT = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.prefHelper = PrefHelper.getInstance(context);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x007c, code lost:
            if (r1 != null) goto L_0x006b;
         */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0082  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.json.JSONObject doInBackground(java.lang.Void... r8) {
            /*
                r7 = this;
                r8 = 0
                android.net.TrafficStats.setThreadStatsTag(r8)
                org.json.JSONObject r8 = new org.json.JSONObject
                r8.<init>()
                r0 = 0
                java.lang.String r1 = "%sdk/uriskiplist_v#.json"
                java.lang.String r2 = "%"
                java.lang.String r3 = io.branch.referral.PrefHelper.getCDNBaseUrl()     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.lang.String r3 = "#"
                org.json.JSONObject r4 = io.branch.referral.UniversalResourceAnalyser.skipURLFormats     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.lang.String r5 = "version"
                int r4 = r4.optInt(r5)     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                int r4 = r4 + 1
                java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                r2.<init>(r1)     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                java.net.URLConnection r1 = r2.openConnection()     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ Exception -> 0x0071, all -> 0x006f }
                r0 = 1500(0x5dc, float:2.102E-42)
                r1.setConnectTimeout(r0)     // Catch:{ Exception -> 0x0069 }
                r1.setReadTimeout(r0)     // Catch:{ Exception -> 0x0069 }
                int r0 = r1.getResponseCode()     // Catch:{ Exception -> 0x0069 }
                r2 = 200(0xc8, float:2.8E-43)
                if (r0 != r2) goto L_0x006b
                java.io.InputStream r0 = r1.getInputStream()     // Catch:{ Exception -> 0x0069 }
                if (r0 == 0) goto L_0x006b
                java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0069 }
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0069 }
                java.io.InputStream r3 = r1.getInputStream()     // Catch:{ Exception -> 0x0069 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0069 }
                r0.<init>(r2)     // Catch:{ Exception -> 0x0069 }
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0069 }
                java.lang.String r0 = r0.readLine()     // Catch:{ Exception -> 0x0069 }
                r2.<init>(r0)     // Catch:{ Exception -> 0x0069 }
                r8 = r2
                goto L_0x006b
            L_0x0066:
                r8 = move-exception
                r0 = r1
                goto L_0x0080
            L_0x0069:
                r0 = move-exception
                goto L_0x0075
            L_0x006b:
                r1.disconnect()
                goto L_0x007f
            L_0x006f:
                r8 = move-exception
                goto L_0x0080
            L_0x0071:
                r1 = move-exception
                r6 = r1
                r1 = r0
                r0 = r6
            L_0x0075:
                java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0066 }
                io.branch.referral.BranchLogger.d(r0)     // Catch:{ all -> 0x0066 }
                if (r1 == 0) goto L_0x007f
                goto L_0x006b
            L_0x007f:
                return r8
            L_0x0080:
                if (r0 == 0) goto L_0x0085
                r0.disconnect()
            L_0x0085:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.UniversalResourceAnalyser.UrlSkipListUpdateTask.doInBackground(java.lang.Void[]):org.json.JSONObject");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject.optInt("version") > UniversalResourceAnalyser.skipURLFormats.optInt("version")) {
                JSONObject unused = UniversalResourceAnalyser.skipURLFormats = jSONObject;
                this.prefHelper.setString("skip_url_format_key", UniversalResourceAnalyser.skipURLFormats.toString());
            }
        }
    }
}
