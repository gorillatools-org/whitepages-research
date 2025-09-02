package androidx.room;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.IMultiInstanceInvalidationService;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationService$binder$1 extends IMultiInstanceInvalidationService.Stub {
    final /* synthetic */ MultiInstanceInvalidationService this$0;

    MultiInstanceInvalidationService$binder$1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.this$0 = multiInstanceInvalidationService;
    }

    public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, String str) {
        Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback, "callback");
        int i = 0;
        if (str == null) {
            return 0;
        }
        RemoteCallbackList callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            try {
                multiInstanceInvalidationService.setMaxClientId$room_runtime_release(multiInstanceInvalidationService.getMaxClientId$room_runtime_release() + 1);
                int maxClientId$room_runtime_release = multiInstanceInvalidationService.getMaxClientId$room_runtime_release();
                if (multiInstanceInvalidationService.getCallbackList$room_runtime_release().register(iMultiInstanceInvalidationCallback, Integer.valueOf(maxClientId$room_runtime_release))) {
                    multiInstanceInvalidationService.getClientNames$room_runtime_release().put(Integer.valueOf(maxClientId$room_runtime_release), str);
                    i = maxClientId$room_runtime_release;
                } else {
                    multiInstanceInvalidationService.setMaxClientId$room_runtime_release(multiInstanceInvalidationService.getMaxClientId$room_runtime_release() - 1);
                    multiInstanceInvalidationService.getMaxClientId$room_runtime_release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback, int i) {
        Intrinsics.checkNotNullParameter(iMultiInstanceInvalidationCallback, "callback");
        RemoteCallbackList callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            multiInstanceInvalidationService.getCallbackList$room_runtime_release().unregister(iMultiInstanceInvalidationCallback);
            String str = (String) multiInstanceInvalidationService.getClientNames$room_runtime_release().remove(Integer.valueOf(i));
        }
    }

    public void broadcastInvalidation(int i, String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "tables");
        RemoteCallbackList callbackList$room_runtime_release = this.this$0.getCallbackList$room_runtime_release();
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.this$0;
        synchronized (callbackList$room_runtime_release) {
            String str = (String) multiInstanceInvalidationService.getClientNames$room_runtime_release().get(Integer.valueOf(i));
            if (str == null) {
                Log.w("ROOM", "Remote invalidation client ID not registered");
                return;
            }
            int beginBroadcast = multiInstanceInvalidationService.getCallbackList$room_runtime_release().beginBroadcast();
            int i2 = 0;
            while (i2 < beginBroadcast) {
                try {
                    Object broadcastCookie = multiInstanceInvalidationService.getCallbackList$room_runtime_release().getBroadcastCookie(i2);
                    Intrinsics.checkNotNull(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                    Integer num = (Integer) broadcastCookie;
                    int intValue = num.intValue();
                    String str2 = (String) multiInstanceInvalidationService.getClientNames$room_runtime_release().get(num);
                    if (i != intValue && Intrinsics.areEqual((Object) str, (Object) str2)) {
                        try {
                            ((IMultiInstanceInvalidationCallback) multiInstanceInvalidationService.getCallbackList$room_runtime_release().getBroadcastItem(i2)).onInvalidation(strArr);
                        } catch (RemoteException e) {
                            Log.w("ROOM", "Error invoking a remote callback", e);
                        }
                    }
                    i2++;
                } catch (Throwable th) {
                    multiInstanceInvalidationService.getCallbackList$room_runtime_release().finishBroadcast();
                    throw th;
                }
            }
            multiInstanceInvalidationService.getCallbackList$room_runtime_release().finishBroadcast();
            Unit unit = Unit.INSTANCE;
        }
    }
}
