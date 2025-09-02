package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.Intrinsics;

public final class CompositeGeneratedAdaptersObserver implements LifecycleEventObserver {
    private final GeneratedAdapter[] generatedAdapters;

    public CompositeGeneratedAdaptersObserver(GeneratedAdapter[] generatedAdapterArr) {
        Intrinsics.checkNotNullParameter(generatedAdapterArr, "generatedAdapters");
        this.generatedAdapters = generatedAdapterArr;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        new MethodCallsLogger();
        GeneratedAdapter[] generatedAdapterArr = this.generatedAdapters;
        if (generatedAdapterArr.length > 0) {
            GeneratedAdapter generatedAdapter = generatedAdapterArr[0];
            throw null;
        } else if (generatedAdapterArr.length > 0) {
            GeneratedAdapter generatedAdapter2 = generatedAdapterArr[0];
            throw null;
        }
    }
}
