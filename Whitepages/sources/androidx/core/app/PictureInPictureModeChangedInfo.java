package androidx.core.app;

import android.content.res.Configuration;
import kotlin.jvm.internal.Intrinsics;

public final class PictureInPictureModeChangedInfo {
    private final boolean isInPictureInPictureMode;
    private Configuration newConfiguration;

    public PictureInPictureModeChangedInfo(boolean z) {
        this.isInPictureInPictureMode = z;
    }

    public final boolean isInPictureInPictureMode() {
        return this.isInPictureInPictureMode;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PictureInPictureModeChangedInfo(boolean z, Configuration configuration) {
        this(z);
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        this.newConfiguration = configuration;
    }
}
