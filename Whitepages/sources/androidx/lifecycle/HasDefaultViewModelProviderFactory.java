package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;

public interface HasDefaultViewModelProviderFactory {
    CreationExtras getDefaultViewModelCreationExtras();
}
