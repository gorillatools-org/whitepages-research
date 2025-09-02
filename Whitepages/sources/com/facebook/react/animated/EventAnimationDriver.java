package com.facebook.react.animated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import com.salesforce.marketingcloud.config.a;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class EventAnimationDriver implements RCTModernEventEmitter {
    public String eventName;
    private final List<String> eventPath;
    public ValueAnimatedNode valueNode;
    public int viewTag;

    public EventAnimationDriver(String str, int i, List<String> list, ValueAnimatedNode valueAnimatedNode) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(list, "eventPath");
        Intrinsics.checkNotNullParameter(valueAnimatedNode, "valueNode");
        this.eventName = str;
        this.viewTag = i;
        this.eventPath = list;
        this.valueNode = valueAnimatedNode;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(-1, i, str, writableMap);
    }

    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(str, a.h);
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(writableArray, "touches");
        Intrinsics.checkNotNullParameter(writableArray2, "changedIndices");
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    public void receiveTouches(TouchEvent touchEvent) {
        Intrinsics.checkNotNullParameter(touchEvent, "event");
        throw new UnsupportedOperationException("receiveTouches is not support by native animated events");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.facebook.react.bridge.WritableMap, code=com.facebook.react.bridge.ReadableMap, for r8v0, types: [com.facebook.react.bridge.WritableMap] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receiveEvent(int r3, int r4, java.lang.String r5, boolean r6, int r7, com.facebook.react.bridge.ReadableMap r8, int r9) {
        /*
            r2 = this;
            java.lang.String r3 = "eventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
            if (r8 == 0) goto L_0x00d7
            java.util.List<java.lang.String> r3 = r2.eventPath
            int r3 = r3.size()
            int r3 = r3 + -1
            r4 = 0
            r5 = 0
            r6 = r4
        L_0x0012:
            if (r5 >= r3) goto L_0x00ac
            java.lang.String r7 = "'"
            java.lang.String r9 = "Unexpected type "
            if (r8 == 0) goto L_0x005b
            java.util.List<java.lang.String> r6 = r2.eventPath
            java.lang.Object r6 = r6.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            com.facebook.react.bridge.ReadableType r0 = r8.getType(r6)
            com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map
            if (r0 != r1) goto L_0x0031
            com.facebook.react.bridge.ReadableMap r6 = r8.getMap(r6)
        L_0x002e:
            r8 = r6
            r6 = r4
            goto L_0x0089
        L_0x0031:
            com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array
            if (r0 != r1) goto L_0x003b
            com.facebook.react.bridge.ReadableArray r6 = r8.getArray(r6)
        L_0x0039:
            r8 = r4
            goto L_0x0089
        L_0x003b:
            com.facebook.react.bridge.UnexpectedNativeTypeException r3 = new com.facebook.react.bridge.UnexpectedNativeTypeException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r0)
            java.lang.String r5 = " for key '"
            r4.append(r5)
            r4.append(r6)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x005b:
            java.util.List<java.lang.String> r8 = r2.eventPath
            java.lang.Object r8 = r8.get(r5)
            java.lang.String r8 = (java.lang.String) r8
            int r8 = java.lang.Integer.parseInt(r8)
            if (r6 == 0) goto L_0x006e
            com.facebook.react.bridge.ReadableType r0 = r6.getType(r8)
            goto L_0x006f
        L_0x006e:
            r0 = r4
        L_0x006f:
            com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map
            if (r0 != r1) goto L_0x007c
            if (r6 == 0) goto L_0x007a
            com.facebook.react.bridge.ReadableMap r6 = r6.getMap(r8)
            goto L_0x002e
        L_0x007a:
            r6 = r4
            goto L_0x002e
        L_0x007c:
            com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array
            if (r0 != r1) goto L_0x008c
            if (r6 == 0) goto L_0x0087
            com.facebook.react.bridge.ReadableArray r6 = r6.getArray(r8)
            goto L_0x0039
        L_0x0087:
            r6 = r4
            goto L_0x0039
        L_0x0089:
            int r5 = r5 + 1
            goto L_0x0012
        L_0x008c:
            com.facebook.react.bridge.UnexpectedNativeTypeException r3 = new com.facebook.react.bridge.UnexpectedNativeTypeException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r0)
            java.lang.String r5 = " for index '"
            r4.append(r5)
            r4.append(r8)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x00ac:
            java.util.List<java.lang.String> r3 = r2.eventPath
            int r4 = r3.size()
            int r4 = r4 + -1
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            if (r8 == 0) goto L_0x00c5
            com.facebook.react.animated.ValueAnimatedNode r4 = r2.valueNode
            double r5 = r8.getDouble(r3)
            r4.nodeValue = r5
            goto L_0x00d6
        L_0x00c5:
            int r3 = java.lang.Integer.parseInt(r3)
            com.facebook.react.animated.ValueAnimatedNode r4 = r2.valueNode
            if (r6 == 0) goto L_0x00d2
            double r5 = r6.getDouble(r3)
            goto L_0x00d4
        L_0x00d2:
            r5 = 0
        L_0x00d4:
            r4.nodeValue = r5
        L_0x00d6:
            return
        L_0x00d7:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Native animated events must have event data."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.EventAnimationDriver.receiveEvent(int, int, java.lang.String, boolean, int, com.facebook.react.bridge.WritableMap, int):void");
    }
}
