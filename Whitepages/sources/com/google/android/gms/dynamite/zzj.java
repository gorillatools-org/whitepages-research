package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzj implements DynamiteModule.VersionPolicy {
    zzj() {
    }

    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        int i;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int zza = iVersions.zza(context, str);
        selectionResult.localVersion = zza;
        int i2 = 1;
        int i3 = 0;
        if (zza != 0) {
            i = iVersions.zzb(context, str, false);
            selectionResult.remoteVersion = i;
        } else {
            i = iVersions.zzb(context, str, true);
            selectionResult.remoteVersion = i;
        }
        int i4 = selectionResult.localVersion;
        if (i4 != 0) {
            i3 = i4;
        } else if (i == 0) {
            i2 = 0;
            selectionResult.selection = i2;
            return selectionResult;
        }
        if (i3 >= i) {
            i2 = -1;
        }
        selectionResult.selection = i2;
        return selectionResult;
    }
}
