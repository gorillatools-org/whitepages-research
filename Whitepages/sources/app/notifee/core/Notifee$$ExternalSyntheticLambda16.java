package app.notifee.core;

import app.notifee.core.interfaces.MethodCallResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class Notifee$$ExternalSyntheticLambda16 implements OnCompleteListener {
    public final /* synthetic */ MethodCallResult f$0;

    public /* synthetic */ Notifee$$ExternalSyntheticLambda16(MethodCallResult methodCallResult) {
        this.f$0 = methodCallResult;
    }

    public final void onComplete(Task task) {
        Notifee.g(this.f$0, task);
    }
}
