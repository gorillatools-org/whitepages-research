package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;

public abstract class ConfigurationCompat {
    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(configuration));
    }

    public static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
        Api24Impl.setLocales(configuration, localeListCompat);
    }

    static class Api24Impl {
        static LocaleList getLocales(Configuration configuration) {
            return configuration.getLocales();
        }

        static void setLocales(Configuration configuration, LocaleListCompat localeListCompat) {
            configuration.setLocales((LocaleList) localeListCompat.unwrap());
        }
    }
}
