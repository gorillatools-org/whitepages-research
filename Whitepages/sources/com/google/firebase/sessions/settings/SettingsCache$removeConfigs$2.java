package com.google.firebase.sessions.settings;

import androidx.datastore.preferences.core.MutablePreferences;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@DebugMetadata(c = "com.google.firebase.sessions.settings.SettingsCache$removeConfigs$2", f = "SettingsCache.kt", l = {}, m = "invokeSuspend")
final class SettingsCache$removeConfigs$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SettingsCache this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SettingsCache$removeConfigs$2(SettingsCache settingsCache, Continuation continuation) {
        super(2, continuation);
        this.this$0 = settingsCache;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SettingsCache$removeConfigs$2 settingsCache$removeConfigs$2 = new SettingsCache$removeConfigs$2(this.this$0, continuation);
        settingsCache$removeConfigs$2.L$0 = obj;
        return settingsCache$removeConfigs$2;
    }

    public final Object invoke(MutablePreferences mutablePreferences, Continuation continuation) {
        return ((SettingsCache$removeConfigs$2) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MutablePreferences mutablePreferences = (MutablePreferences) this.L$0;
            mutablePreferences.clear();
            this.this$0.updateSessionConfigs(mutablePreferences);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
