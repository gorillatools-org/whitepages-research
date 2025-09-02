package androidx.room;

import androidx.room.IMultiInstanceInvalidationCallback;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

public final class MultiInstanceInvalidationClient$callback$1 extends IMultiInstanceInvalidationCallback.Stub {
    final /* synthetic */ MultiInstanceInvalidationClient this$0;

    MultiInstanceInvalidationClient$callback$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        this.this$0 = multiInstanceInvalidationClient;
    }

    /* access modifiers changed from: private */
    public static final void onInvalidation$lambda$0(MultiInstanceInvalidationClient multiInstanceInvalidationClient, String[] strArr) {
        Intrinsics.checkNotNullParameter(multiInstanceInvalidationClient, "this$0");
        Intrinsics.checkNotNullParameter(strArr, "$tables");
        multiInstanceInvalidationClient.getInvalidationTracker().notifyObserversByTableNames((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void onInvalidation(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "tables");
        this.this$0.getExecutor().execute(new MultiInstanceInvalidationClient$callback$1$$ExternalSyntheticLambda0(this.this$0, strArr));
    }
}
