package com.facebook.imagepipeline.backends.okhttp3;

import android.content.Context;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;

public final class OkHttpImagePipelineConfigFactory {
    public static final OkHttpImagePipelineConfigFactory INSTANCE = new OkHttpImagePipelineConfigFactory();

    private OkHttpImagePipelineConfigFactory() {
    }

    public static final ImagePipelineConfig.Builder newBuilder(Context context, OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        return ImagePipelineConfig.Companion.newBuilder(context).setNetworkFetcher(new OkHttpNetworkFetcher(okHttpClient));
    }
}
