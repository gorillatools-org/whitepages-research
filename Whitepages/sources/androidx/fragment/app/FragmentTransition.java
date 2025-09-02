package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.transition.FragmentTransitionSupport;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class FragmentTransition {
    public static final FragmentTransition INSTANCE;
    public static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    public static final FragmentTransitionImpl SUPPORT_IMPL;

    private FragmentTransition() {
    }

    static {
        FragmentTransition fragmentTransition = new FragmentTransition();
        INSTANCE = fragmentTransition;
        SUPPORT_IMPL = fragmentTransition.resolveSupportImpl();
    }

    private final FragmentTransitionImpl resolveSupportImpl() {
        Class<FragmentTransitionSupport> cls = FragmentTransitionSupport.class;
        try {
            Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<androidx.fragment.app.FragmentTransitionImpl>");
            return cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final void retainValues(ArrayMap arrayMap, ArrayMap arrayMap2) {
        Intrinsics.checkNotNullParameter(arrayMap, "<this>");
        Intrinsics.checkNotNullParameter(arrayMap2, "namedViews");
        int size = arrayMap.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (!arrayMap2.containsKey((String) arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    public static final void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap arrayMap, boolean z2) {
        Intrinsics.checkNotNullParameter(fragment, "inFragment");
        Intrinsics.checkNotNullParameter(fragment2, "outFragment");
        Intrinsics.checkNotNullParameter(arrayMap, "sharedElements");
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    public static final void setViewVisibility(List list, int i) {
        Intrinsics.checkNotNullParameter(list, "views");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(i);
        }
    }
}
