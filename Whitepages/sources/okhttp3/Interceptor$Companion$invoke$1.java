package okhttp3;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;

public final class Interceptor$Companion$invoke$1 implements Interceptor {
    final /* synthetic */ Function1 $block;

    public Interceptor$Companion$invoke$1(Function1 function1) {
        this.$block = function1;
    }

    public final Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "it");
        return (Response) this.$block.invoke(chain);
    }
}
