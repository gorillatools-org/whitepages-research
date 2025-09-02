package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

abstract class PickerFragment extends Fragment {
    protected final LinkedHashSet onSelectionChangedListeners = new LinkedHashSet();

    PickerFragment() {
    }

    /* access modifiers changed from: package-private */
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener);
    }

    /* access modifiers changed from: package-private */
    public void clearOnSelectionChangedListeners() {
        this.onSelectionChangedListeners.clear();
    }
}
