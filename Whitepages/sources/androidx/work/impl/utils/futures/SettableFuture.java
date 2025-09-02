package androidx.work.impl.utils.futures;

import com.google.common.util.concurrent.ListenableFuture;

public final class SettableFuture extends AbstractFuture {
    public static SettableFuture create() {
        return new SettableFuture();
    }

    public boolean set(Object obj) {
        return super.set(obj);
    }

    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    public boolean setFuture(ListenableFuture listenableFuture) {
        return super.setFuture(listenableFuture);
    }

    private SettableFuture() {
    }
}
