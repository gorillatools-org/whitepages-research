package com.google.firebase.sessions.settings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettingsFetcher$doConfigFetch$2", f = "RemoteSettingsFetcher.kt", l = {68, 70, 73}, m = "invokeSuspend")
final class RemoteSettingsFetcher$doConfigFetch$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Map<String, String> $headerOptions;
    final /* synthetic */ Function2 $onFailure;
    final /* synthetic */ Function2 $onSuccess;
    int label;
    final /* synthetic */ RemoteSettingsFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteSettingsFetcher$doConfigFetch$2(RemoteSettingsFetcher remoteSettingsFetcher, Map<String, String> map, Function2 function2, Function2 function22, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteSettingsFetcher;
        this.$headerOptions = map;
        this.$onSuccess = function2;
        this.$onFailure = function22;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RemoteSettingsFetcher$doConfigFetch$2(this.this$0, this.$headerOptions, this.$onSuccess, this.$onFailure, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((RemoteSettingsFetcher$doConfigFetch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            URLConnection openConnection = this.this$0.settingsUrl().openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("Accept", "application/json");
            for (Map.Entry next : this.$headerOptions.entrySet()) {
                httpsURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    ref$ObjectRef.element = readLine;
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                bufferedReader.close();
                inputStream.close();
                JSONObject jSONObject = new JSONObject(sb.toString());
                Function2 function2 = this.$onSuccess;
                this.label = 1;
                if (function2.invoke(jSONObject, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Function2 function22 = this.$onFailure;
                String str = "Bad response code: " + responseCode;
                this.label = 2;
                if (function22.invoke(str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1 || i == 2) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                Function2 function23 = this.$onFailure;
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                this.label = 3;
                if (function23.invoke(message, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 3) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
