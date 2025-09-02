package com.rnlineargradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = "RNLinearGradient")
public class LinearGradientManager extends LinearGradientManagerSpec<LinearGradientView> {
    public static final String PROP_ANGLE = "angle";
    public static final String PROP_ANGLE_CENTER = "angleCenter";
    public static final String PROP_BORDER_RADII = "borderRadii";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_END_POINT = "endPoint";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POINT = "startPoint";
    public static final String PROP_USE_ANGLE = "useAngle";
    public static final String REACT_CLASS = "RNLinearGradient";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public LinearGradientView createViewInstance(ThemedReactContext themedReactContext) {
        return new LinearGradientView(themedReactContext);
    }

    @ReactProp(name = "colors")
    public void setColors(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setColors(readableArray);
    }

    @ReactProp(name = "locations")
    public void setLocations(LinearGradientView linearGradientView, ReadableArray readableArray) {
        if (readableArray != null) {
            linearGradientView.setLocations(readableArray);
        }
    }

    @ReactProp(name = "startPoint")
    public void setStartPoint(LinearGradientView linearGradientView, ReadableMap readableMap) {
        linearGradientView.setStartPoint(readableMap);
    }

    @ReactProp(name = "endPoint")
    public void setEndPoint(LinearGradientView linearGradientView, ReadableMap readableMap) {
        linearGradientView.setEndPoint(readableMap);
    }

    @ReactProp(defaultBoolean = false, name = "useAngle")
    public void setUseAngle(LinearGradientView linearGradientView, boolean z) {
        linearGradientView.setUseAngle(z);
    }

    @ReactProp(name = "angleCenter")
    public void setAngleCenter(LinearGradientView linearGradientView, ReadableMap readableMap) {
        linearGradientView.setAngleCenter(readableMap);
    }

    @ReactProp(defaultFloat = 45.0f, name = "angle")
    public void setAngle(LinearGradientView linearGradientView, float f) {
        linearGradientView.setAngle(f);
    }

    @ReactProp(name = "borderRadii")
    public void setBorderRadii(LinearGradientView linearGradientView, ReadableArray readableArray) {
        linearGradientView.setBorderRadii(readableArray);
    }
}
