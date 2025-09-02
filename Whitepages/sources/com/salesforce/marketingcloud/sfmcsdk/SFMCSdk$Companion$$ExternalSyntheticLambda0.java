package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;

public final /* synthetic */ class SFMCSdk$Companion$$ExternalSyntheticLambda0 implements SFMCSdkReadyListener {
    public final /* synthetic */ Event[] f$0;

    public /* synthetic */ SFMCSdk$Companion$$ExternalSyntheticLambda0(Event[] eventArr) {
        this.f$0 = eventArr;
    }

    public final void ready(SFMCSdk sFMCSdk) {
        SFMCSdk.Companion.m807track$lambda16(this.f$0, sFMCSdk);
    }
}
