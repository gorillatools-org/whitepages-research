package androidx.core.view;

import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.sequences.Sequence;

public abstract class ViewGroupKt {
    public static final Iterator iterator(ViewGroup viewGroup) {
        return new ViewGroupKt$iterator$1(viewGroup);
    }

    public static final Sequence getChildren(ViewGroup viewGroup) {
        return new ViewGroupKt$children$1(viewGroup);
    }
}
