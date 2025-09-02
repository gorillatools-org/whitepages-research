package com.facebook.react.shell;

import com.facebook.imagepipeline.core.ImagePipelineConfig;
import kotlin.jvm.internal.Intrinsics;

public final class MainPackageConfig {
    private final ImagePipelineConfig frescoConfig;

    public MainPackageConfig(ImagePipelineConfig imagePipelineConfig) {
        Intrinsics.checkNotNullParameter(imagePipelineConfig, "frescoConfig");
        this.frescoConfig = imagePipelineConfig;
    }

    public final ImagePipelineConfig getFrescoConfig() {
        return this.frescoConfig;
    }
}
