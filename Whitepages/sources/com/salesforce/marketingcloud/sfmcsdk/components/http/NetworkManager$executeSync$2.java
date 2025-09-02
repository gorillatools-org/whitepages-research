package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

final class NetworkManager$executeSync$2 extends Lambda implements Function0 {
    final /* synthetic */ Ref$ObjectRef $request;
    final /* synthetic */ Ref$ObjectRef $response;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkManager$executeSync$2(Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2) {
        super(0);
        this.$request = ref$ObjectRef;
        this.$response = ref$ObjectRef2;
    }

    public final String invoke() {
        return ((Request) this.$request.element).getName() + " request to " + ((Request) this.$request.element).getUrl() + " took " + ((Response) this.$response.element).timeToExecute() + "ms and resulted in a " + ((Response) this.$response.element).getCode() + " - " + ((Response) this.$response.element).getMessage() + " response.";
    }
}
