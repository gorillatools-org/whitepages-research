package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import kotlin.jvm.internal.Intrinsics;

public final class SavedStateHandleController implements LifecycleEventObserver {
    private final SavedStateHandle handle;
    private boolean isAttached;
    private final String key;

    public SavedStateHandleController(String str, SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(savedStateHandle, "handle");
        this.key = str;
        this.handle = savedStateHandle;
    }

    public final SavedStateHandle getHandle() {
        return this.handle;
    }

    public final boolean isAttached() {
        return this.isAttached;
    }

    public final void attachToLifecycle(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        if (!this.isAttached) {
            this.isAttached = true;
            lifecycle.addObserver(this);
            savedStateRegistry.registerSavedStateProvider(this.key, this.handle.savedStateProvider());
            return;
        }
        throw new IllegalStateException("Already attached to lifecycleOwner");
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.isAttached = false;
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}
