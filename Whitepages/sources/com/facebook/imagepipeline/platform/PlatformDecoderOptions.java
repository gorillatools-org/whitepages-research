package com.facebook.imagepipeline.platform;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class PlatformDecoderOptions {
    private final boolean avoidPoolGet;
    private final boolean avoidPoolRelease;

    public PlatformDecoderOptions(boolean z, boolean z2) {
        this.avoidPoolGet = z;
        this.avoidPoolRelease = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PlatformDecoderOptions(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getAvoidPoolGet() {
        return this.avoidPoolGet;
    }

    public final boolean getAvoidPoolRelease() {
        return this.avoidPoolRelease;
    }
}
