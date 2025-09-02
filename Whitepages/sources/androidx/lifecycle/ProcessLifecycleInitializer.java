package androidx.lifecycle;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class ProcessLifecycleInitializer implements Initializer {
    public LifecycleOwner create(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppInitializer instance = AppInitializer.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context)");
        if (instance.isEagerlyInitialized(ProcessLifecycleInitializer.class)) {
            LifecycleDispatcher.init(context);
            ProcessLifecycleOwner.Companion companion = ProcessLifecycleOwner.Companion;
            companion.init$lifecycle_process_release(context);
            return companion.get();
        }
        throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
    }

    public List dependencies() {
        return CollectionsKt.emptyList();
    }
}
