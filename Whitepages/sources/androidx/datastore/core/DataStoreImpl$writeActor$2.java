package androidx.datastore.core;

import androidx.datastore.core.Message;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableDeferred;

final class DataStoreImpl$writeActor$2 extends Lambda implements Function2 {
    public static final DataStoreImpl$writeActor$2 INSTANCE = new DataStoreImpl$writeActor$2();

    DataStoreImpl$writeActor$2() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Message.Update) obj, (Throwable) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Message.Update update, Throwable th) {
        Intrinsics.checkNotNullParameter(update, "msg");
        CompletableDeferred ack = update.getAck();
        if (th == null) {
            th = new CancellationException("DataStore scope was cancelled before updateData could complete");
        }
        ack.completeExceptionally(th);
    }
}
