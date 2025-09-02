package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class NetworkManager$makeRequest$3$1 extends Lambda implements Function0 {
    final /* synthetic */ Response $it;
    final /* synthetic */ Request $request;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkManager$makeRequest$3$1(Response response, Request request) {
        super(0);
        this.$it = response;
        this.$request = request;
    }

    public final String invoke() {
        return "HTTP response " + this.$it.getCode() + " for " + this.$request.getName() + " request. Request took " + this.$it.timeToExecute() + "ms.";
    }
}
