package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

abstract class AnimatorUtils {
    static void addPauseListener(Animator animator, AnimatorListenerAdapter animatorListenerAdapter) {
        animator.addPauseListener(animatorListenerAdapter);
    }

    static void pause(Animator animator) {
        animator.pause();
    }

    static void resume(Animator animator) {
        animator.resume();
    }
}
