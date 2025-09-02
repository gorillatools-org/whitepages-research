package com.google.firebase.sessions.api;

import androidx.customview.widget.ExploreByTouchHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.google.firebase.sessions.api.FirebaseSessionsDependencies", f = "FirebaseSessionsDependencies.kt", l = {124}, m = "getRegisteredSubscribers$com_google_firebase_firebase_sessions")
final class FirebaseSessionsDependencies$getRegisteredSubscribers$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FirebaseSessionsDependencies this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FirebaseSessionsDependencies$getRegisteredSubscribers$1(FirebaseSessionsDependencies firebaseSessionsDependencies, Continuation continuation) {
        super(continuation);
        this.this$0 = firebaseSessionsDependencies;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= ExploreByTouchHelper.INVALID_ID;
        return this.this$0.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
    }
}
