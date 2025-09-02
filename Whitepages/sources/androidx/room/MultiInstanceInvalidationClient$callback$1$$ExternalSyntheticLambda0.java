package androidx.room;

public final /* synthetic */ class MultiInstanceInvalidationClient$callback$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MultiInstanceInvalidationClient f$0;
    public final /* synthetic */ String[] f$1;

    public /* synthetic */ MultiInstanceInvalidationClient$callback$1$$ExternalSyntheticLambda0(MultiInstanceInvalidationClient multiInstanceInvalidationClient, String[] strArr) {
        this.f$0 = multiInstanceInvalidationClient;
        this.f$1 = strArr;
    }

    public final void run() {
        MultiInstanceInvalidationClient$callback$1.onInvalidation$lambda$0(this.f$0, this.f$1);
    }
}
