package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;
import java.util.List;
import java.util.Set;

public final /* synthetic */ class WorkerUpdater$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ WorkDatabase f$0;
    public final /* synthetic */ WorkSpec f$1;
    public final /* synthetic */ WorkSpec f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ Set f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ WorkerUpdater$$ExternalSyntheticLambda1(WorkDatabase workDatabase, WorkSpec workSpec, WorkSpec workSpec2, List list, String str, Set set, boolean z) {
        this.f$0 = workDatabase;
        this.f$1 = workSpec;
        this.f$2 = workSpec2;
        this.f$3 = list;
        this.f$4 = str;
        this.f$5 = set;
        this.f$6 = z;
    }

    public final void run() {
        WorkerUpdater.updateWorkImpl$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
