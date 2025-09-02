package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzjy;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class zze implements zza {
    final Set zza = new HashSet();
    /* access modifiers changed from: private */
    public final AnalyticsConnector.AnalyticsConnectorListener zzb;
    private final AppMeasurementSdk zzc;
    private final zzd zzd;

    public zze(AppMeasurementSdk appMeasurementSdk, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzb = analyticsConnectorListener;
        this.zzc = appMeasurementSdk;
        zzd zzd2 = new zzd(this);
        this.zzd = zzd2;
        appMeasurementSdk.registerOnMeasurementEventListener(zzd2);
    }

    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    public final void zzb(Set set) {
        Set set2 = this.zza;
        set2.clear();
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (hashSet.size() >= 50) {
                break;
            }
            int i = zzc.zza;
            if (str != null && str.length() != 0) {
                int codePointAt = str.codePointAt(0);
                if (!Character.isLetter(codePointAt)) {
                    if (codePointAt == 95) {
                        codePointAt = 95;
                    }
                }
                int length = str.length();
                int charCount = Character.charCount(codePointAt);
                while (true) {
                    if (charCount < length) {
                        int codePointAt2 = str.codePointAt(charCount);
                        if (codePointAt2 != 95 && !Character.isLetterOrDigit(codePointAt2)) {
                            break;
                        }
                        charCount += Character.charCount(codePointAt2);
                    } else if (str.length() != 0) {
                        int codePointAt3 = str.codePointAt(0);
                        if (Character.isLetter(codePointAt3)) {
                            int length2 = str.length();
                            int charCount2 = Character.charCount(codePointAt3);
                            while (true) {
                                if (charCount2 < length2) {
                                    int codePointAt4 = str.codePointAt(charCount2);
                                    if (codePointAt4 != 95 && !Character.isLetterOrDigit(codePointAt4)) {
                                        break;
                                    }
                                    charCount2 += Character.charCount(codePointAt4);
                                } else {
                                    String zzb2 = zzjy.zzb(str);
                                    if (zzb2 != null) {
                                        str = zzb2;
                                    }
                                    Preconditions.checkNotNull(str);
                                    hashSet.add(str);
                                }
                            }
                        }
                    }
                }
            }
        }
        set2.addAll(hashSet);
    }

    public final void zzc() {
        this.zza.clear();
    }
}
