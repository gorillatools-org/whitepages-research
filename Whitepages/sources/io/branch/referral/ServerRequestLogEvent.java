package io.branch.referral;

import android.content.Context;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.ServerRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServerRequestLogEvent extends ServerRequest {
    public void clearCallbacks() {
    }

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldAddDMAParams() {
        return true;
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return true;
    }

    public ServerRequestLogEvent(Context context, Defines$RequestPath defines$RequestPath, String str, HashMap hashMap, JSONObject jSONObject, JSONObject jSONObject2, List list) {
        super(context, defines$RequestPath);
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(Defines$Jsonkey.Name.getKey(), str);
            if (jSONObject2.length() > 0) {
                jSONObject3.put(Defines$Jsonkey.CustomData.getKey(), jSONObject2);
            }
            if (jSONObject.length() > 0) {
                jSONObject3.put(Defines$Jsonkey.EventData.getKey(), jSONObject);
            }
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    jSONObject3.put((String) entry.getKey(), entry.getValue());
                }
            }
            if (list.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                jSONObject3.put(Defines$Jsonkey.ContentItems.getKey(), jSONArray);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((BranchUniversalObject) it.next()).convertToJson());
                }
            }
            setPost(jSONObject3);
        } catch (JSONException e) {
            BranchLogger.w("Caught JSONException " + e.getMessage());
        }
        updateEnvironment(context, jSONObject3);
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject jSONObject) {
        super.setPost(jSONObject);
        this.prefHelper_.loadPartnerParams(jSONObject);
    }

    public ServerRequest.BRANCH_API_VERSION getBranchRemoteAPIVersion() {
        return ServerRequest.BRANCH_API_VERSION.V2;
    }
}
