package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class MultiplexProducer implements Producer {
    /* access modifiers changed from: private */
    public final String mDedupedRequestsCountKey;
    /* access modifiers changed from: private */
    public final Producer mInputProducer;
    /* access modifiers changed from: private */
    public final boolean mKeepCancelledFetchAsLowPriority;
    final Map mMultiplexers;
    /* access modifiers changed from: private */
    public final String mProducerName;

    /* access modifiers changed from: protected */
    public abstract Closeable cloneOrNull(Closeable closeable);

    /* access modifiers changed from: protected */
    public abstract Object getKey(ProducerContext producerContext);

    protected MultiplexProducer(Producer producer, String str, String str2) {
        this(producer, str, str2, false);
    }

    protected MultiplexProducer(Producer producer, String str, String str2, boolean z) {
        this.mInputProducer = producer;
        this.mMultiplexers = new HashMap();
        this.mKeepCancelledFetchAsLowPriority = z;
        this.mProducerName = str;
        this.mDedupedRequestsCountKey = str2;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        Multiplexer existingMultiplexer;
        boolean z;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("MultiplexProducer#produceResults");
            }
            producerContext.getProducerListener().onProducerStart(producerContext, this.mProducerName);
            Object key = getKey(producerContext);
            do {
                synchronized (this) {
                    existingMultiplexer = getExistingMultiplexer(key);
                    if (existingMultiplexer == null) {
                        existingMultiplexer = createAndPutNewMultiplexer(key);
                        z = true;
                    } else {
                        z = false;
                    }
                }
            } while (!existingMultiplexer.addNewConsumer(consumer, producerContext));
            if (z) {
                existingMultiplexer.startInputProducerIfHasAttachedConsumers(TriState.valueOf(producerContext.isPrefetch()));
            }
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized Multiplexer getExistingMultiplexer(Object obj) {
        return (Multiplexer) this.mMultiplexers.get(obj);
    }

    private synchronized Multiplexer createAndPutNewMultiplexer(Object obj) {
        Multiplexer multiplexer;
        multiplexer = new Multiplexer(obj);
        this.mMultiplexers.put(obj, multiplexer);
        return multiplexer;
    }

    /* access modifiers changed from: protected */
    public synchronized void removeMultiplexer(Object obj, Multiplexer multiplexer) {
        if (this.mMultiplexers.get(obj) == multiplexer) {
            this.mMultiplexers.remove(obj);
        }
    }

    class Multiplexer {
        /* access modifiers changed from: private */
        public final CopyOnWriteArraySet mConsumerContextPairs = Sets.newCopyOnWriteArraySet();
        private ForwardingConsumer mForwardingConsumer;
        private final Object mKey;
        private Closeable mLastIntermediateResult;
        private float mLastProgress;
        private int mLastStatus;
        /* access modifiers changed from: private */
        public BaseProducerContext mMultiplexProducerContext;

        public Multiplexer(Object obj) {
            this.mKey = obj;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsPrefetchChanged(r1);
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnPriorityChanged(r2);
            com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsIntermediateResultExpectedChanged(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
            if (r4 == r7.mLastIntermediateResult) goto L_0x003d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
            r4 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
            if (r4 == null) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
            r4 = r7.this$0.cloneOrNull(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
            if (r4 == null) goto L_0x005c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
            if (r5 <= 0.0f) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            r8.onProgressUpdate(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0056, code lost:
            r8.onNewResult(r4, r6);
            closeSafely(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x005c, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
            addCallbacks(r0, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0061, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            throw r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0065, code lost:
            throw r8;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean addNewConsumer(com.facebook.imagepipeline.producers.Consumer r8, com.facebook.imagepipeline.producers.ProducerContext r9) {
            /*
                r7 = this;
                android.util.Pair r0 = android.util.Pair.create(r8, r9)
                monitor-enter(r7)
                com.facebook.imagepipeline.producers.MultiplexProducer r1 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0012 }
                java.lang.Object r2 = r7.mKey     // Catch:{ all -> 0x0012 }
                com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer r1 = r1.getExistingMultiplexer(r2)     // Catch:{ all -> 0x0012 }
                if (r1 == r7) goto L_0x0014
                monitor-exit(r7)     // Catch:{ all -> 0x0012 }
                r8 = 0
                return r8
            L_0x0012:
                r8 = move-exception
                goto L_0x0066
            L_0x0014:
                java.util.concurrent.CopyOnWriteArraySet r1 = r7.mConsumerContextPairs     // Catch:{ all -> 0x0012 }
                r1.add(r0)     // Catch:{ all -> 0x0012 }
                java.util.List r1 = r7.updateIsPrefetch()     // Catch:{ all -> 0x0012 }
                java.util.List r2 = r7.updatePriority()     // Catch:{ all -> 0x0012 }
                java.util.List r3 = r7.updateIsIntermediateResultExpected()     // Catch:{ all -> 0x0012 }
                java.io.Closeable r4 = r7.mLastIntermediateResult     // Catch:{ all -> 0x0012 }
                float r5 = r7.mLastProgress     // Catch:{ all -> 0x0012 }
                int r6 = r7.mLastStatus     // Catch:{ all -> 0x0012 }
                monitor-exit(r7)     // Catch:{ all -> 0x0012 }
                com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsPrefetchChanged(r1)
                com.facebook.imagepipeline.producers.BaseProducerContext.callOnPriorityChanged(r2)
                com.facebook.imagepipeline.producers.BaseProducerContext.callOnIsIntermediateResultExpectedChanged(r3)
                monitor-enter(r0)
                monitor-enter(r7)     // Catch:{ all -> 0x0054 }
                java.io.Closeable r1 = r7.mLastIntermediateResult     // Catch:{ all -> 0x0046 }
                if (r4 == r1) goto L_0x003d
                r4 = 0
                goto L_0x0048
            L_0x003d:
                if (r4 == 0) goto L_0x0048
                com.facebook.imagepipeline.producers.MultiplexProducer r1 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0046 }
                java.io.Closeable r4 = r1.cloneOrNull(r4)     // Catch:{ all -> 0x0046 }
                goto L_0x0048
            L_0x0046:
                r8 = move-exception
                goto L_0x0062
            L_0x0048:
                monitor-exit(r7)     // Catch:{ all -> 0x0046 }
                if (r4 == 0) goto L_0x005c
                r1 = 0
                int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                if (r1 <= 0) goto L_0x0056
                r8.onProgressUpdate(r5)     // Catch:{ all -> 0x0054 }
                goto L_0x0056
            L_0x0054:
                r8 = move-exception
                goto L_0x0064
            L_0x0056:
                r8.onNewResult(r4, r6)     // Catch:{ all -> 0x0054 }
                r7.closeSafely(r4)     // Catch:{ all -> 0x0054 }
            L_0x005c:
                monitor-exit(r0)     // Catch:{ all -> 0x0054 }
                r7.addCallbacks(r0, r9)
                r8 = 1
                return r8
            L_0x0062:
                monitor-exit(r7)     // Catch:{ all -> 0x0046 }
                throw r8     // Catch:{ all -> 0x0054 }
            L_0x0064:
                monitor-exit(r0)     // Catch:{ all -> 0x0054 }
                throw r8
            L_0x0066:
                monitor-exit(r7)     // Catch:{ all -> 0x0012 }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.addNewConsumer(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):boolean");
        }

        private void addCallbacks(final Pair pair, ProducerContext producerContext) {
            producerContext.addCallbacks(new BaseProducerContextCallbacks() {
                public void onCancellationRequested() {
                    boolean remove;
                    List list;
                    List list2;
                    List list3;
                    BaseProducerContext baseProducerContext;
                    synchronized (Multiplexer.this) {
                        try {
                            remove = Multiplexer.this.mConsumerContextPairs.remove(pair);
                            list = null;
                            if (!remove) {
                                baseProducerContext = null;
                                list3 = null;
                            } else if (Multiplexer.this.mConsumerContextPairs.isEmpty()) {
                                baseProducerContext = Multiplexer.this.mMultiplexProducerContext;
                                list3 = null;
                            } else {
                                List r3 = Multiplexer.this.updateIsPrefetch();
                                list3 = Multiplexer.this.updatePriority();
                                list2 = Multiplexer.this.updateIsIntermediateResultExpected();
                                List list4 = r3;
                                baseProducerContext = null;
                                list = list4;
                            }
                            list2 = list3;
                        } catch (Throwable th) {
                            while (true) {
                                throw th;
                            }
                        }
                    }
                    BaseProducerContext.callOnIsPrefetchChanged(list);
                    BaseProducerContext.callOnPriorityChanged(list3);
                    BaseProducerContext.callOnIsIntermediateResultExpectedChanged(list2);
                    if (baseProducerContext != null) {
                        if (!MultiplexProducer.this.mKeepCancelledFetchAsLowPriority || baseProducerContext.isPrefetch()) {
                            baseProducerContext.cancel();
                        } else {
                            BaseProducerContext.callOnPriorityChanged(baseProducerContext.setPriorityNoCallbacks(Priority.LOW));
                        }
                    }
                    if (remove) {
                        ((Consumer) pair.first).onCancellation();
                    }
                }

                public void onIsPrefetchChanged() {
                    BaseProducerContext.callOnIsPrefetchChanged(Multiplexer.this.updateIsPrefetch());
                }

                public void onIsIntermediateResultExpectedChanged() {
                    BaseProducerContext.callOnIsIntermediateResultExpectedChanged(Multiplexer.this.updateIsIntermediateResultExpected());
                }

                public void onPriorityChanged() {
                    BaseProducerContext.callOnPriorityChanged(Multiplexer.this.updatePriority());
                }
            });
        }

        /* access modifiers changed from: private */
        public void startInputProducerIfHasAttachedConsumers(TriState triState) {
            synchronized (this) {
                try {
                    boolean z = false;
                    Preconditions.checkArgument(Boolean.valueOf(this.mMultiplexProducerContext == null));
                    if (this.mForwardingConsumer == null) {
                        z = true;
                    }
                    Preconditions.checkArgument(Boolean.valueOf(z));
                    if (this.mConsumerContextPairs.isEmpty()) {
                        MultiplexProducer.this.removeMultiplexer(this.mKey, this);
                        return;
                    }
                    ProducerContext producerContext = (ProducerContext) ((Pair) this.mConsumerContextPairs.iterator().next()).second;
                    BaseProducerContext baseProducerContext = new BaseProducerContext(producerContext.getImageRequest(), producerContext.getId(), producerContext.getProducerListener(), producerContext.getCallerContext(), producerContext.getLowestPermittedRequestLevel(), computeIsPrefetch(), computeIsIntermediateResultExpected(), computePriority(), producerContext.getImagePipelineConfig());
                    this.mMultiplexProducerContext = baseProducerContext;
                    baseProducerContext.putExtras(producerContext.getExtras());
                    if (triState.isSet()) {
                        this.mMultiplexProducerContext.putExtra("started_as_prefetch", Boolean.valueOf(triState.asBoolean()));
                    }
                    ForwardingConsumer forwardingConsumer = new ForwardingConsumer();
                    this.mForwardingConsumer = forwardingConsumer;
                    BaseProducerContext baseProducerContext2 = this.mMultiplexProducerContext;
                    MultiplexProducer.this.mInputProducer.produceResults(forwardingConsumer, baseProducerContext2);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public synchronized List updateIsPrefetch() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            if (baseProducerContext == null) {
                return null;
            }
            return baseProducerContext.setIsPrefetchNoCallbacks(computeIsPrefetch());
        }

        private synchronized boolean computeIsPrefetch() {
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (!((ProducerContext) ((Pair) it.next()).second).isPrefetch()) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: private */
        public synchronized List updateIsIntermediateResultExpected() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            if (baseProducerContext == null) {
                return null;
            }
            return baseProducerContext.setIsIntermediateResultExpectedNoCallbacks(computeIsIntermediateResultExpected());
        }

        private synchronized boolean computeIsIntermediateResultExpected() {
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                if (((ProducerContext) ((Pair) it.next()).second).isIntermediateResultExpected()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public synchronized List updatePriority() {
            BaseProducerContext baseProducerContext = this.mMultiplexProducerContext;
            if (baseProducerContext == null) {
                return null;
            }
            return baseProducerContext.setPriorityNoCallbacks(computePriority());
        }

        private synchronized Priority computePriority() {
            Priority priority;
            priority = Priority.LOW;
            Iterator it = this.mConsumerContextPairs.iterator();
            while (it.hasNext()) {
                priority = Priority.getHigherPriority(priority, ((ProducerContext) ((Pair) it.next()).second).getPriority());
            }
            return priority;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
            if (r6.hasNext() == false) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
            r1 = (android.util.Pair) r6.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            ((com.facebook.imagepipeline.producers.ProducerContext) r1.second).getProducerListener().onProducerFinishWithFailure((com.facebook.imagepipeline.producers.ProducerContext) r1.second, com.facebook.imagepipeline.producers.MultiplexProducer.m171$$Nest$fgetmProducerName(r5.this$0), r7, (java.util.Map) null);
            r2 = r5.mMultiplexProducerContext;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
            if (r2 == null) goto L_0x0058;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
            ((com.facebook.imagepipeline.producers.ProducerContext) r1.second).putExtras(r2.getExtras());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
            ((com.facebook.imagepipeline.producers.Consumer) r1.first).onFailure(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
            throw r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onFailure(com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.ForwardingConsumer r6, java.lang.Throwable r7) {
            /*
                r5 = this;
                monitor-enter(r5)
                com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer r0 = r5.mForwardingConsumer     // Catch:{ all -> 0x0007 }
                if (r0 == r6) goto L_0x0009
                monitor-exit(r5)     // Catch:{ all -> 0x0007 }
                return
            L_0x0007:
                r6 = move-exception
                goto L_0x0064
            L_0x0009:
                java.util.concurrent.CopyOnWriteArraySet r6 = r5.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0007 }
                java.util.concurrent.CopyOnWriteArraySet r0 = r5.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                r0.clear()     // Catch:{ all -> 0x0007 }
                com.facebook.imagepipeline.producers.MultiplexProducer r0 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0007 }
                java.lang.Object r1 = r5.mKey     // Catch:{ all -> 0x0007 }
                r0.removeMultiplexer(r1, r5)     // Catch:{ all -> 0x0007 }
                java.io.Closeable r0 = r5.mLastIntermediateResult     // Catch:{ all -> 0x0007 }
                r5.closeSafely(r0)     // Catch:{ all -> 0x0007 }
                r0 = 0
                r5.mLastIntermediateResult = r0     // Catch:{ all -> 0x0007 }
                monitor-exit(r5)     // Catch:{ all -> 0x0007 }
            L_0x0024:
                boolean r1 = r6.hasNext()
                if (r1 == 0) goto L_0x0063
                java.lang.Object r1 = r6.next()
                android.util.Pair r1 = (android.util.Pair) r1
                monitor-enter(r1)
                java.lang.Object r2 = r1.second     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.ProducerContext r2 = (com.facebook.imagepipeline.producers.ProducerContext) r2     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.ProducerListener2 r2 = r2.getProducerListener()     // Catch:{ all -> 0x0056 }
                java.lang.Object r3 = r1.second     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = (com.facebook.imagepipeline.producers.ProducerContext) r3     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.MultiplexProducer r4 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0056 }
                java.lang.String r4 = r4.mProducerName     // Catch:{ all -> 0x0056 }
                r2.onProducerFinishWithFailure(r3, r4, r7, r0)     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.BaseProducerContext r2 = r5.mMultiplexProducerContext     // Catch:{ all -> 0x0056 }
                if (r2 == 0) goto L_0x0058
                java.lang.Object r3 = r1.second     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = (com.facebook.imagepipeline.producers.ProducerContext) r3     // Catch:{ all -> 0x0056 }
                java.util.Map r2 = r2.getExtras()     // Catch:{ all -> 0x0056 }
                r3.putExtras(r2)     // Catch:{ all -> 0x0056 }
                goto L_0x0058
            L_0x0056:
                r6 = move-exception
                goto L_0x0061
            L_0x0058:
                java.lang.Object r2 = r1.first     // Catch:{ all -> 0x0056 }
                com.facebook.imagepipeline.producers.Consumer r2 = (com.facebook.imagepipeline.producers.Consumer) r2     // Catch:{ all -> 0x0056 }
                r2.onFailure(r7)     // Catch:{ all -> 0x0056 }
                monitor-exit(r1)     // Catch:{ all -> 0x0056 }
                goto L_0x0024
            L_0x0061:
                monitor-exit(r1)     // Catch:{ all -> 0x0056 }
                throw r6
            L_0x0063:
                return
            L_0x0064:
                monitor-exit(r5)     // Catch:{ all -> 0x0007 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onFailure(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.lang.Throwable):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
            if (r0.hasNext() == false) goto L_0x0092;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
            r2 = (android.util.Pair) r0.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0048, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
            if (com.facebook.imagepipeline.producers.BaseConsumer.isLast(r9) == false) goto L_0x0087;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x004f, code lost:
            ((com.facebook.imagepipeline.producers.ProducerContext) r2.second).getProducerListener().onProducerFinishWithSuccess((com.facebook.imagepipeline.producers.ProducerContext) r2.second, com.facebook.imagepipeline.producers.MultiplexProducer.m171$$Nest$fgetmProducerName(r6.this$0), (java.util.Map) null);
            r3 = r6.mMultiplexProducerContext;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
            if (r3 == null) goto L_0x0076;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0068, code lost:
            ((com.facebook.imagepipeline.producers.ProducerContext) r2.second).putExtras(r3.getExtras());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0074, code lost:
            r7 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
            ((com.facebook.imagepipeline.producers.ProducerContext) r2.second).putExtra(com.facebook.imagepipeline.producers.MultiplexProducer.m168$$Nest$fgetmDedupedRequestsCountKey(r6.this$0), java.lang.Integer.valueOf(r1));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0087, code lost:
            ((com.facebook.imagepipeline.producers.Consumer) r2.first).onNewResult(r8, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
            throw r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0092, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.ForwardingConsumer r7, java.io.Closeable r8, int r9) {
            /*
                r6 = this;
                monitor-enter(r6)
                com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer r0 = r6.mForwardingConsumer     // Catch:{ all -> 0x0007 }
                if (r0 == r7) goto L_0x000a
                monitor-exit(r6)     // Catch:{ all -> 0x0007 }
                return
            L_0x0007:
                r7 = move-exception
                goto L_0x0093
            L_0x000a:
                java.io.Closeable r7 = r6.mLastIntermediateResult     // Catch:{ all -> 0x0007 }
                r6.closeSafely(r7)     // Catch:{ all -> 0x0007 }
                r7 = 0
                r6.mLastIntermediateResult = r7     // Catch:{ all -> 0x0007 }
                java.util.concurrent.CopyOnWriteArraySet r0 = r6.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0007 }
                java.util.concurrent.CopyOnWriteArraySet r1 = r6.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                int r1 = r1.size()     // Catch:{ all -> 0x0007 }
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r9)     // Catch:{ all -> 0x0007 }
                if (r2 == 0) goto L_0x002f
                com.facebook.imagepipeline.producers.MultiplexProducer r2 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0007 }
                java.io.Closeable r2 = r2.cloneOrNull(r8)     // Catch:{ all -> 0x0007 }
                r6.mLastIntermediateResult = r2     // Catch:{ all -> 0x0007 }
                r6.mLastStatus = r9     // Catch:{ all -> 0x0007 }
                goto L_0x003b
            L_0x002f:
                java.util.concurrent.CopyOnWriteArraySet r2 = r6.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                r2.clear()     // Catch:{ all -> 0x0007 }
                com.facebook.imagepipeline.producers.MultiplexProducer r2 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0007 }
                java.lang.Object r3 = r6.mKey     // Catch:{ all -> 0x0007 }
                r2.removeMultiplexer(r3, r6)     // Catch:{ all -> 0x0007 }
            L_0x003b:
                monitor-exit(r6)     // Catch:{ all -> 0x0007 }
            L_0x003c:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x0092
                java.lang.Object r2 = r0.next()
                android.util.Pair r2 = (android.util.Pair) r2
                monitor-enter(r2)
                boolean r3 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r9)     // Catch:{ all -> 0x0074 }
                if (r3 == 0) goto L_0x0087
                java.lang.Object r3 = r2.second     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = (com.facebook.imagepipeline.producers.ProducerContext) r3     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.ProducerListener2 r3 = r3.getProducerListener()     // Catch:{ all -> 0x0074 }
                java.lang.Object r4 = r2.second     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.ProducerContext r4 = (com.facebook.imagepipeline.producers.ProducerContext) r4     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.MultiplexProducer r5 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0074 }
                java.lang.String r5 = r5.mProducerName     // Catch:{ all -> 0x0074 }
                r3.onProducerFinishWithSuccess(r4, r5, r7)     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.BaseProducerContext r3 = r6.mMultiplexProducerContext     // Catch:{ all -> 0x0074 }
                if (r3 == 0) goto L_0x0076
                java.lang.Object r4 = r2.second     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.ProducerContext r4 = (com.facebook.imagepipeline.producers.ProducerContext) r4     // Catch:{ all -> 0x0074 }
                java.util.Map r3 = r3.getExtras()     // Catch:{ all -> 0x0074 }
                r4.putExtras(r3)     // Catch:{ all -> 0x0074 }
                goto L_0x0076
            L_0x0074:
                r7 = move-exception
                goto L_0x0090
            L_0x0076:
                java.lang.Object r3 = r2.second     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = (com.facebook.imagepipeline.producers.ProducerContext) r3     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.MultiplexProducer r4 = com.facebook.imagepipeline.producers.MultiplexProducer.this     // Catch:{ all -> 0x0074 }
                java.lang.String r4 = r4.mDedupedRequestsCountKey     // Catch:{ all -> 0x0074 }
                java.lang.Integer r5 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0074 }
                r3.putExtra(r4, r5)     // Catch:{ all -> 0x0074 }
            L_0x0087:
                java.lang.Object r3 = r2.first     // Catch:{ all -> 0x0074 }
                com.facebook.imagepipeline.producers.Consumer r3 = (com.facebook.imagepipeline.producers.Consumer) r3     // Catch:{ all -> 0x0074 }
                r3.onNewResult(r8, r9)     // Catch:{ all -> 0x0074 }
                monitor-exit(r2)     // Catch:{ all -> 0x0074 }
                goto L_0x003c
            L_0x0090:
                monitor-exit(r2)     // Catch:{ all -> 0x0074 }
                throw r7
            L_0x0092:
                return
            L_0x0093:
                monitor-exit(r6)     // Catch:{ all -> 0x0007 }
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onNextResult(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, java.io.Closeable, int):void");
        }

        public void onCancelled(ForwardingConsumer forwardingConsumer) {
            synchronized (this) {
                try {
                    if (this.mForwardingConsumer == forwardingConsumer) {
                        this.mForwardingConsumer = null;
                        this.mMultiplexProducerContext = null;
                        closeSafely(this.mLastIntermediateResult);
                        this.mLastIntermediateResult = null;
                        startInputProducerIfHasAttachedConsumers(TriState.UNSET);
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
            if (r3.hasNext() == false) goto L_0x002b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
            r0 = (android.util.Pair) r3.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            ((com.facebook.imagepipeline.producers.Consumer) r0.first).onProgressUpdate(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x002b, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.ForwardingConsumer r3, float r4) {
            /*
                r2 = this;
                monitor-enter(r2)
                com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer r0 = r2.mForwardingConsumer     // Catch:{ all -> 0x0007 }
                if (r0 == r3) goto L_0x0009
                monitor-exit(r2)     // Catch:{ all -> 0x0007 }
                return
            L_0x0007:
                r3 = move-exception
                goto L_0x002c
            L_0x0009:
                r2.mLastProgress = r4     // Catch:{ all -> 0x0007 }
                java.util.concurrent.CopyOnWriteArraySet r3 = r2.mConsumerContextPairs     // Catch:{ all -> 0x0007 }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0007 }
                monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            L_0x0012:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x002b
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                monitor-enter(r0)
                java.lang.Object r1 = r0.first     // Catch:{ all -> 0x0028 }
                com.facebook.imagepipeline.producers.Consumer r1 = (com.facebook.imagepipeline.producers.Consumer) r1     // Catch:{ all -> 0x0028 }
                r1.onProgressUpdate(r4)     // Catch:{ all -> 0x0028 }
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                goto L_0x0012
            L_0x0028:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r3
            L_0x002b:
                return
            L_0x002c:
                monitor-exit(r2)     // Catch:{ all -> 0x0007 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer.onProgressUpdate(com.facebook.imagepipeline.producers.MultiplexProducer$Multiplexer$ForwardingConsumer, float):void");
        }

        private void closeSafely(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private class ForwardingConsumer extends BaseConsumer {
            private ForwardingConsumer() {
            }

            /* access modifiers changed from: protected */
            public void onNewResultImpl(Closeable closeable, int i) {
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("MultiplexProducer#onNewResult");
                    }
                    Multiplexer.this.onNextResult(this, closeable, i);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void onFailureImpl(Throwable th) {
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("MultiplexProducer#onFailure");
                    }
                    Multiplexer.this.onFailure(this, th);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th2) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void onCancellationImpl() {
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("MultiplexProducer#onCancellation");
                    }
                    Multiplexer.this.onCancelled(this);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void onProgressUpdateImpl(float f) {
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("MultiplexProducer#onProgressUpdate");
                    }
                    Multiplexer.this.onProgressUpdate(this, f);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    throw th;
                }
            }
        }
    }
}
