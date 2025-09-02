package com.google.firebase.sessions;

import android.os.Message;
import android.util.Log;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.google.firebase.sessions.SessionLifecycleClient$sendLifecycleEvents$1", f = "SessionLifecycleClient.kt", l = {151}, m = "invokeSuspend")
final class SessionLifecycleClient$sendLifecycleEvents$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ List<Message> $messages;
    int label;
    final /* synthetic */ SessionLifecycleClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionLifecycleClient$sendLifecycleEvents$1(SessionLifecycleClient sessionLifecycleClient, List<Message> list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sessionLifecycleClient;
        this.$messages = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new SessionLifecycleClient$sendLifecycleEvents$1(this.this$0, this.$messages, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((SessionLifecycleClient$sendLifecycleEvents$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FirebaseSessionsDependencies firebaseSessionsDependencies = FirebaseSessionsDependencies.INSTANCE;
            this.label = 1;
            obj = firebaseSessionsDependencies.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Map map = (Map) obj;
        if (map.isEmpty()) {
            Log.d(SessionLifecycleClient.TAG, "Sessions SDK did not have any dependent SDKs register as dependencies. Events will not be sent.");
        } else {
            Iterable values = map.values();
            if (!(values instanceof Collection) || !((Collection) values).isEmpty()) {
                Iterator it = values.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((SessionSubscriber) it.next()).isDataCollectionEnabled()) {
                        SessionLifecycleClient sessionLifecycleClient = this.this$0;
                        for (Message access$sendMessageToServer : CollectionsKt.sortedWith(CollectionsKt.filterNotNull(CollectionsKt.mutableListOf(this.this$0.getLatestByCode(this.$messages, 2), this.this$0.getLatestByCode(this.$messages, 1))), new SessionLifecycleClient$sendLifecycleEvents$1$invokeSuspend$$inlined$sortedBy$1())) {
                            sessionLifecycleClient.sendMessageToServer(access$sendMessageToServer);
                        }
                    }
                }
            }
            Log.d(SessionLifecycleClient.TAG, "Data Collection is disabled for all subscribers. Skipping this Event");
        }
        return Unit.INSTANCE;
    }
}
