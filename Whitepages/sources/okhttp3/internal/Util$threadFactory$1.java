package okhttp3.internal;

import java.util.concurrent.ThreadFactory;

final class Util$threadFactory$1 implements ThreadFactory {
    final /* synthetic */ boolean $daemon;
    final /* synthetic */ String $name;

    Util$threadFactory$1(String str, boolean z) {
        this.$name = str;
        this.$daemon = z;
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.$name);
        thread.setDaemon(this.$daemon);
        return thread;
    }
}
