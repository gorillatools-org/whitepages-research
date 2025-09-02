package com.facebook.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class BundleJSONConverter {
    public static final BundleJSONConverter INSTANCE = new BundleJSONConverter();
    private static final Map SETTERS;

    public interface Setter {
        void setOnBundle(Bundle bundle, String str, Object obj);
    }

    private BundleJSONConverter() {
    }

    static {
        HashMap hashMap = new HashMap();
        SETTERS = hashMap;
        hashMap.put(Boolean.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            }
        });
        hashMap.put(Integer.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                bundle.putInt(str, ((Integer) obj).intValue());
            }
        });
        hashMap.put(Long.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                bundle.putLong(str, ((Long) obj).longValue());
            }
        });
        hashMap.put(Double.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                bundle.putDouble(str, ((Double) obj).doubleValue());
            }
        });
        hashMap.put(String.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                bundle.putString(str, (String) obj);
            }
        });
        hashMap.put(String[].class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                throw new IllegalArgumentException("Unexpected type from JSON");
            }
        });
        hashMap.put(JSONArray.class, new Setter() {
            public void setOnBundle(Bundle bundle, String str, Object obj) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                Intrinsics.checkNotNullParameter(str, "key");
                Intrinsics.checkNotNullParameter(obj, "value");
                JSONArray jSONArray = (JSONArray) obj;
                ArrayList arrayList = new ArrayList();
                if (jSONArray.length() == 0) {
                    bundle.putStringArrayList(str, arrayList);
                    return;
                }
                int length = jSONArray.length();
                int i = 0;
                while (i < length) {
                    Object obj2 = jSONArray.get(i);
                    if (obj2 instanceof String) {
                        arrayList.add(obj2);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Unexpected type in an array: " + obj2.getClass());
                    }
                }
                bundle.putStringArrayList(str, arrayList);
            }
        });
    }

    public static final Bundle convertToBundle(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj != JSONObject.NULL) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(next, convertToBundle((JSONObject) obj));
                } else {
                    Setter setter = (Setter) SETTERS.get(obj.getClass());
                    if (setter != null) {
                        Intrinsics.checkNotNullExpressionValue(next, "key");
                        Intrinsics.checkNotNullExpressionValue(obj, "value");
                        setter.setOnBundle(bundle, next, obj);
                    } else {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                }
            }
        }
        return bundle;
    }
}
