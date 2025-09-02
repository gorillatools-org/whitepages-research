package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public abstract class PlatformServiceClient implements ServiceConnection {
    private final String applicationId;
    private final Context context;
    private final Handler handler;
    private CompletedListener listener;
    private final String nonce;
    private final int protocolVersion;
    private final int replyMessage;
    private final int requestMessage;
    private boolean running;
    private Messenger sender;

    public interface CompletedListener {
        void completed(Bundle bundle);
    }

    /* access modifiers changed from: protected */
    public abstract void populateRequestBundle(Bundle bundle);

    public PlatformServiceClient(Context context2, int i, int i2, int i3, String str, String str2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext != null ? applicationContext : context2;
        this.requestMessage = i;
        this.replyMessage = i2;
        this.applicationId = str;
        this.protocolVersion = i3;
        this.nonce = str2;
        this.handler = new Handler(this) {
            final /* synthetic */ PlatformServiceClient this$0;

            {
                this.this$0 = r1;
            }

            public void handleMessage(Message message) {
                if (!CrashShieldHandler.isObjectCrashing(this)) {
                    try {
                        Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
                        this.this$0.handleMessage(message);
                    } catch (Throwable th) {
                        CrashShieldHandler.handleThrowable(th, this);
                    }
                }
            }
        };
    }

    public final void setCompletedListener(CompletedListener completedListener) {
        this.listener = completedListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0025, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean start() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.running     // Catch:{ all -> 0x0026 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r3)
            return r1
        L_0x0008:
            int r0 = r3.protocolVersion     // Catch:{ all -> 0x0026 }
            int r0 = com.facebook.internal.NativeProtocol.getLatestAvailableProtocolVersionForService(r0)     // Catch:{ all -> 0x0026 }
            r2 = -1
            if (r0 != r2) goto L_0x0013
            monitor-exit(r3)
            return r1
        L_0x0013:
            android.content.Context r0 = r3.context     // Catch:{ all -> 0x0026 }
            android.content.Intent r0 = com.facebook.internal.NativeProtocol.createPlatformServiceIntent(r0)     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r1 = 1
            r3.running = r1     // Catch:{ all -> 0x0026 }
            android.content.Context r2 = r3.context     // Catch:{ all -> 0x0026 }
            r2.bindService(r0, r3, r1)     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r3)
            return r1
        L_0x0026:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.PlatformServiceClient.start():boolean");
    }

    public final void cancel() {
        this.running = false;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Intrinsics.checkNotNullParameter(componentName, "name");
        Intrinsics.checkNotNullParameter(iBinder, "service");
        this.sender = new Messenger(iBinder);
        sendMessage();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "name");
        this.sender = null;
        try {
            this.context.unbindService(this);
        } catch (IllegalArgumentException unused) {
        }
        callback((Bundle) null);
    }

    private final void sendMessage() {
        Bundle bundle = new Bundle();
        bundle.putString("com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
        String str = this.nonce;
        if (str != null) {
            bundle.putString("com.facebook.platform.extra.NONCE", str);
        }
        populateRequestBundle(bundle);
        Message obtain = Message.obtain((Handler) null, this.requestMessage);
        obtain.arg1 = this.protocolVersion;
        obtain.setData(bundle);
        obtain.replyTo = new Messenger(this.handler);
        try {
            Messenger messenger = this.sender;
            if (messenger != null) {
                messenger.send(obtain);
            }
        } catch (RemoteException unused) {
            callback((Bundle) null);
        }
    }

    /* access modifiers changed from: protected */
    public final void handleMessage(Message message) {
        Intrinsics.checkNotNullParameter(message, StackTraceHelper.MESSAGE_KEY);
        if (message.what == this.replyMessage) {
            Bundle data = message.getData();
            if (data.getString("com.facebook.platform.status.ERROR_TYPE") != null) {
                callback((Bundle) null);
            } else {
                callback(data);
            }
            try {
                this.context.unbindService(this);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private final void callback(Bundle bundle) {
        if (this.running) {
            this.running = false;
            CompletedListener completedListener = this.listener;
            if (completedListener != null) {
                completedListener.completed(bundle);
            }
        }
    }
}
