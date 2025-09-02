package n.o.t.i.f.e.e;

import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.model.NotificationAndroidPressActionModel;
import app.notifee.core.model.NotificationModel;

public abstract class k {
    public static void a(Context context, Intent intent) {
        NotificationAndroidPressActionModel notificationAndroidPressActionModel;
        CharSequence charSequence;
        if (intent.hasExtra("notification")) {
            if (e.a == null) {
                e.a(context.getApplicationContext());
            }
            int intExtra = intent.getIntExtra("notifee_event_type", 0);
            if (intExtra != 0) {
                if (intExtra == 1) {
                    Bundle bundleExtra = intent.getBundleExtra("notification");
                    if (bundleExtra != null) {
                        NotificationModel notificationModel = new NotificationModel(bundleExtra);
                        Bundle bundleExtra2 = intent.getBundleExtra("pressAction");
                        Bundle bundle = new Bundle();
                        if (bundleExtra2 != null) {
                            notificationAndroidPressActionModel = NotificationAndroidPressActionModel.fromBundle(bundleExtra2);
                            bundle.putBundle("pressAction", notificationAndroidPressActionModel.toBundle());
                        } else {
                            notificationAndroidPressActionModel = null;
                        }
                        f.a(new NotificationEvent(1, notificationModel, bundle));
                        if (notificationAndroidPressActionModel != null) {
                            String mainComponent = notificationAndroidPressActionModel.getMainComponent();
                            f.b(new g(notificationModel, bundle));
                            if (mainComponent != null) {
                                f.b(new i(mainComponent));
                            }
                        }
                    }
                } else if (intExtra == 2) {
                    Bundle bundleExtra3 = intent.getBundleExtra("notification");
                    Bundle bundleExtra4 = intent.getBundleExtra("pressAction");
                    if (bundleExtra3 != null && bundleExtra4 != null) {
                        NotificationModel notificationModel2 = new NotificationModel(bundleExtra3);
                        NotificationAndroidPressActionModel fromBundle = NotificationAndroidPressActionModel.fromBundle(bundleExtra4);
                        Bundle bundle2 = new Bundle();
                        bundle2.putBundle("pressAction", fromBundle.toBundle());
                        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                        if (!(resultsFromIntent == null || (charSequence = resultsFromIntent.getCharSequence("app.notifee.core.ReceiverService.REMOTE_INPUT_RECEIVER_KEY")) == null)) {
                            bundle2.putString("input", charSequence.toString());
                        }
                        if (notificationModel2.a().getAutoCancel().booleanValue()) {
                            NotificationManagerCompat.from(context).cancel(intent.getIntExtra("notification_id", 0));
                        }
                        f.b(new g(notificationModel2, bundle2));
                        f.a(new NotificationEvent(2, notificationModel2, bundle2));
                    }
                }
            }
        }
    }
}
