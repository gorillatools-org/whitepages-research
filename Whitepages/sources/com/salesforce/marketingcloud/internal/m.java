package com.salesforce.marketingcloud.internal;

import com.facebook.hermes.intl.Constants;
import com.salesforce.marketingcloud.g;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class m {
    private static final String a = g.a("GeneralUtils");
    private static final String b = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final TimeZone c;
    private static final Charset d;

    static final class a extends Lambda implements Function0 {
        final /* synthetic */ String a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.a = str;
        }

        /* renamed from: a */
        public final String invoke() {
            String str = this.a;
            return "Unable to parse " + str + " as a Date.";
        }
    }

    static {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Intrinsics.checkNotNullExpressionValue(timeZone, "getTimeZone(...)");
        c = timeZone;
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        d = forName;
    }

    public static final Date a(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
            simpleDateFormat.setTimeZone(c);
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            g.a.b(a, (Throwable) e, (Function0) new a(str));
            return null;
        }
    }

    public static final Map<String, String> b(JSONArray jSONArray) {
        JSONObject jSONObject;
        Object obj;
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList<JSONObject> arrayList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            Class<JSONObject> cls = JSONObject.class;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(cls);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(cls))) {
                jSONObject = jSONArray.getJSONObject(nextInt);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    obj = Integer.valueOf(jSONArray.getInt(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    obj = Double.valueOf(jSONArray.getDouble(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    obj = Long.valueOf(jSONArray.getLong(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    obj = Boolean.valueOf(jSONArray.getBoolean(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    obj = jSONArray.getString(nextInt);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                } else {
                    obj = jSONArray.get(nextInt);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                }
                jSONObject = (JSONObject) obj;
            }
            arrayList.add(jSONObject);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (JSONObject jSONObject2 : arrayList) {
            Pair pair = TuplesKt.to(jSONObject2.optString("key"), jSONObject2.optString("value"));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public static final String a(Date date) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
        simpleDateFormat.setTimeZone(c);
        String format = simpleDateFormat.format(date);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final Charset b() {
        return d;
    }

    public static final JSONArray a(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", (String) next.getKey());
            jSONObject.put("value", (String) next.getValue());
            arrayList.add(jSONObject);
        }
        return new JSONArray(arrayList);
    }

    public static final String b(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return null;
        }
        return str;
    }

    public static final /* synthetic */ <T> List<T> a(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            Intrinsics.reifiedOperationMarker(4, "T");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            Object jSONObject = Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class)) ? jSONArray.getJSONObject(nextInt) : Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE)) ? Integer.valueOf(jSONArray.getInt(nextInt)) : Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE)) ? Double.valueOf(jSONArray.getDouble(nextInt)) : Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE)) ? Long.valueOf(jSONArray.getLong(nextInt)) : Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE)) ? Boolean.valueOf(jSONArray.getBoolean(nextInt)) : Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class)) ? jSONArray.getString(nextInt) : jSONArray.get(nextInt);
            Intrinsics.reifiedOperationMarker(1, "T");
            arrayList.add(jSONObject);
        }
        return arrayList;
    }

    public static final /* synthetic */ <T extends Enum<T>> T a(JSONObject jSONObject, String str) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        String string = jSONObject.getString(str);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Intrinsics.reifiedOperationMarker(5, "T");
        return Enum.valueOf((Class) null, string);
    }

    public static final TimeZone a() {
        return c;
    }

    public static final /* synthetic */ <T extends Enum<T>> T a(JSONObject jSONObject, String str, T t) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(t, Constants.COLLATION_DEFAULT);
        String optString = jSONObject.optString(str);
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b2 = b(optString);
        if (b2 == null) {
            return t;
        }
        Intrinsics.reifiedOperationMarker(5, "T");
        return Enum.valueOf((Class) null, b2);
    }
}
