package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResult;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ActivityResultContracts$StartActivityForResult extends ActivityResultContract {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public Intent createIntent(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "input");
        return intent;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public ActivityResult parseResult(int i, Intent intent) {
        return new ActivityResult(i, intent);
    }
}
