package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

abstract /* synthetic */ class FlowKt__EmittersKt {
    public static final Flow onCompletion(Flow flow, Function3 function3) {
        return new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(flow, function3);
    }

    public static final Flow onStart(Flow flow, Function2 function2) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(function2, flow);
    }

    public static final void ensureActive(FlowCollector flowCollector) {
        if (flowCollector instanceof ThrowingCollector) {
            throw ((ThrowingCollector) flowCollector).e;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.Throwable} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object invokeSafely$FlowKt__EmittersKt(kotlinx.coroutines.flow.FlowCollector r4, kotlin.jvm.functions.Function3 r5, java.lang.Throwable r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$invokeSafely$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__EmittersKt$invokeSafely$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$invokeSafely$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$invokeSafely$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$invokeSafely$1
            r0.<init>(r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            java.lang.Object r4 = r0.L$0
            r6 = r4
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x002e }
            goto L_0x0046
        L_0x002e:
            r4 = move-exception
            goto L_0x0049
        L_0x0030:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6     // Catch:{ all -> 0x002e }
            r0.label = r3     // Catch:{ all -> 0x002e }
            java.lang.Object r4 = r5.invoke(r4, r6, r0)     // Catch:{ all -> 0x002e }
            if (r4 != r1) goto L_0x0046
            return r1
        L_0x0046:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L_0x0049:
            if (r6 == 0) goto L_0x0050
            if (r6 == r4) goto L_0x0050
            kotlin.ExceptionsKt.addSuppressed(r4, r6)
        L_0x0050:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(kotlinx.coroutines.flow.FlowCollector, kotlin.jvm.functions.Function3, java.lang.Throwable, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
