package com.facebook.react.views.image;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.horcrux.svg.events.SvgLoadEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final String errorMessage;
    private final int eventType;
    private final int height;
    private final int loaded;
    private final String sourceUri;
    private final int total;
    private final int width;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageEventType {
    }

    public /* synthetic */ ImageLoadEvent(int i, int i2, int i3, String str, String str2, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str, str2, i4, i5, i6, i7);
    }

    public static final ImageLoadEvent createErrorEvent(int i, int i2, Throwable th) {
        return Companion.createErrorEvent(i, i2, th);
    }

    public static final ImageLoadEvent createErrorEvent(int i, Throwable th) {
        return Companion.createErrorEvent(i, th);
    }

    public static final ImageLoadEvent createLoadEndEvent(int i) {
        return Companion.createLoadEndEvent(i);
    }

    public static final ImageLoadEvent createLoadEndEvent(int i, int i2) {
        return Companion.createLoadEndEvent(i, i2);
    }

    public static final ImageLoadEvent createLoadEvent(int i, int i2, String str, int i3, int i4) {
        return Companion.createLoadEvent(i, i2, str, i3, i4);
    }

    public static final ImageLoadEvent createLoadEvent(int i, String str, int i2, int i3) {
        return Companion.createLoadEvent(i, str, i2, i3);
    }

    public static final ImageLoadEvent createLoadStartEvent(int i) {
        return Companion.createLoadStartEvent(i);
    }

    public static final ImageLoadEvent createLoadStartEvent(int i, int i2) {
        return Companion.createLoadStartEvent(i, i2);
    }

    public static final ImageLoadEvent createProgressEvent(int i, int i2, String str, int i3, int i4) {
        return Companion.createProgressEvent(i, i2, str, i3, i4);
    }

    public static final ImageLoadEvent createProgressEvent(int i, String str, int i2, int i3) {
        return Companion.createProgressEvent(i, str, i2, i3);
    }

    public static final String eventNameForType(int i) {
        return Companion.eventNameForType(i);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ ImageLoadEvent(int r14, int r15, int r16, java.lang.String r17, java.lang.String r18, int r19, int r20, int r21, int r22, int r23, kotlin.jvm.internal.DefaultConstructorMarker r24) {
        /*
            r13 = this;
            r0 = r23
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r17
        L_0x000b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0011
            r8 = r2
            goto L_0x0013
        L_0x0011:
            r8 = r18
        L_0x0013:
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L_0x001a
            r9 = r2
            goto L_0x001c
        L_0x001a:
            r9 = r19
        L_0x001c:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0022
            r10 = r2
            goto L_0x0024
        L_0x0022:
            r10 = r20
        L_0x0024:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x002a
            r11 = r2
            goto L_0x002c
        L_0x002a:
            r11 = r21
        L_0x002c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0032
            r12 = r2
            goto L_0x0034
        L_0x0032:
            r12 = r22
        L_0x0034:
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ImageLoadEvent.<init>(int, int, int, java.lang.String, java.lang.String, int, int, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private ImageLoadEvent(int i, int i2, int i3, String str, String str2, int i4, int i5, int i6, int i7) {
        super(i, i2);
        this.eventType = i3;
        this.errorMessage = str;
        this.sourceUri = str2;
        this.width = i4;
        this.height = i5;
        this.loaded = i6;
        this.total = i7;
    }

    public String getEventName() {
        return Companion.eventNameForType(this.eventType);
    }

    public short getCoalescingKey() {
        return (short) this.eventType;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        int i = this.eventType;
        if (i == 1) {
            createMap.putString("error", this.errorMessage);
        } else if (i == 2) {
            createMap.putMap("source", createEventDataSource());
        } else if (i == 5) {
            createMap.putInt("loaded", this.loaded);
            createMap.putInt("total", this.total);
            createMap.putDouble(ReactProgressBarViewManager.PROP_PROGRESS, ((double) this.loaded) / ((double) this.total));
        }
        return createMap;
    }

    private final WritableMap createEventDataSource() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", this.sourceUri);
        createMap.putDouble("width", (double) this.width);
        createMap.putDouble("height", (double) this.height);
        Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
        return createMap;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageLoadEvent createLoadStartEvent(int i) {
            return createLoadStartEvent(-1, i);
        }

        public final ImageLoadEvent createProgressEvent(int i, String str, int i2, int i3) {
            return createProgressEvent(-1, i, str, i2, i3);
        }

        public final ImageLoadEvent createLoadEvent(int i, String str, int i2, int i3) {
            return createLoadEvent(-1, i, str, i2, i3);
        }

        public final ImageLoadEvent createErrorEvent(int i, Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            return createErrorEvent(-1, i, th);
        }

        public final ImageLoadEvent createLoadEndEvent(int i) {
            return createLoadEndEvent(-1, i);
        }

        public final ImageLoadEvent createLoadStartEvent(int i, int i2) {
            return new ImageLoadEvent(i, i2, 4, (String) null, (String) null, 0, 0, 0, 0, 504, (DefaultConstructorMarker) null);
        }

        public final ImageLoadEvent createProgressEvent(int i, int i2, String str, int i3, int i4) {
            return new ImageLoadEvent(i, i2, 5, (String) null, str, 0, 0, i3, i4, (DefaultConstructorMarker) null);
        }

        public final ImageLoadEvent createLoadEvent(int i, int i2, String str, int i3, int i4) {
            return new ImageLoadEvent(i, i2, 2, (String) null, str, i3, i4, 0, 0, (DefaultConstructorMarker) null);
        }

        public final ImageLoadEvent createErrorEvent(int i, int i2, Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            return new ImageLoadEvent(i, i2, 1, th.getMessage(), (String) null, 0, 0, 0, 0, (DefaultConstructorMarker) null);
        }

        public final ImageLoadEvent createLoadEndEvent(int i, int i2) {
            return new ImageLoadEvent(i, i2, 3, (String) null, (String) null, 0, 0, 0, 0, 504, (DefaultConstructorMarker) null);
        }

        public final String eventNameForType(int i) {
            if (i == 1) {
                return "topError";
            }
            if (i == 2) {
                return SvgLoadEvent.EVENT_NAME;
            }
            if (i == 3) {
                return "topLoadEnd";
            }
            if (i == 4) {
                return "topLoadStart";
            }
            if (i == 5) {
                return "topProgress";
            }
            throw new IllegalStateException(("Invalid image event: " + i).toString());
        }
    }
}
