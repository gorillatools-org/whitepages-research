package app.notifee.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class c$$ExternalSyntheticLambda17 implements OnCompleteListener {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ Data f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ c$$ExternalSyntheticLambda17(CallbackToFutureAdapter.Completer completer, Data data, String str) {
        this.f$0 = completer;
        this.f$1 = data;
        this.f$2 = str;
    }

    public final void onComplete(Task task) {
        c.a(this.f$0, this.f$1, this.f$2, task);
    }
}
