package com.facebook.appevents.suggestedevents;

import org.json.JSONObject;

public final /* synthetic */ class ViewOnClickListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ JSONObject f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ViewOnClickListener f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ ViewOnClickListener$$ExternalSyntheticLambda0(JSONObject jSONObject, String str, ViewOnClickListener viewOnClickListener, String str2) {
        this.f$0 = jSONObject;
        this.f$1 = str;
        this.f$2 = viewOnClickListener;
        this.f$3 = str2;
    }

    public final void run() {
        ViewOnClickListener.predictAndProcess$lambda$0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
