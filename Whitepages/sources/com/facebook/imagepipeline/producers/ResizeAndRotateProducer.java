package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.transcoder.ImageTranscodeResult;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class ResizeAndRotateProducer implements Producer {
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    private final ImageTranscoderFactory mImageTranscoderFactory;
    private final Producer mInputProducer;
    private final boolean mIsResizingEnabled;
    /* access modifiers changed from: private */
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    public ResizeAndRotateProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer producer, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mImageTranscoderFactory = (ImageTranscoderFactory) Preconditions.checkNotNull(imageTranscoderFactory);
        this.mIsResizingEnabled = z;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, producerContext, this.mIsResizingEnabled, this.mImageTranscoderFactory), producerContext);
    }

    private class TransformingConsumer extends DelegatingConsumer {
        /* access modifiers changed from: private */
        public final ImageTranscoderFactory mImageTranscoderFactory;
        /* access modifiers changed from: private */
        public boolean mIsCancelled = false;
        /* access modifiers changed from: private */
        public final boolean mIsResizingEnabled;
        /* access modifiers changed from: private */
        public final JobScheduler mJobScheduler;
        /* access modifiers changed from: private */
        public final ProducerContext mProducerContext;

        TransformingConsumer(final Consumer consumer, ProducerContext producerContext, boolean z, ImageTranscoderFactory imageTranscoderFactory) {
            super(consumer);
            this.mProducerContext = producerContext;
            Boolean resizingAllowedOverride = producerContext.getImageRequest().getResizingAllowedOverride();
            this.mIsResizingEnabled = resizingAllowedOverride != null ? resizingAllowedOverride.booleanValue() : z;
            this.mImageTranscoderFactory = imageTranscoderFactory;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new JobScheduler.JobRunnable(ResizeAndRotateProducer.this) {
                public void run(EncodedImage encodedImage, int i) {
                    if (encodedImage != null) {
                        TransformingConsumer transformingConsumer = TransformingConsumer.this;
                        transformingConsumer.doTransform(encodedImage, i, (ImageTranscoder) Preconditions.checkNotNull(transformingConsumer.mImageTranscoderFactory.createImageTranscoder(encodedImage.getImageFormat(), TransformingConsumer.this.mIsResizingEnabled)));
                        return;
                    }
                    TransformingConsumer.this.getConsumer().onNewResult((Object) null, i);
                }
            }, 100);
            producerContext.addCallbacks(new BaseProducerContextCallbacks(ResizeAndRotateProducer.this) {
                public void onIsIntermediateResultExpectedChanged() {
                    if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                        TransformingConsumer.this.mJobScheduler.scheduleJob();
                    }
                }

                public void onCancellationRequested() {
                    TransformingConsumer.this.mJobScheduler.clearJob();
                    TransformingConsumer.this.mIsCancelled = true;
                    consumer.onCancellation();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (!this.mIsCancelled) {
                boolean isLast = BaseConsumer.isLast(i);
                if (encodedImage != null) {
                    ImageFormat imageFormat = encodedImage.getImageFormat();
                    TriState r2 = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), encodedImage, (ImageTranscoder) Preconditions.checkNotNull(this.mImageTranscoderFactory.createImageTranscoder(imageFormat, this.mIsResizingEnabled)));
                    if (!isLast && r2 == TriState.UNSET) {
                        return;
                    }
                    if (r2 != TriState.YES) {
                        forwardNewResult(encodedImage, i, imageFormat);
                    } else if (this.mJobScheduler.updateJob(encodedImage, i)) {
                        if (isLast || this.mProducerContext.isIntermediateResultExpected()) {
                            this.mJobScheduler.scheduleJob();
                        }
                    }
                } else if (isLast) {
                    getConsumer().onNewResult((Object) null, 1);
                }
            }
        }

        private void forwardNewResult(EncodedImage encodedImage, int i, ImageFormat imageFormat) {
            EncodedImage encodedImage2;
            if (imageFormat == DefaultImageFormats.JPEG || imageFormat == DefaultImageFormats.HEIF) {
                encodedImage2 = getNewResultsForJpegOrHeif(encodedImage);
            } else {
                encodedImage2 = getNewResultForImagesWithoutExifData(encodedImage);
            }
            getConsumer().onNewResult(encodedImage2, i);
        }

        private EncodedImage getNewResultForImagesWithoutExifData(EncodedImage encodedImage) {
            RotationOptions rotationOptions = this.mProducerContext.getImageRequest().getRotationOptions();
            return (rotationOptions.useImageMetadata() || !rotationOptions.rotationEnabled()) ? encodedImage : getCloneWithRotationApplied(encodedImage, rotationOptions.getForcedAngle());
        }

        private EncodedImage getNewResultsForJpegOrHeif(EncodedImage encodedImage) {
            return (this.mProducerContext.getImageRequest().getRotationOptions().canDeferUntilRendered() || encodedImage.getRotationAngle() == 0 || encodedImage.getRotationAngle() == -1) ? encodedImage : getCloneWithRotationApplied(encodedImage, 0);
        }

        private EncodedImage getCloneWithRotationApplied(EncodedImage encodedImage, int i) {
            EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
            if (cloneOrNull != null) {
                cloneOrNull.setRotationAngle(i);
            }
            return cloneOrNull;
        }

        /* access modifiers changed from: private */
        public void doTransform(EncodedImage encodedImage, int i, ImageTranscoder imageTranscoder) {
            CloseableReference of;
            EncodedImage encodedImage2;
            this.mProducerContext.getProducerListener().onProducerStart(this.mProducerContext, "ResizeAndRotateProducer");
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            PooledByteBufferOutputStream newOutputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.newOutputStream();
            Map map = null;
            try {
                ImageTranscodeResult transcode = imageTranscoder.transcode(encodedImage, newOutputStream, imageRequest.getRotationOptions(), imageRequest.getResizeOptions(), (ImageFormat) null, 85, encodedImage.getColorSpace());
                if (transcode.getTranscodeStatus() != 2) {
                    map = getExtraMap(encodedImage, imageRequest.getResizeOptions(), transcode, imageTranscoder.getIdentifier());
                    of = CloseableReference.of(newOutputStream.toByteBuffer());
                    encodedImage2 = new EncodedImage(of);
                    encodedImage2.setImageFormat(DefaultImageFormats.JPEG);
                    encodedImage2.parseMetaData();
                    this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, "ResizeAndRotateProducer", map);
                    if (transcode.getTranscodeStatus() != 1) {
                        i |= 16;
                    }
                    getConsumer().onNewResult(encodedImage2, i);
                    EncodedImage.closeSafely(encodedImage2);
                    CloseableReference.closeSafely(of);
                    newOutputStream.close();
                    return;
                }
                throw new RuntimeException("Error while transcoding the image");
            } catch (Exception e) {
                this.mProducerContext.getProducerListener().onProducerFinishWithFailure(this.mProducerContext, "ResizeAndRotateProducer", e, map);
                if (BaseConsumer.isLast(i)) {
                    getConsumer().onFailure(e);
                }
                newOutputStream.close();
            } catch (Throwable th) {
                newOutputStream.close();
                throw th;
            }
        }

        private Map getExtraMap(EncodedImage encodedImage, ResizeOptions resizeOptions, ImageTranscodeResult imageTranscodeResult, String str) {
            String str2;
            if (!this.mProducerContext.getProducerListener().requiresExtraMap(this.mProducerContext, "ResizeAndRotateProducer")) {
                return null;
            }
            String str3 = encodedImage.getWidth() + "x" + encodedImage.getHeight();
            if (resizeOptions != null) {
                str2 = resizeOptions.width + "x" + resizeOptions.height;
            } else {
                str2 = "Unspecified";
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Image format", String.valueOf(encodedImage.getImageFormat()));
            hashMap.put("Original size", str3);
            hashMap.put("Requested size", str2);
            hashMap.put("queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
            hashMap.put("Transcoder id", str);
            hashMap.put("Transcoding result", String.valueOf(imageTranscodeResult));
            return ImmutableMap.copyOf(hashMap);
        }
    }

    /* access modifiers changed from: private */
    public static TriState shouldTransform(ImageRequest imageRequest, EncodedImage encodedImage, ImageTranscoder imageTranscoder) {
        if (encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        if (!imageTranscoder.canTranscode(encodedImage.getImageFormat())) {
            return TriState.NO;
        }
        return TriState.valueOf(shouldRotate(imageRequest.getRotationOptions(), encodedImage) || imageTranscoder.canResize(encodedImage, imageRequest.getRotationOptions(), imageRequest.getResizeOptions()));
    }

    private static boolean shouldRotate(RotationOptions rotationOptions, EncodedImage encodedImage) {
        return !rotationOptions.canDeferUntilRendered() && (JpegTranscoderUtils.getRotationAngle(rotationOptions, encodedImage) != 0 || shouldRotateUsingExifOrientation(rotationOptions, encodedImage));
    }

    private static boolean shouldRotateUsingExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (rotationOptions.rotationEnabled() && !rotationOptions.canDeferUntilRendered()) {
            return JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()));
        }
        encodedImage.setExifOrientation(0);
        return false;
    }
}
