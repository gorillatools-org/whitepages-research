package com.th3rdwave.safeareacontext;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* synthetic */ class SafeAreaProviderManager$addEventEmitters$1 extends FunctionReferenceImpl implements Function3 {
    public static final SafeAreaProviderManager$addEventEmitters$1 INSTANCE = new SafeAreaProviderManager$addEventEmitters$1();

    SafeAreaProviderManager$addEventEmitters$1() {
        super(3, SafeAreaProviderManagerKt.class, "handleOnInsetsChange", "handleOnInsetsChange(Lcom/th3rdwave/safeareacontext/SafeAreaProvider;Lcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/Rect;)V", 1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((SafeAreaProvider) obj, (EdgeInsets) obj2, (Rect) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(SafeAreaProvider safeAreaProvider, EdgeInsets edgeInsets, Rect rect) {
        Intrinsics.checkNotNullParameter(safeAreaProvider, "p0");
        Intrinsics.checkNotNullParameter(edgeInsets, "p1");
        Intrinsics.checkNotNullParameter(rect, "p2");
        SafeAreaProviderManagerKt.handleOnInsetsChange(safeAreaProvider, edgeInsets, rect);
    }
}
