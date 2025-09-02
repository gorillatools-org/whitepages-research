package androidx.datastore.preferences.core;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

final class PreferencesKt$edit$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $transform;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesKt$edit$2(Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$transform = function2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        PreferencesKt$edit$2 preferencesKt$edit$2 = new PreferencesKt$edit$2(this.$transform, continuation);
        preferencesKt$edit$2.L$0 = obj;
        return preferencesKt$edit$2;
    }

    public final Object invoke(Preferences preferences, Continuation continuation) {
        return ((PreferencesKt$edit$2) create(preferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = ((Preferences) this.L$0).toMutablePreferences();
            Function2 function2 = this.$transform;
            this.L$0 = mutablePreferences;
            this.label = 1;
            return function2.invoke(mutablePreferences, this) == coroutine_suspended ? coroutine_suspended : mutablePreferences;
        } else if (i == 1) {
            MutablePreferences mutablePreferences2 = (MutablePreferences) this.L$0;
            ResultKt.throwOnFailure(obj);
            return mutablePreferences2;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
