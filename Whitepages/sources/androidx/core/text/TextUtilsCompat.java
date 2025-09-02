package androidx.core.text;

import android.text.TextUtils;
import java.util.Locale;

public abstract class TextUtilsCompat {
    public static int getLayoutDirectionFromLocale(Locale locale) {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }
}
