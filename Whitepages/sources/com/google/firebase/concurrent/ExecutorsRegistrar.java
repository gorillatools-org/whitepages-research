package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Lazy;
import com.google.firebase.components.Qualified;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

@SuppressLint({"ThreadPoolCreation"})
public class ExecutorsRegistrar implements ComponentRegistrar {
    static final Lazy<ScheduledExecutorService> BG_EXECUTOR = new Lazy<>(new ExecutorsRegistrar$$ExternalSyntheticLambda0());
    static final Lazy<ScheduledExecutorService> BLOCKING_EXECUTOR = new Lazy<>(new ExecutorsRegistrar$$ExternalSyntheticLambda2());
    static final Lazy<ScheduledExecutorService> LITE_EXECUTOR = new Lazy<>(new ExecutorsRegistrar$$ExternalSyntheticLambda1());
    static final Lazy<ScheduledExecutorService> SCHEDULER = new Lazy<>(new ExecutorsRegistrar$$ExternalSyntheticLambda3());

    public List<Component<?>> getComponents() {
        Class<Background> cls = Background.class;
        Class<ScheduledExecutorService> cls2 = ScheduledExecutorService.class;
        Class<ExecutorService> cls3 = ExecutorService.class;
        Class<Executor> cls4 = Executor.class;
        Class<Blocking> cls5 = Blocking.class;
        Class<Lightweight> cls6 = Lightweight.class;
        return Arrays.asList(new Component[]{Component.builder(Qualified.qualified(cls, cls2), (Qualified<? super T>[]) new Qualified[]{Qualified.qualified(cls, cls3), Qualified.qualified(cls, cls4)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda4()).build(), Component.builder(Qualified.qualified(cls5, cls2), (Qualified<? super T>[]) new Qualified[]{Qualified.qualified(cls5, cls3), Qualified.qualified(cls5, cls4)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda5()).build(), Component.builder(Qualified.qualified(cls6, cls2), (Qualified<? super T>[]) new Qualified[]{Qualified.qualified(cls6, cls3), Qualified.qualified(cls6, cls4)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda6()).build(), Component.builder(Qualified.qualified(UiThread.class, cls4)).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda7()).build()});
    }

    /* access modifiers changed from: private */
    public static ScheduledExecutorService scheduled(ExecutorService executorService) {
        return new DelegatingScheduledExecutorService(executorService, SCHEDULER.get());
    }

    private static ThreadFactory factory(String str, int i) {
        return new CustomThreadFactory(str, i, (StrictMode.ThreadPolicy) null);
    }

    private static ThreadFactory factory(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return new CustomThreadFactory(str, i, threadPolicy);
    }

    private static StrictMode.ThreadPolicy bgPolicy() {
        StrictMode.ThreadPolicy.Builder detectNetwork = new StrictMode.ThreadPolicy.Builder().detectNetwork();
        detectNetwork.detectResourceMismatches();
        detectNetwork.detectUnbufferedIo();
        return detectNetwork.penaltyLog().build();
    }

    private static StrictMode.ThreadPolicy litePolicy() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
    }
}
