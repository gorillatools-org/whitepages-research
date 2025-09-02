package com.facebook.appevents;

import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PersistedEvents implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 20160629001L;
    private final HashMap events;

    public PersistedEvents() {
        this.events = new HashMap();
    }

    public PersistedEvents(HashMap hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "appEventMap");
        HashMap hashMap2 = new HashMap();
        this.events = hashMap2;
        hashMap2.putAll(hashMap);
    }

    public final Set entrySet() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Set entrySet = this.events.entrySet();
            Intrinsics.checkNotNullExpressionValue(entrySet, "events.entries");
            return entrySet;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final void addEvents(AccessTokenAppIdPair accessTokenAppIdPair, List list) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(accessTokenAppIdPair, "accessTokenAppIdPair");
                Intrinsics.checkNotNullParameter(list, "appEvents");
                if (!this.events.containsKey(accessTokenAppIdPair)) {
                    this.events.put(accessTokenAppIdPair, CollectionsKt.toMutableList((Collection) list));
                    return;
                }
                List list2 = (List) this.events.get(accessTokenAppIdPair);
                if (list2 != null) {
                    list2.addAll(list);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class SerializationProxyV1 implements Serializable {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final long serialVersionUID = 20160629001L;
        private final HashMap proxyEvents;

        public SerializationProxyV1(HashMap hashMap) {
            Intrinsics.checkNotNullParameter(hashMap, "proxyEvents");
            this.proxyEvents = hashMap;
        }

        private final Object readResolve() throws ObjectStreamException {
            return new PersistedEvents(this.proxyEvents);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    private final Object writeReplace() throws ObjectStreamException {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return new SerializationProxyV1(this.events);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
