package com.google.firebase;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

public final class FirebaseKt$coroutineDispatcher$1<T> implements ComponentFactory {
    public static final FirebaseKt$coroutineDispatcher$1<T> INSTANCE = new FirebaseKt$coroutineDispatcher$1<>();

    public final CoroutineDispatcher create(ComponentContainer componentContainer) {
        Intrinsics.reifiedOperationMarker(4, "T");
        Object obj = componentContainer.get(Qualified.qualified(Annotation.class, Executor.class));
        Intrinsics.checkNotNullExpressionValue(obj, "c.get(Qualified.qualifie…a, Executor::class.java))");
        return ExecutorsKt.from((Executor) obj);
    }
}
