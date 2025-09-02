package androidx.fragment.app;

import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.Collection;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class DefaultSpecialEffectsController$retainMatchingViews$1 extends Lambda implements Function1 {
    final /* synthetic */ Collection $names;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultSpecialEffectsController$retainMatchingViews$1(Collection collection) {
        super(1);
        this.$names = collection;
    }

    public final Boolean invoke(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        return Boolean.valueOf(CollectionsKt.contains(this.$names, ViewCompat.getTransitionName((View) entry.getValue())));
    }
}
