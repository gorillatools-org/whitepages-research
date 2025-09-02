package com.facebook.soloader.observer;

import com.facebook.soloader.SoFileLoader;
import com.facebook.soloader.SoSource;
import com.facebook.soloader.recovery.RecoveryStrategy;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ObserverHolder {
    private static final AtomicReference sObservers = new AtomicReference();

    public static void onLoadLibraryStart(String str, String str2, int i) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onLoadLibraryEnd(Throwable th, boolean z) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onLoadDependencyStart(String str, int i) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onLoadDependencyEnd(Throwable th, boolean z) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onSoSourceLoadLibraryStart(SoSource soSource) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onSoSourceLoadLibraryEnd(Throwable th) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onRecoveryStart(RecoveryStrategy recoveryStrategy) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onRecoveryEnd(Throwable th) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onGetDependenciesStart() {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onGetDependenciesEnd(Throwable th) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onSoFileLoaderLoadStart(SoFileLoader soFileLoader, String str, int i) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }

    public static void onSoFileLoaderLoadEnd(Throwable th) {
        Observer[] observerArr = (Observer[]) sObservers.get();
        if (observerArr != null && observerArr.length > 0) {
            Observer observer = observerArr[0];
            throw null;
        }
    }
}
