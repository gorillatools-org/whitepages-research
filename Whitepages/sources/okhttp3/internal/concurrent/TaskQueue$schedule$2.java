package okhttp3.internal.concurrent;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class TaskQueue$schedule$2 extends Task {
    final /* synthetic */ Function0 $block;
    final /* synthetic */ String $name;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TaskQueue$schedule$2(Function0 function0, String str, String str2) {
        super(str2, false, 2, (DefaultConstructorMarker) null);
        this.$block = function0;
        this.$name = str;
    }

    public long runOnce() {
        return ((Number) this.$block.invoke()).longValue();
    }
}
