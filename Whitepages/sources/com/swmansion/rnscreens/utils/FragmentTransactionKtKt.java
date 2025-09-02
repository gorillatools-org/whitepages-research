package com.swmansion.rnscreens.utils;

import androidx.fragment.app.FragmentTransaction;
import com.swmansion.rnscreens.R$anim;
import com.swmansion.rnscreens.Screen;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public abstract class FragmentTransactionKtKt {

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(2:17|18)|19|21) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|21) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.swmansion.rnscreens.Screen$StackAnimation[] r0 = com.swmansion.rnscreens.Screen.StackAnimation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.NONE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.FADE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_RIGHT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_LEFT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_BOTTOM     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.FADE_FROM_BOTTOM     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.IOS_FROM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.swmansion.rnscreens.Screen$StackAnimation r1 = com.swmansion.rnscreens.Screen.StackAnimation.IOS_FROM_LEFT     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.utils.FragmentTransactionKtKt.WhenMappings.<clinit>():void");
        }
    }

    public static final void setTweenAnimations(FragmentTransaction fragmentTransaction, Screen.StackAnimation stackAnimation, boolean z) {
        Intrinsics.checkNotNullParameter(fragmentTransaction, "<this>");
        Intrinsics.checkNotNullParameter(stackAnimation, "stackAnimation");
        if (z) {
            switch (WhenMappings.$EnumSwitchMapping$0[stackAnimation.ordinal()]) {
                case 1:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_default_enter_in, R$anim.rns_default_enter_out);
                    return;
                case 2:
                    int i = R$anim.rns_no_animation_20;
                    fragmentTransaction.setCustomAnimations(i, i);
                    return;
                case 3:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_fade_in, R$anim.rns_fade_out);
                    return;
                case 4:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_slide_in_from_right, R$anim.rns_slide_out_to_left);
                    return;
                case 5:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_slide_in_from_left, R$anim.rns_slide_out_to_right);
                    return;
                case 6:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_slide_in_from_bottom, R$anim.rns_no_animation_medium);
                    return;
                case 7:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_fade_from_bottom, R$anim.rns_no_animation_350);
                    return;
                case 8:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_ios_from_right_foreground_open, R$anim.rns_ios_from_right_background_open);
                    return;
                case 9:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_ios_from_left_foreground_open, R$anim.rns_ios_from_left_background_open);
                    return;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[stackAnimation.ordinal()]) {
                case 1:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_default_exit_in, R$anim.rns_default_exit_out);
                    return;
                case 2:
                    int i2 = R$anim.rns_no_animation_20;
                    fragmentTransaction.setCustomAnimations(i2, i2);
                    return;
                case 3:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_fade_in, R$anim.rns_fade_out);
                    return;
                case 4:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_slide_in_from_left, R$anim.rns_slide_out_to_right);
                    return;
                case 5:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_slide_in_from_right, R$anim.rns_slide_out_to_left);
                    return;
                case 6:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_no_animation_medium, R$anim.rns_slide_out_to_bottom);
                    return;
                case 7:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_no_animation_250, R$anim.rns_fade_to_bottom);
                    return;
                case 8:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_ios_from_right_background_close, R$anim.rns_ios_from_right_foreground_close);
                    return;
                case 9:
                    fragmentTransaction.setCustomAnimations(R$anim.rns_ios_from_left_background_close, R$anim.rns_ios_from_left_foreground_close);
                    return;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }
}
