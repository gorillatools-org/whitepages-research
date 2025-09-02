package androidx.datastore.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;

final class DataStoreImpl$readDataOrHandleCorruption$3 extends SuspendLambda implements Function1 {
    final /* synthetic */ Ref$ObjectRef $newData;
    final /* synthetic */ Ref$IntRef $version;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataOrHandleCorruption$3(Ref$ObjectRef ref$ObjectRef, DataStoreImpl dataStoreImpl, Ref$IntRef ref$IntRef, Continuation continuation) {
        super(1, continuation);
        this.$newData = ref$ObjectRef;
        this.this$0 = dataStoreImpl;
        this.$version = ref$IntRef;
    }

    public final Continuation create(Continuation continuation) {
        return new DataStoreImpl$readDataOrHandleCorruption$3(this.$newData, this.this$0, this.$version, continuation);
    }

    public final Object invoke(Continuation continuation) {
        return ((DataStoreImpl$readDataOrHandleCorruption$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref$IntRef ref$IntRef;
        Ref$IntRef ref$IntRef2;
        Ref$ObjectRef ref$ObjectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ref$ObjectRef = this.$newData;
            DataStoreImpl dataStoreImpl = this.this$0;
            this.L$0 = ref$ObjectRef;
            this.label = 1;
            obj = dataStoreImpl.readDataFromFileOrDefault(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ref$ObjectRef = (Ref$ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ref$IntRef2 = (Ref$IntRef) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                ref$IntRef2.element = ((Number) obj).intValue();
            } catch (CorruptionException unused) {
                Ref$IntRef ref$IntRef3 = this.$version;
                DataStoreImpl dataStoreImpl2 = this.this$0;
                Object obj2 = this.$newData.element;
                this.L$0 = ref$IntRef3;
                this.label = 3;
                Object writeData$datastore_core_release = dataStoreImpl2.writeData$datastore_core_release(obj2, true, this);
                if (writeData$datastore_core_release == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ref$IntRef = ref$IntRef3;
                obj = writeData$datastore_core_release;
            }
            return Unit.INSTANCE;
        } else if (i == 3) {
            ref$IntRef = (Ref$IntRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            ref$IntRef.element = ((Number) obj).intValue();
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ref$ObjectRef.element = obj;
        ref$IntRef2 = this.$version;
        InterProcessCoordinator access$getCoordinator = this.this$0.getCoordinator();
        this.L$0 = ref$IntRef2;
        this.label = 2;
        obj = access$getCoordinator.getVersion(this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        ref$IntRef2.element = ((Number) obj).intValue();
        return Unit.INSTANCE;
    }
}
