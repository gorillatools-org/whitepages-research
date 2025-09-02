package androidx.work.impl.constraints;

public interface WorkConstraintsTracker {
    void replace(Iterable iterable);

    void reset();
}
