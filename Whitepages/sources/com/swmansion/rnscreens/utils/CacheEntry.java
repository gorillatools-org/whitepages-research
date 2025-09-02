package com.swmansion.rnscreens.utils;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

final class CacheEntry {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final CacheEntry EMPTY = new CacheEntry(new CacheKey(ExploreByTouchHelper.INVALID_ID, false), 0.0f);
    private final CacheKey cacheKey;
    private final float headerHeight;

    public CacheEntry(CacheKey cacheKey2, float f) {
        Intrinsics.checkNotNullParameter(cacheKey2, "cacheKey");
        this.cacheKey = cacheKey2;
        this.headerHeight = f;
    }

    public final float getHeaderHeight() {
        return this.headerHeight;
    }

    public final boolean hasKey(CacheKey cacheKey2) {
        Intrinsics.checkNotNullParameter(cacheKey2, "key");
        return this.cacheKey.getFontSize() != Integer.MIN_VALUE && Intrinsics.areEqual((Object) this.cacheKey, (Object) cacheKey2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
