package com.facebook.imagepipeline.animated.factory;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import java.util.concurrent.ExecutorService;
import kotlin.jvm.internal.Intrinsics;

public final class AnimatedFactoryProvider {
    public static final AnimatedFactoryProvider INSTANCE = new AnimatedFactoryProvider();
    private static boolean implLoaded;

    private AnimatedFactoryProvider() {
    }

    public static final AnimatedFactory getAnimatedFactory(PlatformBitmapFactory platformBitmapFactory, ExecutorSupplier executorSupplier, CountingMemoryCache countingMemoryCache, boolean z, boolean z2, int i, int i2, ExecutorService executorService) {
        if (implLoaded) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl");
            Class cls2 = Boolean.TYPE;
            Class cls3 = Integer.TYPE;
            Object newInstance = cls.getConstructor(new Class[]{PlatformBitmapFactory.class, ExecutorSupplier.class, CountingMemoryCache.class, cls2, cls2, cls3, cls3, SerialExecutorService.class}).newInstance(new Object[]{platformBitmapFactory, executorSupplier, countingMemoryCache, Boolean.valueOf(z), Boolean.valueOf(z2), Integer.valueOf(i), Integer.valueOf(i2), executorService});
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.imagepipeline.animated.factory.AnimatedFactory");
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(newInstance);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
