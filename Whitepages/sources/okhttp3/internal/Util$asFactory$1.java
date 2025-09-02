package okhttp3.internal;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.EventListener;

final class Util$asFactory$1 implements EventListener.Factory {
    final /* synthetic */ EventListener $this_asFactory;

    Util$asFactory$1(EventListener eventListener) {
        this.$this_asFactory = eventListener;
    }

    public final EventListener create(Call call) {
        Intrinsics.checkNotNullParameter(call, "it");
        return this.$this_asFactory;
    }
}
