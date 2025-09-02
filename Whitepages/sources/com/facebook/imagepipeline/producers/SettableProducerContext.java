package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfigInterface;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;

public class SettableProducerContext extends BaseProducerContext {
    public SettableProducerContext(ImageRequest imageRequest, ProducerContext producerContext) {
        this(imageRequest, producerContext.getId(), producerContext.getUiComponentId(), producerContext.getProducerListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), producerContext.isPrefetch(), producerContext.isIntermediateResultExpected(), producerContext.getPriority(), producerContext.getImagePipelineConfig());
    }

    public SettableProducerContext(ImageRequest imageRequest, String str, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        super(imageRequest, str, producerListener2, obj, requestLevel, z, z2, priority, imagePipelineConfigInterface);
    }

    public SettableProducerContext(ImageRequest imageRequest, String str, String str2, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        super(imageRequest, str, str2, (Map) null, producerListener2, obj, requestLevel, z, z2, priority, imagePipelineConfigInterface);
    }
}
