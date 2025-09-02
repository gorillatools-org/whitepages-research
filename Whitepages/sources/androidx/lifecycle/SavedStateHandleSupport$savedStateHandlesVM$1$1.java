package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class SavedStateHandleSupport$savedStateHandlesVM$1$1 extends Lambda implements Function1 {
    public static final SavedStateHandleSupport$savedStateHandlesVM$1$1 INSTANCE = new SavedStateHandleSupport$savedStateHandlesVM$1$1();

    SavedStateHandleSupport$savedStateHandlesVM$1$1() {
        super(1);
    }

    public final SavedStateHandlesVM invoke(CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(creationExtras, "$this$initializer");
        return new SavedStateHandlesVM();
    }
}
