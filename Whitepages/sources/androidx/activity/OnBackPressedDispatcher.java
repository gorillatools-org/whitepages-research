package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ListIterator;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class OnBackPressedDispatcher {
    private boolean backInvokedCallbackRegistered;
    private Function0 enabledChangedCallback;
    private final Runnable fallbackOnBackPressed;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackInvokedCallback onBackInvokedCallback;
    /* access modifiers changed from: private */
    public final ArrayDeque onBackPressedCallbacks = new ArrayDeque();

    public OnBackPressedDispatcher(Runnable runnable) {
        this.fallbackOnBackPressed = runnable;
        if (Build.VERSION.SDK_INT >= 33) {
            this.enabledChangedCallback = new Function0(this) {
                final /* synthetic */ OnBackPressedDispatcher this$0;

                {
                    this.this$0 = r1;
                }

                public final void invoke() {
                    this.this$0.updateBackInvokedCallbackState$activity_release();
                }
            };
            this.onBackInvokedCallback = Api33Impl.INSTANCE.createOnBackInvokedCallback(new Function0(this) {
                final /* synthetic */ OnBackPressedDispatcher this$0;

                {
                    this.this$0 = r1;
                }

                public final void invoke() {
                    this.this$0.onBackPressed();
                }
            });
        }
    }

    public final void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher) {
        Intrinsics.checkNotNullParameter(onBackInvokedDispatcher, "invoker");
        this.invokedDispatcher = onBackInvokedDispatcher;
        updateBackInvokedCallbackState$activity_release();
    }

    public final void updateBackInvokedCallbackState$activity_release() {
        boolean hasEnabledCallbacks = hasEnabledCallbacks();
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.invokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback2 = this.onBackInvokedCallback;
        if (onBackInvokedDispatcher != null && onBackInvokedCallback2 != null) {
            if (hasEnabledCallbacks && !this.backInvokedCallbackRegistered) {
                Api33Impl.INSTANCE.registerOnBackInvokedCallback(onBackInvokedDispatcher, 0, onBackInvokedCallback2);
                this.backInvokedCallbackRegistered = true;
            } else if (!hasEnabledCallbacks && this.backInvokedCallbackRegistered) {
                Api33Impl.INSTANCE.unregisterOnBackInvokedCallback(onBackInvokedDispatcher, onBackInvokedCallback2);
                this.backInvokedCallbackRegistered = false;
            }
        }
    }

    public final Cancellable addCancellableCallback$activity_release(OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
        this.onBackPressedCallbacks.add(onBackPressedCallback);
        OnBackPressedCancellable onBackPressedCancellable = new OnBackPressedCancellable(this, onBackPressedCallback);
        onBackPressedCallback.addCancellable(onBackPressedCancellable);
        if (Build.VERSION.SDK_INT >= 33) {
            updateBackInvokedCallbackState$activity_release();
            onBackPressedCallback.setEnabledChangedCallback$activity_release(this.enabledChangedCallback);
        }
        return onBackPressedCancellable;
    }

    public final void addCallback(LifecycleOwner lifecycleOwner, OnBackPressedCallback onBackPressedCallback) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        Intrinsics.checkNotNullParameter(onBackPressedCallback, "onBackPressedCallback");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
            onBackPressedCallback.addCancellable(new LifecycleOnBackPressedCancellable(this, lifecycle, onBackPressedCallback));
            if (Build.VERSION.SDK_INT >= 33) {
                updateBackInvokedCallbackState$activity_release();
                onBackPressedCallback.setEnabledChangedCallback$activity_release(this.enabledChangedCallback);
            }
        }
    }

    public final boolean hasEnabledCallbacks() {
        ArrayDeque<OnBackPressedCallback> arrayDeque = this.onBackPressedCallbacks;
        if (arrayDeque != null && arrayDeque.isEmpty()) {
            return false;
        }
        for (OnBackPressedCallback isEnabled : arrayDeque) {
            if (isEnabled.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public final void onBackPressed() {
        Object obj;
        ArrayDeque arrayDeque = this.onBackPressedCallbacks;
        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            if (((OnBackPressedCallback) obj).isEnabled()) {
                break;
            }
        }
        OnBackPressedCallback onBackPressedCallback = (OnBackPressedCallback) obj;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.handleOnBackPressed();
            return;
        }
        Runnable runnable = this.fallbackOnBackPressed;
        if (runnable != null) {
            runnable.run();
        }
    }

    private final class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedCallback onBackPressedCallback;
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public OnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, OnBackPressedCallback onBackPressedCallback2) {
            Intrinsics.checkNotNullParameter(onBackPressedCallback2, "onBackPressedCallback");
            this.this$0 = onBackPressedDispatcher;
            this.onBackPressedCallback = onBackPressedCallback2;
        }

        public void cancel() {
            this.this$0.onBackPressedCallbacks.remove(this.onBackPressedCallback);
            this.onBackPressedCallback.removeCancellable(this);
            if (Build.VERSION.SDK_INT >= 33) {
                this.onBackPressedCallback.setEnabledChangedCallback$activity_release((Function0) null);
                this.this$0.updateBackInvokedCallbackState$activity_release();
            }
        }
    }

    private final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, Cancellable {
        private Cancellable currentCancellable;
        private final Lifecycle lifecycle;
        private final OnBackPressedCallback onBackPressedCallback;
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher onBackPressedDispatcher, Lifecycle lifecycle2, OnBackPressedCallback onBackPressedCallback2) {
            Intrinsics.checkNotNullParameter(lifecycle2, "lifecycle");
            Intrinsics.checkNotNullParameter(onBackPressedCallback2, "onBackPressedCallback");
            this.this$0 = onBackPressedDispatcher;
            this.lifecycle = lifecycle2;
            this.onBackPressedCallback = onBackPressedCallback2;
            lifecycle2.addObserver(this);
        }

        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
            Intrinsics.checkNotNullParameter(event, "event");
            if (event == Lifecycle.Event.ON_START) {
                this.currentCancellable = this.this$0.addCancellableCallback$activity_release(this.onBackPressedCallback);
            } else if (event == Lifecycle.Event.ON_STOP) {
                Cancellable cancellable = this.currentCancellable;
                if (cancellable != null) {
                    cancellable.cancel();
                }
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                cancel();
            }
        }

        public void cancel() {
            this.lifecycle.removeObserver(this);
            this.onBackPressedCallback.removeCancellable(this);
            Cancellable cancellable = this.currentCancellable;
            if (cancellable != null) {
                cancellable.cancel();
            }
            this.currentCancellable = null;
        }
    }

    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        public final void registerOnBackInvokedCallback(Object obj, int i, Object obj2) {
            Intrinsics.checkNotNullParameter(obj, "dispatcher");
            Intrinsics.checkNotNullParameter(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
        }

        public final void unregisterOnBackInvokedCallback(Object obj, Object obj2) {
            Intrinsics.checkNotNullParameter(obj, "dispatcher");
            Intrinsics.checkNotNullParameter(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }

        /* access modifiers changed from: private */
        public static final void createOnBackInvokedCallback$lambda$0(Function0 function0) {
            Intrinsics.checkNotNullParameter(function0, "$onBackInvoked");
            function0.invoke();
        }

        public final OnBackInvokedCallback createOnBackInvokedCallback(Function0 function0) {
            Intrinsics.checkNotNullParameter(function0, "onBackInvoked");
            return new OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0(function0);
        }
    }
}
