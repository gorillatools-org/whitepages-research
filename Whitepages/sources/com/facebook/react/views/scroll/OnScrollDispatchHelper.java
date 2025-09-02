package com.facebook.react.views.scroll;

import android.os.SystemClock;
import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class OnScrollDispatchHelper {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MIN_EVENT_SEPARATION_MS = 10;
    private long lastScrollEventTimeMs = -11;
    private int prevX = ExploreByTouchHelper.INVALID_ID;
    private int prevY = ExploreByTouchHelper.INVALID_ID;
    private float xFlingVelocity;
    private float yFlingVelocity;

    public final float getXFlingVelocity() {
        return this.xFlingVelocity;
    }

    public final float getYFlingVelocity() {
        return this.yFlingVelocity;
    }

    public final boolean onScrollChanged(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.lastScrollEventTimeMs;
        boolean z = (uptimeMillis - j <= 10 && this.prevX == i && this.prevY == i2) ? false : true;
        if (uptimeMillis - j != 0) {
            this.xFlingVelocity = ((float) (i - this.prevX)) / ((float) (uptimeMillis - j));
            this.yFlingVelocity = ((float) (i2 - this.prevY)) / ((float) (uptimeMillis - j));
        }
        this.lastScrollEventTimeMs = uptimeMillis;
        this.prevX = i;
        this.prevY = i2;
        return z;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
