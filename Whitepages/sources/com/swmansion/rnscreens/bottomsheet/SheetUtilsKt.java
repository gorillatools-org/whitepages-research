package com.swmansion.rnscreens.bottomsheet;

import com.swmansion.rnscreens.Screen;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class SheetUtilsKt {
    public static final boolean isSheetFitToContents(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        if (screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET && screen.getSheetDetents().size() == 1 && ((Number) CollectionsKt.first((List) screen.getSheetDetents())).doubleValue() == -1.0d) {
            return true;
        }
        return false;
    }

    public static final boolean usesFormSheetPresentation(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET;
    }
}
