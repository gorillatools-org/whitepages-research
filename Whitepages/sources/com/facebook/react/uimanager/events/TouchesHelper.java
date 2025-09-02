package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import kotlin.jvm.internal.Intrinsics;

public final class TouchesHelper {
    private static final String CHANGED_TOUCHES_KEY = "changedTouches";
    public static final TouchesHelper INSTANCE = new TouchesHelper();
    private static final String LOCATION_X_KEY = "locationX";
    private static final String LOCATION_Y_KEY = "locationY";
    private static final String PAGE_X_KEY = "pageX";
    private static final String PAGE_Y_KEY = "pageY";
    private static final String POINTER_IDENTIFIER_KEY = "identifier";
    public static final String TARGET_KEY = "target";
    private static final String TARGET_SURFACE_KEY = "targetSurface";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String TOUCHES_KEY = "touches";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.uimanager.events.TouchEventType[] r0 = com.facebook.react.uimanager.events.TouchEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.START     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.END     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.MOVE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.events.TouchEventType r1 = com.facebook.react.uimanager.events.TouchEventType.CANCEL     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.TouchesHelper.WhenMappings.<clinit>():void");
        }
    }

    public static /* synthetic */ void getTARGET_KEY$annotations() {
    }

    private TouchesHelper() {
    }

    private final WritableMap[] createPointersArray(TouchEvent touchEvent) {
        MotionEvent motionEvent = touchEvent.getMotionEvent();
        WritableMap[] writableMapArr = new WritableMap[motionEvent.getPointerCount()];
        float x = motionEvent.getX() - touchEvent.getViewX();
        float y = motionEvent.getY() - touchEvent.getViewY();
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            WritableMap createMap = Arguments.createMap();
            PixelUtil pixelUtil = PixelUtil.INSTANCE;
            createMap.putDouble(PAGE_X_KEY, (double) pixelUtil.pxToDp(motionEvent.getX(i)));
            createMap.putDouble(PAGE_Y_KEY, (double) pixelUtil.pxToDp(motionEvent.getY(i)));
            createMap.putDouble(LOCATION_X_KEY, (double) pixelUtil.pxToDp(motionEvent.getX(i) - x));
            createMap.putDouble(LOCATION_Y_KEY, (double) pixelUtil.pxToDp(motionEvent.getY(i) - y));
            createMap.putInt(TARGET_SURFACE_KEY, touchEvent.getSurfaceId());
            createMap.putInt(TARGET_KEY, touchEvent.getViewTag());
            createMap.putDouble("timestamp", (double) touchEvent.getTimestampMs());
            createMap.putDouble(POINTER_IDENTIFIER_KEY, (double) motionEvent.getPointerId(i));
            writableMapArr[i] = createMap;
        }
        return writableMapArr;
    }

    public static final void sendTouchesLegacy(RCTEventEmitter rCTEventEmitter, TouchEvent touchEvent) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        Intrinsics.checkNotNullParameter(touchEvent, "touchEvent");
        TouchEventType touchEventType = touchEvent.getTouchEventType();
        TouchesHelper touchesHelper = INSTANCE;
        WritableArray writableArray = touchesHelper.getWritableArray(false, touchesHelper.createPointersArray(touchEvent));
        MotionEvent motionEvent = touchEvent.getMotionEvent();
        WritableArray createArray = Arguments.createArray();
        if (touchEventType == TouchEventType.MOVE || touchEventType == TouchEventType.CANCEL) {
            int pointerCount = motionEvent.getPointerCount();
            for (int i = 0; i < pointerCount; i++) {
                createArray.pushInt(i);
            }
        } else if (touchEventType == TouchEventType.START || touchEventType == TouchEventType.END) {
            createArray.pushInt(motionEvent.getActionIndex());
        } else {
            throw new RuntimeException("Unknown touch type: " + touchEventType);
        }
        String jSEventName = TouchEventType.Companion.getJSEventName(touchEventType);
        Intrinsics.checkNotNull(createArray);
        rCTEventEmitter.receiveTouches(jSEventName, writableArray, createArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x009f A[Catch:{ all -> 0x0055 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void sendTouchEvent(com.facebook.react.uimanager.events.RCTModernEventEmitter r17, com.facebook.react.uimanager.events.TouchEvent r18) {
        /*
            r0 = r18
            r1 = 0
            r2 = 1
            java.lang.String r3 = "eventEmitter"
            r12 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r3)
            java.lang.String r3 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = r18.getEventName()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "TouchesHelper.sentTouchEventModern("
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = ")"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r13 = 0
            com.facebook.systrace.Systrace.beginSection(r13, r3)
            com.facebook.react.uimanager.events.TouchEventType r3 = r18.getTouchEventType()     // Catch:{ all -> 0x0055 }
            android.view.MotionEvent r4 = r18.getMotionEvent()     // Catch:{ all -> 0x0055 }
            com.facebook.react.uimanager.events.TouchesHelper r5 = INSTANCE     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableMap[] r5 = r5.createPointersArray(r0)     // Catch:{ all -> 0x0055 }
            int[] r6 = com.facebook.react.uimanager.events.TouchesHelper.WhenMappings.$EnumSwitchMapping$0     // Catch:{ all -> 0x0055 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x0055 }
            r3 = r6[r3]     // Catch:{ all -> 0x0055 }
            r15 = 0
            if (r3 == r2) goto L_0x0081
            r6 = 2
            if (r3 == r6) goto L_0x0074
            r4 = 3
            if (r3 == r4) goto L_0x005e
            r4 = 4
            if (r3 != r4) goto L_0x0058
            com.facebook.react.bridge.WritableMap[] r1 = new com.facebook.react.bridge.WritableMap[r1]     // Catch:{ all -> 0x0055 }
            r3 = r5
            goto L_0x0095
        L_0x0055:
            r0 = move-exception
            goto L_0x00e1
        L_0x0058:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException     // Catch:{ all -> 0x0055 }
            r0.<init>()     // Catch:{ all -> 0x0055 }
            throw r0     // Catch:{ all -> 0x0055 }
        L_0x005e:
            int r3 = r5.length     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableMap[] r3 = new com.facebook.react.bridge.WritableMap[r3]     // Catch:{ all -> 0x0055 }
        L_0x0061:
            int r4 = r5.length     // Catch:{ all -> 0x0055 }
            if (r1 >= r4) goto L_0x0072
            r4 = r5[r1]     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x006d
            com.facebook.react.bridge.WritableMap r4 = r4.copy()     // Catch:{ all -> 0x0055 }
            goto L_0x006e
        L_0x006d:
            r4 = r15
        L_0x006e:
            r3[r1] = r4     // Catch:{ all -> 0x0055 }
            int r1 = r1 + r2
            goto L_0x0061
        L_0x0072:
            r1 = r5
            goto L_0x0095
        L_0x0074:
            int r3 = r4.getActionIndex()     // Catch:{ all -> 0x0055 }
            r4 = r5[r3]     // Catch:{ all -> 0x0055 }
            r5[r3] = r15     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableMap[] r3 = new com.facebook.react.bridge.WritableMap[r2]     // Catch:{ all -> 0x0055 }
            r3[r1] = r4     // Catch:{ all -> 0x0055 }
            goto L_0x0072
        L_0x0081:
            int r3 = r4.getActionIndex()     // Catch:{ all -> 0x0055 }
            r3 = r5[r3]     // Catch:{ all -> 0x0055 }
            if (r3 == 0) goto L_0x008e
            com.facebook.react.bridge.WritableMap r3 = r3.copy()     // Catch:{ all -> 0x0055 }
            goto L_0x008f
        L_0x008e:
            r3 = r15
        L_0x008f:
            com.facebook.react.bridge.WritableMap[] r4 = new com.facebook.react.bridge.WritableMap[r2]     // Catch:{ all -> 0x0055 }
            r4[r1] = r3     // Catch:{ all -> 0x0055 }
            r3 = r4
            goto L_0x0072
        L_0x0095:
            java.util.Iterator r16 = kotlin.jvm.internal.ArrayIteratorKt.iterator(r3)     // Catch:{ all -> 0x0055 }
        L_0x0099:
            boolean r4 = r16.hasNext()     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x00dd
            java.lang.Object r4 = r16.next()     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableMap r4 = (com.facebook.react.bridge.WritableMap) r4     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x00c1
            com.facebook.react.bridge.WritableMap r4 = r4.copy()     // Catch:{ all -> 0x0055 }
            com.facebook.react.uimanager.events.TouchesHelper r5 = INSTANCE     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableArray r6 = r5.getWritableArray(r2, r3)     // Catch:{ all -> 0x0055 }
            com.facebook.react.bridge.WritableArray r5 = r5.getWritableArray(r2, r1)     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = "changedTouches"
            r4.putArray(r7, r6)     // Catch:{ all -> 0x0055 }
            java.lang.String r6 = "touches"
            r4.putArray(r6, r5)     // Catch:{ all -> 0x0055 }
            r10 = r4
            goto L_0x00c2
        L_0x00c1:
            r10 = r15
        L_0x00c2:
            int r5 = r18.getSurfaceId()     // Catch:{ all -> 0x0055 }
            int r6 = r18.getViewTag()     // Catch:{ all -> 0x0055 }
            java.lang.String r7 = r18.getEventName()     // Catch:{ all -> 0x0055 }
            boolean r8 = r18.canCoalesce()     // Catch:{ all -> 0x0055 }
            int r11 = r18.getEventCategory()     // Catch:{ all -> 0x0055 }
            r9 = 0
            r4 = r17
            r4.receiveEvent(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0055 }
            goto L_0x0099
        L_0x00dd:
            com.facebook.systrace.Systrace.endSection(r13)
            return
        L_0x00e1:
            com.facebook.systrace.Systrace.endSection(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.TouchesHelper.sendTouchEvent(com.facebook.react.uimanager.events.RCTModernEventEmitter, com.facebook.react.uimanager.events.TouchEvent):void");
    }

    private final WritableArray getWritableArray(boolean z, WritableMap[] writableMapArr) {
        WritableArray createArray = Arguments.createArray();
        for (WritableMap writableMap : writableMapArr) {
            if (writableMap != null) {
                if (z) {
                    writableMap = writableMap.copy();
                }
                createArray.pushMap(writableMap);
            }
        }
        Intrinsics.checkNotNull(createArray);
        return createArray;
    }
}
