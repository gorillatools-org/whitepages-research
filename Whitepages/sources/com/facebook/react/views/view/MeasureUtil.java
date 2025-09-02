package com.facebook.react.views.view;

import android.view.View;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.yoga.YogaMeasureMode;
import kotlin.jvm.internal.Intrinsics;

public final class MeasureUtil {
    public static final MeasureUtil INSTANCE = new MeasureUtil();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.facebook.yoga.YogaMeasureMode[] r0 = com.facebook.yoga.YogaMeasureMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.yoga.YogaMeasureMode r1 = com.facebook.yoga.YogaMeasureMode.EXACTLY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.yoga.YogaMeasureMode r1 = com.facebook.yoga.YogaMeasureMode.AT_MOST     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.MeasureUtil.WhenMappings.<clinit>():void");
        }
    }

    private MeasureUtil() {
    }

    public static final int getMeasureSpec(float f, YogaMeasureMode yogaMeasureMode) {
        Intrinsics.checkNotNullParameter(yogaMeasureMode, "mode");
        int i = WhenMappings.$EnumSwitchMapping$0[yogaMeasureMode.ordinal()];
        if (i == 1) {
            return View.MeasureSpec.makeMeasureSpec((int) f, 1073741824);
        }
        if (i != 2) {
            return View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        return View.MeasureSpec.makeMeasureSpec((int) f, ExploreByTouchHelper.INVALID_ID);
    }
}
