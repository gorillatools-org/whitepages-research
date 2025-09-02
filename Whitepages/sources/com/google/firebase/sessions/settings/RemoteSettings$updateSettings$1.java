package com.google.firebase.sessions.settings;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings", f = "RemoteSettings.kt", l = {170, 76, 94}, m = "updateSettings")
final class RemoteSettings$updateSettings$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RemoteSettings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteSettings$updateSettings$1(RemoteSettings remoteSettings, Continuation continuation) {
        super(continuation);
        this.this$0 = remoteSettings;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.updateSettings(this);
    }
}
