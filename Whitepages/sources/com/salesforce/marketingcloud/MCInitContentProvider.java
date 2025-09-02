package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.salesforce.marketingcloud.behaviors.LifecycleManager;

@SuppressLint({"UnknownNullness"})
public final class MCInitContentProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        ProcessLifecycleOwner.get().getLifecycle().addObserver(LifecycleManager.a((Application) context.getApplicationContext()));
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
