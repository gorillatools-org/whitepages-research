package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.util.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;

public interface g {
    public static final int a = 1;
    public static final int b = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    int a(InAppMessage inAppMessage, c cVar) throws Exception;

    int a(Collection<String> collection);

    InAppMessage a(String str, c cVar);

    InAppMessage a(Collection<String> collection, c cVar);

    void a(InAppMessage inAppMessage);

    void b(String str, int i);

    JSONArray d(c cVar);

    List<String> e(c cVar);
}
