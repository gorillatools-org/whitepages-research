package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@ReactModule(name = "RNCSafeAreaContext")
public final class SafeAreaContextModule extends NativeSafeAreaContextSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "RNCSafeAreaContext";

    public SafeAreaContextModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getTypedExportedConstants() {
        return MapsKt.mapOf(TuplesKt.to("initialWindowMetrics", getInitialWindowMetrics()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r0 = r0.getWindow();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.Map<java.lang.String, java.lang.Object> getInitialWindowMetrics() {
        /*
            r4 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r4.getReactApplicationContext()
            android.app.Activity r0 = r0.getCurrentActivity()
            r1 = 0
            if (r0 == 0) goto L_0x0016
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0016
            android.view.View r0 = r0.getDecorView()
            goto L_0x0017
        L_0x0016:
            r0 = r1
        L_0x0017:
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x004e
            r2 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r2 = r0.findViewById(r2)
            if (r2 != 0) goto L_0x0025
            goto L_0x004e
        L_0x0025:
            com.th3rdwave.safeareacontext.EdgeInsets r3 = com.th3rdwave.safeareacontext.SafeAreaUtilsKt.getSafeAreaInsets(r0)
            com.th3rdwave.safeareacontext.Rect r0 = com.th3rdwave.safeareacontext.SafeAreaUtilsKt.getFrame(r0, r2)
            if (r3 == 0) goto L_0x004e
            if (r0 != 0) goto L_0x0032
            goto L_0x004e
        L_0x0032:
            java.lang.String r1 = "insets"
            java.util.Map r2 = com.th3rdwave.safeareacontext.SerializationUtilsKt.edgeInsetsToJavaMap(r3)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            java.lang.String r2 = "frame"
            java.util.Map r0 = com.th3rdwave.safeareacontext.SerializationUtilsKt.rectToJavaMap(r0)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r2, r0)
            kotlin.Pair[] r0 = new kotlin.Pair[]{r1, r0}
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r0)
        L_0x004e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.th3rdwave.safeareacontext.SafeAreaContextModule.getInitialWindowMetrics():java.util.Map");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
