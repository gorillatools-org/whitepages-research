package com.facebook.internal;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.salesforce.marketingcloud.UrlHandler;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class InstagramCustomTab extends CustomTab {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Uri getURIForAction(String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
            if (Intrinsics.areEqual((Object) str, (Object) "oauth")) {
                return Utility.buildUri(ServerProtocol.getInstagramDialogAuthority(), "oauth/authorize", bundle);
            }
            String instagramDialogAuthority = ServerProtocol.getInstagramDialogAuthority();
            return Utility.buildUri(instagramDialogAuthority, FacebookSdk.getGraphApiVersion() + "/dialog/" + str, bundle);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstagramCustomTab(String str, Bundle bundle) {
        super(str, bundle);
        Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
        setUri(Companion.getURIForAction(str, bundle == null ? new Bundle() : bundle));
    }
}
