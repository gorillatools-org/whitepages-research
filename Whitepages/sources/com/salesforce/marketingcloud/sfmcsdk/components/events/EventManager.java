package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class EventManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "~$EventManager";
    /* access modifiers changed from: private */
    public static final List<EventSubscriber> subscribers = new ArrayList();
    private final String moduleName;

    public static final Event customEvent(String str) {
        return Companion.customEvent(str);
    }

    public static final Event customEvent(String str, Map<String, ? extends Object> map) {
        return Companion.customEvent(str, map);
    }

    public static final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer) {
        return Companion.customEvent(str, map, producer);
    }

    public static final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer, Event.Category category) {
        return Companion.customEvent(str, map, producer, category);
    }

    public EventManager(String str) {
        Intrinsics.checkNotNullParameter(str, "moduleName");
        this.moduleName = str;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Event customEvent(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return customEvent$default(this, str, (Map) null, (Event.Producer) null, (Event.Category) null, 14, (Object) null);
        }

        public final Event customEvent(String str, Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, k.a.h);
            return customEvent$default(this, str, map, (Event.Producer) null, (Event.Category) null, 12, (Object) null);
        }

        public final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, k.a.h);
            Intrinsics.checkNotNullParameter(producer, "producer");
            return customEvent$default(this, str, map, producer, (Event.Category) null, 8, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ Event customEvent$default(Companion companion, String str, Map map, Event.Producer producer, Event.Category category, int i, Object obj) {
            if ((i & 2) != 0) {
                map = MapsKt.emptyMap();
            }
            if ((i & 4) != 0) {
                producer = Event.Producer.SFMC_SDK;
            }
            if ((i & 8) != 0) {
                category = Event.Category.ENGAGEMENT;
            }
            return companion.customEvent(str, map, producer, category);
        }

        public final Event customEvent(String str, Map<String, ? extends Object> map, Event.Producer producer, Event.Category category) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(map, k.a.h);
            Intrinsics.checkNotNullParameter(producer, "producer");
            Intrinsics.checkNotNullParameter(category, "category");
            String validatedName$default = getValidatedName$default(this, str, (String) null, 2, (Object) null);
            if (validatedName$default == null) {
                return null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry next : map.entrySet()) {
                String validatedAttributeKey$default = getValidatedAttributeKey$default(EventManager.Companion, (String) next.getKey(), (String) null, 2, (Object) null);
                if (validatedAttributeKey$default != null) {
                    linkedHashMap.put(validatedAttributeKey$default, next.getValue());
                }
            }
            return new CustomEvent(validatedName$default, linkedHashMap, producer, category);
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(10:9|10|11|13|14|15|16|25|22|7) */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0024, code lost:
            continue;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0059 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void publish$sfmcsdk_release(com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors r7, com.salesforce.marketingcloud.sfmcsdk.components.events.Event... r8) {
            /*
                r6 = this;
                java.lang.String r0 = "executors"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "events"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.util.List r0 = kotlin.collections.ArraysKt.filterNotNull(r8)
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x0015
                return
            L_0x0015:
                java.util.List r0 = com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager.subscribers
                monitor-enter(r0)
                java.util.List r1 = com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager.subscribers     // Catch:{ all -> 0x003d }
                java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x003d }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x003d }
            L_0x0024:
                boolean r2 = r1.hasNext()     // Catch:{ all -> 0x003d }
                if (r2 == 0) goto L_0x0066
                java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x003d }
                com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber r2 = (com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber) r2     // Catch:{ all -> 0x003d }
                com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r3 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ Exception -> 0x003f }
                java.lang.String r4 = "~$EventManager"
                com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$1 r5 = new com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$1     // Catch:{ Exception -> 0x003f }
                r5.<init>(r8, r2)     // Catch:{ Exception -> 0x003f }
                r3.d(r4, r5)     // Catch:{ Exception -> 0x003f }
                goto L_0x003f
            L_0x003d:
                r7 = move-exception
                goto L_0x006a
            L_0x003f:
                java.util.concurrent.ExecutorService r3 = r7.getDiskIO()     // Catch:{ Exception -> 0x0059 }
                java.lang.Class r4 = r2.getClass()     // Catch:{ Exception -> 0x0059 }
                java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x0059 }
                java.lang.String r5 = "subscriber::class.java.name"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ Exception -> 0x0059 }
                com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$2 r5 = new com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$2     // Catch:{ Exception -> 0x0059 }
                r5.<init>(r2, r8)     // Catch:{ Exception -> 0x0059 }
                com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt.namedRunnable(r3, r4, r5)     // Catch:{ Exception -> 0x0059 }
                goto L_0x0024
            L_0x0059:
                com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r3 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ all -> 0x003d }
                java.lang.String r4 = "~$EventManager"
                com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$3 r5 = new com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager$Companion$publish$1$1$3     // Catch:{ all -> 0x003d }
                r5.<init>(r8, r2)     // Catch:{ all -> 0x003d }
                r3.e(r4, r5)     // Catch:{ all -> 0x003d }
                goto L_0x0024
            L_0x0066:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003d }
                monitor-exit(r0)
                return
            L_0x006a:
                monitor-exit(r0)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager.Companion.publish$sfmcsdk_release(com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors, com.salesforce.marketingcloud.sfmcsdk.components.events.Event[]):void");
        }

        public final void staticTearDown$sfmcsdk_release() {
            synchronized (EventManager.subscribers) {
                EventManager.subscribers.clear();
                Unit unit = Unit.INSTANCE;
            }
        }

        static /* synthetic */ String getValidatedAttributeKey$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "Attribute Key";
            }
            return companion.getValidatedAttributeKey(str, str2);
        }

        private final String getValidatedAttributeKey(String str, String str2) {
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
                return getValidatedName(str, str2);
            }
            SFMCSdkLogger.INSTANCE.w(EventManager.TAG, new EventManager$Companion$getValidatedAttributeKey$1(str2, str));
            return null;
        }

        static /* synthetic */ String getValidatedName$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "Event Name";
            }
            return companion.getValidatedName(str, str2);
        }

        private final String getValidatedName(String str, String str2) {
            String obj = StringsKt.trim(str).toString();
            if (!StringsKt.isBlank(obj) && !StringsKt.startsWith$default(obj, "$", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) obj, (CharSequence) "\r", false, 2, (Object) null)) {
                return obj;
            }
            SFMCSdkLogger.INSTANCE.w(EventManager.TAG, new EventManager$Companion$getValidatedName$1$1(str2, str));
            return null;
        }

        public final Event identityEvent$sfmcsdk_release() {
            return customEvent$default(this, "IdentityUpdate", Identity.Companion.toEvent$sfmcsdk_release(), (Event.Producer) null, Event.Category.IDENTITY, 4, (Object) null);
        }
    }

    public final void track(Event... eventArr) {
        Event.Producer producer;
        Intrinsics.checkNotNullParameter(eventArr, "events");
        String str = this.moduleName;
        if (Intrinsics.areEqual((Object) str, (Object) ModuleIdentifier.PUSH.name())) {
            producer = Event.Producer.PUSH;
        } else if (Intrinsics.areEqual((Object) str, (Object) ModuleIdentifier.CDP.name())) {
            producer = Event.Producer.CDP;
        } else {
            producer = Event.Producer.SFMC_SDK;
        }
        ArrayList arrayList = new ArrayList();
        for (Event event : eventArr) {
            Event customEvent$default = Companion.customEvent$default(Companion, event.name(), event.attributes(), producer, (Event.Category) null, 8, (Object) null);
            if (customEvent$default != null) {
                arrayList.add(customEvent$default);
            }
        }
        SFMCSdk.Companion companion = SFMCSdk.Companion;
        Object[] array = arrayList.toArray(new Event[0]);
        if (array != null) {
            Event[] eventArr2 = (Event[]) array;
            companion.track((Event[]) Arrays.copyOf(eventArr2, eventArr2.length));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public final void subscribe(EventSubscriber eventSubscriber) {
        Intrinsics.checkNotNullParameter(eventSubscriber, "subscriber");
        List<EventSubscriber> list = subscribers;
        synchronized (list) {
            list.add(eventSubscriber);
        }
    }

    public final void unsubscribe(EventSubscriber eventSubscriber) {
        Intrinsics.checkNotNullParameter(eventSubscriber, "subscriber");
        List<EventSubscriber> list = subscribers;
        synchronized (list) {
            list.remove(eventSubscriber);
        }
    }
}
