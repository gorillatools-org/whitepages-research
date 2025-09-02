package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.PixelUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CornerRadii {
    private final float horizontal;
    private final float vertical;

    public CornerRadii() {
        this(0.0f, 0.0f, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ CornerRadii copy$default(CornerRadii cornerRadii, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = cornerRadii.horizontal;
        }
        if ((i & 2) != 0) {
            f2 = cornerRadii.vertical;
        }
        return cornerRadii.copy(f, f2);
    }

    public final float component1() {
        return this.horizontal;
    }

    public final float component2() {
        return this.vertical;
    }

    public final CornerRadii copy(float f, float f2) {
        return new CornerRadii(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CornerRadii)) {
            return false;
        }
        CornerRadii cornerRadii = (CornerRadii) obj;
        return Float.compare(this.horizontal, cornerRadii.horizontal) == 0 && Float.compare(this.vertical, cornerRadii.vertical) == 0;
    }

    public int hashCode() {
        return (Float.hashCode(this.horizontal) * 31) + Float.hashCode(this.vertical);
    }

    public String toString() {
        float f = this.horizontal;
        float f2 = this.vertical;
        return "CornerRadii(horizontal=" + f + ", vertical=" + f2 + ")";
    }

    public CornerRadii(float f, float f2) {
        this.horizontal = f;
        this.vertical = f2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CornerRadii(float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0.0f : f2);
    }

    public final float getHorizontal() {
        return this.horizontal;
    }

    public final float getVertical() {
        return this.vertical;
    }

    public final CornerRadii toPixelFromDIP() {
        return new CornerRadii(PixelUtil.toPixelFromDIP(this.horizontal), PixelUtil.toPixelFromDIP(this.vertical));
    }
}
