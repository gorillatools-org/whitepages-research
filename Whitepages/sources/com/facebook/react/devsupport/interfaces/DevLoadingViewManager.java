package com.facebook.react.devsupport.interfaces;

public interface DevLoadingViewManager {
    void hide();

    void showMessage(String str);

    void updateProgress(String str, Integer num, Integer num2);
}
