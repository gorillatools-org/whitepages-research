package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.g;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class l {
    private static final String c = "~!SdkExecutors";
    private final ExecutorService a;
    private final ExecutorService b;

    class a implements RejectedExecutionHandler {
        a() {
        }

        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            g.e(l.c, "Fixed Thread Pool Task %s rejected.", runnable.toString());
        }
    }

    class b implements RejectedExecutionHandler {
        b() {
        }

        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            g.e(l.c, "Cached Thread Pool Task %s rejected.", runnable.toString());
        }
    }

    public l() {
        this(Executors.newFixedThreadPool(1), Executors.newCachedThreadPool());
    }

    public ExecutorService a() {
        return this.b;
    }

    public ExecutorService b() {
        return this.a;
    }

    public void c() {
        List<Runnable> shutdownNow;
        List<Runnable> shutdownNow2;
        if (!this.a.isShutdown()) {
            this.a.shutdown();
        }
        if (!this.b.isShutdown()) {
            this.b.shutdown();
        }
        try {
            ExecutorService executorService = this.a;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (!executorService.awaitTermination(5, timeUnit) && (shutdownNow2 = this.a.shutdownNow()) != null && !shutdownNow2.isEmpty()) {
                g.b(c, "Shutdown DiskIO executor with %d tasks pending", Integer.valueOf(shutdownNow2.size()));
            }
            if (!this.b.awaitTermination(5, timeUnit) && (shutdownNow = this.b.shutdownNow()) != null && !shutdownNow.isEmpty()) {
                g.b(c, "Shutdown CachedExecutor executor with %d tasks pending", Integer.valueOf(shutdownNow.size()));
            }
        } catch (InterruptedException e) {
            g.b(c, e, "Unable to complete executors", new Object[0]);
        }
    }

    public l(ExecutorService executorService, ExecutorService executorService2) {
        if (executorService instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executorService).setRejectedExecutionHandler(new a());
        }
        this.a = executorService;
        if (executorService2 instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executorService2).setRejectedExecutionHandler(new b());
        }
        this.b = executorService2;
    }
}
