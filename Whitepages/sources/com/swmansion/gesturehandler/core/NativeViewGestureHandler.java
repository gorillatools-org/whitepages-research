package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout;
import com.facebook.react.views.text.ReactTextView;
import com.facebook.react.views.textinput.ReactEditText;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.gesturehandler.react.ExtensionsKt;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NativeViewGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final NativeViewGestureHandler$Companion$defaultHook$1 defaultHook = new NativeViewGestureHandler$Companion$defaultHook$1();
    private boolean disallowInterruption;
    private NativeViewGestureHandlerHook hook = defaultHook;
    private boolean shouldActivateOnStart;

    public NativeViewGestureHandler() {
        setShouldCancelWhenOutside(true);
    }

    public final boolean getDisallowInterruption() {
        return this.disallowInterruption;
    }

    public void resetConfig() {
        super.resetConfig();
        this.shouldActivateOnStart = false;
        this.disallowInterruption = false;
    }

    public final NativeViewGestureHandler setShouldActivateOnStart(boolean z) {
        this.shouldActivateOnStart = z;
        return this;
    }

    public final NativeViewGestureHandler setDisallowInterruption(boolean z) {
        this.disallowInterruption = z;
        return this;
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        Boolean shouldRecognizeSimultaneously = this.hook.shouldRecognizeSimultaneously(gestureHandler);
        if (shouldRecognizeSimultaneously != null) {
            return shouldRecognizeSimultaneously.booleanValue();
        }
        if (super.shouldRecognizeSimultaneously(gestureHandler)) {
            return true;
        }
        if (gestureHandler instanceof NativeViewGestureHandler) {
            NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
            if (nativeViewGestureHandler.getState() == 4 && nativeViewGestureHandler.disallowInterruption) {
                return false;
            }
        }
        boolean z = this.disallowInterruption;
        int state = gestureHandler.getState();
        if ((getState() != 4 || state != 4 || z) && getState() == 4 && !z && (!this.hook.shouldCancelRootViewGestureHandlerIfNecessary() || gestureHandler.getTag() > 0)) {
            return true;
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        return !this.disallowInterruption;
    }

    /* access modifiers changed from: protected */
    public void onPrepare() {
        View view = getView();
        if (view instanceof NativeViewGestureHandlerHook) {
            this.hook = (NativeViewGestureHandlerHook) view;
        } else if (view instanceof ReactEditText) {
            this.hook = new EditTextHook(this, (ReactEditText) view);
        } else if (view instanceof ReactSwipeRefreshLayout) {
            this.hook = new SwipeRefreshLayoutHook(this, (ReactSwipeRefreshLayout) view);
        } else if (view instanceof ReactScrollView) {
            this.hook = new ScrollViewHook();
        } else if (view instanceof ReactHorizontalScrollView) {
            this.hook = new ScrollViewHook();
        } else if (view instanceof ReactTextView) {
            this.hook = new TextViewHook();
        } else if (view instanceof ReactViewGroup) {
            this.hook = new ReactViewGroupHook();
        }
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        View view = getView();
        Intrinsics.checkNotNull(view);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        boolean isScreenReaderOn = ExtensionsKt.isScreenReaderOn(context);
        if ((view instanceof RNGestureHandlerButtonViewManager.ButtonViewGroup) && isScreenReaderOn) {
            return;
        }
        if (motionEvent.getActionMasked() == 1) {
            if (getState() != 0 || this.hook.canBegin(motionEvent)) {
                this.hook.sendTouchEvent(view, motionEvent);
                if ((getState() == 0 || getState() == 2) && this.hook.canActivate(view)) {
                    activate();
                }
                if (getState() == 0) {
                    cancel();
                } else {
                    end();
                }
            } else {
                cancel();
            }
            this.hook.afterGestureEnd(motionEvent);
        } else if (getState() == 0 || getState() == 2) {
            if (this.shouldActivateOnStart) {
                boolean unused = Companion.tryIntercept(view, motionEvent);
                this.hook.sendTouchEvent(view, motionEvent);
                activate();
            } else if (Companion.tryIntercept(view, motionEvent)) {
                this.hook.sendTouchEvent(view, motionEvent);
                activate();
            } else if (this.hook.wantsToHandleEventBeforeActivation()) {
                this.hook.handleEventBeforeActivation(motionEvent);
            } else if (getState() != 2 && this.hook.canBegin(motionEvent)) {
                begin();
            }
        } else if (getState() == 4) {
            this.hook.sendTouchEvent(view, motionEvent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        obtain.setAction(3);
        NativeViewGestureHandlerHook nativeViewGestureHandlerHook = this.hook;
        View view = getView();
        Intrinsics.checkNotNull(obtain);
        nativeViewGestureHandlerHook.sendTouchEvent(view, obtain);
        obtain.recycle();
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.hook = defaultHook;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean tryIntercept(View view, MotionEvent motionEvent) {
            return (view instanceof ViewGroup) && ((ViewGroup) view).onInterceptTouchEvent(motionEvent);
        }
    }

    public interface NativeViewGestureHandlerHook {
        void afterGestureEnd(MotionEvent motionEvent);

        boolean canActivate(View view);

        boolean canBegin(MotionEvent motionEvent);

        void handleEventBeforeActivation(MotionEvent motionEvent);

        Boolean sendTouchEvent(View view, MotionEvent motionEvent);

        boolean shouldCancelRootViewGestureHandlerIfNecessary();

        Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler);

        boolean wantsToHandleEventBeforeActivation();

        public static final class DefaultImpls {
            public static void afterGestureEnd(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, MotionEvent motionEvent) {
                Intrinsics.checkNotNullParameter(motionEvent, "event");
            }

            public static boolean canBegin(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, MotionEvent motionEvent) {
                Intrinsics.checkNotNullParameter(motionEvent, "event");
                return true;
            }

            public static void handleEventBeforeActivation(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, MotionEvent motionEvent) {
                Intrinsics.checkNotNullParameter(motionEvent, "event");
            }

            public static boolean shouldCancelRootViewGestureHandlerIfNecessary(NativeViewGestureHandlerHook nativeViewGestureHandlerHook) {
                return false;
            }

            public static Boolean shouldRecognizeSimultaneously(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, GestureHandler gestureHandler) {
                Intrinsics.checkNotNullParameter(gestureHandler, "handler");
                return null;
            }

            public static boolean wantsToHandleEventBeforeActivation(NativeViewGestureHandlerHook nativeViewGestureHandlerHook) {
                return false;
            }

            public static boolean canActivate(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                return view.isPressed();
            }

            public static Boolean sendTouchEvent(NativeViewGestureHandlerHook nativeViewGestureHandlerHook, View view, MotionEvent motionEvent) {
                Intrinsics.checkNotNullParameter(motionEvent, "event");
                if (view != null) {
                    return Boolean.valueOf(view.onTouchEvent(motionEvent));
                }
                return null;
            }
        }
    }

    private static final class TextViewHook implements NativeViewGestureHandlerHook {
        public void afterGestureEnd(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.afterGestureEnd(this, motionEvent);
        }

        public boolean canBegin(MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
        }

        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
        }

        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            return Boolean.FALSE;
        }

        public boolean canActivate(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            return view instanceof ReactTextView;
        }
    }

    private static final class EditTextHook implements NativeViewGestureHandlerHook {
        private final ReactEditText editText;
        private final NativeViewGestureHandler handler;
        private float startX;
        private float startY;
        private int touchSlopSquared;

        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return true;
        }

        public boolean wantsToHandleEventBeforeActivation() {
            return true;
        }

        public EditTextHook(NativeViewGestureHandler nativeViewGestureHandler, ReactEditText reactEditText) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(reactEditText, "editText");
            this.handler = nativeViewGestureHandler;
            this.editText = reactEditText;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(reactEditText.getContext());
            this.touchSlopSquared = viewConfiguration.getScaledTouchSlop() * viewConfiguration.getScaledTouchSlop();
        }

        public boolean canActivate(View view) {
            return NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
        }

        public boolean canBegin(MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
        }

        public void afterGestureEnd(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            if (((motionEvent.getX() - this.startX) * (motionEvent.getX() - this.startX)) + ((motionEvent.getY() - this.startY) * (motionEvent.getY() - this.startY)) < ((float) this.touchSlopSquared)) {
                this.editText.requestFocusFromJS();
            }
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            return Boolean.valueOf(gestureHandler.getTag() > 0 && !(gestureHandler instanceof NativeViewGestureHandler));
        }

        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            this.handler.activate();
            this.editText.onTouchEvent(motionEvent);
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
        }
    }

    private static final class SwipeRefreshLayoutHook implements NativeViewGestureHandlerHook {
        private final NativeViewGestureHandler handler;
        private final ReactSwipeRefreshLayout swipeRefreshLayout;

        public boolean wantsToHandleEventBeforeActivation() {
            return true;
        }

        public SwipeRefreshLayoutHook(NativeViewGestureHandler nativeViewGestureHandler, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "swipeRefreshLayout");
            this.handler = nativeViewGestureHandler;
            this.swipeRefreshLayout = reactSwipeRefreshLayout;
        }

        public void afterGestureEnd(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.afterGestureEnd(this, motionEvent);
        }

        public boolean canActivate(View view) {
            return NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
        }

        public boolean canBegin(MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
        }

        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleEventBeforeActivation(android.view.MotionEvent r4) {
            /*
                r3 = this;
                java.lang.String r0 = "event"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout r4 = r3.swipeRefreshLayout
                r0 = 0
                android.view.View r4 = r4.getChildAt(r0)
                boolean r0 = r4 instanceof android.widget.ScrollView
                r1 = 0
                if (r0 == 0) goto L_0x0014
                android.widget.ScrollView r4 = (android.widget.ScrollView) r4
                goto L_0x0015
            L_0x0014:
                r4 = r1
            L_0x0015:
                if (r4 != 0) goto L_0x0018
                return
            L_0x0018:
                com.swmansion.gesturehandler.core.NativeViewGestureHandler r0 = r3.handler
                com.swmansion.gesturehandler.core.GestureHandlerOrchestrator r0 = r0.getOrchestrator()
                if (r0 == 0) goto L_0x0043
                java.util.ArrayList r0 = r0.getHandlersForView(r4)
                if (r0 == 0) goto L_0x0043
                java.util.Iterator r0 = r0.iterator()
            L_0x002a:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x003b
                java.lang.Object r1 = r0.next()
                com.swmansion.gesturehandler.core.GestureHandler r1 = (com.swmansion.gesturehandler.core.GestureHandler) r1
                boolean r2 = r1 instanceof com.swmansion.gesturehandler.core.NativeViewGestureHandler
                if (r2 == 0) goto L_0x002a
                goto L_0x0043
            L_0x003b:
                java.util.NoSuchElementException r4 = new java.util.NoSuchElementException
                java.lang.String r0 = "Collection contains no element matching the predicate."
                r4.<init>(r0)
                throw r4
            L_0x0043:
                if (r1 == 0) goto L_0x0057
                int r0 = r1.getState()
                r1 = 4
                if (r0 != r1) goto L_0x0057
                int r4 = r4.getScrollY()
                if (r4 <= 0) goto L_0x0057
                com.swmansion.gesturehandler.core.NativeViewGestureHandler r4 = r3.handler
                r4.fail()
            L_0x0057:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.NativeViewGestureHandler.SwipeRefreshLayoutHook.handleEventBeforeActivation(android.view.MotionEvent):void");
        }
    }

    private static final class ScrollViewHook implements NativeViewGestureHandlerHook {
        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return true;
        }

        public void afterGestureEnd(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.afterGestureEnd(this, motionEvent);
        }

        public boolean canActivate(View view) {
            return NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
        }

        public boolean canBegin(MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
        }

        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }
    }

    private static final class ReactViewGroupHook implements NativeViewGestureHandlerHook {
        public void afterGestureEnd(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.afterGestureEnd(this, motionEvent);
        }

        public boolean canActivate(View view) {
            return NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
        }

        public boolean canBegin(MotionEvent motionEvent) {
            return NativeViewGestureHandlerHook.DefaultImpls.canBegin(this, motionEvent);
        }

        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            return NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            if (view != null) {
                return Boolean.valueOf(view.dispatchTouchEvent(motionEvent));
            }
            return null;
        }
    }
}
