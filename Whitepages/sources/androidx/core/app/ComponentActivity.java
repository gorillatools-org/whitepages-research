package androidx.core.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.KeyEvent;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import kotlin.jvm.internal.Intrinsics;

public abstract class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {
    private final SimpleArrayMap extraDataMap = new SimpleArrayMap();
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public static class ExtraData {
    }

    public void putExtraData(ExtraData extraData) {
        Intrinsics.checkNotNullParameter(extraData, "extraData");
        throw null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.Companion.injectIfNeededIn(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    public <T extends ExtraData> T getExtraData(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "extraDataClass");
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.extraDataMap.get(cls));
        return null;
    }

    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        if (KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        if (KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
    }

    /* access modifiers changed from: protected */
    public final boolean shouldDumpInternalState(String[] strArr) {
        return !shouldSkipDump(strArr);
    }

    private final boolean shouldSkipDump(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        String str = strArr[0];
        switch (str.hashCode()) {
            case -645125871:
                if (str.equals("--translation") && Build.VERSION.SDK_INT >= 31) {
                    return true;
                }
                return false;
            case 100470631:
                if (!str.equals("--dump-dumpable")) {
                    return false;
                }
                break;
            case 472614934:
                if (!str.equals("--list-dumpables")) {
                    return false;
                }
                break;
            case 1159329357:
                if (str.equals("--contentcapture") && Build.VERSION.SDK_INT >= 29) {
                    return true;
                }
                return false;
            case 1455016274:
                if (!str.equals("--autofill")) {
                    return false;
                }
                return true;
            default:
                return false;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return true;
        }
        return false;
    }
}
