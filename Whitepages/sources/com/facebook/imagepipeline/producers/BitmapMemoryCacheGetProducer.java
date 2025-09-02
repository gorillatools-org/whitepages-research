package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BitmapMemoryCacheGetProducer extends BitmapMemoryCacheProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* access modifiers changed from: protected */
    public Consumer wrapConsumer(Consumer consumer, CacheKey cacheKey, boolean z) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(cacheKey, "cacheKey");
        return consumer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapMemoryCacheGetProducer(MemoryCache memoryCache, CacheKeyFactory cacheKeyFactory, Producer producer) {
        super(memoryCache, cacheKeyFactory, producer);
        Intrinsics.checkNotNullParameter(memoryCache, "memoryCache");
        Intrinsics.checkNotNullParameter(cacheKeyFactory, "cacheKeyFactory");
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "BitmapMemoryCacheGetProducer";
    }

    /* access modifiers changed from: protected */
    public String getOriginSubcategory() {
        return "pipe_ui";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
