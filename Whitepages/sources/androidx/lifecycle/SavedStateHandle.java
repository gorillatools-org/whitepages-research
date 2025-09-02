package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;

public final class SavedStateHandle {
    /* access modifiers changed from: private */
    public static final Class[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Map flows;
    private final Map liveDatas;
    private final Map regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;
    private final Map savedStateProviders;

    /* access modifiers changed from: private */
    public static final Bundle savedStateProvider$lambda$0(SavedStateHandle savedStateHandle) {
        Intrinsics.checkNotNullParameter(savedStateHandle, "this$0");
        for (Map.Entry entry : MapsKt.toMap(savedStateHandle.savedStateProviders).entrySet()) {
            savedStateHandle.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        Set<String> keySet = savedStateHandle.regular.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(savedStateHandle.regular.get(str));
        }
        return BundleKt.bundleOf(TuplesKt.to("keys", arrayList), TuplesKt.to("values", arrayList2));
    }

    public SavedStateHandle(Map map) {
        Intrinsics.checkNotNullParameter(map, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateHandle$$ExternalSyntheticLambda0(this);
        linkedHashMap.putAll(map);
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateHandle$$ExternalSyntheticLambda0(this);
    }

    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    public final void set(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "key");
        if (Companion.validateValue(obj)) {
            Object obj2 = this.liveDatas.get(str);
            MutableLiveData mutableLiveData = obj2 instanceof MutableLiveData ? (MutableLiveData) obj2 : null;
            if (mutableLiveData != null) {
                mutableLiveData.setValue(obj);
            } else {
                this.regular.put(str, obj);
            }
            MutableStateFlow mutableStateFlow = (MutableStateFlow) this.flows.get(str);
            if (mutableStateFlow != null) {
                mutableStateFlow.setValue(obj);
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't put value with type ");
        Intrinsics.checkNotNull(obj);
        sb.append(obj.getClass());
        sb.append(" into saved state");
        throw new IllegalArgumentException(sb.toString());
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
                if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                    throw new IllegalStateException("Invalid bundle passed as restored state");
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int size = parcelableArrayList.size();
                for (int i = 0; i < size; i++) {
                    Object obj = parcelableArrayList.get(i);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                    linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
                }
                return new SavedStateHandle(linkedHashMap);
            } else if (bundle2 == null) {
                return new SavedStateHandle();
            } else {
                HashMap hashMap = new HashMap();
                for (String next : bundle2.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(next, "key");
                    hashMap.put(next, bundle2.get(next));
                }
                return new SavedStateHandle(hashMap);
            }
        }

        public final boolean validateValue(Object obj) {
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.ACCEPTABLE_CLASSES) {
                Intrinsics.checkNotNull(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }
    }
}
