package kotlinx.coroutines;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.coroutines.CoroutineContext;

public final class Unconfined extends CoroutineDispatcher {
    public static final Unconfined INSTANCE = new Unconfined();

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return false;
    }

    private Unconfined() {
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(coroutineContext.get(YieldContext.Key));
        throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
    }

    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
