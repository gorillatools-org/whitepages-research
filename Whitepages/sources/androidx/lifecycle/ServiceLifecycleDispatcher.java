package androidx.lifecycle;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.Intrinsics;

public class ServiceLifecycleDispatcher {
    private final Handler handler = new Handler();
    private DispatchRunnable lastDispatchRunnable;
    private final LifecycleRegistry registry;

    public ServiceLifecycleDispatcher(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "provider");
        this.registry = new LifecycleRegistry(lifecycleOwner);
    }

    private final void postDispatchRunnable(Lifecycle.Event event) {
        DispatchRunnable dispatchRunnable = this.lastDispatchRunnable;
        if (dispatchRunnable != null) {
            dispatchRunnable.run();
        }
        DispatchRunnable dispatchRunnable2 = new DispatchRunnable(this.registry, event);
        this.lastDispatchRunnable = dispatchRunnable2;
        Handler handler2 = this.handler;
        Intrinsics.checkNotNull(dispatchRunnable2);
        handler2.postAtFrontOfQueue(dispatchRunnable2);
    }

    public void onServicePreSuperOnCreate() {
        postDispatchRunnable(Lifecycle.Event.ON_CREATE);
    }

    public void onServicePreSuperOnBind() {
        postDispatchRunnable(Lifecycle.Event.ON_START);
    }

    public void onServicePreSuperOnStart() {
        postDispatchRunnable(Lifecycle.Event.ON_START);
    }

    public void onServicePreSuperOnDestroy() {
        postDispatchRunnable(Lifecycle.Event.ON_STOP);
        postDispatchRunnable(Lifecycle.Event.ON_DESTROY);
    }

    public Lifecycle getLifecycle() {
        return this.registry;
    }

    public static final class DispatchRunnable implements Runnable {
        private final Lifecycle.Event event;
        private final LifecycleRegistry registry;
        private boolean wasExecuted;

        public DispatchRunnable(LifecycleRegistry lifecycleRegistry, Lifecycle.Event event2) {
            Intrinsics.checkNotNullParameter(lifecycleRegistry, "registry");
            Intrinsics.checkNotNullParameter(event2, "event");
            this.registry = lifecycleRegistry;
            this.event = event2;
        }

        public void run() {
            if (!this.wasExecuted) {
                this.registry.handleLifecycleEvent(this.event);
                this.wasExecuted = true;
            }
        }
    }
}
