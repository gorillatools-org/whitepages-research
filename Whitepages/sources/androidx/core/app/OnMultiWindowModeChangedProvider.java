package androidx.core.app;

import androidx.core.util.Consumer;

public interface OnMultiWindowModeChangedProvider {
    void addOnMultiWindowModeChangedListener(Consumer consumer);

    void removeOnMultiWindowModeChangedListener(Consumer consumer);
}
