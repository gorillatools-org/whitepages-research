package androidx.work.impl.background.systemalarm;

public final /* synthetic */ class DelayMetCommandHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DelayMetCommandHandler f$0;

    public /* synthetic */ DelayMetCommandHandler$$ExternalSyntheticLambda0(DelayMetCommandHandler delayMetCommandHandler) {
        this.f$0 = delayMetCommandHandler;
    }

    public final void run() {
        this.f$0.stopWork();
    }
}
