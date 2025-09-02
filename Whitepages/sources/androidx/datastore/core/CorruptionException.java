package androidx.datastore.core;

import com.facebook.react.devsupport.StackTraceHelper;
import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CorruptionException extends IOException {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CorruptionException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CorruptionException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
    }
}
