package com.facebook.react.uimanager.layoutanimation;

import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;

public class LayoutAnimationController {
    private Runnable mCompletionRunnable;
    private final AbstractLayoutAnimation mLayoutCreateAnimation = new LayoutCreateAnimation();
    private final AbstractLayoutAnimation mLayoutDeleteAnimation = new LayoutDeleteAnimation();
    /* access modifiers changed from: private */
    public final SparseArray<LayoutHandlingAnimation> mLayoutHandlers = new SparseArray<>(0);
    private final AbstractLayoutAnimation mLayoutUpdateAnimation = new LayoutUpdateAnimation();
    private long mMaxAnimationDuration = -1;
    private boolean mShouldAnimateLayout;

    public void initializeFromConfig(ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            reset();
            return;
        }
        int i = 0;
        this.mShouldAnimateLayout = false;
        if (readableMap.hasKey("duration")) {
            i = readableMap.getInt("duration");
        }
        LayoutAnimationType layoutAnimationType = LayoutAnimationType.CREATE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType))) {
            this.mLayoutCreateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType)), i);
            this.mShouldAnimateLayout = true;
        }
        LayoutAnimationType layoutAnimationType2 = LayoutAnimationType.UPDATE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType2))) {
            this.mLayoutUpdateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType2)), i);
            this.mShouldAnimateLayout = true;
        }
        LayoutAnimationType layoutAnimationType3 = LayoutAnimationType.DELETE;
        if (readableMap.hasKey(LayoutAnimationType.toString(layoutAnimationType3))) {
            this.mLayoutDeleteAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(layoutAnimationType3)), i);
            this.mShouldAnimateLayout = true;
        }
        if (this.mShouldAnimateLayout && callback != null) {
            this.mCompletionRunnable = new Runnable() {
                public void run() {
                    callback.invoke(Boolean.TRUE);
                }
            };
        }
    }

    public void reset() {
        this.mLayoutCreateAnimation.reset();
        this.mLayoutUpdateAnimation.reset();
        this.mLayoutDeleteAnimation.reset();
        this.mCompletionRunnable = null;
        this.mShouldAnimateLayout = false;
        this.mMaxAnimationDuration = -1;
    }

    public boolean shouldAnimateLayout(View view) {
        if (view == null) {
            return false;
        }
        if ((!this.mShouldAnimateLayout || view.getParent() == null) && this.mLayoutHandlers.get(view.getId()) == null) {
            return false;
        }
        return true;
    }

    public void applyLayoutUpdate(View view, int i, int i2, int i3, int i4) {
        AbstractLayoutAnimation abstractLayoutAnimation;
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        LayoutHandlingAnimation layoutHandlingAnimation = this.mLayoutHandlers.get(id);
        if (layoutHandlingAnimation != null) {
            layoutHandlingAnimation.onLayoutUpdate(i, i2, i3, i4);
            return;
        }
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            abstractLayoutAnimation = this.mLayoutCreateAnimation;
        } else {
            abstractLayoutAnimation = this.mLayoutUpdateAnimation;
        }
        Animation createAnimation = abstractLayoutAnimation.createAnimation(view, i, i2, i3, i4);
        if (createAnimation instanceof LayoutHandlingAnimation) {
            createAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.put(id, (LayoutHandlingAnimation) animation);
                }

                public void onAnimationEnd(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.remove(id);
                }
            });
        } else {
            view.layout(i, i2, i3 + i, i4 + i2);
        }
        if (createAnimation != null) {
            long duration = createAnimation.getDuration();
            if (duration > this.mMaxAnimationDuration) {
                this.mMaxAnimationDuration = duration;
                scheduleCompletionCallback(duration);
            }
            view.startAnimation(createAnimation);
        }
    }

    public void deleteView(View view, final LayoutAnimationListener layoutAnimationListener) {
        UiThreadUtil.assertOnUiThread();
        Animation createAnimation = this.mLayoutDeleteAnimation.createAnimation(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (createAnimation != null) {
            disableUserInteractions(view);
            createAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    layoutAnimationListener.onAnimationEnd();
                }
            });
            long duration = createAnimation.getDuration();
            if (duration > this.mMaxAnimationDuration) {
                scheduleCompletionCallback(duration);
                this.mMaxAnimationDuration = duration;
            }
            view.startAnimation(createAnimation);
            return;
        }
        layoutAnimationListener.onAnimationEnd();
    }

    private void disableUserInteractions(View view) {
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                disableUserInteractions(viewGroup.getChildAt(i));
            }
        }
    }

    private void scheduleCompletionCallback(long j) {
        if (this.mCompletionRunnable != null) {
            Handler uiThreadHandler = UiThreadUtil.getUiThreadHandler();
            uiThreadHandler.removeCallbacks(this.mCompletionRunnable);
            uiThreadHandler.postDelayed(this.mCompletionRunnable, j);
        }
    }
}
