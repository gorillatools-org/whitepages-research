package n.o.t.i.f.e.e;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import app.notifee.core.Logger;
import com.facebook.hermes.intl.Constants;

public abstract class h {
    public static void a(Activity activity, Intent intent) {
        if (activity != null) {
            Context context = e.a;
            if (context == null) {
                Logger.w("IntentUtils", "Unable to get application context when calling startActivityOnUiThread()");
            }
            activity.runOnUiThread(new h$$ExternalSyntheticLambda0(context, intent));
            return;
        }
        Logger.w("IntentUtils", "Activity or intent is null when calling startActivityOnUiThread()");
    }

    public static /* synthetic */ void b(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Logger.e("IntentUtils", "An error occurred whilst trying to start activity on Ui Thread", e);
        }
    }

    public static boolean a(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            try {
                if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                Logger.e("IntentUtils", "An error occurred whilst trying to check if intent is available on device", e);
            }
        }
        return false;
    }

    public static String a(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            String className = intent.getComponent().getClassName();
            int lastIndexOf = className.lastIndexOf(".");
            if (lastIndexOf != -1) {
                return className.substring(lastIndexOf + 1);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static Class a(String str) {
        String str2;
        Class<?> cls;
        if (str == null || str.equals(Constants.COLLATION_DEFAULT)) {
            Intent launchIntentForPackage = e.a.getPackageManager().getLaunchIntentForPackage(e.a.getPackageName());
            str2 = (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) ? null : launchIntentForPackage.getComponent().getClassName();
        } else {
            str2 = str;
        }
        if (str2 == null) {
            Logger.e("ReceiverService", "Launch Activity for notification could not be found.");
            return null;
        }
        try {
            cls = Class.forName(str2);
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls != null) {
            return cls;
        }
        Logger.e("ReceiverService", String.format("Launch Activity for notification does not exist ('%s').", new Object[]{str}));
        return null;
    }
}
