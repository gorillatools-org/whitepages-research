package androidx.lifecycle;

import android.app.Application;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class ViewModelProvider {
    private final CreationExtras defaultCreationExtras;
    private final Factory factory;
    private final ViewModelStore store;

    public static class OnRequeryFactory {
        public abstract void onRequery(ViewModel viewModel);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory2) {
        this(viewModelStore, factory2, (CreationExtras) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(viewModelStore, "store");
        Intrinsics.checkNotNullParameter(factory2, "factory");
    }

    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory2, CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(viewModelStore, "store");
        Intrinsics.checkNotNullParameter(factory2, "factory");
        Intrinsics.checkNotNullParameter(creationExtras, "defaultCreationExtras");
        this.store = viewModelStore;
        this.factory = factory2;
        this.defaultCreationExtras = creationExtras;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewModelProvider(ViewModelStore viewModelStore, Factory factory2, CreationExtras creationExtras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(viewModelStore, factory2, (i & 4) != 0 ? CreationExtras.Empty.INSTANCE : creationExtras);
    }

    public interface Factory {
        public static final Companion Companion = Companion.$$INSTANCE;

        ViewModel create(Class cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
        }

        ViewModel create(Class cls, CreationExtras creationExtras) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Intrinsics.checkNotNullParameter(creationExtras, "extras");
            return create(cls);
        }

        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStoreOwner viewModelStoreOwner, Factory factory2) {
        this(viewModelStoreOwner.getViewModelStore(), factory2, ViewModelProviderGetKt.defaultCreationExtras(viewModelStoreOwner));
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "owner");
        Intrinsics.checkNotNullParameter(factory2, "factory");
    }

    public ViewModel get(Class cls) {
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public ViewModel get(String str, Class cls) {
        ViewModel viewModel;
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(cls, "modelClass");
        ViewModel viewModel2 = this.store.get(str);
        if (cls.isInstance(viewModel2)) {
            Factory factory2 = this.factory;
            OnRequeryFactory onRequeryFactory = factory2 instanceof OnRequeryFactory ? (OnRequeryFactory) factory2 : null;
            if (onRequeryFactory != null) {
                Intrinsics.checkNotNull(viewModel2);
                onRequeryFactory.onRequery(viewModel2);
            }
            Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
            return viewModel2;
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.defaultCreationExtras);
        mutableCreationExtras.set(NewInstanceFactory.VIEW_MODEL_KEY, str);
        try {
            viewModel = this.factory.create(cls, mutableCreationExtras);
        } catch (AbstractMethodError unused) {
            viewModel = this.factory.create(cls);
        }
        this.store.put(str, viewModel);
        return viewModel;
    }

    public static class NewInstanceFactory implements Factory {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final CreationExtras.Key VIEW_MODEL_KEY = Companion.ViewModelKeyImpl.INSTANCE;
        /* access modifiers changed from: private */
        public static NewInstanceFactory sInstance;

        public ViewModel create(Class cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            try {
                Object newInstance = cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                Intrinsics.checkNotNullExpressionValue(newInstance, "{\n                modelC…wInstance()\n            }");
                return (ViewModel) newInstance;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final NewInstanceFactory getInstance() {
                if (NewInstanceFactory.sInstance == null) {
                    NewInstanceFactory.sInstance = new NewInstanceFactory();
                }
                NewInstanceFactory access$getSInstance$cp = NewInstanceFactory.sInstance;
                Intrinsics.checkNotNull(access$getSInstance$cp);
                return access$getSInstance$cp;
            }

            private static final class ViewModelKeyImpl implements CreationExtras.Key {
                public static final ViewModelKeyImpl INSTANCE = new ViewModelKeyImpl();

                private ViewModelKeyImpl() {
                }
            }
        }
    }

    public static class AndroidViewModelFactory extends NewInstanceFactory {
        public static final CreationExtras.Key APPLICATION_KEY = Companion.ApplicationKeyImpl.INSTANCE;
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static AndroidViewModelFactory sInstance;
        private final Application application;

        private AndroidViewModelFactory(Application application2, int i) {
            this.application = application2;
        }

        public AndroidViewModelFactory() {
            this((Application) null, 0);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public AndroidViewModelFactory(Application application2) {
            this(application2, 0);
            Intrinsics.checkNotNullParameter(application2, "application");
        }

        public ViewModel create(Class cls, CreationExtras creationExtras) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Intrinsics.checkNotNullParameter(creationExtras, "extras");
            if (this.application != null) {
                return create(cls);
            }
            Application application2 = (Application) creationExtras.get(APPLICATION_KEY);
            if (application2 != null) {
                return create(cls, application2);
            }
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.create(cls);
            }
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }

        public ViewModel create(Class cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Application application2 = this.application;
            if (application2 != null) {
                return create(cls, application2);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }

        private final ViewModel create(Class cls, Application application2) {
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.create(cls);
            }
            try {
                ViewModel viewModel = (ViewModel) cls.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{application2});
                Intrinsics.checkNotNullExpressionValue(viewModel, "{\n                try {\n…          }\n            }");
                return viewModel;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AndroidViewModelFactory getInstance(Application application) {
                Intrinsics.checkNotNullParameter(application, "application");
                if (AndroidViewModelFactory.sInstance == null) {
                    AndroidViewModelFactory.sInstance = new AndroidViewModelFactory(application);
                }
                AndroidViewModelFactory access$getSInstance$cp = AndroidViewModelFactory.sInstance;
                Intrinsics.checkNotNull(access$getSInstance$cp);
                return access$getSInstance$cp;
            }

            private static final class ApplicationKeyImpl implements CreationExtras.Key {
                public static final ApplicationKeyImpl INSTANCE = new ApplicationKeyImpl();

                private ApplicationKeyImpl() {
                }
            }
        }
    }
}
