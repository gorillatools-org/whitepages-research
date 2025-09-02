package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.os.Process;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressLint({"UnknownNullness"})
public class m extends ThreadPoolExecutor {
    private static final int a = 2;

    class a implements ThreadFactory {

        /* renamed from: com.salesforce.marketingcloud.media.m$a$a  reason: collision with other inner class name */
        class C0022a extends Thread {
            C0022a(Runnable runnable) {
                super(runnable);
            }

            public void run() {
                Process.setThreadPriority(10);
                super.run();
            }
        }

        a() {
        }

        public Thread newThread(Runnable runnable) {
            return new C0022a(runnable);
        }
    }

    private static class b extends FutureTask<e> implements Comparable<Runnable> {
        b(e eVar) {
            super(eVar, (Object) null);
        }

        /* renamed from: a */
        public int compareTo(Runnable runnable) {
            return 0;
        }
    }

    private static class c extends FutureTask<n> implements Comparable<Runnable> {
        private final n a;

        c(n nVar) {
            super(nVar, (Object) null);
            this.a = nVar;
        }

        /* renamed from: a */
        public int compareTo(Runnable runnable) {
            if (runnable instanceof c) {
                return ((c) runnable).a.l.ordinal() - this.a.l.ordinal();
            }
            return 0;
        }
    }

    m() {
        super(2, 2, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a());
    }

    public Future<?> submit(Runnable runnable) {
        RunnableFuture cVar = runnable instanceof n ? new c((n) runnable) : new b((e) runnable);
        execute(cVar);
        return cVar;
    }
}
