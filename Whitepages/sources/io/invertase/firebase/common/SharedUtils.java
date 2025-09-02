package io.invertase.firebase.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.salesforce.marketingcloud.messages.iam.j;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedUtils {
    private static final String EXPO_CORE_PACKAGE = "expo.core";
    private static final String EXPO_REGISTRY_CLASS = "ModuleRegistry";
    private static final String FLUTTER_CORE_PACKAGE = "io.flutter.plugin.common";
    private static final String FLUTTER_REGISTRY_CLASS = "PluginRegistry";
    private static final String REACT_NATIVE_CORE_PACKAGE = "com.facebook.react.bridge";
    private static final String REACT_NATIVE_REGISTRY_CLASS = "NativeModuleRegistry";
    private static final String RN_DEVSUPPORT_CLASS = "DevSupportManagerImpl";
    private static final String RN_DEVSUPPORT_PACKAGE = "com.facebook.react.devsupport";
    private static final String TAG = "Utils";

    public static int[] rectToIntArray(Rect rect) {
        if (rect == null || rect.isEmpty()) {
            return new int[0];
        }
        return new int[]{rect.left, rect.top, rect.right, rect.bottom};
    }

    public static int[] pointToIntArray(Point point) {
        if (point == null) {
            return new int[0];
        }
        return new int[]{point.x, point.y};
    }

    public static List<int[]> pointsToIntsList(Point[] pointArr) {
        if (pointArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point pointToIntArray : pointArr) {
            arrayList.add(pointToIntArray(pointToIntArray));
        }
        return arrayList;
    }

    public static Uri getUri(String str) {
        Uri parse = Uri.parse(str);
        if (parse.getScheme() == null || parse.getScheme().isEmpty()) {
            return Uri.fromFile(new File(str));
        }
        return parse;
    }

    public static WritableMap getExceptionMap(Exception exc) {
        WritableMap createMap = Arguments.createMap();
        String message = exc.getMessage();
        createMap.putString("code", j.h);
        createMap.putString("nativeErrorCode", j.h);
        createMap.putString(StackTraceHelper.MESSAGE_KEY, message);
        createMap.putString("nativeErrorMessage", message);
        return createMap;
    }

    public static String timestampToUTC(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(Long.valueOf(j * 1000));
    }

    public static void sendEvent(ReactContext reactContext, String str, Object obj) {
        if (reactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
        } else {
            Log.d(TAG, "Missing context - cannot send event!");
        }
    }

    public static boolean isAppInForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        if (sharedInstance.contains("android_background_activity_names")) {
            ArrayList<String> arrayValue = sharedInstance.getArrayValue("android_background_activity_names");
            if (arrayValue.size() != 0) {
                List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
                if (appTasks.size() > 0) {
                    str = appTasks.get(0).getTaskInfo().baseActivity.getShortClassName();
                } else {
                    str = "";
                }
                if (!"".equals(str) && arrayValue.contains(str)) {
                    return false;
                }
            }
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && next.processName.equals(packageName)) {
                try {
                    if (((ReactContext) context).getLifecycleState() == LifecycleState.RESUMED) {
                        return true;
                    }
                    return false;
                } catch (ClassCastException unused) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressLint({"DiscouragedApi"})
    public static int getResId(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier == 0) {
            Log.e(TAG, "resource " + str + " could not be found");
        }
        return identifier;
    }

    public static Boolean reactNativeHasDevSupport() {
        return hasPackageClass(RN_DEVSUPPORT_PACKAGE, RN_DEVSUPPORT_CLASS);
    }

    public static Boolean isExpo() {
        return hasPackageClass(EXPO_CORE_PACKAGE, EXPO_REGISTRY_CLASS);
    }

    public static Boolean isFlutter() {
        return hasPackageClass(FLUTTER_CORE_PACKAGE, FLUTTER_REGISTRY_CLASS);
    }

    public static Boolean isReactNative() {
        return Boolean.valueOf(!isExpo().booleanValue() && hasPackageClass(REACT_NATIVE_CORE_PACKAGE, REACT_NATIVE_REGISTRY_CLASS).booleanValue());
    }

    public static Boolean hasPackageClass(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return Boolean.TRUE;
        } catch (Exception unused) {
            return Boolean.FALSE;
        }
    }

    public static WritableMap jsonObjectToWritableMap(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        WritableMap createMap = Arguments.createMap();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createMap.putDouble(next, jSONObject.getDouble(next));
            } else if (obj instanceof Number) {
                createMap.putInt(next, jSONObject.getInt(next));
            } else if (obj instanceof String) {
                createMap.putString(next, jSONObject.getString(next));
            } else if (obj instanceof JSONObject) {
                createMap.putMap(next, jsonObjectToWritableMap(jSONObject.getJSONObject(next)));
            } else if (obj instanceof JSONArray) {
                createMap.putArray(next, jsonArrayToWritableArray(jSONObject.getJSONArray(next)));
            } else if (obj == JSONObject.NULL) {
                createMap.putNull(next);
            }
        }
        return createMap;
    }

    public static WritableArray jsonArrayToWritableArray(JSONArray jSONArray) throws JSONException {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createArray.pushDouble(jSONArray.getDouble(i));
            } else if (obj instanceof Number) {
                createArray.pushInt(jSONArray.getInt(i));
            } else if (obj instanceof String) {
                createArray.pushString(jSONArray.getString(i));
            } else if (obj instanceof JSONObject) {
                createArray.pushMap(jsonObjectToWritableMap(jSONArray.getJSONObject(i)));
            } else if (obj instanceof JSONArray) {
                createArray.pushArray(jsonArrayToWritableArray(jSONArray.getJSONArray(i)));
            } else if (obj == JSONObject.NULL) {
                createArray.pushNull();
            }
        }
        return createArray;
    }

    public static WritableMap mapToWritableMap(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry next : map.entrySet()) {
            mapPutValue((String) next.getKey(), next.getValue(), createMap);
        }
        return createMap;
    }

    private static WritableArray listToWritableArray(List<Object> list) {
        WritableArray createArray = Arguments.createArray();
        for (Object arrayPushValue : list) {
            arrayPushValue(arrayPushValue, createArray);
        }
        return createArray;
    }

    public static void arrayPushValue(Object obj, WritableArray writableArray) {
        if (obj == null || obj == JSONObject.NULL) {
            writableArray.pushNull();
            return;
        }
        String name = obj.getClass().getName();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 0;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 1;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 2;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 3;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 4;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 5;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 6;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableArray.pushInt(((Integer) obj).intValue());
                return;
            case 1:
                writableArray.pushDouble((double) ((Float) obj).floatValue());
                return;
            case 2:
                try {
                    writableArray.pushArray(jsonArrayToWritableArray((JSONArray) obj));
                    return;
                } catch (JSONException unused) {
                    writableArray.pushNull();
                    return;
                }
            case 3:
                writableArray.pushBoolean(((Boolean) obj).booleanValue());
                return;
            case 4:
                writableArray.pushDouble((double) ((Long) obj).longValue());
                return;
            case 5:
                writableArray.pushDouble(((Double) obj).doubleValue());
                return;
            case 6:
                writableArray.pushString((String) obj);
                return;
            case 7:
                try {
                    writableArray.pushMap(jsonObjectToWritableMap((JSONObject) obj));
                    return;
                } catch (JSONException unused2) {
                    writableArray.pushNull();
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushArray(listToWritableArray((List) obj));
                    return;
                }
                if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushMap(mapToWritableMap((Map) obj));
                    return;
                }
                Log.d(TAG, "utils:arrayPushValue:unknownType:" + name);
                writableArray.pushNull();
                return;
        }
    }

    public static void mapPutValue(String str, Object obj, WritableMap writableMap) {
        if (obj == null || obj == JSONObject.NULL) {
            writableMap.putNull(str);
            return;
        }
        String name = obj.getClass().getName();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 0;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 1;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 2;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 3;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 4;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 5;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 6;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableMap.putInt(str, ((Integer) obj).intValue());
                return;
            case 1:
                writableMap.putDouble(str, (double) ((Float) obj).floatValue());
                return;
            case 2:
                try {
                    writableMap.putArray(str, jsonArrayToWritableArray((JSONArray) obj));
                    return;
                } catch (JSONException unused) {
                    writableMap.putNull(str);
                    return;
                }
            case 3:
                writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
                return;
            case 4:
                writableMap.putDouble(str, (double) ((Long) obj).longValue());
                return;
            case 5:
                writableMap.putDouble(str, ((Double) obj).doubleValue());
                return;
            case 6:
                writableMap.putString(str, (String) obj);
                return;
            case 7:
                try {
                    writableMap.putMap(str, jsonObjectToWritableMap((JSONObject) obj));
                    return;
                } catch (JSONException unused2) {
                    writableMap.putNull(str);
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putArray(str, listToWritableArray((List) obj));
                    return;
                }
                if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putMap(str, mapToWritableMap((Map) obj));
                    return;
                }
                Log.d(TAG, "utils:mapPutValue:unknownType:" + name);
                writableMap.putNull(str);
                return;
        }
    }

    public static WritableMap readableMapToWritableMap(ReadableMap readableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        return createMap;
    }
}
