package com.facebook.appevents.gps.topics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class GpsTopicsManager$executor$2 extends Lambda implements Function0 {
    public static final GpsTopicsManager$executor$2 INSTANCE = new GpsTopicsManager$executor$2();

    GpsTopicsManager$executor$2() {
        super(0);
    }

    public final ExecutorService invoke() {
        return Executors.newCachedThreadPool();
    }
}
