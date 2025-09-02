package io.branch.referral;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchQRCodeCache {
    public ConcurrentHashMap cache = new ConcurrentHashMap();
    private final Context context_;
    private final SystemObserver systemObserver_;

    public static BranchQRCodeCache getInstance() {
        Branch instance = Branch.getInstance();
        if (instance == null) {
            return null;
        }
        return instance.getBranchQRCodeCache();
    }

    BranchQRCodeCache(Context context) {
        this.context_ = context;
        this.systemObserver_ = new SystemObserverInstance();
    }

    private class SystemObserverInstance extends SystemObserver {
        public SystemObserverInstance() {
        }
    }

    public void addQRCodeToCache(JSONObject jSONObject, byte[] bArr) {
        this.cache.clear();
        try {
            jSONObject.getJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE).remove(Defines$Jsonkey.CreationTimestamp.getKey());
            this.cache.put(jSONObject, bArr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public byte[] checkQRCodeCache(JSONObject jSONObject) {
        if (this.cache.isEmpty()) {
            return null;
        }
        try {
            jSONObject.getJSONObject(Constants.ScionAnalytics.MessageType.DATA_MESSAGE).remove(Defines$Jsonkey.CreationTimestamp.getKey());
            JSONObject jSONObject2 = (JSONObject) this.cache.keySet().iterator().next();
            if (areEqual(jSONObject, jSONObject2)) {
                return (byte[]) this.cache.get(jSONObject2);
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean areEqual(Object obj, Object obj2) {
        return convertJsonElement(obj).equals(convertJsonElement(obj2));
    }

    private static Object convertJsonElement(Object obj) {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, convertJsonElement(jSONObject.get(next)));
            }
            return hashMap;
        } else if (!(obj instanceof JSONArray)) {
            return obj;
        } else {
            JSONArray jSONArray = (JSONArray) obj;
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(convertJsonElement(jSONArray.get(i)));
            }
            return hashSet;
        }
    }
}
