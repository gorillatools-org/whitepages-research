package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

public abstract class TargetFragmentUsageViolation extends Violation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TargetFragmentUsageViolation(Fragment fragment, String str) {
        super(fragment, str);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }
}
