package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.Logger;
import com.facebook.react.uimanager.ViewProps;

public abstract class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    public static void setComponentEnabled(Context context, Class cls, boolean z) {
        String str = "disabled";
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            Logger logger = Logger.get();
            String str2 = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append(" ");
            sb.append(z ? ViewProps.ENABLED : str);
            logger.debug(str2, sb.toString());
        } catch (Exception e) {
            Logger logger2 = Logger.get();
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cls.getName());
            sb2.append("could not be ");
            if (z) {
                str = ViewProps.ENABLED;
            }
            sb2.append(str);
            logger2.debug(str3, sb2.toString(), e);
        }
    }
}
