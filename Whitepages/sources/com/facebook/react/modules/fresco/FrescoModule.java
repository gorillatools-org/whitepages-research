package com.facebook.react.modules.fresco;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.network.OkHttpCompat;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

@ReactModule(name = "FrescoModule", needsEagerInit = true)
public class FrescoModule extends ReactContextBaseJavaModule implements ModuleDataCleaner.Cleanable, LifecycleEventListener, TurboModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "FrescoModule";
    /* access modifiers changed from: private */
    public static boolean hasBeenInitialized;
    private final boolean clearOnDestroy;
    private ImagePipelineConfig config;
    private ImagePipeline pipeline;

    public FrescoModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, false, (ImagePipelineConfig) null, 6, (DefaultConstructorMarker) null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline) {
        this(reactApplicationContext, imagePipeline, false, false, 12, (DefaultConstructorMarker) null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline, boolean z) {
        this(reactApplicationContext, imagePipeline, z, false, 8, (DefaultConstructorMarker) null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z) {
        this(reactApplicationContext, z, (ImagePipelineConfig) null, 4, (DefaultConstructorMarker) null);
    }

    public static final ImagePipelineConfig.Builder getDefaultConfigBuilder(ReactContext reactContext) {
        return Companion.getDefaultConfigBuilder(reactContext);
    }

    public static final boolean hasBeenInitialized() {
        return Companion.hasBeenInitialized();
    }

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FrescoModule(ReactApplicationContext reactApplicationContext, boolean z, ImagePipelineConfig imagePipelineConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : imagePipelineConfig);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z, ImagePipelineConfig imagePipelineConfig) {
        super(reactApplicationContext);
        this.clearOnDestroy = z;
        this.config = imagePipelineConfig;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FrescoModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, imagePipeline, (i & 4) != 0 ? true : z, (i & 8) != 0 ? false : z2);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline, boolean z, boolean z2) {
        this(reactApplicationContext, z, (ImagePipelineConfig) null, 4, (DefaultConstructorMarker) null);
        this.pipeline = imagePipeline;
        if (z2) {
            hasBeenInitialized = true;
        }
    }

    public void initialize() {
        super.initialize();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.addLifecycleEventListener(this);
        Companion companion = Companion;
        if (!companion.hasBeenInitialized()) {
            ImagePipelineConfig imagePipelineConfig = this.config;
            if (imagePipelineConfig == null) {
                Intrinsics.checkNotNull(reactApplicationContext);
                imagePipelineConfig = companion.getDefaultConfig(reactApplicationContext);
            }
            DraweeConfig.Builder newBuilder = DraweeConfig.newBuilder();
            Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder(...)");
            Fresco.initialize(reactApplicationContext.getApplicationContext(), imagePipelineConfig, newBuilder.build());
            hasBeenInitialized = true;
        } else if (this.config != null) {
            FLog.w(ReactConstants.TAG, "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.config = null;
    }

    public String getName() {
        return NAME;
    }

    public void clearSensitiveData() {
        ImagePipeline imagePipeline = getImagePipeline();
        if (imagePipeline != null) {
            imagePipeline.clearCaches();
        }
    }

    public void onHostDestroy() {
        ImagePipeline imagePipeline;
        if (Companion.hasBeenInitialized() && this.clearOnDestroy && (imagePipeline = getImagePipeline()) != null) {
            imagePipeline.clearMemoryCaches();
        }
    }

    private final ImagePipeline getImagePipeline() {
        if (this.pipeline == null) {
            this.pipeline = Fresco.getImagePipeline();
        }
        return this.pipeline;
    }

    public void invalidate() {
        getReactApplicationContext().removeLifecycleEventListener(this);
        super.invalidate();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean hasBeenInitialized() {
            return FrescoModule.hasBeenInitialized;
        }

        /* access modifiers changed from: private */
        public final ImagePipelineConfig getDefaultConfig(ReactContext reactContext) {
            return getDefaultConfigBuilder(reactContext).build();
        }

        public final ImagePipelineConfig.Builder getDefaultConfigBuilder(ReactContext reactContext) {
            Intrinsics.checkNotNullParameter(reactContext, "context");
            HashSet hashSet = new HashSet();
            hashSet.add(new SystraceRequestListener());
            OkHttpClient createClient = OkHttpClientProvider.createClient();
            OkHttpCompat.getCookieJarContainer(createClient).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler()));
            Context applicationContext = reactContext.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            Intrinsics.checkNotNull(createClient);
            ImagePipelineConfig.Builder requestListeners = OkHttpImagePipelineConfigFactory.newBuilder(applicationContext, createClient).setNetworkFetcher(new ReactOkHttpNetworkFetcher(createClient)).setDownsampleMode(DownsampleMode.AUTO).setRequestListeners(hashSet);
            requestListeners.experiment().setBinaryXmlEnabled(ReactNativeFeatureFlags.loadVectorDrawablesOnImages());
            return requestListeners;
        }
    }
}
