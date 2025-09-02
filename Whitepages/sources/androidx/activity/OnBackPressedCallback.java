package androidx.activity;

import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public abstract class OnBackPressedCallback {
    private final CopyOnWriteArrayList cancellables = new CopyOnWriteArrayList();
    private Function0 enabledChangedCallback;
    private boolean isEnabled;

    public abstract void handleOnBackPressed();

    public OnBackPressedCallback(boolean z) {
        this.isEnabled = z;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
        Function0 function0 = this.enabledChangedCallback;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void setEnabledChangedCallback$activity_release(Function0 function0) {
        this.enabledChangedCallback = function0;
    }

    public final void remove() {
        for (Cancellable cancel : this.cancellables) {
            cancel.cancel();
        }
    }

    public final void addCancellable(Cancellable cancellable) {
        Intrinsics.checkNotNullParameter(cancellable, "cancellable");
        this.cancellables.add(cancellable);
    }

    public final void removeCancellable(Cancellable cancellable) {
        Intrinsics.checkNotNullParameter(cancellable, "cancellable");
        this.cancellables.remove(cancellable);
    }
}
