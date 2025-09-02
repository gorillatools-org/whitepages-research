package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import androidx.room.IMultiInstanceInvalidationService;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationService extends Service {
    private final IMultiInstanceInvalidationService.Stub binder = new MultiInstanceInvalidationService$binder$1(this);
    private final RemoteCallbackList callbackList = new MultiInstanceInvalidationService$callbackList$1(this);
    private final Map clientNames = new LinkedHashMap();
    private int maxClientId;

    public final int getMaxClientId$room_runtime_release() {
        return this.maxClientId;
    }

    public final void setMaxClientId$room_runtime_release(int i) {
        this.maxClientId = i;
    }

    public final Map getClientNames$room_runtime_release() {
        return this.clientNames;
    }

    public final RemoteCallbackList getCallbackList$room_runtime_release() {
        return this.callbackList;
    }

    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return this.binder;
    }
}
