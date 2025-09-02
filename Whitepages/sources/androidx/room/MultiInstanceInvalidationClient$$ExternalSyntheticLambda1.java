package androidx.room;

public final /* synthetic */ class MultiInstanceInvalidationClient$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MultiInstanceInvalidationClient f$0;

    public /* synthetic */ MultiInstanceInvalidationClient$$ExternalSyntheticLambda1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.f$0 = multiInstanceInvalidationClient;
    }

    public final void run() {
        MultiInstanceInvalidationClient.removeObserverRunnable$lambda$2(this.f$0);
    }
}
