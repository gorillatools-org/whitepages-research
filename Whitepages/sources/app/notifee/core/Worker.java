package app.notifee.core;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;

public class Worker extends ListenableWorker {
    public CallbackToFutureAdapter.Completer a;

    @Keep
    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object a(CallbackToFutureAdapter.Completer completer) {
        this.a = completer;
        String string = getInputData().getString("workType");
        if (string == null) {
            Logger.d("Worker", "received task with no input key type.");
            completer.set(ListenableWorker.Result.success());
            return "Worker.startWork operation cancelled - no input.";
        } else if (string.equals("app.notifee.core.BlockStateBroadcastReceiver.WORKER")) {
            Logger.d("Worker", "received task with type " + string);
            BlockStateBroadcastReceiver.a(getInputData(), completer);
            return "Worker.startWork operation created successfully.";
        } else if (!string.equals("app.notifee.core.NotificationManager.TRIGGER")) {
            Logger.d("Worker", "unknown work type received: " + string);
            completer.set(ListenableWorker.Result.success());
            return "Worker.startWork operation cancelled - unknown work type.";
        } else {
            c.a(getInputData(), completer);
            return "Worker.startWork operation created successfully.";
        }
    }

    public void onStopped() {
        CallbackToFutureAdapter.Completer completer = this.a;
        if (completer != null) {
            completer.set(ListenableWorker.Result.failure());
        }
        this.a = null;
    }

    public ListenableFuture startWork() {
        return CallbackToFutureAdapter.getFuture(new Worker$$ExternalSyntheticLambda0(this));
    }
}
