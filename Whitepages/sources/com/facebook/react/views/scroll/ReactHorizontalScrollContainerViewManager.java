package com.facebook.react.views.scroll;

import android.view.View;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AndroidHorizontalScrollContentView")
public final class ReactHorizontalScrollContainerViewManager extends ReactViewManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "AndroidHorizontalScrollContentView";
    private static Integer uiManagerType;

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactViewGroup createViewInstance(int i, ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        if (uiManagerType == null) {
            uiManagerType = Integer.valueOf(ViewUtil.getUIManagerType(i));
            View createViewInstance = super.createViewInstance(i, themedReactContext, reactStylesDiffMap, stateWrapper);
            Intrinsics.checkNotNullExpressionValue(createViewInstance, "createViewInstance(...)");
            ReactViewGroup reactViewGroup = (ReactViewGroup) createViewInstance;
            uiManagerType = null;
            return reactViewGroup;
        }
        throw new IllegalStateException("Check failed.");
    }

    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Integer num = uiManagerType;
        if (num == null) {
            throw new IllegalStateException("Required value was null.");
        } else if (num.intValue() == 2) {
            return new ReactViewGroup(themedReactContext);
        } else {
            return new ReactHorizontalScrollContainerLegacyView(themedReactContext);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getUiManagerType$annotations() {
        }

        private Companion() {
        }
    }
}
