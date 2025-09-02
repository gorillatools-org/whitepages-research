package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.foreground.SystemForegroundDispatcher;

public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.Callback {
    /* access modifiers changed from: private */
    public static final String TAG = Logger.tagWithPrefix("SystemFgService");
    private static SystemForegroundService sForegroundService = null;
    SystemForegroundDispatcher mDispatcher;
    private Handler mHandler;
    private boolean mIsShutdown;
    NotificationManager mNotificationManager;

    public void onCreate() {
        super.onCreate();
        sForegroundService = this;
        initializeDispatcher();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (this.mIsShutdown) {
            Logger.get().info(TAG, "Re-initializing SystemForegroundService after a request to shut-down.");
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent == null) {
            return 3;
        }
        this.mDispatcher.onStartCommand(intent);
        return 3;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    private void initializeDispatcher() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher systemForegroundDispatcher = new SystemForegroundDispatcher(getApplicationContext());
        this.mDispatcher = systemForegroundDispatcher;
        systemForegroundDispatcher.setCallback(this);
    }

    public void stop() {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "All commands completed.");
        stopForeground(true);
        sForegroundService = null;
        stopSelf();
    }

    public void startForeground(final int i, final int i2, final Notification notification) {
        this.mHandler.post(new Runnable() {
            public void run() {
                int i = Build.VERSION.SDK_INT;
                if (i >= 31) {
                    Api31Impl.startForeground(SystemForegroundService.this, i, notification, i2);
                } else if (i >= 29) {
                    Api29Impl.startForeground(SystemForegroundService.this, i, notification, i2);
                } else {
                    SystemForegroundService.this.startForeground(i, notification);
                }
            }
        });
    }

    public void notify(final int i, final Notification notification) {
        this.mHandler.post(new Runnable() {
            public void run() {
                SystemForegroundService.this.mNotificationManager.notify(i, notification);
            }
        });
    }

    public void cancelNotification(final int i) {
        this.mHandler.post(new Runnable() {
            public void run() {
                SystemForegroundService.this.mNotificationManager.cancel(i);
            }
        });
    }

    static class Api29Impl {
        static void startForeground(Service service, int i, Notification notification, int i2) {
            service.startForeground(i, notification, i2);
        }
    }

    static class Api31Impl {
        static void startForeground(Service service, int i, Notification notification, int i2) {
            try {
                service.startForeground(i, notification, i2);
            } catch (ForegroundServiceStartNotAllowedException e) {
                Logger.get().warning(SystemForegroundService.TAG, "Unable to start foreground service", e);
            }
        }
    }
}
