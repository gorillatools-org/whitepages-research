package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

public final class SetRetainInstanceUsageViolation extends RetainInstanceUsageViolation {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetRetainInstanceUsageViolation(Fragment fragment) {
        super(fragment, "Attempting to set retain instance for fragment " + fragment);
        Intrinsics.checkNotNullParameter(fragment, "fragment");
    }
}
