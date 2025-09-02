package com.facebook.react.views.scroll;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactScrollViewCommandHelper {
    public static final int COMMAND_FLASH_SCROLL_INDICATORS = 3;
    public static final int COMMAND_SCROLL_TO = 1;
    public static final int COMMAND_SCROLL_TO_END = 2;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public interface ScrollCommandHandler<T> {
        void flashScrollIndicators(T t);

        void scrollTo(T t, ScrollToCommandData scrollToCommandData);

        void scrollToEnd(T t, ScrollToEndCommandData scrollToEndCommandData);
    }

    public static final Map<String, Integer> getCommandsMap() {
        return Companion.getCommandsMap();
    }

    public static final <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, int i, ReadableArray readableArray) {
        Companion.receiveCommand(scrollCommandHandler, t, i, readableArray);
    }

    public static final <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, String str, ReadableArray readableArray) {
        Companion.receiveCommand(scrollCommandHandler, t, str, readableArray);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Integer> getCommandsMap() {
            return MapsKt.hashMapOf(TuplesKt.to("scrollTo", 1), TuplesKt.to("scrollToEnd", 2), TuplesKt.to("flashScrollIndicators", 3));
        }

        public final <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, int i, ReadableArray readableArray) {
            Intrinsics.checkNotNullParameter(scrollCommandHandler, "viewManager");
            if (t == null) {
                throw new IllegalStateException("Required value was null.");
            } else if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        scrollCommandHandler.flashScrollIndicators(t);
                        return;
                    }
                    String simpleName = scrollCommandHandler.getClass().getSimpleName();
                    throw new IllegalArgumentException("Unsupported command " + i + " received by " + simpleName + ".");
                } else if (readableArray != null) {
                    scrollToEnd(scrollCommandHandler, t, readableArray);
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            } else if (readableArray != null) {
                scrollTo(scrollCommandHandler, t, readableArray);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }

        public final <T> void receiveCommand(ScrollCommandHandler<T> scrollCommandHandler, T t, String str, ReadableArray readableArray) {
            Intrinsics.checkNotNullParameter(scrollCommandHandler, "viewManager");
            Intrinsics.checkNotNullParameter(str, "commandType");
            if (t != null) {
                int hashCode = str.hashCode();
                if (hashCode != -402165208) {
                    if (hashCode != 28425985) {
                        if (hashCode == 2055114131 && str.equals("scrollToEnd")) {
                            if (readableArray != null) {
                                scrollToEnd(scrollCommandHandler, t, readableArray);
                                return;
                            }
                            throw new IllegalStateException("Required value was null.");
                        }
                    } else if (str.equals("flashScrollIndicators")) {
                        scrollCommandHandler.flashScrollIndicators(t);
                        return;
                    }
                } else if (str.equals("scrollTo")) {
                    if (readableArray != null) {
                        scrollTo(scrollCommandHandler, t, readableArray);
                        return;
                    }
                    throw new IllegalStateException("Required value was null.");
                }
                String simpleName = scrollCommandHandler.getClass().getSimpleName();
                throw new IllegalArgumentException("Unsupported command " + str + " received by " + simpleName + ".");
            }
            throw new IllegalStateException("Required value was null.");
        }

        private final <T> void scrollTo(ScrollCommandHandler<T> scrollCommandHandler, T t, ReadableArray readableArray) {
            scrollCommandHandler.scrollTo(t, new ScrollToCommandData(Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), readableArray.getBoolean(2)));
        }

        private final <T> void scrollToEnd(ScrollCommandHandler<T> scrollCommandHandler, T t, ReadableArray readableArray) {
            scrollCommandHandler.scrollToEnd(t, new ScrollToEndCommandData(readableArray.getBoolean(0)));
        }
    }

    public static final class ScrollToCommandData {
        public final boolean mAnimated;
        public final int mDestX;
        public final int mDestY;

        public ScrollToCommandData(int i, int i2, boolean z) {
            this.mDestX = i;
            this.mDestY = i2;
            this.mAnimated = z;
        }
    }

    public static final class ScrollToEndCommandData {
        public final boolean mAnimated;

        public ScrollToEndCommandData(boolean z) {
            this.mAnimated = z;
        }
    }
}
