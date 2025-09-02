package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTTextInlineImage")
public final class FrescoBasedReactTextInlineImageViewManager extends BaseViewManager<View, FrescoBasedReactTextInlineImageShadowNode> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RCTTextInlineImage";
    private final Object callerContext;
    private final AbstractDraweeControllerBuilder draweeControllerBuilder;

    public FrescoBasedReactTextInlineImageViewManager() {
        this((AbstractDraweeControllerBuilder) null, (Object) null, 3, (DefaultConstructorMarker) null);
    }

    public FrescoBasedReactTextInlineImageViewManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder) {
        this(abstractDraweeControllerBuilder, (Object) null, 2, (DefaultConstructorMarker) null);
    }

    public void updateExtraData(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "root");
        Intrinsics.checkNotNullParameter(obj, "extraData");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FrescoBasedReactTextInlineImageViewManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : abstractDraweeControllerBuilder, (i & 2) != 0 ? null : obj);
    }

    public FrescoBasedReactTextInlineImageViewManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.draweeControllerBuilder = abstractDraweeControllerBuilder;
        this.callerContext = obj;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public View createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    public FrescoBasedReactTextInlineImageShadowNode createShadowNodeInstance() {
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = this.draweeControllerBuilder;
        if (abstractDraweeControllerBuilder == null) {
            abstractDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return new FrescoBasedReactTextInlineImageShadowNode(abstractDraweeControllerBuilder, this.callerContext);
    }

    public Class<FrescoBasedReactTextInlineImageShadowNode> getShadowNodeClass() {
        return FrescoBasedReactTextInlineImageShadowNode.class;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
