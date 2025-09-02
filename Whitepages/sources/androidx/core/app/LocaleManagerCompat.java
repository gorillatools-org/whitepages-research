package androidx.core.app;

import android.app.LocaleManager;
import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import androidx.core.os.LocaleListCompat;

public abstract class LocaleManagerCompat {
    public static LocaleListCompat getApplicationLocales(Context context) {
        if (Build.VERSION.SDK_INT < 33) {
            return LocaleListCompat.forLanguageTags(AppLocalesStorageHelper.readLocales(context));
        }
        Object localeManagerForApplication = getLocaleManagerForApplication(context);
        if (localeManagerForApplication != null) {
            return LocaleListCompat.wrap(Api33Impl.localeManagerGetApplicationLocales(localeManagerForApplication));
        }
        return LocaleListCompat.getEmptyLocaleList();
    }

    private static Object getLocaleManagerForApplication(Context context) {
        return context.getSystemService("locale");
    }

    static class Api33Impl {
        static LocaleList localeManagerGetSystemLocales(Object obj) {
            return ((LocaleManager) obj).getSystemLocales();
        }

        static LocaleList localeManagerGetApplicationLocales(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }
    }
}
