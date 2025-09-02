package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

public final class FragmentReuseViolation extends Violation {
    private final String previousFragmentId;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FragmentReuseViolation(Fragment fragment, String str) {
        super(fragment, "Attempting to reuse fragment " + fragment + " with previous ID " + str);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(str, "previousFragmentId");
        this.previousFragmentId = str;
    }
}
