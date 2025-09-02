package androidx.savedstate;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public abstract class ViewTreeSavedStateRegistryOwner {
    public static final void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R$id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
