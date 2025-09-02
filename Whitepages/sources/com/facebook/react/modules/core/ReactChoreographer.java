package com.facebook.react.modules.core;

import android.view.Choreographer;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.ChoreographerProvider;
import java.util.ArrayDeque;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactChoreographer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ReactChoreographer choreographer;
    private final ArrayDeque<Choreographer.FrameCallback>[] callbackQueues;
    private ChoreographerProvider.Choreographer choreographer$1;
    private final Choreographer.FrameCallback frameCallback;
    private boolean hasPostedCallback;
    private int totalCallbacks;

    public /* synthetic */ ReactChoreographer(ChoreographerProvider choreographerProvider, DefaultConstructorMarker defaultConstructorMarker) {
        this(choreographerProvider);
    }

    public static final ReactChoreographer getInstance() {
        return Companion.getInstance();
    }

    public static final void initialize(ChoreographerProvider choreographerProvider) {
        Companion.initialize(choreographerProvider);
    }

    public enum CallbackType {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int order;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        private CallbackType(int i) {
            this.order = i;
        }

        public final int getOrder$ReactAndroid_release() {
            return this.order;
        }

        static {
            CallbackType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    private ReactChoreographer(ChoreographerProvider choreographerProvider) {
        int size = CallbackType.getEntries().size();
        ArrayDeque<Choreographer.FrameCallback>[] arrayDequeArr = new ArrayDeque[size];
        for (int i = 0; i < size; i++) {
            arrayDequeArr[i] = new ArrayDeque<>();
        }
        this.callbackQueues = arrayDequeArr;
        this.frameCallback = new ReactChoreographer$$ExternalSyntheticLambda0(this);
        UiThreadUtil.runOnUiThread(new ReactChoreographer$$ExternalSyntheticLambda1(this, choreographerProvider));
    }

    /* access modifiers changed from: private */
    public static final void frameCallback$lambda$1(ReactChoreographer reactChoreographer, long j) {
        synchronized (reactChoreographer.callbackQueues) {
            try {
                reactChoreographer.hasPostedCallback = false;
                for (ArrayDeque<Choreographer.FrameCallback> arrayDeque : reactChoreographer.callbackQueues) {
                    int size = arrayDeque.size();
                    for (int i = 0; i < size; i++) {
                        Choreographer.FrameCallback pollFirst = arrayDeque.pollFirst();
                        if (pollFirst != null) {
                            pollFirst.doFrame(j);
                            reactChoreographer.totalCallbacks--;
                        } else {
                            FLog.e(ReactConstants.TAG, "Tried to execute non-existent frame callback");
                        }
                    }
                }
                reactChoreographer.maybeRemoveFrameCallback();
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$2(ReactChoreographer reactChoreographer, ChoreographerProvider choreographerProvider) {
        reactChoreographer.choreographer$1 = choreographerProvider.getChoreographer();
    }

    public final void postFrameCallback(CallbackType callbackType, Choreographer.FrameCallback frameCallback2) {
        Intrinsics.checkNotNullParameter(callbackType, "type");
        Intrinsics.checkNotNullParameter(frameCallback2, "callback");
        synchronized (this.callbackQueues) {
            this.callbackQueues[callbackType.getOrder$ReactAndroid_release()].addLast(frameCallback2);
            boolean z = true;
            int i = this.totalCallbacks + 1;
            this.totalCallbacks = i;
            if (i <= 0) {
                z = false;
            }
            Assertions.assertCondition(z);
            postFrameCallbackOnChoreographer();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void removeFrameCallback(CallbackType callbackType, Choreographer.FrameCallback frameCallback2) {
        Intrinsics.checkNotNullParameter(callbackType, "type");
        synchronized (this.callbackQueues) {
            try {
                if (this.callbackQueues[callbackType.getOrder$ReactAndroid_release()].removeFirstOccurrence(frameCallback2)) {
                    this.totalCallbacks--;
                    maybeRemoveFrameCallback();
                } else {
                    FLog.e(ReactConstants.TAG, "Tried to remove non-existent frame callback");
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void postFrameCallbackOnChoreographer() {
        if (!this.hasPostedCallback) {
            ChoreographerProvider.Choreographer choreographer2 = this.choreographer$1;
            if (choreographer2 == null) {
                UiThreadUtil.runOnUiThread(new ReactChoreographer$$ExternalSyntheticLambda2(this));
                return;
            }
            choreographer2.postFrameCallback(this.frameCallback);
            this.hasPostedCallback = true;
        }
    }

    /* access modifiers changed from: private */
    public static final void postFrameCallbackOnChoreographer$lambda$6(ReactChoreographer reactChoreographer) {
        synchronized (reactChoreographer.callbackQueues) {
            reactChoreographer.postFrameCallbackOnChoreographer();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void maybeRemoveFrameCallback() {
        Assertions.assertCondition(this.totalCallbacks >= 0);
        if (this.totalCallbacks == 0 && this.hasPostedCallback) {
            ChoreographerProvider.Choreographer choreographer2 = this.choreographer$1;
            if (choreographer2 != null) {
                choreographer2.removeFrameCallback(this.frameCallback);
            }
            this.hasPostedCallback = false;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void initialize(ChoreographerProvider choreographerProvider) {
            Intrinsics.checkNotNullParameter(choreographerProvider, "choreographerProvider");
            if (ReactChoreographer.choreographer == null) {
                ReactChoreographer.choreographer = new ReactChoreographer(choreographerProvider, (DefaultConstructorMarker) null);
            }
        }

        public final ReactChoreographer getInstance() {
            ReactChoreographer access$getChoreographer$cp = ReactChoreographer.choreographer;
            if (access$getChoreographer$cp != null) {
                return access$getChoreographer$cp;
            }
            throw new IllegalStateException("ReactChoreographer needs to be initialized.");
        }

        @VisibleForTesting
        public final ReactChoreographer overrideInstanceForTest$ReactAndroid_release(ReactChoreographer reactChoreographer) {
            ReactChoreographer access$getChoreographer$cp = ReactChoreographer.choreographer;
            ReactChoreographer.choreographer = reactChoreographer;
            return access$getChoreographer$cp;
        }
    }
}
