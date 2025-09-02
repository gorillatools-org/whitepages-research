package com.google.firebase.sessions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

public final class SessionLifecycleClient {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_QUEUED_MESSAGES = 20;
    public static final String TAG = "SessionLifecycleClient";
    private final CoroutineContext backgroundDispatcher;
    /* access modifiers changed from: private */
    public final LinkedBlockingDeque<Message> queuedMessages = new LinkedBlockingDeque<>(20);
    /* access modifiers changed from: private */
    public Messenger service;
    /* access modifiers changed from: private */
    public boolean serviceBound;
    private final SessionLifecycleClient$serviceConnection$1 serviceConnection = new SessionLifecycleClient$serviceConnection$1(this);

    public SessionLifecycleClient(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        this.backgroundDispatcher = coroutineContext;
    }

    public static final class ClientUpdateHandler extends Handler {
        private final CoroutineContext backgroundDispatcher;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ClientUpdateHandler(CoroutineContext coroutineContext) {
            super(Looper.getMainLooper());
            Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
            this.backgroundDispatcher = coroutineContext;
        }

        public void handleMessage(Message message) {
            String str;
            Intrinsics.checkNotNullParameter(message, "msg");
            if (message.what == 3) {
                Bundle data = message.getData();
                if (data == null || (str = data.getString(SessionLifecycleService.SESSION_UPDATE_EXTRA)) == null) {
                    str = "";
                }
                handleSessionUpdate(str);
                return;
            }
            Log.w(SessionLifecycleClient.TAG, "Received unexpected event from the SessionLifecycleService: " + message);
            super.handleMessage(message);
        }

        private final void handleSessionUpdate(String str) {
            Log.d(SessionLifecycleClient.TAG, "Session update received.");
            Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1(str, (Continuation) null), 3, (Object) null);
        }
    }

    public final void bindToService(SessionLifecycleServiceBinder sessionLifecycleServiceBinder) {
        Intrinsics.checkNotNullParameter(sessionLifecycleServiceBinder, "sessionLifecycleServiceBinder");
        sessionLifecycleServiceBinder.bindToService(new Messenger(new ClientUpdateHandler(this.backgroundDispatcher)), this.serviceConnection);
    }

    public final void foregrounded() {
        sendLifecycleEvent(1);
    }

    public final void backgrounded() {
        sendLifecycleEvent(2);
    }

    private final void sendLifecycleEvent(int i) {
        List<Message> drainQueue = drainQueue();
        Message obtain = Message.obtain((Handler) null, i, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtain, "obtain(null, messageCode, 0, 0)");
        drainQueue.add(obtain);
        sendLifecycleEvents(drainQueue);
    }

    /* access modifiers changed from: private */
    public final Job sendLifecycleEvents(List<Message> list) {
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionLifecycleClient$sendLifecycleEvents$1(this, list, (Continuation) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void sendMessageToServer(Message message) {
        if (this.service != null) {
            try {
                Log.d(TAG, "Sending lifecycle " + message.what + " to service");
                Messenger messenger = this.service;
                if (messenger != null) {
                    messenger.send(message);
                }
            } catch (RemoteException e) {
                Log.w(TAG, "Unable to deliver message: " + message.what, e);
                queueMessage(message);
            }
        } else {
            queueMessage(message);
        }
    }

    private final void queueMessage(Message message) {
        if (this.queuedMessages.offer(message)) {
            Log.d(TAG, "Queued message " + message.what + ". Queue size " + this.queuedMessages.size());
            return;
        }
        Log.d(TAG, "Failed to enqueue message " + message.what + ". Dropping.");
    }

    /* access modifiers changed from: private */
    public final List<Message> drainQueue() {
        ArrayList arrayList = new ArrayList();
        this.queuedMessages.drainTo(arrayList);
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.os.Message} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Message getLatestByCode(java.util.List<android.os.Message> r7, int r8) {
        /*
            r6 = this;
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x000b:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0020
            java.lang.Object r1 = r7.next()
            r2 = r1
            android.os.Message r2 = (android.os.Message) r2
            int r2 = r2.what
            if (r2 != r8) goto L_0x000b
            r0.add(r1)
            goto L_0x000b
        L_0x0020:
            java.util.Iterator r7 = r0.iterator()
            boolean r8 = r7.hasNext()
            if (r8 != 0) goto L_0x002c
            r7 = 0
            goto L_0x0057
        L_0x002c:
            java.lang.Object r8 = r7.next()
            boolean r0 = r7.hasNext()
            if (r0 != 0) goto L_0x0038
        L_0x0036:
            r7 = r8
            goto L_0x0057
        L_0x0038:
            r0 = r8
            android.os.Message r0 = (android.os.Message) r0
            long r0 = r0.getWhen()
        L_0x003f:
            java.lang.Object r2 = r7.next()
            r3 = r2
            android.os.Message r3 = (android.os.Message) r3
            long r3 = r3.getWhen()
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0050
            r8 = r2
            r0 = r3
        L_0x0050:
            boolean r2 = r7.hasNext()
            if (r2 != 0) goto L_0x003f
            goto L_0x0036
        L_0x0057:
            android.os.Message r7 = (android.os.Message) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.SessionLifecycleClient.getLatestByCode(java.util.List, int):android.os.Message");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
