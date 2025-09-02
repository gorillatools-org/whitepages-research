package com.facebook.react.bridge;

import android.os.Bundle;
import android.os.Parcelable;
import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DoNotStrip
public class Arguments {
    private static Object makeNativeObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Float) || (obj instanceof Long) || (obj instanceof Byte) || (obj instanceof Short)) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj.getClass().isArray()) {
            return makeNativeArray(obj);
        }
        if (obj instanceof List) {
            return makeNativeArray((List) obj);
        }
        if (obj instanceof Map) {
            return makeNativeMap((Map<String, Object>) (Map) obj);
        }
        if (obj instanceof Bundle) {
            return makeNativeMap((Bundle) obj);
        }
        if (obj instanceof JavaOnlyMap) {
            return makeNativeMap((Map<String, Object>) ((JavaOnlyMap) obj).toHashMap());
        }
        return obj instanceof JavaOnlyArray ? makeNativeArray((List) ((JavaOnlyArray) obj).toArrayList()) : obj;
    }

    public static WritableNativeArray makeNativeArray(List list) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (list == null) {
            return writableNativeArray;
        }
        for (Object makeNativeObject : list) {
            Object makeNativeObject2 = makeNativeObject(makeNativeObject);
            if (makeNativeObject2 == null) {
                writableNativeArray.pushNull();
            } else if (makeNativeObject2 instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) makeNativeObject2).booleanValue());
            } else if (makeNativeObject2 instanceof Integer) {
                writableNativeArray.pushInt(((Integer) makeNativeObject2).intValue());
            } else if (makeNativeObject2 instanceof Double) {
                writableNativeArray.pushDouble(((Double) makeNativeObject2).doubleValue());
            } else if (makeNativeObject2 instanceof String) {
                writableNativeArray.pushString((String) makeNativeObject2);
            } else if (makeNativeObject2 instanceof WritableNativeArray) {
                writableNativeArray.pushArray((WritableNativeArray) makeNativeObject2);
            } else if (makeNativeObject2 instanceof WritableNativeMap) {
                writableNativeArray.pushMap((WritableNativeMap) makeNativeObject2);
            } else {
                throw new IllegalArgumentException("Could not convert " + makeNativeObject2.getClass());
            }
        }
        return writableNativeArray;
    }

    public static <T> WritableNativeArray makeNativeArray(final Object obj) {
        if (obj == null) {
            return new WritableNativeArray();
        }
        return makeNativeArray((List) new AbstractList() {
            public int size() {
                return Array.getLength(obj);
            }

            public Object get(int i) {
                return Array.get(obj, i);
            }
        });
    }

    private static void addEntry(WritableNativeMap writableNativeMap, String str, Object obj) {
        Object makeNativeObject = makeNativeObject(obj);
        if (makeNativeObject == null) {
            writableNativeMap.putNull(str);
        } else if (makeNativeObject instanceof Boolean) {
            writableNativeMap.putBoolean(str, ((Boolean) makeNativeObject).booleanValue());
        } else if (makeNativeObject instanceof Integer) {
            writableNativeMap.putInt(str, ((Integer) makeNativeObject).intValue());
        } else if (makeNativeObject instanceof Number) {
            writableNativeMap.putDouble(str, ((Number) makeNativeObject).doubleValue());
        } else if (makeNativeObject instanceof String) {
            writableNativeMap.putString(str, (String) makeNativeObject);
        } else if (makeNativeObject instanceof WritableNativeArray) {
            writableNativeMap.putArray(str, (WritableNativeArray) makeNativeObject);
        } else if (makeNativeObject instanceof WritableNativeMap) {
            writableNativeMap.putMap(str, (WritableNativeMap) makeNativeObject);
        } else {
            throw new IllegalArgumentException("Could not convert " + makeNativeObject.getClass());
        }
    }

    @DoNotStrip
    public static WritableNativeMap makeNativeMap(Map<String, Object> map) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (map == null) {
            return writableNativeMap;
        }
        for (Map.Entry next : map.entrySet()) {
            addEntry(writableNativeMap, (String) next.getKey(), next.getValue());
        }
        return writableNativeMap;
    }

    @DoNotStrip
    public static WritableNativeMap makeNativeMap(Bundle bundle) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (bundle == null) {
            return writableNativeMap;
        }
        for (String next : bundle.keySet()) {
            addEntry(writableNativeMap, next, bundle.get(next));
        }
        return writableNativeMap;
    }

    public static WritableArray createArray() {
        return new WritableNativeArray();
    }

    public static WritableMap createMap() {
        return new WritableNativeMap();
    }

    public static WritableNativeArray fromJavaArgs(Object[] objArr) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (Boolean bool : objArr) {
            if (bool == null) {
                writableNativeArray.pushNull();
            } else {
                Class<?> cls = bool.getClass();
                if (cls == Boolean.class) {
                    writableNativeArray.pushBoolean(bool.booleanValue());
                } else if (cls == Integer.class) {
                    writableNativeArray.pushDouble(((Integer) bool).doubleValue());
                } else if (cls == Double.class) {
                    writableNativeArray.pushDouble(((Double) bool).doubleValue());
                } else if (cls == Float.class) {
                    writableNativeArray.pushDouble(((Float) bool).doubleValue());
                } else if (cls == String.class) {
                    writableNativeArray.pushString(bool.toString());
                } else if (cls == WritableNativeMap.class) {
                    writableNativeArray.pushMap((WritableNativeMap) bool);
                } else if (cls == WritableNativeArray.class) {
                    writableNativeArray.pushArray((WritableNativeArray) bool);
                } else {
                    throw new RuntimeException("Cannot convert argument of type " + cls);
                }
            }
        }
        return writableNativeArray;
    }

    public static WritableArray fromArray(Object obj) {
        WritableArray createArray = createArray();
        int i = 0;
        if (obj instanceof String[]) {
            String[] strArr = (String[]) obj;
            int length = strArr.length;
            while (i < length) {
                createArray.pushString(strArr[i]);
                i++;
            }
        } else if (obj instanceof Bundle[]) {
            Bundle[] bundleArr = (Bundle[]) obj;
            int length2 = bundleArr.length;
            while (i < length2) {
                createArray.pushMap(fromBundle(bundleArr[i]));
                i++;
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length;
            while (i < length3) {
                createArray.pushInt(iArr[i]);
                i++;
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length4 = fArr.length;
            while (i < length4) {
                createArray.pushDouble((double) fArr[i]);
                i++;
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length5 = dArr.length;
            while (i < length5) {
                createArray.pushDouble(dArr[i]);
                i++;
            }
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            int length6 = zArr.length;
            while (i < length6) {
                createArray.pushBoolean(zArr[i]);
                i++;
            }
        } else if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            int length7 = parcelableArr.length;
            while (i < length7) {
                Parcelable parcelable = parcelableArr[i];
                if (parcelable instanceof Bundle) {
                    createArray.pushMap(fromBundle((Bundle) parcelable));
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected array member type " + parcelable.getClass());
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown array type " + obj.getClass());
        }
        return createArray;
    }

    public static WritableArray fromList(List list) {
        WritableArray createArray = createArray();
        for (Object next : list) {
            if (next == null) {
                createArray.pushNull();
            } else if (next.getClass().isArray()) {
                createArray.pushArray(fromArray(next));
            } else if (next instanceof Bundle) {
                createArray.pushMap(fromBundle((Bundle) next));
            } else if (next instanceof List) {
                createArray.pushArray(fromList((List) next));
            } else if (next instanceof String) {
                createArray.pushString((String) next);
            } else if (next instanceof Integer) {
                createArray.pushInt(((Integer) next).intValue());
            } else if (next instanceof Number) {
                createArray.pushDouble(((Number) next).doubleValue());
            } else if (next instanceof Boolean) {
                createArray.pushBoolean(((Boolean) next).booleanValue());
            } else {
                throw new IllegalArgumentException("Unknown value type " + next.getClass());
            }
        }
        return createArray;
    }

    public static WritableMap fromBundle(Bundle bundle) {
        WritableMap createMap = createMap();
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            if (obj == null) {
                createMap.putNull(next);
            } else if (obj.getClass().isArray()) {
                createMap.putArray(next, fromArray(obj));
            } else if (obj instanceof String) {
                createMap.putString(next, (String) obj);
            } else if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    createMap.putInt(next, ((Integer) obj).intValue());
                } else {
                    createMap.putDouble(next, ((Number) obj).doubleValue());
                }
            } else if (obj instanceof Boolean) {
                createMap.putBoolean(next, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Bundle) {
                createMap.putMap(next, fromBundle((Bundle) obj));
            } else if (obj instanceof List) {
                createMap.putArray(next, fromList((List) obj));
            } else {
                throw new IllegalArgumentException("Could not convert " + obj.getClass());
            }
        }
        return createMap;
    }

    public static ArrayList toList(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    arrayList.add((Object) null);
                    break;
                case 2:
                    arrayList.add(Boolean.valueOf(readableArray.getBoolean(i)));
                    break;
                case 3:
                    double d = readableArray.getDouble(i);
                    if (d != Math.rint(d)) {
                        arrayList.add(Double.valueOf(d));
                        break;
                    } else {
                        arrayList.add(Integer.valueOf((int) d));
                        break;
                    }
                case 4:
                    arrayList.add(readableArray.getString(i));
                    break;
                case 5:
                    arrayList.add(toBundle(readableArray.getMap(i)));
                    break;
                case 6:
                    arrayList.add(toList(readableArray.getArray(i)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object in array.");
            }
        }
        return arrayList;
    }

    /* renamed from: com.facebook.react.bridge.Arguments$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.Arguments.AnonymousClass2.<clinit>():void");
        }
    }

    public static Bundle toBundle(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        Bundle bundle = new Bundle();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    bundle.putString(nextKey, (String) null);
                    break;
                case 2:
                    bundle.putBoolean(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case 3:
                    bundle.putDouble(nextKey, readableMap.getDouble(nextKey));
                    break;
                case 4:
                    bundle.putString(nextKey, readableMap.getString(nextKey));
                    break;
                case 5:
                    bundle.putBundle(nextKey, toBundle(readableMap.getMap(nextKey)));
                    break;
                case 6:
                    bundle.putSerializable(nextKey, toList(readableMap.getArray(nextKey)));
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + nextKey + ".");
            }
        }
        return bundle;
    }
}
