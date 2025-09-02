package kotlinx.coroutines.internal;

public abstract class LimitedDispatcherKt {
    public static final void checkParallelism(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i).toString());
        }
    }
}
