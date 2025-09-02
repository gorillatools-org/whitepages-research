package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.WindowManager;
import java.lang.ref.WeakReference;

public abstract class SplashScreen {
    private static WeakReference mActivity;
    /* access modifiers changed from: private */
    public static Dialog mSplashDialog;

    public static void show(final Activity activity, final int i, final boolean z) {
        if (activity != null) {
            mActivity = new WeakReference(activity);
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (!activity.isFinishing()) {
                        Dialog unused = SplashScreen.mSplashDialog = new Dialog(activity, i);
                        SplashScreen.mSplashDialog.setContentView(R$layout.launch_screen);
                        SplashScreen.mSplashDialog.setCancelable(false);
                        if (z) {
                            SplashScreen.setActivityAndroidP(SplashScreen.mSplashDialog);
                        }
                        if (!SplashScreen.mSplashDialog.isShowing()) {
                            SplashScreen.mSplashDialog.show();
                        }
                    }
                }
            });
        }
    }

    public static void show(Activity activity, boolean z) {
        show(activity, z ? R$style.SplashScreen_Fullscreen : R$style.SplashScreen_SplashTheme, z);
    }

    public static void show(Activity activity) {
        show(activity, false);
    }

    public static void hide(final Activity activity) {
        if (activity == null) {
            WeakReference weakReference = mActivity;
            if (weakReference != null) {
                activity = (Activity) weakReference.get();
            } else {
                return;
            }
        }
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (SplashScreen.mSplashDialog != null && SplashScreen.mSplashDialog.isShowing()) {
                        boolean isDestroyed = activity.isDestroyed();
                        if (!activity.isFinishing() && !isDestroyed) {
                            SplashScreen.mSplashDialog.dismiss();
                        }
                        Dialog unused = SplashScreen.mSplashDialog = null;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void setActivityAndroidP(Dialog dialog) {
        if (Build.VERSION.SDK_INT >= 28 && dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().addFlags(512);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            dialog.getWindow().setAttributes(attributes);
        }
    }
}
