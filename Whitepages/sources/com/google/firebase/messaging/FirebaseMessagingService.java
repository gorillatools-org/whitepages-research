package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    static final String ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN";
    static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    static final String EXTRA_TOKEN = "token";
    private static final int RECENTLY_RECEIVED_MESSAGE_IDS_MAX_SIZE = 10;
    private static final Queue<String> recentlyReceivedMessageIds = new ArrayDeque(10);
    private Rpc rpc;

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @Deprecated
    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    @Deprecated
    public void onSendError(String str, Exception exc) {
    }

    /* access modifiers changed from: protected */
    public Intent getStartCommandIntent(Intent intent) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (ACTION_REMOTE_INTENT.equals(action) || ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(action)) {
            handleMessageIntent(intent);
        } else if (ACTION_NEW_TOKEN.equals(action)) {
            onNewToken(intent.getStringExtra(EXTRA_TOKEN));
        } else {
            Log.d(Constants.TAG, "Unknown intent action: " + intent.getAction());
        }
    }

    private void handleMessageIntent(Intent intent) {
        if (!alreadyReceivedMessage(intent.getStringExtra(Constants.MessagePayloadKeys.MSGID))) {
            passMessageIntentToSdk(intent);
        }
        getRpc(this).messageHandled(new CloudMessage(intent));
    }

    private void passMessageIntentToSdk(Intent intent) {
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = Constants.MessageTypes.MESSAGE;
        }
        char c = 65535;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals(Constants.MessageTypes.DELETED)) {
                    c = 0;
                    break;
                }
                break;
            case 102161:
                if (stringExtra.equals(Constants.MessageTypes.MESSAGE)) {
                    c = 1;
                    break;
                }
                break;
            case 814694033:
                if (stringExtra.equals(Constants.MessageTypes.SEND_ERROR)) {
                    c = 2;
                    break;
                }
                break;
            case 814800675:
                if (stringExtra.equals(Constants.MessageTypes.SEND_EVENT)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                onDeletedMessages();
                return;
            case 1:
                MessagingAnalytics.logNotificationReceived(intent);
                dispatchMessage(intent);
                return;
            case 2:
                onSendError(getMessageId(intent), new SendException(intent.getStringExtra("error")));
                return;
            case 3:
                onMessageSent(intent.getStringExtra(Constants.MessagePayloadKeys.MSGID));
                return;
            default:
                Log.w(Constants.TAG, "Received message with unknown type: " + stringExtra);
                return;
        }
    }

    private void dispatchMessage(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        extras.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(extras)) {
            NotificationParams notificationParams = new NotificationParams(extras);
            ExecutorService newNetworkIOExecutor = FcmExecutors.newNetworkIOExecutor();
            try {
                if (!new DisplayNotification(this, notificationParams, newNetworkIOExecutor).handleNotification()) {
                    newNetworkIOExecutor.shutdown();
                    if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                        MessagingAnalytics.logNotificationForeground(intent);
                    }
                } else {
                    return;
                }
            } finally {
                newNetworkIOExecutor.shutdown();
            }
        }
        onMessageReceived(new RemoteMessage(extras));
    }

    private boolean alreadyReceivedMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Queue<String> queue = recentlyReceivedMessageIds;
        if (!queue.contains(str)) {
            if (queue.size() >= 10) {
                queue.remove();
            }
            queue.add(str);
            return false;
        } else if (!Log.isLoggable(Constants.TAG, 3)) {
            return true;
        } else {
            Log.d(Constants.TAG, "Received duplicate message: " + str);
            return true;
        }
    }

    private String getMessageId(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        return stringExtra == null ? intent.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER) : stringExtra;
    }

    private Rpc getRpc(Context context) {
        if (this.rpc == null) {
            this.rpc = new Rpc(context.getApplicationContext());
        }
        return this.rpc;
    }

    static void resetForTesting() {
        recentlyReceivedMessageIds.clear();
    }

    /* access modifiers changed from: package-private */
    public void setRpcForTesting(Rpc rpc2) {
        this.rpc = rpc2;
    }
}
