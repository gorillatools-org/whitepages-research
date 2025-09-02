package androidx.core.widget;

import android.widget.ListView;

public abstract class ListViewCompat {
    public static boolean canScrollList(ListView listView, int i) {
        return listView.canScrollList(i);
    }
}
