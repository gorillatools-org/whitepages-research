package androidx.browser.customtabs;

import android.os.Bundle;

public final class CustomTabColorSchemeParams {
    public final Integer navigationBarColor;
    public final Integer secondaryToolbarColor;
    public final Integer toolbarColor;

    CustomTabColorSchemeParams(Integer num, Integer num2, Integer num3) {
        this.toolbarColor = num;
        this.secondaryToolbarColor = num2;
        this.navigationBarColor = num3;
    }

    /* access modifiers changed from: package-private */
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        Integer num = this.toolbarColor;
        if (num != null) {
            bundle.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", num.intValue());
        }
        Integer num2 = this.secondaryToolbarColor;
        if (num2 != null) {
            bundle.putInt("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR", num2.intValue());
        }
        Integer num3 = this.navigationBarColor;
        if (num3 != null) {
            bundle.putInt("androidx.browser.customtabs.extra.NAVIGATION_BAR_COLOR", num3.intValue());
        }
        return bundle;
    }

    public static final class Builder {
        private Integer mNavigationBarColor;
        private Integer mSecondaryToolbarColor;
        private Integer mToolbarColor;

        public CustomTabColorSchemeParams build() {
            return new CustomTabColorSchemeParams(this.mToolbarColor, this.mSecondaryToolbarColor, this.mNavigationBarColor);
        }
    }
}
