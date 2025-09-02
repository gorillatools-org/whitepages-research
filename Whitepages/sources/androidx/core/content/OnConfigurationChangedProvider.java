package androidx.core.content;

import androidx.core.util.Consumer;

public interface OnConfigurationChangedProvider {
    void addOnConfigurationChangedListener(Consumer consumer);

    void removeOnConfigurationChangedListener(Consumer consumer);
}
