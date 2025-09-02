package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class TransitionManager {
    private static Transition sDefaultTransition = new AutoTransition();
    static ArrayList sPendingTransitions = new ArrayList();
    private static ThreadLocal sRunningTransitions = new ThreadLocal();

    static ArrayMap getRunningTransitions() {
        ArrayMap arrayMap;
        WeakReference weakReference = (WeakReference) sRunningTransitions.get();
        if (weakReference != null && (arrayMap = (ArrayMap) weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap arrayMap2 = new ArrayMap();
        sRunningTransitions.set(new WeakReference(arrayMap2));
        return arrayMap2;
    }

    private static void sceneChangeRunTransition(ViewGroup viewGroup, Transition transition) {
        if (transition != null && viewGroup != null) {
            MultiListener multiListener = new MultiListener(transition, viewGroup);
            viewGroup.addOnAttachStateChangeListener(multiListener);
            viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
        }
    }

    private static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        public void onViewAttachedToWindow(View view) {
        }

        MultiListener(Transition transition, ViewGroup viewGroup) {
            this.mTransition = transition;
            this.mSceneRoot = viewGroup;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        public void onViewDetachedFromWindow(View view) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList arrayList = (ArrayList) TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        public boolean onPreDraw() {
            removeListeners();
            if (!TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                return true;
            }
            final ArrayMap runningTransitions = TransitionManager.getRunningTransitions();
            ArrayList arrayList = (ArrayList) runningTransitions.get(this.mSceneRoot);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                runningTransitions.put(this.mSceneRoot, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.mTransition);
            this.mTransition.addListener(new TransitionListenerAdapter() {
                public void onTransitionEnd(Transition transition) {
                    ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(transition);
                    transition.removeListener(this);
                }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }
    }

    private static void sceneChangeSetup(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.captureValues(viewGroup, true);
        }
        Scene.getCurrentScene(viewGroup);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Transition transition) {
        if (!sPendingTransitions.contains(viewGroup) && ViewCompat.isLaidOut(viewGroup)) {
            sPendingTransitions.add(viewGroup);
            if (transition == null) {
                transition = sDefaultTransition;
            }
            Transition clone = transition.clone();
            sceneChangeSetup(viewGroup, clone);
            Scene.setCurrentScene(viewGroup, (Scene) null);
            sceneChangeRunTransition(viewGroup, clone);
        }
    }
}
