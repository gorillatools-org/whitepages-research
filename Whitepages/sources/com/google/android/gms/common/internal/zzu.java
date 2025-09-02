package com.google.android.gms.common.internal;

import android.net.Uri;

public final class zzu {
    public static final /* synthetic */ int zza = 0;
    private static final Uri zzb;
    private static final Uri zzc;

    static {
        Uri parse = Uri.parse("https://plus.google.com/");
        zzb = parse;
        zzc = parse.buildUpon().appendPath("circles").appendPath("find").build();
    }
}
