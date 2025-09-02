package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private final IMultiInstanceInvalidationCallback callback = new MultiInstanceInvalidationClient$callback$1(this);
    private int clientId;
    private final Executor executor;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    public InvalidationTracker.Observer observer;
    private final Runnable removeObserverRunnable;
    private IMultiInstanceInvalidationService service;
    private final ServiceConnection serviceConnection;
    private final Runnable setUpRunnable;
    private final AtomicBoolean stopped = new AtomicBoolean(false);

    public MultiInstanceInvalidationClient(Context context, String str, Intent intent, InvalidationTracker invalidationTracker2, Executor executor2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(intent, "serviceIntent");
        Intrinsics.checkNotNullParameter(invalidationTracker2, "invalidationTracker");
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.name = str;
        this.invalidationTracker = invalidationTracker2;
        this.executor = executor2;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        MultiInstanceInvalidationClient$serviceConnection$1 multiInstanceInvalidationClient$serviceConnection$1 = new MultiInstanceInvalidationClient$serviceConnection$1(this);
        this.serviceConnection = multiInstanceInvalidationClient$serviceConnection$1;
        this.setUpRunnable = new MultiInstanceInvalidationClient$$ExternalSyntheticLambda0(this);
        this.removeObserverRunnable = new MultiInstanceInvalidationClient$$ExternalSyntheticLambda1(this);
        Object[] array = invalidationTracker2.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        setObserver(new InvalidationTracker.Observer(this, (String[]) array) {
            final /* synthetic */ MultiInstanceInvalidationClient this$0;

            public boolean isRemote$room_runtime_release() {
                return true;
            }

            {
                this.this$0 = r1;
            }

            public void onInvalidated(Set set) {
                Intrinsics.checkNotNullParameter(set, "tables");
                if (!this.this$0.getStopped().get()) {
                    try {
                        IMultiInstanceInvalidationService service = this.this$0.getService();
                        if (service != null) {
                            int clientId = this.this$0.getClientId();
                            Object[] array = set.toArray(new String[0]);
                            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                            service.broadcastInvalidation(clientId, (String[]) array);
                        }
                    } catch (RemoteException e) {
                        Log.w("ROOM", "Cannot broadcast invalidation", e);
                    }
                }
            }
        });
        applicationContext.bindService(intent, multiInstanceInvalidationClient$serviceConnection$1, 1);
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final int getClientId() {
        return this.clientId;
    }

    public final InvalidationTracker.Observer getObserver() {
        InvalidationTracker.Observer observer2 = this.observer;
        if (observer2 != null) {
            return observer2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("observer");
        return null;
    }

    public final void setObserver(InvalidationTracker.Observer observer2) {
        Intrinsics.checkNotNullParameter(observer2, "<set-?>");
        this.observer = observer2;
    }

    public final IMultiInstanceInvalidationService getService() {
        return this.service;
    }

    public final void setService(IMultiInstanceInvalidationService iMultiInstanceInvalidationService) {
        this.service = iMultiInstanceInvalidationService;
    }

    public final AtomicBoolean getStopped() {
        return this.stopped;
    }

    public final Runnable getSetUpRunnable() {
        return this.setUpRunnable;
    }

    /* access modifiers changed from: private */
    public static final void setUpRunnable$lambda$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient, "this$0");
        try {
            IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.service;
            if (iMultiInstanceInvalidationService != null) {
                multiInstanceInvalidationClient.clientId = iMultiInstanceInvalidationService.registerCallback(multiInstanceInvalidationClient.callback, multiInstanceInvalidationClient.name);
                multiInstanceInvalidationClient.invalidationTracker.addObserver(multiInstanceInvalidationClient.getObserver());
            }
        } catch (RemoteException e) {
            Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
        }
    }

    /* access modifiers changed from: private */
    public static final void removeObserverRunnable$lambda$2(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient, "this$0");
        multiInstanceInvalidationClient.invalidationTracker.removeObserver(multiInstanceInvalidationClient.getObserver());
    }

    public final Runnable getRemoveObserverRunnable() {
        return this.removeObserverRunnable;
    }
}
