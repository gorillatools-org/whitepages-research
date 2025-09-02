package com.google.firebase.sessions;

import com.google.android.datatransport.Transformer;

public final /* synthetic */ class EventGDTLogger$$ExternalSyntheticLambda0 implements Transformer {
    public final /* synthetic */ EventGDTLogger f$0;

    public /* synthetic */ EventGDTLogger$$ExternalSyntheticLambda0(EventGDTLogger eventGDTLogger) {
        this.f$0 = eventGDTLogger;
    }

    public final Object apply(Object obj) {
        return this.f$0.encode((SessionEvent) obj);
    }
}
