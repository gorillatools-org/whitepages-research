package com.salesforce.marketingcloud.events;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@MCKeep
public abstract class EventManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = g.a("EventManager");

    @MCKeep
    public enum AuthEventType {
        LOGIN;

        static {
            AuthEventType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    public static final class Companion {

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
                return str + " contains a \".\" and will be dropped.";
            }
        }

        static final class b extends Lambda implements Function0 {
            final /* synthetic */ String a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            b(String str) {
                super(0);
                this.a = str;
            }

            /* renamed from: a */
            public final String invoke() {
                String str = this.a;
                return str + " is null, blank, starts with a \"$\", or contains a line break and will be dropped.";
            }
        }

        private Companion() {
        }

        public static /* synthetic */ Event a(Companion companion, String str, Map map, Event.Producer producer, int i, Object obj) {
            if ((i & 2) != 0) {
                map = MapsKt.emptyMap();
            }
            if ((i & 4) != 0) {
                producer = Event.Producer.PUSH;
            }
            return companion.customEvent(str, map, producer);
        }

        public final String b(String str) {
            Intrinsics.checkNotNullParameter(str, "input");
            String obj = StringsKt.trim(str).toString();
            if (!StringsKt.isBlank(obj) && !StringsKt.startsWith$default(obj, "$", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) "\r", false, 2, (Object) null)) {
                return obj;
            }
            g.e(g.a, EventManager.TAG, (Throwable) null, new b(str), 2, (Object) null);
            return null;
        }

        @MCKeep
        public final Event customEvent(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return customEvent(str, MapsKt.emptyMap(), Event.Producer.PUSH);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String a(String str) {
            Intrinsics.checkNotNullParameter(str, "input");
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
                return b(str);
            }
            g.e(g.a, EventManager.TAG, (Throwable) null, new a(str), 2, (Object) null);
            return null;
        }

        @MCKeep
        public final Event customEvent(String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, k.a.h);
            return customEvent(str, map, Event.Producer.PUSH);
        }

        @MCKeep
        public final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, k.a.h);
            Intrinsics.checkNotNullParameter(producer, "producer");
            String b2 = b(str);
            if (b2 == null) {
                return null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry next : map.entrySet()) {
                Object value = next.getValue();
                String a2 = EventManager.Companion.a((String) next.getKey());
                if (a2 != null) {
                    linkedHashMap.put(a2, value);
                }
            }
            return new b(b2, linkedHashMap, producer);
        }
    }

    @MCKeep
    public static final Event customEvent(String str) {
        return Companion.customEvent(str);
    }

    public abstract void track(Event... eventArr);

    @MCKeep
    public static final Event customEvent(String str, Map<String, ? extends Object> map) {
        return Companion.customEvent(str, map);
    }

    @MCKeep
    public static final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer) {
        return Companion.customEvent(str, map, producer);
    }
}
