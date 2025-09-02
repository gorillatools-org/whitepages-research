package com.google.firebase.analytics;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzff;

public class GoogleAnalyticsServerPreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzff.zzg(this, (String) null, (String) null, (String) null, (Bundle) null).zzN(getIntent());
        finish();
    }
}
