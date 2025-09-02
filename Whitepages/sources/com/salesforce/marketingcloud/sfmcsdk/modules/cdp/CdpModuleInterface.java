package com.salesforce.marketingcloud.sfmcsdk.modules.cdp;

import com.salesforce.marketingcloud.cdp.consent.Consent;
import com.salesforce.marketingcloud.cdp.events.Event;
import com.salesforce.marketingcloud.cdp.location.Coordinates;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;

public abstract class CdpModuleInterface implements ModuleInterface {
    public abstract Consent getConsent();

    public abstract void setConsent(Consent consent);

    public abstract void setLocation(Coordinates coordinates, long j);

    public abstract void track(Event event);
}
