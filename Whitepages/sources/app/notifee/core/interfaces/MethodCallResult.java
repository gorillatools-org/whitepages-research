package app.notifee.core.interfaces;

import app.notifee.core.KeepForSdk;

@KeepForSdk
public interface MethodCallResult<T> {
    @KeepForSdk
    void onComplete(Exception exc, T t);
}
