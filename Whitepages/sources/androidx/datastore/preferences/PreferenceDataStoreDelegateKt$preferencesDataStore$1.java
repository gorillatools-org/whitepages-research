package androidx.datastore.preferences;

import android.content.Context;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class PreferenceDataStoreDelegateKt$preferencesDataStore$1 extends Lambda implements Function1 {
    public static final PreferenceDataStoreDelegateKt$preferencesDataStore$1 INSTANCE = new PreferenceDataStoreDelegateKt$preferencesDataStore$1();

    PreferenceDataStoreDelegateKt$preferencesDataStore$1() {
        super(1);
    }

    public final List invoke(Context context) {
        Intrinsics.checkNotNullParameter(context, "it");
        return CollectionsKt.emptyList();
    }
}
