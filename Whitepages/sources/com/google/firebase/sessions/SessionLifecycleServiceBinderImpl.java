package com.google.firebase.sessions;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SessionLifecycleServiceBinderImpl implements SessionLifecycleServiceBinder {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final String TAG = "LifecycleServiceBinder";
    private final Context appContext;

    public SessionLifecycleServiceBinderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        this.appContext = context;
    }

    public void bindToService(Messenger messenger, ServiceConnection serviceConnection) {
        boolean z;
        Intrinsics.checkNotNullParameter(messenger, "callback");
        Intrinsics.checkNotNullParameter(serviceConnection, "serviceConnection");
        Intent intent = new Intent(this.appContext, SessionLifecycleService.class);
        Log.d(TAG, "Binding service to application.");
        intent.setAction(String.valueOf(Process.myPid()));
        intent.putExtra(SessionLifecycleService.CLIENT_CALLBACK_MESSENGER, messenger);
        intent.setPackage(this.appContext.getPackageName());
        try {
            z = this.appContext.bindService(intent, serviceConnection, 65);
        } catch (SecurityException e) {
            Log.w(TAG, "Failed to bind session lifecycle service to application.", e);
            z = false;
        }
        if (!z) {
            unbindServiceSafely(this.appContext, serviceConnection);
            Log.i(TAG, "Session lifecycle service binding failed.");
        }
    }

    private final Object unbindServiceSafely(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
            return Unit.INSTANCE;
        } catch (IllegalArgumentException e) {
            return Integer.valueOf(Log.w(TAG, "Session lifecycle service binding failed.", e));
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
