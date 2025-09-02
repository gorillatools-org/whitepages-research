package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function2;

public final class FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 implements Flow {
    final /* synthetic */ Function2 $action$inlined;
    final /* synthetic */ Flow $this_onStart$inlined;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$1
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r4) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0083
        L_0x002c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0034:
            java.lang.Object r7 = r0.L$2
            kotlinx.coroutines.flow.internal.SafeCollector r7 = (kotlinx.coroutines.flow.internal.SafeCollector) r7
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 r4 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0044 }
            goto L_0x006e
        L_0x0044:
            r8 = move-exception
            goto L_0x008a
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.flow.internal.SafeCollector r8 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            r8.<init>(r7, r2)
            kotlin.jvm.functions.Function2 r2 = r6.$action$inlined     // Catch:{ all -> 0x0086 }
            r0.L$0 = r6     // Catch:{ all -> 0x0086 }
            r0.L$1 = r7     // Catch:{ all -> 0x0086 }
            r0.L$2 = r8     // Catch:{ all -> 0x0086 }
            r0.label = r4     // Catch:{ all -> 0x0086 }
            r4 = 6
            kotlin.jvm.internal.InlineMarker.mark(r4)     // Catch:{ all -> 0x0086 }
            java.lang.Object r2 = r2.invoke(r8, r0)     // Catch:{ all -> 0x0086 }
            r4 = 7
            kotlin.jvm.internal.InlineMarker.mark(r4)     // Catch:{ all -> 0x0086 }
            if (r2 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r4 = r6
            r2 = r7
            r7 = r8
        L_0x006e:
            r7.releaseIntercepted()
            kotlinx.coroutines.flow.Flow r7 = r4.$this_onStart$inlined
            r8 = 0
            r0.L$0 = r8
            r0.L$1 = r8
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r7 = r7.collect(r2, r0)
            if (r7 != r1) goto L_0x0083
            return r1
        L_0x0083:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0086:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x008a:
            r7.releaseIntercepted()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(Function2 function2, Flow flow) {
        this.$action$inlined = function2;
        this.$this_onStart$inlined = flow;
    }
}
