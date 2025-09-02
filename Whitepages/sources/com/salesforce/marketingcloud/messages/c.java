package com.salesforce.marketingcloud.messages;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.location.LatLon;

@SuppressLint({"UnknownNullness"})
public interface c {

    public interface a {
        void a(Region region);

        void a(Region region, Message message);

        void b(Region region);
    }

    public interface b {
        void a(MessageResponse messageResponse);
    }

    void a();

    void a(LatLon latLon, String str, MarketingCloudConfig marketingCloudConfig, b bVar);

    void b();

    void c();
}
