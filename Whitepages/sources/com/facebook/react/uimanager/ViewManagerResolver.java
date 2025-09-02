package com.facebook.react.uimanager;

import java.util.Collection;

public interface ViewManagerResolver {
    ViewManager getViewManager(String str);

    Collection<String> getViewManagerNames();
}
