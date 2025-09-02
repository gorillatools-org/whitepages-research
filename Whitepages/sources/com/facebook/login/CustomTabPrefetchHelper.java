package com.facebook.login;

import android.net.Uri;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class CustomTabPrefetchHelper extends CustomTabsServiceConnection {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ReentrantLock lock = new ReentrantLock();

    public static final /* synthetic */ CustomTabsClient access$getClient$cp() {
        return null;
    }

    public static final /* synthetic */ CustomTabsSession access$getSession$cp() {
        return null;
    }

    public static final /* synthetic */ void access$setSession$cp(CustomTabsSession customTabsSession) {
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final void prepareSession() {
            CustomTabPrefetchHelper.lock.lock();
            CustomTabPrefetchHelper.access$getSession$cp();
            CustomTabPrefetchHelper.access$getClient$cp();
            CustomTabPrefetchHelper.lock.unlock();
        }

        public final void mayLaunchUrl(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "url");
            prepareSession();
            CustomTabPrefetchHelper.lock.lock();
            CustomTabPrefetchHelper.access$getSession$cp();
            CustomTabPrefetchHelper.lock.unlock();
        }

        public final CustomTabsSession getPreparedSessionOnce() {
            CustomTabPrefetchHelper.lock.lock();
            CustomTabPrefetchHelper.access$getSession$cp();
            CustomTabPrefetchHelper.access$setSession$cp((CustomTabsSession) null);
            CustomTabPrefetchHelper.lock.unlock();
            return null;
        }
    }
}
