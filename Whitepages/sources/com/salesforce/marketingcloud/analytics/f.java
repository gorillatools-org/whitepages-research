package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.j;
import java.util.List;
import org.json.JSONObject;

public interface f {
    void a(InAppMessage inAppMessage);

    void a(InAppMessage inAppMessage, j jVar);

    void a(InAppMessage inAppMessage, JSONObject jSONObject);

    void a(String str, String str2, List<String> list);

    void b(InAppMessage inAppMessage);
}
