package com.facebook.react.uimanager.style;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Shader;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public final class Gradient {
    private final LinearGradient linearGradient;
    private final GradientType type;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GradientType.values().length];
            try {
                iArr[GradientType.LINEAR_GRADIENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Gradient(ReadableMap readableMap, Context context) {
        Integer num;
        Intrinsics.checkNotNullParameter(context, "context");
        if (readableMap != null) {
            String string = readableMap.getString("type");
            if (Intrinsics.areEqual((Object) string, (Object) "linearGradient")) {
                this.type = GradientType.LINEAR_GRADIENT;
                ReadableMap map = readableMap.getMap("direction");
                if (map != null) {
                    ReadableArray array = readableMap.getArray("colorStops");
                    if (array != null) {
                        int size = array.size();
                        int[] iArr = new int[size];
                        float[] fArr = new float[size];
                        for (int i = 0; i < size; i++) {
                            ReadableMap map2 = array.getMap(i);
                            if (map2 != null) {
                                if (map2.getType(ViewProps.COLOR) == ReadableType.Map) {
                                    num = ColorPropConverter.getColor(map2.getMap(ViewProps.COLOR), context);
                                } else {
                                    num = Integer.valueOf(map2.getInt(ViewProps.COLOR));
                                }
                                Intrinsics.checkNotNull(num);
                                iArr[i] = num.intValue();
                                fArr[i] = (float) map2.getDouble(ViewProps.POSITION);
                            }
                        }
                        this.linearGradient = new LinearGradient(map, iArr, fArr);
                        return;
                    }
                    throw new IllegalArgumentException("Invalid colorStops array");
                }
                throw new IllegalArgumentException("Gradient must have direction");
            }
            throw new IllegalArgumentException("Unsupported gradient type: " + string);
        }
        throw new IllegalArgumentException("Gradient cannot be null");
    }

    private enum GradientType {
        LINEAR_GRADIENT;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        static {
            GradientType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final Shader getShader(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        if (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()] == 1) {
            return this.linearGradient.getShader((float) rect.width(), (float) rect.height());
        }
        throw new NoWhenBranchMatchedException();
    }
}
