package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapProbeProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DelayProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedProbeProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalThumbnailBitmapSdk29Producer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProducerSequenceFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean allowDelay;
    private final Lazy backgroundLocalContentUriFetchToEncodeMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda17(this));
    private final Lazy backgroundLocalFileFetchToEncodeMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda16(this));
    private final Lazy backgroundNetworkFetchToEncodedMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda12(this));
    private Map bitmapPrepareSequences = new LinkedHashMap();
    private Map closeableImagePrefetchSequences = new LinkedHashMap();
    private final Lazy commonNetworkFetchToEncodedMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda14(this));
    private final ContentResolver contentResolver;
    private final Set customProducerSequenceFactories;
    private final Lazy dataFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda8(this));
    private final boolean diskCacheEnabled;
    private final DownsampleMode downsampleMode;
    private final ImageTranscoderFactory imageTranscoderFactory;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;
    private final Lazy localAssetFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda7(this));
    private final Lazy localContentUriFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda10(this));
    private final Lazy localContentUriFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda3(this));
    private final Lazy localFileFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda9(this));
    private final Lazy localFileFetchToEncodedMemoryPrefetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda15(this));
    private final Lazy localImageFileFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda1(this));
    private final Lazy localResourceFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda6(this));
    private final Lazy localThumbnailBitmapSdk29FetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda4(this));
    private final Lazy localVideoFileFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda2(this));
    private final Lazy networkFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda0(this));
    private final Lazy networkFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda11(this));
    private final Lazy networkFetchToEncodedMemoryPrefetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda13(this));
    private final NetworkFetcher networkFetcher;
    private final boolean partialImageCachingEnabled;
    private Map postprocessorSequences = new LinkedHashMap();
    private final ProducerFactory producerFactory;
    private final Lazy qualifiedResourceFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$$ExternalSyntheticLambda5(this));
    private final boolean resizeAndRotateEnabledForNetwork;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private final boolean useBitmapPrepareToDraw;
    private final boolean webpSupportEnabled;

    public ProducerSequenceFactory(ContentResolver contentResolver2, ProducerFactory producerFactory2, NetworkFetcher networkFetcher2, boolean z, boolean z2, ThreadHandoffProducerQueue threadHandoffProducerQueue2, DownsampleMode downsampleMode2, boolean z3, boolean z4, boolean z5, ImageTranscoderFactory imageTranscoderFactory2, boolean z6, boolean z7, boolean z8, Set set) {
        ImageTranscoderFactory imageTranscoderFactory3 = imageTranscoderFactory2;
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        Intrinsics.checkNotNullParameter(producerFactory2, "producerFactory");
        Intrinsics.checkNotNullParameter(networkFetcher2, "networkFetcher");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue2, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(downsampleMode2, "downsampleMode");
        Intrinsics.checkNotNullParameter(imageTranscoderFactory3, "imageTranscoderFactory");
        this.contentResolver = contentResolver2;
        this.producerFactory = producerFactory2;
        this.networkFetcher = networkFetcher2;
        this.resizeAndRotateEnabledForNetwork = z;
        this.webpSupportEnabled = z2;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue2;
        this.downsampleMode = downsampleMode2;
        this.useBitmapPrepareToDraw = z3;
        this.partialImageCachingEnabled = z4;
        this.diskCacheEnabled = z5;
        this.imageTranscoderFactory = imageTranscoderFactory3;
        this.isEncodedMemoryCacheProbingEnabled = z6;
        this.isDiskCacheProbingEnabled = z7;
        this.allowDelay = z8;
        this.customProducerSequenceFactories = set;
    }

    /* access modifiers changed from: private */
    public static final Producer backgroundLocalContentUriFetchToEncodeMemorySequence_delegate$lambda$24(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            LocalContentUriFetchProducer newLocalContentUriFetchProducer = producerSequenceFactory.producerFactory.newLocalContentUriFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer, "newLocalContentUriFetchProducer(...)");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalContentUriFetchProducer), producerSequenceFactory.threadHandoffProducerQueue);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalContentUriFetchToEncodeMemorySequence:init");
        try {
            LocalContentUriFetchProducer newLocalContentUriFetchProducer2 = producerSequenceFactory.producerFactory.newLocalContentUriFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer2, "newLocalContentUriFetchProducer(...)");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalContentUriFetchProducer2), producerSequenceFactory.threadHandoffProducerQueue);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final Producer backgroundLocalFileFetchToEncodeMemorySequence_delegate$lambda$22(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            LocalFileFetchProducer newLocalFileFetchProducer = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer, "newLocalFileFetchProducer(...)");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer), producerSequenceFactory.threadHandoffProducerQueue);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence");
        try {
            LocalFileFetchProducer newLocalFileFetchProducer2 = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer2, "newLocalFileFetchProducer(...)");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer2), producerSequenceFactory.threadHandoffProducerQueue);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final Producer backgroundNetworkFetchToEncodedMemorySequence_delegate$lambda$13(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence:init");
        try {
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final Producer commonNetworkFetchToEncodedMemorySequence_delegate$lambda$17(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence");
        try {
            return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    private final Producer getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Producer producer;
        if (!FrescoSystrace.isTracing()) {
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "getSourceUri(...)");
            if (sourceUri != null) {
                int sourceUriType = imageRequest.getSourceUriType();
                if (sourceUriType == 0) {
                    return getNetworkFetchSequence();
                }
                switch (sourceUriType) {
                    case 2:
                        if (imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                        return getLocalVideoFileFetchSequence();
                    case 3:
                        if (imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                        return getLocalImageFileFetchSequence();
                    case 4:
                        if (imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                        if (MediaUtils.isVideo(this.contentResolver.getType(sourceUri))) {
                            return getLocalVideoFileFetchSequence();
                        }
                        return getLocalContentUriFetchSequence();
                    case 5:
                        return getLocalAssetFetchSequence();
                    case 6:
                        return getLocalResourceFetchSequence();
                    case 7:
                        return getDataFetchSequence();
                    case 8:
                        return getQualifiedResourceFetchSequence();
                    default:
                        Set set = this.customProducerSequenceFactories;
                        if (set != null) {
                            Iterator it = set.iterator();
                            if (it.hasNext()) {
                                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                                throw null;
                            }
                        }
                        String access$getShortenedUriString = Companion.getShortenedUriString(sourceUri);
                        throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + access$getShortenedUriString);
                }
            } else {
                throw new IllegalStateException("Uri is null.");
            }
        } else {
            FrescoSystrace.beginSection("ProducerSequenceFactory#getBasicDecodedImageSequence");
            try {
                Uri sourceUri2 = imageRequest.getSourceUri();
                Intrinsics.checkNotNullExpressionValue(sourceUri2, "getSourceUri(...)");
                if (sourceUri2 != null) {
                    int sourceUriType2 = imageRequest.getSourceUriType();
                    if (sourceUriType2 != 0) {
                        switch (sourceUriType2) {
                            case 2:
                                if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                                    producer = getLocalVideoFileFetchSequence();
                                    break;
                                } else {
                                    Producer localThumbnailBitmapSdk29FetchSequence = getLocalThumbnailBitmapSdk29FetchSequence();
                                    FrescoSystrace.endSection();
                                    return localThumbnailBitmapSdk29FetchSequence;
                                }
                            case 3:
                                if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                                    producer = getLocalImageFileFetchSequence();
                                    break;
                                } else {
                                    Producer localThumbnailBitmapSdk29FetchSequence2 = getLocalThumbnailBitmapSdk29FetchSequence();
                                    FrescoSystrace.endSection();
                                    return localThumbnailBitmapSdk29FetchSequence2;
                                }
                            case 4:
                                if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                                    if (!MediaUtils.isVideo(this.contentResolver.getType(sourceUri2))) {
                                        producer = getLocalContentUriFetchSequence();
                                        break;
                                    } else {
                                        Producer localVideoFileFetchSequence = getLocalVideoFileFetchSequence();
                                        FrescoSystrace.endSection();
                                        return localVideoFileFetchSequence;
                                    }
                                } else {
                                    Producer localThumbnailBitmapSdk29FetchSequence3 = getLocalThumbnailBitmapSdk29FetchSequence();
                                    FrescoSystrace.endSection();
                                    return localThumbnailBitmapSdk29FetchSequence3;
                                }
                            case 5:
                                producer = getLocalAssetFetchSequence();
                                break;
                            case 6:
                                producer = getLocalResourceFetchSequence();
                                break;
                            case 7:
                                producer = getDataFetchSequence();
                                break;
                            case 8:
                                producer = getQualifiedResourceFetchSequence();
                                break;
                            default:
                                Set set2 = this.customProducerSequenceFactories;
                                if (set2 != null) {
                                    Iterator it2 = set2.iterator();
                                    if (it2.hasNext()) {
                                        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it2.next());
                                        throw null;
                                    }
                                }
                                String access$getShortenedUriString2 = Companion.getShortenedUriString(sourceUri2);
                                throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + access$getShortenedUriString2);
                        }
                    } else {
                        producer = getNetworkFetchSequence();
                    }
                    return producer;
                }
                throw new IllegalStateException("Uri is null.");
            } finally {
                FrescoSystrace.endSection();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final RemoveImageTransformMetaDataProducer localContentUriFetchEncodedImageProducerSequence_delegate$lambda$6(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalContentUriFetchEncodedImageProducerSequence:init");
        try {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final RemoveImageTransformMetaDataProducer localFileFetchEncodedImageProducerSequence_delegate$lambda$4(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchEncodedImageProducerSequence:init");
        try {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final SwallowResultProducer localFileFetchToEncodedMemoryPrefetchSequence_delegate$lambda$20(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchToEncodedMemoryPrefetchSequence:init");
        try {
            return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final RemoveImageTransformMetaDataProducer networkFetchEncodedImageProducerSequence_delegate$lambda$2(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchEncodedImageProducerSequence:init");
        try {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final Producer networkFetchSequence_delegate$lambda$11(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchSequence:init");
        try {
            return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public static final SwallowResultProducer networkFetchToEncodedMemoryPrefetchSequence_delegate$lambda$15(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchToEncodedMemoryPrefetchSequence");
        try {
            return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }

    private final Producer newDiskCacheSequence(Producer producer) {
        DiskCacheWriteProducer diskCacheWriteProducer;
        DiskCacheWriteProducer diskCacheWriteProducer2;
        if (!FrescoSystrace.isTracing()) {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer = this.producerFactory.newPartialDiskCacheProducer(producer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer, "newPartialDiskCacheProducer(...)");
                diskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer);
            } else {
                diskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(producer);
            }
            Intrinsics.checkNotNull(diskCacheWriteProducer2);
            DiskCacheReadProducer newDiskCacheReadProducer = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducer2);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer, "newDiskCacheReadProducer(...)");
            return newDiskCacheReadProducer;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newDiskCacheSequence");
        try {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer2 = this.producerFactory.newPartialDiskCacheProducer(producer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer2, "newPartialDiskCacheProducer(...)");
                diskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer2);
            } else {
                diskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(producer);
            }
            Intrinsics.checkNotNull(diskCacheWriteProducer);
            DiskCacheReadProducer newDiskCacheReadProducer2 = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducer);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer2, "newDiskCacheReadProducer(...)");
            FrescoSystrace.endSection();
            return newDiskCacheReadProducer2;
        } catch (Throwable th) {
            FrescoSystrace.endSection();
            throw th;
        }
    }

    public final Producer getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        if (!FrescoSystrace.isTracing()) {
            Producer basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence = getPostprocessorSequence(basicDecodedImageSequence);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
            }
            if (!this.allowDelay || imageRequest.getDelayMs() <= 0) {
                return basicDecodedImageSequence;
            }
            return getDelaySequence(basicDecodedImageSequence);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getDecodedImageProducerSequence");
        try {
            Producer basicDecodedImageSequence2 = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence2 = getPostprocessorSequence(basicDecodedImageSequence2);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence2 = getBitmapPrepareSequence(basicDecodedImageSequence2);
            }
            if (this.allowDelay && imageRequest.getDelayMs() > 0) {
                basicDecodedImageSequence2 = getDelaySequence(basicDecodedImageSequence2);
            }
            FrescoSystrace.endSection();
            return basicDecodedImageSequence2;
        } catch (Throwable th) {
            FrescoSystrace.endSection();
            throw th;
        }
    }

    public final Producer newBitmapCacheGetToDecodeSequence(Producer producer) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        if (!FrescoSystrace.isTracing()) {
            DecodeProducer newDecodeProducer = this.producerFactory.newDecodeProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer, "newDecodeProducer(...)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newBitmapCacheGetToDecodeSequence");
        try {
            DecodeProducer newDecodeProducer2 = this.producerFactory.newDecodeProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer2, "newDecodeProducer(...)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer2);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Companion companion = Companion;
        companion.validateEncodedImageRequest(imageRequest);
        int sourceUriType = imageRequest.getSourceUriType();
        if (sourceUriType == 0) {
            return getNetworkFetchToEncodedMemoryPrefetchSequence();
        }
        if (sourceUriType == 2 || sourceUriType == 3) {
            return getLocalFileFetchToEncodedMemoryPrefetchSequence();
        }
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "getSourceUri(...)");
        String access$getShortenedUriString = companion.getShortenedUriString(sourceUri);
        throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + access$getShortenedUriString);
    }

    public final Producer getNetworkFetchSequence() {
        return (Producer) this.networkFetchSequence$delegate.getValue();
    }

    public final Producer getBackgroundNetworkFetchToEncodedMemorySequence() {
        Object value = this.backgroundNetworkFetchToEncodedMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Producer) value;
    }

    public final Producer getNetworkFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.networkFetchToEncodedMemoryPrefetchSequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Producer) value;
    }

    public final Producer getCommonNetworkFetchToEncodedMemorySequence() {
        return (Producer) this.commonNetworkFetchToEncodedMemorySequence$delegate.getValue();
    }

    /* JADX INFO: finally extract failed */
    public final synchronized Producer newCommonNetworkFetchToEncodedMemorySequence(NetworkFetcher networkFetcher2) {
        try {
            Intrinsics.checkNotNullParameter(networkFetcher2, "networkFetcher");
            boolean z = false;
            if (!FrescoSystrace.isTracing()) {
                Producer newNetworkFetchProducer = this.producerFactory.newNetworkFetchProducer(networkFetcher2);
                Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer, "newNetworkFetchProducer(...)");
                AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer));
                Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMetaDataProducer(...)");
                ProducerFactory producerFactory2 = this.producerFactory;
                if (this.resizeAndRotateEnabledForNetwork && this.downsampleMode != DownsampleMode.NEVER) {
                    z = true;
                }
                return producerFactory2.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer, z, this.imageTranscoderFactory);
            }
            FrescoSystrace.beginSection("ProducerSequenceFactory#createCommonNetworkFetchToEncodedMemorySequence");
            Producer newNetworkFetchProducer2 = this.producerFactory.newNetworkFetchProducer(networkFetcher2);
            Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer2, "newNetworkFetchProducer(...)");
            AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer2 = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer2));
            Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer2, "newAddImageTransformMetaDataProducer(...)");
            ProducerFactory producerFactory3 = this.producerFactory;
            if (this.resizeAndRotateEnabledForNetwork && this.downsampleMode != DownsampleMode.NEVER) {
                z = true;
            }
            ResizeAndRotateProducer newResizeAndRotateProducer = producerFactory3.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer2, z, this.imageTranscoderFactory);
            FrescoSystrace.endSection();
            return newResizeAndRotateProducer;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final Producer getLocalFileFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.localFileFetchToEncodedMemoryPrefetchSequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Producer) value;
    }

    public final Producer getBackgroundLocalFileFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalFileFetchToEncodeMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Producer) value;
    }

    public final Producer getBackgroundLocalContentUriFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalContentUriFetchToEncodeMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Producer) value;
    }

    public final Producer getLocalImageFileFetchSequence() {
        return (Producer) this.localImageFileFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localImageFileFetchSequence_delegate$lambda$25(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        LocalFileFetchProducer newLocalFileFetchProducer = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer, "newLocalFileFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToLocalTransformSequence(newLocalFileFetchProducer);
    }

    public final Producer getLocalVideoFileFetchSequence() {
        return (Producer) this.localVideoFileFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localVideoFileFetchSequence_delegate$lambda$26(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        LocalVideoThumbnailProducer newLocalVideoThumbnailProducer = producerSequenceFactory.producerFactory.newLocalVideoThumbnailProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalVideoThumbnailProducer, "newLocalVideoThumbnailProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToBitmapCacheSequence(newLocalVideoThumbnailProducer);
    }

    public final Producer getLocalContentUriFetchSequence() {
        return (Producer) this.localContentUriFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localContentUriFetchSequence_delegate$lambda$27(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        LocalContentUriFetchProducer newLocalContentUriFetchProducer = producerSequenceFactory.producerFactory.newLocalContentUriFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalContentUriFetchProducer, "newLocalContentUriFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToLocalTransformSequence(newLocalContentUriFetchProducer, new ThumbnailProducer[]{producerSequenceFactory.producerFactory.newLocalContentUriThumbnailFetchProducer(), producerSequenceFactory.producerFactory.newLocalExifThumbnailProducer()});
    }

    public final Producer getLocalThumbnailBitmapSdk29FetchSequence() {
        return (Producer) this.localThumbnailBitmapSdk29FetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localThumbnailBitmapSdk29FetchSequence_delegate$lambda$28(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        if (Build.VERSION.SDK_INT >= 29) {
            LocalThumbnailBitmapSdk29Producer newLocalThumbnailBitmapSdk29Producer = producerSequenceFactory.producerFactory.newLocalThumbnailBitmapSdk29Producer();
            Intrinsics.checkNotNullExpressionValue(newLocalThumbnailBitmapSdk29Producer, "newLocalThumbnailBitmapSdk29Producer(...)");
            return producerSequenceFactory.newBitmapCacheGetToBitmapCacheSequence(newLocalThumbnailBitmapSdk29Producer);
        }
        throw new Throwable("Unreachable exception. Just to make linter happy for the lazy block.");
    }

    public final Producer getQualifiedResourceFetchSequence() {
        return (Producer) this.qualifiedResourceFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer qualifiedResourceFetchSequence_delegate$lambda$29(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        QualifiedResourceFetchProducer newQualifiedResourceFetchProducer = producerSequenceFactory.producerFactory.newQualifiedResourceFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newQualifiedResourceFetchProducer, "newQualifiedResourceFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToLocalTransformSequence(newQualifiedResourceFetchProducer);
    }

    public final Producer getLocalResourceFetchSequence() {
        return (Producer) this.localResourceFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localResourceFetchSequence_delegate$lambda$30(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        LocalResourceFetchProducer newLocalResourceFetchProducer = producerSequenceFactory.producerFactory.newLocalResourceFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalResourceFetchProducer, "newLocalResourceFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToLocalTransformSequence(newLocalResourceFetchProducer);
    }

    public final Producer getLocalAssetFetchSequence() {
        return (Producer) this.localAssetFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer localAssetFetchSequence_delegate$lambda$31(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        LocalAssetFetchProducer newLocalAssetFetchProducer = producerSequenceFactory.producerFactory.newLocalAssetFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalAssetFetchProducer, "newLocalAssetFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToLocalTransformSequence(newLocalAssetFetchProducer);
    }

    public final Producer getDataFetchSequence() {
        return (Producer) this.dataFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Producer dataFetchSequence_delegate$lambda$32(ProducerSequenceFactory producerSequenceFactory) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "this$0");
        DataFetchProducer newDataFetchProducer = producerSequenceFactory.producerFactory.newDataFetchProducer();
        Intrinsics.checkNotNullExpressionValue(newDataFetchProducer, "newDataFetchProducer(...)");
        return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.producerFactory.newResizeAndRotateProducer(ProducerFactory.newAddImageTransformMetaDataProducer(newDataFetchProducer), true, producerSequenceFactory.imageTranscoderFactory));
    }

    private final Producer newBitmapCacheGetToLocalTransformSequence(Producer producer) {
        return newBitmapCacheGetToLocalTransformSequence(producer, new ThumbnailProducer[]{this.producerFactory.newLocalExifThumbnailProducer()});
    }

    private final Producer newBitmapCacheGetToLocalTransformSequence(Producer producer, ThumbnailProducer[] thumbnailProducerArr) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(producer), thumbnailProducerArr));
    }

    private final Producer newEncodedCacheMultiplexToTranscodeSequence(Producer producer) {
        if (this.diskCacheEnabled) {
            producer = newDiskCacheSequence(producer);
        }
        Producer newEncodedMemoryCacheProducer = this.producerFactory.newEncodedMemoryCacheProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newEncodedMemoryCacheProducer, "newEncodedMemoryCacheProducer(...)");
        if (this.isDiskCacheProbingEnabled) {
            EncodedProbeProducer newEncodedProbeProducer = this.producerFactory.newEncodedProbeProducer(newEncodedMemoryCacheProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedProbeProducer, "newEncodedProbeProducer(...)");
            EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedProbeProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer, "newEncodedCacheKeyMultiplexProducer(...)");
            return newEncodedCacheKeyMultiplexProducer;
        }
        EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer2 = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer2, "newEncodedCacheKeyMultiplexProducer(...)");
        return newEncodedCacheKeyMultiplexProducer2;
    }

    private final Producer newBitmapCacheGetToBitmapCacheSequence(Producer producer) {
        BitmapMemoryCacheProducer newBitmapMemoryCacheProducer = this.producerFactory.newBitmapMemoryCacheProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheProducer, "newBitmapMemoryCacheProducer(...)");
        BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer = this.producerFactory.newBitmapMemoryCacheKeyMultiplexProducer(newBitmapMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheKeyMultiplexProducer, "newBitmapMemoryCacheKeyMultiplexProducer(...)");
        Producer newBackgroundThreadHandoffProducer = this.producerFactory.newBackgroundThreadHandoffProducer(newBitmapMemoryCacheKeyMultiplexProducer, this.threadHandoffProducerQueue);
        Intrinsics.checkNotNullExpressionValue(newBackgroundThreadHandoffProducer, "newBackgroundThreadHandoffProducer(...)");
        if (this.isEncodedMemoryCacheProbingEnabled || this.isDiskCacheProbingEnabled) {
            BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer, "newBitmapMemoryCacheGetProducer(...)");
            BitmapProbeProducer newBitmapProbeProducer = this.producerFactory.newBitmapProbeProducer(newBitmapMemoryCacheGetProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapProbeProducer, "newBitmapProbeProducer(...)");
            return newBitmapProbeProducer;
        }
        BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer2 = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer2, "newBitmapMemoryCacheGetProducer(...)");
        return newBitmapMemoryCacheGetProducer2;
    }

    private final Producer newLocalTransformationsSequence(Producer producer, ThumbnailProducer[] thumbnailProducerArr) {
        AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMetaDataProducer(...)");
        ThrottlingProducer newThrottlingProducer = this.producerFactory.newThrottlingProducer(this.producerFactory.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer, true, this.imageTranscoderFactory));
        Intrinsics.checkNotNullExpressionValue(newThrottlingProducer, "newThrottlingProducer(...)");
        BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer = ProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducerArr), newThrottlingProducer);
        Intrinsics.checkNotNullExpressionValue(newBranchOnSeparateImagesProducer, "newBranchOnSeparateImagesProducer(...)");
        return newBranchOnSeparateImagesProducer;
    }

    private final Producer newLocalThumbnailProducer(ThumbnailProducer[] thumbnailProducerArr) {
        ThumbnailBranchProducer newThumbnailBranchProducer = this.producerFactory.newThumbnailBranchProducer(thumbnailProducerArr);
        Intrinsics.checkNotNullExpressionValue(newThumbnailBranchProducer, "newThumbnailBranchProducer(...)");
        ResizeAndRotateProducer newResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(newThumbnailBranchProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "newResizeAndRotateProducer(...)");
        return newResizeAndRotateProducer;
    }

    private final synchronized Producer getPostprocessorSequence(Producer producer) {
        Producer producer2;
        producer2 = (Producer) this.postprocessorSequences.get(producer);
        if (producer2 == null) {
            PostprocessorProducer newPostprocessorProducer = this.producerFactory.newPostprocessorProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newPostprocessorProducer, "newPostprocessorProducer(...)");
            producer2 = this.producerFactory.newPostprocessorBitmapMemoryCacheProducer(newPostprocessorProducer);
            this.postprocessorSequences.put(producer, producer2);
        }
        return producer2;
    }

    private final synchronized Producer getBitmapPrepareSequence(Producer producer) {
        Producer producer2;
        producer2 = (Producer) this.bitmapPrepareSequences.get(producer);
        if (producer2 == null) {
            producer2 = this.producerFactory.newBitmapPrepareProducer(producer);
            this.bitmapPrepareSequences.put(producer, producer2);
        }
        return producer2;
    }

    private final synchronized Producer getDelaySequence(Producer producer) {
        DelayProducer newDelayProducer;
        newDelayProducer = this.producerFactory.newDelayProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newDelayProducer, "newDelayProducer(...)");
        return newDelayProducer;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void validateEncodedImageRequest(ImageRequest imageRequest) {
            Preconditions.checkArgument(Boolean.valueOf(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()));
        }

        /* access modifiers changed from: private */
        public final String getShortenedUriString(Uri uri) {
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
            if (uri2.length() <= 30) {
                return uri2;
            }
            String substring = uri2.substring(0, 30);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring + "...";
        }
    }
}
