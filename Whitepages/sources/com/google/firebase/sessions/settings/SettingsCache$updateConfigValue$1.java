package com.google.firebase.sessions.settings;

import androidx.customview.widget.ExploreByTouchHelper;
import androidx.datastore.preferences.core.Preferences;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.settings.SettingsCache", f = "SettingsCache.kt", l = {119}, m = "updateConfigValue")
final class SettingsCache$updateConfigValue$1<T> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SettingsCache this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SettingsCache$updateConfigValue$1(SettingsCache settingsCache, Continuation continuation) {
        super(continuation);
        this.this$0 = settingsCache;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.updateConfigValue((Preferences.Key) null, null, this);
    }
}
