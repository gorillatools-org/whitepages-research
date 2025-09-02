package androidx.privacysandbox.ads.adservices.internal;

import android.content.Context;
import android.util.Log;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class BackCompatManager {
    public static final BackCompatManager INSTANCE = new BackCompatManager();

    private BackCompatManager() {
    }

    public final Object getManager(Context context, String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function1, "manager");
        try {
            return function1.invoke(context);
        } catch (NoClassDefFoundError unused) {
            Log.d(str, "Unable to find adservices code, check manifest for uses-library tag, versionS=" + AdServicesInfo.INSTANCE.extServicesVersionS());
            return null;
        }
    }
}
