package io.branch.referral.network;

import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import io.branch.referral.Defines$Jsonkey;
import io.branch.referral.ServerRequestQueue;
import io.branch.referral.ServerResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BranchRemoteInterface {
    public abstract BranchResponse doRestfulGet(String str);

    public abstract BranchResponse doRestfulPost(String str, JSONObject jSONObject);

    public final ServerResponse make_restful_get(String str, JSONObject jSONObject, String str2, String str3) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, -114, "", "");
        }
        String str4 = str + convertJSONtoString(jSONObject);
        long currentTimeMillis = System.currentTimeMillis();
        BranchLogger.v("getting " + str4);
        try {
            BranchResponse doRestfulGet = doRestfulGet(str4);
            ServerResponse processEntityForJSON = processEntityForJSON(doRestfulGet, str2, doRestfulGet.requestId);
            if (Branch.getInstance() != null) {
                Branch.getInstance().requestQueue_.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return processEntityForJSON;
        } catch (BranchRemoteException e) {
            ServerResponse serverResponse = new ServerResponse(str2, e.branchErrorCode, "", e.branchErrorMessage);
            if (Branch.getInstance() != null) {
                Branch.getInstance().requestQueue_.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return serverResponse;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                Branch.getInstance().requestQueue_.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            throw th;
        }
    }

    public final ServerResponse make_restful_post(JSONObject jSONObject, String str, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, -114, "", "");
        }
        BranchLogger.v("posting to " + str);
        BranchLogger.v("Post value = " + jSONObject.toString());
        try {
            BranchResponse doRestfulPost = doRestfulPost(str, jSONObject);
            ServerResponse processEntityForJSON = processEntityForJSON(doRestfulPost, str2, doRestfulPost.requestId);
            if (Branch.getInstance() != null) {
                ServerRequestQueue serverRequestQueue = Branch.getInstance().requestQueue_;
                serverRequestQueue.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return processEntityForJSON;
        } catch (BranchRemoteException e) {
            ServerResponse serverResponse = new ServerResponse(str2, e.branchErrorCode, "", e.branchErrorMessage);
            if (Branch.getInstance() != null) {
                ServerRequestQueue serverRequestQueue2 = Branch.getInstance().requestQueue_;
                serverRequestQueue2.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return serverResponse;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                ServerRequestQueue serverRequestQueue3 = Branch.getInstance().requestQueue_;
                serverRequestQueue3.addExtraInstrumentationData(str2 + "-" + Defines$Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            throw th;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.ServerResponse processEntityForJSON(io.branch.referral.network.BranchRemoteInterface.BranchResponse r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            java.lang.String r0 = r4.responseData
            int r4 = r4.responseCode
            io.branch.referral.ServerResponse r1 = new io.branch.referral.ServerResponse
            java.lang.String r2 = ""
            r1.<init>(r5, r4, r6, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 != 0) goto L_0x002b
            java.util.Locale r2 = java.util.Locale.getDefault()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r4 = new java.lang.Object[]{r6, r4, r0}
            java.lang.String r6 = "Server returned: [%s] Status: [%d]; Data: %s"
            java.lang.String r4 = java.lang.String.format(r2, r6, r4)
            io.branch.referral.BranchLogger.v(r4)
            goto L_0x0038
        L_0x002b:
            java.lang.String r4 = "returned %s"
            java.lang.Object[] r6 = new java.lang.Object[]{r0}
            java.lang.String r4 = java.lang.String.format(r4, r6)
            io.branch.referral.BranchLogger.v(r4)
        L_0x0038:
            if (r0 == 0) goto L_0x009b
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0043 }
            r4.<init>(r0)     // Catch:{ JSONException -> 0x0043 }
            r1.setPost(r4)     // Catch:{ JSONException -> 0x0043 }
            goto L_0x009b
        L_0x0043:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x004c }
            r4.<init>(r0)     // Catch:{ JSONException -> 0x004c }
            r1.setPost(r4)     // Catch:{ JSONException -> 0x004c }
            goto L_0x009b
        L_0x004c:
            r4 = move-exception
            io.branch.referral.Defines$Jsonkey r6 = io.branch.referral.Defines$Jsonkey.QRCodeTag
            java.lang.String r6 = r6.getKey()
            boolean r5 = r5.contains(r6)
            java.lang.String r6 = "Caught JSONException "
            if (r5 == 0) goto L_0x0085
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x006d }
            r4.<init>()     // Catch:{ JSONException -> 0x006d }
            io.branch.referral.Defines$Jsonkey r5 = io.branch.referral.Defines$Jsonkey.QRCodeResponseString     // Catch:{ JSONException -> 0x006d }
            java.lang.String r5 = r5.getKey()     // Catch:{ JSONException -> 0x006d }
            r4.put(r5, r0)     // Catch:{ JSONException -> 0x006d }
            r1.setPost(r4)     // Catch:{ JSONException -> 0x006d }
            goto L_0x009b
        L_0x006d:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            java.lang.String r4 = r4.getMessage()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            io.branch.referral.BranchLogger.w(r4)
            goto L_0x009b
        L_0x0085:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            java.lang.String r4 = r4.getMessage()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            io.branch.referral.BranchLogger.w(r4)
        L_0x009b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterface.processEntityForJSON(io.branch.referral.network.BranchRemoteInterface$BranchResponse, java.lang.String, java.lang.String):io.branch.referral.ServerResponse");
    }

    private boolean addCommonParams(JSONObject jSONObject, String str) {
        try {
            if (!jSONObject.has(Defines$Jsonkey.UserData.getKey())) {
                String key = Defines$Jsonkey.SDK.getKey();
                jSONObject.put(key, "android" + Branch.getSdkVersionNumber());
            }
            if (str.equals("bnc_no_value")) {
                return false;
            }
            jSONObject.put(Defines$Jsonkey.BranchKey.getKey(), str);
            return true;
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
            return false;
        }
    }

    private String convertJSONtoString(JSONObject jSONObject) {
        JSONArray names;
        StringBuilder sb = new StringBuilder();
        if (!(jSONObject == null || (names = jSONObject.names()) == null)) {
            int length = names.length();
            boolean z = true;
            int i = 0;
            while (i < length) {
                try {
                    String string = names.getString(i);
                    if (z) {
                        sb.append("?");
                        z = false;
                    } else {
                        sb.append("&");
                    }
                    String string2 = jSONObject.getString(string);
                    sb.append(string);
                    sb.append("=");
                    sb.append(string2);
                    i++;
                } catch (JSONException e) {
                    BranchLogger.w("Caught JSONException " + e.getMessage());
                    return null;
                }
            }
        }
        return sb.toString();
    }

    public static class BranchResponse {
        String requestId;
        /* access modifiers changed from: private */
        public final int responseCode;
        /* access modifiers changed from: private */
        public final String responseData;

        public BranchResponse(String str, int i) {
            this.responseData = str;
            this.responseCode = i;
        }
    }

    public static class BranchRemoteException extends Exception {
        /* access modifiers changed from: private */
        public int branchErrorCode;
        /* access modifiers changed from: private */
        public String branchErrorMessage;

        public BranchRemoteException(int i, String str) {
            this.branchErrorCode = i;
            this.branchErrorMessage = str;
        }
    }
}
