package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SavedStateRegistry {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean attached;
    private final SafeIterableMap components = new SafeIterableMap();
    private boolean isAllowingSavingState = true;
    private boolean isRestored;
    private Recreator.SavedStateProvider recreatorProvider;
    private Bundle restoredState;

    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    public interface SavedStateProvider {
        Bundle saveState();
    }

    public final Bundle consumeRestoredStateForKey(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        if (this.isRestored) {
            Bundle bundle = this.restoredState;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle != null ? bundle.getBundle(str) : null;
            Bundle bundle3 = this.restoredState;
            if (bundle3 != null) {
                bundle3.remove(str);
            }
            Bundle bundle4 = this.restoredState;
            if (bundle4 == null || bundle4.isEmpty()) {
                this.restoredState = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public final void registerSavedStateProvider(String str, SavedStateProvider savedStateProvider) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(savedStateProvider, "provider");
        if (((SavedStateProvider) this.components.putIfAbsent(str, savedStateProvider)) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final SavedStateProvider getSavedStateProvider(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        Iterator it = this.components.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Intrinsics.checkNotNullExpressionValue(entry, "components");
            SavedStateProvider savedStateProvider = (SavedStateProvider) entry.getValue();
            if (Intrinsics.areEqual((Object) (String) entry.getKey(), (Object) str)) {
                return savedStateProvider;
            }
        }
        return null;
    }

    public final void runOnNextRecreation(Class cls) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        if (this.isAllowingSavingState) {
            Recreator.SavedStateProvider savedStateProvider = this.recreatorProvider;
            if (savedStateProvider == null) {
                savedStateProvider = new Recreator.SavedStateProvider(this);
            }
            this.recreatorProvider = savedStateProvider;
            try {
                cls.getDeclaredConstructor((Class[]) null);
                Recreator.SavedStateProvider savedStateProvider2 = this.recreatorProvider;
                if (savedStateProvider2 != null) {
                    String name = cls.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "clazz.name");
                    savedStateProvider2.add(name);
                }
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Class " + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public final void performAttach$savedstate_release(Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        if (!this.attached) {
            lifecycle.addObserver(new SavedStateRegistry$$ExternalSyntheticLambda0(this));
            this.attached = true;
            return;
        }
        throw new IllegalStateException("SavedStateRegistry was already attached.");
    }

    /* access modifiers changed from: private */
    public static final void performAttach$lambda$4(SavedStateRegistry savedStateRegistry, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_START) {
            savedStateRegistry.isAllowingSavingState = true;
        } else if (event == Lifecycle.Event.ON_STOP) {
            savedStateRegistry.isAllowingSavingState = false;
        }
    }

    public final void performRestore$savedstate_release(Bundle bundle) {
        if (!this.attached) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        } else if (!this.isRestored) {
            this.restoredState = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
            this.isRestored = true;
        } else {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
    }

    public final void performSave(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outBundle");
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.restoredState;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap.IteratorWithAdditions iteratorWithAdditions = this.components.iteratorWithAdditions();
        Intrinsics.checkNotNullExpressionValue(iteratorWithAdditions, "this.components.iteratorWithAdditions()");
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            bundle2.putBundle((String) entry.getKey(), ((SavedStateProvider) entry.getValue()).saveState());
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
