package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzh implements DynamiteModule.VersionPolicy {
    zzh() {
    }

    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int i = 0;
        int zzb = iVersions.zzb(context, str, false);
        selectionResult.remoteVersion = zzb;
        if (zzb != 0) {
            i = 1;
        }
        selectionResult.selection = i;
        return selectionResult;
    }
}
