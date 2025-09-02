package com.airbnb.lottie.model;

import androidx.core.util.Pair;

public class MutablePair {
    Object first;
    Object second;

    public void set(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (!objectsEqual(pair.first, this.first) || !objectsEqual(pair.second, this.second)) {
            return false;
        }
        return true;
    }

    private static boolean objectsEqual(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public int hashCode() {
        Object obj = this.first;
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.second;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}
