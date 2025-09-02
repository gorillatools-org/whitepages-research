package com.facebook.react.uimanager;

public final class ReactRootViewTagGenerator {
    public static final ReactRootViewTagGenerator INSTANCE = new ReactRootViewTagGenerator();
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private static int nextRootViewTag = 1;

    private ReactRootViewTagGenerator() {
    }

    public static final synchronized int getNextRootViewTag() {
        int i;
        synchronized (ReactRootViewTagGenerator.class) {
            i = nextRootViewTag;
            nextRootViewTag = i + 10;
        }
        return i;
    }
}
