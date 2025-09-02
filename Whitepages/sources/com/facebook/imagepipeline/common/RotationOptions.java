package com.facebook.imagepipeline.common;

import com.facebook.common.util.HashCodeUtil;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class RotationOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RotationOptions ROTATION_OPTIONS_AUTO_ROTATE = new RotationOptions(-1, false);
    /* access modifiers changed from: private */
    public static final RotationOptions ROTATION_OPTIONS_DISABLE_ROTATION = new RotationOptions(-2, false);
    private static final RotationOptions ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME = new RotationOptions(-1, true);
    private final boolean deferUntilRendered;
    private final int rotation;

    public static final RotationOptions autoRotate() {
        return Companion.autoRotate();
    }

    public static final RotationOptions disableRotation() {
        return Companion.disableRotation();
    }

    private RotationOptions(int i, boolean z) {
        this.rotation = i;
        this.deferUntilRendered = z;
    }

    public final boolean useImageMetadata() {
        return this.rotation == -1;
    }

    public final boolean rotationEnabled() {
        return this.rotation != -2;
    }

    public final int getForcedAngle() {
        if (!useImageMetadata()) {
            return this.rotation;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public final boolean canDeferUntilRendered() {
        return this.deferUntilRendered;
    }

    public int hashCode() {
        return HashCodeUtil.hashCode((Object) Integer.valueOf(this.rotation), (Object) Boolean.valueOf(this.deferUntilRendered));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RotationOptions)) {
            return false;
        }
        RotationOptions rotationOptions = (RotationOptions) obj;
        if (this.rotation == rotationOptions.rotation && this.deferUntilRendered == rotationOptions.deferUntilRendered) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format((Locale) null, "%d defer:%b", Arrays.copyOf(new Object[]{Integer.valueOf(this.rotation), Boolean.valueOf(this.deferUntilRendered)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RotationOptions autoRotate() {
            return RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE;
        }

        public final RotationOptions disableRotation() {
            return RotationOptions.ROTATION_OPTIONS_DISABLE_ROTATION;
        }
    }
}
