package com.facebook.react.views.progressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.AndroidProgressBarManagerDelegate;
import com.facebook.react.viewmanagers.AndroidProgressBarManagerInterface;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.WeakHashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AndroidProgressBar")
public final class ReactProgressBarViewManager extends BaseViewManager<ProgressBarContainerView, ProgressBarShadowNode> implements AndroidProgressBarManagerInterface<ProgressBarContainerView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DEFAULT_STYLE = "Normal";
    public static final String PROP_ANIMATING = "animating";
    public static final String PROP_ATTR = "typeAttr";
    public static final String PROP_INDETERMINATE = "indeterminate";
    public static final String PROP_PROGRESS = "progress";
    public static final String PROP_STYLE = "styleAttr";
    public static final String REACT_CLASS = "AndroidProgressBar";
    /* access modifiers changed from: private */
    public static final Object progressBarCtorLock = new Object();
    private final ViewManagerDelegate<ProgressBarContainerView> delegate = new AndroidProgressBarManagerDelegate(this);
    private final WeakHashMap<Integer, Pair<Integer, Integer>> measuredStyles = new WeakHashMap<>();

    @ReactProp(name = "typeAttr")
    public void setTypeAttr(ProgressBarContainerView progressBarContainerView, String str) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
    }

    public void updateExtraData(ProgressBarContainerView progressBarContainerView, Object obj) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "root");
        Intrinsics.checkNotNullParameter(obj, "extraData");
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ProgressBarContainerView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ProgressBarContainerView(themedReactContext);
    }

    @ReactProp(name = "styleAttr")
    public void setStyleAttr(ProgressBarContainerView progressBarContainerView, String str) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.setStyle$ReactAndroid_release(str);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ProgressBarContainerView progressBarContainerView, Integer num) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.setColor$ReactAndroid_release(num);
    }

    @ReactProp(name = "indeterminate")
    public void setIndeterminate(ProgressBarContainerView progressBarContainerView, boolean z) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.setIndeterminate$ReactAndroid_release(z);
    }

    @ReactProp(name = "progress")
    public void setProgress(ProgressBarContainerView progressBarContainerView, double d) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.setProgress$ReactAndroid_release(d);
    }

    @ReactProp(name = "animating")
    public void setAnimating(ProgressBarContainerView progressBarContainerView, boolean z) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.setAnimating$ReactAndroid_release(z);
    }

    public void setTestID(ProgressBarContainerView progressBarContainerView, String str) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        super.setTestId(progressBarContainerView, str);
    }

    public ProgressBarShadowNode createShadowNodeInstance() {
        return new ProgressBarShadowNode();
    }

    public Class<ProgressBarShadowNode> getShadowNodeClass() {
        return ProgressBarShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ProgressBarContainerView progressBarContainerView) {
        Intrinsics.checkNotNullParameter(progressBarContainerView, "view");
        progressBarContainerView.apply$ReactAndroid_release();
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ProgressBarContainerView> getDelegate() {
        return this.delegate;
    }

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(readableMap, "localData");
        Intrinsics.checkNotNullParameter(readableMap2, "props");
        Intrinsics.checkNotNullParameter(readableMap3, RemoteConfigConstants.ResponseFieldKey.STATE);
        Intrinsics.checkNotNullParameter(yogaMeasureMode, "widthMode");
        Intrinsics.checkNotNullParameter(yogaMeasureMode2, "heightMode");
        Companion companion = Companion;
        int styleFromString$ReactAndroid_release = companion.getStyleFromString$ReactAndroid_release(readableMap2.getString(PROP_STYLE));
        WeakHashMap<Integer, Pair<Integer, Integer>> weakHashMap = this.measuredStyles;
        Integer valueOf = Integer.valueOf(styleFromString$ReactAndroid_release);
        Pair<Integer, Integer> pair = weakHashMap.get(valueOf);
        if (pair == null) {
            ProgressBar createProgressBar = companion.createProgressBar(context, styleFromString$ReactAndroid_release);
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            createProgressBar.measure(makeMeasureSpec, makeMeasureSpec);
            pair = Pair.create(Integer.valueOf(createProgressBar.getMeasuredWidth()), Integer.valueOf(createProgressBar.getMeasuredHeight()));
            weakHashMap.put(valueOf, pair);
        }
        Pair pair2 = pair;
        return YogaMeasureOutput.make(PixelUtil.toDIPFromPixel((float) ((Number) pair2.first).intValue()), PixelUtil.toDIPFromPixel((float) ((Number) pair2.second).intValue()));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ProgressBar createProgressBar(Context context, int i) {
            ProgressBar progressBar;
            synchronized (ReactProgressBarViewManager.progressBarCtorLock) {
                progressBar = new ProgressBar(context, (AttributeSet) null, i);
            }
            return progressBar;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
            if (r5.equals(com.facebook.react.views.progressbar.ReactProgressBarViewManager.DEFAULT_STYLE) == false) goto L_0x0065;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final int getStyleFromString$ReactAndroid_release(java.lang.String r5) {
            /*
                r4 = this;
                java.lang.String r0 = "ReactNative"
                r1 = 16842871(0x1010077, float:2.3693892E-38)
                if (r5 == 0) goto L_0x007a
                int r2 = r5.hashCode()
                switch(r2) {
                    case -1955878649: goto L_0x005d;
                    case -1414214583: goto L_0x0050;
                    case -913872828: goto L_0x0043;
                    case -670403824: goto L_0x0036;
                    case -142408811: goto L_0x0029;
                    case 73190171: goto L_0x001c;
                    case 79996135: goto L_0x000f;
                    default: goto L_0x000e;
                }
            L_0x000e:
                goto L_0x0065
            L_0x000f:
                java.lang.String r2 = "Small"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x0018
                goto L_0x0065
            L_0x0018:
                r5 = 16842873(0x1010079, float:2.3693897E-38)
                return r5
            L_0x001c:
                java.lang.String r2 = "Large"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x0025
                goto L_0x0065
            L_0x0025:
                r5 = 16842874(0x101007a, float:2.36939E-38)
                return r5
            L_0x0029:
                java.lang.String r2 = "LargeInverse"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x0032
                goto L_0x0065
            L_0x0032:
                r5 = 16843401(0x1010289, float:2.3695377E-38)
                return r5
            L_0x0036:
                java.lang.String r2 = "Inverse"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x003f
                goto L_0x0065
            L_0x003f:
                r5 = 16843399(0x1010287, float:2.369537E-38)
                return r5
            L_0x0043:
                java.lang.String r2 = "Horizontal"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x004c
                goto L_0x0065
            L_0x004c:
                r5 = 16842872(0x1010078, float:2.3693894E-38)
                return r5
            L_0x0050:
                java.lang.String r2 = "SmallInverse"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x0059
                goto L_0x0065
            L_0x0059:
                r5 = 16843400(0x1010288, float:2.3695374E-38)
                return r5
            L_0x005d:
                java.lang.String r2 = "Normal"
                boolean r2 = r5.equals(r2)
                if (r2 != 0) goto L_0x0079
            L_0x0065:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Unknown ProgressBar style: "
                r2.append(r3)
                r2.append(r5)
                java.lang.String r5 = r2.toString()
                com.facebook.common.logging.FLog.w((java.lang.String) r0, (java.lang.String) r5)
            L_0x0079:
                return r1
            L_0x007a:
                java.lang.String r5 = "ProgressBar needs to have a style, null received"
                com.facebook.common.logging.FLog.w((java.lang.String) r0, (java.lang.String) r5)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.progressbar.ReactProgressBarViewManager.Companion.getStyleFromString$ReactAndroid_release(java.lang.String):int");
        }
    }
}
