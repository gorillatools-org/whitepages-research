package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

public abstract class Violation extends RuntimeException {
    private final Fragment fragment;

    public final Fragment getFragment() {
        return this.fragment;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Violation(Fragment fragment2, String str) {
        super(str);
        Intrinsics.checkNotNullParameter(fragment2, "fragment");
        this.fragment = fragment2;
    }
}
