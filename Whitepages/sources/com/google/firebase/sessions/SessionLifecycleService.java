package com.google.firebase.sessions;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.google.firebase.sessions.SessionGenerator;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

public final class SessionLifecycleService extends Service {
    public static final int BACKGROUNDED = 2;
    private static final int CLIENT_BOUND = 4;
    public static final String CLIENT_CALLBACK_MESSENGER = "ClientCallbackMessenger";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int FOREGROUNDED = 1;
    public static final int SESSION_UPDATED = 3;
    public static final String SESSION_UPDATE_EXTRA = "SessionUpdateExtra";
    public static final String TAG = "SessionLifecycleService";
    private final HandlerThread handlerThread = new HandlerThread("FirebaseSessions_HandlerThread");
    private MessageHandler messageHandler;
    private Messenger messenger;

    public final HandlerThread getHandlerThread$com_google_firebase_firebase_sessions() {
        return this.handlerThread;
    }

    public static final class MessageHandler extends Handler {
        private final ArrayList<Messenger> boundClients = new ArrayList<>();
        private boolean hasForegrounded;
        private long lastMsgTimeMs;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MessageHandler(Looper looper) {
            super(looper);
            Intrinsics.checkNotNullParameter(looper, "looper");
        }

        public void handleMessage(Message message) {
            Intrinsics.checkNotNullParameter(message, "msg");
            if (this.lastMsgTimeMs > message.getWhen()) {
                Log.d(SessionLifecycleService.TAG, "Ignoring old message from " + message.getWhen() + " which is older than " + this.lastMsgTimeMs + '.');
                return;
            }
            int i = message.what;
            if (i == 1) {
                handleForegrounding(message);
            } else if (i == 2) {
                handleBackgrounding(message);
            } else if (i != 4) {
                Log.w(SessionLifecycleService.TAG, "Received unexpected event from the SessionLifecycleClient: " + message);
                super.handleMessage(message);
            } else {
                handleClientBound(message);
            }
        }

        private final void handleForegrounding(Message message) {
            Log.d(SessionLifecycleService.TAG, "Activity foregrounding at " + message.getWhen() + '.');
            if (!this.hasForegrounded) {
                Log.d(SessionLifecycleService.TAG, "Cold start detected.");
                this.hasForegrounded = true;
                newSession();
            } else if (isSessionRestart(message.getWhen())) {
                Log.d(SessionLifecycleService.TAG, "Session too long in background. Creating new session.");
                newSession();
            }
            this.lastMsgTimeMs = message.getWhen();
        }

        private final void handleBackgrounding(Message message) {
            Log.d(SessionLifecycleService.TAG, "Activity backgrounding at " + message.getWhen());
            this.lastMsgTimeMs = message.getWhen();
        }

        private final void handleClientBound(Message message) {
            this.boundClients.add(message.replyTo);
            Messenger messenger = message.replyTo;
            Intrinsics.checkNotNullExpressionValue(messenger, "msg.replyTo");
            maybeSendSessionToClient(messenger);
            Log.d(SessionLifecycleService.TAG, "Client " + message.replyTo + " bound at " + message.getWhen() + ". Clients: " + this.boundClients.size());
        }

        private final void newSession() {
            try {
                SessionGenerator.Companion companion = SessionGenerator.Companion;
                companion.getInstance().generateNewSession();
                Log.d(SessionLifecycleService.TAG, "Generated new session.");
                broadcastSession();
                SessionDatastore.Companion.getInstance().updateSessionId(companion.getInstance().getCurrentSession().getSessionId());
            } catch (IllegalStateException e) {
                Log.w(SessionLifecycleService.TAG, "Failed to generate new session.", e);
            }
        }

        private final void broadcastSession() {
            Log.d(SessionLifecycleService.TAG, "Broadcasting new session");
            SessionFirelogPublisher.Companion.getInstance().logSession(SessionGenerator.Companion.getInstance().getCurrentSession());
            for (Messenger messenger : new ArrayList(this.boundClients)) {
                Intrinsics.checkNotNullExpressionValue(messenger, "it");
                maybeSendSessionToClient(messenger);
            }
        }

        private final void maybeSendSessionToClient(Messenger messenger) {
            try {
                if (this.hasForegrounded) {
                    sendSessionToClient(messenger, SessionGenerator.Companion.getInstance().getCurrentSession().getSessionId());
                    return;
                }
                String currentSessionId = SessionDatastore.Companion.getInstance().getCurrentSessionId();
                Log.d(SessionLifecycleService.TAG, "App has not yet foregrounded. Using previously stored session.");
                if (currentSessionId != null) {
                    sendSessionToClient(messenger, currentSessionId);
                }
            } catch (IllegalStateException e) {
                Log.w(SessionLifecycleService.TAG, "Failed to send session to client.", e);
            }
        }

        private final void sendSessionToClient(Messenger messenger, String str) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(SessionLifecycleService.SESSION_UPDATE_EXTRA, str);
                Message obtain = Message.obtain((Handler) null, 3, 0, 0);
                obtain.setData(bundle);
                messenger.send(obtain);
            } catch (DeadObjectException unused) {
                Log.d(SessionLifecycleService.TAG, "Removing dead client from list: " + messenger);
                this.boundClients.remove(messenger);
            } catch (Exception e) {
                Log.w(SessionLifecycleService.TAG, "Unable to push new session to " + messenger + '.', e);
            }
        }

        private final boolean isSessionRestart(long j) {
            return j - this.lastMsgTimeMs > Duration.m881getInWholeMillisecondsimpl(SessionsSettings.Companion.getInstance().m598getSessionRestartTimeoutUwyO8pc());
        }
    }

    public void onCreate() {
        super.onCreate();
        this.handlerThread.start();
        Looper looper = this.handlerThread.getLooper();
        Intrinsics.checkNotNullExpressionValue(looper, "handlerThread.looper");
        this.messageHandler = new MessageHandler(looper);
        this.messenger = new Messenger(this.messageHandler);
    }

    public IBinder onBind(Intent intent) {
        if (intent == null) {
            Log.d(TAG, "Service bound with null intent. Ignoring.");
            return null;
        }
        Log.d(TAG, "Service bound to new client on process " + intent.getAction());
        Messenger clientCallback = getClientCallback(intent);
        if (clientCallback != null) {
            Message obtain = Message.obtain((Handler) null, 4, 0, 0);
            obtain.replyTo = clientCallback;
            MessageHandler messageHandler2 = this.messageHandler;
            if (messageHandler2 != null) {
                messageHandler2.sendMessage(obtain);
            }
        }
        Messenger messenger2 = this.messenger;
        if (messenger2 != null) {
            return messenger2.getBinder();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.handlerThread.quit();
    }

    private final Messenger getClientCallback(Intent intent) {
        if (Build.VERSION.SDK_INT >= 33) {
            return (Messenger) intent.getParcelableExtra(CLIENT_CALLBACK_MESSENGER, Messenger.class);
        }
        return (Messenger) intent.getParcelableExtra(CLIENT_CALLBACK_MESSENGER);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
