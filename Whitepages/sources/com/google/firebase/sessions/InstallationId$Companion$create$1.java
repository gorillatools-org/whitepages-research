package com.google.firebase.sessions;

import androidx.customview.widget.ExploreByTouchHelper;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.InstallationId;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.InstallationId$Companion", f = "InstallationId.kt", l = {32, 40}, m = "create")
final class InstallationId$Companion$create$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InstallationId.Companion this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallationId$Companion$create$1(InstallationId.Companion companion, Continuation continuation) {
        super(continuation);
        this.this$0 = companion;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.create((FirebaseInstallationsApi) null, this);
    }
}
