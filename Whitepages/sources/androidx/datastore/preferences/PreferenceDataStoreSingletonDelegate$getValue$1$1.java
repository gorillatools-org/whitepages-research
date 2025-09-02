package androidx.datastore.preferences;

import android.content.Context;
import java.io.File;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class PreferenceDataStoreSingletonDelegate$getValue$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Context $applicationContext;
    final /* synthetic */ PreferenceDataStoreSingletonDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceDataStoreSingletonDelegate$getValue$1$1(Context context, PreferenceDataStoreSingletonDelegate preferenceDataStoreSingletonDelegate) {
        super(0);
        this.$applicationContext = context;
        this.this$0 = preferenceDataStoreSingletonDelegate;
    }

    public final File invoke() {
        Context context = this.$applicationContext;
        Intrinsics.checkNotNullExpressionValue(context, "applicationContext");
        return PreferenceDataStoreFile.preferencesDataStoreFile(context, this.this$0.name);
    }
}
