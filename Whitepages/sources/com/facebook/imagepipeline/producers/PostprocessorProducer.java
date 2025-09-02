package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.Map;
import java.util.concurrent.Executor;

public class PostprocessorProducer implements Producer {
    /* access modifiers changed from: private */
    public final PlatformBitmapFactory mBitmapFactory;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    private final Producer mInputProducer;

    public PostprocessorProducer(Producer producer, PlatformBitmapFactory platformBitmapFactory, Executor executor) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mBitmapFactory = platformBitmapFactory;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        Postprocessor postprocessor = producerContext.getImageRequest().getPostprocessor();
        Preconditions.checkNotNull(postprocessor);
        this.mInputProducer.produceResults(new SingleUsePostprocessorConsumer(new PostprocessorConsumer(consumer, producerListener, postprocessor, producerContext)), producerContext);
    }

    private class PostprocessorConsumer extends DelegatingConsumer {
        private boolean mIsClosed;
        /* access modifiers changed from: private */
        public boolean mIsDirty = false;
        private boolean mIsPostProcessingRunning = false;
        private final ProducerListener2 mListener;
        private final Postprocessor mPostprocessor;
        private final ProducerContext mProducerContext;
        /* access modifiers changed from: private */
        public CloseableReference mSourceImageRef = null;
        /* access modifiers changed from: private */
        public int mStatus = 0;

        public PostprocessorConsumer(Consumer consumer, ProducerListener2 producerListener2, Postprocessor postprocessor, ProducerContext producerContext) {
            super(consumer);
            this.mListener = producerListener2;
            this.mPostprocessor = postprocessor;
            this.mProducerContext = producerContext;
            producerContext.addCallbacks(new BaseProducerContextCallbacks(PostprocessorProducer.this) {
                public void onCancellationRequested() {
                    PostprocessorConsumer.this.maybeNotifyOnCancellation();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(CloseableReference closeableReference, int i) {
            if (CloseableReference.isValid(closeableReference)) {
                updateSourceImageRef(closeableReference, i);
            } else if (BaseConsumer.isLast(i)) {
                maybeNotifyOnNewResult((CloseableReference) null, i);
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            maybeNotifyOnFailure(th);
        }

        /* access modifiers changed from: protected */
        public void onCancellationImpl() {
            maybeNotifyOnCancellation();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            com.facebook.common.references.CloseableReference.closeSafely(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            if (r2 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
            submitPostprocessing();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void updateSourceImageRef(com.facebook.common.references.CloseableReference r2, int r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mIsClosed     // Catch:{ all -> 0x0007 }
                if (r0 == 0) goto L_0x0009
                monitor-exit(r1)     // Catch:{ all -> 0x0007 }
                return
            L_0x0007:
                r2 = move-exception
                goto L_0x0024
            L_0x0009:
                com.facebook.common.references.CloseableReference r0 = r1.mSourceImageRef     // Catch:{ all -> 0x0007 }
                com.facebook.common.references.CloseableReference r2 = com.facebook.common.references.CloseableReference.cloneOrNull(r2)     // Catch:{ all -> 0x0007 }
                r1.mSourceImageRef = r2     // Catch:{ all -> 0x0007 }
                r1.mStatus = r3     // Catch:{ all -> 0x0007 }
                r2 = 1
                r1.mIsDirty = r2     // Catch:{ all -> 0x0007 }
                boolean r2 = r1.setRunningIfDirtyAndNotRunning()     // Catch:{ all -> 0x0007 }
                monitor-exit(r1)     // Catch:{ all -> 0x0007 }
                com.facebook.common.references.CloseableReference.closeSafely(r0)
                if (r2 == 0) goto L_0x0023
                r1.submitPostprocessing()
            L_0x0023:
                return
            L_0x0024:
                monitor-exit(r1)     // Catch:{ all -> 0x0007 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.updateSourceImageRef(com.facebook.common.references.CloseableReference, int):void");
        }

        private void submitPostprocessing() {
            PostprocessorProducer.this.mExecutor.execute(new Runnable() {
                public void run() {
                    CloseableReference r1;
                    int r2;
                    synchronized (PostprocessorConsumer.this) {
                        r1 = PostprocessorConsumer.this.mSourceImageRef;
                        r2 = PostprocessorConsumer.this.mStatus;
                        PostprocessorConsumer.this.mSourceImageRef = null;
                        PostprocessorConsumer.this.mIsDirty = false;
                    }
                    if (CloseableReference.isValid(r1)) {
                        try {
                            PostprocessorConsumer.this.doPostprocessing(r1, r2);
                        } finally {
                            CloseableReference.closeSafely(r1);
                        }
                    }
                    PostprocessorConsumer.this.clearRunningAndStartIfDirty();
                }
            });
        }

        /* access modifiers changed from: private */
        public void clearRunningAndStartIfDirty() {
            boolean runningIfDirtyAndNotRunning;
            synchronized (this) {
                this.mIsPostProcessingRunning = false;
                runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
            }
            if (runningIfDirtyAndNotRunning) {
                submitPostprocessing();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized boolean setRunningIfDirtyAndNotRunning() {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mIsClosed     // Catch:{ all -> 0x001a }
                if (r0 != 0) goto L_0x001c
                boolean r0 = r1.mIsDirty     // Catch:{ all -> 0x001a }
                if (r0 == 0) goto L_0x001c
                boolean r0 = r1.mIsPostProcessingRunning     // Catch:{ all -> 0x001a }
                if (r0 != 0) goto L_0x001c
                com.facebook.common.references.CloseableReference r0 = r1.mSourceImageRef     // Catch:{ all -> 0x001a }
                boolean r0 = com.facebook.common.references.CloseableReference.isValid(r0)     // Catch:{ all -> 0x001a }
                if (r0 == 0) goto L_0x001c
                r0 = 1
                r1.mIsPostProcessingRunning = r0     // Catch:{ all -> 0x001a }
                monitor-exit(r1)
                return r0
            L_0x001a:
                r0 = move-exception
                goto L_0x001f
            L_0x001c:
                monitor-exit(r1)
                r0 = 0
                return r0
            L_0x001f:
                monitor-exit(r1)     // Catch:{ all -> 0x001a }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.setRunningIfDirtyAndNotRunning():boolean");
        }

        /* access modifiers changed from: private */
        public void doPostprocessing(CloseableReference closeableReference, int i) {
            Preconditions.checkArgument(Boolean.valueOf(CloseableReference.isValid(closeableReference)));
            if (!shouldPostprocess((CloseableImage) closeableReference.get())) {
                maybeNotifyOnNewResult(closeableReference, i);
                return;
            }
            this.mListener.onProducerStart(this.mProducerContext, "PostprocessorProducer");
            CloseableReference closeableReference2 = null;
            try {
                closeableReference2 = postprocessInternal((CloseableImage) closeableReference.get());
                ProducerListener2 producerListener2 = this.mListener;
                ProducerContext producerContext = this.mProducerContext;
                producerListener2.onProducerFinishWithSuccess(producerContext, "PostprocessorProducer", getExtraMap(producerListener2, producerContext, this.mPostprocessor));
                maybeNotifyOnNewResult(closeableReference2, i);
            } catch (Exception e) {
                ProducerListener2 producerListener22 = this.mListener;
                ProducerContext producerContext2 = this.mProducerContext;
                producerListener22.onProducerFinishWithFailure(producerContext2, "PostprocessorProducer", e, getExtraMap(producerListener22, producerContext2, this.mPostprocessor));
                maybeNotifyOnFailure(e);
            } finally {
                CloseableReference.closeSafely(closeableReference2);
            }
        }

        private Map getExtraMap(ProducerListener2 producerListener2, ProducerContext producerContext, Postprocessor postprocessor) {
            if (!producerListener2.requiresExtraMap(producerContext, "PostprocessorProducer")) {
                return null;
            }
            return ImmutableMap.of("Postprocessor", postprocessor.getName());
        }

        private boolean shouldPostprocess(CloseableImage closeableImage) {
            return closeableImage instanceof CloseableStaticBitmap;
        }

        private CloseableReference postprocessInternal(CloseableImage closeableImage) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            CloseableReference process = this.mPostprocessor.process(closeableStaticBitmap.getUnderlyingBitmap(), PostprocessorProducer.this.mBitmapFactory);
            try {
                CloseableStaticBitmap of = CloseableStaticBitmap.of(process, closeableImage.getQualityInfo(), closeableStaticBitmap.getRotationAngle(), closeableStaticBitmap.getExifOrientation());
                of.putExtras(closeableStaticBitmap.getExtras());
                return CloseableReference.of(of);
            } finally {
                CloseableReference.closeSafely(process);
            }
        }

        private void maybeNotifyOnNewResult(CloseableReference closeableReference, int i) {
            boolean isLast = BaseConsumer.isLast(i);
            if ((!isLast && !isClosed()) || (isLast && close())) {
                getConsumer().onNewResult(closeableReference, i);
            }
        }

        private void maybeNotifyOnFailure(Throwable th) {
            if (close()) {
                getConsumer().onFailure(th);
            }
        }

        /* access modifiers changed from: private */
        public void maybeNotifyOnCancellation() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        private synchronized boolean isClosed() {
            return this.mIsClosed;
        }

        private boolean close() {
            synchronized (this) {
                try {
                    if (this.mIsClosed) {
                        return false;
                    }
                    CloseableReference closeableReference = this.mSourceImageRef;
                    this.mSourceImageRef = null;
                    this.mIsClosed = true;
                    CloseableReference.closeSafely(closeableReference);
                    return true;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
    }

    class SingleUsePostprocessorConsumer extends DelegatingConsumer {
        private SingleUsePostprocessorConsumer(PostprocessorConsumer postprocessorConsumer) {
            super(postprocessorConsumer);
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(CloseableReference closeableReference, int i) {
            if (!BaseConsumer.isNotLast(i)) {
                getConsumer().onNewResult(closeableReference, i);
            }
        }
    }
}
