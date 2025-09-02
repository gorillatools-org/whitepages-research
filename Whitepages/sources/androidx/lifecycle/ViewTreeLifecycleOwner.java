package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R$id;
import kotlin.jvm.internal.Intrinsics;

public abstract class ViewTreeLifecycleOwner {
    public static final void set(View view, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setTag(R$id.view_tree_lifecycle_owner, lifecycleOwner);
    }
}
