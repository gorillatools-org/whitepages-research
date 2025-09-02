package androidx.core.content;

import androidx.core.util.Consumer;

public interface OnTrimMemoryProvider {
    void addOnTrimMemoryListener(Consumer consumer);

    void removeOnTrimMemoryListener(Consumer consumer);
}
