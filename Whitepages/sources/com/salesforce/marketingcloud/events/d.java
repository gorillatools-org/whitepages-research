package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;

public final class d {
    private static final String a = g.a("EventUtilsKt");

    static final class a extends Lambda implements Function0 {
        final /* synthetic */ Event a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(Event event) {
            super(0);
            this.a = event;
        }

        /* renamed from: a */
        public final String invoke() {
            Event event = this.a;
            return "(" + event + ") returned null during conversion to Push SDK Event.";
        }
    }

    static final class b extends Lambda implements Function0 {
        final /* synthetic */ Object a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(Object obj) {
            super(0);
            this.a = obj;
        }

        /* renamed from: a */
        public final String invoke() {
            Object obj = this.a;
            return "Failed to convert event '" + obj + "' to Push SDK Event.";
        }
    }

    static final class c extends Lambda implements Function0 {
        final /* synthetic */ Event a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        c(Event event) {
            super(0);
            this.a = event;
        }

        /* renamed from: a */
        public final String invoke() {
            Event event = this.a;
            return "(" + event + ") returned null during conversion to SFMC SDK Event.";
        }
    }

    /* renamed from: com.salesforce.marketingcloud.events.d$d  reason: collision with other inner class name */
    static final class C0013d extends Lambda implements Function0 {
        final /* synthetic */ Object a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C0013d(Object obj) {
            super(0);
            this.a = obj;
        }

        /* renamed from: a */
        public final String invoke() {
            Object obj = this.a;
            return "Failed to convert event '" + obj + "' to SFMCSdk Event.";
        }
    }

    public static final Map<String, List<Object>> a(Event event) {
        Intrinsics.checkNotNullParameter(event, "<this>");
        return a((Map) new LinkedHashMap(), (Object) event.attributes(), (String) null, 4, (Object) null);
    }

    public static final Event b(Event event) {
        Intrinsics.checkNotNullParameter(event, "<this>");
        return EventManager.Companion.customEvent$default(EventManager.Companion, event.name(), event.attributes(), Event.Producer.PUSH, (Event.Category) null, 8, (Object) null);
    }

    private static final Map<String, List<Object>> a(Map<String, List<Object>> map, Object obj, String str) {
        try {
            if (obj instanceof List) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Any>");
                return a(map, (List<Object>) TypeIntrinsics.asMutableList(obj), str);
            } else if (!(obj instanceof Map)) {
                return map;
            } else {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.Any, kotlin.Any>");
                return a(map, (Map<Object, Object>) TypeIntrinsics.asMutableMap(obj), str);
            }
        } catch (Exception unused) {
            return map;
        }
    }

    public static final Event[] b(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return a(objArr, (EnumSet) null, 1, (Object) null);
    }

    static /* synthetic */ Map a(Map map, Object obj, String str, int i, Object obj2) {
        if ((i & 4) != 0) {
            str = "";
        }
        return a((Map<String, List<Object>>) map, obj, str);
    }

    public static final Event[] b(Object[] objArr, EnumSet<Event.Producer> enumSet) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(enumSet, "producers");
        ArrayList arrayList = new ArrayList();
        for (Event event : objArr) {
            if (event != null) {
                try {
                    Event event2 = event;
                    if (enumSet.contains(event2.getProducer())) {
                        Event b2 = b(event2);
                        if ((b2 != null ? Boolean.valueOf(arrayList.add(b2)) : null) == null) {
                            g.c(g.a, a, (Throwable) null, new c(event2), 2, (Object) null);
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                } catch (Exception unused) {
                    g.e(g.a, a, (Throwable) null, new C0013d(event), 2, (Object) null);
                }
            }
        }
        return (Event[]) arrayList.toArray(new Event[0]);
    }

    private static final boolean a(Object obj) {
        if (obj instanceof Map) {
            return true;
        }
        if (obj instanceof List) {
            int i = 0;
            for (Object next : (Iterable) obj) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (next != null && a(next)) {
                    return true;
                }
                i = i2;
            }
        }
        return false;
    }

    private static final Map<String, List<Object>> a(Map<String, List<Object>> map, List<Object> list, String str) {
        for (Object next : list) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (next != null) {
                if (a(next)) {
                    map.putAll(a(map, next, str));
                } else {
                    Collection collection = map.get(lowerCase);
                    if (collection == null || collection.isEmpty()) {
                        map.put(lowerCase, CollectionsKt.mutableListOf(next));
                    } else {
                        List list2 = map.get(lowerCase);
                        if (list2 != null) {
                            list2.add(next);
                        }
                    }
                }
            }
        }
        return map;
    }

    private static final Map<String, List<Object>> a(Map<String, List<Object>> map, Map<Object, Object> map2, String str) {
        String str2;
        for (Map.Entry next : map2.entrySet()) {
            if (!StringsKt.isBlank(str)) {
                str2 = str + "." + next.getKey();
            } else {
                Object key = next.getKey();
                Intrinsics.checkNotNull(key, "null cannot be cast to non-null type kotlin.String");
                str2 = (String) key;
            }
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = str2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (a(next.getValue())) {
                map.putAll(a(map, next.getValue(), lowerCase));
            } else {
                Collection collection = map.get(lowerCase);
                if (collection == null || collection.isEmpty()) {
                    map.put(lowerCase, CollectionsKt.mutableListOf(next.getValue()));
                } else {
                    List list = map.get(lowerCase);
                    if (list != null) {
                        list.add(next.getValue());
                    }
                }
            }
        }
        return map;
    }

    public static final Event[] a(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        return a(objArr, (EnumSet) null, (EnumSet) null, 3, (Object) null);
    }

    public static final Event[] a(Object[] objArr, EnumSet<Event.Producer> enumSet) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(enumSet, "producers");
        return a(objArr, (EnumSet) enumSet, (EnumSet) null, 2, (Object) null);
    }

    public static final Event[] a(Object[] objArr, EnumSet<Event.Producer> enumSet, EnumSet<Event.Category> enumSet2) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        Intrinsics.checkNotNullParameter(enumSet, "producers");
        Intrinsics.checkNotNullParameter(enumSet2, "categories");
        ArrayList arrayList = new ArrayList();
        for (Event event : objArr) {
            if (event != null) {
                try {
                    Event event2 = event;
                    if (enumSet.contains(event2.getProducer()) && enumSet2.contains(event2.getCategory())) {
                        Event a2 = a(event2);
                        if ((a2 != null ? Boolean.valueOf(arrayList.add(a2)) : null) == null) {
                            g.c(g.a, a, (Throwable) null, new a(event2), 2, (Object) null);
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                } catch (Exception unused) {
                    g.b(g.a, a, (Throwable) null, new b(event), 2, (Object) null);
                }
            }
        }
        return (Event[]) arrayList.toArray(new Event[0]);
    }

    public static /* synthetic */ Event[] a(Object[] objArr, EnumSet<E> enumSet, EnumSet<E> enumSet2, int i, Object obj) {
        if ((i & 1) != 0) {
            enumSet = EnumSet.allOf(Event.Producer.class);
            Intrinsics.checkNotNullExpressionValue(enumSet, "allOf(...)");
        }
        if ((i & 2) != 0) {
            enumSet2 = EnumSet.allOf(Event.Category.class);
            Intrinsics.checkNotNullExpressionValue(enumSet2, "allOf(...)");
        }
        return a(objArr, (EnumSet<Event.Producer>) enumSet, (EnumSet<Event.Category>) enumSet2);
    }

    public static final Event a(Event event) {
        Intrinsics.checkNotNullParameter(event, "<this>");
        return EventManager.Companion.customEvent(event.name(), event.attributes(), event.getProducer());
    }

    public static /* synthetic */ Event[] a(Object[] objArr, EnumSet<E> enumSet, int i, Object obj) {
        if ((i & 1) != 0) {
            enumSet = EnumSet.allOf(Event.Producer.class);
            Intrinsics.checkNotNullExpressionValue(enumSet, "allOf(...)");
        }
        return b(objArr, enumSet);
    }
}
