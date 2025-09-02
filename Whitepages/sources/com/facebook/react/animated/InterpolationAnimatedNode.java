package com.facebook.react.animated;

import androidx.core.graphics.ColorUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InterpolationAnimatedNode extends ValueAnimatedNode {
    private static final String COLOR_OUTPUT_TYPE = "color";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    /* access modifiers changed from: private */
    public static final Pattern numericPattern;
    private final String extrapolateLeft;
    private final String extrapolateRight;
    private final double[] inputRange;
    private Object objectValue;
    private Object outputRange;
    private OutputType outputType;
    private ValueAnimatedNode parent;
    private String pattern;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType[] r0 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.Number     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.Color     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.String     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InterpolationAnimatedNode(ReadableMap readableMap) {
        super((ReadableMap) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(readableMap, "config");
        ReadableType readableType = null;
        Companion companion = Companion;
        this.inputRange = companion.fromDoubleArray(readableMap.getArray("inputRange"));
        this.extrapolateLeft = readableMap.getString("extrapolateLeft");
        this.extrapolateRight = readableMap.getString("extrapolateRight");
        ReadableArray array = readableMap.getArray("outputRange");
        if (Intrinsics.areEqual((Object) "color", (Object) readableMap.getString("outputType"))) {
            this.outputType = OutputType.Color;
            this.outputRange = companion.fromIntArray(array);
            return;
        }
        if ((array != null ? array.getType(0) : readableType) == ReadableType.String) {
            this.outputType = OutputType.String;
            this.outputRange = companion.fromStringPattern(array);
            this.pattern = array.getString(0);
            return;
        }
        this.outputType = OutputType.Number;
        this.outputRange = companion.fromDoubleArray(array);
    }

    private enum OutputType {
        Number,
        Color,
        String;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        static {
            OutputType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public void onAttachedToNode(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "parent");
        if (this.parent != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (animatedNode instanceof ValueAnimatedNode) {
            this.parent = (ValueAnimatedNode) animatedNode;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "parent");
        if (animatedNode == this.parent) {
            this.parent = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    public void update() {
        String str;
        ValueAnimatedNode valueAnimatedNode = this.parent;
        if (valueAnimatedNode != null) {
            double value = valueAnimatedNode.getValue();
            OutputType outputType2 = this.outputType;
            int i = outputType2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[outputType2.ordinal()];
            if (i == 1) {
                Companion companion = Companion;
                double[] dArr = this.inputRange;
                Object obj = this.outputRange;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.DoubleArray");
                this.nodeValue = companion.interpolate(value, dArr, (double[]) obj, this.extrapolateLeft, this.extrapolateRight);
            } else if (i == 2) {
                Companion companion2 = Companion;
                double[] dArr2 = this.inputRange;
                Object obj2 = this.outputRange;
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.IntArray");
                this.objectValue = Integer.valueOf(companion2.interpolateColor(value, dArr2, (int[]) obj2));
            } else if (i == 3 && (str = this.pattern) != null) {
                Companion companion3 = Companion;
                double[] dArr3 = this.inputRange;
                Object obj3 = this.outputRange;
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Array<kotlin.DoubleArray>");
                this.objectValue = companion3.interpolateString(str, value, dArr3, (double[][]) obj3, this.extrapolateLeft, this.extrapolateRight);
            }
        }
    }

    public Object getAnimatedObject() {
        return this.objectValue;
    }

    public String prettyPrint() {
        int i = this.tag;
        return "InterpolationAnimatedNode[" + i + "] super: {super.prettyPrint()}";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final double[] fromDoubleArray(ReadableArray readableArray) {
            if (readableArray == null) {
                return new double[0];
            }
            int size = readableArray.size();
            double[] dArr = new double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = readableArray.getDouble(i);
            }
            return dArr;
        }

        /* access modifiers changed from: private */
        public final int[] fromIntArray(ReadableArray readableArray) {
            if (readableArray == null) {
                return new int[0];
            }
            int size = readableArray.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = readableArray.getInt(i);
            }
            return iArr;
        }

        /* access modifiers changed from: private */
        public final double[][] fromStringPattern(ReadableArray readableArray) {
            int size = readableArray.size();
            double[][] dArr = new double[size][];
            Pattern access$getNumericPattern$cp = InterpolationAnimatedNode.numericPattern;
            String string = readableArray.getString(0);
            if (string == null) {
                string = "";
            }
            Matcher matcher = access$getNumericPattern$cp.matcher(string);
            ArrayList arrayList = new ArrayList();
            while (matcher.find()) {
                String group = matcher.group();
                Intrinsics.checkNotNullExpressionValue(group, "group(...)");
                arrayList.add(Double.valueOf(Double.parseDouble(group)));
            }
            int size2 = arrayList.size();
            double[] dArr2 = new double[size2];
            int size3 = arrayList.size();
            for (int i = 0; i < size3; i++) {
                dArr2[i] = ((Number) arrayList.get(i)).doubleValue();
            }
            dArr[0] = dArr2;
            for (int i2 = 1; i2 < size; i2++) {
                double[] dArr3 = new double[size2];
                Pattern access$getNumericPattern$cp2 = InterpolationAnimatedNode.numericPattern;
                String string2 = readableArray.getString(i2);
                if (string2 == null) {
                    string2 = "";
                }
                Matcher matcher2 = access$getNumericPattern$cp2.matcher(string2);
                int i3 = 0;
                while (matcher2.find() && i3 < size2) {
                    String group2 = matcher2.group();
                    Intrinsics.checkNotNullExpressionValue(group2, "group(...)");
                    dArr3[i3] = Double.parseDouble(group2);
                    i3++;
                }
                dArr[i2] = dArr3;
            }
            return dArr;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
            if (r0.equals(com.facebook.react.animated.InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND) != false) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
            if (r1.equals(com.facebook.react.animated.InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND) != false) goto L_0x0097;
         */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x005a  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x009b A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x009c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final double interpolate(double r13, double r15, double r17, double r19, double r21, java.lang.String r23, java.lang.String r24) {
            /*
                r12 = this;
                r0 = r23
                r1 = r24
                int r2 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                java.lang.String r3 = "Invalid extrapolation type "
                java.lang.String r4 = "extend"
                java.lang.String r5 = "identity"
                java.lang.String r6 = "clamp"
                r7 = 94742715(0x5a5a8bb, float:1.5578507E-35)
                r8 = -135761730(0xfffffffff7e870be, float:-9.428903E33)
                r9 = -1289044198(0xffffffffb32abf1a, float:-3.9755015E-8)
                if (r2 >= 0) goto L_0x0055
                if (r0 == 0) goto L_0x003b
                int r10 = r23.hashCode()
                if (r10 == r9) goto L_0x0034
                if (r10 == r8) goto L_0x002d
                if (r10 != r7) goto L_0x003b
                boolean r10 = r0.equals(r6)
                if (r10 == 0) goto L_0x003b
                r10 = r15
                goto L_0x0056
            L_0x002d:
                boolean r1 = r0.equals(r5)
                if (r1 == 0) goto L_0x003b
                return r13
            L_0x0034:
                boolean r10 = r0.equals(r4)
                if (r10 == 0) goto L_0x003b
                goto L_0x0055
            L_0x003b:
                com.facebook.react.bridge.JSApplicationIllegalArgumentException r1 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r2.append(r3)
                r2.append(r0)
                java.lang.String r0 = "for left extrapolation"
                r2.append(r0)
                java.lang.String r0 = r2.toString()
                r1.<init>(r0)
                throw r1
            L_0x0055:
                r10 = r13
            L_0x0056:
                int r0 = (r10 > r17 ? 1 : (r10 == r17 ? 0 : -1))
                if (r0 <= 0) goto L_0x0097
                if (r1 == 0) goto L_0x007d
                int r0 = r24.hashCode()
                if (r0 == r9) goto L_0x0076
                if (r0 == r8) goto L_0x006f
                if (r0 != r7) goto L_0x007d
                boolean r0 = r1.equals(r6)
                if (r0 == 0) goto L_0x007d
                r10 = r17
                goto L_0x0097
            L_0x006f:
                boolean r0 = r1.equals(r5)
                if (r0 == 0) goto L_0x007d
                return r10
            L_0x0076:
                boolean r0 = r1.equals(r4)
                if (r0 == 0) goto L_0x007d
                goto L_0x0097
            L_0x007d:
                com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                r2.append(r3)
                r2.append(r1)
                java.lang.String r1 = "for right extrapolation"
                r2.append(r1)
                java.lang.String r1 = r2.toString()
                r0.<init>(r1)
                throw r0
            L_0x0097:
                int r0 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
                if (r0 != 0) goto L_0x009c
                return r19
            L_0x009c:
                int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
                if (r0 != 0) goto L_0x00a8
                if (r2 > 0) goto L_0x00a5
                r0 = r19
                goto L_0x00b1
            L_0x00a5:
                r0 = r21
                goto L_0x00b1
            L_0x00a8:
                double r0 = r21 - r19
                double r10 = r10 - r15
                double r0 = r0 * r10
                double r2 = r17 - r15
                double r0 = r0 / r2
                double r0 = r19 + r0
            L_0x00b1:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.Companion.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
        }

        public final double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
            double[] dArr3 = dArr;
            double[] dArr4 = dArr2;
            Intrinsics.checkNotNullParameter(dArr3, "inputRange");
            Intrinsics.checkNotNullParameter(dArr4, "outputRange");
            int findRangeIndex = findRangeIndex(d, dArr);
            int i = findRangeIndex + 1;
            return interpolate(d, dArr3[findRangeIndex], dArr3[i], dArr4[findRangeIndex], dArr4[i], str, str2);
        }

        public final int interpolateColor(double d, double[] dArr, int[] iArr) {
            Intrinsics.checkNotNullParameter(dArr, "inputRange");
            Intrinsics.checkNotNullParameter(iArr, "outputRange");
            int findRangeIndex = findRangeIndex(d, dArr);
            int i = iArr[findRangeIndex];
            int i2 = findRangeIndex + 1;
            int i3 = iArr[i2];
            if (i == i3) {
                return i;
            }
            double d2 = dArr[findRangeIndex];
            double d3 = dArr[i2];
            if (d2 == d3) {
                return d <= d2 ? i : i3;
            }
            return ColorUtils.blendARGB(i, i3, (float) ((d - d2) / (d3 - d2)));
        }

        public final String interpolateString(String str, double d, double[] dArr, double[][] dArr2, String str2, String str3) {
            String str4 = str;
            double[] dArr3 = dArr;
            double[][] dArr4 = dArr2;
            Intrinsics.checkNotNullParameter(str4, "pattern");
            Intrinsics.checkNotNullParameter(dArr3, "inputRange");
            Intrinsics.checkNotNullParameter(dArr4, "outputRange");
            int findRangeIndex = findRangeIndex(d, dArr3);
            StringBuffer stringBuffer = new StringBuffer(str.length());
            Matcher matcher = InterpolationAnimatedNode.numericPattern.matcher(str4);
            int i = 0;
            while (matcher.find()) {
                double[] dArr5 = dArr4[findRangeIndex];
                if (i >= dArr5.length) {
                    break;
                }
                int i2 = findRangeIndex + 1;
                int i3 = i;
                StringBuffer stringBuffer2 = stringBuffer;
                double interpolate = interpolate(d, dArr3[findRangeIndex], dArr3[i2], dArr5[i], dArr4[i2][i], str2, str3);
                int i4 = (int) interpolate;
                matcher.appendReplacement(stringBuffer2, ((double) i4) == interpolate ? String.valueOf(i4) : String.valueOf(interpolate));
                i = i3 + 1;
                double d2 = d;
                stringBuffer = stringBuffer2;
                dArr3 = dArr;
            }
            StringBuffer stringBuffer3 = stringBuffer;
            matcher.appendTail(stringBuffer3);
            String stringBuffer4 = stringBuffer3.toString();
            Intrinsics.checkNotNullExpressionValue(stringBuffer4, "toString(...)");
            return stringBuffer4;
        }

        private final int findRangeIndex(double d, double[] dArr) {
            int i = 1;
            while (i < dArr.length - 1 && dArr[i] < d) {
                i++;
            }
            return i - 1;
        }
    }

    static {
        Pattern compile = Pattern.compile("[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?");
        Intrinsics.checkNotNullExpressionValue(compile, "compile(...)");
        numericPattern = compile;
    }
}
