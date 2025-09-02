package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ViewGroupKt$iterator$1 implements Iterator, KMappedMarker {
    final /* synthetic */ ViewGroup $this_iterator;
    private int index;

    ViewGroupKt$iterator$1(ViewGroup viewGroup) {
        this.$this_iterator = viewGroup;
    }

    public boolean hasNext() {
        return this.index < this.$this_iterator.getChildCount();
    }

    public View next() {
        ViewGroup viewGroup = this.$this_iterator;
        int i = this.index;
        this.index = i + 1;
        View childAt = viewGroup.getChildAt(i);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException();
    }

    public void remove() {
        ViewGroup viewGroup = this.$this_iterator;
        int i = this.index - 1;
        this.index = i;
        viewGroup.removeViewAt(i);
    }
}
