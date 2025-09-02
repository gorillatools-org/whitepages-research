package androidx.activity;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public abstract class ViewTreeFullyDrawnReporterOwner {
    public static final void set(View view, FullyDrawnReporterOwner fullyDrawnReporterOwner) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(fullyDrawnReporterOwner, "fullyDrawnReporterOwner");
        view.setTag(R$id.report_drawn, fullyDrawnReporterOwner);
    }
}
