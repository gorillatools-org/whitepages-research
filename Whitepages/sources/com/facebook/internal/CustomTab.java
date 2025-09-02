package com.facebook.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsSession;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.login.CustomTabPrefetchHelper;
import com.salesforce.marketingcloud.UrlHandler;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class CustomTab {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Uri uri;

    public CustomTab(String str, Bundle bundle) {
        Uri uri2;
        Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
        bundle = bundle == null ? new Bundle() : bundle;
        GamingAction[] values = GamingAction.values();
        ArrayList arrayList = new ArrayList(values.length);
        for (GamingAction rawValue : values) {
            arrayList.add(rawValue.getRawValue());
        }
        if (arrayList.contains(str)) {
            uri2 = Utility.buildUri(ServerProtocol.getGamingDialogAuthority(), "/dialog/" + str, bundle);
        } else {
            uri2 = Companion.getURIForAction(str, bundle);
        }
        this.uri = uri2;
    }

    /* access modifiers changed from: protected */
    public final void setUri(Uri uri2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(uri2, "<set-?>");
                this.uri = uri2;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final boolean openCustomTab(Activity activity, String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(activity, "activity");
            CustomTabPrefetchHelper.Companion.getPreparedSessionOnce();
            CustomTabsIntent build = new CustomTabsIntent.Builder((CustomTabsSession) null).build();
            build.intent.setPackage(str);
            try {
                build.launchUrl(activity, this.uri);
                return true;
            } catch (ActivityNotFoundException unused) {
                return false;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Uri getURIForAction(String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
            String dialogAuthority = ServerProtocol.getDialogAuthority();
            return Utility.buildUri(dialogAuthority, FacebookSdk.getGraphApiVersion() + "/dialog/" + str, bundle);
        }
    }
}
