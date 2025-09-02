package com.facebook.react.uimanager.style;

import android.content.Context;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BoxShadow {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Float blurRadius;
    private final Integer color;
    private final Boolean inset;
    private final float offsetX;
    private final float offsetY;
    private final Float spreadDistance;

    public static /* synthetic */ BoxShadow copy$default(BoxShadow boxShadow, float f, float f2, Integer num, Float f3, Float f4, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            f = boxShadow.offsetX;
        }
        if ((i & 2) != 0) {
            f2 = boxShadow.offsetY;
        }
        float f5 = f2;
        if ((i & 4) != 0) {
            num = boxShadow.color;
        }
        Integer num2 = num;
        if ((i & 8) != 0) {
            f3 = boxShadow.blurRadius;
        }
        Float f6 = f3;
        if ((i & 16) != 0) {
            f4 = boxShadow.spreadDistance;
        }
        Float f7 = f4;
        if ((i & 32) != 0) {
            bool = boxShadow.inset;
        }
        return boxShadow.copy(f, f5, num2, f6, f7, bool);
    }

    public static final BoxShadow parse(ReadableMap readableMap, Context context) {
        return Companion.parse(readableMap, context);
    }

    public final float component1() {
        return this.offsetX;
    }

    public final float component2() {
        return this.offsetY;
    }

    public final Integer component3() {
        return this.color;
    }

    public final Float component4() {
        return this.blurRadius;
    }

    public final Float component5() {
        return this.spreadDistance;
    }

    public final Boolean component6() {
        return this.inset;
    }

    public final BoxShadow copy(float f, float f2, Integer num, Float f3, Float f4, Boolean bool) {
        return new BoxShadow(f, f2, num, f3, f4, bool);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BoxShadow)) {
            return false;
        }
        BoxShadow boxShadow = (BoxShadow) obj;
        return Float.compare(this.offsetX, boxShadow.offsetX) == 0 && Float.compare(this.offsetY, boxShadow.offsetY) == 0 && Intrinsics.areEqual((Object) this.color, (Object) boxShadow.color) && Intrinsics.areEqual((Object) this.blurRadius, (Object) boxShadow.blurRadius) && Intrinsics.areEqual((Object) this.spreadDistance, (Object) boxShadow.spreadDistance) && Intrinsics.areEqual((Object) this.inset, (Object) boxShadow.inset);
    }

    public int hashCode() {
        int hashCode = ((Float.hashCode(this.offsetX) * 31) + Float.hashCode(this.offsetY)) * 31;
        Integer num = this.color;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Float f = this.blurRadius;
        int hashCode3 = (hashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Float f2 = this.spreadDistance;
        int hashCode4 = (hashCode3 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Boolean bool = this.inset;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        float f = this.offsetX;
        float f2 = this.offsetY;
        Integer num = this.color;
        Float f3 = this.blurRadius;
        Float f4 = this.spreadDistance;
        Boolean bool = this.inset;
        return "BoxShadow(offsetX=" + f + ", offsetY=" + f2 + ", color=" + num + ", blurRadius=" + f3 + ", spreadDistance=" + f4 + ", inset=" + bool + ")";
    }

    public BoxShadow(float f, float f2, Integer num, Float f3, Float f4, Boolean bool) {
        this.offsetX = f;
        this.offsetY = f2;
        this.color = num;
        this.blurRadius = f3;
        this.spreadDistance = f4;
        this.inset = bool;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BoxShadow(float f, float f2, Integer num, Float f3, Float f4, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : f3, (i & 16) != 0 ? null : f4, (i & 32) != 0 ? null : bool);
    }

    public final float getOffsetX() {
        return this.offsetX;
    }

    public final float getOffsetY() {
        return this.offsetY;
    }

    public final Integer getColor() {
        return this.color;
    }

    public final Float getBlurRadius() {
        return this.blurRadius;
    }

    public final Float getSpreadDistance() {
        return this.spreadDistance;
    }

    public final Boolean getInset() {
        return this.inset;
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.BoxShadow.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BoxShadow parse(ReadableMap readableMap, Context context) {
            Integer num;
            Integer valueOf;
            Intrinsics.checkNotNullParameter(context, "context");
            Boolean bool = null;
            if (readableMap == null || !readableMap.hasKey("offsetX") || !readableMap.hasKey("offsetY")) {
                return null;
            }
            float f = (float) readableMap.getDouble("offsetX");
            float f2 = (float) readableMap.getDouble("offsetY");
            if (readableMap.hasKey(ViewProps.COLOR)) {
                ReadableType type = readableMap.getType(ViewProps.COLOR);
                int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                if (i == 1) {
                    valueOf = Integer.valueOf(readableMap.getInt(ViewProps.COLOR));
                } else if (i == 2) {
                    valueOf = ColorPropConverter.getColor(readableMap.getMap(ViewProps.COLOR), context);
                } else {
                    throw new JSApplicationCausedNativeException("Unsupported color type " + type);
                }
                num = valueOf;
            } else {
                num = null;
            }
            Float valueOf2 = readableMap.hasKey("blurRadius") ? Float.valueOf((float) readableMap.getDouble("blurRadius")) : null;
            Float valueOf3 = readableMap.hasKey("spreadDistance") ? Float.valueOf((float) readableMap.getDouble("spreadDistance")) : null;
            if (readableMap.hasKey("inset")) {
                bool = Boolean.valueOf(readableMap.getBoolean("inset"));
            }
            return new BoxShadow(f, f2, num, valueOf2, valueOf3, bool);
        }
    }
}
