package app.notifee.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.l;

public class BlockStateBroadcastReceiver extends BroadcastReceiver {
    public static void a(Data data, CallbackToFutureAdapter.Completer completer) {
        Logger.v("BlockState", "starting background work");
        boolean z = data.getBoolean("blocked", false);
        int i = data.getInt("type", 4);
        BlockStateBroadcastReceiver$$ExternalSyntheticLambda0 blockStateBroadcastReceiver$$ExternalSyntheticLambda0 = new BlockStateBroadcastReceiver$$ExternalSyntheticLambda0(completer, i, z);
        if (i == 4) {
            blockStateBroadcastReceiver$$ExternalSyntheticLambda0.a((Object) null);
            return;
        }
        BlockStateBroadcastReceiver$$ExternalSyntheticLambda1 blockStateBroadcastReceiver$$ExternalSyntheticLambda1 = new BlockStateBroadcastReceiver$$ExternalSyntheticLambda1(completer, blockStateBroadcastReceiver$$ExternalSyntheticLambda0);
        String string = data.getString("channelOrGroupId");
        if (i == 5) {
            Notifee.getInstance().getChannel(string, blockStateBroadcastReceiver$$ExternalSyntheticLambda1);
        } else if (i == 6) {
            Notifee.getInstance().getChannelGroup(string, blockStateBroadcastReceiver$$ExternalSyntheticLambda1);
        } else {
            Logger.e("BlockState", "unknown block state work type");
            completer.set(ListenableWorker.Result.success());
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action;
        if (Build.VERSION.SDK_INT >= 28 && (action = intent.getAction()) != null) {
            if (e.a == null) {
                e.a(context.getApplicationContext());
            }
            Data.Builder builder = new Data.Builder();
            builder.putString("workType", "app.notifee.core.BlockStateBroadcastReceiver.WORKER");
            char c = 65535;
            switch (action.hashCode()) {
                case 452039370:
                    if (action.equals("android.app.action.APP_BLOCK_STATE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    break;
                case 806551504:
                    if (action.equals("android.app.action.NOTIFICATION_CHANNEL_GROUP_BLOCK_STATE_CHANGED")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1171977904:
                    if (action.equals("android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    builder.putInt("type", 4);
                    break;
                case 1:
                    builder.putInt("type", 6);
                    String stringExtra = intent.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_GROUP_ID");
                    builder.putString("channelOrGroupId", stringExtra);
                    action = action + "." + stringExtra;
                    break;
                case 2:
                    builder.putInt("type", 5);
                    String stringExtra2 = intent.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_ID");
                    builder.putString("channelOrGroupId", stringExtra2);
                    action = action + "." + stringExtra2;
                    break;
                default:
                    Logger.d("BlockState", "unknown intent action received, ignoring.");
                    return;
            }
            builder.putBoolean("blocked", intent.getBooleanExtra("android.app.extra.BLOCKED_STATE", false));
            try {
                WorkManager.getInstance(e.a).enqueueUniqueWork(action, ExistingWorkPolicy.REPLACE, (OneTimeWorkRequest) ((OneTimeWorkRequest.Builder) ((OneTimeWorkRequest.Builder) new OneTimeWorkRequest.Builder(Worker.class).setInitialDelay(1, TimeUnit.SECONDS)).setInputData(builder.build())).build());
            } catch (IllegalStateException e) {
                Logger.e("BlockState", "Error while calling WorkManager.getInstance", (Exception) e);
                if (e.a == null) {
                    Logger.e("BlockState", "Application Context is null");
                }
            }
            Logger.v("BlockState", "scheduled new background work with id " + action);
        }
    }

    public static /* synthetic */ void a(CallbackToFutureAdapter.Completer completer, Exception exc, Void voidR) {
        if (exc != null) {
            Logger.e("BlockState", "background work failed with error: ", exc);
            completer.set(ListenableWorker.Result.failure());
            return;
        }
        Logger.v("BlockState", "background work completed successfully");
        completer.set(ListenableWorker.Result.success());
    }

    public static /* synthetic */ void a(CallbackToFutureAdapter.Completer completer, l.a aVar, Exception exc, Bundle bundle) {
        if (exc != null) {
            Logger.e("BlockState", "Failed getting channel or channel group bundle, received error: ", exc);
            completer.set(ListenableWorker.Result.success());
            return;
        }
        aVar.a(bundle);
    }
}
