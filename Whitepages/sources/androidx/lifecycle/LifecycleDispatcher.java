package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

public final class LifecycleDispatcher {
    public static final LifecycleDispatcher INSTANCE = new LifecycleDispatcher();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    private LifecycleDispatcher() {
    }

    public static final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!initialized.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
        }
    }

    public static final class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            ReportFragment.Companion.injectIfNeededIn(activity);
        }
    }
}
