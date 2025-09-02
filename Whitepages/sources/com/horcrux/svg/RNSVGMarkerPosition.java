package com.horcrux.svg;

import java.util.ArrayList;
import java.util.Iterator;

class RNSVGMarkerPosition {
    private static boolean auto_start_reverse_;
    private static int element_index_;
    private static Point in_slope_;
    private static Point origin_;
    private static Point out_slope_;
    private static ArrayList positions_;
    private static Point subpath_start_;
    double angle;
    Point origin;
    RNSVGMarkerType type;

    private static double rad2deg(double d) {
        return d * 57.29577951308232d;
    }

    private RNSVGMarkerPosition(RNSVGMarkerType rNSVGMarkerType, Point point, double d) {
        this.type = rNSVGMarkerType;
        this.origin = point;
        this.angle = d;
    }

    static ArrayList fromPath(ArrayList arrayList) {
        positions_ = new ArrayList();
        element_index_ = 0;
        origin_ = new Point(0.0d, 0.0d);
        subpath_start_ = new Point(0.0d, 0.0d);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            UpdateFromPathElement((PathElement) it.next());
        }
        PathIsDone();
        return positions_;
    }

    private static void PathIsDone() {
        RNSVGMarkerType rNSVGMarkerType = RNSVGMarkerType.kEndMarker;
        positions_.add(new RNSVGMarkerPosition(rNSVGMarkerType, origin_, CurrentAngle(rNSVGMarkerType)));
    }

    private static double BisectingAngle(double d, double d2) {
        if (Math.abs(d - d2) > 180.0d) {
            d += 360.0d;
        }
        return (d + d2) / 2.0d;
    }

    private static double SlopeAngleRadians(Point point) {
        return Math.atan2(point.y, point.x);
    }

    private static double CurrentAngle(RNSVGMarkerType rNSVGMarkerType) {
        double rad2deg = rad2deg(SlopeAngleRadians(in_slope_));
        double rad2deg2 = rad2deg(SlopeAngleRadians(out_slope_));
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$RNSVGMarkerType[rNSVGMarkerType.ordinal()];
        if (i == 1) {
            return auto_start_reverse_ ? rad2deg2 + 180.0d : rad2deg2;
        }
        if (i == 2) {
            return BisectingAngle(rad2deg, rad2deg2);
        }
        if (i != 3) {
            return 0.0d;
        }
        return rad2deg;
    }

    private static Point subtract(Point point, Point point2) {
        return new Point(point2.x - point.x, point2.y - point.y);
    }

    private static boolean isZero(Point point) {
        return point.x == 0.0d && point.y == 0.0d;
    }

    private static void ComputeQuadTangents(SegmentData segmentData, Point point, Point point2, Point point3) {
        segmentData.start_tangent = subtract(point2, point);
        segmentData.end_tangent = subtract(point3, point2);
        if (isZero(segmentData.start_tangent)) {
            segmentData.start_tangent = segmentData.end_tangent;
        } else if (isZero(segmentData.end_tangent)) {
            segmentData.end_tangent = segmentData.start_tangent;
        }
    }

    private static SegmentData ExtractPathElementFeatures(PathElement pathElement) {
        SegmentData segmentData = new SegmentData();
        Point[] pointArr = pathElement.points;
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$ElementType[pathElement.type.ordinal()];
        if (i == 1) {
            segmentData.position = pointArr[2];
            segmentData.start_tangent = subtract(pointArr[0], origin_);
            segmentData.end_tangent = subtract(pointArr[2], pointArr[1]);
            if (isZero(segmentData.start_tangent)) {
                ComputeQuadTangents(segmentData, pointArr[0], pointArr[1], pointArr[2]);
            } else if (isZero(segmentData.end_tangent)) {
                ComputeQuadTangents(segmentData, origin_, pointArr[0], pointArr[1]);
            }
        } else if (i == 2) {
            Point point = pointArr[1];
            segmentData.position = point;
            ComputeQuadTangents(segmentData, origin_, pointArr[0], point);
        } else if (i == 3 || i == 4) {
            Point point2 = pointArr[0];
            segmentData.position = point2;
            segmentData.start_tangent = subtract(point2, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        } else if (i == 5) {
            Point point3 = subpath_start_;
            segmentData.position = point3;
            segmentData.start_tangent = subtract(point3, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        }
        return segmentData;
    }

    /* renamed from: com.horcrux.svg.RNSVGMarkerPosition$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$ElementType;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RNSVGMarkerType;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|9|10|11|12|13|14|15|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0059 */
        static {
            /*
                com.horcrux.svg.ElementType[] r0 = com.horcrux.svg.ElementType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$ElementType = r0
                r1 = 1
                com.horcrux.svg.ElementType r2 = com.horcrux.svg.ElementType.kCGPathElementAddCurveToPoint     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$horcrux$svg$ElementType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.ElementType r3 = com.horcrux.svg.ElementType.kCGPathElementAddQuadCurveToPoint     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$horcrux$svg$ElementType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.ElementType r4 = com.horcrux.svg.ElementType.kCGPathElementMoveToPoint     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$horcrux$svg$ElementType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.ElementType r4 = com.horcrux.svg.ElementType.kCGPathElementAddLineToPoint     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$horcrux$svg$ElementType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.ElementType r4 = com.horcrux.svg.ElementType.kCGPathElementCloseSubpath     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.horcrux.svg.RNSVGMarkerType[] r3 = com.horcrux.svg.RNSVGMarkerType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType = r3
                com.horcrux.svg.RNSVGMarkerType r4 = com.horcrux.svg.RNSVGMarkerType.kStartMarker     // Catch:{ NoSuchFieldError -> 0x004f }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$com$horcrux$svg$RNSVGMarkerType     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.horcrux.svg.RNSVGMarkerType r3 = com.horcrux.svg.RNSVGMarkerType.kMidMarker     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$com$horcrux$svg$RNSVGMarkerType     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.horcrux.svg.RNSVGMarkerType r1 = com.horcrux.svg.RNSVGMarkerType.kEndMarker     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RNSVGMarkerPosition.AnonymousClass1.<clinit>():void");
        }
    }

    private static void UpdateFromPathElement(PathElement pathElement) {
        SegmentData ExtractPathElementFeatures = ExtractPathElementFeatures(pathElement);
        out_slope_ = ExtractPathElementFeatures.start_tangent;
        int i = element_index_;
        if (i > 0) {
            RNSVGMarkerType rNSVGMarkerType = i == 1 ? RNSVGMarkerType.kStartMarker : RNSVGMarkerType.kMidMarker;
            positions_.add(new RNSVGMarkerPosition(rNSVGMarkerType, origin_, CurrentAngle(rNSVGMarkerType)));
        }
        in_slope_ = ExtractPathElementFeatures.end_tangent;
        origin_ = ExtractPathElementFeatures.position;
        ElementType elementType = pathElement.type;
        if (elementType == ElementType.kCGPathElementMoveToPoint) {
            subpath_start_ = pathElement.points[0];
        } else if (elementType == ElementType.kCGPathElementCloseSubpath) {
            subpath_start_ = new Point(0.0d, 0.0d);
        }
        element_index_++;
    }
}
