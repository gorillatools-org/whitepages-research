package app.notifee.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c$$ExternalSyntheticLambda8 implements Continuation {
    public final /* synthetic */ int f$0;

    public /* synthetic */ c$$ExternalSyntheticLambda8(int i) {
        this.f$0 = i;
    }

    public final Object then(Task task) {
        return c.a(this.f$0, task);
    }
}
