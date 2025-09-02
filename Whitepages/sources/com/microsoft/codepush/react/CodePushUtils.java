package com.microsoft.codepush.react;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import java.io.File;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CodePushUtils {
    public static String appendPathComponent(String str, String str2) {
        return new File(str, str2).getAbsolutePath();
    }

    public static WritableArray convertJsonArrayToWritable(JSONArray jSONArray) {
        WritableArray createArray = Arguments.createArray();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONObject) {
                    createArray.pushMap(convertJsonObjectToWritable((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    createArray.pushArray(convertJsonArrayToWritable((JSONArray) obj));
                } else if (obj instanceof String) {
                    createArray.pushString((String) obj);
                } else if (obj instanceof Double) {
                    createArray.pushDouble(((Double) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    createArray.pushInt(((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    createArray.pushBoolean(((Boolean) obj).booleanValue());
                } else if (obj == null) {
                    createArray.pushNull();
                } else {
                    throw new CodePushUnknownException("Unrecognized object: " + obj);
                }
                i++;
            } catch (JSONException e) {
                throw new CodePushUnknownException(i + " should be within bounds of array " + jSONArray.toString(), e);
            }
        }
        return createArray;
    }

    public static WritableMap convertJsonObjectToWritable(JSONObject jSONObject) {
        WritableMap createMap = Arguments.createMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof JSONObject) {
                    createMap.putMap(next, convertJsonObjectToWritable((JSONObject) obj));
                } else if (obj instanceof JSONArray) {
                    createMap.putArray(next, convertJsonArrayToWritable((JSONArray) obj));
                } else if (obj instanceof String) {
                    createMap.putString(next, (String) obj);
                } else if (obj instanceof Double) {
                    createMap.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    createMap.putDouble(next, ((Long) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    createMap.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Boolean) {
                    createMap.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj == null) {
                    createMap.putNull(next);
                } else {
                    throw new CodePushUnknownException("Unrecognized object: " + obj);
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Key " + next + " should exist in " + jSONObject.toString() + ".", e);
            }
        }
        return createMap;
    }

    public static JSONArray convertReadableToJsonArray(ReadableArray readableArray) {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    jSONArray.put(convertReadableToJsonObject(readableArray.getMap(i)));
                    break;
                case 2:
                    jSONArray.put(convertReadableToJsonArray(readableArray.getArray(i)));
                    break;
                case 3:
                    jSONArray.put(readableArray.getString(i));
                    break;
                case 4:
                    double d = readableArray.getDouble(i);
                    Double valueOf = Double.valueOf(d);
                    if (d == Math.floor(d) && !Double.isInfinite(d)) {
                        jSONArray.put(valueOf.longValue());
                        break;
                    } else {
                        try {
                            jSONArray.put(d);
                            break;
                        } catch (JSONException unused) {
                            throw new CodePushUnknownException("Unable to put value " + readableArray.getDouble(i) + " in JSONArray");
                        }
                    }
                    break;
                case 5:
                    jSONArray.put(readableArray.getBoolean(i));
                    break;
                case 6:
                    jSONArray.put((Object) null);
                    break;
            }
        }
        return jSONArray;
    }

    /* renamed from: com.microsoft.codepush.react.CodePushUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static JSONObject convertReadableToJsonObject(ReadableMap readableMap) {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            ReadableType type = readableMap.getType(nextKey);
            try {
                switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[type.ordinal()]) {
                    case 1:
                        jSONObject.put(nextKey, convertReadableToJsonObject(readableMap.getMap(nextKey)));
                        break;
                    case 2:
                        jSONObject.put(nextKey, convertReadableToJsonArray(readableMap.getArray(nextKey)));
                        break;
                    case 3:
                        jSONObject.put(nextKey, readableMap.getString(nextKey));
                        break;
                    case 4:
                        jSONObject.put(nextKey, readableMap.getDouble(nextKey));
                        break;
                    case 5:
                        jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                        break;
                    case 6:
                        jSONObject.put(nextKey, (Object) null);
                        break;
                    default:
                        throw new CodePushUnknownException("Unrecognized type: " + type + " of key: " + nextKey);
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Error setting key: " + nextKey + " in JSONObject", e);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getStringFromInputStream(java.io.InputStream r4) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0032 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0032 }
            r3.<init>(r4)     // Catch:{ all -> 0x0032 }
            r2.<init>(r3)     // Catch:{ all -> 0x0032 }
        L_0x0010:
            java.lang.String r0 = r2.readLine()     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x0021
            r1.append(r0)     // Catch:{ all -> 0x001f }
            java.lang.String r0 = "\n"
            r1.append(r0)     // Catch:{ all -> 0x001f }
            goto L_0x0010
        L_0x001f:
            r0 = move-exception
            goto L_0x0035
        L_0x0021:
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x001f }
            java.lang.String r0 = r0.trim()     // Catch:{ all -> 0x001f }
            r2.close()
            if (r4 == 0) goto L_0x0031
            r4.close()
        L_0x0031:
            return r0
        L_0x0032:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            r4.close()
        L_0x003f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUtils.getStringFromInputStream(java.io.InputStream):java.lang.String");
    }

    public static JSONObject getJsonObjectFromFile(String str) {
        try {
            return new JSONObject(FileUtils.readFileToString(str));
        } catch (JSONException e) {
            throw new CodePushMalformedDataException(str, (Throwable) e);
        }
    }

    public static void log(String str) {
        Log.d(ReactConstants.TAG, "[CodePush] " + str);
    }

    public static void log(Throwable th) {
        Log.e(ReactConstants.TAG, "[CodePush] Exception", th);
    }

    public static void logBundleUrl(String str) {
        log("Loading JS bundle from \"" + str + "\"");
    }

    public static void setJSONValueForKey(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException unused) {
            throw new CodePushUnknownException("Unable to set value " + obj + " for key " + str + " to JSONObject");
        }
    }

    public static String tryGetString(ReadableMap readableMap, String str) {
        try {
            return readableMap.getString(str);
        } catch (NoSuchKeyException unused) {
            return null;
        }
    }

    public static void writeJsonToFile(JSONObject jSONObject, String str) {
        FileUtils.writeStringToFile(jSONObject.toString(), str);
    }
}
