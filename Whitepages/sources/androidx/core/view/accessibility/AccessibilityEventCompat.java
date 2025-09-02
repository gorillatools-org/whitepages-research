package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

public abstract class AccessibilityEventCompat {
    public static void setContentChangeTypes(AccessibilityEvent accessibilityEvent, int i) {
        accessibilityEvent.setContentChangeTypes(i);
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getContentChangeTypes();
    }
}
