package com.facebook.react.uimanager.style;

import android.graphics.Shader;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LinearGradient {
    private final int[] colors;
    private final Direction direction;
    private final float[] positions;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords[] r0 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r1 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r1 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r1 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r1 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.LinearGradient.WhenMappings.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0071, code lost:
        r4 = new com.facebook.react.uimanager.style.LinearGradient.Direction.Keyword(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LinearGradient(com.facebook.react.bridge.ReadableMap r2, int[] r3, float[] r4) {
        /*
            r1 = this;
            java.lang.String r0 = "directionMap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "colors"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "positions"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r1.<init>()
            r1.colors = r3
            r1.positions = r4
            java.lang.String r3 = "type"
            java.lang.String r3 = r2.getString(r3)
            java.lang.String r4 = "angle"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            java.lang.String r0 = "value"
            if (r4 == 0) goto L_0x0030
            double r2 = r2.getDouble(r0)
            com.facebook.react.uimanager.style.LinearGradient$Direction$Angle r4 = new com.facebook.react.uimanager.style.LinearGradient$Direction$Angle
            r4.<init>(r2)
            goto L_0x0076
        L_0x0030:
            java.lang.String r4 = "keyword"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x0094
            java.lang.String r3 = r2.getString(r0)
            if (r3 == 0) goto L_0x0079
            int r4 = r3.hashCode()
            switch(r4) {
                case -1849920841: goto L_0x0067;
                case -1507310228: goto L_0x005c;
                case -1359525897: goto L_0x0051;
                case 810031148: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x0079
        L_0x0046:
            java.lang.String r4 = "to top right"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0079
            com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r2 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_TOP_RIGHT
            goto L_0x0071
        L_0x0051:
            java.lang.String r4 = "to top left"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0079
            com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r2 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_TOP_LEFT
            goto L_0x0071
        L_0x005c:
            java.lang.String r4 = "to bottom right"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0079
            com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r2 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_BOTTOM_RIGHT
            goto L_0x0071
        L_0x0067:
            java.lang.String r4 = "to bottom left"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0079
            com.facebook.react.uimanager.style.LinearGradient$Direction$Keywords r2 = com.facebook.react.uimanager.style.LinearGradient.Direction.Keywords.TO_BOTTOM_LEFT
        L_0x0071:
            com.facebook.react.uimanager.style.LinearGradient$Direction$Keyword r4 = new com.facebook.react.uimanager.style.LinearGradient$Direction$Keyword
            r4.<init>(r2)
        L_0x0076:
            r1.direction = r4
            return
        L_0x0079:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r2 = r2.getString(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Invalid linear gradient direction keyword: "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r3.<init>(r2)
            throw r3
        L_0x0094:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Invalid direction type: "
            r4.append(r0)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.LinearGradient.<init>(com.facebook.react.bridge.ReadableMap, int[], float[]):void");
    }

    private static abstract class Direction {
        public /* synthetic */ Direction(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final class Angle extends Direction {
            private final double value;

            public static /* synthetic */ Angle copy$default(Angle angle, double d, int i, Object obj) {
                if ((i & 1) != 0) {
                    d = angle.value;
                }
                return angle.copy(d);
            }

            public final double component1() {
                return this.value;
            }

            public final Angle copy(double d) {
                return new Angle(d);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Angle) && Double.compare(this.value, ((Angle) obj).value) == 0;
            }

            public int hashCode() {
                return Double.hashCode(this.value);
            }

            public String toString() {
                double d = this.value;
                return "Angle(value=" + d + ")";
            }

            public Angle(double d) {
                super((DefaultConstructorMarker) null);
                this.value = d;
            }

            public final double getValue() {
                return this.value;
            }
        }

        private Direction() {
        }

        public enum Keywords {
            TO_TOP_RIGHT,
            TO_BOTTOM_RIGHT,
            TO_TOP_LEFT,
            TO_BOTTOM_LEFT;

            public static EnumEntries getEntries() {
                return $ENTRIES;
            }

            static {
                Keywords[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }
        }

        public static final class Keyword extends Direction {
            private final Keywords value;

            public static /* synthetic */ Keyword copy$default(Keyword keyword, Keywords keywords, int i, Object obj) {
                if ((i & 1) != 0) {
                    keywords = keyword.value;
                }
                return keyword.copy(keywords);
            }

            public final Keywords component1() {
                return this.value;
            }

            public final Keyword copy(Keywords keywords) {
                Intrinsics.checkNotNullParameter(keywords, "value");
                return new Keyword(keywords);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Keyword) && this.value == ((Keyword) obj).value;
            }

            public int hashCode() {
                return this.value.hashCode();
            }

            public String toString() {
                Keywords keywords = this.value;
                return "Keyword(value=" + keywords + ")";
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Keyword(Keywords keywords) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(keywords, "value");
                this.value = keywords;
            }

            public final Keywords getValue() {
                return this.value;
            }
        }
    }

    public final Shader getShader(float f, float f2) {
        double d;
        Direction direction2 = this.direction;
        if (direction2 instanceof Direction.Angle) {
            d = ((Direction.Angle) direction2).getValue();
        } else if (direction2 instanceof Direction.Keyword) {
            d = getAngleForKeyword(((Direction.Keyword) direction2).getValue(), (double) f, (double) f2);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Pair endPointsFromAngle = endPointsFromAngle(d, f2, f);
        float[] fArr = (float[]) endPointsFromAngle.component1();
        float[] fArr2 = (float[]) endPointsFromAngle.component2();
        return new android.graphics.LinearGradient(fArr[0], fArr[1], fArr2[0], fArr2[1], this.colors, this.positions, Shader.TileMode.CLAMP);
    }

    private final double getAngleForKeyword(Direction.Keywords keywords, double d, double d2) {
        double degrees;
        double d3;
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[keywords.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 3) {
                    degrees = Math.toDegrees(Math.atan(d / d2));
                    i = 270;
                } else if (i2 == 4) {
                    degrees = Math.toDegrees(Math.atan(d2 / d));
                    i = 180;
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                d3 = (double) i;
            } else {
                degrees = Math.toDegrees(Math.atan(d / d2));
                d3 = (double) 90;
            }
            return degrees + d3;
        }
        return ((double) 90) - Math.toDegrees(Math.atan(d / d2));
    }

    private final Pair endPointsFromAngle(double d, float f, float f2) {
        double d2 = (double) 360;
        double d3 = d % d2;
        if (d3 < 0.0d) {
            d3 += d2;
        }
        if (d3 == 0.0d) {
            return new Pair(new float[]{0.0f, f}, new float[]{0.0f, 0.0f});
        }
        int i = (d3 > 90.0d ? 1 : (d3 == 90.0d ? 0 : -1));
        if (i == 0) {
            return new Pair(new float[]{0.0f, 0.0f}, new float[]{f2, 0.0f});
        }
        int i2 = (d3 > 180.0d ? 1 : (d3 == 180.0d ? 0 : -1));
        if (i2 == 0) {
            return new Pair(new float[]{0.0f, 0.0f}, new float[]{0.0f, f});
        }
        int i3 = (d3 > 270.0d ? 1 : (d3 == 270.0d ? 0 : -1));
        if (i3 == 0) {
            return new Pair(new float[]{f2, 0.0f}, new float[]{0.0f, 0.0f});
        }
        float tan = (float) Math.tan(Math.toRadians(((double) 90) - d3));
        float f3 = ((float) -1) / tan;
        float f4 = (float) 2;
        float f5 = f / f4;
        float f6 = f2 / f4;
        float[] fArr = i < 0 ? new float[]{f6, f5} : i2 < 0 ? new float[]{f6, -f5} : i3 < 0 ? new float[]{-f6, -f5} : new float[]{-f6, f5};
        float f7 = fArr[1] - (fArr[0] * f3);
        float f8 = f7 / (tan - f3);
        float f9 = (f3 * f8) + f7;
        return new Pair(new float[]{f6 - f8, f5 + f9}, new float[]{f6 + f8, f5 - f9});
    }
}
