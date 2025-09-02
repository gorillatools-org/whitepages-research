package app.notifee.core;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class b$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ String f$0;

    public /* synthetic */ b$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final Object then(Task task) {
        return b.a(this.f$0, task);
    }
}
