package kotlinx.coroutines.internal;

public abstract class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    public boolean isRemoved() {
        return false;
    }
}
