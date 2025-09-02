package com.salesforce.marketingcloud.reactnative;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.sfmcsdk.components.events.CartEvent;
import com.salesforce.marketingcloud.sfmcsdk.components.events.CatalogEvent;
import com.salesforce.marketingcloud.sfmcsdk.components.events.CatalogObject;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import com.salesforce.marketingcloud.sfmcsdk.components.events.LineItem;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Order;
import com.salesforce.marketingcloud.sfmcsdk.components.events.OrderEvent;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventUtility {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.salesforce.marketingcloud.sfmcsdk.components.events.Event toEvent(com.facebook.react.bridge.ReadableMap r5) {
        /*
            org.json.JSONObject r5 = toJSONObject(r5)     // Catch:{ JSONException -> 0x001f }
            java.lang.String r0 = "objType"
            java.lang.String r0 = r5.optString(r0)     // Catch:{ JSONException -> 0x001f }
            int r1 = r0.hashCode()     // Catch:{ JSONException -> 0x001f }
            r2 = 2
            r3 = 1
            r4 = 3
            switch(r1) {
                case -2101929158: goto L_0x0035;
                case -1569048564: goto L_0x002b;
                case -133620343: goto L_0x0021;
                case 662517986: goto L_0x0015;
                default: goto L_0x0014;
            }     // Catch:{ JSONException -> 0x001f }
        L_0x0014:
            goto L_0x003f
        L_0x0015:
            java.lang.String r1 = "CatalogObjectEvent"
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x001f }
            if (r0 == 0) goto L_0x003f
            r0 = r4
            goto L_0x0040
        L_0x001f:
            r5 = move-exception
            goto L_0x0071
        L_0x0021:
            java.lang.String r1 = "CustomEvent"
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x001f }
            if (r0 == 0) goto L_0x003f
            r0 = r3
            goto L_0x0040
        L_0x002b:
            java.lang.String r1 = "OrderEvent"
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x001f }
            if (r0 == 0) goto L_0x003f
            r0 = r2
            goto L_0x0040
        L_0x0035:
            java.lang.String r1 = "CartEvent"
            boolean r0 = r0.equals(r1)     // Catch:{ JSONException -> 0x001f }
            if (r0 == 0) goto L_0x003f
            r0 = 0
            goto L_0x0040
        L_0x003f:
            r0 = -1
        L_0x0040:
            if (r0 == 0) goto L_0x006c
            if (r0 == r3) goto L_0x0057
            if (r0 == r2) goto L_0x0052
            if (r0 == r4) goto L_0x004d
            com.salesforce.marketingcloud.sfmcsdk.components.events.Event r5 = checkForOtherEvents(r5)     // Catch:{ JSONException -> 0x001f }
            return r5
        L_0x004d:
            com.salesforce.marketingcloud.sfmcsdk.components.events.Event r5 = createCatalogEvent(r5)     // Catch:{ JSONException -> 0x001f }
            return r5
        L_0x0052:
            com.salesforce.marketingcloud.sfmcsdk.components.events.OrderEvent r5 = createOrderEvent(r5)     // Catch:{ JSONException -> 0x001f }
            return r5
        L_0x0057:
            java.lang.String r0 = "name"
            java.lang.String r0 = r5.optString(r0)     // Catch:{ JSONException -> 0x001f }
            java.lang.String r1 = "attributes"
            org.json.JSONObject r5 = r5.optJSONObject(r1)     // Catch:{ JSONException -> 0x001f }
            java.util.Map r5 = toMap((org.json.JSONObject) r5)     // Catch:{ JSONException -> 0x001f }
            com.salesforce.marketingcloud.sfmcsdk.components.events.Event r5 = com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager.customEvent(r0, r5)     // Catch:{ JSONException -> 0x001f }
            return r5
        L_0x006c:
            com.salesforce.marketingcloud.sfmcsdk.components.events.CartEvent r5 = createCartEvent(r5)     // Catch:{ JSONException -> 0x001f }
            return r5
        L_0x0071:
            r5.printStackTrace()
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.reactnative.EventUtility.toEvent(com.facebook.react.bridge.ReadableMap):com.salesforce.marketingcloud.sfmcsdk.components.events.Event");
    }

    private static Event createCatalogEvent(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("name");
        optString.hashCode();
        char c = 65535;
        switch (optString.hashCode()) {
            case -1708828336:
                if (optString.equals("View Catalog Object Detail")) {
                    c = 0;
                    break;
                }
                break;
            case -119842841:
                if (optString.equals("Share Catalog Object")) {
                    c = 1;
                    break;
                }
                break;
            case -36735666:
                if (optString.equals("Quick View Catalog Object")) {
                    c = 2;
                    break;
                }
                break;
            case 769011786:
                if (optString.equals("Favorite Catalog Object")) {
                    c = 3;
                    break;
                }
                break;
            case 907895143:
                if (optString.equals("Comment Catalog Object")) {
                    c = 4;
                    break;
                }
                break;
            case 1368939553:
                if (optString.equals("View Catalog Object")) {
                    c = 5;
                    break;
                }
                break;
            case 1924975374:
                if (optString.equals("Review Catalog Object")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return CatalogEvent.viewDetail(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 1:
                return CatalogEvent.share(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 2:
                return CatalogEvent.quickView(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 3:
                return CatalogEvent.favorite(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 4:
                return CatalogEvent.comment(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 5:
                return CatalogEvent.view(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            case 6:
                return CatalogEvent.review(getCatalogObject(jSONObject.optJSONObject("catalogObject")));
            default:
                return null;
        }
    }

    private static CatalogObject getCatalogObject(JSONObject jSONObject) throws JSONException {
        return new CatalogObject(jSONObject.optString("type"), jSONObject.optString("id"), toMap(jSONObject.optJSONObject(k.a.h)), getRelatedCatalogObjects(jSONObject.optJSONObject("relatedCatalogObjects")));
    }

    private static Map<String, List<String>> getRelatedCatalogObjects(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (opt instanceof JSONArray) {
                hashMap.put(next, toList((JSONArray) opt));
            }
        }
        return hashMap;
    }

    private static List<String> toList(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optString(i));
        }
        return arrayList;
    }

    private static Event checkForOtherEvents(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("category");
        optString.hashCode();
        if (optString.equals("engagement")) {
            return EventManager.customEvent(jSONObject.optString("name"), toMap(jSONObject.optJSONObject(k.a.h)), Event.Producer.PUSH, Event.Category.ENGAGEMENT);
        }
        if (!optString.equals("system")) {
            return null;
        }
        return EventManager.customEvent(jSONObject.optString("name"), toMap(jSONObject.optJSONObject(k.a.h)), Event.Producer.PUSH, Event.Category.SYSTEM);
    }

    private static OrderEvent createOrderEvent(JSONObject jSONObject) {
        String optString = jSONObject.optString("name");
        optString.hashCode();
        char c = 65535;
        switch (optString.hashCode()) {
            case -1850529456:
                if (optString.equals("Return")) {
                    c = 0;
                    break;
                }
                break;
            case -1215338837:
                if (optString.equals("Preorder")) {
                    c = 1;
                    break;
                }
                break;
            case -1079729915:
                if (optString.equals("Deliver")) {
                    c = 2;
                    break;
                }
                break;
            case 2575964:
                if (optString.equals("Ship")) {
                    c = 3;
                    break;
                }
                break;
            case 1807968545:
                if (optString.equals("Purchase")) {
                    c = 4;
                    break;
                }
                break;
            case 2011110042:
                if (optString.equals("Cancel")) {
                    c = 5;
                    break;
                }
                break;
            case 2054419011:
                if (optString.equals("Exchange")) {
                    c = 6;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return OrderEvent.returnOrder(getOrder(jSONObject.optJSONObject("order")));
            case 1:
                return OrderEvent.preorder(getOrder(jSONObject.optJSONObject("order")));
            case 2:
                return OrderEvent.deliver(getOrder(jSONObject.optJSONObject("order")));
            case 3:
                return OrderEvent.ship(getOrder(jSONObject.optJSONObject("order")));
            case 4:
                return OrderEvent.purchase(getOrder(jSONObject.optJSONObject("order")));
            case 5:
                return OrderEvent.cancel(getOrder(jSONObject.optJSONObject("order")));
            case 6:
                return OrderEvent.exchange(getOrder(jSONObject.optJSONObject("order")));
            default:
                return null;
        }
    }

    private static Order getOrder(JSONObject jSONObject) {
        String optString = jSONObject.optString("id");
        String optString2 = jSONObject.optString(FirebaseAnalytics.Param.CURRENCY);
        double optDouble = jSONObject.optDouble("totalValue");
        JSONArray optJSONArray = jSONObject.optJSONArray("lineItems");
        Map<String, Object> attributesMap = getAttributesMap(jSONObject.optJSONObject(k.a.h));
        if (attributesMap == null) {
            return new Order(optString, getLineItems(optJSONArray), Double.valueOf(optDouble), optString2);
        }
        return new Order(optString, getLineItems(optJSONArray), Double.valueOf(optDouble), optString2, attributesMap);
    }

    private static Map<String, Object> getAttributesMap(JSONObject jSONObject) {
        try {
            return toMap(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<LineItem> getLineItems(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(getLineItem(jSONArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private static CartEvent createCartEvent(JSONObject jSONObject) {
        LineItem lineItem = getLineItem(jSONObject.optJSONObject("lineItem"));
        String optString = jSONObject.optString("name");
        optString.hashCode();
        char c = 65535;
        switch (optString.hashCode()) {
            case -1769558694:
                if (optString.equals("Remove From Cart")) {
                    c = 0;
                    break;
                }
                break;
            case 565776716:
                if (optString.equals("Replace Cart")) {
                    c = 1;
                    break;
                }
                break;
            case 1014679814:
                if (optString.equals("Add To Cart")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return CartEvent.remove(lineItem);
            case 1:
                return CartEvent.replace(new ArrayList<LineItem>() {
                    {
                        add(LineItem.this);
                    }
                });
            case 2:
                return CartEvent.add(lineItem);
            default:
                return null;
        }
    }

    private static LineItem getLineItem(JSONObject jSONObject) {
        String optString = jSONObject.optString("catalogObjectType");
        String optString2 = jSONObject.optString("catalogObjectId");
        int optInt = jSONObject.optInt(FirebaseAnalytics.Param.QUANTITY);
        double optDouble = jSONObject.optDouble(FirebaseAnalytics.Param.PRICE);
        String optString3 = jSONObject.optString(FirebaseAnalytics.Param.CURRENCY);
        Map<String, Object> attributesMap = getAttributesMap(jSONObject.optJSONObject(k.a.h));
        if (attributesMap == null) {
            return new LineItem(optString, optString2, optInt, Double.valueOf(optDouble), optString3);
        }
        return new LineItem(optString, optString2, optInt, Double.valueOf(optDouble), optString3, attributesMap);
    }

    private static JSONObject toJSONObject(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int i = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()];
            if (i == 1) {
                jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
            } else if (i == 2) {
                jSONObject.put(nextKey, readableMap.getDouble(nextKey));
            } else if (i == 3) {
                jSONObject.put(nextKey, readableMap.getString(nextKey));
            } else if (i == 4) {
                jSONObject.put(nextKey, toJSONObject(readableMap.getMap(nextKey)));
            } else if (i == 5) {
                jSONObject.put(nextKey, toJSONArray(readableMap.getArray(nextKey)));
            }
        }
        return jSONObject;
    }

    /* renamed from: com.salesforce.marketingcloud.reactnative.EventUtility$2  reason: invalid class name */
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
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x001d }
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
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x003e }
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
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.reactnative.EventUtility.AnonymousClass2.<clinit>():void");
        }
    }

    private static JSONArray toJSONArray(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    jSONArray.put(i, readableArray.getBoolean(i));
                    break;
                case 2:
                    jSONArray.put(i, readableArray.getDouble(i));
                    break;
                case 3:
                    jSONArray.put(i, readableArray.getString(i));
                    break;
                case 4:
                    jSONArray.put(i, toJSONObject(readableArray.getMap(i)));
                    break;
                case 5:
                    jSONArray.put(i, toJSONArray(readableArray.getArray(i)));
                    break;
                case 6:
                    jSONArray.put(i, (Object) null);
                    break;
            }
        }
        return jSONArray;
    }

    private static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                obj = toArray((JSONArray) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    private static Map<String, Object> toMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
                    break;
                case 2:
                    hashMap.put(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
                    break;
                case 3:
                    hashMap.put(nextKey, readableMap.getString(nextKey));
                    break;
                case 4:
                    hashMap.put(nextKey, toMap(readableMap.getMap(nextKey)));
                    break;
                case 5:
                    hashMap.put(nextKey, toArray(readableMap.getArray(nextKey)));
                    break;
                case 6:
                    hashMap.put(nextKey, (Object) null);
                    break;
            }
        }
        return hashMap;
    }

    private static Object[] toArray(JSONArray jSONArray) throws JSONException {
        Object[] objArr = new Object[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                obj = toArray((JSONArray) obj);
            }
            objArr[i] = obj;
        }
        return objArr;
    }

    private static Object[] toArray(ReadableArray readableArray) {
        Object[] objArr = new Object[readableArray.size()];
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    objArr[i] = Boolean.valueOf(readableArray.getBoolean(i));
                    break;
                case 2:
                    objArr[i] = Double.valueOf(readableArray.getDouble(i));
                    break;
                case 3:
                    objArr[i] = readableArray.getString(i);
                    break;
                case 4:
                    objArr[i] = toMap(readableArray.getMap(i));
                    break;
                case 5:
                    objArr[i] = toArray(readableArray.getArray(i));
                    break;
                case 6:
                    objArr[i] = null;
                    break;
            }
        }
        return objArr;
    }
}
