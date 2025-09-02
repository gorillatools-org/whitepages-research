package com.salesforce.marketingcloud.messages.iam;

import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.internal.m;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONObject;

public final class h {
    public static final List<InAppMessage.Button> a(JSONArray jSONArray) {
        InAppMessage.Button button;
        JSONObject jSONObject;
        Object obj;
        JSONArray jSONArray2 = jSONArray;
        Intrinsics.checkNotNullParameter(jSONArray2, "<this>");
        IntRange until = RangesKt.until(0, jSONArray.length());
        ArrayList<JSONObject> arrayList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            Class<JSONObject> cls = JSONObject.class;
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(cls);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(cls))) {
                jSONObject = jSONArray2.getJSONObject(nextInt);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    obj = Integer.valueOf(jSONArray2.getInt(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    obj = Double.valueOf(jSONArray2.getDouble(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    obj = Long.valueOf(jSONArray2.getLong(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    obj = Boolean.valueOf(jSONArray2.getBoolean(nextInt));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    obj = jSONArray2.getString(nextInt);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                } else {
                    obj = jSONArray2.get(nextInt);
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                }
                jSONObject = (JSONObject) obj;
            }
            arrayList.add(jSONObject);
        }
        ArrayList arrayList2 = new ArrayList();
        for (JSONObject jSONObject2 : arrayList) {
            try {
                String string = jSONObject2.getString("id");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                int optInt = jSONObject2.optInt(FirebaseAnalytics.Param.INDEX, 0);
                String string2 = jSONObject2.getString("text");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                InAppMessage.Button.ActionType actionType = InAppMessage.Button.ActionType.close;
                String optString = jSONObject2.optString("actionType");
                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                String b = m.b(optString);
                if (b != null) {
                    actionType = InAppMessage.Button.ActionType.valueOf(b);
                }
                String optString2 = jSONObject2.optString("actionAndroid");
                Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                String b2 = m.b(optString2);
                String optString3 = jSONObject2.optString("fontColor");
                Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                String b3 = m.b(optString3);
                InAppMessage.Size size = InAppMessage.Size.s;
                String optString4 = jSONObject2.optString(ViewProps.FONT_SIZE);
                Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                String b4 = m.b(optString4);
                InAppMessage.Size valueOf = b4 != null ? InAppMessage.Size.valueOf(b4) : size;
                String optString5 = jSONObject2.optString(ViewProps.BACKGROUND_COLOR);
                Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
                String b5 = m.b(optString5);
                String optString6 = jSONObject2.optString(ViewProps.BORDER_COLOR);
                Intrinsics.checkNotNullExpressionValue(optString6, "optString(...)");
                String b6 = m.b(optString6);
                String optString7 = jSONObject2.optString(ViewProps.BORDER_WIDTH);
                Intrinsics.checkNotNullExpressionValue(optString7, "optString(...)");
                String b7 = m.b(optString7);
                InAppMessage.Size valueOf2 = b7 != null ? InAppMessage.Size.valueOf(b7) : size;
                String optString8 = jSONObject2.optString("cornerRadius");
                Intrinsics.checkNotNullExpressionValue(optString8, "optString(...)");
                String b8 = m.b(optString8);
                button = new InAppMessage.Button(string, optInt, string2, actionType, b2, b3, valueOf, b5, b6, valueOf2, b8 != null ? InAppMessage.Size.valueOf(b8) : size);
            } catch (Exception unused) {
                button = null;
            }
            InAppMessage.Button button2 = button;
            if (button2 != null) {
                arrayList2.add(button2);
            }
        }
        return arrayList2;
    }

    public static final InAppMessage.Media b(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String string = jSONObject.getString("url");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        InAppMessage.Media.ImageSize imageSize = InAppMessage.Media.ImageSize.e2e;
        String optString = jSONObject.optString("size");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = m.b(optString);
        if (b != null) {
            imageSize = InAppMessage.Media.ImageSize.valueOf(b);
        }
        InAppMessage.Media.ImageSize imageSize2 = imageSize;
        String optString2 = jSONObject.optString("altText");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b2 = m.b(optString2);
        InAppMessage.Size size = InAppMessage.Size.s;
        String optString3 = jSONObject.optString(ViewProps.BORDER_WIDTH);
        Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
        String b3 = m.b(optString3);
        InAppMessage.Size valueOf = b3 != null ? InAppMessage.Size.valueOf(b3) : size;
        String optString4 = jSONObject.optString(ViewProps.BORDER_COLOR);
        Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
        String b4 = m.b(optString4);
        String optString5 = jSONObject.optString("cornerRadius");
        Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
        String b5 = m.b(optString5);
        return new InAppMessage.Media(string, imageSize2, b2, valueOf, b4, b5 != null ? InAppMessage.Size.valueOf(b5) : size);
    }

    public static final InAppMessage.TextField c(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String string = jSONObject.getString("text");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        InAppMessage.Size size = InAppMessage.Size.s;
        String optString = jSONObject.optString(ViewProps.FONT_SIZE);
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = m.b(optString);
        if (b != null) {
            size = InAppMessage.Size.valueOf(b);
        }
        String optString2 = jSONObject.optString("fontColor");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b2 = m.b(optString2);
        InAppMessage.Alignment alignment = InAppMessage.Alignment.center;
        String optString3 = jSONObject.optString("alignment");
        Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
        String b3 = m.b(optString3);
        if (b3 != null) {
            alignment = InAppMessage.Alignment.valueOf(b3);
        }
        return new InAppMessage.TextField(string, size, b2, alignment);
    }

    public static final InAppMessage.CloseButton a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        InAppMessage.Alignment alignment = InAppMessage.Alignment.end;
        String optString = jSONObject.optString("alignment");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b = m.b(optString);
        if (b != null) {
            alignment = InAppMessage.Alignment.valueOf(b);
        }
        return new InAppMessage.CloseButton(alignment);
    }
}
