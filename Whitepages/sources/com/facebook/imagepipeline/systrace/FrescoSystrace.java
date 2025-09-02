package com.facebook.imagepipeline.systrace;

import kotlin.jvm.internal.Intrinsics;

public final class FrescoSystrace {
    public static final FrescoSystrace INSTANCE = new FrescoSystrace();
    public static final ArgsBuilder NO_OP_ARGS_BUILDER = new NoOpArgsBuilder();
    private static Systrace _instance;

    public interface ArgsBuilder {
    }

    private static final class NoOpArgsBuilder implements ArgsBuilder {
    }

    public interface Systrace {
        void beginSection(String str);

        void endSection();

        boolean isTracing();
    }

    private FrescoSystrace() {
    }

    public static final void beginSection(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        INSTANCE.getInstance().beginSection(str);
    }

    public static final void endSection() {
        INSTANCE.getInstance().endSection();
    }

    public static final boolean isTracing() {
        return INSTANCE.getInstance().isTracing();
    }

    private final Systrace getInstance() {
        DefaultFrescoSystrace defaultFrescoSystrace;
        Systrace systrace = _instance;
        if (systrace != null) {
            return systrace;
        }
        synchronized (FrescoSystrace.class) {
            defaultFrescoSystrace = new DefaultFrescoSystrace();
            _instance = defaultFrescoSystrace;
        }
        return defaultFrescoSystrace;
    }
}
