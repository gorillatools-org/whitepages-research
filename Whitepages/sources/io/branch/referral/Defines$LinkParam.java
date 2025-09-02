package io.branch.referral;

import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.storage.db.k;

public enum Defines$LinkParam {
    Tags(k.a.g),
    Alias("alias"),
    Type("type"),
    Duration("duration"),
    Channel("channel"),
    Feature("feature"),
    Stage("stage"),
    Campaign("campaign"),
    Data(Constants.ScionAnalytics.MessageType.DATA_MESSAGE),
    URL("url");
    
    private final String key;

    private Defines$LinkParam(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }
}
