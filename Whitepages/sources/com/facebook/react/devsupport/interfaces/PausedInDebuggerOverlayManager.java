package com.facebook.react.devsupport.interfaces;

import com.facebook.react.devsupport.interfaces.DevSupportManager;

public interface PausedInDebuggerOverlayManager {
    void hidePausedInDebuggerOverlay();

    void showPausedInDebuggerOverlay(String str, DevSupportManager.PausedInDebuggerOverlayCommandListener pausedInDebuggerOverlayCommandListener);
}
