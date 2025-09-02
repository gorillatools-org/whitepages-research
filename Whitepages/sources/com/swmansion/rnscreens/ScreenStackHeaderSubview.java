package com.swmansion.rnscreens;

import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenStackHeaderSubview extends FabricEnabledHeaderSubviewViewGroup {
    private boolean isReactSizeSet;
    private int reactHeight;
    private int reactWidth;
    private Type type = Type.RIGHT;

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public ScreenStackHeaderSubview(ReactContext reactContext) {
        super(reactContext);
    }

    public final Type getType() {
        return this.type;
    }

    public final void setType(Type type2) {
        Intrinsics.checkNotNullParameter(type2, "<set-?>");
        this.type = type2;
    }

    public final ScreenStackHeaderConfig getConfig() {
        ViewParent parent = getParent();
        CustomToolbar customToolbar = parent instanceof CustomToolbar ? (CustomToolbar) parent : null;
        if (customToolbar != null) {
            return customToolbar.getConfig();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) == 1073741824 && View.MeasureSpec.getMode(i2) == 1073741824) {
            this.reactWidth = View.MeasureSpec.getSize(i);
            this.reactHeight = View.MeasureSpec.getSize(i2);
            this.isReactSizeSet = true;
            ViewParent parent = getParent();
            if (parent != null) {
                forceLayout();
                ((View) parent).requestLayout();
            }
        }
        setMeasuredDimension(this.reactWidth, this.reactHeight);
    }

    public enum Type {
        LEFT,
        CENTER,
        RIGHT,
        BACK,
        SEARCH_BAR;

        static {
            Type[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }
}
