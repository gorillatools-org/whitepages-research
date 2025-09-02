package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

public abstract class OnUndeliveredElementKt {
    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i, Object obj2) {
        if ((i & 2) != 0) {
            undeliveredElementException = null;
        }
        return callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
    }

    public static final UndeliveredElementException callUndeliveredElementCatchingException(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException) {
        try {
            function1.invoke(obj);
        } catch (Throwable th) {
            if (undeliveredElementException == null || undeliveredElementException.getCause() == th) {
                return new UndeliveredElementException("Exception in undelivered element handler for " + obj, th);
            }
            ExceptionsKt.addSuppressed(undeliveredElementException, th);
        }
        return undeliveredElementException;
    }

    public static final void callUndeliveredElement(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        UndeliveredElementException callUndeliveredElementCatchingException = callUndeliveredElementCatchingException(function1, obj, (UndeliveredElementException) null);
        if (callUndeliveredElementCatchingException != null) {
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext, callUndeliveredElementCatchingException);
        }
    }

    public static final Function1 bindCancellationFun(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        return new OnUndeliveredElementKt$bindCancellationFun$1(function1, obj, coroutineContext);
    }
}
