package kotlinx.coroutines.sync;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.selects.SelectInstance;

final class MutexImpl$onSelectCancellationUnlockConstructor$1 extends Lambda implements Function3 {
    final /* synthetic */ MutexImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MutexImpl$onSelectCancellationUnlockConstructor$1(MutexImpl mutexImpl) {
        super(3);
        this.this$0 = mutexImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        return invoke((SelectInstance) null, obj2, obj3);
    }

    public final Function1 invoke(SelectInstance selectInstance, final Object obj, Object obj2) {
        final MutexImpl mutexImpl = this.this$0;
        return new Function1() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                mutexImpl.unlock(obj);
            }
        };
    }
}
