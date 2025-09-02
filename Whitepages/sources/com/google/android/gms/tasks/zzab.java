package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;

final class zzab implements Continuation {
    final /* synthetic */ Collection zza;

    zzab(Collection collection) {
        this.zza = collection;
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zza);
        return Tasks.forResult(arrayList);
    }
}
