package com.google.firebase.sessions.settings;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.settings.SessionsSettings", f = "SessionsSettings.kt", l = {145, 146}, m = "updateSettings")
final class SessionsSettings$updateSettings$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionsSettings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionsSettings$updateSettings$1(SessionsSettings sessionsSettings, Continuation continuation) {
        super(continuation);
        this.this$0 = sessionsSettings;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.updateSettings(this);
    }
}
