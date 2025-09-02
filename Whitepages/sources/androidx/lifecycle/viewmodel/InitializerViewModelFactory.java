package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.jvm.internal.Intrinsics;

public final class InitializerViewModelFactory implements ViewModelProvider.Factory {
    private final ViewModelInitializer[] initializers;

    public InitializerViewModelFactory(ViewModelInitializer... viewModelInitializerArr) {
        Intrinsics.checkNotNullParameter(viewModelInitializerArr, "initializers");
        this.initializers = viewModelInitializerArr;
    }

    public ViewModel create(Class cls, CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras, "extras");
        ViewModel viewModel = null;
        for (ViewModelInitializer viewModelInitializer : this.initializers) {
            if (Intrinsics.areEqual((Object) viewModelInitializer.getClazz$lifecycle_viewmodel_release(), (Object) cls)) {
                Object invoke = viewModelInitializer.getInitializer$lifecycle_viewmodel_release().invoke(creationExtras);
                viewModel = invoke instanceof ViewModel ? (ViewModel) invoke : null;
            }
        }
        if (viewModel != null) {
            return viewModel;
        }
        throw new IllegalArgumentException("No initializer set for given class " + cls.getName());
    }
}
