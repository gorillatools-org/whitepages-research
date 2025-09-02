package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.Set;

public class PipelineDraweeControllerBuilderSupplier implements Supplier {
    private final Set mBoundControllerListeners;
    private final Set mBoundControllerListeners2;
    private final Context mContext;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context, DraweeConfig draweeConfig) {
        this(context, ImagePipelineFactory.getInstance(), draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, DraweeConfig draweeConfig) {
        this(context, imagePipelineFactory, (Set) null, (Set) null, draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, Set set, Set set2, DraweeConfig draweeConfig) {
        this.mContext = context;
        ImagePipeline imagePipeline = imagePipelineFactory.getImagePipeline();
        this.mImagePipeline = imagePipeline;
        if (draweeConfig == null || draweeConfig.getPipelineDraweeControllerFactory() == null) {
            this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
        } else {
            this.mPipelineDraweeControllerFactory = draweeConfig.getPipelineDraweeControllerFactory();
        }
        ImagePipelineFactory imagePipelineFactory2 = imagePipelineFactory;
        this.mPipelineDraweeControllerFactory.init(context.getResources(), DeferredReleaser.getInstance(), imagePipelineFactory.getAnimatedDrawableFactory(context), imagePipelineFactory.getXmlDrawableFactory(), UiThreadImmediateExecutorService.getInstance(), imagePipeline.getBitmapMemoryCache(), draweeConfig != null ? draweeConfig.getCustomDrawableFactories() : null, draweeConfig != null ? draweeConfig.getDebugOverlayEnabledSupplier() : null);
        this.mBoundControllerListeners = set;
        this.mBoundControllerListeners2 = set2;
        if (draweeConfig != null) {
            draweeConfig.getImagePerfDataListener();
        }
    }

    public PipelineDraweeControllerBuilder get() {
        return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners, this.mBoundControllerListeners2).setPerfDataListener((ImagePerfDataListener) null);
    }
}
