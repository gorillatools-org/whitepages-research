package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.DownsampleUtil;
import com.facebook.imageutils.BitmapUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DecodeProducer implements Producer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ByteArrayPool byteArrayPool;
    private final CloseableReferenceFactory closeableReferenceFactory;
    private final boolean decodeCancellationEnabled;
    private final boolean downsampleEnabledForNetwork;
    private final DownsampleMode downsampleMode;
    private final Executor executor;
    private final ImageDecoder imageDecoder;
    private final Producer inputProducer;
    private final int maxBitmapDimension;
    private final ProgressiveJpegConfig progressiveJpegConfig;
    private final Runnable reclaimMemoryRunnable;
    private final Supplier recoverFromDecoderOOM;

    private abstract class ProgressiveDecoder extends DelegatingConsumer {
        private final String TAG = "ProgressiveDecoder";
        private final ImageDecodeOptions imageDecodeOptions;
        private boolean isFinished;
        /* access modifiers changed from: private */
        public final JobScheduler jobScheduler;
        private int lastScheduledScanNumber;
        /* access modifiers changed from: private */
        public final ProducerContext producerContext;
        private final ProducerListener2 producerListener;
        final /* synthetic */ DecodeProducer this$0;

        /* access modifiers changed from: protected */
        public abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        /* access modifiers changed from: protected */
        public abstract QualityInfo getQualityInfo();

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (!FrescoSystrace.isTracing()) {
                boolean isLast = BaseConsumer.isLast(i);
                if (isLast) {
                    if (encodedImage == null) {
                        boolean areEqual = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) Boolean.TRUE);
                        if (!this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() || this.producerContext.getLowestPermittedRequestLevel() == ImageRequest.RequestLevel.FULL_FETCH || areEqual) {
                            handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                            return;
                        }
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        return;
                    }
                }
                if (updateDecodeJob(encodedImage, i)) {
                    boolean statusHasFlag = BaseConsumer.statusHasFlag(i, 4);
                    if (isLast || statusHasFlag || this.producerContext.isIntermediateResultExpected()) {
                        this.jobScheduler.scheduleJob();
                        return;
                    }
                    return;
                }
                return;
            }
            FrescoSystrace.beginSection("DecodeProducer#onNewResultImpl");
            try {
                boolean isLast2 = BaseConsumer.isLast(i);
                if (isLast2) {
                    if (encodedImage == null) {
                        boolean areEqual2 = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) Boolean.TRUE);
                        if (this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() && this.producerContext.getLowestPermittedRequestLevel() != ImageRequest.RequestLevel.FULL_FETCH) {
                            if (areEqual2) {
                            }
                        }
                        handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                        FrescoSystrace.endSection();
                        return;
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        FrescoSystrace.endSection();
                        return;
                    }
                }
                if (!updateDecodeJob(encodedImage, i)) {
                    FrescoSystrace.endSection();
                    return;
                }
                boolean statusHasFlag2 = BaseConsumer.statusHasFlag(i, 4);
                if (isLast2 || statusHasFlag2 || this.producerContext.isIntermediateResultExpected()) {
                    this.jobScheduler.scheduleJob();
                }
                Unit unit = Unit.INSTANCE;
                FrescoSystrace.endSection();
            } catch (Throwable th) {
                FrescoSystrace.endSection();
                throw th;
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ProgressiveDecoder(DecodeProducer decodeProducer, Consumer consumer, ProducerContext producerContext2, final boolean z, int i) {
            super(consumer);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext2, "producerContext");
            this.this$0 = decodeProducer;
            this.producerContext = producerContext2;
            this.producerListener = producerContext2.getProducerListener();
            ImageDecodeOptions imageDecodeOptions2 = producerContext2.getImageRequest().getImageDecodeOptions();
            Intrinsics.checkNotNullExpressionValue(imageDecodeOptions2, "getImageDecodeOptions(...)");
            this.imageDecodeOptions = imageDecodeOptions2;
            this.jobScheduler = new JobScheduler(decodeProducer.getExecutor(), new DecodeProducer$ProgressiveDecoder$$ExternalSyntheticLambda0(this, decodeProducer, i), imageDecodeOptions2.minDecodeIntervalMs);
            producerContext2.addCallbacks(new BaseProducerContextCallbacks(this) {
                final /* synthetic */ ProgressiveDecoder this$0;

                {
                    this.this$0 = r1;
                }

                public void onIsIntermediateResultExpectedChanged() {
                    if (this.this$0.producerContext.isIntermediateResultExpected()) {
                        this.this$0.jobScheduler.scheduleJob();
                    }
                }

                public void onCancellationRequested() {
                    if (z) {
                        this.this$0.handleCancellation();
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public final int getLastScheduledScanNumber() {
            return this.lastScheduledScanNumber;
        }

        /* access modifiers changed from: protected */
        public final void setLastScheduledScanNumber(int i) {
            this.lastScheduledScanNumber = i;
        }

        private final void maybeIncreaseSampleSize(EncodedImage encodedImage) {
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                encodedImage.setSampleSize(DownsampleUtil.determineSampleSizeJPEG(encodedImage, BitmapUtil.getPixelSizeForBitmapConfig(this.imageDecodeOptions.bitmapConfig), 104857600));
            }
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdateImpl(float f) {
            super.onProgressUpdateImpl(f * 0.99f);
        }

        public void onFailureImpl(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "t");
            handleError(th);
        }

        public void onCancellationImpl() {
            handleCancellation();
        }

        /* access modifiers changed from: protected */
        public boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            return this.jobScheduler.updateJob(encodedImage, i);
        }

        /* JADX WARNING: Removed duplicated region for block: B:39:0x011c A[Catch:{ all -> 0x0113 }] */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x013a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void doDecode(com.facebook.imagepipeline.image.EncodedImage r18, int r19, int r20) {
            /*
                r17 = this;
                r11 = r17
                r12 = r18
                r0 = r19
                com.facebook.imageformat.ImageFormat r1 = r18.getImageFormat()
                com.facebook.imageformat.ImageFormat r2 = com.facebook.imageformat.DefaultImageFormats.JPEG
                if (r1 == r2) goto L_0x0015
                boolean r1 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r19)
                if (r1 == 0) goto L_0x0015
                return
            L_0x0015:
                boolean r1 = r11.isFinished
                if (r1 != 0) goto L_0x01a0
                boolean r1 = com.facebook.imagepipeline.image.EncodedImage.isValid(r18)
                if (r1 != 0) goto L_0x0021
                goto L_0x01a0
            L_0x0021:
                com.facebook.imageformat.ImageFormat r1 = r18.getImageFormat()
                com.facebook.imageformat.ImageFormat r2 = com.facebook.imageformat.DefaultImageFormats.GIF
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
                r2 = 0
                java.lang.String r13 = "DecodeProducer"
                if (r1 == 0) goto L_0x007c
                com.facebook.imagepipeline.producers.DecodeProducer$Companion r1 = com.facebook.imagepipeline.producers.DecodeProducer.Companion
                com.facebook.imagepipeline.common.ImageDecodeOptions r3 = r11.imageDecodeOptions
                boolean r1 = r1.isTooBig(r12, r3)
                if (r1 == 0) goto L_0x007c
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                int r1 = r18.getWidth()
                int r3 = r18.getHeight()
                com.facebook.imagepipeline.common.ImageDecodeOptions r4 = r11.imageDecodeOptions
                android.graphics.Bitmap$Config r4 = r4.bitmapConfig
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "Image is too big to attempt decoding: w = "
                r5.append(r6)
                r5.append(r1)
                java.lang.String r1 = ", h = "
                r5.append(r1)
                r5.append(r3)
                java.lang.String r1 = ", pixel config = "
                r5.append(r1)
                r5.append(r4)
                java.lang.String r1 = ", max bitmap size = 104857600"
                r5.append(r1)
                java.lang.String r1 = r5.toString()
                r0.<init>(r1)
                com.facebook.imagepipeline.producers.ProducerListener2 r1 = r11.producerListener
                com.facebook.imagepipeline.producers.ProducerContext r3 = r11.producerContext
                r1.onProducerFinishWithFailure(r3, r13, r0, r2)
                r11.handleError(r0)
                return
            L_0x007c:
                com.facebook.imageformat.ImageFormat r1 = r18.getImageFormat()
                java.lang.String r3 = "getImageFormat(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                java.lang.String r1 = r1.getName()
                java.lang.String r3 = "unknown"
                if (r1 != 0) goto L_0x008f
                r7 = r3
                goto L_0x0090
            L_0x008f:
                r7 = r1
            L_0x0090:
                int r1 = r18.getWidth()
                int r4 = r18.getHeight()
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                r5.append(r1)
                java.lang.String r1 = "x"
                r5.append(r1)
                r5.append(r4)
                java.lang.String r8 = r5.toString()
                int r4 = r18.getSampleSize()
                java.lang.String r10 = java.lang.String.valueOf(r4)
                boolean r6 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r19)
                r4 = 1
                if (r6 == 0) goto L_0x00c5
                r5 = 8
                boolean r5 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r0, r5)
                if (r5 != 0) goto L_0x00c5
                r5 = r4
                goto L_0x00c6
            L_0x00c5:
                r5 = 0
            L_0x00c6:
                r9 = 4
                boolean r9 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r0, r9)
                com.facebook.imagepipeline.producers.ProducerContext r14 = r11.producerContext
                com.facebook.imagepipeline.request.ImageRequest r14 = r14.getImageRequest()
                com.facebook.imagepipeline.common.ResizeOptions r14 = r14.getResizeOptions()
                if (r14 == 0) goto L_0x00ef
                int r3 = r14.width
                int r14 = r14.height
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                r15.<init>()
                r15.append(r3)
                r15.append(r1)
                r15.append(r14)
                java.lang.String r1 = r15.toString()
                r14 = r1
                goto L_0x00f0
            L_0x00ef:
                r14 = r3
            L_0x00f0:
                com.facebook.imagepipeline.producers.JobScheduler r1 = r11.jobScheduler     // Catch:{ all -> 0x0113 }
                long r15 = r1.getQueuedTime()     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerContext r1 = r11.producerContext     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.request.ImageRequest r1 = r1.getImageRequest()     // Catch:{ all -> 0x0113 }
                android.net.Uri r1 = r1.getSourceUri()     // Catch:{ all -> 0x0113 }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0113 }
                java.lang.String r3 = "toString(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)     // Catch:{ all -> 0x0113 }
                if (r5 != 0) goto L_0x0116
                if (r9 == 0) goto L_0x010e
                goto L_0x0116
            L_0x010e:
                int r3 = r17.getIntermediateImageEndOffset(r18)     // Catch:{ all -> 0x0113 }
                goto L_0x011a
            L_0x0113:
                r0 = move-exception
                goto L_0x019c
            L_0x0116:
                int r3 = r18.getSize()     // Catch:{ all -> 0x0113 }
            L_0x011a:
                if (r5 != 0) goto L_0x0124
                if (r9 == 0) goto L_0x011f
                goto L_0x0124
            L_0x011f:
                com.facebook.imagepipeline.image.QualityInfo r5 = r17.getQualityInfo()     // Catch:{ all -> 0x0113 }
                goto L_0x0126
            L_0x0124:
                com.facebook.imagepipeline.image.QualityInfo r5 = com.facebook.imagepipeline.image.ImmutableQualityInfo.FULL_QUALITY     // Catch:{ all -> 0x0113 }
            L_0x0126:
                com.facebook.imagepipeline.producers.ProducerListener2 r9 = r11.producerListener     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerContext r2 = r11.producerContext     // Catch:{ all -> 0x0113 }
                r9.onProducerStart(r2, r13)     // Catch:{ all -> 0x0113 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ DecodeException -> 0x0160 }
                com.facebook.imagepipeline.image.CloseableImage r9 = r11.internalDecode(r12, r3, r5)     // Catch:{ DecodeException -> 0x0160 }
                int r1 = r18.getSampleSize()     // Catch:{ Exception -> 0x0159 }
                if (r1 == r4) goto L_0x013c
                r0 = r0 | 16
            L_0x013c:
                r1 = r17
                r2 = r9
                r3 = r15
                r15 = r9
                r9 = r14
                java.util.Map r1 = r1.getExtraMap(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerListener2 r2 = r11.producerListener     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = r11.producerContext     // Catch:{ all -> 0x0113 }
                r2.onProducerFinishWithSuccess(r3, r13, r1)     // Catch:{ all -> 0x0113 }
                r1 = r20
                r11.setImageExtras(r12, r15, r1)     // Catch:{ all -> 0x0113 }
                r11.handleResult(r15, r0)     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r18)
                return
            L_0x0159:
                r0 = move-exception
                r1 = r9
                r2 = r1
                goto L_0x0183
            L_0x015d:
                r0 = move-exception
                r2 = 0
                goto L_0x0183
            L_0x0160:
                r0 = move-exception
                com.facebook.imagepipeline.image.EncodedImage r2 = r0.getEncodedImage()     // Catch:{ Exception -> 0x015d }
                java.lang.String r3 = r11.TAG     // Catch:{ Exception -> 0x015d }
                java.lang.String r4 = "%s, {uri: %s, firstEncodedBytes: %s, length: %d}"
                java.lang.String r9 = r0.getMessage()     // Catch:{ Exception -> 0x015d }
                r12 = 10
                java.lang.String r12 = r2.getFirstBytesAsHexString(r12)     // Catch:{ Exception -> 0x015d }
                int r2 = r2.getSize()     // Catch:{ Exception -> 0x015d }
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x015d }
                java.lang.Object[] r1 = new java.lang.Object[]{r9, r1, r12, r2}     // Catch:{ Exception -> 0x015d }
                com.facebook.common.logging.FLog.w((java.lang.String) r3, (java.lang.String) r4, (java.lang.Object[]) r1)     // Catch:{ Exception -> 0x015d }
                throw r0     // Catch:{ Exception -> 0x015d }
            L_0x0183:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x0113 }
                r1 = r17
                r3 = r15
                r9 = r14
                java.util.Map r1 = r1.getExtraMap(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerListener2 r2 = r11.producerListener     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = r11.producerContext     // Catch:{ all -> 0x0113 }
                r2.onProducerFinishWithFailure(r3, r13, r0, r1)     // Catch:{ all -> 0x0113 }
                r11.handleError(r0)     // Catch:{ all -> 0x0113 }
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r18)
                return
            L_0x019c:
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r18)
                throw r0
            L_0x01a0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.doDecode(com.facebook.imagepipeline.image.EncodedImage, int, int):void");
        }

        private final CloseableImage internalDecode(EncodedImage encodedImage, int i, QualityInfo qualityInfo) {
            boolean z = this.this$0.getReclaimMemoryRunnable() != null && ((Boolean) this.this$0.getRecoverFromDecoderOOM().get()).booleanValue();
            try {
                return this.this$0.getImageDecoder().decode(encodedImage, i, qualityInfo, this.imageDecodeOptions);
            } catch (OutOfMemoryError e) {
                if (z) {
                    Runnable reclaimMemoryRunnable = this.this$0.getReclaimMemoryRunnable();
                    if (reclaimMemoryRunnable != null) {
                        reclaimMemoryRunnable.run();
                    }
                    System.gc();
                    return this.this$0.getImageDecoder().decode(encodedImage, i, qualityInfo, this.imageDecodeOptions);
                }
                throw e;
            }
        }

        private final void setImageExtras(EncodedImage encodedImage, CloseableImage closeableImage, int i) {
            this.producerContext.putExtra("encoded_width", Integer.valueOf(encodedImage.getWidth()));
            this.producerContext.putExtra("encoded_height", Integer.valueOf(encodedImage.getHeight()));
            this.producerContext.putExtra("encoded_size", Integer.valueOf(encodedImage.getSize()));
            this.producerContext.putExtra("image_color_space", encodedImage.getColorSpace());
            if (closeableImage instanceof CloseableBitmap) {
                this.producerContext.putExtra("bitmap_config", String.valueOf(((CloseableBitmap) closeableImage).getUnderlyingBitmap().getConfig()));
            }
            if (closeableImage != null) {
                closeableImage.putExtras(this.producerContext.getExtras());
            }
            this.producerContext.putExtra("last_scan_num", Integer.valueOf(i));
        }

        private final Map getExtraMap(CloseableImage closeableImage, long j, QualityInfo qualityInfo, boolean z, String str, String str2, String str3, String str4) {
            Map extras;
            Object obj;
            CloseableImage closeableImage2 = closeableImage;
            String str5 = str;
            String str6 = str2;
            String str7 = str3;
            String str8 = str4;
            String str9 = null;
            if (!this.producerListener.requiresExtraMap(this.producerContext, "DecodeProducer")) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
            String valueOf3 = String.valueOf(z);
            if (!(closeableImage2 == null || (extras = closeableImage.getExtras()) == null || (obj = extras.get("non_fatal_decode_error")) == null)) {
                str9 = obj.toString();
            }
            Object obj2 = "non_fatal_decode_error";
            String str10 = str9;
            if (closeableImage2 instanceof CloseableStaticBitmap) {
                Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage2).getUnderlyingBitmap();
                Intrinsics.checkNotNullExpressionValue(underlyingBitmap, "getUnderlyingBitmap(...)");
                Bitmap bitmap = underlyingBitmap;
                String str11 = underlyingBitmap.getWidth() + "x" + underlyingBitmap.getHeight();
                HashMap hashMap = new HashMap(8);
                hashMap.put("bitmapSize", str11);
                hashMap.put("queueTime", valueOf);
                hashMap.put("hasGoodQuality", valueOf2);
                hashMap.put("isFinal", valueOf3);
                hashMap.put("encodedImageSize", str6);
                hashMap.put("imageFormat", str5);
                hashMap.put("requestedImageSize", str7);
                hashMap.put("sampleSize", str4);
                int byteCount = bitmap.getByteCount();
                StringBuilder sb = new StringBuilder();
                sb.append(byteCount);
                hashMap.put("byteCount", sb.toString());
                if (str10 != null) {
                    hashMap.put(obj2, str10);
                }
                return ImmutableMap.copyOf(hashMap);
            }
            String str12 = str8;
            String str13 = str10;
            HashMap hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put("hasGoodQuality", valueOf2);
            hashMap2.put("isFinal", valueOf3);
            hashMap2.put("encodedImageSize", str6);
            hashMap2.put("imageFormat", str5);
            hashMap2.put("requestedImageSize", str7);
            hashMap2.put("sampleSize", str12);
            if (str10 != null) {
                hashMap2.put(obj2, str10);
            }
            return ImmutableMap.copyOf(hashMap2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void maybeFinish(boolean r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                if (r2 == 0) goto L_0x0020
                boolean r2 = r1.isFinished     // Catch:{ all -> 0x001d }
                if (r2 == 0) goto L_0x0008
                goto L_0x0020
            L_0x0008:
                com.facebook.imagepipeline.producers.Consumer r2 = r1.getConsumer()     // Catch:{ all -> 0x001d }
                r0 = 1065353216(0x3f800000, float:1.0)
                r2.onProgressUpdate(r0)     // Catch:{ all -> 0x001d }
                r2 = 1
                r1.isFinished = r2     // Catch:{ all -> 0x001d }
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001d }
                monitor-exit(r1)
                com.facebook.imagepipeline.producers.JobScheduler r2 = r1.jobScheduler
                r2.clearJob()
                return
            L_0x001d:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            L_0x0020:
                monitor-exit(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.maybeFinish(boolean):void");
        }

        private final void handleResult(CloseableImage closeableImage, int i) {
            CloseableReference create = this.this$0.getCloseableReferenceFactory().create(closeableImage);
            try {
                maybeFinish(BaseConsumer.isLast(i));
                getConsumer().onNewResult(create, i);
            } finally {
                CloseableReference.closeSafely(create);
            }
        }

        private final void handleError(Throwable th) {
            maybeFinish(true);
            getConsumer().onFailure(th);
        }

        /* access modifiers changed from: private */
        public final void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$2(ProgressiveDecoder progressiveDecoder, DecodeProducer decodeProducer, int i, EncodedImage encodedImage, int i2) {
            Intrinsics.checkNotNullParameter(progressiveDecoder, "this$0");
            Intrinsics.checkNotNullParameter(decodeProducer, "this$1");
            if (encodedImage != null) {
                ImageRequest imageRequest = progressiveDecoder.producerContext.getImageRequest();
                progressiveDecoder.producerContext.putExtra("image_format", encodedImage.getImageFormat().getName());
                Uri sourceUri = imageRequest.getSourceUri();
                encodedImage.setSource(sourceUri != null ? sourceUri.toString() : null);
                DownsampleMode downsampleOverride = imageRequest.getDownsampleOverride();
                if (downsampleOverride == null) {
                    downsampleOverride = decodeProducer.getDownsampleMode();
                }
                boolean statusHasFlag = BaseConsumer.statusHasFlag(i2, 16);
                if ((downsampleOverride == DownsampleMode.ALWAYS || (downsampleOverride == DownsampleMode.AUTO && !statusHasFlag)) && (decodeProducer.getDownsampleEnabledForNetwork() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()))) {
                    RotationOptions rotationOptions = imageRequest.getRotationOptions();
                    Intrinsics.checkNotNullExpressionValue(rotationOptions, "getRotationOptions(...)");
                    encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(rotationOptions, imageRequest.getResizeOptions(), encodedImage, i));
                }
                if (progressiveDecoder.producerContext.getImagePipelineConfig().getExperiments().getDownsampleIfLargeBitmap()) {
                    progressiveDecoder.maybeIncreaseSampleSize(encodedImage);
                }
                progressiveDecoder.doDecode(encodedImage, i2, progressiveDecoder.lastScheduledScanNumber);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void produceResults(com.facebook.imagepipeline.producers.Consumer r11, com.facebook.imagepipeline.producers.ProducerContext r12) {
        /*
            r10 = this;
            java.lang.String r1 = "consumer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r1)
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            boolean r1 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r1 != 0) goto L_0x0053
            com.facebook.imagepipeline.request.ImageRequest r1 = r12.getImageRequest()
            android.net.Uri r2 = r1.getSourceUri()
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)
            if (r2 != 0) goto L_0x0036
            android.net.Uri r1 = r1.getSourceUri()
            boolean r1 = com.facebook.imagepipeline.request.ImageRequestBuilder.isCustomNetworkUri(r1)
            if (r1 != 0) goto L_0x0036
            com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder r7 = new com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder
            boolean r5 = r10.decodeCancellationEnabled
            int r6 = r10.maxBitmapDimension
            r1 = r7
            r2 = r10
            r3 = r11
            r4 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x004d
        L_0x0036:
            com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = new com.facebook.imagepipeline.decoder.ProgressiveJpegParser
            com.facebook.common.memory.ByteArrayPool r1 = r10.byteArrayPool
            r5.<init>(r1)
            com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder r9 = new com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder
            com.facebook.imagepipeline.decoder.ProgressiveJpegConfig r6 = r10.progressiveJpegConfig
            boolean r7 = r10.decodeCancellationEnabled
            int r8 = r10.maxBitmapDimension
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r7 = r9
        L_0x004d:
            com.facebook.imagepipeline.producers.Producer r1 = r10.inputProducer
            r1.produceResults(r7, r12)
            goto L_0x00a1
        L_0x0053:
            java.lang.String r1 = "DecodeProducer#produceResults"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r1)
            com.facebook.imagepipeline.request.ImageRequest r1 = r12.getImageRequest()     // Catch:{ all -> 0x007e }
            android.net.Uri r2 = r1.getSourceUri()     // Catch:{ all -> 0x007e }
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)     // Catch:{ all -> 0x007e }
            if (r2 != 0) goto L_0x0080
            android.net.Uri r1 = r1.getSourceUri()     // Catch:{ all -> 0x007e }
            boolean r1 = com.facebook.imagepipeline.request.ImageRequestBuilder.isCustomNetworkUri(r1)     // Catch:{ all -> 0x007e }
            if (r1 != 0) goto L_0x0080
            com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder r7 = new com.facebook.imagepipeline.producers.DecodeProducer$LocalImagesProgressiveDecoder     // Catch:{ all -> 0x007e }
            boolean r5 = r10.decodeCancellationEnabled     // Catch:{ all -> 0x007e }
            int r6 = r10.maxBitmapDimension     // Catch:{ all -> 0x007e }
            r1 = r7
            r2 = r10
            r3 = r11
            r4 = r12
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x007e }
            goto L_0x0097
        L_0x007e:
            r0 = move-exception
            goto L_0x00a2
        L_0x0080:
            com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = new com.facebook.imagepipeline.decoder.ProgressiveJpegParser     // Catch:{ all -> 0x007e }
            com.facebook.common.memory.ByteArrayPool r1 = r10.byteArrayPool     // Catch:{ all -> 0x007e }
            r5.<init>(r1)     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder r9 = new com.facebook.imagepipeline.producers.DecodeProducer$NetworkImagesProgressiveDecoder     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.decoder.ProgressiveJpegConfig r6 = r10.progressiveJpegConfig     // Catch:{ all -> 0x007e }
            boolean r7 = r10.decodeCancellationEnabled     // Catch:{ all -> 0x007e }
            int r8 = r10.maxBitmapDimension     // Catch:{ all -> 0x007e }
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x007e }
            r7 = r9
        L_0x0097:
            com.facebook.imagepipeline.producers.Producer r1 = r10.inputProducer     // Catch:{ all -> 0x007e }
            r1.produceResults(r7, r12)     // Catch:{ all -> 0x007e }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x00a1:
            return
        L_0x00a2:
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.produceResults(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }

    public DecodeProducer(ByteArrayPool byteArrayPool2, Executor executor2, ImageDecoder imageDecoder2, ProgressiveJpegConfig progressiveJpegConfig2, DownsampleMode downsampleMode2, boolean z, boolean z2, Producer producer, int i, CloseableReferenceFactory closeableReferenceFactory2, Runnable runnable, Supplier supplier) {
        Intrinsics.checkNotNullParameter(byteArrayPool2, "byteArrayPool");
        Intrinsics.checkNotNullParameter(executor2, "executor");
        Intrinsics.checkNotNullParameter(imageDecoder2, "imageDecoder");
        Intrinsics.checkNotNullParameter(progressiveJpegConfig2, "progressiveJpegConfig");
        Intrinsics.checkNotNullParameter(downsampleMode2, "downsampleMode");
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory2, "closeableReferenceFactory");
        Intrinsics.checkNotNullParameter(supplier, "recoverFromDecoderOOM");
        this.byteArrayPool = byteArrayPool2;
        this.executor = executor2;
        this.imageDecoder = imageDecoder2;
        this.progressiveJpegConfig = progressiveJpegConfig2;
        this.downsampleMode = downsampleMode2;
        this.downsampleEnabledForNetwork = z;
        this.decodeCancellationEnabled = z2;
        this.inputProducer = producer;
        this.maxBitmapDimension = i;
        this.closeableReferenceFactory = closeableReferenceFactory2;
        this.reclaimMemoryRunnable = runnable;
        this.recoverFromDecoderOOM = supplier;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final ImageDecoder getImageDecoder() {
        return this.imageDecoder;
    }

    public final DownsampleMode getDownsampleMode() {
        return this.downsampleMode;
    }

    public final boolean getDownsampleEnabledForNetwork() {
        return this.downsampleEnabledForNetwork;
    }

    public final CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.closeableReferenceFactory;
    }

    public final Runnable getReclaimMemoryRunnable() {
        return this.reclaimMemoryRunnable;
    }

    public final Supplier getRecoverFromDecoderOOM() {
        return this.recoverFromDecoderOOM;
    }

    private final class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        final /* synthetic */ DecodeProducer this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LocalImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer consumer, ProducerContext producerContext, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            this.this$0 = decodeProducer;
        }

        /* access modifiers changed from: protected */
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            boolean z;
            if (BaseConsumer.isNotLast(i)) {
                z = false;
            } else {
                z = super.updateDecodeJob(encodedImage, i);
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return encodedImage.getSize();
        }

        /* access modifiers changed from: protected */
        public QualityInfo getQualityInfo() {
            QualityInfo of = ImmutableQualityInfo.of(0, false, false);
            Intrinsics.checkNotNullExpressionValue(of, "of(...)");
            return of;
        }
    }

    private final class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private final ProgressiveJpegConfig progressiveJpegConfig;
        private final ProgressiveJpegParser progressiveJpegParser;
        final /* synthetic */ DecodeProducer this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NetworkImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser2, ProgressiveJpegConfig progressiveJpegConfig2, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            Intrinsics.checkNotNullParameter(progressiveJpegParser2, "progressiveJpegParser");
            Intrinsics.checkNotNullParameter(progressiveJpegConfig2, "progressiveJpegConfig");
            this.this$0 = decodeProducer;
            this.progressiveJpegParser = progressiveJpegParser2;
            this.progressiveJpegConfig = progressiveJpegConfig2;
            setLastScheduledScanNumber(0);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0062, code lost:
            return r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean updateDecodeJob(com.facebook.imagepipeline.image.EncodedImage r4, int r5) {
            /*
                r3 = this;
                monitor-enter(r3)
                r0 = 0
                if (r4 != 0) goto L_0x0006
                monitor-exit(r3)
                return r0
            L_0x0006:
                boolean r1 = super.updateDecodeJob(r4, r5)     // Catch:{ all -> 0x0019 }
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r5)     // Catch:{ all -> 0x0019 }
                if (r2 != 0) goto L_0x001b
                r2 = 8
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r5, r2)     // Catch:{ all -> 0x0019 }
                if (r2 == 0) goto L_0x0061
                goto L_0x001b
            L_0x0019:
                r4 = move-exception
                goto L_0x0063
            L_0x001b:
                r2 = 4
                boolean r5 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r5, r2)     // Catch:{ all -> 0x0019 }
                if (r5 != 0) goto L_0x0061
                boolean r5 = com.facebook.imagepipeline.image.EncodedImage.isValid(r4)     // Catch:{ all -> 0x0019 }
                if (r5 == 0) goto L_0x0061
                com.facebook.imageformat.ImageFormat r5 = r4.getImageFormat()     // Catch:{ all -> 0x0019 }
                com.facebook.imageformat.ImageFormat r2 = com.facebook.imageformat.DefaultImageFormats.JPEG     // Catch:{ all -> 0x0019 }
                if (r5 != r2) goto L_0x0061
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = r3.progressiveJpegParser     // Catch:{ all -> 0x0019 }
                boolean r4 = r5.parseMoreData(r4)     // Catch:{ all -> 0x0019 }
                if (r4 != 0) goto L_0x003a
                monitor-exit(r3)
                return r0
            L_0x003a:
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r4 = r3.progressiveJpegParser     // Catch:{ all -> 0x0019 }
                int r4 = r4.getBestScanNumber()     // Catch:{ all -> 0x0019 }
                int r5 = r3.getLastScheduledScanNumber()     // Catch:{ all -> 0x0019 }
                if (r4 > r5) goto L_0x0048
                monitor-exit(r3)
                return r0
            L_0x0048:
                com.facebook.imagepipeline.decoder.ProgressiveJpegConfig r5 = r3.progressiveJpegConfig     // Catch:{ all -> 0x0019 }
                int r2 = r3.getLastScheduledScanNumber()     // Catch:{ all -> 0x0019 }
                int r5 = r5.getNextScanNumberToDecode(r2)     // Catch:{ all -> 0x0019 }
                if (r4 >= r5) goto L_0x005e
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = r3.progressiveJpegParser     // Catch:{ all -> 0x0019 }
                boolean r5 = r5.isEndMarkerRead()     // Catch:{ all -> 0x0019 }
                if (r5 != 0) goto L_0x005e
                monitor-exit(r3)
                return r0
            L_0x005e:
                r3.setLastScheduledScanNumber(r4)     // Catch:{ all -> 0x0019 }
            L_0x0061:
                monitor-exit(r3)
                return r1
            L_0x0063:
                monitor-exit(r3)     // Catch:{ all -> 0x0019 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.NetworkImagesProgressiveDecoder.updateDecodeJob(com.facebook.imagepipeline.image.EncodedImage, int):boolean");
        }

        /* access modifiers changed from: protected */
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return this.progressiveJpegParser.getBestScanEndOffset();
        }

        /* access modifiers changed from: protected */
        public QualityInfo getQualityInfo() {
            QualityInfo qualityInfo = this.progressiveJpegConfig.getQualityInfo(this.progressiveJpegParser.getBestScanNumber());
            Intrinsics.checkNotNullExpressionValue(qualityInfo, "getQualityInfo(...)");
            return qualityInfo;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean isTooBig(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
            return (((long) encodedImage.getWidth()) * ((long) encodedImage.getHeight())) * ((long) BitmapUtil.getPixelSizeForBitmapConfig(imageDecodeOptions.bitmapConfig)) > 104857600;
        }
    }
}
