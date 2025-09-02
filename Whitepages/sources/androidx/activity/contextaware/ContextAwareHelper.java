package androidx.activity.contextaware;

import android.content.Context;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

public final class ContextAwareHelper {
    private volatile Context context;
    private final Set listeners = new CopyOnWriteArraySet();

    public final Context peekAvailableContext() {
        return this.context;
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Context context2 = this.context;
        if (context2 != null) {
            onContextAvailableListener.onContextAvailable(context2);
        }
        this.listeners.add(onContextAvailableListener);
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.remove(onContextAvailableListener);
    }

    public final void dispatchOnContextAvailable(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        for (OnContextAvailableListener onContextAvailable : this.listeners) {
            onContextAvailable.onContextAvailable(context2);
        }
    }

    public final void clearAvailableContext() {
        this.context = null;
    }
}
