package com.google.firebase.ktx;

import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

public final class FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$1<T> implements ComponentFactory {
    public static final FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$1<T> INSTANCE = new FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$1<>();

    public final CoroutineDispatcher create(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(Qualified.qualified(Background.class, Executor.class));
        Intrinsics.checkNotNullExpressionValue(obj, "c.get(Qualified.qualifie…a, Executor::class.java))");
        return ExecutorsKt.from((Executor) obj);
    }
}
