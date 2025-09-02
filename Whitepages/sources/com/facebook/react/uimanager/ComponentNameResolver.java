package com.facebook.react.uimanager;

import com.facebook.proguard.annotations.DoNotStripAny;

@DoNotStripAny
public interface ComponentNameResolver {
    String[] getComponentNames();
}
