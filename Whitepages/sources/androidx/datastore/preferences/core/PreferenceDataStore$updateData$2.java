package androidx.datastore.preferences.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

final class PreferenceDataStore$updateData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $transform;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceDataStore$updateData$2(Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$transform = function2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        PreferenceDataStore$updateData$2 preferenceDataStore$updateData$2 = new PreferenceDataStore$updateData$2(this.$transform, continuation);
        preferenceDataStore$updateData$2.L$0 = obj;
        return preferenceDataStore$updateData$2;
    }

    public final Object invoke(Preferences preferences, Continuation continuation) {
        return ((PreferenceDataStore$updateData$2) create(preferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2 function2 = this.$transform;
            this.label = 1;
            obj = function2.invoke((Preferences) this.L$0, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Preferences preferences = (Preferences) obj;
        Intrinsics.checkNotNull(preferences, "null cannot be cast to non-null type androidx.datastore.preferences.core.MutablePreferences");
        ((MutablePreferences) preferences).freeze$datastore_preferences_core();
        return preferences;
    }
}
