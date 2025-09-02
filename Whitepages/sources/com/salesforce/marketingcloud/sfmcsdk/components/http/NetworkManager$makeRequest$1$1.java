package com.salesforce.marketingcloud.sfmcsdk.components.http;

import java.net.HttpURLConnection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

final class NetworkManager$makeRequest$1$1 extends Lambda implements Function0 {
    final /* synthetic */ Ref$ObjectRef $connection;
    final /* synthetic */ Request $request;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkManager$makeRequest$1$1(Request request, Ref$ObjectRef ref$ObjectRef) {
        super(0);
        this.$request = request;
        this.$connection = ref$ObjectRef;
    }

    public final String invoke() {
        return this.$request.getName() + ' ' + ((HttpURLConnection) this.$connection.element).getRequestMethod() + " initiated\nwith request properties " + ((HttpURLConnection) this.$connection.element).getRequestProperties() + "\nand body " + this.$request.getRequestBody();
    }
}
