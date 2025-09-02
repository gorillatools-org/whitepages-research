package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.collection.ArrayMap;

public final class zzjx {
    public static final /* synthetic */ int zza = 0;
    private static final ArrayMap zzb = new ArrayMap();

    public static synchronized Uri zza(String str) {
        synchronized (zzjx.class) {
            ArrayMap arrayMap = zzb;
            Uri uri = (Uri) arrayMap.get("com.google.android.gms.measurement");
            if (uri != null) {
                return uri;
            }
            Uri parse = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode("com.google.android.gms.measurement"))));
            arrayMap.put("com.google.android.gms.measurement", parse);
            return parse;
        }
    }
}
