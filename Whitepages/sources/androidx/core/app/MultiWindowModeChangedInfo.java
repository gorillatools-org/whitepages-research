package androidx.core.app;

import android.content.res.Configuration;
import kotlin.jvm.internal.Intrinsics;

public final class MultiWindowModeChangedInfo {
    private final boolean isInMultiWindowMode;
    private Configuration newConfiguration;

    public MultiWindowModeChangedInfo(boolean z) {
        this.isInMultiWindowMode = z;
    }

    public final boolean isInMultiWindowMode() {
        return this.isInMultiWindowMode;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MultiWindowModeChangedInfo(boolean z, Configuration configuration) {
        this(z);
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        this.newConfiguration = configuration;
    }
}
