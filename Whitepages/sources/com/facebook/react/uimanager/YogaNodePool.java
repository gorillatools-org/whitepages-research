package com.facebook.react.uimanager;

import com.facebook.react.common.ClearableSynchronizedPool;
import com.facebook.yoga.YogaNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;

public final class YogaNodePool {
    public static final YogaNodePool INSTANCE = new YogaNodePool();
    private static final Lazy pool$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new YogaNodePool$$ExternalSyntheticLambda0());

    private YogaNodePool() {
    }

    private final ClearableSynchronizedPool<YogaNode> getPool() {
        return (ClearableSynchronizedPool) pool$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final ClearableSynchronizedPool pool_delegate$lambda$0() {
        return new ClearableSynchronizedPool(1024);
    }

    public static final ClearableSynchronizedPool<YogaNode> get() {
        return INSTANCE.getPool();
    }
}
