package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AccessTokenAppIdPair;
import com.facebook.appevents.AppEvent;
import com.facebook.internal.Utility;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AppEventDiskStore {
    public static final AppEventDiskStore INSTANCE = new AppEventDiskStore();
    private static final String TAG = AppEventDiskStore.class.getName();

    private AppEventDiskStore() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x009a A[Catch:{ Exception -> 0x003b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized com.facebook.appevents.PersistedEvents readAndClearStore() {
        /*
            java.lang.Class<com.facebook.appevents.AppEventDiskStore> r0 = com.facebook.appevents.AppEventDiskStore.class
            monitor-enter(r0)
            com.facebook.appevents.internal.AppEventUtility.assertIsNotMainThread()     // Catch:{ all -> 0x0038 }
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ all -> 0x0038 }
            r2 = 0
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.FileInputStream r3 = r1.openFileInput(r3)     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            java.lang.String r4 = "context.openFileInput(PERSISTED_EVENTS_FILENAME)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            com.facebook.appevents.AppEventDiskStore$MovedClassObjectInputStream r4 = new com.facebook.appevents.AppEventDiskStore$MovedClassObjectInputStream     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            r5.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0050, Exception -> 0x004d, all -> 0x0049 }
            java.lang.Object r3 = r4.readObject()     // Catch:{ FileNotFoundException -> 0x0085, Exception -> 0x0047 }
            java.lang.String r5 = "null cannot be cast to non-null type com.facebook.appevents.PersistedEvents"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)     // Catch:{ FileNotFoundException -> 0x0085, Exception -> 0x0047 }
            com.facebook.appevents.PersistedEvents r3 = (com.facebook.appevents.PersistedEvents) r3     // Catch:{ FileNotFoundException -> 0x0085, Exception -> 0x0047 }
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch:{ all -> 0x0038 }
            java.lang.String r2 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r2)     // Catch:{ Exception -> 0x003b }
            r1.delete()     // Catch:{ Exception -> 0x003b }
            goto L_0x0043
        L_0x0038:
            r1 = move-exception
            goto L_0x00a1
        L_0x003b:
            r1 = move-exception
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0038 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            android.util.Log.w(r2, r4, r1)     // Catch:{ all -> 0x0038 }
        L_0x0043:
            r2 = r3
            goto L_0x0098
        L_0x0045:
            r2 = move-exception
            goto L_0x006f
        L_0x0047:
            r3 = move-exception
            goto L_0x0052
        L_0x0049:
            r3 = move-exception
            r4 = r2
            r2 = r3
            goto L_0x006f
        L_0x004d:
            r3 = move-exception
            r4 = r2
            goto L_0x0052
        L_0x0050:
            r4 = r2
            goto L_0x0085
        L_0x0052:
            java.lang.String r5 = TAG     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = "Got unexpected exception while reading events: "
            android.util.Log.w(r5, r6, r3)     // Catch:{ all -> 0x0045 }
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch:{ Exception -> 0x0066 }
            r1.delete()     // Catch:{ Exception -> 0x0066 }
            goto L_0x0098
        L_0x0066:
            r1 = move-exception
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0038 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
        L_0x006b:
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x0038 }
            goto L_0x0098
        L_0x006f:
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch:{ Exception -> 0x007c }
            r1.delete()     // Catch:{ Exception -> 0x007c }
            goto L_0x0084
        L_0x007c:
            r1 = move-exception
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0038 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x0038 }
        L_0x0084:
            throw r2     // Catch:{ all -> 0x0038 }
        L_0x0085:
            com.facebook.internal.Utility.closeQuietly(r4)     // Catch:{ all -> 0x0038 }
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch:{ Exception -> 0x0092 }
            r1.delete()     // Catch:{ Exception -> 0x0092 }
            goto L_0x0098
        L_0x0092:
            r1 = move-exception
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0038 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            goto L_0x006b
        L_0x0098:
            if (r2 != 0) goto L_0x009f
            com.facebook.appevents.PersistedEvents r2 = new com.facebook.appevents.PersistedEvents     // Catch:{ all -> 0x0038 }
            r2.<init>()     // Catch:{ all -> 0x0038 }
        L_0x009f:
            monitor-exit(r0)
            return r2
        L_0x00a1:
            monitor-exit(r0)     // Catch:{ all -> 0x0038 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEventDiskStore.readAndClearStore():com.facebook.appevents.PersistedEvents");
    }

    public static final void saveEventsToDisk$facebook_core_release(PersistedEvents persistedEvents) {
        Context applicationContext = FacebookSdk.getApplicationContext();
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(applicationContext.openFileOutput("AppEventsLogger.persistedevents", 0)));
            try {
                objectOutputStream2.writeObject(persistedEvents);
                Utility.closeQuietly(objectOutputStream2);
            } catch (Throwable th) {
                th = th;
                objectOutputStream = objectOutputStream2;
                try {
                    Log.w(TAG, "Got unexpected exception while persisting events: ", th);
                    try {
                        applicationContext.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    } catch (Exception unused) {
                    }
                } finally {
                    Utility.closeQuietly(objectOutputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            Log.w(TAG, "Got unexpected exception while persisting events: ", th);
            applicationContext.getFileStreamPath("AppEventsLogger.persistedevents").delete();
        }
    }

    private static final class MovedClassObjectInputStream extends ObjectInputStream {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

        public MovedClassObjectInputStream(InputStream inputStream) {
            super(inputStream);
        }

        /* access modifiers changed from: protected */
        public ObjectStreamClass readClassDescriptor() {
            ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
            if (Intrinsics.areEqual((Object) readClassDescriptor.getName(), (Object) "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1")) {
                readClassDescriptor = ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            } else if (Intrinsics.areEqual((Object) readClassDescriptor.getName(), (Object) "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV2")) {
                readClassDescriptor = ObjectStreamClass.lookup(AppEvent.SerializationProxyV2.class);
            }
            Intrinsics.checkNotNullExpressionValue(readClassDescriptor, "resultClassDescriptor");
            return readClassDescriptor;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
