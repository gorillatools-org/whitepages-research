package io.branch.coroutines;

import android.content.Context;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public abstract class DeviceSignalsKt {
    private static final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);

    public static final Mutex getMutex() {
        return mutex;
    }

    public static final Object getUserAgentAsync(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new DeviceSignalsKt$getUserAgentAsync$2(context, (Continuation) null), continuation);
    }

    public static final Object getUserAgentSync(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new DeviceSignalsKt$getUserAgentSync$2(context, (Continuation) null), continuation);
    }
}
