package com.facebook.appevents.gps.ara;

import java.net.URLEncoder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.json.JSONObject;

final class GpsAraTriggersManager$getEventParameters$1 extends Lambda implements Function1 {
    final /* synthetic */ JSONObject $params;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    GpsAraTriggersManager$getEventParameters$1(JSONObject jSONObject) {
        super(1);
        this.$params = jSONObject;
    }

    public final String invoke(String str) {
        Object opt = this.$params.opt(str);
        if (opt == null) {
            return null;
        }
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            String encode2 = URLEncoder.encode(opt.toString(), "UTF-8");
            return encode + '=' + encode2;
        } catch (Exception unused) {
            return null;
        }
    }
}
