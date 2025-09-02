package com.facebook.react.runtime;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.uimanager.UIConstantsProviderBinding;
import com.facebook.react.uimanager.UIManagerModuleConstantsHelper;

public final /* synthetic */ class ReactInstance$$ExternalSyntheticLambda2 implements UIConstantsProviderBinding.DefaultEventTypesProvider {
    public final NativeMap getDefaultEventTypes() {
        return Arguments.makeNativeMap(UIManagerModuleConstantsHelper.getDefaultExportableEventTypes());
    }
}
