package androidx.lifecycle;

import androidx.lifecycle.LegacySavedStateHandleController;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import kotlin.jvm.internal.Intrinsics;

public final class LegacySavedStateHandleController$tryToAddRecreator$1 implements LifecycleEventObserver {
    final /* synthetic */ Lifecycle $lifecycle;
    final /* synthetic */ SavedStateRegistry $registry;

    LegacySavedStateHandleController$tryToAddRecreator$1(Lifecycle lifecycle, SavedStateRegistry savedStateRegistry) {
        this.$lifecycle = lifecycle;
        this.$registry = savedStateRegistry;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_START) {
            this.$lifecycle.removeObserver(this);
            this.$registry.runOnNextRecreation(LegacySavedStateHandleController.OnRecreation.class);
        }
    }
}
