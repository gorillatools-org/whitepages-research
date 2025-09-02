package com.google.firebase.sessions.api;

import android.util.Log;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public final class FirebaseSessionsDependencies {
    public static final FirebaseSessionsDependencies INSTANCE = new FirebaseSessionsDependencies();
    private static final String TAG = "SessionsDependencies";
    private static final Map<SessionSubscriber.Name, Dependency> dependencies = Collections.synchronizedMap(new LinkedHashMap());

    private FirebaseSessionsDependencies() {
    }

    public static final void addDependency(SessionSubscriber.Name name) {
        Intrinsics.checkNotNullParameter(name, "subscriberName");
        if (name != SessionSubscriber.Name.PERFORMANCE) {
            Map<SessionSubscriber.Name, Dependency> map = dependencies;
            if (map.containsKey(name)) {
                Log.d(TAG, "Dependency " + name + " already added.");
                return;
            }
            Intrinsics.checkNotNullExpressionValue(map, "dependencies");
            map.put(name, new Dependency(MutexKt.Mutex(true), (SessionSubscriber) null, 2, (DefaultConstructorMarker) null));
            Log.d(TAG, "Dependency to " + name + " added.");
            return;
        }
        throw new IllegalArgumentException("Incompatible versions of Firebase Perf and Firebase Sessions.\nA safe combination would be:\n  firebase-sessions:1.1.0\n  firebase-crashlytics:18.5.0\n  firebase-perf:20.5.0\nFor more information contact Firebase Support.");
    }

    public static final void register(SessionSubscriber sessionSubscriber) {
        Intrinsics.checkNotNullParameter(sessionSubscriber, "subscriber");
        SessionSubscriber.Name sessionSubscriberName = sessionSubscriber.getSessionSubscriberName();
        Dependency dependency = INSTANCE.getDependency(sessionSubscriberName);
        if (dependency.getSubscriber() != null) {
            Log.d(TAG, "Subscriber " + sessionSubscriberName + " already registered.");
            return;
        }
        dependency.setSubscriber(sessionSubscriber);
        Log.d(TAG, "Subscriber " + sessionSubscriberName + " registered.");
        Mutex.DefaultImpls.unlock$default(dependency.getMutex(), (Object) null, 1, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.google.firebase.sessions.api.SessionSubscriber$Name} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getRegisteredSubscribers$com_google_firebase_firebase_sessions(kotlin.coroutines.Continuation r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.google.firebase.sessions.api.FirebaseSessionsDependencies$getRegisteredSubscribers$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.google.firebase.sessions.api.FirebaseSessionsDependencies$getRegisteredSubscribers$1 r0 = (com.google.firebase.sessions.api.FirebaseSessionsDependencies$getRegisteredSubscribers$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.google.firebase.sessions.api.FirebaseSessionsDependencies$getRegisteredSubscribers$1 r0 = new com.google.firebase.sessions.api.FirebaseSessionsDependencies$getRegisteredSubscribers$1
            r0.<init>(r10, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r2 = r0.L$5
            java.lang.Object r5 = r0.L$4
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r0.L$2
            com.google.firebase.sessions.api.SessionSubscriber$Name r7 = (com.google.firebase.sessions.api.SessionSubscriber.Name) r7
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$0
            java.util.Map r9 = (java.util.Map) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00a2
        L_0x0040:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.Map<com.google.firebase.sessions.api.SessionSubscriber$Name, com.google.firebase.sessions.api.FirebaseSessionsDependencies$Dependency> r11 = dependencies
            java.lang.String r2 = "dependencies"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            int r5 = r11.size()
            int r5 = kotlin.collections.MapsKt.mapCapacity(r5)
            r2.<init>(r5)
            java.util.Set r11 = r11.entrySet()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r11 = r11.iterator()
            r8 = r11
            r5 = r2
        L_0x006b:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x00b5
            java.lang.Object r11 = r8.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            java.lang.Object r2 = r11.getKey()
            java.lang.Object r6 = r11.getKey()
            r7 = r6
            com.google.firebase.sessions.api.SessionSubscriber$Name r7 = (com.google.firebase.sessions.api.SessionSubscriber.Name) r7
            java.lang.Object r11 = r11.getValue()
            com.google.firebase.sessions.api.FirebaseSessionsDependencies$Dependency r11 = (com.google.firebase.sessions.api.FirebaseSessionsDependencies.Dependency) r11
            kotlinx.coroutines.sync.Mutex r6 = r11.getMutex()
            r0.L$0 = r5
            r0.L$1 = r8
            r0.L$2 = r7
            r0.L$3 = r6
            r0.L$4 = r5
            r0.L$5 = r2
            r0.label = r3
            java.lang.Object r11 = r6.lock(r4, r0)
            if (r11 != r1) goto L_0x00a1
            return r1
        L_0x00a1:
            r9 = r5
        L_0x00a2:
            com.google.firebase.sessions.api.FirebaseSessionsDependencies r11 = INSTANCE     // Catch:{ all -> 0x00b0 }
            com.google.firebase.sessions.api.SessionSubscriber r11 = r11.getSubscriber$com_google_firebase_firebase_sessions(r7)     // Catch:{ all -> 0x00b0 }
            r6.unlock(r4)
            r5.put(r2, r11)
            r5 = r9
            goto L_0x006b
        L_0x00b0:
            r11 = move-exception
            r6.unlock(r4)
            throw r11
        L_0x00b5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.api.FirebaseSessionsDependencies.getRegisteredSubscribers$com_google_firebase_firebase_sessions(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final SessionSubscriber getSubscriber$com_google_firebase_firebase_sessions(SessionSubscriber.Name name) {
        Intrinsics.checkNotNullParameter(name, "subscriberName");
        SessionSubscriber subscriber = getDependency(name).getSubscriber();
        if (subscriber != null) {
            return subscriber;
        }
        throw new IllegalStateException("Subscriber " + name + " has not been registered.");
    }

    public final void reset$com_google_firebase_firebase_sessions() {
        dependencies.clear();
    }

    private final Dependency getDependency(SessionSubscriber.Name name) {
        Map<SessionSubscriber.Name, Dependency> map = dependencies;
        Intrinsics.checkNotNullExpressionValue(map, "dependencies");
        Dependency dependency = map.get(name);
        if (dependency != null) {
            Intrinsics.checkNotNullExpressionValue(dependency, "dependencies.getOrElse(s…load time.\"\n      )\n    }");
            return dependency;
        }
        throw new IllegalStateException("Cannot get dependency " + name + ". Dependencies should be added at class load time.");
    }

    private static final class Dependency {
        private final Mutex mutex;
        private SessionSubscriber subscriber;

        public static /* synthetic */ Dependency copy$default(Dependency dependency, Mutex mutex2, SessionSubscriber sessionSubscriber, int i, Object obj) {
            if ((i & 1) != 0) {
                mutex2 = dependency.mutex;
            }
            if ((i & 2) != 0) {
                sessionSubscriber = dependency.subscriber;
            }
            return dependency.copy(mutex2, sessionSubscriber);
        }

        public final Mutex component1() {
            return this.mutex;
        }

        public final SessionSubscriber component2() {
            return this.subscriber;
        }

        public final Dependency copy(Mutex mutex2, SessionSubscriber sessionSubscriber) {
            Intrinsics.checkNotNullParameter(mutex2, "mutex");
            return new Dependency(mutex2, sessionSubscriber);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Dependency)) {
                return false;
            }
            Dependency dependency = (Dependency) obj;
            return Intrinsics.areEqual((Object) this.mutex, (Object) dependency.mutex) && Intrinsics.areEqual((Object) this.subscriber, (Object) dependency.subscriber);
        }

        public int hashCode() {
            int hashCode = this.mutex.hashCode() * 31;
            SessionSubscriber sessionSubscriber = this.subscriber;
            return hashCode + (sessionSubscriber == null ? 0 : sessionSubscriber.hashCode());
        }

        public String toString() {
            return "Dependency(mutex=" + this.mutex + ", subscriber=" + this.subscriber + ')';
        }

        public Dependency(Mutex mutex2, SessionSubscriber sessionSubscriber) {
            Intrinsics.checkNotNullParameter(mutex2, "mutex");
            this.mutex = mutex2;
            this.subscriber = sessionSubscriber;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Dependency(Mutex mutex2, SessionSubscriber sessionSubscriber, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(mutex2, (i & 2) != 0 ? null : sessionSubscriber);
        }

        public final Mutex getMutex() {
            return this.mutex;
        }

        public final SessionSubscriber getSubscriber() {
            return this.subscriber;
        }

        public final void setSubscriber(SessionSubscriber sessionSubscriber) {
            this.subscriber = sessionSubscriber;
        }
    }
}
