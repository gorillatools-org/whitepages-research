package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OnLifecycleEvent {
    Lifecycle.Event value();
}
