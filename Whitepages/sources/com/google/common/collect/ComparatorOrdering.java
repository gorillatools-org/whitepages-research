package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;

final class ComparatorOrdering extends Ordering implements Serializable {
    private static final long serialVersionUID = 0;
    final Comparator comparator;

    ComparatorOrdering(Comparator comparator2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
    }

    public int compare(Object obj, Object obj2) {
        return this.comparator.compare(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ComparatorOrdering) {
            return this.comparator.equals(((ComparatorOrdering) obj).comparator);
        }
        return false;
    }

    public int hashCode() {
        return this.comparator.hashCode();
    }

    public String toString() {
        return this.comparator.toString();
    }
}
