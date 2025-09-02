package kotlinx.coroutines;

public final class NonDisposableHandle implements DisposableHandle, ChildHandle {
    public static final NonDisposableHandle INSTANCE = new NonDisposableHandle();

    public boolean childCancelled(Throwable th) {
        return false;
    }

    public void dispose() {
    }

    public Job getParent() {
        return null;
    }

    private NonDisposableHandle() {
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
