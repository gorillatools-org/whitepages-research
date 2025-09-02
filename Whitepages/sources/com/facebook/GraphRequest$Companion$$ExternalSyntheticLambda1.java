package com.facebook;

import com.facebook.GraphRequest;
import java.util.ArrayList;

public final /* synthetic */ class GraphRequest$Companion$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ GraphRequestBatch f$1;

    public /* synthetic */ GraphRequest$Companion$$ExternalSyntheticLambda1(ArrayList arrayList, GraphRequestBatch graphRequestBatch) {
        this.f$0 = arrayList;
        this.f$1 = graphRequestBatch;
    }

    public final void run() {
        GraphRequest.Companion.runCallbacks$lambda$2(this.f$0, this.f$1);
    }
}
