package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.style.CornerRadii;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class LengthPercentage {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final LengthPercentageType type;
    private final float value;

    private final float component1() {
        return this.value;
    }

    public static /* synthetic */ LengthPercentage copy$default(LengthPercentage lengthPercentage, float f, LengthPercentageType lengthPercentageType, int i, Object obj) {
        if ((i & 1) != 0) {
            f = lengthPercentage.value;
        }
        if ((i & 2) != 0) {
            lengthPercentageType = lengthPercentage.type;
        }
        return lengthPercentage.copy(f, lengthPercentageType);
    }

    public static final LengthPercentage setFromDynamic(Dynamic dynamic) {
        return Companion.setFromDynamic(dynamic);
    }

    public final LengthPercentageType component2() {
        return this.type;
    }

    public final LengthPercentage copy(float f, LengthPercentageType lengthPercentageType) {
        Intrinsics.checkNotNullParameter(lengthPercentageType, "type");
        return new LengthPercentage(f, lengthPercentageType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LengthPercentage)) {
            return false;
        }
        LengthPercentage lengthPercentage = (LengthPercentage) obj;
        return Float.compare(this.value, lengthPercentage.value) == 0 && this.type == lengthPercentage.type;
    }

    public int hashCode() {
        return (Float.hashCode(this.value) * 31) + this.type.hashCode();
    }

    public String toString() {
        float f = this.value;
        LengthPercentageType lengthPercentageType = this.type;
        return "LengthPercentage(value=" + f + ", type=" + lengthPercentageType + ")";
    }

    public LengthPercentage(float f, LengthPercentageType lengthPercentageType) {
        Intrinsics.checkNotNullParameter(lengthPercentageType, "type");
        this.value = f;
        this.type = lengthPercentageType;
    }

    public final LengthPercentageType getType() {
        return this.type;
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
                    com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.LengthPercentage.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LengthPercentage setFromDynamic(Dynamic dynamic) {
            Intrinsics.checkNotNullParameter(dynamic, "dynamic");
            int i = WhenMappings.$EnumSwitchMapping$0[dynamic.getType().ordinal()];
            if (i == 1) {
                double asDouble = dynamic.asDouble();
                if (asDouble >= 0.0d) {
                    return new LengthPercentage((float) asDouble, LengthPercentageType.POINT);
                }
                return null;
            } else if (i != 2) {
                ReadableType type = dynamic.getType();
                FLog.w(ReactConstants.TAG, "Unsupported type for radius property: " + type);
                return null;
            } else {
                String asString = dynamic.asString();
                if (StringsKt.endsWith$default(asString, "%", false, 2, (Object) null)) {
                    try {
                        String substring = asString.substring(0, asString.length() - 1);
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        float parseFloat = Float.parseFloat(substring);
                        if (parseFloat >= 0.0f) {
                            return new LengthPercentage(parseFloat, LengthPercentageType.PERCENT);
                        }
                        return null;
                    } catch (NumberFormatException unused) {
                        FLog.w(ReactConstants.TAG, "Invalid percentage format: " + asString);
                        return null;
                    }
                } else {
                    FLog.w(ReactConstants.TAG, "Invalid string value: " + asString);
                    return null;
                }
            }
        }
    }

    public final CornerRadii resolve(float f, float f2) {
        if (this.type == LengthPercentageType.PERCENT) {
            float f3 = this.value;
            float f4 = (float) 100;
            return new CornerRadii((f3 / f4) * f, (f3 / f4) * f2);
        }
        float f5 = this.value;
        return new CornerRadii(f5, f5);
    }

    public LengthPercentage() {
        this(0.0f, LengthPercentageType.POINT);
    }
}
