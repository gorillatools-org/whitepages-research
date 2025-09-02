package androidx.constraintlayout.core;

class Pools$SimplePool implements Pools$Pool {
    private final Object[] mPool;
    private int mPoolSize;

    Pools$SimplePool(int i) {
        if (i > 0) {
            this.mPool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public Object acquire() {
        int i = this.mPoolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.mPool;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.mPoolSize = i - 1;
        return obj;
    }

    public boolean release(Object obj) {
        int i = this.mPoolSize;
        Object[] objArr = this.mPool;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = obj;
        this.mPoolSize = i + 1;
        return true;
    }

    public void releaseAll(Object[] objArr, int i) {
        if (i > objArr.length) {
            i = objArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            int i3 = this.mPoolSize;
            Object[] objArr2 = this.mPool;
            if (i3 < objArr2.length) {
                objArr2[i3] = obj;
                this.mPoolSize = i3 + 1;
            }
        }
    }
}
