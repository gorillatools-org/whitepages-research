package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;

class SnackbarManager {
    private static SnackbarManager snackbarManager;
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            SnackbarManager snackbarManager = SnackbarManager.this;
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
            snackbarManager.handleTimeout((SnackbarRecord) null);
            return true;
        }
    });
    private final Object lock = new Object();

    interface Callback {
    }

    private static class SnackbarRecord {
    }

    private boolean isCurrentSnackbarLocked(Callback callback) {
        return false;
    }

    static SnackbarManager getInstance() {
        if (snackbarManager == null) {
            snackbarManager = new SnackbarManager();
        }
        return snackbarManager;
    }

    private SnackbarManager() {
    }

    public void pauseTimeout(Callback callback) {
        synchronized (this.lock) {
            try {
                if (isCurrentSnackbarLocked(callback)) {
                    throw null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void restoreTimeoutIfPaused(Callback callback) {
        synchronized (this.lock) {
            try {
                if (isCurrentSnackbarLocked(callback)) {
                    throw null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private boolean cancelSnackbarLocked(SnackbarRecord snackbarRecord, int i) {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void handleTimeout(SnackbarRecord snackbarRecord) {
        synchronized (this.lock) {
            cancelSnackbarLocked(snackbarRecord, 2);
        }
    }
}
