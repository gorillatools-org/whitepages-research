package kotlinx.coroutines.internal;

public abstract class ThreadSafeHeap {
    public abstract ThreadSafeHeapNode firstImpl();

    public abstract boolean isEmpty();

    public abstract ThreadSafeHeapNode peek();

    public abstract ThreadSafeHeapNode removeFirstOrNull();
}
