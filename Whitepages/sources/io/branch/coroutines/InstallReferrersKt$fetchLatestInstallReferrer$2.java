package io.branch.coroutines;

import android.content.Context;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

final class InstallReferrersKt$fetchLatestInstallReferrer$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallReferrersKt$fetchLatestInstallReferrer$2(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        InstallReferrersKt$fetchLatestInstallReferrer$2 installReferrersKt$fetchLatestInstallReferrer$2 = new InstallReferrersKt$fetchLatestInstallReferrer$2(this.$context, continuation);
        installReferrersKt$fetchLatestInstallReferrer$2.L$0 = obj;
        return installReferrersKt$fetchLatestInstallReferrer$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((InstallReferrersKt$fetchLatestInstallReferrer$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0151 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x016b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0183 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0184  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            if (r2 == 0) goto L_0x00aa
            if (r2 == r7) goto L_0x0087
            if (r2 == r6) goto L_0x0067
            if (r2 == r5) goto L_0x004b
            if (r2 == r4) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            int r4 = r0.I$0
            java.lang.Object r1 = r0.L$1
            io.branch.data.InstallReferrerResult[] r1 = (io.branch.data.InstallReferrerResult[]) r1
            java.lang.Object r2 = r0.L$0
            io.branch.data.InstallReferrerResult[] r2 = (io.branch.data.InstallReferrerResult[]) r2
            kotlin.ResultKt.throwOnFailure(r18)
            r6 = r2
            r2 = r18
            goto L_0x0185
        L_0x002c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0034:
            int r5 = r0.I$0
            java.lang.Object r2 = r0.L$2
            io.branch.data.InstallReferrerResult[] r2 = (io.branch.data.InstallReferrerResult[]) r2
            java.lang.Object r6 = r0.L$1
            io.branch.data.InstallReferrerResult[] r6 = (io.branch.data.InstallReferrerResult[]) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.Deferred r7 = (kotlinx.coroutines.Deferred) r7
            kotlin.ResultKt.throwOnFailure(r18)
            r11 = r7
            r7 = r5
            r5 = r18
            goto L_0x016f
        L_0x004b:
            int r6 = r0.I$0
            java.lang.Object r2 = r0.L$3
            io.branch.data.InstallReferrerResult[] r2 = (io.branch.data.InstallReferrerResult[]) r2
            java.lang.Object r7 = r0.L$2
            io.branch.data.InstallReferrerResult[] r7 = (io.branch.data.InstallReferrerResult[]) r7
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.Deferred r9 = (kotlinx.coroutines.Deferred) r9
            java.lang.Object r10 = r0.L$0
            kotlinx.coroutines.Deferred r10 = (kotlinx.coroutines.Deferred) r10
            kotlin.ResultKt.throwOnFailure(r18)
            r11 = r9
            r9 = r2
            r2 = r5
            r5 = r18
            goto L_0x0154
        L_0x0067:
            int r7 = r0.I$0
            java.lang.Object r2 = r0.L$4
            io.branch.data.InstallReferrerResult[] r2 = (io.branch.data.InstallReferrerResult[]) r2
            java.lang.Object r9 = r0.L$3
            io.branch.data.InstallReferrerResult[] r9 = (io.branch.data.InstallReferrerResult[]) r9
            java.lang.Object r10 = r0.L$2
            kotlinx.coroutines.Deferred r10 = (kotlinx.coroutines.Deferred) r10
            java.lang.Object r11 = r0.L$1
            kotlinx.coroutines.Deferred r11 = (kotlinx.coroutines.Deferred) r11
            java.lang.Object r12 = r0.L$0
            kotlinx.coroutines.Deferred r12 = (kotlinx.coroutines.Deferred) r12
            kotlin.ResultKt.throwOnFailure(r18)
            r4 = r11
            r11 = r10
            r10 = r2
            r2 = r18
            goto L_0x0138
        L_0x0087:
            int r2 = r0.I$0
            java.lang.Object r9 = r0.L$5
            io.branch.data.InstallReferrerResult[] r9 = (io.branch.data.InstallReferrerResult[]) r9
            java.lang.Object r10 = r0.L$4
            io.branch.data.InstallReferrerResult[] r10 = (io.branch.data.InstallReferrerResult[]) r10
            java.lang.Object r11 = r0.L$3
            kotlinx.coroutines.Deferred r11 = (kotlinx.coroutines.Deferred) r11
            java.lang.Object r12 = r0.L$2
            kotlinx.coroutines.Deferred r12 = (kotlinx.coroutines.Deferred) r12
            java.lang.Object r13 = r0.L$1
            kotlinx.coroutines.Deferred r13 = (kotlinx.coroutines.Deferred) r13
            java.lang.Object r14 = r0.L$0
            kotlinx.coroutines.Deferred r14 = (kotlinx.coroutines.Deferred) r14
            kotlin.ResultKt.throwOnFailure(r18)
            r4 = r12
            r5 = r13
            r12 = r18
            goto L_0x011b
        L_0x00aa:
            kotlin.ResultKt.throwOnFailure(r18)
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1 r12 = new io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$googleReferrer$1
            android.content.Context r9 = r0.$context
            r12.<init>(r9, r8)
            r13 = 3
            r14 = 0
            r10 = 0
            r11 = 0
            r9 = r2
            kotlinx.coroutines.Deferred r15 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r9, r10, r11, r12, r13, r14)
            io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$huaweiReferrer$1 r12 = new io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$huaweiReferrer$1
            android.content.Context r9 = r0.$context
            r12.<init>(r9, r8)
            r9 = r2
            kotlinx.coroutines.Deferred r14 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r9, r10, r11, r12, r13, r14)
            io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$samsungReferrer$1 r12 = new io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$samsungReferrer$1
            android.content.Context r9 = r0.$context
            r12.<init>(r9, r8)
            r16 = 0
            r9 = r2
            r4 = r14
            r14 = r16
            kotlinx.coroutines.Deferred r14 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r9, r10, r11, r12, r13, r14)
            io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$xiaomiReferrer$1 r12 = new io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$xiaomiReferrer$1
            android.content.Context r9 = r0.$context
            r12.<init>(r9, r8)
            r9 = r2
            r5 = r14
            r14 = r16
            kotlinx.coroutines.Deferred r14 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r9, r10, r11, r12, r13, r14)
            io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$metaReferrer$1 r12 = new io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2$metaReferrer$1
            android.content.Context r9 = r0.$context
            r12.<init>(r9, r8)
            r9 = r2
            r2 = r14
            r14 = r16
            kotlinx.coroutines.Deferred r9 = kotlinx.coroutines.BuildersKt__Builders_commonKt.async$default(r9, r10, r11, r12, r13, r14)
            io.branch.data.InstallReferrerResult[] r10 = new io.branch.data.InstallReferrerResult[r3]
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r9
            r0.L$4 = r10
            r0.L$5 = r10
            r11 = 0
            r0.I$0 = r11
            r0.label = r7
            java.lang.Object r12 = r15.await(r0)
            if (r12 != r1) goto L_0x0116
            return r1
        L_0x0116:
            r14 = r4
            r4 = r2
            r2 = r11
            r11 = r9
            r9 = r10
        L_0x011b:
            io.branch.data.InstallReferrerResult r12 = (io.branch.data.InstallReferrerResult) r12
            r9[r2] = r12
            r0.L$0 = r5
            r0.L$1 = r4
            r0.L$2 = r11
            r0.L$3 = r10
            r0.L$4 = r10
            r0.L$5 = r8
            r0.I$0 = r7
            r0.label = r6
            java.lang.Object r2 = r14.await(r0)
            if (r2 != r1) goto L_0x0136
            return r1
        L_0x0136:
            r12 = r5
            r9 = r10
        L_0x0138:
            io.branch.data.InstallReferrerResult r2 = (io.branch.data.InstallReferrerResult) r2
            r10[r7] = r2
            r0.L$0 = r4
            r0.L$1 = r11
            r0.L$2 = r9
            r0.L$3 = r9
            r0.L$4 = r8
            r0.I$0 = r6
            r2 = 3
            r0.label = r2
            java.lang.Object r5 = r12.await(r0)
            if (r5 != r1) goto L_0x0152
            return r1
        L_0x0152:
            r10 = r4
            r7 = r9
        L_0x0154:
            io.branch.data.InstallReferrerResult r5 = (io.branch.data.InstallReferrerResult) r5
            r9[r6] = r5
            r0.L$0 = r11
            r0.L$1 = r7
            r0.L$2 = r7
            r0.L$3 = r8
            r0.I$0 = r2
            r4 = 4
            r0.label = r4
            java.lang.Object r5 = r10.await(r0)
            if (r5 != r1) goto L_0x016c
            return r1
        L_0x016c:
            r6 = r7
            r7 = r2
            r2 = r6
        L_0x016f:
            io.branch.data.InstallReferrerResult r5 = (io.branch.data.InstallReferrerResult) r5
            r2[r7] = r5
            r0.L$0 = r6
            r0.L$1 = r6
            r0.L$2 = r8
            r0.I$0 = r4
            r0.label = r3
            java.lang.Object r2 = r11.await(r0)
            if (r2 != r1) goto L_0x0184
            return r1
        L_0x0184:
            r1 = r6
        L_0x0185:
            io.branch.data.InstallReferrerResult r2 = (io.branch.data.InstallReferrerResult) r2
            r1[r4] = r2
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r6)
            io.branch.data.InstallReferrerResult r2 = io.branch.coroutines.InstallReferrersKt.getLatestValidReferrerStore(r1)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "All Install Referrers: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            io.branch.referral.BranchLogger.v(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Latest Install Referrer: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            io.branch.referral.BranchLogger.v(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.coroutines.InstallReferrersKt$fetchLatestInstallReferrer$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
