package kotlinx.coroutines.channels;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.selects.SelectInstance;

final class BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1 extends Lambda implements Function3 {
    final /* synthetic */ BufferedChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BufferedChannel$onUndeliveredElementReceiveCancellationConstructor$1$1(BufferedChannel bufferedChannel) {
        super(3);
        this.this$0 = bufferedChannel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
        return invoke((SelectInstance) null, obj2, obj3);
    }

    public final Function1 invoke(SelectInstance selectInstance, Object obj, Object obj2) {
        return new Function1(obj2, this.this$0, selectInstance) {
            final /* synthetic */ Object $element;
            final /* synthetic */ BufferedChannel this$0;

            {
                this.$element = r1;
                this.this$0 = r2;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                if (this.$element != BufferedChannelKt.getCHANNEL_CLOSED()) {
                    Function1 function1 = this.this$0.onUndeliveredElement;
                    throw null;
                }
            }
        };
    }
}
