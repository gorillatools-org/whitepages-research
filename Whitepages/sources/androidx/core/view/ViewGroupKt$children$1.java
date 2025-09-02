package androidx.core.view;

import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.sequences.Sequence;

public final class ViewGroupKt$children$1 implements Sequence {
    final /* synthetic */ ViewGroup $this_children;

    ViewGroupKt$children$1(ViewGroup viewGroup) {
        this.$this_children = viewGroup;
    }

    public Iterator iterator() {
        return ViewGroupKt.iterator(this.$this_children);
    }
}
