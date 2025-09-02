package com.salesforce.marketingcloud.messages;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.location.LatLon;

@MCKeep
public interface MessageResponse {
    LatLon getRefreshCenter();

    int getRefreshRadius();
}
