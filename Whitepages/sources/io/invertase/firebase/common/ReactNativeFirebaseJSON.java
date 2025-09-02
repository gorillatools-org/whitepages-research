package io.invertase.firebase.common;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReactNativeFirebaseJSON {
    private static ReactNativeFirebaseJSON sharedInstance = new ReactNativeFirebaseJSON();
    private JSONObject jsonObject;

    private ReactNativeFirebaseJSON() {
        try {
            this.jsonObject = new JSONObject("{\"crashlytics_auto_collection_enabled\":true,\"analytics_auto_collection_enabled\":true,\"google_analytics_automatic_screen_reporting_enabled\":true,\"perf_auto_collection_enabled\":true,\"crashlytics_debug_enabled\":false}");
        } catch (JSONException unused) {
        }
    }

    public static ReactNativeFirebaseJSON getSharedInstance() {
        return sharedInstance;
    }

    public boolean contains(String str) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.has(str);
    }

    public boolean getBooleanValue(String str, boolean z) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return z;
        }
        return jSONObject.optBoolean(str, z);
    }

    public int getIntValue(String str, int i) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return i;
        }
        return jSONObject.optInt(str, i);
    }

    public long getLongValue(String str, long j) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return j;
        }
        return jSONObject.optLong(str, j);
    }

    public String getStringValue(String str, String str2) {
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return str2;
        }
        return jSONObject.optString(str, str2);
    }

    public ArrayList<String> getArrayValue(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        JSONObject jSONObject = this.jsonObject;
        if (jSONObject == null) {
            return arrayList;
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public String getRawJSON() {
        return "{\"crashlytics_auto_collection_enabled\":true,\"analytics_auto_collection_enabled\":true,\"google_analytics_automatic_screen_reporting_enabled\":true,\"perf_auto_collection_enabled\":true,\"crashlytics_debug_enabled\":false}";
    }

    public WritableMap getAll() {
        WritableMap createMap = Arguments.createMap();
        JSONArray names = this.jsonObject.names();
        for (int i = 0; i < names.length(); i++) {
            try {
                String string = names.getString(i);
                SharedUtils.mapPutValue(string, this.jsonObject.get(string), createMap);
            } catch (JSONException unused) {
            }
        }
        return createMap;
    }
}
