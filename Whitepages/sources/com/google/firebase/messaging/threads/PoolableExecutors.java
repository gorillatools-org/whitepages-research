package com.google.firebase.messaging.threads;

import android.annotation.SuppressLint;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolableExecutors {
    private static final ExecutorFactory DEFAULT_INSTANCE;
    private static volatile ExecutorFactory instance;

    static {
        DefaultExecutorFactory defaultExecutorFactory = new DefaultExecutorFactory();
        DEFAULT_INSTANCE = defaultExecutorFactory;
        instance = defaultExecutorFactory;
    }

    private PoolableExecutors() {
    }

    public static ExecutorFactory factory() {
        return instance;
    }

    private static class DefaultExecutorFactory implements ExecutorFactory {
        private static final long CORE_THREAD_TIMEOUT_SECS = 60;

        private DefaultExecutorFactory() {
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ExecutorService newThreadPool(ThreadPriority threadPriority) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ExecutorService newThreadPool(ThreadFactory threadFactory, ThreadPriority threadPriority) {
            return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(threadFactory));
        }

        public ExecutorService newThreadPool(int i, ThreadPriority threadPriority) {
            return newThreadPool(i, Executors.defaultThreadFactory(), threadPriority);
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ExecutorService newThreadPool(int i, ThreadFactory threadFactory, ThreadPriority threadPriority) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            return Executors.unconfigurableExecutorService(threadPoolExecutor);
        }

        public ExecutorService newSingleThreadExecutor(ThreadPriority threadPriority) {
            return newThreadPool(1, threadPriority);
        }

        public ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory, ThreadPriority threadPriority) {
            return newThreadPool(1, threadFactory, threadPriority);
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ScheduledExecutorService newScheduledThreadPool(int i, ThreadPriority threadPriority) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(i));
        }

        @SuppressLint({"ThreadPoolCreation"})
        public ScheduledExecutorService newScheduledThreadPool(int i, ThreadFactory threadFactory, ThreadPriority threadPriority) {
            return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(i, threadFactory));
        }

        @SuppressLint({"ThreadPoolCreation"})
        public void executeOneOff(String str, String str2, ThreadPriority threadPriority, Runnable runnable) {
            new Thread(runnable, str2).start();
        }

        @SuppressLint({"ThreadPoolCreation"})
        public Future<?> submitOneOff(String str, String str2, ThreadPriority threadPriority, Runnable runnable) {
            FutureTask futureTask = new FutureTask(runnable, (Object) null);
            new Thread(futureTask, str2).start();
            return futureTask;
        }
    }

    static void installExecutorFactory(ExecutorFactory executorFactory) {
        if (instance == DEFAULT_INSTANCE) {
            instance = executorFactory;
            return;
        }
        throw new IllegalStateException("Trying to install an ExecutorFactory twice!");
    }
}
