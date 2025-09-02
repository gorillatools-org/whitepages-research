package androidx.constraintlayout.core;

interface Pools$Pool {
    Object acquire();

    boolean release(Object obj);

    void releaseAll(Object[] objArr, int i);
}
