package kotlin;

import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NotImplementedError extends Error {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotImplementedError(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NotImplementedError(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "An operation is not implemented." : str);
    }
}
