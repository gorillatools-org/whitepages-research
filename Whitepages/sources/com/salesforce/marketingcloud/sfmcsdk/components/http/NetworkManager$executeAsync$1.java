package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkManager$executeAsync$1 extends Lambda implements Function0 {
    final /* synthetic */ Callback $callback;
    final /* synthetic */ Request $request;
    final /* synthetic */ NetworkManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkManager$executeAsync$1(Callback callback, Request request, NetworkManager networkManager) {
        super(0);
        this.$callback = callback;
        this.$request = request;
        this.this$0 = networkManager;
    }

    public final void invoke() {
        Callback callback = this.$callback;
        Request request = this.$request;
        callback.onResponse(request, this.this$0.executeSync(request));
    }
}
