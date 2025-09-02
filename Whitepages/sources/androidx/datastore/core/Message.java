package androidx.datastore.core;

import com.facebook.react.uimanager.ViewProps;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;

public abstract class Message {
    public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Message() {
    }

    public static final class Update extends Message {
        private final CompletableDeferred ack;
        private final CoroutineContext callerContext;
        private final State lastState;
        private final Function2 transform;

        public final Function2 getTransform() {
            return this.transform;
        }

        public final CompletableDeferred getAck() {
            return this.ack;
        }

        public State getLastState() {
            return this.lastState;
        }

        public final CoroutineContext getCallerContext() {
            return this.callerContext;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Update(Function2 function2, CompletableDeferred completableDeferred, State state, CoroutineContext coroutineContext) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(function2, ViewProps.TRANSFORM);
            Intrinsics.checkNotNullParameter(completableDeferred, "ack");
            Intrinsics.checkNotNullParameter(coroutineContext, "callerContext");
            this.transform = function2;
            this.ack = completableDeferred;
            this.lastState = state;
            this.callerContext = coroutineContext;
        }
    }
}
