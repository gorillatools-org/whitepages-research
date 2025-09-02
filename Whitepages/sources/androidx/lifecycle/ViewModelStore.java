package androidx.lifecycle;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public class ViewModelStore {
    private final Map map = new LinkedHashMap();

    public final void put(String str, ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        ViewModel viewModel2 = (ViewModel) this.map.put(str, viewModel);
        if (viewModel2 != null) {
            viewModel2.onCleared();
        }
    }

    public final ViewModel get(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return (ViewModel) this.map.get(str);
    }

    public final Set keys() {
        return new HashSet(this.map.keySet());
    }

    public final void clear() {
        for (ViewModel clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
    }
}
