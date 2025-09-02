package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import java.util.List;

public class DraweeConfig {
    private final ImmutableList mCustomDrawableFactories;
    private final Supplier mDebugOverlayEnabledSupplier;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public ImagePerfDataListener getImagePerfDataListener() {
        return null;
    }

    private DraweeConfig(Builder builder) {
        Supplier supplier;
        this.mCustomDrawableFactories = builder.mCustomDrawableFactories != null ? ImmutableList.copyOf(builder.mCustomDrawableFactories) : null;
        if (builder.mDebugOverlayEnabledSupplier != null) {
            supplier = builder.mDebugOverlayEnabledSupplier;
        } else {
            supplier = Suppliers.of(Boolean.FALSE);
        }
        this.mDebugOverlayEnabledSupplier = supplier;
        this.mPipelineDraweeControllerFactory = builder.mPipelineDraweeControllerFactory;
        ImagePerfDataListener unused = builder.getClass();
    }

    public ImmutableList getCustomDrawableFactories() {
        return this.mCustomDrawableFactories;
    }

    public PipelineDraweeControllerFactory getPipelineDraweeControllerFactory() {
        return this.mPipelineDraweeControllerFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Supplier getDebugOverlayEnabledSupplier() {
        return this.mDebugOverlayEnabledSupplier;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public List mCustomDrawableFactories;
        /* access modifiers changed from: private */
        public Supplier mDebugOverlayEnabledSupplier;
        /* access modifiers changed from: private */
        public PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

        public DraweeConfig build() {
            return new DraweeConfig(this);
        }
    }
}
