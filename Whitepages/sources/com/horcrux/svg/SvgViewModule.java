package com.horcrux.svg;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "RNSVGSvgViewModule")
class SvgViewModule extends NativeSvgViewModuleSpec {
    public static final String NAME = "RNSVGSvgViewModule";

    SvgViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNSVGSvgViewModule";
    }

    /* access modifiers changed from: private */
    public static void toDataURL(final int i, final ReadableMap readableMap, final Callback callback, final int i2) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                SvgView svgViewByTag = SvgViewManager.getSvgViewByTag(i);
                if (svgViewByTag == null) {
                    SvgViewManager.runWhenViewIsAvailable(i, new Runnable() {
                        public void run() {
                            SvgView svgViewByTag = SvgViewManager.getSvgViewByTag(i);
                            if (svgViewByTag != null) {
                                svgViewByTag.setToDataUrlTask(new Runnable() {
                                    public void run() {
                                        AnonymousClass1 r0 = AnonymousClass1.this;
                                        SvgViewModule.toDataURL(i, readableMap, callback, i2 + 1);
                                    }
                                });
                            }
                        }
                    });
                } else if (svgViewByTag.notRendered()) {
                    svgViewByTag.setToDataUrlTask(new Runnable() {
                        public void run() {
                            AnonymousClass1 r0 = AnonymousClass1.this;
                            SvgViewModule.toDataURL(i, readableMap, callback, i2 + 1);
                        }
                    });
                } else {
                    ReadableMap readableMap = readableMap;
                    if (readableMap != null) {
                        callback.invoke(svgViewByTag.toDataURL(readableMap.getInt("width"), readableMap.getInt("height")));
                    } else {
                        callback.invoke(svgViewByTag.toDataURL());
                    }
                }
            }
        });
    }

    @ReactMethod
    public void toDataURL(Double d, ReadableMap readableMap, Callback callback) {
        toDataURL(d.intValue(), readableMap, callback, 0);
    }
}
