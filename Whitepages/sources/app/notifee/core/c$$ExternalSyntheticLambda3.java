package app.notifee.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final /* synthetic */ class c$$ExternalSyntheticLambda3 implements Continuation {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ c$$ExternalSyntheticLambda3(int i, List list) {
        this.f$0 = i;
        this.f$1 = list;
    }

    public final Object then(Task task) {
        return c.a(this.f$0, this.f$1, task);
    }
}
