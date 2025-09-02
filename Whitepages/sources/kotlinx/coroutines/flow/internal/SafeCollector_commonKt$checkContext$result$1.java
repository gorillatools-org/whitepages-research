package kotlinx.coroutines.flow.internal;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

final class SafeCollector_commonKt$checkContext$result$1 extends Lambda implements Function2 {
    final /* synthetic */ SafeCollector $this_checkContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SafeCollector_commonKt$checkContext$result$1(SafeCollector safeCollector) {
        super(2);
        this.$this_checkContext = safeCollector;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), (CoroutineContext.Element) obj2);
    }

    public final Integer invoke(int i, CoroutineContext.Element element) {
        CoroutineContext.Key key = element.getKey();
        CoroutineContext.Element element2 = this.$this_checkContext.collectContext.get(key);
        if (key != Job.Key) {
            return Integer.valueOf(element != element2 ? ExploreByTouchHelper.INVALID_ID : i + 1);
        }
        Job job = (Job) element2;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type kotlinx.coroutines.Job");
        Job transitiveCoroutineParent = SafeCollector_commonKt.transitiveCoroutineParent((Job) element, job);
        if (transitiveCoroutineParent == job) {
            if (job != null) {
                i++;
            }
            return Integer.valueOf(i);
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + transitiveCoroutineParent + ", expected child of " + job + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
    }
}
