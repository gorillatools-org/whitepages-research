package com.google.firebase.sessions.settings;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.settings.SettingsCache", f = "SettingsCache.kt", l = {103}, m = "removeConfigs$com_google_firebase_firebase_sessions")
final class SettingsCache$removeConfigs$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SettingsCache this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SettingsCache$removeConfigs$1(SettingsCache settingsCache, Continuation continuation) {
        super(continuation);
        this.this$0 = settingsCache;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.removeConfigs$com_google_firebase_firebase_sessions(this);
    }
}
