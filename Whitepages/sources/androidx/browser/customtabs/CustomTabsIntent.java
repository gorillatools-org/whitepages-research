package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseArray;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public final Intent intent;
    public final Bundle startAnimationBundle;

    public void launchUrl(Context context, Uri uri) {
        this.intent.setData(uri);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }

    CustomTabsIntent(Intent intent2, Bundle bundle) {
        this.intent = intent2;
        this.startAnimationBundle = bundle;
    }

    public static final class Builder {
        private ArrayList mActionButtons;
        private SparseArray mColorSchemeParamBundles;
        private final CustomTabColorSchemeParams.Builder mDefaultColorSchemeBuilder = new CustomTabColorSchemeParams.Builder();
        private boolean mInstantAppsEnabled = true;
        private final Intent mIntent = new Intent("android.intent.action.VIEW");
        private ArrayList mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder(CustomTabsSession customTabsSession) {
        }

        private void setSessionParameters(IBinder iBinder, PendingIntent pendingIntent) {
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "android.support.customtabs.extra.SESSION", iBinder);
            if (pendingIntent != null) {
                bundle.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent);
            }
            this.mIntent.putExtras(bundle);
        }

        public CustomTabsIntent build() {
            if (!this.mIntent.hasExtra("android.support.customtabs.extra.SESSION")) {
                setSessionParameters((IBinder) null, (PendingIntent) null);
            }
            ArrayList arrayList = this.mMenuItems;
            if (arrayList != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            ArrayList arrayList2 = this.mActionButtons;
            if (arrayList2 != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", arrayList2);
            }
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            this.mIntent.putExtras(this.mDefaultColorSchemeBuilder.build().toBundle());
            if (this.mColorSchemeParamBundles != null) {
                Bundle bundle = new Bundle();
                bundle.putSparseParcelableArray("androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS", this.mColorSchemeParamBundles);
                this.mIntent.putExtras(bundle);
            }
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }
}
