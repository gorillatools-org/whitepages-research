package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.Region;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class j {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final boolean a(Region region) {
            Intrinsics.checkNotNullParameter(region, "region");
            return region.m693isInside();
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Region a(LatLon latLon, int i) {
            Intrinsics.checkNotNullParameter(latLon, "center");
            return Region.Companion.m695magicFence(latLon, i);
        }

        public final void a(Region region, boolean z) {
            Intrinsics.checkNotNullParameter(region, "region");
            region.m692isInside(z);
        }
    }

    public static final boolean a(Region region) {
        return a.a(region);
    }

    public static final Region a(LatLon latLon, int i) {
        return a.a(latLon, i);
    }

    public static final void a(Region region, boolean z) {
        a.a(region, z);
    }
}
