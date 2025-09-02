package com.salesforce.marketingcloud.sfmcsdk;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.LifecycleListener;
import kotlin.jvm.internal.Intrinsics;

public final class SFMCSdkInitContentProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    public boolean onCreate() {
        Context applicationContext;
        Context context = getContext();
        if (context == null || (applicationContext = context.getApplicationContext()) == null) {
            return true;
        }
        SFMCSdk.Companion.getBehaviorManager$sfmcsdk_release().initIfNecessary$sfmcsdk_release(applicationContext);
        Lifecycle lifecycle = ProcessLifecycleOwner.get().getLifecycle();
        LifecycleListener.Companion companion = LifecycleListener.Companion;
        lifecycle.addObserver(companion.getInstance(applicationContext));
        ((Application) applicationContext).registerActivityLifecycleCallbacks(companion.getInstance(applicationContext));
        return true;
    }
}
