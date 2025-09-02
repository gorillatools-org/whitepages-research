package androidx.room;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.room.IMultiInstanceInvalidationService;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationClient$serviceConnection$1 implements ServiceConnection {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    MultiInstanceInvalidationClient$serviceConnection$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Intrinsics.checkNotNullParameter(componentName, "name");
        Intrinsics.checkNotNullParameter(iBinder, "service");
        this.this$0.setService(IMultiInstanceInvalidationService.Stub.asInterface(iBinder));
        this.this$0.getExecutor().execute(this.this$0.getSetUpRunnable());
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "name");
        this.this$0.getExecutor().execute(this.this$0.getRemoveObserverRunnable());
        this.this$0.setService((IMultiInstanceInvalidationService) null);
    }
}
