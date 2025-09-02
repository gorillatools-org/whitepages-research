package org.greenrobot.eventbus;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.android.AndroidComponents;

public class EventBusBuilder {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    boolean eventInheritance = true;
    ExecutorService executorService = DEFAULT_EXECUTOR_SERVICE;
    boolean ignoreGeneratedIndex;
    boolean logNoSubscriberMessages = true;
    boolean logSubscriberExceptions = true;
    Logger logger;
    MainThreadSupport mainThreadSupport;
    boolean sendNoSubscriberEvent = true;
    boolean sendSubscriberExceptionEvent = true;
    boolean strictMethodVerification;
    List subscriberInfoIndexes;
    boolean throwSubscriberException;

    EventBusBuilder() {
    }

    /* access modifiers changed from: package-private */
    public Logger getLogger() {
        Logger logger2 = this.logger;
        if (logger2 != null) {
            return logger2;
        }
        return Logger.Default.get();
    }

    /* access modifiers changed from: package-private */
    public MainThreadSupport getMainThreadSupport() {
        MainThreadSupport mainThreadSupport2 = this.mainThreadSupport;
        if (mainThreadSupport2 != null) {
            return mainThreadSupport2;
        }
        if (AndroidComponents.areAvailable()) {
            return AndroidComponents.get().defaultMainThreadSupport;
        }
        return null;
    }

    public EventBus build() {
        return new EventBus(this);
    }
}
