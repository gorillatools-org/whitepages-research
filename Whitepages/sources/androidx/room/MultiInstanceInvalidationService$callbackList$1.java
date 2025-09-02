package androidx.room;

import android.os.RemoteCallbackList;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationService$callbackList$1 extends RemoteCallbackList {
    final /* synthetic */ MultiInstanceInvalidationService this$0;

    MultiInstanceInvalidationService$callbackList$1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.this$0 = multiInstanceInvalidationService;
    }

    public void onCallbackDied(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, Object obj) {
        Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback, "callback");
        Intrinsics.checkNotNullParameter(obj, "cookie");
        this.this$0.getClientNames$room_runtime_release().remove((Integer) obj);
    }
}
