package com.facebook.react.common;

public interface SurfaceDelegate {
    void createContentView(String str);

    void destroyContentView();

    void hide();

    boolean isContentViewReady();

    boolean isShowing();

    void show();
}
