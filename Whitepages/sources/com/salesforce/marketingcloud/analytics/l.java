package com.salesforce.marketingcloud.analytics;

import org.json.JSONObject;

public interface l {

    public enum a {
        SYNC_API("deviceSyncCallTimeMs"),
        TRIGGER_PROCESS("inAppMsgProcessingTimeMs");
        
        private final String a;

        private a(String str) {
            this.a = str;
        }

        public String b() {
            return this.a;
        }
    }

    void a(a aVar, JSONObject jSONObject);
}
