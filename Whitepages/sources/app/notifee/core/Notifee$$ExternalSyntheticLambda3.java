package app.notifee.core;

import app.notifee.core.interfaces.MethodCallResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class Notifee$$ExternalSyntheticLambda3 implements OnCompleteListener {
    public final /* synthetic */ MethodCallResult f$0;

    public /* synthetic */ Notifee$$ExternalSyntheticLambda3(MethodCallResult methodCallResult) {
        this.f$0 = methodCallResult;
    }

    public final void onComplete(Task task) {
        Notifee.l(this.f$0, task);
    }
}
