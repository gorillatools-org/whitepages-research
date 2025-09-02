package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

public abstract class ActivityResultContract {
    public abstract Intent createIntent(Context context, Object obj);

    public SynchronousResult getSynchronousResult(Context context, Object obj) {
        Intrinsics.checkNotNullParameter(context, "context");
        return null;
    }

    public abstract Object parseResult(int i, Intent intent);

    public static final class SynchronousResult {
        private final Object value;

        public SynchronousResult(Object obj) {
            this.value = obj;
        }

        public final Object getValue() {
            return this.value;
        }
    }
}
