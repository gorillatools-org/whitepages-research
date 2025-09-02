package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.text.TextUtilsCompat;
import com.facebook.hermes.intl.Constants;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class I18nUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES = "RCTI18nUtil_makeRTLFlipLeftAndRightStyles";
    private static final String KEY_FOR_PREFS_ALLOWRTL = "RCTI18nUtil_allowRTL";
    private static final String KEY_FOR_PREFS_FORCERTL = "RCTI18nUtil_forceRTL";
    private static final String SHARED_PREFS_NAME = "com.facebook.react.modules.i18nmanager.I18nUtil";
    /* access modifiers changed from: private */
    public static final I18nUtil instance = new I18nUtil();

    public static final I18nUtil getInstance() {
        return Companion.getInstance();
    }

    private I18nUtil() {
    }

    public final boolean isRTL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return applicationHasRtlSupport(context) && (isRTLForced(context) || (isRTLAllowed(context) && isDevicePreferredLanguageRTL()));
    }

    private final boolean applicationHasRtlSupport(Context context) {
        return (context.getApplicationInfo().flags & 4194304) != 0;
    }

    private final boolean isRTLAllowed(Context context) {
        return isPrefSet(context, KEY_FOR_PREFS_ALLOWRTL, true);
    }

    public final void allowRTL(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PREFS_ALLOWRTL, z);
    }

    public final boolean doLeftAndRightSwapInRTL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isPrefSet(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, true);
    }

    public final void swapLeftAndRightInRTL(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, z);
    }

    private final boolean isRTLForced(Context context) {
        if (isPrefSet(context, KEY_FOR_PREFS_FORCERTL, false) || StringsKt.equals(System.getProperty("FORCE_RTL_FOR_TESTING", Constants.CASEFIRST_FALSE), "true", true)) {
            return true;
        }
        return false;
    }

    public final void forceRTL(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        setPref(context, KEY_FOR_PREFS_FORCERTL, z);
    }

    private final boolean isDevicePreferredLanguageRTL() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    private final boolean isPrefSet(Context context, String str, boolean z) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getBoolean(str, z);
    }

    private final void setPref(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFS_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final I18nUtil getInstance() {
            return I18nUtil.instance;
        }

        public final I18nUtil DEPRECATED$getInstance() {
            return getInstance();
        }
    }
}
