package app.notifee.core.model;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final /* synthetic */ class NotificationAndroidStyleModel$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ NotificationAndroidStyleModel f$0;
    public final /* synthetic */ Executor f$1;

    public /* synthetic */ NotificationAndroidStyleModel$$ExternalSyntheticLambda2(NotificationAndroidStyleModel notificationAndroidStyleModel, Executor executor) {
        this.f$0 = notificationAndroidStyleModel;
        this.f$1 = executor;
    }

    public final Object call() {
        return this.f$0.lambda$getMessagingStyleTask$2(this.f$1);
    }
}
