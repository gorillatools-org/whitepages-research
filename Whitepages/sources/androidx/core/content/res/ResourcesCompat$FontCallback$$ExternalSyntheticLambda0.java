package androidx.core.content.res;

import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;

public final /* synthetic */ class ResourcesCompat$FontCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ResourcesCompat.FontCallback f$0;
    public final /* synthetic */ Typeface f$1;

    public /* synthetic */ ResourcesCompat$FontCallback$$ExternalSyntheticLambda0(ResourcesCompat.FontCallback fontCallback, Typeface typeface) {
        this.f$0 = fontCallback;
        this.f$1 = typeface;
    }

    public final void run() {
        this.f$0.lambda$callbackSuccessAsync$0(this.f$1);
    }
}
