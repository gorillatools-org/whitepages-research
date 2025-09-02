package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.internal.MaterialCheckable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckableGroup {
    private final Map checkables = new HashMap();
    private final Set checkedIds = new HashSet();
    private OnCheckedStateChangeListener onCheckedStateChangeListener;
    /* access modifiers changed from: private */
    public boolean selectionRequired;
    private boolean singleSelection;

    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(Set set);
    }

    public void setSingleSelection(boolean z) {
        if (this.singleSelection != z) {
            this.singleSelection = z;
            clearCheck();
        }
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSelectionRequired(boolean z) {
        this.selectionRequired = z;
    }

    public void setOnCheckedStateChangeListener(OnCheckedStateChangeListener onCheckedStateChangeListener2) {
        this.onCheckedStateChangeListener = onCheckedStateChangeListener2;
    }

    public void addCheckable(MaterialCheckable materialCheckable) {
        this.checkables.put(Integer.valueOf(materialCheckable.getId()), materialCheckable);
        if (materialCheckable.isChecked()) {
            checkInternal(materialCheckable);
        }
        materialCheckable.setInternalOnCheckedChangeListener(new MaterialCheckable.OnCheckedChangeListener() {
            public void onCheckedChanged(MaterialCheckable materialCheckable, boolean z) {
                if (!z) {
                    CheckableGroup checkableGroup = CheckableGroup.this;
                    if (!checkableGroup.uncheckInternal(materialCheckable, checkableGroup.selectionRequired)) {
                        return;
                    }
                } else if (!CheckableGroup.this.checkInternal(materialCheckable)) {
                    return;
                }
                CheckableGroup.this.onCheckedStateChanged();
            }
        });
    }

    public void removeCheckable(MaterialCheckable materialCheckable) {
        materialCheckable.setInternalOnCheckedChangeListener((MaterialCheckable.OnCheckedChangeListener) null);
        this.checkables.remove(Integer.valueOf(materialCheckable.getId()));
        this.checkedIds.remove(Integer.valueOf(materialCheckable.getId()));
    }

    public void check(int i) {
        MaterialCheckable materialCheckable = (MaterialCheckable) this.checkables.get(Integer.valueOf(i));
        if (materialCheckable != null && checkInternal(materialCheckable)) {
            onCheckedStateChanged();
        }
    }

    public void clearCheck() {
        boolean isEmpty = this.checkedIds.isEmpty();
        for (MaterialCheckable uncheckInternal : this.checkables.values()) {
            uncheckInternal(uncheckInternal, false);
        }
        if (!isEmpty) {
            onCheckedStateChanged();
        }
    }

    public int getSingleCheckedId() {
        if (!this.singleSelection || this.checkedIds.isEmpty()) {
            return -1;
        }
        return ((Integer) this.checkedIds.iterator().next()).intValue();
    }

    public Set getCheckedIds() {
        return new HashSet(this.checkedIds);
    }

    public List getCheckedIdsSortedByChildOrder(ViewGroup viewGroup) {
        Set checkedIds2 = getCheckedIds();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof MaterialCheckable) && checkedIds2.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public boolean checkInternal(MaterialCheckable materialCheckable) {
        int id = materialCheckable.getId();
        if (this.checkedIds.contains(Integer.valueOf(id))) {
            return false;
        }
        MaterialCheckable materialCheckable2 = (MaterialCheckable) this.checkables.get(Integer.valueOf(getSingleCheckedId()));
        if (materialCheckable2 != null) {
            uncheckInternal(materialCheckable2, false);
        }
        boolean add = this.checkedIds.add(Integer.valueOf(id));
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    /* access modifiers changed from: private */
    public boolean uncheckInternal(MaterialCheckable materialCheckable, boolean z) {
        int id = materialCheckable.getId();
        if (!this.checkedIds.contains(Integer.valueOf(id))) {
            return false;
        }
        if (!z || this.checkedIds.size() != 1 || !this.checkedIds.contains(Integer.valueOf(id))) {
            boolean remove = this.checkedIds.remove(Integer.valueOf(id));
            if (materialCheckable.isChecked()) {
                materialCheckable.setChecked(false);
            }
            return remove;
        }
        materialCheckable.setChecked(true);
        return false;
    }

    /* access modifiers changed from: private */
    public void onCheckedStateChanged() {
        OnCheckedStateChangeListener onCheckedStateChangeListener2 = this.onCheckedStateChangeListener;
        if (onCheckedStateChangeListener2 != null) {
            onCheckedStateChangeListener2.onCheckedStateChanged(getCheckedIds());
        }
    }
}
