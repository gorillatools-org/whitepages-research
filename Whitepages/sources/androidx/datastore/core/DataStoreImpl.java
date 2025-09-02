package androidx.datastore.core;

import androidx.datastore.core.UpdatingDataContextElement;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

public final class DataStoreImpl implements DataStore {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int collectorCounter;
    private Job collectorJob;
    private final Mutex collectorMutex = MutexKt.Mutex$default(false, 1, (Object) null);
    private final Lazy coordinator$delegate;
    private final CorruptionHandler corruptionHandler;
    private final Flow data = FlowKt.flow(new DataStoreImpl$data$1(this, (Continuation) null));
    /* access modifiers changed from: private */
    public final DataStoreInMemoryCache inMemoryCache = new DataStoreInMemoryCache();
    /* access modifiers changed from: private */
    public final InitDataStore readAndInit;
    private final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final Storage storage;
    /* access modifiers changed from: private */
    public final Lazy storageConnectionDelegate;
    /* access modifiers changed from: private */
    public final SimpleActor writeActor;

    public DataStoreImpl(Storage storage2, List list, CorruptionHandler corruptionHandler2, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(storage2, "storage");
        Intrinsics.checkNotNullParameter(list, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler2, "corruptionHandler");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        this.storage = storage2;
        this.corruptionHandler = corruptionHandler2;
        this.scope = coroutineScope;
        this.readAndInit = new InitDataStore(this, list);
        this.storageConnectionDelegate = LazyKt.lazy(new DataStoreImpl$storageConnectionDelegate$1(this));
        this.coordinator$delegate = LazyKt.lazy(new DataStoreImpl$coordinator$2(this));
        this.writeActor = new SimpleActor(coroutineScope, new DataStoreImpl$writeActor$1(this), DataStoreImpl$writeActor$2.INSTANCE, new DataStoreImpl$writeActor$3(this, (Continuation) null));
    }

    public Flow getData() {
        return this.data;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0055 A[Catch:{ all -> 0x0067 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object incrementCollector(kotlin.coroutines.Continuation r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof androidx.datastore.core.DataStoreImpl$incrementCollector$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            androidx.datastore.core.DataStoreImpl$incrementCollector$1 r0 = (androidx.datastore.core.DataStoreImpl$incrementCollector$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$incrementCollector$1 r0 = new androidx.datastore.core.DataStoreImpl$incrementCollector$1
            r0.<init>(r11, r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.DataStoreImpl r0 = (androidx.datastore.core.DataStoreImpl) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x004e
        L_0x0032:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.sync.Mutex r12 = r11.collectorMutex
            r0.L$0 = r11
            r0.L$1 = r12
            r0.label = r3
            java.lang.Object r0 = r12.lock(r4, r0)
            if (r0 != r1) goto L_0x004c
            return r1
        L_0x004c:
            r0 = r11
            r1 = r12
        L_0x004e:
            int r12 = r0.collectorCounter     // Catch:{ all -> 0x0067 }
            int r12 = r12 + r3
            r0.collectorCounter = r12     // Catch:{ all -> 0x0067 }
            if (r12 != r3) goto L_0x0069
            kotlinx.coroutines.CoroutineScope r5 = r0.scope     // Catch:{ all -> 0x0067 }
            androidx.datastore.core.DataStoreImpl$incrementCollector$2$1 r8 = new androidx.datastore.core.DataStoreImpl$incrementCollector$2$1     // Catch:{ all -> 0x0067 }
            r8.<init>(r0, r4)     // Catch:{ all -> 0x0067 }
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job r12 = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0067 }
            r0.collectorJob = r12     // Catch:{ all -> 0x0067 }
            goto L_0x0069
        L_0x0067:
            r12 = move-exception
            goto L_0x0071
        L_0x0069:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0067 }
            r1.unlock(r4)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0071:
            r1.unlock(r4)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.incrementCollector(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0056 A[Catch:{ all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object decrementCollector(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.datastore.core.DataStoreImpl$decrementCollector$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            androidx.datastore.core.DataStoreImpl$decrementCollector$1 r0 = (androidx.datastore.core.DataStoreImpl$decrementCollector$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$decrementCollector$1 r0 = new androidx.datastore.core.DataStoreImpl$decrementCollector$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.DataStoreImpl r0 = (androidx.datastore.core.DataStoreImpl) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004e
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.sync.Mutex r6 = r5.collectorMutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r0 = r6.lock(r4, r0)
            if (r0 != r1) goto L_0x004c
            return r1
        L_0x004c:
            r0 = r5
            r1 = r6
        L_0x004e:
            int r6 = r0.collectorCounter     // Catch:{ all -> 0x005e }
            int r6 = r6 + -1
            r0.collectorCounter = r6     // Catch:{ all -> 0x005e }
            if (r6 != 0) goto L_0x0062
            kotlinx.coroutines.Job r6 = r0.collectorJob     // Catch:{ all -> 0x005e }
            if (r6 == 0) goto L_0x0060
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r6, r4, r3, r4)     // Catch:{ all -> 0x005e }
            goto L_0x0060
        L_0x005e:
            r6 = move-exception
            goto L_0x006a
        L_0x0060:
            r0.collectorJob = r4     // Catch:{ all -> 0x005e }
        L_0x0062:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005e }
            r1.unlock(r4)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006a:
            r1.unlock(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.decrementCollector(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object updateData(Function2 function2, Continuation continuation) {
        UpdatingDataContextElement updatingDataContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (updatingDataContextElement != null) {
            updatingDataContextElement.checkNotUpdating(this);
        }
        return BuildersKt.withContext(new UpdatingDataContextElement(updatingDataContextElement, this), new DataStoreImpl$updateData$2(this, function2, (Continuation) null), continuation);
    }

    public final StorageConnection getStorageConnection$datastore_core_release() {
        return (StorageConnection) this.storageConnectionDelegate.getValue();
    }

    /* access modifiers changed from: private */
    public final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final Object readState(boolean z, Continuation continuation) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new DataStoreImpl$readState$2(this, z, (Continuation) null), continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object handleUpdate(androidx.datastore.core.Message.Update r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.datastore.core.DataStoreImpl$handleUpdate$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            androidx.datastore.core.DataStoreImpl$handleUpdate$1 r0 = (androidx.datastore.core.DataStoreImpl$handleUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$handleUpdate$1 r0 = new androidx.datastore.core.DataStoreImpl$handleUpdate$1
            r0.<init>(r8, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0056
            if (r2 == r5) goto L_0x0051
            if (r2 == r4) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0034 }
            goto L_0x00bc
        L_0x0034:
            r10 = move-exception
            goto L_0x00de
        L_0x0037:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003f:
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
            java.lang.Object r2 = r0.L$1
            androidx.datastore.core.DataStoreImpl r2 = (androidx.datastore.core.DataStoreImpl) r2
            java.lang.Object r4 = r0.L$0
            androidx.datastore.core.Message$Update r4 = (androidx.datastore.core.Message.Update) r4
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0034 }
            r10 = r9
            r9 = r4
            goto L_0x00a4
        L_0x0051:
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.CompletableDeferred r9 = (kotlinx.coroutines.CompletableDeferred) r9
            goto L_0x002f
        L_0x0056:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.CompletableDeferred r10 = r9.getAck()
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x0080 }
            androidx.datastore.core.DataStoreInMemoryCache r2 = r8.inMemoryCache     // Catch:{ all -> 0x0080 }
            androidx.datastore.core.State r2 = r2.getCurrentState()     // Catch:{ all -> 0x0080 }
            boolean r6 = r2 instanceof androidx.datastore.core.Data     // Catch:{ all -> 0x0080 }
            if (r6 == 0) goto L_0x0085
            kotlin.jvm.functions.Function2 r2 = r9.getTransform()     // Catch:{ all -> 0x0080 }
            kotlin.coroutines.CoroutineContext r9 = r9.getCallerContext()     // Catch:{ all -> 0x0080 }
            r0.L$0 = r10     // Catch:{ all -> 0x0080 }
            r0.label = r5     // Catch:{ all -> 0x0080 }
            java.lang.Object r9 = r8.transformAndWrite(r2, r9, r0)     // Catch:{ all -> 0x0080 }
            if (r9 != r1) goto L_0x007c
            return r1
        L_0x007c:
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x00bc
        L_0x0080:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x00de
        L_0x0085:
            boolean r6 = r2 instanceof androidx.datastore.core.ReadException     // Catch:{ all -> 0x0080 }
            if (r6 == 0) goto L_0x008a
            goto L_0x008c
        L_0x008a:
            boolean r5 = r2 instanceof androidx.datastore.core.UnInitialized     // Catch:{ all -> 0x0080 }
        L_0x008c:
            if (r5 == 0) goto L_0x00cd
            androidx.datastore.core.State r5 = r9.getLastState()     // Catch:{ all -> 0x0080 }
            if (r2 != r5) goto L_0x00c1
            r0.L$0 = r9     // Catch:{ all -> 0x0080 }
            r0.L$1 = r8     // Catch:{ all -> 0x0080 }
            r0.L$2 = r10     // Catch:{ all -> 0x0080 }
            r0.label = r4     // Catch:{ all -> 0x0080 }
            java.lang.Object r2 = r8.readAndInitOrPropagateAndThrowFailure(r0)     // Catch:{ all -> 0x0080 }
            if (r2 != r1) goto L_0x00a3
            return r1
        L_0x00a3:
            r2 = r8
        L_0x00a4:
            kotlin.jvm.functions.Function2 r4 = r9.getTransform()     // Catch:{ all -> 0x0080 }
            kotlin.coroutines.CoroutineContext r9 = r9.getCallerContext()     // Catch:{ all -> 0x0080 }
            r0.L$0 = r10     // Catch:{ all -> 0x0080 }
            r5 = 0
            r0.L$1 = r5     // Catch:{ all -> 0x0080 }
            r0.L$2 = r5     // Catch:{ all -> 0x0080 }
            r0.label = r3     // Catch:{ all -> 0x0080 }
            java.lang.Object r9 = r2.transformAndWrite(r4, r9, r0)     // Catch:{ all -> 0x0080 }
            if (r9 != r1) goto L_0x007c
            return r1
        L_0x00bc:
            java.lang.Object r10 = kotlin.Result.m866constructorimpl(r10)     // Catch:{ all -> 0x0034 }
            goto L_0x00e8
        L_0x00c1:
            java.lang.String r9 = "null cannot be cast to non-null type androidx.datastore.core.ReadException<T of androidx.datastore.core.DataStoreImpl.handleUpdate$lambda$2>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r9)     // Catch:{ all -> 0x0080 }
            androidx.datastore.core.ReadException r2 = (androidx.datastore.core.ReadException) r2     // Catch:{ all -> 0x0080 }
            java.lang.Throwable r9 = r2.getReadException()     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x00cd:
            boolean r9 = r2 instanceof androidx.datastore.core.Final     // Catch:{ all -> 0x0080 }
            if (r9 == 0) goto L_0x00d8
            androidx.datastore.core.Final r2 = (androidx.datastore.core.Final) r2     // Catch:{ all -> 0x0080 }
            java.lang.Throwable r9 = r2.getFinalException()     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x00d8:
            kotlin.NoWhenBranchMatchedException r9 = new kotlin.NoWhenBranchMatchedException     // Catch:{ all -> 0x0080 }
            r9.<init>()     // Catch:{ all -> 0x0080 }
            throw r9     // Catch:{ all -> 0x0080 }
        L_0x00de:
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r10 = kotlin.ResultKt.createFailure(r10)
            java.lang.Object r10 = kotlin.Result.m866constructorimpl(r10)
        L_0x00e8:
            kotlinx.coroutines.CompletableDeferredKt.completeWith(r9, r10)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.handleUpdate(androidx.datastore.core.Message$Update, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 r0 = (androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 r0 = new androidx.datastore.core.DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            int r1 = r0.I$0
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.DataStoreImpl r0 = (androidx.datastore.core.DataStoreImpl) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0032 }
            goto L_0x006c
        L_0x0032:
            r6 = move-exception
            goto L_0x0073
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x003c:
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.DataStoreImpl r2 = (androidx.datastore.core.DataStoreImpl) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0057
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.datastore.core.InterProcessCoordinator r6 = r5.getCoordinator()
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r6.getVersion(r0)
            if (r6 != r1) goto L_0x0056
            return r1
        L_0x0056:
            r2 = r5
        L_0x0057:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            androidx.datastore.core.DataStoreImpl$InitDataStore r4 = r2.readAndInit     // Catch:{ all -> 0x006f }
            r0.L$0 = r2     // Catch:{ all -> 0x006f }
            r0.I$0 = r6     // Catch:{ all -> 0x006f }
            r0.label = r3     // Catch:{ all -> 0x006f }
            java.lang.Object r6 = r4.runIfNeeded(r0)     // Catch:{ all -> 0x006f }
            if (r6 != r1) goto L_0x006c
            return r1
        L_0x006c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006f:
            r0 = move-exception
            r1 = r6
            r6 = r0
            r0 = r2
        L_0x0073:
            androidx.datastore.core.DataStoreInMemoryCache r0 = r0.inMemoryCache
            androidx.datastore.core.ReadException r2 = new androidx.datastore.core.ReadException
            r2.<init>(r6, r1)
            r0.tryUpdate(r2)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.readAndInitOrPropagateAndThrowFailure(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readDataAndUpdateCache(boolean r10, kotlin.coroutines.Continuation r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1 r0 = (androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1 r0 = new androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$1
            r0.<init>(r9, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0058
            if (r2 == r5) goto L_0x0045
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r10 = r0.L$0
            androidx.datastore.core.DataStoreImpl r10 = (androidx.datastore.core.DataStoreImpl) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c3
        L_0x0034:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x003c:
            java.lang.Object r10 = r0.L$0
            androidx.datastore.core.DataStoreImpl r10 = (androidx.datastore.core.DataStoreImpl) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00aa
        L_0x0045:
            boolean r10 = r0.Z$0
            java.lang.Object r2 = r0.L$1
            androidx.datastore.core.State r2 = (androidx.datastore.core.State) r2
            java.lang.Object r5 = r0.L$0
            androidx.datastore.core.DataStoreImpl r5 = (androidx.datastore.core.DataStoreImpl) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r11
            r11 = r10
            r10 = r5
            r5 = r2
            r2 = r8
            goto L_0x007c
        L_0x0058:
            kotlin.ResultKt.throwOnFailure(r11)
            androidx.datastore.core.DataStoreInMemoryCache r11 = r9.inMemoryCache
            androidx.datastore.core.State r2 = r11.getCurrentState()
            boolean r11 = r2 instanceof androidx.datastore.core.UnInitialized
            if (r11 != 0) goto L_0x00dd
            androidx.datastore.core.InterProcessCoordinator r11 = r9.getCoordinator()
            r0.L$0 = r9
            r0.L$1 = r2
            r0.Z$0 = r10
            r0.label = r5
            java.lang.Object r11 = r11.getVersion(r0)
            if (r11 != r1) goto L_0x0078
            return r1
        L_0x0078:
            r5 = r2
            r2 = r11
            r11 = r10
            r10 = r9
        L_0x007c:
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            boolean r6 = r5 instanceof androidx.datastore.core.Data
            if (r6 == 0) goto L_0x008b
            int r7 = r5.getVersion()
            goto L_0x008c
        L_0x008b:
            r7 = -1
        L_0x008c:
            if (r6 == 0) goto L_0x0091
            if (r2 != r7) goto L_0x0091
            return r5
        L_0x0091:
            r2 = 0
            if (r11 == 0) goto L_0x00ad
            androidx.datastore.core.InterProcessCoordinator r11 = r10.getCoordinator()
            androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3 r3 = new androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$3
            r3.<init>(r10, r2)
            r0.L$0 = r10
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r11 = r11.lock(r3, r0)
            if (r11 != r1) goto L_0x00aa
            return r1
        L_0x00aa:
            kotlin.Pair r11 = (kotlin.Pair) r11
            goto L_0x00c5
        L_0x00ad:
            androidx.datastore.core.InterProcessCoordinator r11 = r10.getCoordinator()
            androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4 r4 = new androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4
            r4.<init>(r10, r7, r2)
            r0.L$0 = r10
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r11 = r11.tryLock(r4, r0)
            if (r11 != r1) goto L_0x00c3
            return r1
        L_0x00c3:
            kotlin.Pair r11 = (kotlin.Pair) r11
        L_0x00c5:
            java.lang.Object r0 = r11.component1()
            androidx.datastore.core.State r0 = (androidx.datastore.core.State) r0
            java.lang.Object r11 = r11.component2()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00dc
            androidx.datastore.core.DataStoreInMemoryCache r10 = r10.inMemoryCache
            r10.tryUpdate(r0)
        L_0x00dc:
            return r0
        L_0x00dd:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.readDataAndUpdateCache(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object readDataFromFileOrDefault(Continuation continuation) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), continuation);
    }

    private final Object transformAndWrite(Function2 function2, CoroutineContext coroutineContext, Continuation continuation) {
        return getCoordinator().lock(new DataStoreImpl$transformAndWrite$2(this, coroutineContext, function2, (Continuation) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeData$datastore_core_release(java.lang.Object r12, boolean r13, kotlin.coroutines.Continuation r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.datastore.core.DataStoreImpl$writeData$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            androidx.datastore.core.DataStoreImpl$writeData$1 r0 = (androidx.datastore.core.DataStoreImpl$writeData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$writeData$1 r0 = new androidx.datastore.core.DataStoreImpl$writeData$1
            r0.<init>(r11, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r12 = r0.L$0
            kotlin.jvm.internal.Ref$IntRef r12 = (kotlin.jvm.internal.Ref$IntRef) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0058
        L_0x002d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            androidx.datastore.core.StorageConnection r2 = r11.getStorageConnection$datastore_core_release()
            androidx.datastore.core.DataStoreImpl$writeData$2 r10 = new androidx.datastore.core.DataStoreImpl$writeData$2
            r9 = 0
            r4 = r10
            r5 = r14
            r6 = r11
            r7 = r12
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9)
            r0.L$0 = r14
            r0.label = r3
            java.lang.Object r12 = r2.writeScope(r10, r0)
            if (r12 != r1) goto L_0x0057
            return r1
        L_0x0057:
            r12 = r14
        L_0x0058:
            int r12 = r12.element
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.writeData$datastore_core_release(java.lang.Object, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0086, code lost:
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a7, code lost:
        if (r12 == null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r2 = r12.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ae, code lost:
        r12 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b0, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b1, code lost:
        r3 = r6.getCoordinator();
        r0.L$0 = r6;
        r0.L$1 = r12;
        r0.Z$0 = r11;
        r0.I$0 = r2;
        r0.label = 2;
        r3 = r3.getVersion(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c4, code lost:
        if (r3 != r1) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c6, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c7, code lost:
        r8 = r2;
        r2 = r11;
        r11 = r8;
        r9 = r3;
        r3 = r12;
        r12 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r12 = ((java.lang.Number) r12).intValue();
        r3 = r2.getCoordinator();
        r6 = new androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2(r2, r12, (kotlin.coroutines.Continuation) null);
        r0.L$0 = r2;
        r0.Z$0 = r11;
        r0.label = 4;
        r12 = r3.tryLock(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0109, code lost:
        if (r12 != r1) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x010b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012a, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x012b, code lost:
        r6 = r12;
        r12 = r3;
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x012e, code lost:
        r3.element = r12;
        r12 = new kotlin.jvm.internal.Ref$IntRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r3 = new androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3(r2, r7, r12, (kotlin.coroutines.Continuation) null);
        r0.L$0 = r6;
        r0.L$1 = r2;
        r0.L$2 = r12;
        r0.L$3 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0149, code lost:
        if (r7.doWithWriteFileLock(r11, r3, r0) == r1) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x014b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x014c, code lost:
        r11 = r12;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x014e, code lost:
        r0 = r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0152, code lost:
        if (r0 != null) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0154, code lost:
        r5 = r0.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x015d, code lost:
        return new androidx.datastore.core.Data(r0, r5, r11.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x015e, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015f, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0160, code lost:
        kotlin.ExceptionsKt.addSuppressed(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0163, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        return (androidx.datastore.core.Data) r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        return new androidx.datastore.core.Data(r3, r11, ((java.lang.Number) r12).intValue());
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x012a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readDataOrHandleCorruption(boolean r11, kotlin.coroutines.Continuation r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1 r0 = (androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1 r0 = new androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$1
            r0.<init>(r10, r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            r5 = 0
            switch(r2) {
                case 0: goto L_0x0094;
                case 1: goto L_0x0089;
                case 2: goto L_0x0076;
                case 3: goto L_0x006b;
                case 4: goto L_0x005c;
                case 5: goto L_0x0042;
                case 6: goto L_0x002e;
                default: goto L_0x0026;
            }
        L_0x0026:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x002e:
            java.lang.Object r11 = r0.L$2
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref$IntRef) r11
            java.lang.Object r1 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
            java.lang.Object r0 = r0.L$0
            androidx.datastore.core.CorruptionException r0 = (androidx.datastore.core.CorruptionException) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x003f }
            goto L_0x014e
        L_0x003f:
            r11 = move-exception
            goto L_0x0160
        L_0x0042:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$3
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref$ObjectRef) r2
            java.lang.Object r3 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r3 = (kotlin.jvm.internal.Ref$ObjectRef) r3
            java.lang.Object r6 = r0.L$1
            androidx.datastore.core.CorruptionException r6 = (androidx.datastore.core.CorruptionException) r6
            java.lang.Object r7 = r0.L$0
            androidx.datastore.core.DataStoreImpl r7 = (androidx.datastore.core.DataStoreImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r3
            r3 = r2
            r2 = r8
            goto L_0x012e
        L_0x005c:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.DataStoreImpl r2 = (androidx.datastore.core.DataStoreImpl) r2
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ CorruptionException -> 0x0067 }
            goto L_0x010c
        L_0x0067:
            r12 = move-exception
            r7 = r2
            goto L_0x0110
        L_0x006b:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.DataStoreImpl r2 = (androidx.datastore.core.DataStoreImpl) r2
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ CorruptionException -> 0x0067 }
            goto L_0x00ef
        L_0x0076:
            int r11 = r0.I$0
            boolean r2 = r0.Z$0
            java.lang.Object r3 = r0.L$1
            java.lang.Object r6 = r0.L$0
            androidx.datastore.core.DataStoreImpl r6 = (androidx.datastore.core.DataStoreImpl) r6
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ CorruptionException -> 0x0084 }
            goto L_0x00cd
        L_0x0084:
            r12 = move-exception
            r11 = r2
        L_0x0086:
            r7 = r6
            goto L_0x0110
        L_0x0089:
            boolean r11 = r0.Z$0
            java.lang.Object r2 = r0.L$0
            androidx.datastore.core.DataStoreImpl r2 = (androidx.datastore.core.DataStoreImpl) r2
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ CorruptionException -> 0x0067 }
            r6 = r2
            goto L_0x00a7
        L_0x0094:
            kotlin.ResultKt.throwOnFailure(r12)
            if (r11 == 0) goto L_0x00dc
            r0.L$0 = r10     // Catch:{ CorruptionException -> 0x00d9 }
            r0.Z$0 = r11     // Catch:{ CorruptionException -> 0x00d9 }
            r0.label = r3     // Catch:{ CorruptionException -> 0x00d9 }
            java.lang.Object r12 = r10.readDataFromFileOrDefault(r0)     // Catch:{ CorruptionException -> 0x00d9 }
            if (r12 != r1) goto L_0x00a6
            return r1
        L_0x00a6:
            r6 = r10
        L_0x00a7:
            if (r12 == 0) goto L_0x00b0
            int r2 = r12.hashCode()     // Catch:{ CorruptionException -> 0x00ae }
            goto L_0x00b1
        L_0x00ae:
            r12 = move-exception
            goto L_0x0086
        L_0x00b0:
            r2 = r5
        L_0x00b1:
            androidx.datastore.core.InterProcessCoordinator r3 = r6.getCoordinator()     // Catch:{ CorruptionException -> 0x00ae }
            r0.L$0 = r6     // Catch:{ CorruptionException -> 0x00ae }
            r0.L$1 = r12     // Catch:{ CorruptionException -> 0x00ae }
            r0.Z$0 = r11     // Catch:{ CorruptionException -> 0x00ae }
            r0.I$0 = r2     // Catch:{ CorruptionException -> 0x00ae }
            r7 = 2
            r0.label = r7     // Catch:{ CorruptionException -> 0x00ae }
            java.lang.Object r3 = r3.getVersion(r0)     // Catch:{ CorruptionException -> 0x00ae }
            if (r3 != r1) goto L_0x00c7
            return r1
        L_0x00c7:
            r8 = r2
            r2 = r11
            r11 = r8
            r9 = r3
            r3 = r12
            r12 = r9
        L_0x00cd:
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ CorruptionException -> 0x0084 }
            int r12 = r12.intValue()     // Catch:{ CorruptionException -> 0x0084 }
            androidx.datastore.core.Data r7 = new androidx.datastore.core.Data     // Catch:{ CorruptionException -> 0x0084 }
            r7.<init>(r3, r11, r12)     // Catch:{ CorruptionException -> 0x0084 }
            goto L_0x010f
        L_0x00d9:
            r12 = move-exception
            r7 = r10
            goto L_0x0110
        L_0x00dc:
            androidx.datastore.core.InterProcessCoordinator r12 = r10.getCoordinator()     // Catch:{ CorruptionException -> 0x00d9 }
            r0.L$0 = r10     // Catch:{ CorruptionException -> 0x00d9 }
            r0.Z$0 = r11     // Catch:{ CorruptionException -> 0x00d9 }
            r2 = 3
            r0.label = r2     // Catch:{ CorruptionException -> 0x00d9 }
            java.lang.Object r12 = r12.getVersion(r0)     // Catch:{ CorruptionException -> 0x00d9 }
            if (r12 != r1) goto L_0x00ee
            return r1
        L_0x00ee:
            r2 = r10
        L_0x00ef:
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ CorruptionException -> 0x0067 }
            int r12 = r12.intValue()     // Catch:{ CorruptionException -> 0x0067 }
            androidx.datastore.core.InterProcessCoordinator r3 = r2.getCoordinator()     // Catch:{ CorruptionException -> 0x0067 }
            androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2 r6 = new androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2     // Catch:{ CorruptionException -> 0x0067 }
            r6.<init>(r2, r12, r4)     // Catch:{ CorruptionException -> 0x0067 }
            r0.L$0 = r2     // Catch:{ CorruptionException -> 0x0067 }
            r0.Z$0 = r11     // Catch:{ CorruptionException -> 0x0067 }
            r12 = 4
            r0.label = r12     // Catch:{ CorruptionException -> 0x0067 }
            java.lang.Object r12 = r3.tryLock(r6, r0)     // Catch:{ CorruptionException -> 0x0067 }
            if (r12 != r1) goto L_0x010c
            return r1
        L_0x010c:
            r7 = r12
            androidx.datastore.core.Data r7 = (androidx.datastore.core.Data) r7     // Catch:{ CorruptionException -> 0x0067 }
        L_0x010f:
            return r7
        L_0x0110:
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            androidx.datastore.core.CorruptionHandler r3 = r7.corruptionHandler
            r0.L$0 = r7
            r0.L$1 = r12
            r0.L$2 = r2
            r0.L$3 = r2
            r0.Z$0 = r11
            r6 = 5
            r0.label = r6
            java.lang.Object r3 = r3.handleCorruption(r12, r0)
            if (r3 != r1) goto L_0x012b
            return r1
        L_0x012b:
            r6 = r12
            r12 = r3
            r3 = r2
        L_0x012e:
            r3.element = r12
            kotlin.jvm.internal.Ref$IntRef r12 = new kotlin.jvm.internal.Ref$IntRef
            r12.<init>()
            androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3 r3 = new androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3     // Catch:{ all -> 0x015e }
            r3.<init>(r2, r7, r12, r4)     // Catch:{ all -> 0x015e }
            r0.L$0 = r6     // Catch:{ all -> 0x015e }
            r0.L$1 = r2     // Catch:{ all -> 0x015e }
            r0.L$2 = r12     // Catch:{ all -> 0x015e }
            r0.L$3 = r4     // Catch:{ all -> 0x015e }
            r4 = 6
            r0.label = r4     // Catch:{ all -> 0x015e }
            java.lang.Object r11 = r7.doWithWriteFileLock(r11, r3, r0)     // Catch:{ all -> 0x015e }
            if (r11 != r1) goto L_0x014c
            return r1
        L_0x014c:
            r11 = r12
            r1 = r2
        L_0x014e:
            androidx.datastore.core.Data r12 = new androidx.datastore.core.Data
            java.lang.Object r0 = r1.element
            if (r0 == 0) goto L_0x0158
            int r5 = r0.hashCode()
        L_0x0158:
            int r11 = r11.element
            r12.<init>(r0, r5, r11)
            return r12
        L_0x015e:
            r11 = move-exception
            r0 = r6
        L_0x0160:
            kotlin.ExceptionsKt.addSuppressed(r0, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.readDataOrHandleCorruption(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Object doWithWriteFileLock(boolean z, Function1 function1, Continuation continuation) {
        if (z) {
            return function1.invoke(continuation);
        }
        return getCoordinator().lock(new DataStoreImpl$doWithWriteFileLock$3(function1, (Continuation) null), continuation);
    }

    private final class InitDataStore extends RunOnce {
        /* access modifiers changed from: private */
        public List initTasks;
        final /* synthetic */ DataStoreImpl this$0;

        public InitDataStore(DataStoreImpl dataStoreImpl, List list) {
            Intrinsics.checkNotNullParameter(list, "initTasksList");
            this.this$0 = dataStoreImpl;
            this.initTasks = CollectionsKt.toList(list);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doRun(kotlin.coroutines.Continuation r7) {
            /*
                r6 = this;
                boolean r0 = r7 instanceof androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1
                if (r0 == 0) goto L_0x0013
                r0 = r7
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1 r0 = (androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.label = r1
                goto L_0x0018
            L_0x0013:
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1 r0 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$1
                r0.<init>(r6, r7)
            L_0x0018:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L_0x0040
                if (r2 == r4) goto L_0x0038
                if (r2 != r3) goto L_0x0030
                java.lang.Object r0 = r0.L$0
                androidx.datastore.core.DataStoreImpl$InitDataStore r0 = (androidx.datastore.core.DataStoreImpl.InitDataStore) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x006b
            L_0x0030:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L_0x0038:
                java.lang.Object r0 = r0.L$0
                androidx.datastore.core.DataStoreImpl$InitDataStore r0 = (androidx.datastore.core.DataStoreImpl.InitDataStore) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto L_0x007d
            L_0x0040:
                kotlin.ResultKt.throwOnFailure(r7)
                java.util.List r7 = r6.initTasks
                if (r7 == 0) goto L_0x006e
                kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L_0x0051
                goto L_0x006e
            L_0x0051:
                androidx.datastore.core.DataStoreImpl r7 = r6.this$0
                androidx.datastore.core.InterProcessCoordinator r7 = r7.getCoordinator()
                androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1 r2 = new androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1
                androidx.datastore.core.DataStoreImpl r4 = r6.this$0
                r5 = 0
                r2.<init>(r4, r6, r5)
                r0.L$0 = r6
                r0.label = r3
                java.lang.Object r7 = r7.lock(r2, r0)
                if (r7 != r1) goto L_0x006a
                return r1
            L_0x006a:
                r0 = r6
            L_0x006b:
                androidx.datastore.core.Data r7 = (androidx.datastore.core.Data) r7
                goto L_0x007f
            L_0x006e:
                androidx.datastore.core.DataStoreImpl r7 = r6.this$0
                r0.L$0 = r6
                r0.label = r4
                r2 = 0
                java.lang.Object r7 = r7.readDataOrHandleCorruption(r2, r0)
                if (r7 != r1) goto L_0x007c
                return r1
            L_0x007c:
                r0 = r6
            L_0x007d:
                androidx.datastore.core.Data r7 = (androidx.datastore.core.Data) r7
            L_0x007f:
                androidx.datastore.core.DataStoreImpl r0 = r0.this$0
                androidx.datastore.core.DataStoreInMemoryCache r0 = r0.inMemoryCache
                r0.tryUpdate(r7)
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreImpl.InitDataStore.doRun(kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
