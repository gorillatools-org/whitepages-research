package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.PorterDuff;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.views.image.ImageLoadEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTImageView")
public final class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ON_ERROR = "onError";
    private static final String ON_LOAD = "onLoad";
    private static final String ON_LOAD_END = "onLoadEnd";
    private static final String ON_LOAD_START = "onLoadStart";
    private static final String ON_PROGRESS = "onProgress";
    public static final String REACT_CLASS = "RCTImageView";
    private static final String REGISTRATION_NAME = "registrationName";
    private Object callerContext;
    private final ReactCallerContextFactory callerContextFactory;
    private final AbstractDraweeControllerBuilder draweeControllerBuilder;
    private final GlobalImageLoadListener globalImageLoadListener;

    public ReactImageManager() {
        this((AbstractDraweeControllerBuilder) null, (GlobalImageLoadListener) null, (ReactCallerContextFactory) null, 7, (DefaultConstructorMarker) null);
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, (ReactCallerContextFactory) null, 6, (DefaultConstructorMarker) null);
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener2) {
        this(abstractDraweeControllerBuilder, globalImageLoadListener2, (ReactCallerContextFactory) null, 4, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener2, ReactCallerContextFactory reactCallerContextFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : abstractDraweeControllerBuilder, (i & 2) != 0 ? null : globalImageLoadListener2, (i & 4) != 0 ? null : reactCallerContextFactory);
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener2, ReactCallerContextFactory reactCallerContextFactory) {
        this.draweeControllerBuilder = abstractDraweeControllerBuilder;
        this.globalImageLoadListener = globalImageLoadListener2;
        this.callerContextFactory = reactCallerContextFactory;
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, (ReactCallerContextFactory) null);
        this.callerContext = obj;
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener2, Object obj) {
        this(abstractDraweeControllerBuilder, globalImageLoadListener2, (ReactCallerContextFactory) null);
        this.callerContext = obj;
    }

    public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Object obj = this.callerContext;
        if (obj == null) {
            ReactCallerContextFactory reactCallerContextFactory = this.callerContextFactory;
            obj = reactCallerContextFactory != null ? reactCallerContextFactory.getOrCreateCallerContext(themedReactContext.getModuleName(), (String) null) : null;
        }
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = this.draweeControllerBuilder;
        if (abstractDraweeControllerBuilder == null) {
            abstractDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        Intrinsics.checkNotNull(abstractDraweeControllerBuilder);
        return new ReactImageView(themedReactContext, abstractDraweeControllerBuilder, this.globalImageLoadListener, obj);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "accessible")
    public final void setAccessible(ReactImageView reactImageView, boolean z) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setFocusable(z);
    }

    @ReactProp(name = "src")
    public final void setSrc(ReactImageView reactImageView, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        setSource(reactImageView, readableArray);
    }

    @ReactProp(name = "source")
    public final void setSource(ReactImageView reactImageView, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setSource(readableArray);
    }

    @ReactProp(name = "blurRadius")
    public final void setBlurRadius(ReactImageView reactImageView, float f) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setBlurRadius(f);
    }

    @ReactProp(name = "internal_analyticTag")
    public final void setInternal_AnalyticsTag(ReactImageView reactImageView, String str) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        ReactCallerContextFactory reactCallerContextFactory = this.callerContextFactory;
        if (reactCallerContextFactory != null) {
            Context context = reactImageView.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
            reactImageView.updateCallerContext(reactCallerContextFactory.getOrCreateCallerContext(((ThemedReactContext) context).getModuleName(), str));
        }
    }

    @ReactProp(name = "defaultSource")
    public final void setDefaultSource(ReactImageView reactImageView, String str) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setDefaultSource(str);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public final void setLoadingIndicatorSource(ReactImageView reactImageView, String str) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setLoadingIndicatorSource(str);
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public final void setBorderColor(ReactImageView reactImageView, Integer num) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        BackgroundStyleApplicator.setBorderColor(reactImageView, LogicalEdge.ALL, num);
    }

    @ReactProp(customType = "Color", name = "overlayColor")
    public final void setOverlayColor(ReactImageView reactImageView, Integer num) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        if (num == null) {
            reactImageView.setOverlayColor(0);
        } else {
            reactImageView.setOverlayColor(num.intValue());
        }
    }

    @ReactProp(name = "borderWidth")
    public final void setBorderWidth(ReactImageView reactImageView, float f) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        BackgroundStyleApplicator.setBorderWidth(reactImageView, LogicalEdge.ALL, Float.valueOf(f));
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public final void setBorderRadius(ReactImageView reactImageView, int i, float f) {
        LengthPercentage lengthPercentage;
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(f, LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(reactImageView, BorderRadiusProp.values()[i], lengthPercentage);
    }

    @ReactProp(name = "resizeMode")
    public final void setResizeMode(ReactImageView reactImageView, String str) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setScaleType(ImageResizeMode.toScaleType(str));
        reactImageView.setTileMode(ImageResizeMode.toTileMode(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r3.equals("auto") == false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r2.setResizeMethod(com.facebook.react.views.image.ImageResizeMethod.AUTO);
        com.facebook.common.logging.FLog.w(com.facebook.react.common.ReactConstants.TAG, "Invalid resize method: '" + r3 + "'");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "resizeMethod")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResizeMethod(com.facebook.react.views.image.ReactImageView r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            if (r3 == 0) goto L_0x0065
            int r0 = r3.hashCode()
            switch(r0) {
                case -934437708: goto L_0x0036;
                case 3005871: goto L_0x002d;
                case 3387192: goto L_0x001e;
                case 109250890: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x003e
        L_0x000f:
            java.lang.String r0 = "scale"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0018
            goto L_0x003e
        L_0x0018:
            com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.SCALE
            r2.setResizeMethod(r3)
            goto L_0x006a
        L_0x001e:
            java.lang.String r0 = "none"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0027
            goto L_0x003e
        L_0x0027:
            com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.NONE
            r2.setResizeMethod(r3)
            goto L_0x006a
        L_0x002d:
            java.lang.String r0 = "auto"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0065
            goto L_0x003e
        L_0x0036:
            java.lang.String r0 = "resize"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x005f
        L_0x003e:
            com.facebook.react.views.image.ImageResizeMethod r0 = com.facebook.react.views.image.ImageResizeMethod.AUTO
            r2.setResizeMethod(r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "Invalid resize method: '"
            r2.append(r0)
            r2.append(r3)
            java.lang.String r3 = "'"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "ReactNative"
            com.facebook.common.logging.FLog.w((java.lang.String) r3, (java.lang.String) r2)
            goto L_0x006a
        L_0x005f:
            com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.RESIZE
            r2.setResizeMethod(r3)
            goto L_0x006a
        L_0x0065:
            com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.AUTO
            r2.setResizeMethod(r3)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ReactImageManager.setResizeMethod(com.facebook.react.views.image.ReactImageView, java.lang.String):void");
    }

    @ReactProp(name = "resizeMultiplier")
    public final void setResizeMultiplier(ReactImageView reactImageView, float f) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        if (f < 0.01f) {
            FLog.w(ReactConstants.TAG, "Invalid resize multiplier: '" + f + "'");
        }
        reactImageView.setResizeMultiplier(f);
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public final void setTintColor(ReactImageView reactImageView, Integer num) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        if (num == null) {
            reactImageView.clearColorFilter();
        } else {
            reactImageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public final void setProgressiveRenderingEnabled(ReactImageView reactImageView, boolean z) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setProgressiveRenderingEnabled(z);
    }

    @ReactProp(name = "fadeDuration")
    public final void setFadeDuration(ReactImageView reactImageView, int i) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setFadeDuration(i);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public final void setLoadHandlersRegistered(ReactImageView reactImageView, boolean z) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        reactImageView.setShouldNotifyLoadEvents(z);
    }

    @ReactProp(name = "headers")
    public final void setHeaders(ReactImageView reactImageView, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        if (readableMap != null) {
            reactImageView.setHeaders(readableMap);
        }
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new LinkedHashMap<>();
        }
        ImageLoadEvent.Companion companion = ImageLoadEvent.Companion;
        exportedCustomDirectEventTypeConstants.put(companion.eventNameForType(4), MapsKt.mapOf(TuplesKt.to(REGISTRATION_NAME, ON_LOAD_START)));
        exportedCustomDirectEventTypeConstants.put(companion.eventNameForType(5), MapsKt.mapOf(TuplesKt.to(REGISTRATION_NAME, ON_PROGRESS)));
        exportedCustomDirectEventTypeConstants.put(companion.eventNameForType(2), MapsKt.mapOf(TuplesKt.to(REGISTRATION_NAME, ON_LOAD)));
        exportedCustomDirectEventTypeConstants.put(companion.eventNameForType(1), MapsKt.mapOf(TuplesKt.to(REGISTRATION_NAME, ON_ERROR)));
        exportedCustomDirectEventTypeConstants.put(companion.eventNameForType(3), MapsKt.mapOf(TuplesKt.to(REGISTRATION_NAME, ON_LOAD_END)));
        return exportedCustomDirectEventTypeConstants;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactImageView reactImageView) {
        Intrinsics.checkNotNullParameter(reactImageView, "view");
        super.onAfterUpdateTransaction(reactImageView);
        reactImageView.maybeUpdateView();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
