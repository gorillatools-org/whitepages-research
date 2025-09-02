package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.rnlineargradient.LinearGradientManager;

@ReactModule(name = "RNSVGRenderableModule")
class RNSVGRenderableManager extends NativeSvgRenderableModuleSpec {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    public static final String NAME = "RNSVGRenderableModule";

    RNSVGRenderableManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNSVGRenderableModule";
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isPointInFill(Double d, ReadableMap readableMap) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return false;
        }
        float f = renderableViewByTag.mScale;
        if (renderableViewByTag.hitTest(new float[]{((float) readableMap.getDouble("x")) * f, ((float) readableMap.getDouble("y")) * f}) != -1) {
            return true;
        }
        return false;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean isPointInStroke(Double d, ReadableMap readableMap) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return false;
        }
        try {
            renderableViewByTag.getPath((Canvas) null, (Paint) null);
            renderableViewByTag.initBounds();
            double d2 = (double) renderableViewByTag.mScale;
            int i = (int) (readableMap.getDouble("x") * d2);
            int i2 = (int) (readableMap.getDouble("y") * d2);
            Region region = renderableViewByTag.mStrokeRegion;
            if (region == null || !region.contains(i, i2)) {
                return false;
            }
            return true;
        } catch (NullPointerException unused) {
            renderableViewByTag.invalidate();
            return false;
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public double getTotalLength(Double d) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return 0.0d;
        }
        try {
            return (double) (new PathMeasure(renderableViewByTag.getPath((Canvas) null, (Paint) null), false).getLength() / renderableViewByTag.mScale);
        } catch (NullPointerException unused) {
            renderableViewByTag.invalidate();
            return -1.0d;
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getPointAtLength(Double d, ReadableMap readableMap) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return Arguments.createMap();
        }
        try {
            PathMeasure pathMeasure = new PathMeasure(renderableViewByTag.getPath((Canvas) null, (Paint) null), false);
            float f = (float) readableMap.getDouble("length");
            float f2 = renderableViewByTag.mScale;
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            pathMeasure.getPosTan(Math.max(0.0f, Math.min(f * f2, pathMeasure.getLength())), fArr, fArr2);
            double atan2 = Math.atan2((double) fArr2[1], (double) fArr2[0]);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (fArr[0] / f2));
            createMap.putDouble("y", (double) (fArr[1] / f2));
            createMap.putDouble(LinearGradientManager.PROP_ANGLE, atan2);
            return createMap;
        } catch (NullPointerException unused) {
            renderableViewByTag.invalidate();
            return Arguments.createMap();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getBBox(Double d, ReadableMap readableMap) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return Arguments.createMap();
        }
        boolean z = readableMap.getBoolean("fill");
        boolean z2 = readableMap.getBoolean("stroke");
        boolean z3 = readableMap.getBoolean("markers");
        boolean z4 = readableMap.getBoolean("clipped");
        try {
            renderableViewByTag.getPath((Canvas) null, (Paint) null);
            float f = renderableViewByTag.mScale;
            renderableViewByTag.initBounds();
            RectF rectF = new RectF();
            RectF rectF2 = renderableViewByTag.mFillBounds;
            RectF rectF3 = renderableViewByTag.mStrokeBounds;
            RectF rectF4 = renderableViewByTag.mMarkerBounds;
            RectF rectF5 = renderableViewByTag.mClipBounds;
            if (z && rectF2 != null) {
                rectF.union(rectF2);
            }
            if (z2 && rectF3 != null) {
                rectF.union(rectF3);
            }
            if (z3 && rectF4 != null) {
                rectF.union(rectF4);
            }
            if (z4 && rectF5 != null) {
                rectF.intersect(rectF5);
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (rectF.left / f));
            createMap.putDouble("y", (double) (rectF.top / f));
            createMap.putDouble("width", (double) (rectF.width() / f));
            createMap.putDouble("height", (double) (rectF.height() / f));
            return createMap;
        } catch (NullPointerException unused) {
            renderableViewByTag.invalidate();
            return Arguments.createMap();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getCTM(Double d) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return Arguments.createMap();
        }
        float f = renderableViewByTag.mScale;
        Matrix matrix = new Matrix(renderableViewByTag.mCTM);
        SvgView svgView = renderableViewByTag.getSvgView();
        if (svgView != null) {
            matrix.preConcat(svgView.mInvViewBoxMatrix);
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("a", (double) fArr[0]);
            createMap.putDouble("b", (double) fArr[3]);
            createMap.putDouble("c", (double) fArr[1]);
            createMap.putDouble("d", (double) fArr[4]);
            createMap.putDouble("e", (double) (fArr[2] / f));
            createMap.putDouble("f", (double) (fArr[5] / f));
            return createMap;
        }
        throw new RuntimeException("Did not find parent SvgView for view with tag: " + d);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getScreenCTM(Double d) {
        RenderableView renderableViewByTag = VirtualViewManager.getRenderableViewByTag(d.intValue());
        if (renderableViewByTag == null) {
            return Arguments.createMap();
        }
        float[] fArr = new float[9];
        renderableViewByTag.mCTM.getValues(fArr);
        float f = renderableViewByTag.mScale;
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("a", (double) fArr[0]);
        createMap.putDouble("b", (double) fArr[3]);
        createMap.putDouble("c", (double) fArr[1]);
        createMap.putDouble("d", (double) fArr[4]);
        createMap.putDouble("e", (double) (fArr[2] / f));
        createMap.putDouble("f", (double) (fArr[5] / f));
        return createMap;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0044 */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getRawResource(java.lang.String r8, com.facebook.react.bridge.Promise r9) {
        /*
            r7 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r7.getReactApplicationContext()     // Catch:{ Exception -> 0x003f }
            android.content.res.Resources r1 = r0.getResources()     // Catch:{ Exception -> 0x003f }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ Exception -> 0x003f }
            java.lang.String r2 = "raw"
            int r8 = r1.getIdentifier(r8, r2, r0)     // Catch:{ Exception -> 0x003f }
            java.io.InputStream r8 = r1.openRawResource(r8)     // Catch:{ Exception -> 0x003f }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x0032 }
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0032 }
            r0.<init>(r8, r1)     // Catch:{ all -> 0x0032 }
            r1 = 4096(0x1000, float:5.74E-42)
            char[] r2 = new char[r1]     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r3.<init>()     // Catch:{ all -> 0x0032 }
        L_0x0026:
            r4 = 0
            int r5 = r0.read(r2, r4, r1)     // Catch:{ all -> 0x0032 }
            r6 = -1
            if (r5 == r6) goto L_0x0034
            r3.append(r2, r4, r5)     // Catch:{ all -> 0x0032 }
            goto L_0x0026
        L_0x0032:
            r0 = move-exception
            goto L_0x0041
        L_0x0034:
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0032 }
            r9.resolve(r0)     // Catch:{ all -> 0x0032 }
            r8.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004b
        L_0x003f:
            r8 = move-exception
            goto L_0x0045
        L_0x0041:
            r8.close()     // Catch:{ IOException -> 0x0044 }
        L_0x0044:
            throw r0     // Catch:{ Exception -> 0x003f }
        L_0x0045:
            r8.printStackTrace()
            r9.reject((java.lang.Throwable) r8)
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RNSVGRenderableManager.getRawResource(java.lang.String, com.facebook.react.bridge.Promise):void");
    }
}
