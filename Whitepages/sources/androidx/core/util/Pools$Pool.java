package androidx.core.util;

public interface Pools$Pool {
    Object acquire();

    boolean release(Object obj);
}
