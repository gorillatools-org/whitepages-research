package com.facebook.react.packagerconnection;

import com.facebook.common.logging.FLog;

public abstract class RequestOnlyHandler implements RequestHandler {
    public abstract void onRequest(Object obj, Responder responder);

    public final void onNotification(Object obj) {
        FLog.e(JSPackagerClient.class.getSimpleName(), "Notification is not supported");
    }
}
