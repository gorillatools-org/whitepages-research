package com.facebook.react.views.imagehelper;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.react.modules.fresco.ImageCacheControl;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class MultiSourceHelper {
    public static final MultiSourceHelper INSTANCE = new MultiSourceHelper();

    private MultiSourceHelper() {
    }

    public static final MultiSourceResult getBestSourceForSize(int i, int i2, List<? extends ImageSource> list) {
        Intrinsics.checkNotNullParameter(list, "sources");
        return getBestSourceForSize(i, i2, list, 1.0d);
    }

    public static final MultiSourceResult getBestSourceForSize(int i, int i2, List<? extends ImageSource> list, double d) {
        Intrinsics.checkNotNullParameter(list, "sources");
        ImageSource imageSource = null;
        if (list.isEmpty()) {
            return new MultiSourceResult((ImageSource) null, (ImageSource) null);
        }
        if (list.size() == 1) {
            return new MultiSourceResult((ImageSource) list.get(0), (ImageSource) null);
        }
        if (i <= 0 || i2 <= 0) {
            return new MultiSourceResult((ImageSource) null, (ImageSource) null);
        }
        ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
        Intrinsics.checkNotNullExpressionValue(imagePipeline, "getImagePipeline(...)");
        double d2 = ((double) (i * i2)) * d;
        double d3 = Double.MAX_VALUE;
        double d4 = Double.MAX_VALUE;
        ImageSource imageSource2 = null;
        ImageSource imageSource3 = null;
        for (ImageSource imageSource4 : list) {
            double abs = Math.abs(1.0d - (imageSource4.getSize() / d2));
            if (abs < d3) {
                imageSource3 = imageSource4;
                d3 = abs;
            }
            if (abs < d4 && imageSource4.getCacheControl() != ImageCacheControl.RELOAD) {
                if (imagePipeline.isInBitmapMemoryCache(imageSource4.getUri()) || imagePipeline.isInDiskCacheSync(imageSource4.getUri())) {
                    imageSource2 = imageSource4;
                    d4 = abs;
                }
            }
        }
        if (imageSource2 == null || imageSource3 == null || !Intrinsics.areEqual((Object) imageSource2.getSource(), (Object) imageSource3.getSource())) {
            imageSource = imageSource2;
        }
        return new MultiSourceResult(imageSource3, imageSource);
    }

    public static final class MultiSourceResult {
        public final ImageSource bestResult;
        public final ImageSource bestResultInCache;

        public MultiSourceResult(ImageSource imageSource, ImageSource imageSource2) {
            this.bestResult = imageSource;
            this.bestResultInCache = imageSource2;
        }
    }
}
