package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.GestureHandlerInteractionController;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RNGestureHandlerInteractionManager implements GestureHandlerInteractionController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final SparseArray blockingRelations = new SparseArray();
    private final SparseArray simultaneousRelations = new SparseArray();
    private final SparseArray waitForRelations = new SparseArray();

    public final void dropRelationsForHandlerWithTag(int i) {
        this.waitForRelations.remove(i);
        this.simultaneousRelations.remove(i);
    }

    private final int[] convertHandlerTagsArray(ReadableMap readableMap, String str) {
        ReadableArray array = readableMap.getArray(str);
        Intrinsics.checkNotNull(array);
        int size = array.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = array.getInt(i);
        }
        return iArr;
    }

    public final void configureInteractions(GestureHandler gestureHandler, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(readableMap, "config");
        gestureHandler.setInteractionController(this);
        if (readableMap.hasKey("waitFor")) {
            this.waitForRelations.put(gestureHandler.getTag(), convertHandlerTagsArray(readableMap, "waitFor"));
        }
        if (readableMap.hasKey("simultaneousHandlers")) {
            this.simultaneousRelations.put(gestureHandler.getTag(), convertHandlerTagsArray(readableMap, "simultaneousHandlers"));
        }
        if (readableMap.hasKey("blocksHandlers")) {
            this.blockingRelations.put(gestureHandler.getTag(), convertHandlerTagsArray(readableMap, "blocksHandlers"));
        }
    }

    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(gestureHandler2, "otherHandler");
        int[] iArr = (int[]) this.waitForRelations.get(gestureHandler.getTag());
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (i == gestureHandler2.getTag()) {
                return true;
            }
        }
        return false;
    }

    public boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(gestureHandler2, "otherHandler");
        int[] iArr = (int[]) this.blockingRelations.get(gestureHandler.getTag());
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (i == gestureHandler2.getTag()) {
                return true;
            }
        }
        return false;
    }

    public boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(gestureHandler2, "otherHandler");
        if (gestureHandler2 instanceof NativeViewGestureHandler) {
            return ((NativeViewGestureHandler) gestureHandler2).getDisallowInterruption();
        }
        return gestureHandler2 instanceof RNGestureHandlerRootHelper.RootViewGestureHandler;
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Intrinsics.checkNotNullParameter(gestureHandler2, "otherHandler");
        int[] iArr = (int[]) this.simultaneousRelations.get(gestureHandler.getTag());
        if (iArr == null) {
            return false;
        }
        for (int i : iArr) {
            if (i == gestureHandler2.getTag()) {
                return true;
            }
        }
        return false;
    }

    public final void reset() {
        this.waitForRelations.clear();
        this.simultaneousRelations.clear();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
