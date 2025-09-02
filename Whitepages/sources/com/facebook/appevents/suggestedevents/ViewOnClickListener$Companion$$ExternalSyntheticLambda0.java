package com.facebook.appevents.suggestedevents;

import com.facebook.appevents.suggestedevents.ViewOnClickListener;

public final /* synthetic */ class ViewOnClickListener$Companion$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ViewOnClickListener$Companion$$ExternalSyntheticLambda0(String str, String str2) {
        this.f$0 = str;
        this.f$1 = str2;
    }

    public final void run() {
        ViewOnClickListener.Companion.queryHistoryAndProcess$lambda$0(this.f$0, this.f$1);
    }
}
