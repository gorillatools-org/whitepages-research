package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public final class PointerEventHelper {
    public static final String CLICK = "topClick";
    public static final PointerEventHelper INSTANCE = new PointerEventHelper();
    public static final String POINTER_CANCEL = "topPointerCancel";
    public static final String POINTER_DOWN = "topPointerDown";
    public static final String POINTER_ENTER = "topPointerEnter";
    public static final String POINTER_LEAVE = "topPointerLeave";
    public static final String POINTER_MOVE = "topPointerMove";
    public static final String POINTER_OUT = "topPointerOut";
    public static final String POINTER_OVER = "topPointerOver";
    public static final String POINTER_TYPE_MOUSE = "mouse";
    public static final String POINTER_TYPE_PEN = "pen";
    public static final String POINTER_TYPE_TOUCH = "touch";
    public static final String POINTER_TYPE_UNKNOWN = "";
    public static final String POINTER_UP = "topPointerUp";
    public static final int X_FLAG_SUPPORTS_HOVER = 16777216;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|19) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT[] r0 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.DOWN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.DOWN_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.UP     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.UP_CAPTURE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CANCEL     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CANCEL_CAPTURE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CLICK     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.facebook.react.uimanager.events.PointerEventHelper$EVENT r1 = com.facebook.react.uimanager.events.PointerEventHelper.EVENT.CLICK_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.WhenMappings.<clinit>():void");
        }
    }

    private PointerEventHelper() {
    }

    public enum EVENT {
        CANCEL,
        CANCEL_CAPTURE,
        CLICK,
        CLICK_CAPTURE,
        DOWN,
        DOWN_CAPTURE,
        ENTER,
        ENTER_CAPTURE,
        LEAVE,
        LEAVE_CAPTURE,
        MOVE,
        MOVE_CAPTURE,
        UP,
        UP_CAPTURE,
        OUT,
        OUT_CAPTURE,
        OVER,
        OVER_CAPTURE;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        static {
            EVENT[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public static final int getButtons(String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(str2, "pointerType");
        if (INSTANCE.isExitEvent(str)) {
            return 0;
        }
        if (Intrinsics.areEqual((Object) POINTER_TYPE_TOUCH, (Object) str2)) {
            return 1;
        }
        return i;
    }

    public static final int getButtonChange(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "pointerType");
        if (Intrinsics.areEqual((Object) POINTER_TYPE_TOUCH, (Object) str)) {
            return 0;
        }
        int i3 = i2 ^ i;
        if (i3 == 0) {
            return -1;
        }
        if (i3 == 1) {
            return 0;
        }
        if (i3 == 2) {
            return 2;
        }
        if (i3 == 4) {
            return 1;
        }
        if (i3 != 8) {
            return i3 != 16 ? -1 : 4;
        }
        return 3;
    }

    public static final String getW3CPointerType(int i) {
        if (i == 1) {
            return POINTER_TYPE_TOUCH;
        }
        if (i == 2) {
            return POINTER_TYPE_PEN;
        }
        if (i != 3) {
            return "";
        }
        return POINTER_TYPE_MOUSE;
    }

    public static final boolean isListening(View view, EVENT event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (view == null) {
            return true;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                Object tag = view.getTag(R.id.pointer_events);
                Integer num = tag instanceof Integer ? (Integer) tag : null;
                if (num == null || (num.intValue() & (1 << event.ordinal())) == 0) {
                    return false;
                }
                return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        return 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int getEventCategory(java.lang.String r2) {
        /*
            r0 = 2
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r2.hashCode()
            switch(r1) {
                case -1786514288: goto L_0x004d;
                case -1780335505: goto L_0x0044;
                case -1304584214: goto L_0x0039;
                case -1304316135: goto L_0x0030;
                case -1304250340: goto L_0x0027;
                case -1065042973: goto L_0x001e;
                case 383186882: goto L_0x0015;
                case 1343400710: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0057
        L_0x000c:
            java.lang.String r1 = "topPointerOut"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0056
            goto L_0x0057
        L_0x0015:
            java.lang.String r1 = "topPointerCancel"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0042
            goto L_0x0057
        L_0x001e:
            java.lang.String r1 = "topPointerUp"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0042
            goto L_0x0057
        L_0x0027:
            java.lang.String r1 = "topPointerOver"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0056
            goto L_0x0057
        L_0x0030:
            java.lang.String r1 = "topPointerMove"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0056
            goto L_0x0057
        L_0x0039:
            java.lang.String r1 = "topPointerDown"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0042
            goto L_0x0057
        L_0x0042:
            r0 = 3
            goto L_0x0057
        L_0x0044:
            java.lang.String r1 = "topPointerLeave"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0056
            goto L_0x0057
        L_0x004d:
            java.lang.String r1 = "topPointerEnter"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r0 = 4
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.getEventCategory(java.lang.String):int");
    }

    public final boolean supportsHover(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        if ((motionEvent.getFlags() & 16777216) != 0) {
            return true;
        }
        return motionEvent.isFromSource(8194);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r3.hashCode();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isExitEvent(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0032
            int r0 = r3.hashCode()
            r1 = -1780335505(0xffffffff95e23c6f, float:-9.137602E-26)
            if (r0 == r1) goto L_0x0028
            r1 = -1065042973(0xffffffffc084bbe3, float:-4.1479354)
            if (r0 == r1) goto L_0x001f
            r1 = 1343400710(0x5012ab06, float:9.8427269E9)
            if (r0 == r1) goto L_0x0016
            goto L_0x0032
        L_0x0016:
            java.lang.String r0 = "topPointerOut"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0030
            goto L_0x0032
        L_0x001f:
            java.lang.String r0 = "topPointerUp"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0030
            goto L_0x0032
        L_0x0028:
            java.lang.String r0 = "topPointerLeave"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
        L_0x0030:
            r3 = 1
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.isExitEvent(java.lang.String):boolean");
    }

    public static final double getPressure(int i, String str) {
        return (!INSTANCE.isExitEvent(str) && i != 0) ? 0.5d : 0.0d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isBubblingEvent(java.lang.String r1) {
        /*
            if (r1 == 0) goto L_0x0042
            int r0 = r1.hashCode()
            switch(r0) {
                case -1304584214: goto L_0x0037;
                case -1304316135: goto L_0x002e;
                case -1304250340: goto L_0x0025;
                case -1065042973: goto L_0x001c;
                case 383186882: goto L_0x0013;
                case 1343400710: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0042
        L_0x000a:
            java.lang.String r0 = "topPointerOut"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0013:
            java.lang.String r0 = "topPointerCancel"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            goto L_0x0042
        L_0x001c:
            java.lang.String r0 = "topPointerUp"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0025:
            java.lang.String r0 = "topPointerOver"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0042
            goto L_0x0040
        L_0x002e:
            java.lang.String r0 = "topPointerMove"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0037:
            java.lang.String r0 = "topPointerDown"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r1 = 1
            goto L_0x0043
        L_0x0042:
            r1 = 0
        L_0x0043:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.isBubblingEvent(java.lang.String):boolean");
    }
}
