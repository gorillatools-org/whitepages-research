package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;

public abstract class ActivityCompat extends ContextCompat {

    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        activity.startActivityForResult(intent, i, bundle);
    }

    public static void startIntentSenderForResult(Activity activity, IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        activity.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public static void finishAffinity(Activity activity) {
        activity.finishAffinity();
    }

    public static void finishAfterTransition(Activity activity) {
        Api21Impl.finishAfterTransition(activity);
    }

    public static Uri getReferrer(Activity activity) {
        return Api22Impl.getReferrer(activity);
    }

    public static void setEnterSharedElementCallback(Activity activity, SharedElementCallback sharedElementCallback) {
        Api21Impl.setEnterSharedElementCallback(activity, (SharedElementCallback) null);
    }

    public static void setExitSharedElementCallback(Activity activity, SharedElementCallback sharedElementCallback) {
        Api21Impl.setExitSharedElementCallback(activity, (SharedElementCallback) null);
    }

    public static void postponeEnterTransition(Activity activity) {
        Api21Impl.postponeEnterTransition(activity);
    }

    public static void startPostponedEnterTransition(Activity activity) {
        Api21Impl.startPostponedEnterTransition(activity);
    }

    public static void requestPermissions(Activity activity, String[] strArr, int i) {
        HashSet hashSet = new HashSet();
        int i2 = 0;
        while (i2 < strArr.length) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(strArr[i2], "android.permission.POST_NOTIFICATIONS")) {
                    hashSet.add(Integer.valueOf(i2));
                }
                i2++;
            } else {
                throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
            }
        }
        int size = hashSet.size();
        String[] strArr2 = size > 0 ? new String[(strArr.length - size)] : strArr;
        if (size > 0) {
            if (size != strArr.length) {
                int i3 = 0;
                for (int i4 = 0; i4 < strArr.length; i4++) {
                    if (!hashSet.contains(Integer.valueOf(i4))) {
                        strArr2[i3] = strArr[i4];
                        i3++;
                    }
                }
            } else {
                return;
            }
        }
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(i);
        }
        Api23Impl.requestPermissions(activity, strArr, i);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        int i = Build.VERSION.SDK_INT;
        if (i < 33 && TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return false;
        }
        if (i >= 32) {
            return Api32Impl.shouldShowRequestPermissionRationale(activity, str);
        }
        if (i == 31) {
            return Api31Impl.shouldShowRequestPermissionRationale(activity, str);
        }
        return Api23Impl.shouldShowRequestPermissionRationale(activity, str);
    }

    public static void recreate(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            new Handler(activity.getMainLooper()).post(new ActivityCompat$$ExternalSyntheticLambda0(activity));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$recreate$0(Activity activity) {
        if (!activity.isFinishing() && !ActivityRecreator.recreate(activity)) {
            activity.recreate();
        }
    }

    static class Api31Impl {
        static boolean isLaunchedFromBubble(Activity activity) {
            return activity.isLaunchedFromBubble();
        }

        @SuppressLint({"BanUncheckedReflection"})
        static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            try {
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class}).invoke(activity.getApplication().getPackageManager(), new Object[]{str})).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return activity.shouldShowRequestPermissionRationale(str);
            }
        }
    }

    static class Api32Impl {
        static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
    }

    static class Api21Impl {
        static void finishAfterTransition(Activity activity) {
            activity.finishAfterTransition();
        }

        static void setEnterSharedElementCallback(Activity activity, SharedElementCallback sharedElementCallback) {
            activity.setEnterSharedElementCallback(sharedElementCallback);
        }

        static void setExitSharedElementCallback(Activity activity, SharedElementCallback sharedElementCallback) {
            activity.setExitSharedElementCallback(sharedElementCallback);
        }

        static void postponeEnterTransition(Activity activity) {
            activity.postponeEnterTransition();
        }

        static void startPostponedEnterTransition(Activity activity) {
            activity.startPostponedEnterTransition();
        }
    }

    static class Api22Impl {
        static Uri getReferrer(Activity activity) {
            return activity.getReferrer();
        }
    }

    static class Api23Impl {
        static void requestPermissions(Activity activity, String[] strArr, int i) {
            activity.requestPermissions(strArr, i);
        }

        static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return activity.shouldShowRequestPermissionRationale(str);
        }

        static void onSharedElementsReady(Object obj) {
            ((SharedElementCallback.OnSharedElementsReadyListener) obj).onSharedElementsReady();
        }
    }
}
